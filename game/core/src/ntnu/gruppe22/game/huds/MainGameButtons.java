package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.sprites.animals.Animal;
import ntnu.gruppe22.game.helpers.GameManager;

/**
 * @author aase
 */

public class MainGameButtons extends Buttons {

    private ImageButton jump;
    private ImageButton throwLeft;
    private ImageButton throwRight;
    private Animal currentAnimal;

    private float dt;


    public MainGameButtons(AnimalWar game) {
        this.initializeButtons(game);

        stage.addActor(jump);
        stage.addActor(throwLeft);
        stage.addActor(throwRight);
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

        jump.setPosition(GameInfo.WIDTH / 2 - jump.getWidth() / 2, 10);
        throwLeft.setPosition(GameInfo.WIDTH / 2 - jump.getWidth() / 2 - 200, 10);
        throwRight.setPosition(GameInfo.WIDTH / 2 - jump.getWidth() / 2 + 200, 10);
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
    }

    public void replaceEventListener(Animal currentAnimal, float dt) {
        this.currentAnimal = currentAnimal;
        this.dt = dt;

    }
}


