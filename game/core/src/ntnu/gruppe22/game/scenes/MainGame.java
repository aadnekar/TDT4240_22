package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.MainGameButtons;
import ntnu.gruppe22.game.maps.Map;
import ntnu.gruppe22.game.sprites.animals.Animal;
import ntnu.gruppe22.game.sprites.animals.Chicken;
import ntnu.gruppe22.game.sprites.animals.Monkey;
import ntnu.gruppe22.game.sprites.animals.Moose;
import ntnu.gruppe22.game.sprites.animals.Rabbit;
import ntnu.gruppe22.game.sprites.animals.Walrus;
import ntnu.gruppe22.game.states.weapons.ListenerClass;
import ntnu.gruppe22.game.states.weapons.Stone;
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

    public boolean DestroyWeapon = false;
    private OrthographicCamera camera;
    private Viewport gameViewport;
    private MainGameButtons btns;

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

        listenerClass = new ListenerClass(this, world);
        world.setContactListener(listenerClass);
        btns = new MainGameButtons(game);


        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        this.camera.update();

        //this.camera.position.set(GameInfo.WIDTH / 2f /GameInfo.PPM, GameInfo.HEIGHT / 2f /GameInfo.PPM, 0);
        charactersPlayer1 = new ArrayList<>();
        charactersPlayer2 = new ArrayList<>();

        //gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);

        charactersPlayer1 = generateAnimals(roster.get(0));
        charactersPlayer2 = generateAnimals(roster.get(1));

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


    //TODO: make animal.setDeadAnimal() på hvert dyr som blir fjernet, ikke bare det siste som dør
    public void removeAnimal(Animal animal) {
        if (charactersPlayer1.contains(animal)) {
            charactersPlayer1.remove(animal);
            if (charactersPlayer1.size() == 0) {
                currentAnimal.setDeadAnimal();
                GameOver.setWinner("Player 2");
                gameOver();
                GameOver.setWinnerScore(getPlayer2Health());
            }
        } else {
            charactersPlayer2.remove(animal);
            if (charactersPlayer2.size() == 0) {
                GameOver.setWinner("Player 1");
                GameOver.setWinnerScore(getPlayer1Health());
                gameOver();
                currentAnimal.setDeadAnimal();
            }
        }
    }

    private void checkSoonDeadAnimal(){
        for(Animal i : charactersPlayer1){
            if(i.getHealth() < 10){
                i.setDeadAnimal();
            }
        }
        for(Animal i : charactersPlayer2){
            if(i.getHealth() < 10){
                i.setDeadAnimal();
            }
        }
    }

    public int getPlayer1Health() {
        int totalHealth = 0;
        for (Animal animal: charactersPlayer1) {
            int remainingHealth = animal.getHealth();
            totalHealth += remainingHealth;
        }
        return totalHealth;
    }

    public int getPlayer2Health() {
        int totalHealth = 0;
        for (Animal animal: charactersPlayer2) {
            int remainingHealth = animal.getHealth();
            totalHealth += remainingHealth;
        }
        return totalHealth;
    }


    public List<Animal> generateAnimals(ArrayList rosterList) {
        List<Animal> animals = new ArrayList<>();
        for (Object i : rosterList) {
            if((Integer) i == 1){
                animals.add(new Chicken(this, (Integer) i));
            }
            if((Integer) i == 2){
                animals.add(new Monkey(this, (Integer) i));
            }
            if((Integer) i == 3){
                animals.add(new Walrus(this, (Integer) i));
            }
            if((Integer) i == 4){
                animals.add(new Moose(this, (Integer) i));
            }
            if((Integer) i == 5){
                animals.add(new Rabbit(this, (Integer) i));
            }

        }
        return animals;

    }

    public void handleInput(float dt) {
        if (!bufferTime) {
            getCurrentAnimal().move(this.camera);
        }
    }

    //forandring fra navn i innlevering
    public Animal getCurrentAnimal() {
        return currentAnimal;
    }

    public Stone getStone() {
        return this.stone;
    }

    public void setStone(int pos) {
        this.stone = new Stone(this, pos);
    }

    /**
     * "Randomly" position the animal objects on the screen.
     */
    public void positionAnimals() {

    }

    //vil lagre hver Animal med en index hos hver spiller
    //får neste Animal i rekken
    //antar at vi må sette en ny currencharacter i denne metoden
    public void changeCharacter() {
        if (currentTurn == 0) {
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
            if (currentTurn == 0) {
                iteratePlayer1 = iter;
            } else {
                iteratePlayer2 = iter;
            }
            return nextAnimal(iter, players);
        }
    }


    public void setCurrentTurn() {
        if (currentTurn == 1) {
            this.currentTurn = 0;
        } else if (currentTurn == 0) {
            this.currentTurn = 1;
        } else {
            System.out.println("Wrong turn-number?!");
        }
    }

    public void setCurrentCharacter(Animal animal) {
        currentAnimal = animal;
    }

    public void timesUp() {
        setCurrentTurn();
        changeCharacter();
    }

    public void gameOver() {
        this.dispose();
        game.setScreen(new GameOver(game));
    }


    public float cameraBounds(float animalPosition, float mapEnd, float mapStart) {
        if (animalPosition > mapStart) {
            if (animalPosition < mapEnd) {
                return animalPosition;
            } else return mapEnd;
        } else return mapStart;
    }

    //programmet stopper i ca 1 sek før steinen forsvinner
    private void destroyWeapon() {
        /*
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        world.destroyBody(stone.b2body);
        stone = null;
        DestroyWeapon = false;
    }

    //Cancles current timer and starts new
    private void startNewTimer(){
        timer.cancel();
        timer = null;
        timesUp();
        timer = new MainGameTimer(this);
        timer.startNewRoundCountDown();
    }


    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput(dt);

        checkSoonDeadAnimal();

        //set camera to follow current player within bounds
        //mapEnd: 1920 is the total length of the map, 640 is the total height.
        camera.position.x = cameraBounds(currentAnimal.getX(), (1920 - (GameInfo.WIDTH / 2)) / 100, camera.viewportWidth / 2);
        camera.position.y = cameraBounds(currentAnimal.getY(), (640 - (GameInfo.HEIGHT / 2)) / 100, camera.viewportHeight / 2);

        map.update(camera); //Needs to be created before animal texture

        camera.update();

        game.getSb().setProjectionMatrix(camera.combined);


        game.getSb().begin();
        font.draw(game.getSb(), timer.getDisplayString(), 50, 50);
        btns.replaceEventListener(currentAnimal, dt);

        for (Animal animal : charactersPlayer1) {
            animal.draw(game.getSb());
            animal.healthbar.draw(game.getSb());
            animal.update(dt);

        }
        for (Animal animal : charactersPlayer2) {
            animal.draw(game.getSb());
            animal.healthbar.draw(game.getSb());
            animal.update(dt);
        }


        if (DestroyWeapon) {
            destroyWeapon();
            startNewTimer();
        }

        if (stone != null && (stone.b2body.getPosition().x <= 0.2 || stone.b2body.getPosition().x > (1920 / GameInfo.PPM))) {
            destroyWeapon();
            startNewTimer();
        }


        if (stone != null) {
            stone.draw(game.getSb());
            stone.setPosition(stone.b2body.getPosition().x - stone.getWidth() / 2, stone.b2body.getPosition().y - stone.getHeight() / 2);
        }

        game.getSb().end();

        btns.getStage().draw();
        btns.getStage().act();

        world.step(dt, 6, 2);

    }

        public World getWorld () {
            return world;
        }
        @Override
        public void show () {

        }

        @Override
        public void resize ( int width, int height){

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
            font.dispose();
            timer.cancel();
            btns.disposeStage();
        }



}