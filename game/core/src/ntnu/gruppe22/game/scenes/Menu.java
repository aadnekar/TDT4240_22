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

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        game.getSb().end();

    }

    @Override
    public void resize(int width, int height){
        gameViewport.update(width, height);
    }

    @Override
    public void dispose(){
        Gdx.input.setInputProcessor(null);
        bg.dispose();
        btns.disposeStage();

    }
    public void show(){}
    public void pause(){}
    public void resume(){}
    public void hide(){}







}
