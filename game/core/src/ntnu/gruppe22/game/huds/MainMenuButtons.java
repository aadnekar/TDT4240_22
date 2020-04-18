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
import ntnu.gruppe22.game.helpers.GameManager;
import ntnu.gruppe22.game.scenes.ControlsScreen;
import ntnu.gruppe22.game.scenes.Maps;
import ntnu.gruppe22.game.scenes.SelectScreen;
import ntnu.gruppe22.game.scenes.Settings;

/**
 * @author aadne on 12.03.2020 12:15
 */

public class MainMenuButtons extends Buttons {
    private ImageButton play;
    private ImageButton controls;
    private ImageButton settings;
    private ImageButton maps;


    public MainMenuButtons(AnimalWar game) {
        this.initializeButtons(game);

        this.stage.addActor(play);
        this.stage.addActor(controls);
        this.stage.addActor(settings);
        this.stage.addActor(maps);
    }

    public void createAndPositionButtons() {

        play = new ImageButton(new SpriteDrawable(
                        new Sprite(new Texture("buttons/play-btn.png"))
                ));

        controls = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/controls-btn.png"))
        ));

        settings = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/settings-btn.png"))
        ));

        maps = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/maps-btn.png"))
        ));

        play.setPosition(GameInfo.WIDTH / 2 - play.getWidth()/2, GameInfo.HEIGHT / 2 + 40);
        controls.setPosition(GameInfo.WIDTH / 2 - play.getWidth()/2, GameInfo.HEIGHT / 2 - 30);
        settings.setPosition(GameInfo.WIDTH / 2 - play.getWidth()/2, GameInfo.HEIGHT / 2 - 100);
        maps.setPosition(GameInfo.WIDTH / 2 -play.getWidth()/2, GameInfo.HEIGHT / 2 - 170);

    }


    public void addButtonListeners() {
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameStartedFromMainMenu = true;

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        // TODO: Uncomment when MainGame is implemented
                        game.setScreen(new SelectScreen(game));
                        System.out.println("welcome to Selectscreen");
                    }
                });

                SequenceAction sa = new SequenceAction();
                sa.addAction(Actions.fadeOut(1f));
                sa.addAction(run);

                stage.addAction(sa);
            }
        });

        controls.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new ControlsScreen(game));
                    }
                });

                SequenceAction sa = new SequenceAction();
                sa.addAction(Actions.fadeOut(1f));
                sa.addAction(run);
                stage.addAction(sa);
            }
        });

        settings.addListener(new ChangeListener() {
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

        maps.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new Maps(game));
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
