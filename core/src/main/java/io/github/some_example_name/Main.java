package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.some_example_name.pets.BluePuffle;
import io.github.some_example_name.tools.GameTime;
import io.github.some_example_name.tools.TableManager;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private BluePuffle bluePuffle;
    private TableManager tableManager;

    private GameTime gameTime;
    private int lastDecayMinute;
    private int lastAgeMinute;

    private Texture spriteSheet;
    private Texture background;

    @Override
    public void create() {
        spriteSheet = new Texture(Gdx.files.internal("spritesheet.png"));
        background = new Texture(Gdx.files.internal("background.png"));

        bluePuffle = new BluePuffle();
        tableManager = new TableManager(spriteSheet, bluePuffle);

        gameTime = new GameTime();
        lastDecayMinute = 0;
        lastAgeMinute = -1;

        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        gameTime.update(Gdx.graphics.getDeltaTime());


        if (gameTime.shouldDecay()) {
            bluePuffle.loseHunger(1);
            bluePuffle.losePlay(1);
            bluePuffle.loseCleanliness(1);
            bluePuffle.loseSleep(1);
            bluePuffle.loseHappiness(1);
            tableManager.updateHappinessUI();
        }

        if (gameTime.shouldAge()) {
            bluePuffle.addAge(1);
        }

        batch.begin();
        batch.draw(background, -100, 0);
        batch.draw(bluePuffle.getTexture(), 225, 170, 200, 200);
        batch.end();

        tableManager.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        batch.dispose();
        spriteSheet.dispose();
        background.dispose();
        tableManager.dispose();
    }
}
