package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.HashMap;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.SelectScreenButtons;

/**
 * Denne filen inneholder en screen som gir deg mulighet til å velge characters
 * Importerer metoder som render(), update(), dispose(), handleInput()
 */

public class SelectScreen extends Menu{

    public SelectScreen(AnimalWar game) {
        this.initializeMenu(game);
        btns = new SelectScreenButtons(game);

    }

    public void render ( float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        game.getSb().end();

        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
        btns.getStage().act();
    }

    public void resize ( int width, int height){
        gameViewport.update(width, height);
    }

    public void dispose () {
        Gdx.input.setInputProcessor(null);
        bg.dispose();
        btns.disposeStage();
    }
}

