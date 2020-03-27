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
 * @author aase and hildegun 17.03.20
 */


public class SettingsButtons {

    private AnimalWar game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton musicOnBtn;
    private ImageButton musicOffBtn;
    private ImageButton volumeUp;
    private ImageButton volumeDown;
    private ImageButton homeButton;


    public SettingsButtons(AnimalWar game) {
        this.game = game;
        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport, game.getSb());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(musicOnBtn);
        stage.addActor(musicOffBtn);
        stage.addActor(volumeUp);
        stage.addActor(volumeDown);
        stage.addActor(homeButton);
    }




    private void createAndPositionButtons() {

        musicOnBtn = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/volume-on.png"))
        ));

        musicOffBtn = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/volume-off.png"))
        ));

        volumeUp = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/volume-up.png"))
        ));

        volumeDown = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/volume-down.png"))
        ));

        homeButton = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/home-button.png"))
        ));

        musicOnBtn.setPosition(GameInfo.WIDTH / 2 - (musicOnBtn.getWidth()/2), GameInfo.HEIGHT / 2 + 70);
        musicOffBtn.setPosition(GameInfo.WIDTH / 2 - (musicOnBtn.getWidth()/2), GameInfo.HEIGHT / 2 - 120);
        volumeUp.setPosition(GameInfo.WIDTH / 2 + 60, GameInfo.HEIGHT / 2 );
        volumeDown.setPosition(GameInfo.WIDTH / 2 - 170, GameInfo.HEIGHT / 2 );
        homeButton.setPosition(50, GameInfo.HEIGHT - 90);

    }

    private void addAllListeners() {

        /**
         * Button to turn on the music.
         */
        musicOnBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 if (GameManager.getInstance().gameData.isMusicOn() == false) {
                    GameManager.getInstance().gameData.setMusicOn(true);
                    GameManager.getInstance().playMusic();
                    GameManager.getInstance().saveData();
                } else {
                     System.out.println("Music is already on.");
                 }
            }
        });

        /**
         * Button to turn off the music.
         */
        musicOffBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (GameManager.getInstance().gameData.isMusicOn()) {
                    GameManager.getInstance().gameData.setMusicOn(false);
                    GameManager.getInstance().stopMusic();
                    GameManager.getInstance().saveData();
                } else {
                    System.out.println("Music is already off");
                }
            }
        });

        /**
         * Button to increase the music volume.
         */
        volumeUp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().incrementMusicVolume();
                GameManager.getInstance().saveData();
            }
        });

        /**
         * Button to decrease the music volume
         */
        volumeDown.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().decrementMusicVolume();
                GameManager.getInstance().saveData();
            }
        });


        /**
         * Changes the screen back to main menu.
         */
        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("going back to main menu");
                game.setScreen(new MainMenu(game));
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
