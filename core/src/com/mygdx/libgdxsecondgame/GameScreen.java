package com.mygdx.libgdxsecondgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {

    SecondGdxGame game;

    float touchX = 300;
    float touchY = 300;
    float touchZone = 50;

    public GameScreen(SecondGdxGame game) {
        this.game = game;
    }
    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button){
                int renderY = Gdx.graphics.getHeight() - screenY;

                if(Vector2.dst(touchX, touchY, screenX, renderY) < touchZone)
                  game.setScreen(new TitleScreen(game));

                return true;
            }
        });
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.timePassed += Gdx.graphics.getDeltaTime();
        game.batch.draw(game.animation.getKeyFrame(game.timePassed, true), touchX, touchY);
        game.batch.end();
    }
    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

}
