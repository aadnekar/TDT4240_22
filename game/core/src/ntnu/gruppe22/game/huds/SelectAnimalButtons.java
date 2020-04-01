package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameManager;

public class SelectAnimalButtons {


    private AnimalWar game;
    private Stage stage;
    private ImageButton chicken;
    private ImageButton monkey;
    private ImageButton walrus;
    private ImageButton moose;
    private ImageButton rabbit;


    public SelectAnimalButtons(AnimalWar game, Stage stage){
        this.game = game;
        this.stage= stage;


        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(chicken);
        stage.addActor(monkey);
        stage.addActor(walrus);
        stage.addActor(moose);
        stage.addActor(rabbit);


        checkMusic();

    }

    private void createAndPositionButtons() {
        chicken = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Animals/chicken.png"))
        ));
        monkey = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Animals/mokey.png"))
        ));
        walrus = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Animals/walrus.png"))
        ));
        moose = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Animals/moose.png"))
        ));
        rabbit = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Animals/rabbit.png"))
        ));

        //the width in position is width of 1/5- 65 + (half the image)
        chicken.setPosition(GameInfo.WIDTH/5-92,GameInfo.HEIGHT/3-52);
        monkey.setPosition(2*GameInfo.WIDTH/5-(195/2),GameInfo.HEIGHT/3-52);
        walrus.setPosition(3*GameInfo.WIDTH/5-(183/2),GameInfo.HEIGHT/3-52);
        moose.setPosition(4*GameInfo.WIDTH/5-(227/2),GameInfo.HEIGHT/3-52);
        rabbit.setPosition(5*GameInfo.WIDTH/5-(183/2),GameInfo.HEIGHT/3-52);

    }

    private void addAllListeners() {
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
                            //selectScreen.setCharacter(1);
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
                            //selectScreen.setCharacter(2);
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
                            //selectScreen.setCharacter(3);
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
                            //selectScreen.setCharacter(4);
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
                        if (rabbit.getY()!=GameInfo.HEIGHT/3) {
                            rabbit.setPosition(5*GameInfo.WIDTH/5-(183/2), GameInfo.HEIGHT / 3);
                            //selectScreen.setCharacter(5);
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
    public Stage getStage(){
        return this.stage;
    }

    public void disposeStage(){
        this.stage.dispose();
    }

}
