package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.github.some_example_name.actions.Actions;
import io.github.some_example_name.pets.BluePuffle;
import io.github.some_example_name.tools.GameTime;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private BluePuffle bluePuffle;

    private float width, height;
    private float x, y;

    private GameTime gameTime;
    private int lastDecayMinute;

    private Stage stage;
    private Skin skin;
    private TextButton feedButton;
    private TextButton playButton;
    private TextButton cleanButton;
    private TextButton sleepButton;

    @Override
    public void create() {

        gameTime = new GameTime();
        lastDecayMinute = 0;

        batch = new SpriteBatch();
        bluePuffle = new BluePuffle();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Texture feedTex = new Texture("puffleo.png");
        Image feedIcon = new Image(new TextureRegionDrawable(new TextureRegion(feedTex)));
        Label feedlabel = new Label("Feed", skin);

        Table feedTable = new Table(skin);
        feedTable.add(feedIcon).size(32);

        feedTable.add(feedlabel);
        feedTable.setPosition(20, 50);
        feedTable.setBackground(skin.getDrawable("default-round"));
        feedTable.pack();
        stage.addActor(feedTable);

        Texture playTex = new Texture("play.png");
        Image playIcon = new Image(new TextureRegionDrawable(new TextureRegion(playTex)));
        Label playlabel = new Label("Play", skin);

        Table playTable = new Table(skin);
        playTable.add(playIcon).size(32);

        playTable.add(playlabel);
        playTable.setPosition(150, 50);
        playTable.setBackground(skin.getDrawable("default-round"));
        playTable.pack();
        stage.addActor(playTable);


        cleanButton = new TextButton("Clean", skin);
        cleanButton.setPosition(200, 20);
        stage.addActor(cleanButton);

        sleepButton = new TextButton("Sleep", skin);
        sleepButton.setPosition(300, 20);
        stage.addActor(sleepButton);

        float scale = 4f;
        width = bluePuffle.getTexture().getWidth() * scale;
        height = bluePuffle.getTexture().getHeight() * scale;

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        x = (screenWidth - width) / 2f;
        y = (screenHeight - height) / 2f;

        feedTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actions.feed(bluePuffle);
                System.out.println("Fed puffle. Hunger: " + bluePuffle.getHunger());
            }
        });
        playTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actions.play(bluePuffle);
                System.out.println("Exercised puffle. Play: " + bluePuffle.getPlay());
            }
        });
        cleanButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actions.clean(bluePuffle);
                System.out.println("Cleaned puffle. Cleanliness: " + bluePuffle.getCleanliness());
            }
        });
        sleepButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actions.sleep(bluePuffle);
                System.out.println("Rested puffle. Sleep: " + bluePuffle.getSleep());
            }
        });

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        gameTime.update(Gdx.graphics.getDeltaTime());

        int currentMinutes = gameTime.getMinutes();
        if (currentMinutes - lastDecayMinute >= 2 || (lastDecayMinute > currentMinutes && currentMinutes < 2)) {
            bluePuffle.loseHunger(1);
            bluePuffle.losePlay(1);
            bluePuffle.loseCleanliness(1);
            bluePuffle.loseSleep(1);
        }

        batch.begin();
        batch.draw(bluePuffle.getTexture(), x, y, width, height);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bluePuffle.dispose();
        stage.dispose();
        skin.dispose();
    }
}
