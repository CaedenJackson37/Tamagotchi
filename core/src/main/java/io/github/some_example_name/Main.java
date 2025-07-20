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
    private Label happinessLabel;
    private Texture happinessTex;


    @Override
    public void create() {
        bluePuffle = new BluePuffle();

        gameTime = new GameTime();
        lastDecayMinute = 0;
        lastAgeMinute = -1;

        batch = new SpriteBatch();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        //Feed Table
        Texture feedTex = new Texture("puffleo.png");
        Image feedIcon = new Image(new TextureRegionDrawable(new TextureRegion(feedTex)));
        Label feedlabel = new Label("Feed", skin);

        Table feedTable = new Table(skin);
        feedTable.add(feedIcon).size(32);
        feedTable.add(feedlabel);
        feedTable.setPosition(75, 50);
        feedTable.setBackground(skin.getDrawable("default-round"));
        feedTable.pack();
        stage.addActor(feedTable);

        //Play Table
        Texture playTex = new Texture("play.png");
        Image playIcon = new Image(new TextureRegionDrawable(new TextureRegion(playTex)));
        Label playlabel = new Label("Play", skin);

        Table playTable = new Table(skin);
        playTable.add(playIcon).size(32);
        playTable.add(playlabel);
        playTable.setPosition(225, 50);
        playTable.setBackground(skin.getDrawable("default-round"));
        playTable.pack();
        stage.addActor(playTable);

        //Clean Table
        Texture cleanTex = new Texture("soap.png");
        Image cleanIcon = new Image(new TextureRegionDrawable(new TextureRegion(cleanTex)));
        Label cleanlabel = new Label("Clean", skin);

        Table cleanTable = new Table(skin);
        cleanTable.add(cleanIcon).size(32);
        cleanTable.add(cleanlabel);
        cleanTable.setPosition(350, 50);
        cleanTable.setBackground(skin.getDrawable("default-round"));
        cleanTable.pack();
        stage.addActor(cleanTable);

        //Sleep Table
        Texture sleepTex = new Texture("sleep.png");
        Image sleepIcon = new Image(new TextureRegionDrawable(new TextureRegion(sleepTex)));
        Label sleeplabel = new Label("Sleep", skin);

        Table sleepTable = new Table(skin);
        sleepTable.add(sleepIcon).size(32);
        sleepTable.add(sleeplabel);
        sleepTable.setPosition(500, 50);
        sleepTable.setBackground(skin.getDrawable("default-round"));
        sleepTable.pack();
        stage.addActor(sleepTable);

        //Happiness Table
        int happiness = bluePuffle.getHappiness();
        happinessTex = getHappinessTexture(happiness);
        happinessIcon = new Image(new TextureRegionDrawable(new TextureRegion(happinessTex)));
        happinessLabel = new Label(String.valueOf(happiness), skin);


        Table happinessTable = new Table(skin);
        happinessTable.add(happinessIcon).size(32);
        happinessTable.add(happinessLabel);
        happinessTable.setPosition(225, 400);
        happinessTable.setBackground(skin.getDrawable("default-round"));
        happinessTable.pack();
        stage.addActor(happinessTable);


        //Health Table
        Texture healthTex = new Texture("health.png");
        Image healthIcon = new Image(new TextureRegionDrawable(new TextureRegion(healthTex)));
        Label healthlabel = new Label(String.valueOf(bluePuffle.getHealth()), skin);

        Table healthTable = new Table(skin);
        healthTable.add(healthIcon).size(32);
        healthTable.add(healthlabel);
        healthTable.setPosition(350, 400);
        healthTable.setBackground(skin.getDrawable("default-round"));
        healthTable.pack();
        stage.addActor(healthTable);

        //Age Table
        Texture ageTex = new Texture("time.png");
        Image ageIcon = new Image(new TextureRegionDrawable(new TextureRegion(ageTex)));
        Label agelabel = new Label(String.valueOf(bluePuffle.getAge()), skin);

        Table ageTable = new Table(skin);
        ageTable.add(ageIcon).size(32);
        ageTable.add(agelabel);
        ageTable.setPosition(500, 400);
        ageTable.setBackground(skin.getDrawable("default-round"));
        ageTable.pack();
        stage.addActor(ageTable);


        //Makes Puffle Sprite Bigger
        float scale = 4f;
        width = bluePuffle.getTexture().getWidth() * scale;
        height = bluePuffle.getTexture().getHeight() * scale;

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        x = (screenWidth - width) / 2f;
        y = (screenHeight - height) / 2f;

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
    private Texture getHappinessTexture(int happiness) {
        if (happiness >= 7) {
            return new Texture("happy.png");
        } else if (happiness >= 4) {
            return new Texture("moderate.png");
        } else {
            return new Texture("unhappy.png");
        }
    }

    //Updates Happiness and Texture when called
    private void updateHappinessUI() {
        int happiness = bluePuffle.getHappiness();

        // Dispose old texture
        if (happinessTex != null) happinessTex.dispose();

        happinessTex = getHappinessTexture(happiness);
        happinessIcon.setDrawable(new TextureRegionDrawable(new TextureRegion(happinessTex)));

        happinessLabel.setText(String.valueOf(happiness));
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
