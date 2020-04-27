package ntnu.gruppe22.game.huds.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import ntnu.gruppe22.game.helpers.GameInfo;

/**
 * @author aadne on 16.04.2020 09:49
 */

public abstract class AnimalButton extends ImageButton {
    protected float Y_POSITION_DEFAULT = GameInfo.HEIGHT/3-52;
    protected float Y_POSITION_SELECTED = GameInfo.HEIGHT/3;
    protected int ID;

    public AnimalButton(Drawable imageUp) {
        super(imageUp);
    }

    /**
     * Set the position of the button
     * @param x position in the x-plane
     * @param y position in the y-plane
     */
    protected void setButtonPosition(float x, float y) {
        this.setPosition(x, y);
    }

    /**
     * Set selected position or default position
     * @return true if animal is selected, else
     * @return false
     */
    public boolean select() {
        if (this.getY() == Y_POSITION_DEFAULT) {
            this.setSelected();
            return true;
        }
        this.resetPosition();
        return false;
    }

    /**
     * Set the y-plane position to an appropriate value for selected animal.
     */
    public void setSelected() {
        this.setY(Y_POSITION_SELECTED);
    }

    /**
     * Set the y-plane position to the default.
     */
    public void resetPosition() {
        this.setY(Y_POSITION_DEFAULT);
    }

    /**
     * Each animal has an id, and the button should be equal to that.
     * @return ID of the animal
     */
    public int getId() {
        return this.ID;
    }
}


