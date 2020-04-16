package ntnu.gruppe22.game.huds.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameRules;

/**
 * @author aadne on 16.04.2020 11:06
 */

public class WalrusButton extends AnimalButton {

    private static final float WALRUS_POSITION_X = 3* GameInfo.WIDTH/5-(183/2);

    public WalrusButton() {
        super(new SpriteDrawable(new Sprite(new Texture("animals/walrus.png"))));

        this.setId(GameRules.animalInstanceToId.get(GameRules.Animal.WALRUS));
        this.setButtonPosition(WALRUS_POSITION_X, Y_POSITION_DEFAULT);
    }

    @Override
    public void setButtonPosition(float x, float y) {
        this.setPosition(x, y);
    }

    public int getId() {
        return this.ID;
    }
}
