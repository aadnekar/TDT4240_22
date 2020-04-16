package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameManager;
import ntnu.gruppe22.game.scenes.MainMenu;

/**
 * @author aase and hildegun 17.03.20
 */

public class SettingsButtons extends Buttons {

    private Texture musicOnTexture;
    private Texture musicOffTexture;
    private ImageButton musicOn;
    private ImageButton volumeUp;
    private ImageButton volumeDown;
    private ImageButton homeButton;


    public SettingsButtons(AnimalWar game) {
        this.initializeButtons(game);

        musicOffTexture = new Texture("settings/mute.png");
        musicOnTexture = new Texture("settings/audio-on.png");
        stage.addActor(musicOn);
        stage.addActor(volumeUp);
        stage.addActor(volumeDown);
        stage.addActor(homeButton);
    }


    protected void createAndPositionButtons() {

        volumeUp = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("settings/plus.png"))
        ));

        volumeDown = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("settings/minus.png"))
        ));

        homeButton = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("settings/home.png"))
        ));

        if (GameManager.getInstance().gameData.isMusicOn()) {
            musicOn = new ImageButton(new SpriteDrawable(
                    new Sprite(new Texture("settings/audio-on.png"))
            ));
        }
        else if(!GameManager.getInstance().gameData.isMusicOn()) {
            musicOn = new ImageButton(new SpriteDrawable(
                    new Sprite(new Texture("settings/mute.png"))
            ));
        }

        musicOn.setPosition(GameInfo.WIDTH / 2 + 90, GameInfo.HEIGHT / 2);
        volumeUp.setPosition(GameInfo.WIDTH / 2 + 140, GameInfo.HEIGHT / 2 -77);
        volumeDown.setPosition(GameInfo.WIDTH / 2 + 50, GameInfo.HEIGHT / 2 -57);
        homeButton.setPosition(5, GameInfo.HEIGHT - 70);
    }

    protected void addButtonListeners() {

        musicOn.addListener(new ChangeListener() {
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

        volumeUp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().incrementMusicVolume();
                GameManager.getInstance().saveData();
            }
        });

        volumeDown.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().decrementMusicVolume();
                GameManager.getInstance().saveData();
            }
        });

        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("going back to main menu");
                game.setScreen(new MainMenu(game));
            }
        });
    }

    /**
     * changes button Texture when clicking music on/off
     */
    public void SwitchButton() {
        if (GameManager.getInstance().gameData.isMusicOn()) {
            musicOn.getStyle().imageUp= new TextureRegionDrawable(musicOnTexture);
        }
        else if(!GameManager.getInstance().gameData.isMusicOn()) {
            musicOn.getStyle().imageUp= new TextureRegionDrawable(musicOffTexture);
        }
    }
}
