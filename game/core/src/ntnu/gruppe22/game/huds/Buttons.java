package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Observable;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameManager;

/**
 * @author aadne on 15.04.2020 13:21
 */

public abstract class Buttons extends Observable {
    protected AnimalWar game;
    protected Stage stage;
    protected Viewport gameViewport;


    public void initializeButtons(AnimalWar game) {
        this.game = game;
        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport, game.getSb());

        Gdx.input.setInputProcessor(stage);
        createAndPositionButtons();
        addButtonListeners();

        checkMusic();
    }

    protected abstract void createAndPositionButtons();

    protected abstract void addButtonListeners();


    private void checkMusic() {
        if(GameManager.getInstance().gameData.isMusicOn()) {
            GameManager.getInstance().playMusic();
        }
    }

    public Stage getStage() {
        return this.stage;
    }

    public void disposeStage() {
        this.stage.dispose();
    }

}
