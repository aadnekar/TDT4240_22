package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.SettingsButtons;


public class Settings extends Menu {

    private Texture musicOnOrOff;
    private Texture changeVolume;
    private Texture headline;

    public Settings(AnimalWar game){
        this.initializeMenu(game);
        btns = new SettingsButtons(game);

        musicOnOrOff = new Texture(Gdx.files.internal("settings/turn-music-on-or-off.png"));
        changeVolume = new Texture(Gdx.files.internal("settings/change-volume.png"));
        headline = new Texture("settings/settings.png");
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        game.getSb().draw(musicOnOrOff, GameInfo.WIDTH/2 - musicOnOrOff.getWidth()/2, GameInfo.HEIGHT/2);
        game.getSb().draw(changeVolume,GameInfo.WIDTH/2 - changeVolume.getWidth()/2 , GameInfo.HEIGHT/2 - 80);
        game.getSb().draw(headline,GameInfo.WIDTH/2 - headline.getWidth()/2 , GameInfo.HEIGHT - 150);
        game.getSb().end();

        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
        btns.getStage().act();
    }

    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

    public void dispose() {
        btns.disposeStage();
        bg.dispose();
    }
}
