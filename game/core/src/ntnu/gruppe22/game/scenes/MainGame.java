package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.CreateUserButtons;
import ntnu.gruppe22.game.huds.MainGameButtons;
import ntnu.gruppe22.game.maps.Map;
import ntnu.gruppe22.game.sprites.animals.Animal;
import ntnu.gruppe22.game.sprites.animals.Chicken;
import ntnu.gruppe22.game.sprites.animals.Monkey;
import ntnu.gruppe22.game.sprites.animals.Moose;
import ntnu.gruppe22.game.sprites.animals.Rabbit;
import ntnu.gruppe22.game.sprites.animals.Walrus;
import ntnu.gruppe22.game.sprites.weapons.ListenerClass;
import ntnu.gruppe22.game.sprites.weapons.Stone;
import ntnu.gruppe22.game.utils.MainGameTimer;

public class MainGame implements Screen {

    private AnimalWar game;
    private Map map;
    private World world;

    public boolean DestroyWeapon = false;
    private OrthographicCamera camera;
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
        this.world = new World(new Vector2(0, -10), true);
        map = new Map(world);

        listenerClass = new ListenerClass(this, world);
        world.setContactListener(listenerClass);
        btns = new MainGameButtons(game);

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        this.camera.update();

        charactersPlayer1 = new ArrayList<>();
        charactersPlayer2 = new ArrayList<>();

        charactersPlayer1 = generateAnimals(roster.get(0));
        charactersPlayer2 = generateAnimals(roster.get(1));

        iteratePlayer1 = charactersPlayer1.iterator();
        iteratePlayer2 = charactersPlayer2.iterator();

        setCurrentCharacter(iteratePlayer1.next());

        currentTurn = 0;

        font = new BitmapFont();
        font.getData().setScale(1.5f, 1.5f);
        timer = new MainGameTimer(this);
        timer.startNewRoundCountDown();
        listenerClass = new ListenerClass(this, world);
        world.setContactListener(listenerClass);
    }


    public void removeAnimal(Animal animal) {
        if (charactersPlayer1.contains(animal)) {
            charactersPlayer1.remove(animal);
            currentAnimal.setDeadAnimal();
            if (charactersPlayer1.size() == 0) {
                gameOver(CreateUserButtons.getNick2(), getPlayer2Health());
            }
        } else {
            charactersPlayer2.remove(animal);
            currentAnimal.setDeadAnimal();
            if (charactersPlayer2.size() == 0) {
                gameOver(CreateUserButtons.getNick1(), getPlayer1Health());
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


    public void changeCharacter() {
        if (currentTurn == 0) {
            setCurrentCharacter(nextAnimal(iteratePlayer1, charactersPlayer1));
        } else {
            setCurrentCharacter(nextAnimal(iteratePlayer2, charactersPlayer2));
        }
    }

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

    public void gameOver(String winner, int score) {
        this.dispose();
        game.setScreen(new GameOver(game, winner, score));
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
        camera.position.x = cameraBounds(currentAnimal.getX()+(currentAnimal.getWidth()/2), (1920 - (GameInfo.WIDTH / 2)) / 100, camera.viewportWidth / 2);
        camera.position.y = cameraBounds(currentAnimal.getY(), (640 - (GameInfo.HEIGHT / 2)) / 100, camera.viewportHeight / 2);

        map.update(camera); //Needs to be created before animal texture

        camera.update();

        game.getSb().setProjectionMatrix(camera.combined);


        game.getSb().begin();



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



        game.getSb().setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        font.setColor(0f, 0f, 0f, 1f);
        font.draw(game.getSb(), timer.getDisplayString(), 20, Gdx.graphics.getHeight()-20);
        if (currentTurn == 0) {
            font.draw(game.getSb(),"Player 1's turn (" + CreateUserButtons.getNick1() + ")", Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight()-20);
        }
        else {
            font.draw(game.getSb(),"Player 2's turn (" + CreateUserButtons.getNick2() + ")", Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight()-20);
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
        public void resize (int width, int height){
            //gameViewport.update(width, height);
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