package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.Buttons;

public abstract class Menu implements Screen {
    protected AnimalWar game;
    protected OrthographicCamera camera;
    protected Viewport gameViewport;
    protected Texture bg;
    protected Buttons btns;

    public void initializeMenu(AnimalWar game){
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        this.camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);
        bg = new Texture("backgrounds/menu-bg.png");

    }

    public abstract void render(float delta);
    public abstract void resize(int width, int height);
    public abstract void dispose();

    public void show(){}
    public void pause(){}
    public void resume(){}
    public void hide(){}







}
