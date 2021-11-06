package com.mygdx.libgdxsecondgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TitleScreen extends ScreenAdapter {

    SecondGdxGame game;
    //screen
    private Camera camera;
    private Viewport viewport;

    //graphics
    private SpriteBatch batch;
    private Texture background;

    //timing
    private int backgroundOffset;

    //world parameters
    private final int WORLD_WIDTH = 720;
    private final int WORLD_HEIGHT = 1280;

    public TitleScreen(SecondGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        background = new Texture("RainyBackground.png");
        backgroundOffset = 0;

        batch = new SpriteBatch();
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button){
               game.setScreen(new GameScreen(game));
                return true;
            }
        });
    }
    @Override
    public void render(float deltaT){
        //scrolling background
        backgroundOffset++;
        if(backgroundOffset > WORLD_HEIGHT)
            backgroundOffset = 0;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        //background; it is a little messed up, scroll is a little wonky
        game.batch.draw(background, 25,-backgroundOffset);
        game.batch.draw(background, 25, -backgroundOffset + 360);
        game.batch.draw(background, 25, -backgroundOffset + 720);
        game.batch.draw(background, 25, -backgroundOffset + 1080);
        game.batch.draw(background, 25, -backgroundOffset + 1440);

        //text
        game.font.getData().setScale(4);
        game.font.draw(game.batch, "Welcome to ", 150, 680);
        game.font.draw(game.batch, "Jared's SpoOky Animation!", 25, 580);
        game.font.draw(game.batch, "Tap if you dare...",100 , 200);
        game.batch.end();
    }
    @Override //this resize doesn't work great
    public void resize(int w, int h){
        viewport.update(w, h, true);
        batch.setProjectionMatrix(camera.combined);
    }
    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
