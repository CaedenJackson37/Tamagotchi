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
    private int lastAgeMinute;

    private Stage stage;
    private Skin skin;
    private Image happinessIcon;
    private TextureRegion happinessTex;

    public Texture spriteSheet;


    @Override
    public void create() {
        bluePuffle = new BluePuffle();
        spriteSheet = new Texture(Gdx.files.internal("spritesheet.png"));

        gameTime = new GameTime();
        lastDecayMinute = 0;
        lastAgeMinute = -1;

        batch = new SpriteBatch();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        //Feed Table
        TextureRegion feedTex = new TextureRegion(spriteSheet, 103, 0, 32, 32);
        Image feedIcon = new Image(new TextureRegionDrawable(new TextureRegion(feedTex)));

        Table feedTable = new Table(skin);
        feedTable.add(feedIcon).size(100);
        feedTable.setPosition(75, 50);
        feedTable.pack();
        stage.addActor(feedTable);

        //Play Table
        TextureRegion playTex = new TextureRegion(spriteSheet, 171, 0, 32, 32);
        Image playIcon = new Image(new TextureRegionDrawable(new TextureRegion(playTex)));

        Table playTable = new Table(skin);
        playTable.add(playIcon).size(100);
        playTable.setPosition(225, 50);
        playTable.pack();
        stage.addActor(playTable);

        //Clean Table
        TextureRegion cleanTex = new TextureRegion(spriteSheet, 69, 0, 32, 32);
        Image cleanIcon = new Image(new TextureRegionDrawable(new TextureRegion(cleanTex)));

        Table cleanTable = new Table(skin);
        cleanTable.add(cleanIcon).size(100);
        cleanTable.setPosition(350, 50);
        cleanTable.pack();
        stage.addActor(cleanTable);

        //Sleep Table
        TextureRegion sleepTex = new TextureRegion(spriteSheet, 239, 0, 32, 32);
        Image sleepIcon = new Image(new TextureRegionDrawable(new TextureRegion(sleepTex)));

        Table sleepTable = new Table(skin);
        sleepTable.add(sleepIcon).size(100);
        sleepTable.setPosition(500, 50);
        sleepTable.pack();
        stage.addActor(sleepTable);

        //Happiness Table
        int happiness = bluePuffle.getHappiness();
        TextureRegion happinessTex = getHappinessTexture(happiness);
        happinessIcon = new Image(new TextureRegionDrawable(new TextureRegion(happinessTex)));


        Table happinessTable = new Table(skin);
        happinessTable.add(happinessIcon).size(100);
        happinessTable.setPosition(200, 360);
        happinessTable.pack();
        stage.addActor(happinessTable);


        //Health Table
        Texture healthTex = new Texture("health.png");
        Image healthIcon = new Image(new TextureRegionDrawable(new TextureRegion(healthTex)));
        Label healthlabel = new Label(String.valueOf(bluePuffle.getHealth()), skin);

        Table healthTable = new Table(skin);
        healthTable.add(healthIcon).size(80);
        healthTable.add(healthlabel);
        healthTable.setPosition(350, 350);
        healthTable.pack();
        stage.addActor(healthTable);

        //Age Table
        TextureRegion ageTex = new TextureRegion(spriteSheet, 0, 0, 32, 32);
        Image ageIcon = new Image(new TextureRegionDrawable(new TextureRegion(ageTex)));
        Label agelabel = new Label(String.valueOf(bluePuffle.getAge()), skin);

        Table ageTable = new Table(skin);
        ageTable.add(ageIcon).size(100);
        ageTable.add(agelabel).padLeft(10);
        ageTable.setPosition(500, 350);
        ageTable.pack();
        stage.addActor(ageTable);

        //Hunger Table
        Texture hungerTex = new Texture("time.png");
        Image hungerIcon = new Image(new TextureRegionDrawable(new TextureRegion(hungerTex)));
        Label hungerlabel = new Label(String.valueOf(bluePuffle.getHunger()), skin);

        Table hungerTable = new Table(skin);
        hungerTable.add(hungerIcon).size(32);
        hungerTable.add(hungerlabel);
        hungerTable.setPosition(75,400);
        hungerTable.pack();
        stage.addActor(hungerTable);


        //Listener for Feed Table
        feedTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actions.feed(bluePuffle);
                updateHappinessUI();
                System.out.println("Fed puffle. Hunger: " + bluePuffle.getHunger());
                System.out.println(bluePuffle.getHappiness());
            }
        });

        //Listener for Play Table
        playTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actions.play(bluePuffle);
                updateHappinessUI();
                System.out.println("Exercised puffle. Play: " + bluePuffle.getPlay());
            }
        });

        //Listener for Clean Table
        cleanTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actions.clean(bluePuffle);
                updateHappinessUI();
                System.out.println("Cleaned puffle. Cleanliness: " + bluePuffle.getCleanliness());
            }
        });

        //Listener for Sleep Table
        sleepTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actions.sleep(bluePuffle);
                updateHappinessUI();
                System.out.println("Rested puffle. Sleep: " + bluePuffle.getSleep());
            }
        });
    }
    //Gets Texture for Happiness Table
    private TextureRegion getHappinessTexture(int happiness) {
        if (happiness >= 7) {
            return new TextureRegion(spriteSheet, 36, 0, 32, 32);
        } else if (happiness >= 4) {
            return new TextureRegion(spriteSheet, 138, 0, 32, 32);
        } else {
            return new TextureRegion(spriteSheet, 206, 0, 32, 32);
        }
    }

    //Updates Happiness and Texture when called
    private void updateHappinessUI() {
        int happiness = bluePuffle.getHappiness();


        happinessTex = getHappinessTexture(happiness);
        happinessIcon.setDrawable(new TextureRegionDrawable(new TextureRegion(happinessTex)));
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
            bluePuffle.loseHappiness(1);
            updateHappinessUI();

            lastDecayMinute = currentMinutes;
        }

        if (currentMinutes > 0 && currentMinutes != lastAgeMinute) {
            bluePuffle.addAge(1);
            lastAgeMinute = currentMinutes;
        }

        batch.begin();
        batch.draw(bluePuffle.getTexture(), 225, 170, 200,200);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        spriteSheet.dispose();
        stage.dispose();
        skin.dispose();
    }
}
