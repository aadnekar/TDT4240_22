package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.sprites.animals.Animal;

import ntnu.gruppe22.game.helpers.GameManager;

import ntnu.gruppe22.game.states.weapons.Weapon;


/**
 * @author aase
 */

public class MainGameButtons extends Buttons {

    private ImageButton jump;
    private ImageButton throwLeft;
    private ImageButton throwRight;
    private ImageButton useStone;
    private ImageButton useSpineyBall;
    private Animal currentAnimal;

    private float positionX = 0;
    private float positionY = 0;
    private float rectangleX;
    private float rectangleY;

    private float dt;


    public MainGameButtons(AnimalWar game) {
        this.initializeButtons(game);

        stage.addActor(jump);
        stage.addActor(throwLeft);
        stage.addActor(throwRight);

        stage.addActor(useStone);
        stage.addActor(useSpineyBall);

        checkMusic();

    }

    protected void createAndPositionButtons() {
        jump = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/jump.png"))
        ));
        throwLeft = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/throw_left.png"))
        ));
        throwRight = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/throw_right.png"))
        ));

        useSpineyBall = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/spiney_ball_button.png"))
        ));

        useStone = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("buttons/stone_button.png"))
        ));


        jump.setPosition(GameInfo.WIDTH / 2 - jump.getWidth()/2, 10);
        throwLeft.setPosition(GameInfo.WIDTH / 2 - jump.getWidth()/2 - 200, 10);
        throwRight.setPosition(GameInfo.WIDTH / 2 - jump.getWidth()/2 + 200, 10);
        useStone.setPosition(GameInfo.WIDTH - 70, GameInfo.HEIGHT - 130);
        useSpineyBall.setPosition(GameInfo.WIDTH - 70, GameInfo.HEIGHT - 70);

        rectangleX = useStone.getWidth();
        rectangleY = useSpineyBall.getWidth();


    }

    protected void addButtonListeners() {
        jump.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (currentAnimal.onGround()) {
                    currentAnimal.jump();
                }
            }
        });
        throwLeft.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                currentAnimal.throwLeft(game, dt);
            }
        });
        throwRight.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                currentAnimal.throwRight(game, dt);
            }
        });


        useStone.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: make animal throw stone
                //adding rectangle on chosen weapon
                positionX = useStone.getX();
                positionY = useStone.getY();

                //setting chosen weapon. 1 = Stone
                setChosenWeapon(1);

            }
        });

        useSpineyBall.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: make animal throw spineyBall
                //adding rectangle on chosen weapon
                positionX = useSpineyBall.getX();
                positionY = useSpineyBall.getY();

                //setting chosen weapon. 2 = SpineyBall
                setChosenWeapon(2);
            }
        });


    }

    public void replaceEventListener(Animal currentAnimal, float dt) {
        this.currentAnimal = currentAnimal;
        this.dt = dt;

    }


    private void checkMusic() {
        if(GameManager.getInstance().gameData.isMusicOn()) {
            GameManager.getInstance().playMusic();
        }
    }

    private void setChosenWeapon(int i){
        GameManager.getInstance().gameData.setChosenWeapon(i);
    }

    public Stage getStage() {
        return this.stage;
    }

    public void disposeStage() {
        this.stage.dispose();
    }

    //functions for getting positions and proportions of the weapon images
    public float getRectangleX(){return this.rectangleX;}
    public float getRectangleY(){return this.rectangleY;}
    public float getPositionX(){return this.positionX;}
    public float getPositionY(){return this.positionY;}


}


