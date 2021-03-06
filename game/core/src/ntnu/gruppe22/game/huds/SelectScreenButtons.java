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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameManager;
import ntnu.gruppe22.game.helpers.GameRules;
import ntnu.gruppe22.game.huds.buttons.AnimalButton;
import ntnu.gruppe22.game.huds.buttons.ChickenButton;
import ntnu.gruppe22.game.huds.buttons.MonkeyButton;
import ntnu.gruppe22.game.huds.buttons.MooseButton;
import ntnu.gruppe22.game.huds.buttons.RabbitButton;
import ntnu.gruppe22.game.huds.buttons.WalrusButton;
import ntnu.gruppe22.game.scenes.MainGame;
import ntnu.gruppe22.game.scenes.MainMenu;


public class SelectScreenButtons extends Buttons {

    private ImageButton back;
    private ImageButton ready;
    private ImageButton ready2;
    private AnimalButton chicken;
    private AnimalButton monkey;
    private AnimalButton walrus;
    private AnimalButton moose;
    private AnimalButton rabbit;
    private ImageButton choosePlayer1;
    private ImageButton choosePlayer2;
    private int playerNumber;

    private HashMap<Integer, ArrayList<Integer>> animalChoices;
    private List<AnimalButton> animalButtonList;


    public SelectScreenButtons(AnimalWar game){
        this.game=game;
        this.playerNumber=0;
        this.animalChoices = new HashMap<>();
        this.animalChoices.put(playerNumber, new ArrayList<Integer>());

        this.animalButtonList = new ArrayList<>();
        this.initializeButtons(game);

        stage.addActor(ready);
        stage.addActor(ready2);
        stage.addActor(choosePlayer1);
        stage.addActor(choosePlayer2);
        stage.addActor(back);
        stage.addActor(chicken);
        stage.addActor(monkey);
        stage.addActor(walrus);
        stage.addActor(moose);
        stage.addActor(rabbit);

    }

    protected void createAndPositionButtons() {
        ready = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/play-btn.png"))
        ));
        ready2 = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/continue.png"))
        ));
        back = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("settings/home.png"))
        ));
        choosePlayer1 = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/choose-player1.png"))
        ));
        choosePlayer2 = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/choose-player2.png"))
        ));

        chicken = new ChickenButton();
        animalButtonList.add(chicken);
        monkey = new MonkeyButton();
        animalButtonList.add(monkey);
        walrus = new WalrusButton();
        animalButtonList.add(walrus);
        moose = new MooseButton();
        animalButtonList.add(moose);
        rabbit = new RabbitButton();
        animalButtonList.add(rabbit);

        choosePlayer1.setPosition(GameInfo.WIDTH / 2 - choosePlayer1.getWidth() / 2, GameInfo.HEIGHT / 2 + 140);
        choosePlayer2.setPosition(GameInfo.WIDTH / 2 - choosePlayer2.getWidth() / 2, GameInfo.HEIGHT / 2 + 140);
        choosePlayer2.setVisible(false);

        ready.setPosition(GameInfo.WIDTH / 2 - ready.getWidth() / 2, GameInfo.HEIGHT / 2 + 40);
        ready2.setPosition(GameInfo.WIDTH / 2 - ready2.getWidth() / 2, GameInfo.HEIGHT / 2 + 40);
        ready.setVisible(false);

        back.setPosition(5, GameInfo.HEIGHT - 70);
    }

    protected void addButtonListeners() {

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
                                stage.dispose();
                            }
                        }
                    }
                });

                SequenceAction sa = new SequenceAction();
                sa.addAction(run);

                stage.addAction(sa);
            }
        });

        ready2.addListener(new ChangeListener() {
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
                                choosePlayer1.setVisible(false);
                                choosePlayer2.setVisible(true);
                                ready2.setVisible(false);
                                ready.setVisible(true);
                            }
                            else {
                                game.setScreen(new MainGame(game, animalChoices));
                                stage.dispose();
                            }
                        }
                    }
                });

                SequenceAction sa = new SequenceAction();
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
                System.out.println("Chicken ID: " + chicken.getId());
                selectAnimal(chicken.select(), chicken.getId());
            }
        });

        monkey.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Monkey ID: " + monkey.getId());
                selectAnimal(monkey.select(), monkey.getId());
            }
        });

        walrus.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Walrus ID: " + walrus.getId());
                selectAnimal(walrus.select(), walrus.getId());
            }
        });

        moose.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Moose ID: " + moose.getId());
                selectAnimal(moose.select(), moose.getId());
            }
        });

        rabbit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Rabbit ID: " + rabbit.getId());
                selectAnimal(rabbit.select(), rabbit.getId());
            }
        });
    }

    public void selectAnimal(boolean add, int id) {
        checkLengthAndRemoveAnimal();
        if (add == true) {
            addAnimalChoice(id);
        } else {
            removeAnimalChoice(id);
        }
    }

    private void removeAnimalChoice(int id) {
        int index = animalChoices.get(playerNumber).indexOf(id);
        if (index >= 0) {
            animalChoices.get(playerNumber).remove(index);
        }
    }

    private void addAnimalChoice(int id) {
        animalChoices.get(playerNumber).add(id);
    }

    /**
     * If too many animals are selected by a player, remove the first selected animal.
     */
    private void checkLengthAndRemoveAnimal() {
        if (animalChoices.get(playerNumber).size() == GameRules.NUMBER_OF_CHARS){
            getAnimalButton(animalChoices.get(playerNumber).remove(0)).resetPosition();
        }
    }

    /**
     * Return the button matching the id of an animal.
     * @param id - 1 is equal to the index of the given animal.
     * @return the related animalButton.
     */
    private AnimalButton getAnimalButton(int id) {
        return animalButtonList.get(id - 1);
    }

    private void nextPlayer() {
        for (AnimalButton btn : animalButtonList) {
            btn.resetPosition();
        }
        playerNumber++;
        animalChoices.put(playerNumber, new ArrayList<Integer>());
    }

    private boolean hasFullRoster(){
        return animalChoices.get(playerNumber).size() == GameRules.NUMBER_OF_CHARS;
    }

}
