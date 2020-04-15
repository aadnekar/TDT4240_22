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

import java.util.ArrayList;
import java.util.HashMap;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameManager;
import ntnu.gruppe22.game.scenes.MainGame;
import ntnu.gruppe22.game.scenes.MainMenu;
import ntnu.gruppe22.game.helpers.GameRules;


public class SelectScreenButtons {
    private AnimalWar game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton back;
    private ImageButton ready;
    private ImageButton chicken;
    private ImageButton monkey;
    private ImageButton walrus;
    private ImageButton moose;
    private ImageButton rabbit;
    private int playerNumber;

    private static final float CHICKEN_POSITION_X = GameInfo.WIDTH/5-92;
    private static final float MONKEY_POSITION_X = 2*GameInfo.WIDTH/5-(195/2);
    private static final float WALRUS_POSITION_X = 3*GameInfo.WIDTH/5-(183/2);
    private static final float MOOSE_POSITION_X = 4*GameInfo.WIDTH/5-(227/2);
    private static final float RABBIT_POSITION_X = 5*GameInfo.WIDTH/5-(183/2);
    private static final float Y_POSITION_DEFAULT = GameInfo.HEIGHT/3-52;
    private static final float Y_POSITION_CHOSEN = GameInfo.HEIGHT/3;



    private HashMap<Integer, ArrayList<Integer>> animalChoices;



    public SelectScreenButtons(AnimalWar game){
        this.game=game;
        this.playerNumber=0;
        this.animalChoices = new HashMap<>();
        this.animalChoices.put(playerNumber,new ArrayList<Integer>());



        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport, game.getSb());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(ready);
        stage.addActor(back);
        stage.addActor(chicken);
        stage.addActor(monkey);
        stage.addActor(walrus);
        stage.addActor(moose);
        stage.addActor(rabbit);

        checkMusic();
    }


    private void createAndPositionButtons() {

        ready = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/play-btn.png"))
        ));
        back = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/home-button.png"))
        ));
        chicken = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("animals/chicken.png"))
        ));
        monkey = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("animals/monkey.png"))
        ));
        walrus = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("animals/walrus.png"))
        ));
        moose = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("animals/moose.png"))
        ));
        rabbit = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("animals/rabbit.png"))
        ));

        ready.setPosition(GameInfo.WIDTH / 2 - 95, GameInfo.HEIGHT / 2 + 135);
        back.setPosition(50, GameInfo.HEIGHT - 90);

        //the width in position is width of 1/5- 65 + (half the image)
        chicken.setPosition(CHICKEN_POSITION_X,Y_POSITION_DEFAULT);
        monkey.setPosition(MONKEY_POSITION_X,Y_POSITION_DEFAULT);
        walrus.setPosition(WALRUS_POSITION_X,Y_POSITION_DEFAULT);
        moose.setPosition(MOOSE_POSITION_X,Y_POSITION_DEFAULT);
        rabbit.setPosition(RABBIT_POSITION_X,Y_POSITION_DEFAULT);

    }

    private void addAllListeners() {
        ready.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameStartedFromMainMenu = true;

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        if (hasFullRoster()){
                            if (playerNumber == 0){
                                nextPlayer();
                            }
                            else {
                                game.setScreen(new MainGame(game, animalChoices));
                            }
                        }

                    }
                });

                SequenceAction sa = new SequenceAction();
//                sa.addAction(Actions.fadeOut(1f));
                sa.addAction(run);

                stage.addAction(sa);
            }
        });

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameStartedFromMainMenu = true;

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

        chicken.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                    setPosition(chicken);
                    setCharacter(1);
                }
            });



        monkey.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setPosition(monkey);
                setCharacter(2);
            }
        });


        walrus.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setPosition(walrus);
                setCharacter(3);
            }
        });


        moose.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setPosition(moose);
                setCharacter(4);
            }
        });
        rabbit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setPosition(rabbit);
                setCharacter(5);
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

    public void setCharacter(int id) {
        if (animalChoices.get(playerNumber).contains(id)) {
            animalChoices.get(playerNumber).remove(
                    animalChoices.get(playerNumber).indexOf(id)
            );
        }
        else {
            checkLengthAndRemoveAnimal();
            animalChoices.get(playerNumber).add(id);
        }
    }

    public void setPosition(ImageButton animal) {
        if (animal.getY() != Y_POSITION_CHOSEN){
            animal.setY( Y_POSITION_CHOSEN);
        }
        else {
            animal.setY(Y_POSITION_DEFAULT);
        }
    }

    private ImageButton getImagebuttonFromID(int id) {
        switch (id){
            case 1:return chicken;
            case 2:return monkey;
            case 3:return walrus;
            case 4:return moose;
            default:return rabbit;
        }
    }

    private void checkLengthAndRemoveAnimal() {
        if (animalChoices.get(playerNumber).size() == GameRules.NUMBER_OF_CHARS){
            setPosition(getImagebuttonFromID(animalChoices.get(playerNumber).get(0)));
            animalChoices.get(playerNumber).remove(0);
        }
    }

    private void nextPlayer() {
        for (int animal=1 ; animal<6; animal++){
            resetToDefaultPosition(getImagebuttonFromID(animal));
        }
        playerNumber++;
        animalChoices.put(playerNumber, new ArrayList<Integer>());
    }

    private void resetToDefaultPosition(ImageButton animal) {
        animal.setY(Y_POSITION_DEFAULT);
    }

    private boolean hasFullRoster(){
        return animalChoices.get(playerNumber).size() == GameRules.NUMBER_OF_CHARS;
    }

}
