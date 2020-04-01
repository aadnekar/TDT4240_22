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


public class Settings implements Screen {

    private AnimalWar game;
    private OrthographicCamera camera;
    private Viewport gameViewport;
    private Texture bg;
    private SettingsButtons btns;
    private Texture musicOnOrOff;
    private Texture changeVolume;
    private Texture headline;



    public Settings(AnimalWar game){

        this.game = game;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        this.camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);

        bg = new Texture("backgrounds/menu-bg.png");
        btns = new SettingsButtons(game);

        musicOnOrOff = new Texture(Gdx.files.internal("Settings/Turn_music_on_or_off.png"));
        changeVolume = new Texture(Gdx.files.internal("Settings/change_volume.png"));
        headline = new Texture("Settings/settings.png");

    }


    @Override
    public void show() {

    }

    @Override
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

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        btns.disposeStage();
        bg.dispose();
    }
}
