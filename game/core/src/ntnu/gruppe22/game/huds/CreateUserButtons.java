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


public class CreateUserButtons {
    private AnimalWar game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton continueToGame;
    private ImageButton addNickname1;
    private ImageButton addNickname2;

    private CreateUserInput listener;
    private CreateUserInput listener2;

    private String nick1, nick2;

    public CreateUserButtons(AnimalWar game) {
        this.game = game;

        listener = new CreateUserInput();
        listener2 = new CreateUserInput();

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport, game.getSb());
        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(continueToGame);
        stage.addActor(addNickname1);
        stage.addActor(addNickname2);

        checkMusic();
    }

    private void createAndPositionButtons() {
        addNickname1 = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/settings-btn.png"))
        ));
        addNickname2 = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/settings-btn.png"))
        ));
        continueToGame = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/play-btn.png"))
        ));

        addNickname1.setPosition(GameInfo.WIDTH / 2 - continueToGame.getWidth()/2, GameInfo.HEIGHT / 2 + 50);
        addNickname2.setPosition(GameInfo.WIDTH/2 - continueToGame.getWidth()/2, GameInfo.HEIGHT/2-50);
        continueToGame.setPosition(GameInfo.WIDTH / 2 - continueToGame.getWidth()/2, GameInfo.HEIGHT / 2 - 150);
    }


    private void addAllListeners() {
        addNickname1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.getTextInput(listener, "Enter information", "Player1:", "");
                nick1 = listener.text;

            }
        });
        addNickname2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.getTextInput(listener2, "Enter information", "Player2:", "");
                nick2 = listener2.text;
            }
        });
        continueToGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new MainMenu(game/*, gameMusic*/));
                        System.out.println("GOING TO THE MAIN MENU!!");
                    }
                });
                SequenceAction sa = new SequenceAction();
                sa.addAction(Actions.fadeOut(1f));
                sa.addAction(run);
                stage.addAction(sa);
            }
        });
    }

    private void checkMusic() {
        if(GameManager.getInstance().gameData.isMusicOn()) {
            GameManager.getInstance().playMusic();
        }
    }

    public String getNick1() {
        return listener.text;
    }

    public String getNick2() {
        return listener2.text;
    }

    public Stage getStage() {
        return this.stage;
    }

    public void disposeStage() {
        this.stage.dispose();
    }
}
