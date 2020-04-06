package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.maps.Map;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.states.Animal;
import ntnu.gruppe22.game.utils.MainGameTimer;


/**
 * Hva skal skje i MainGame? (antagelser)
 * 1. Du har i utgangspunktet en liste med karakterer som alle har sine våpen, og spillerne har valgt en bakgrunn.
 * alt dette kommer ifra selectScreen.
 *
 * 2. når det er din tur til å spille vil du få en tid på deg til å flytte neste karakter i listen.
 * Du må også skyte innen denne tiden.
 *
 * 3. når tiden har rent ut, eller du har skutt på motstanderen, er det neste spiller sin tur.
 *
 * I denne klassen må vi også håndtere skudd. Hvis en spiller blir truffet mister den en del av live-baren.
 * Vi må håndtere kollisjon mellom våpen og figur i denne klassen.
 *
 * For å håndtere kast som kommer rett før tiden har rent ut vil vi legge inn 5sek delay av en eller annen form
 * slik at deteksjon av våpen mot figur blir gjennomført før det er motstanderen sin tur.
 *
 * Imens motstanderen din spiller, og du venter, må vi ha en form for pause-state.
 *
 */

public class MainGame implements Screen {
    AnimalWar game;
    Map map;

    private OrthographicCamera camera;
    private Viewport gameViewport;

    private Texture bg;

    private List<Animal> charactersPlayer1;
    private List<Animal> charactersPlayer2;

    private Animal currentAnimal;
    private int currentTurn;

    BitmapFont font;

    public static boolean bufferTime = false;
    private MainGameTimer timer;

    Iterator<Animal> iteratePlayer1;
    Iterator<Animal> iteratePlayer2;

    public MainGame(AnimalWar game, HashMap<Integer, ArrayList<Integer>> roster) {
        this.game = game;
        map = new Map();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        this.camera.update();
        this.camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        charactersPlayer1 = generateAnimals(roster.get(0));
        charactersPlayer2 = generateAnimals(roster.get(1));

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);

        bg = new Texture("backgrounds/menu-bg.png");

        iteratePlayer1 = charactersPlayer1.iterator();
        iteratePlayer2 = charactersPlayer2.iterator();

        setCurrentCharacter(iteratePlayer1.next());

        currentTurn = 0;

        font = new BitmapFont();

        timer = new MainGameTimer(this);
        timer.startNewRoundCountDown();
    }
    public List<Animal> generateAnimals(ArrayList rosterList) {
        List<Animal> animals = new ArrayList<>();
        for (Object i : rosterList ){
            animals.add(new Animal((Integer) i));
        }
        return animals;

    }

    //forandring fra navn i innlevering
    public Animal getCurrentAnimal(){
        return currentAnimal;
    }

    /**
     * "Randomly" position the animal objects on the screen.
     */
    public void positionAnimals() {

    }

    //vil lagre hver Animal med en index hos hver spiller
    //får neste Animal i rekken
    //antar at vi må sette en ny currencharacter i denne metoden
    public void changeCharacter(){
        if(currentTurn == 0){
            setCurrentCharacter(nextAnimal(iteratePlayer1, charactersPlayer1));
        } else {
            setCurrentCharacter(nextAnimal(iteratePlayer2, charactersPlayer2));
        }
    }


    // TODO: Handle when players.size() = 0 - we have a winner!
    public Animal nextAnimal(Iterator<Animal> iter, List<Animal> players) {

        if (iter.hasNext()) {
            return iter.next();
        } else {
            iter = players.iterator();
            if(currentTurn == 0){
                iteratePlayer1 = iter;
            } else{
                iteratePlayer2 = iter;
            }
            return nextAnimal(iter, players);
        }
    }

    //skal vi ha runder med i spillet i det hele tatt?
    //dt?
    public void setCurrentTurn(){
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

    public void setCurrentCharacter(Animal animal){
        currentAnimal = animal;
    }

    public void timesUp(){
        setCurrentTurn();
        changeCharacter();
    }

    public void gameOver(){
        this.dispose();
        game.setScreen(new MainMenu(game));
    }


    @Override
    public void render(float dt) {
        if (!bufferTime) {
            getCurrentAnimal().move();
        }
        // This if-statement can be placed inside die()-method(?)
        if(charactersPlayer1.size() == 0 || charactersPlayer2.size() == 0){
            gameOver();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        font.draw(game.getSb(), timer.getDisplayString(), 50, 50);
        for(Animal animal : charactersPlayer1){
            animal.draw(game.getSb());
        }
        for(Animal animal : charactersPlayer2){
            animal.draw(game.getSb());
        }

        game.getSb().end();

//        map.update(camera);
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
        font.dispose();
        timer.cancel();
    }
}
