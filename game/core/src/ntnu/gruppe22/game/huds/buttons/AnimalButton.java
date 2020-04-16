package ntnu.gruppe22.game.huds.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import ntnu.gruppe22.game.helpers.GameInfo;

/**
 * @author aadne on 16.04.2020 09:49
 */

public abstract class AnimalButton extends ImageButton {
    float Y_POSITION_DEFAULT = GameInfo.HEIGHT/3-52;
    float Y_POSITION_SELECTED = GameInfo.HEIGHT/3;
    protected int ID;

    public AnimalButton(Drawable imageUp) {
        super(imageUp);
    }

    protected abstract void setButtonPosition(float x, float y);

    public boolean select() {
        if (this.getY() == Y_POSITION_DEFAULT) {
            this.setSelected();
            return true;
        }
        this.resetPosition();
        return false;
    }

    public void setSelected() {
        this.setY(Y_POSITION_SELECTED);
    }

    public void resetPosition() {
        this.setY(Y_POSITION_DEFAULT);
    }

    public int getId() {
        return this.ID;
    }

    protected void setId(int id) {
        this.ID = id;
    }
}


