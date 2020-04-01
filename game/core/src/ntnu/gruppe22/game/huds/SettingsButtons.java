package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    private Texture musicOff;
    private Texture musicOn;
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

        musicOff = new Texture("Settings/mute.png");
        musicOn = new Texture("Settings/audio_on.png");
        stage.addActor(musicOnBtn);
        stage.addActor(volumeUp);
        stage.addActor(volumeDown);
        stage.addActor(homeButton);
    }




    private void createAndPositionButtons() {

        volumeUp = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Settings/plus.png"))
        ));

        volumeDown = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Settings/minus.png"))
        ));

        homeButton = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Settings/home.png"))
        ));

        if (GameManager.getInstance().gameData.isMusicOn()) {
            musicOnBtn = new ImageButton(new SpriteDrawable(
                    new Sprite(new Texture("Settings/audio_on.png"))
            ));
        }
        else if(!GameManager.getInstance().gameData.isMusicOn()) {
            musicOnBtn = new ImageButton(new SpriteDrawable(
                    new Sprite(new Texture("Settings/mute.png"))
            ));
        }

        musicOnBtn.setPosition(GameInfo.WIDTH / 2 + 90, GameInfo.HEIGHT / 2);
        volumeUp.setPosition(GameInfo.WIDTH / 2 + 140, GameInfo.HEIGHT / 2 -77);
        volumeDown.setPosition(GameInfo.WIDTH / 2 + 50, GameInfo.HEIGHT / 2 -57);
        homeButton.setPosition(5, GameInfo.HEIGHT - 70);
    }

    //changes button Texture when clicking music on/off
    public void SwitchButton() {
        if (GameManager.getInstance().gameData.isMusicOn()) {
            musicOnBtn.getStyle().imageUp= new TextureRegionDrawable(musicOn);
        }
        else if(!GameManager.getInstance().gameData.isMusicOn()) {
            musicOnBtn.getStyle().imageUp= new TextureRegionDrawable(musicOff);
        }
    }

    private void addAllListeners() {

        /**
         * Button to turn on the music.
         */


        musicOnBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 if (!GameManager.getInstance().gameData.isMusicOn()) {
                    GameManager.getInstance().gameData.setMusicOn(true);
                    GameManager.getInstance().playMusic();
                    GameManager.getInstance().saveData();

                } else if (GameManager.getInstance().gameData.isMusicOn()) {
                     GameManager.getInstance().gameData.setMusicOn(false);
                     GameManager.getInstance().stopMusic();
                     GameManager.getInstance().saveData();
                 }
                 SwitchButton();
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
