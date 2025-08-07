package io.github.some_example_name.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.some_example_name.actions.Actions;
import io.github.some_example_name.pets.BluePuffle;

public class TableManager {
    private Texture spriteSheet;
    private Skin skin;
    private Stage stage;
    private BluePuffle bluePuffle;

    private TextureRegion happinessTex;
    private Image happinessIcon;
    private Texture healthTex;

    public TableManager(Texture spriteSheet, BluePuffle bluePuffle) {
        this.spriteSheet = spriteSheet;
        this.bluePuffle = bluePuffle;

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        createFeedTable();
        createPlayTable();
        createCleanTable();
        createSleepTable();
        createHealthTable();
        createAgeTable();
        createHungerTable();
        createHappinessTable();
    }

    private void createFeedTable() {
        TextureRegion feedTex = new TextureRegion(spriteSheet, 103, 0, 32, 32);
        Image feedIcon = new Image(new TextureRegionDrawable(feedTex));

        Table feedTable = new Table(skin);
        feedTable.add(feedIcon).size(100);
        feedTable.setPosition(75, 50);
        feedTable.pack();
        stage.addActor(feedTable);

        feedTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actions.feed(bluePuffle);
                updateHappinessUI();
                System.out.println("Fed puffle. Hunger: " + bluePuffle.getHunger());
                System.out.println(bluePuffle.getHappiness());
            }
        });
    }

    private void createPlayTable() {
        TextureRegion playTex = new TextureRegion(spriteSheet, 171, 0, 32, 32);
        Image playIcon = new Image(new TextureRegionDrawable(playTex));

        Table playTable = new Table(skin);
        playTable.add(playIcon).size(100);
        playTable.setPosition(225, 50);
        playTable.pack();
        stage.addActor(playTable);

        playTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actions.play(bluePuffle);
                updateHappinessUI();
                System.out.println("Exercised puffle. Play: " + bluePuffle.getPlay());
            }
        });
    }

    private void createCleanTable() {
        TextureRegion cleanTex = new TextureRegion(spriteSheet, 69, 0, 32, 32);
        Image cleanIcon = new Image(new TextureRegionDrawable(cleanTex));

        Table cleanTable = new Table(skin);
        cleanTable.add(cleanIcon).size(100);
        cleanTable.setPosition(350, 50);
        cleanTable.pack();
        stage.addActor(cleanTable);

        cleanTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actions.clean(bluePuffle);
                updateHappinessUI();
                System.out.println("Cleaned puffle. Cleanliness: " + bluePuffle.getCleanliness());
            }
        });
    }

    private void createSleepTable() {
        TextureRegion sleepTex = new TextureRegion(spriteSheet, 239, 0, 32, 32);
        Image sleepIcon = new Image(new TextureRegionDrawable(sleepTex));

        Table sleepTable = new Table(skin);
        sleepTable.add(sleepIcon).size(100);
        sleepTable.setPosition(500, 50);
        sleepTable.pack();
        stage.addActor(sleepTable);

        sleepTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actions.sleep(bluePuffle);
                updateHappinessUI();
                System.out.println("Rested puffle. Sleep: " + bluePuffle.getSleep());
            }
        });
    }

    private void createHealthTable() {
        healthTex = new Texture("health.png");
        Image healthIcon = new Image(new TextureRegionDrawable(healthTex));

        Label healthtip = new Label(String.valueOf(bluePuffle.getHealth()), skin);
        healthtip.setVisible(false);
        healthtip.setPosition(365, 420);

        Table healthTable = new Table(skin);
        healthTable.add(healthIcon).size(60);
        healthTable.setPosition(350, 360);
        healthTable.pack();
        stage.addActor(healthTable);
        stage.addActor(healthtip);
        healthIcon.addListener(new InputListener() {
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                healthtip.setVisible(true);
                return true;
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
                healthtip.setVisible(false);
            }
        });
    }

    private void createAgeTable() {
        TextureRegion ageTex = new TextureRegion(spriteSheet, 0, 0, 32, 32);
        Image ageIcon = new Image(new TextureRegionDrawable(ageTex));

        Label agetip = new Label(String.valueOf(bluePuffle.getAge()), skin);
        agetip.setVisible(false);
        agetip.setPosition(540, 420);

        Table ageTable = new Table(skin);
        ageTable.add(ageIcon).size(100);
        ageTable.setPosition(500, 350);
        ageTable.pack();
        stage.addActor(ageTable);
        stage.addActor(agetip);
        ageIcon.addListener(new InputListener() {
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                agetip.setVisible(true);
                return true;
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
                agetip.setVisible(false);
            }
        });
    }

    private void createHungerTable() {
        Texture hungerTex = new Texture("puffleo.png");
        Image hungerIcon = new Image(new TextureRegionDrawable(hungerTex));

        Label hungertip = new Label(String.valueOf(bluePuffle.getHunger()),skin);
        hungertip.setVisible(false);
        hungertip.setPosition(90, 420);

        Table hungerTable = new Table(skin);
        hungerTable.add(hungerIcon).size(60);
        hungerTable.setPosition(75, 360);
        hungerTable.pack();
        stage.addActor(hungerTable);
        stage.addActor(hungertip);
        hungerIcon.addListener(new InputListener() {
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                hungertip.setVisible(true);
                return true;
            }
        @Override
        public void exit(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
                hungertip.setVisible(false);
            }
        });
    }

    private void createHappinessTable() {
        int happiness = bluePuffle.getHappiness();
        happinessTex = getHappinessTexture(happiness);
        happinessIcon = new Image(new TextureRegionDrawable(happinessTex));

        Label happinesstip = new Label(String.valueOf(bluePuffle.getHappiness()), skin);
        happinesstip.setVisible(false);
        happinesstip.setPosition(235, 420);

        Table happinessTable = new Table(skin);
        happinessTable.add(happinessIcon).size(100);
        happinessTable.setPosition(200, 360);
        happinessTable.pack();
        stage.addActor(happinessTable);
        stage.addActor(happinesstip);
        happinessIcon.addListener(new InputListener() {
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                happinesstip.setVisible(true);
                return true;
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
                happinesstip.setVisible(false);
            }
        });
    }

    public void updateHappinessUI() {
        int happiness = bluePuffle.getHappiness();
        happinessTex = getHappinessTexture(happiness);
        happinessIcon.setDrawable(new TextureRegionDrawable(new TextureRegion(happinessTex)));
    }

    private TextureRegion getHappinessTexture(int happiness) {
        if (happiness >= 7) {
            return new TextureRegion(spriteSheet, 36, 0, 32, 34);
        } else if (happiness >= 4) {
            return new TextureRegion(spriteSheet, 138, 0, 32, 34);
        } else {
            return new TextureRegion(spriteSheet, 206, 0, 32, 34);
        }
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
        healthTex.dispose();
    }
}
