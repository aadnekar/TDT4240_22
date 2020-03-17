package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameManager;
import ntnu.gruppe22.game.scenes.MainMenu;

/**
 * @author aadne on 13.03.2020 11:41
 */

public class SettingsButtons {

    private AnimalWar game;

    private Stage stage;
    private Viewport viewport;

    private ImageButton mainMenuBtn;
    private ImageButton musicBtn;

    private ImageButton.ImageButtonStyle buttonStyle;

    public SettingsButtons(AnimalWar game) {
        this.game = game;

        this.viewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());

        this.stage = new Stage(viewport, game.getSb());

        Gdx.input.setInputProcessor(stage);

        buttonStyle = new ImageButton.ImageButtonStyle();


        createAndPositionButtons();
        addAllListeners();

        stage.addActor(mainMenuBtn);
        stage.addActor(musicBtn);
    }

    private void createAndPositionButtons() {

        mainMenuBtn = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/play-btn.png"))
        ));

        musicBtn = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/play-btn.png"))
        ));

        mainMenuBtn.setPosition(GameInfo.WIDTH / 2 - 95, GameInfo.HEIGHT / 2 + 45);
        musicBtn.setPosition(GameInfo.WIDTH / 2 - 95, GameInfo.HEIGHT / 2 - 45);
    }

    private void addAllListeners() {

        mainMenuBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(event.equals(InputEvent.Type.touchDown)) {
                    mainMenuBtn.setBackground(new SpriteDrawable(
                            new Sprite(new Texture("buttons/play-btn-clicked"))
                    ));
                }
                game.setScreen(new MainMenu(game));
            }
        });

        musicBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(GameManager.getInstance().gameData.isMusicOn()) {
                    GameManager.getInstance().gameData.setMusicOn(false);
                    GameManager.getInstance().stopMusic();
                } else {
                    GameManager.getInstance().gameData.setMusicOn(true);
                    GameManager.getInstance().playMusic();
                }
                GameManager.getInstance().saveData();
            }
        });

    }

    public Stage getStage() {
        return this.stage;
    }

    public void disposeStage() {
        this.stage.dispose();
    }
}
