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
import ntnu.gruppe22.game.scenes.Highscore;
import ntnu.gruppe22.game.scenes.MainMenu;
import ntnu.gruppe22.game.scenes.SelectScreen;

public class GameOverButtons {

    private AnimalWar game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton highscoreList;
    private ImageButton newGame;
    private ImageButton quit;


    private String nick1, nick2;

    public GameOverButtons(AnimalWar game) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport, game.getSb());
        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(highscoreList);
        stage.addActor(newGame);
        stage.addActor(quit);

        checkMusic();
    }

    private void createAndPositionButtons() {
        newGame = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/settings-btn.png"))
        ));
        quit = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/settings-btn.png"))
        ));
        highscoreList = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/play-btn.png"))
        ));

        newGame.setPosition(GameInfo.WIDTH / 2 - highscoreList.getWidth()/2, GameInfo.HEIGHT / 2 + 50);
        quit.setPosition(GameInfo.WIDTH/2 - highscoreList.getWidth()/2, GameInfo.HEIGHT/2-50);
        highscoreList.setPosition(GameInfo.WIDTH / 2 - highscoreList.getWidth()/2, GameInfo.HEIGHT / 2 - 150);
    }


    private void addAllListeners() {
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new SelectScreen(game/*, gameMusic*/));
                        System.out.println("GOING TO THE MAIN MENU!!");
                    }
                });
                SequenceAction sa = new SequenceAction();
                sa.addAction(Actions.fadeOut(1f));
                sa.addAction(run);
                stage.addAction(sa);
            }
        });
        quit.addListener(new ChangeListener() {
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
        highscoreList.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new Highscore(game/*, gameMusic*/));
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

    public Stage getStage() {
        return this.stage;
    }

    public void disposeStage() {
        this.stage.dispose();
    }
}
