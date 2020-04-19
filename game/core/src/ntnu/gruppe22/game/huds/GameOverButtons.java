package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.scenes.CreateUser;
import ntnu.gruppe22.game.scenes.Highscore;
import ntnu.gruppe22.game.scenes.SelectScreen;

public class GameOverButtons extends Buttons {

    public static ImageButton highscoreList;
    private ImageButton newGame;
    private ImageButton quit;

    public static boolean isGameOver;

    public GameOverButtons(AnimalWar game) {
        this.initializeButtons(game);

        stage.addActor(highscoreList);
        stage.addActor(newGame);
        stage.addActor(quit);

        setGameOver(false);
    }

    protected void createAndPositionButtons() {
        newGame = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/new-game.png"))
        ));
        quit = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/back-to-menu.png"))
        ));
        highscoreList = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/highscore.png"))
        ));

        newGame.setPosition(GameInfo.WIDTH / 2 - highscoreList.getWidth()/2, GameInfo.HEIGHT / 2 + 50);
        quit.setPosition(GameInfo.WIDTH/2 - highscoreList.getWidth()/2, GameInfo.HEIGHT/2-50);
        highscoreList.setPosition(GameInfo.WIDTH / 2 - highscoreList.getWidth()/2, GameInfo.HEIGHT / 2 - 150);
    }

    protected void addButtonListeners() {
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new SelectScreen(game));

                        //variable for firebase som sier at runden er over.
                        setGameOver(true);
                        System.out.println("NEW GAME STARTING");
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
                        game.setScreen(new CreateUser(game));
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
                        System.out.println("GOING TO HIGHSCORE");

                    }
                });
                SequenceAction sa = new SequenceAction();
                sa.addAction(Actions.fadeOut(1f));
                sa.addAction(run);
                stage.addAction(sa);
            }
        });
    }

    //Observer-pattern
    public void setGameOver(boolean isGameOver) {
        synchronized (this) {
            this.isGameOver = isGameOver;
        }
        setChanged();
        notifyObservers();
    }

    public synchronized boolean getGameOver() {
        return isGameOver;
    }

}
