package ntnu.gruppe22.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ntnu.gruppe22.game.helpers.GameInfo;

public class Healthbar extends Sprite {

    private float startWidth;
    private final float height;


    public Healthbar() {
        super(new Texture(Gdx.files.internal("animals/rectangle.png")));
        TextureRegion animalStand = new TextureRegion(getTexture(), 0, 0, 100/ GameInfo.PPM, 104/GameInfo.PPM);
        startWidth = 40/GameInfo.PPM;
        height = 7/GameInfo.PPM;
        setBounds(0, 0, startWidth, height);
        setRegion(animalStand);
    }

    public void draw(Batch batch) {
        super.draw(batch);
    }

    public void setWidth(int animalHealth) {
        this.setBounds(0,0, animalHealth/GameInfo.PPM, height);
    }

}
