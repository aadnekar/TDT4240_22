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
import ntnu.gruppe22.game.scenes.SelectScreen;


public class SelectScreenButtons {
    private AnimalWar game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton backBtn;
    private ImageButton readyBtn;
    private ImageButton chicken;
    private ImageButton monkey;
    private ImageButton walrus;
    private ImageButton moose;
    private ImageButton rabbit;
    private int playerNumber;


    private HashMap<Integer, ArrayList> animalChoices;
    private ArrayList<Integer> addedAnimal;
    private boolean ready;
    //private Map<Player.username, Boolean> ready;


    public SelectScreenButtons(AnimalWar game ,int playerNumber){
        this.game=game;
        this.playerNumber=playerNumber;


        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport, game.getSb());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(readyBtn);
        stage.addActor(backBtn);
        stage.addActor(chicken);
        stage.addActor(monkey);
        stage.addActor(walrus);
        stage.addActor(moose);
        stage.addActor(rabbit);

        checkMusic();
    }

    private void createAndPositionButtons() {

        readyBtn = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/play-btn.png"))
        ));
        backBtn = new ImageButton(new SpriteDrawable(
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

        readyBtn.setPosition(GameInfo.WIDTH / 2 - 95, GameInfo.HEIGHT / 2 + 135);
        backBtn.setPosition(50, GameInfo.HEIGHT - 90);

        //the width in position is width of 1/5- 65 + (half the image)
        chicken.setPosition(GameInfo.WIDTH/5-92,GameInfo.HEIGHT/3-52);
        monkey.setPosition(2*GameInfo.WIDTH/5-(195/2),GameInfo.HEIGHT/3-52);
        walrus.setPosition(3*GameInfo.WIDTH/5-(183/2),GameInfo.HEIGHT/3-52);
        moose.setPosition(4*GameInfo.WIDTH/5-(227/2),GameInfo.HEIGHT/3-52);
        rabbit.setPosition(5*GameInfo.WIDTH/5-(183/2),GameInfo.HEIGHT/3-52);

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
                        if (playerNumber ==0){
                            playerNumber =1;
                            game.setScreen(new SelectScreen(game,playerNumber));
                            System.out.println("Player2 can now chose characters");
                        }
                        else {
                            game.setScreen(new MainGame(game));
                            System.out.println("You are no ready to play");
                        }

                    }
                });

                SequenceAction sa = new SequenceAction();
//                sa.addAction(Actions.fadeOut(1f));
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
                        game.setScreen(new MainMenu(game));
                        System.out.println("You are no going to mainmenu");
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
                GameManager.getInstance().gameStartedFromMainMenu = true;

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        if (chicken.getY()!=GameInfo.HEIGHT/3) {
                            chicken.setPosition(GameInfo.WIDTH / 5 - 92, GameInfo.HEIGHT / 3);
                            //setCharacter(1);
                            System.out.println("You have chosen chicken to your army");
                        }

                        else {
                            chicken.setPosition(GameInfo.WIDTH / 5 - 92, GameInfo.HEIGHT / 3 - 52);
                            System.out.println("you have removed chicken from your army");
                        }
                    }
                });

                SequenceAction sa = new SequenceAction();
                sa.addAction(run);

                stage.addAction(sa);
            }
        });


        monkey.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameStartedFromMainMenu = true;

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        if (monkey.getY()!=GameInfo.HEIGHT/3) {
                            monkey.setPosition(2*GameInfo.WIDTH/5-(195/2), GameInfo.HEIGHT / 3);
                            //setCharacter(2);
                            System.out.println("You have chosen monkey to your army");
                        }

                        else {
                            monkey.setPosition(2*GameInfo.WIDTH/5-(195/2), GameInfo.HEIGHT / 3 - 52);
                            System.out.println("you have removed monkey from your army");
                        }
                    }
                });

                SequenceAction sa = new SequenceAction();
                sa.addAction(run);

                stage.addAction(sa);
            }
        });
        walrus.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameStartedFromMainMenu = true;

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        if (walrus.getY()!=GameInfo.HEIGHT/3) {
                            walrus.setPosition(3*GameInfo.WIDTH/5-(183/2), GameInfo.HEIGHT / 3);
                            //setCharacter(3);
                            System.out.println("You have chosen walrus yo your army");
                        }

                        else {
                            walrus.setPosition(3*GameInfo.WIDTH/5-(183/2), GameInfo.HEIGHT / 3 - 52);
                            System.out.println("you have removed walrus from your army");
                        }
                    }
                });

                SequenceAction sa = new SequenceAction();
                sa.addAction(run);

                stage.addAction(sa);
            }
        });
        moose.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameStartedFromMainMenu = true;

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        if (moose.getY()!=GameInfo.HEIGHT/3) {
                            moose.setPosition(4*GameInfo.WIDTH/5-(227/2), GameInfo.HEIGHT / 3);
                            //setCharacter(4);
                            System.out.println("You have chosen mooose to your army");
                        }

                        else {
                            moose.setPosition(4*GameInfo.WIDTH/5-(227/2), GameInfo.HEIGHT / 3 - 52);
                            System.out.println("you have removed moose from your army");
                        }
                    }
                });

                SequenceAction sa = new SequenceAction();
                sa.addAction(run);

                stage.addAction(sa);
            }
        });
        rabbit.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameStartedFromMainMenu = true;

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        //setCharacter(5);
                        if (rabbit.getY()!=GameInfo.HEIGHT/3) {
                            rabbit.setPosition(5*GameInfo.WIDTH/5-(183/2), GameInfo.HEIGHT / 3);
                            System.out.println("You have chosen rabbit to your army");
                        }

                        else {
                            rabbit.setPosition(5*GameInfo.WIDTH/5-(183/2), GameInfo.HEIGHT / 3 - 52);
                            System.out.println("you have removed rabbit from your army");
                        }
                    }
                });


                SequenceAction sa = new SequenceAction();
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

    public void setCharacter ( int id){
        System.out.println(id);
        if (addedAnimal.contains(id)){
            addedAnimal.remove(id);
        }
        else {
            addedAnimal.add(id);
        }
        if (isReady()){
            animalChoices.put(1,addedAnimal);
            System.out.println(animalChoices);
        }

    }

    public boolean isReady(){
        return this.ready;
    }

    public void setReady ( boolean ready){
        this.ready = ready;
    }

}
