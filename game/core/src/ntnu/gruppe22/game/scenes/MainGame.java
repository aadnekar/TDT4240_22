package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.maps.Map;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.states.Animal;
import ntnu.gruppe22.game.states.wapons.ListenerClass;
import ntnu.gruppe22.game.states.wapons.Stone;
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

    private AnimalWar game;
    private Map map;
    private World world;

    public boolean DestroyStone = false;
    private Boolean thrown;
    private OrthographicCamera camera;
    private Viewport gameViewport;

    private List<Animal> charactersPlayer1;
    private List<Animal> charactersPlayer2;

    private Animal currentAnimal;
    private int currentTurn;
    private Stone stone;

    BitmapFont font;
    ListenerClass listenerClass;

    public static boolean bufferTime = false;
    private MainGameTimer timer;

    Iterator<Animal> iteratePlayer1;
    Iterator<Animal> iteratePlayer2;

    public MainGame(AnimalWar game, HashMap<Integer, ArrayList<Integer>> roster) {
        this.game = game;
        //create our Box2D world, setting no gravity in X, -10 gravity in Y, and allow bodies to sleep
        this.world = new World(new Vector2(0, -10), true);
        map = new Map(world);



        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH /GameInfo.PPM, GameInfo.HEIGHT /GameInfo.PPM);
        this.camera.update();

        this.camera.position.set(GameInfo.WIDTH / 2f /GameInfo.PPM, GameInfo.HEIGHT / 2f /GameInfo.PPM, 0);
        charactersPlayer1 = new ArrayList<Animal>();
        charactersPlayer2 = new ArrayList<Animal>();

        gameViewport = new FitViewport(GameInfo.WIDTH /GameInfo.PPM, GameInfo.HEIGHT/GameInfo.PPM, camera);

        //legger ved to animals i første omgang. Videre vil vi gi mulighet til fler.
        //posisjon er random, dette må endres etter gitt map
        Animal animal1 = new Animal(this,2);
        Animal animal2 = new Animal(this, 4);
        Animal animal3 = new Animal(this, 1);

        //animal1.setX(GameInfo.WIDTH / 2 - animal1.getWidth()/2);
        //animal2.setX(GameInfo.WIDTH - animal2.getWidth());



        charactersPlayer1.add(animal1);
        charactersPlayer1.add(animal2);
        charactersPlayer2.add(animal3);

        iteratePlayer1 = charactersPlayer1.iterator();
        iteratePlayer2 = charactersPlayer2.iterator();

        setCurrentCharacter(iteratePlayer1.next());

        currentTurn = 0;

        font = new BitmapFont();

        timer = new MainGameTimer(this);
        timer.startNewRoundCountDown();
        listenerClass = new ListenerClass(this, world);
        world.setContactListener(listenerClass);

    }
    public List<Animal> generateAnimals(ArrayList rosterList) {
        List<Animal> animals = new ArrayList<>();
        for (Object i : rosterList ){
            animals.add(new Animal(this, (Integer) i));
        }
        return animals;

    }

    public void handleInput(float dt){
        //throwStone(dt);
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            currentAnimal.b2body.applyLinearImpulse(new Vector2(0, 5f), currentAnimal.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            camera.position.x += 2f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && currentAnimal.b2body.getLinearVelocity().x <= 2)
            currentAnimal.b2body.applyLinearImpulse(new Vector2(0.1f, 0), currentAnimal.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && currentAnimal.b2body.getLinearVelocity().x >= -2)
            currentAnimal.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), currentAnimal.b2body.getWorldCenter(), true);
    }

    public void throwStone(float dt) {
        stone = new Stone(this);
        boolean hit = false;
        stone.b2body.applyLinearImpulse(new Vector2(2f, 2f), stone.b2body.getWorldCenter(), true);
        stone.draw((game.getSb()));
        stone.update(dt);

        //stone.getTexture().dispose();


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
        handleInput(dt);
        //set camera to follow current player
        camera.position.x = currentAnimal.b2body.getPosition().x;
        if (!bufferTime) {
            getCurrentAnimal().move();
        }
        // This if-statement can be placed inside die()-method(?)
        if(charactersPlayer1.size() == 0 || charactersPlayer2.size() == 0){
            gameOver();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        map.update(camera); //Needs to be created before animal texture
        camera.update();
        game.getSb().setProjectionMatrix(camera.combined);
        game.getSb().begin();
        font.draw(game.getSb(), timer.getDisplayString(), 50, 50);
        for(Animal animal : charactersPlayer1){
            animal.draw(game.getSb());
            animal.update(dt);
        }
        for(Animal animal : charactersPlayer2){
            animal.draw(game.getSb());
            animal.update(dt);
            thrown = false;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            throwStone(dt);
            //stone.b2body.destroyFixture(stone.ff);
        }
        if(stone != null) {
            stone.draw(game.getSb());
            stone.setPosition(stone.b2body.getPosition().x - stone.getWidth() / 2, stone.b2body.getPosition().y - stone.getHeight() / 2);
        }


        game.getSb().end();

        if(DestroyStone) {
            listenerClass.getFb().getBody().destroyFixture(listenerClass.getFb());
        }
        DestroyStone = false;

    }

    public World getWorld(){
        return world;
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
