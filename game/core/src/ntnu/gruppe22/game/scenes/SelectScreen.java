package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameMusic;
import ntnu.gruppe22.game.huds.MainMenuButtons;
import ntnu.gruppe22.game.huds.SelectScreenButtons;

/**
 * Antar at denne filen inneholder en screen som gir eg mulighet til å velge characters
 * Importerer metoder som render(), update(), dispose(), handleInput()
 */


//extend abstract class in some way?
public class SelectScreen extends Object implements Screen {

    private Array<Character> characters;
    private Character selectedCharacter;
    private boolean ready;

    private AnimalWar game;

    private OrthographicCamera camera;
    private Viewport gameViewport;

    private Texture bg;
    private SelectScreenButtons btns;
    private GameMusic gameMusic;




    public SelectScreen(AnimalWar game){
        ready = false;
        //selectedCharacter = characters.get(0); //henter ut første i lista?

        this.game = game;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        this.camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);

        bg = new Texture("Backgrounds/menu-bg.png");
        btns = new SelectScreenButtons(game,gameMusic);




    }

    public Character getSelectedCharacter() {
        return selectedCharacter;
    }

    public Array<Character> getCharacters() {
        return characters;
    }

    public void setSelectedCharacter(Character character){
        this.selectedCharacter = character;
    }

    public boolean isReady(){
        return this.ready;
    }

    public void setReady(boolean ready){
        this.ready = ready;
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

        bg.dispose();
        btns.disposeStage();
    }
}
