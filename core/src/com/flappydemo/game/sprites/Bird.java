package com.flappydemo.game.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velosity;
    private static final int MOVEMENT = 100;

    private Texture bird;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }

    public void update (float dt) {
        if (position.y > 0)
            velosity.add(0, GRAVITY, 0);
        velosity.scl(dt);
        position.add(MOVEMENT *dt, velosity.y, 0);
        if (position.y < 0)
            position.y = 0;

        velosity.scl(1 / dt);
    }

    public void jump () {
        velosity.y = 250;
    }
}
