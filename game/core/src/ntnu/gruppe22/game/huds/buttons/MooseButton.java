package ntnu.gruppe22.game.huds.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameRules;

/**
 * @author aadne on 16.04.2020 11:06
 */

public class MooseButton extends AnimalButton {

    private static final float MOOSE_POSITION_X = 4* GameInfo.WIDTH/5-(227/2);

    public MooseButton() {
        super(new SpriteDrawable(new Sprite(new Texture("animals/moose.png"))));

        this.setId(GameRules.animalInstanceToId.get(GameRules.Animal.MOOSE));
        this.setButtonPosition(MOOSE_POSITION_X, Y_POSITION_DEFAULT);
    }

    @Override
    public void setButtonPosition(float x, float y) {
        this.setPosition(x, y);
    }


    public void setSelected() {
        this.setY(Y_POSITION_SELECTED);
    }

    public int getId() {
        return this.ID;
    }
}
