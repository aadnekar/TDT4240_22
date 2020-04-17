package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.MapButtons;

public class Maps implements Screen {
    private AnimalWar game;
    private ShapeRenderer sr;
    private OrthographicCamera camera;
    private Viewport gameViewport;
    private Texture bg;
    private Texture title;
    private MapButtons mapButtons;

    public Maps(AnimalWar game) {
        this.game = game;
        sr = new ShapeRenderer();

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        this.camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);

        mapButtons = new MapButtons(game);

        bg = new Texture("backgrounds/menu-bg.png");
        title = new Texture("settings/choose-map.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw menu background and title
        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        game.getSb().draw(title,190 , GameInfo.HEIGHT - 80, 334, 43);
        game.getSb().end();

        // Draw rectangle around chosen map
        sr.setProjectionMatrix(camera.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(new Color(255,255,0,0));
        if(mapButtons.getPositionY() > 0){
            sr.rect(mapButtons.getPositionX() - 3, mapButtons.getPositionY() - 3, mapButtons.getRectangleX() + 6, mapButtons.getRectangleY() + 6);
        }
        sr.end();

        //Draw map buttons
        game.getSb().setProjectionMatrix(mapButtons.getStage().getCamera().combined);
        mapButtons.getStage().draw();
        mapButtons.getStage().act();


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
        Gdx.input.setInputProcessor(null);
        bg.dispose();
        mapButtons.disposeStage();
    }
}
