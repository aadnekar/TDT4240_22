package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.HashMap;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.SelectAnimalButtons;
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
    private SelectAnimalButtons animalbtns;
    private Viewport gameViewport;
    private Texture bg;
    private Boolean ready;
    private Stage stage;


    private HashMap<Integer, ArrayList> animalChoices;
    private ArrayList<Integer> addedAnimal;
    //private Map<Player.username, Boolean> ready;


    public SelectScreen(AnimalWar game) {
        //selectedCharacter = characters.get(0); //henter ut første i lista?

        this.game = game;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        this.camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);
        stage = new Stage(gameViewport, game.getSb());

        animalbtns = new SelectAnimalButtons(game, stage);
        screenBtns = new SelectScreenButtons(game, stage);


        bg = new Texture("backgrounds/menu-bg.png");

        animalChoices = new HashMap<>();
        addedAnimal = new ArrayList<>();
        this.ready = false;

    }

    public void setCharacter ( int id){
        System.out.println(id);
        if (addedAnimal.contains(id)){
            addedAnimal.remove(id);
        }
        else {
            addedAnimal.add(id);
        }
        if (isReady()){
            animalChoices.put(1,addedAnimal);
            System.out.println(animalChoices);
        }

    }


    public boolean isReady(){
         return this.ready;
    }

    public void setReady ( boolean ready){
        this.ready = ready;
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

        game.getSb().setProjectionMatrix(stage.getCamera().combined);
        stage.draw();
        stage.act();
        /*
        screenBtns.getStage().draw();
        screenBtns.getStage().act();

        animalbtns.getStage().draw();
        animalbtns.getStage().act();
*/
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

