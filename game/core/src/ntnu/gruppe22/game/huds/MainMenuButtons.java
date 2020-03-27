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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameManager;
import ntnu.gruppe22.game.scenes.MainMenu;
import ntnu.gruppe22.game.scenes.Settings;

/**
 * @author aadne on 12.03.2020 12:15
 */

public class MainMenuButtons {
    private AnimalWar game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton playBtn;
    private ImageButton controlsBtn;
    private ImageButton settingsBtn;
    private ImageButton mapsBtn;


    public MainMenuButtons(AnimalWar game) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());

        stage = new Stage(gameViewport, game.getSb());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(playBtn);
        stage.addActor(controlsBtn);
        stage.addActor(settingsBtn);
        stage.addActor(mapsBtn);

        checkMusic();
    }

    private void createAndPositionButtons() {

        playBtn = new ImageButton(new SpriteDrawable(
                        new Sprite(new Texture("buttons/play_btn.png"))
                ));

        controlsBtn = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/controls_btn.png"))
        ));

        settingsBtn = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/settings_btn.png"))
        ));

        mapsBtn = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/maps_btn.png"))
        ));


        playBtn.setPosition(GameInfo.WIDTH / 2 - playBtn.getWidth()/2, GameInfo.HEIGHT / 2 + 40);
        controlsBtn.setPosition(GameInfo.WIDTH / 2 - playBtn.getWidth()/2, GameInfo.HEIGHT / 2 - 30);
        settingsBtn.setPosition(GameInfo.WIDTH / 2 - playBtn.getWidth()/2, GameInfo.HEIGHT / 2 - 100);
        mapsBtn.setPosition(GameInfo.WIDTH / 2 -playBtn.getWidth()/2, GameInfo.HEIGHT / 2 - 170);

    }

    private void addAllListeners() {
        playBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameStartedFromMainMenu = true;

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        // TODO: Uncomment when MainGame is implemented
                        game.setScreen(new MainMenu(game));
                        System.out.println("NEW GAME STARTED!!");
                    }
                });

                SequenceAction sa = new SequenceAction();
                sa.addAction(Actions.fadeOut(1f));
                sa.addAction(run);

                stage.addAction(sa);
            }
        });

        controlsBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: Uncomment this when Controls screen is implemented.
                /*game.setScreen(new Controls(game));*/
            }
        });

        settingsBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new Settings(game/*, gameMusic*/));
                        System.out.println("GOING TO THE SETTINGS SCREEN!!");
                    }
                });

                SequenceAction sa = new SequenceAction();
                sa.addAction(Actions.fadeOut(1f));
                sa.addAction(run);
                stage.addAction(sa);
            }
        });

        mapsBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO uncomment this when MapScreen screen is implemented
                /*game.setScreen(new MapScreen(game));*/
            }
        });

    }

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
