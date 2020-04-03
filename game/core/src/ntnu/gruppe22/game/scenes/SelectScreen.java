package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.SelectScreenButtons;

/**
 * Denne filen inneholder en screen som gir deg mulighet til å velge characters
 * Importerer metoder som render(), update(), dispose(), handleInput()
 */


//extend abstract class in some way?
public class SelectScreen implements Screen {


    private AnimalWar game;
    private OrthographicCamera camera;
    private SelectScreenButtons screenBtns;
    private Viewport gameViewport;
    private Texture bg;
    private int playerNumber;
    private TextField player1;
    private TextField player2;





    public SelectScreen(AnimalWar game, int playerNumber ) {
        //selectedCharacter = characters.get(0); //henter ut første i lista?

        this.game = game;
        this.playerNumber=playerNumber;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        this.camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);

        screenBtns = new SelectScreenButtons(game, playerNumber);

       /* if(playerNumber==0){
            //player1 = new TextField(" ",new AssetLoader(1));
        }
        else if (playerNumber==1){
            //player2 = new TextField(" ",new AssetLoader(1));

        }*/



        bg = new Texture("backgrounds/menu-bg.png");


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
    }
}

