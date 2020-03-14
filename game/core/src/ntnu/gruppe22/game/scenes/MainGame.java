package ntnu.gruppe22.game.scenes;
//import Map from project

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.MainMenuButtons;
import ntnu.gruppe22.game.states.Animal;

public class MainGame implements Screen {

    AnimalWar game;

    private OrthographicCamera camera;
    private Viewport gameViewport;

    private Texture bg;
    private Settings settings;

    private ArrayList<Animal> charactersPlayer1;
    private ArrayList<Animal> charactersPlayer2;
    private Animal currentCaracter;
    private int currentTurn;

    BitmapFont font;

    static int interval;
    static Timer timer;

    private int delay;
    private int period;

    //Maps?


    public MainGame(AnimalWar game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        this.camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);

        bg = new Texture("Backgrounds/menu-bg.png");
        settings = new Settings();
        settings.setMusic(true);

        //legger ved to animals i første omgang. Videre vil vi gi mulighet til fler.
        //posisjon er random, dette må endres etter gitt map
        Animal player1 = new Animal(GameInfo.WIDTH/2, GameInfo.HEIGHT/2);
        Animal player2 = new Animal(GameInfo.WIDTH/2-50, GameInfo.HEIGHT/2-50);
        charactersPlayer1.add(player1);
        charactersPlayer2.add(player2);

        currentTurn = 0;

        font = new BitmapFont();
        startTimer(30);
        //import map in some way?


    }


    @Override
    public void render(float dt) {
        //sjekker om tiden har gått ut
        if(interval == 0){
            setCurrentTurn(dt);
            startTimer(30);
        }


        game.getSb().begin();
        font.draw(game.getSb(), String.valueOf(interval), 50, 50);
        game.getSb().end();

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

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

    }

    private void startTimer(int secs){
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = secs;
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                setInterval();

            }
        }, delay, period);
    }

    private static final int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }

    //timer for game
    public float getPublicTime(){
        return 2;
    }

    //forandring fra navn i innlevering
    public Animal getCurrentAnimal(){
        return this.currentCaracter;
    }

    //vil lagre hver Animal med en index hos hver spiller
    //får neste Animal i rekken
    //antar at vi må sette en ny currencharacter i denne metoden
    public Animal getNextCharacter(){
        if(currentTurn == 0){
            int prev = charactersPlayer1.indexOf(currentCaracter);
            if(prev > charactersPlayer1.size()+1 || charactersPlayer1.size() == 1){
                setCurrentCaracter(charactersPlayer1.get(0));
                return charactersPlayer1.get(0);
            } else{
                setCurrentCaracter(charactersPlayer1.get(prev + 1));
                return charactersPlayer1.get(prev + 1);
            }

        } else {
            int prev = charactersPlayer2.indexOf(currentCaracter);
            if(prev > charactersPlayer2.size()+1 || charactersPlayer2.size() == 1){
                setCurrentCaracter(charactersPlayer1.get(0));
                return charactersPlayer2.get(0);
            } else{
                setCurrentCaracter(charactersPlayer2.get(prev + 1));
                return charactersPlayer2.get(prev + 1);
            }
        }

    }

    //skal vi ha runder med i spillet i det hele tatt?
    //dt?
    public void setCurrentTurn(float dt){
        if(currentTurn == 1){
            this.currentTurn = 0;
            //update(dt);
        } else if(currentTurn == 0){
            this.currentTurn = 1;
            //update(dt);
        } else{
            System.out.println("Wrong turn-number?!");
        }
    }

    public void setCurrentCaracter(Animal animal){
        this.currentCaracter = animal;
    }
}
