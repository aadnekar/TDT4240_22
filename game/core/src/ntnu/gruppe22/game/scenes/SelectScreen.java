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
import ntnu.gruppe22.game.huds.SelectAnimalButtons;
import ntnu.gruppe22.game.huds.SelectScreenButtons;

/**
 * Antar at denne filen inneholder en screen som gir eg mulighet til å velge characters
 * Importerer metoder som render(), update(), dispose(), handleInput()
 */


//extend abstract class in some way?
public class SelectScreen implements Screen {

    private AnimalWar game;
    private OrthographicCamera camera;
    private SelectScreenButtons screenBtns;
    private SelectAnimalButtons animalbtns;
    private Viewport gameViewport;
    private Texture bg;

    //private Map<Player.username, animal> animalChoices;
    //private Map<Player.username, Boolean> ready;


    public SelectScreen(AnimalWar game) {
        //selectedCharacter = characters.get(0); //henter ut første i lista?

        this.game = game;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        this.camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);

        bg = new Texture("Backgrounds/menu-bg.png");
        screenBtns = new SelectScreenButtons(game);
        animalbtns = new SelectAnimalButtons(game);

    }

    public void setCharacter ( int id){

    }


        //public boolean isReady(){
        // return this.ready;
        // }

    public void setReady ( boolean ready){

    }

    @Override
    public void show () {

    }

    @Override
    public void render ( float delta){


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getSb().begin();

        game.getSb().draw(bg, 0, 0);

        game.getSb().end();

        game.getSb().setProjectionMatrix(screenBtns.getStage().getCamera().combined);
        screenBtns.getStage().draw();
        screenBtns.getStage().act();

        game.getSb().setProjectionMatrix(animalbtns.getStage().getCamera().combined);
        animalbtns.getStage().draw();
        animalbtns.getStage().act();

    }

    @Override
    public void resize ( int width, int height){
        gameViewport.update(width, height);
    }

    @Override
    public void pause () {

    }

    @Override
    public void resume () {

    }

    @Override
    public void hide () {

    }

    @Override
    public void dispose () {
        Gdx.input.setInputProcessor(null);
        bg.dispose();
        screenBtns.disposeStage();
        animalbtns.disposeStage();
    }
}

