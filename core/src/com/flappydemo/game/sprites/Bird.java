package com.flappydemo.game.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    public static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velosity;

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
        velosity.add(0, GRAVITY, 0);
        velosity.scl(dt);
        position.add(0, velosity.y, 0);

        velosity.scl(1 / dt);


    }
}
