package io.github.some_example_name.pets;

import com.badlogic.gdx.graphics.Texture;

public class BluePuffle {

    private Texture bluepuffle;

    private int health;
    private int hunger;
    private int play;
    private int cleanliness;
    private int sleep;
    private int happiness;

    private final int maxHealth;
    private final int maxHunger;
    private final int maxPlay;
    private final int maxCleanliness;
    private final int maxSleep;
    private final int maxHappiness;

    public BluePuffle() {
        bluepuffle = new Texture("bluepuffle.png");

        maxHealth = 10;
        maxHunger = 10;
        maxPlay = 10;
        maxCleanliness = 10;
        maxSleep = 10;
        maxHappiness = 10;

        health = maxHealth;
        hunger = maxHunger;
        play = maxPlay;
        cleanliness = maxCleanliness;
        sleep = maxSleep;
        happiness = maxHappiness;
    }

    public Texture getTexture() {
        return bluepuffle;
    }

    public void dispose() {
        bluepuffle.dispose();
    }

    // Health Accessors
    public int getHealth() {
        return health;
    }

    public void loseHealth(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) health = maxHealth;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public float getHealthPercentage() {
        return (float) health / maxHealth;
    }

    // Hunger Accessors
    public int getHunger() {
        return hunger;
    }

    public void loseHunger(int amount) {
        hunger -= amount;
        if (hunger < 0) hunger = 0;
    }

    public void feed(int amount) {
        hunger += amount;
        if (hunger > maxHunger) hunger = maxHunger;
    }

    public float getHungerPercentage() {
        return (float) hunger / maxHunger;
    }

    // Exercise Accessors
    public int getPlay() {
        return play;
    }

    public void losePlay(int amount) {
        play -= amount;
        if (play < 0) play = 0;
    }

    public void play(int amount) {
        play += amount;
        if (play > maxPlay) play = maxPlay;
    }


    // Cleanliness Accessors
    public int getCleanliness() {
        return cleanliness;
    }

    public void loseCleanliness(int amount) {
        cleanliness -= amount;
        if (cleanliness < 0) cleanliness = 0;
    }

    public void clean(int amount) {
        cleanliness += amount;
        if (cleanliness > maxCleanliness) cleanliness = maxCleanliness;
    }


    // Sleep Accessors
    public int getSleep() {
        return sleep;
    }

    public void loseSleep(int amount) {
        sleep -= amount;
        if (sleep < 0) sleep = 0;
    }

    public void rest(int amount) {
        sleep += amount;
        if (sleep > maxSleep) sleep = maxSleep;
    }


    public int getHappiness() {
        return happiness;
    }

    public void addHappiness(int amount) {
        happiness += amount;
    }
}
