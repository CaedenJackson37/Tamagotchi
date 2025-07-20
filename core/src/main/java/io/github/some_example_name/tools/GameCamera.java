package io.github.some_example_name.tools;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameCamera {

    private OrthographicCamera camera;
    private float worldWidth, worldHeight;

    public GameCamera(float worldWidth, float worldHeight) {

        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.camera = new OrthographicCamera(worldWidth, worldHeight);
        centerCamera();
    }

    public void centerCamera() {
        camera.position.set(worldWidth / 2f, worldHeight / 2f, 0);
        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
