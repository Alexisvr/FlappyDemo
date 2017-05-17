package com.flappydemo.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.flappydemo.game.FlappyDemo;
import com.flappydemo.game.sprites.Bird;
import com.flappydemo.game.sprites.Tube;

public class PlayState extends State {

    public static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture bg;

    private Array<Tube> tubes;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("bg.png");

        tubes = new Array<Tube>();

        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i* (TUBE_SPACING + Tube.TUBE_WIDHT)));

        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 80;

        for (Tube tube : tubes) {
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDHT + TUBE_SPACING) * TUBE_COUNT));
            }
        }
        camera.update();

    }

    @Override
    public void render (SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
            for (Tube tube : tubes) {
                sb.draw(tube.getTopTube(), tube.getPosBotTube().x, tube.getPosTopTube().y);
                sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
            }

        sb.end();

    }

    @Override
    public void dispose() {
        //bird.dispose();

    }
}

