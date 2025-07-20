package io.github.some_example_name.tools;

public class GameTime {
    private float timeSeconds;
    private int hours;
    private int minutes;

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

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public float getTimeSeconds() {
        return timeSeconds;
    }

    public String getFormattedTime() {
        return String.format("%02d:%02d", hours, minutes);
    }

    public void reset() {
        timeSeconds = 0f;
        hours = 0;
        minutes = 0;
    }
}
