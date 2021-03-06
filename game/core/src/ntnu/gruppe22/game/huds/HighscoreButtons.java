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

public class HighscoreButtons extends Buttons {

    private ImageButton quit;

    public HighscoreButtons(AnimalWar game) {
        this.initializeButtons(game);

        stage.addActor(quit);
    }

    protected void createAndPositionButtons() {
        quit = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/back-to-menu.png"))
        ));

        quit.setPosition(GameInfo.WIDTH/2 - quit.getWidth()/2, GameInfo.HEIGHT/2-180);
        }

    protected void addButtonListeners() {
        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new CreateUser(game));
                    }
                });
                SequenceAction sa = new SequenceAction();
                sa.addAction(Actions.fadeOut(1f));
                sa.addAction(run);
                stage.addAction(sa);
            }
        });
    }
}
