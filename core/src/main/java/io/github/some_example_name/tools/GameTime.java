package io.github.some_example_name.tools;

public class GameTime {
    private float timeSeconds;
    private int hours;
    private int minutes;
    private int lastDecayMinute;
    private int lastAgeMinute;

    public GameTime() {
        timeSeconds = 0f;
        hours = 0;
        minutes = 0;
    }

    // Call this every frame with delta time
    public void update(float delta) {
        timeSeconds += delta;

        if (timeSeconds >= 60f) {
            timeSeconds -= 60f;
            minutes++;

            if (minutes >= 60) {
                minutes = 0;
                hours++;

                if (hours >= 24) {
                    hours = 0;
                }
            }
        }
    }

    public int getMinutes() {
        return minutes;
    }

    public boolean shouldDecay() {
        int currentMinutes = getMinutes();
        boolean decay = (currentMinutes - lastDecayMinute >= 2)
            || (lastDecayMinute > currentMinutes && currentMinutes < 2);
        if (decay) {
            lastDecayMinute = currentMinutes;
        }
        return decay;
    }

    public boolean shouldAge() {
        int currentMinutes = getMinutes();
        boolean age = currentMinutes > 0 && currentMinutes != lastAgeMinute;
        if (age) {
            lastAgeMinute = currentMinutes;
        }
        return age;
    }
}
