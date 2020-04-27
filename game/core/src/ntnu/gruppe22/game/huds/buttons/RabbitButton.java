package ntnu.gruppe22.game.huds.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameRules;

/**
 * @author aadne on 16.04.2020 11:06
 */

public class RabbitButton extends AnimalButton {

    private static final float RABBIT_POSITION_X = 5* GameInfo.WIDTH/5-(183/2);

    public RabbitButton() {
        super(new SpriteDrawable(new Sprite(new Texture("animals/rabbit.png"))));

        this.ID = GameRules.animalInstanceToId.get(GameRules.Animal.RABBIT);
        this.setButtonPosition(RABBIT_POSITION_X, Y_POSITION_DEFAULT);
    }
}
