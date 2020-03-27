package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameManager;

public class SelectAnimalButtons {


    private AnimalWar game;
    private Stage stage;
    private Viewport gameViewport;
    private ImageButton chicken;
    private ImageButton monkey;
    private ImageButton walrus;
    private ImageButton moose;
    private ImageButton rabbit;

    public SelectAnimalButtons(AnimalWar game){
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH,GameInfo.HEIGHT,new OrthographicCamera());
        stage = new Stage(gameViewport,game.getSb());
        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(chicken);
        stage.addActor(monkey);
        stage.addActor(walrus);
        stage.addActor(moose);
        stage.addActor(rabbit);

        checkMusic();

    }

    private void createAndPositionButtons() {
        chicken = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Animals/chicken.png"))
        ));
        monkey = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Animals/mokey.png"))
        ));
        walrus = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Animals/walrus.png"))
        ));
        moose = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Animals/moose.png"))
        ));
        rabbit = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Animals/rabbit.png"))
        ));
        chicken.setPosition(GameInfo.WIDTH/5-70,GameInfo.HEIGHT/3-52);
        monkey.setPosition(2*GameInfo.WIDTH/5-70,GameInfo.HEIGHT/3-52);
        walrus.setPosition(3*GameInfo.WIDTH/5-70,GameInfo.HEIGHT/3-52);
        moose.setPosition(4*GameInfo.WIDTH/5-70,GameInfo.HEIGHT/3-52);
        rabbit.setPosition(5*GameInfo.WIDTH/5-70,GameInfo.HEIGHT/3-52);

    }

    private void addAllListeners() {

    }


    private void checkMusic() {
        if(GameManager.getInstance().gameData.isMusicOn()) {
            GameManager.getInstance().playMusic();
        }
    }
    public Stage getStage(){
        return this.stage;
    }

    public void disposeStage(){
        this.stage.dispose();
    }
}
