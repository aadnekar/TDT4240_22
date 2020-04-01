package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.Gdx;
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

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameManager;
import ntnu.gruppe22.game.scenes.MainGame;
import ntnu.gruppe22.game.scenes.MainMenu;


public class SelectScreenButtons {
    private AnimalWar game;
    private Stage stage;

    private ImageButton backBtn;
    private ImageButton readyBtn;


    public SelectScreenButtons(AnimalWar game, Stage stage){
        this.game=game;
        this.stage=stage;

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(readyBtn);
        stage.addActor(backBtn);

        checkMusic();
    }

    private void createAndPositionButtons() {

        readyBtn = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/play-btn.png"))
        ));


        backBtn = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/home-button.png"))
        ));

        readyBtn.setPosition(GameInfo.WIDTH / 2 - 95, GameInfo.HEIGHT / 2 + 135);
        backBtn.setPosition(50, GameInfo.HEIGHT - 90);

    }

    private void addAllListeners() {
        readyBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameStartedFromMainMenu = true;

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        //selectScreen.setReady(true);
                        //if(selectScreen.isReady()){
                        game.setScreen(new MainGame(game));
                        System.out.println("You are no ready to play");
                        //}
                    }
                });

                SequenceAction sa = new SequenceAction();
                sa.addAction(Actions.fadeOut(1f));
                sa.addAction(run);

                stage.addAction(sa);
            }
        });

        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameStartedFromMainMenu = true;

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        //selectScreen.setReady(true);
                        //if(selectScreen.isReady()){
                        game.setScreen(new MainMenu(game));
                        System.out.println("You are no ready to play");
                        //}
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
