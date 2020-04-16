package ntnu.gruppe22.game.huds.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameRules;

/**
 * @author aadne on 16.04.2020 10:13
 */

public class ChickenButton extends AnimalButton {

    private static final float CHICKEN_POSITION_X = GameInfo.WIDTH/5-92;

    public ChickenButton() {
        super(new SpriteDrawable(
                new Sprite(new Texture("animals/chicken.png"))
        ));

        this.setId(GameRules.animalInstanceToId.get(GameRules.Animal.CHICKEN));
        this.setButtonPosition(CHICKEN_POSITION_X, Y_POSITION_DEFAULT);
    }

    @Override
    public void setButtonPosition(float x, float y) {
        this.setPosition(x, y);
    }

    public int getId() {
        return this.ID;
    }
}
