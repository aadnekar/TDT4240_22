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
import java.util.List;

import ntnu.gruppe22.game.AnimalWar;
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


    public MainGame(AnimalWar game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        this.camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);
        charactersPlayer1 = new ArrayList<>();
        charactersPlayer2 = new ArrayList<>();

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);

        bg = new Texture("backgrounds/menu-bg.png");

        //legger ved to animals i første omgang. Videre vil vi gi mulighet til fler.
        //posisjon er random, dette må endres etter gitt map
        Animal player1 = new Animal(GameInfo.WIDTH/2, GameInfo.HEIGHT/2);
        Animal player2 = new Animal(GameInfo.WIDTH/2-50, GameInfo.HEIGHT/2-50);
        Animal player3 = new Animal(GameInfo.WIDTH/2-50, GameInfo.HEIGHT/2-50);

        charactersPlayer1.add(player1);
        charactersPlayer1.add(player2);
        charactersPlayer2.add(player3);

        //assuming character 1 begins, turn = 0
        setCurrentCharacter(charactersPlayer1.get(0));

        currentTurn = 0;

        font = new BitmapFont();

        //import map in some way?

        timer = new MainGameTimer(this);
        timer.startNewRoundCountDown();
    }

    //forandring fra navn i innlevering
    public Animal getCurrentAnimal(){
        return currentAnimal;
    }

    //vil lagre hver Animal med en index hos hver spiller
    //får neste Animal i rekken
    //antar at vi må sette en ny currencharacter i denne metoden
    public void changeCharacter(){
        if(currentTurn == 0){
            int prev = charactersPlayer2.indexOf(currentAnimal);
            if(prev+1 == charactersPlayer1.size()){
                setCurrentCharacter(charactersPlayer1.get(0));
            } else{
                setCurrentCharacter(charactersPlayer1.get(prev + 1));
            }
        } else {
            int prev = charactersPlayer1.indexOf(currentAnimal);
            if(prev == charactersPlayer2.size()){
                setCurrentCharacter(charactersPlayer1.get(0));
            } else{
                setCurrentCharacter(charactersPlayer2.get(prev));
            }
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


    @Override
    public void render(float dt) {
        if (!bufferTime) {
            getCurrentAnimal().move();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        font.draw(game.getSb(), timer.getDisplayString(), 50, 50);
        for(Animal i : charactersPlayer1){
            game.getSb().draw(i.getAnimalTexture(), i.getX(), i.getY());
            game.getSb().draw(i.getBarTexture(), i.getX()+5, i.getY()+110);
        }
        for(Animal i : charactersPlayer2){
            game.getSb().draw(i.getAnimalTexture(), i.getX(), i.getY());
            game.getSb().draw(i.getBarTexture(), i.getX()+5, i.getY()+110);
        }

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
        font.dispose();
        timer.cancel();
    }
}
