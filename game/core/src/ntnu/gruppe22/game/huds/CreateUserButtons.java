package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.Gdx;
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
import ntnu.gruppe22.game.scenes.MainMenu;


public class CreateUserButtons extends Buttons {
    private ImageButton continueToGame;
    private ImageButton addNickname1;
    private ImageButton addNickname2;

    private static CreateUserInput listener;
    private static CreateUserInput listener2;

    private String nick1, nick2;

    public CreateUserButtons(AnimalWar game) {
        this.initializeButtons(game);

        listener = new CreateUserInput();
        listener2 = new CreateUserInput();

        stage.addActor(continueToGame);
        stage.addActor(addNickname1);
        stage.addActor(addNickname2);
    }

    protected void createAndPositionButtons() {
        addNickname1 = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/player-1.png"))
        ));
        addNickname2 = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/player-2.png"))
        ));
        continueToGame = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/play-btn.png"))
        ));

        addNickname1.setPosition(GameInfo.WIDTH / 2 - continueToGame.getWidth()/2, GameInfo.HEIGHT / 2 + 50);
        addNickname2.setPosition(GameInfo.WIDTH/2 - continueToGame.getWidth()/2, GameInfo.HEIGHT/2-50);
        continueToGame.setPosition(GameInfo.WIDTH / 2 - continueToGame.getWidth()/2, GameInfo.HEIGHT / 2 - 150);
    }


    protected void addButtonListeners() {
        addNickname1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.getTextInput(listener, "Enter information", "", "");
                nick1 = listener.text;

            }
        });
        addNickname2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.getTextInput(listener2, "Enter information", "", "");
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
                        game.setScreen(new MainMenu(game));
                    }
                });
                SequenceAction sa = new SequenceAction();
                sa.addAction(Actions.fadeOut(1f));
                sa.addAction(run);
                stage.addAction(sa);
            }
        });
    }

    public static String getNick1() {
        return listener.text;
    }

    public static String getNick2() {
        return listener2.text;
    }
}

