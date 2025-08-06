package io.github.some_example_name.actions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.some_example_name.pets.BluePuffle;

public class Actions {

    BluePuffle bluePuffle;
    public Texture spriteSheet;


    public static void feed(BluePuffle puffle) {
        puffle.feed(2);
        puffle.heal(2);
        puffle.addHappiness(2);
    }

    public static void play(BluePuffle puffle) {
        puffle.play(2);
        puffle.addHappiness(2);
    }

    public static void clean(BluePuffle puffle) {
        puffle.clean(2);
        puffle.addHappiness(2);
    }

    public static void sleep(BluePuffle puffle) {
        puffle.rest(2);
        puffle.addHappiness(2);
    }
}
