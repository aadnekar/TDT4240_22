package ntnu.gruppe22.game.huds.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import ntnu.gruppe22.game.helpers.GameInfo;

/**
 * @author aadne on 16.04.2020 11:06
 */

public class RabbitButton extends AnimalButton {

    private static final float RABBIT_POSITION_X = 5* GameInfo.WIDTH/5-(183/2);

    public RabbitButton() {
        super(new SpriteDrawable(new Sprite(new Texture("animals/rabbit.png"))));

        this.setId(5);
        this.setButtonPosition(RABBIT_POSITION_X, Y_POSITION_DEFAULT);
    }

    @Override
    public void setButtonPosition(float x, float y) {
        this.setPosition(x, y);
    }


    public int getId() {
        return this.ID;
    }
}
