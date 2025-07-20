package io.github.some_example_name.actions;

import io.github.some_example_name.pets.BluePuffle;

public class Actions {
    public static void feed(BluePuffle puffle) {
        puffle.feed(2);
        puffle.heal(2);
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
