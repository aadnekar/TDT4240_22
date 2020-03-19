package ntnu.gruppe22.game.huds;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Sort;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameManager;
import ntnu.gruppe22.game.helpers.GameMusic;
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

    private GameMusic music;


    public SettingsButtons(AnimalWar game, GameMusic music) {
        this.game = game;
        this.music = music;
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
                new Sprite(new Texture("buttons/volumeOn.png"))
        ));

        musicOffBtn = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/volumeOff.png"))
        ));

        volumeUp = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/volumeUp.png"))
        ));

        volumeDown = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/volumeDown.png"))
        ));

        homeButton = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/homeButton.png"))
        ));

        musicOnBtn.setPosition(GameInfo.WIDTH / 2 - (musicOnBtn.getWidth()/2), GameInfo.HEIGHT / 2 + 70);
        musicOffBtn.setPosition(GameInfo.WIDTH / 2 - (musicOnBtn.getWidth()/2), GameInfo.HEIGHT / 2 - 120);
        volumeUp.setPosition(GameInfo.WIDTH / 2 + 60, GameInfo.HEIGHT / 2 );
        volumeDown.setPosition(GameInfo.WIDTH / 2 - 170, GameInfo.HEIGHT / 2 );
        homeButton.setPosition(50, GameInfo.HEIGHT - 90);

    }

    private void addAllListeners() {


        musicOnBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: show of music is on
                System.out.println("music on");
                music.setMusic(true);

            }
        });

        musicOffBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: show if music is off
                System.out.println("music off");
                music.setMusic(false);
            }
        });

        volumeUp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: volume up button
                System.out.println("volume up");
                music.changeVolume((float)0.1);
            }
        });

        volumeDown.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO volume down button
                System.out.println("volume down");
                music.changeVolume((float)-0.1);
            }
        });

        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO volume down button
                System.out.println("going back to main menu");
                music.setMusic(false);
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
