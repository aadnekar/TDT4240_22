package ntnu.gruppe22.game.states.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.scenes.MainGame;

public class Stone extends Sprite {

    private TextureRegion stone;
    public Texture stoneTexture;
    private MainGame screen;
    private World world;
    public Body b2body;
    private int damage;
    public FixtureDef fdef;
    public Fixture fixture;

    public Stone(MainGame screen, int pos) {
        super(new Texture(Gdx.files.internal("weapons/rock.png")));
        stoneTexture = new Texture(Gdx.files.internal("weapons/rock.png"));

        this.screen = screen;
        this.world = screen.getWorld();

        damage = 4;

        stone = new TextureRegion(getTexture(), 0, 0, 100/ GameInfo.PPM, 100/GameInfo.PPM);
        setBounds(0, 0, 15/ GameInfo.PPM, 15/GameInfo.PPM);
        setRegion(stone);
        defineStone(pos);

    }

    public void defineStone(int pos) {
        Random rand = new Random();
        BodyDef bdef = new BodyDef();
        bdef.position.set(screen.getCurrentAnimal().getX()+(pos/GameInfo.PPM), screen.getCurrentAnimal().getY()+(90/GameInfo.PPM));
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.bullet = true;
        b2body = world.createBody(bdef);
        b2body.setUserData(this);

        fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(7/GameInfo.PPM, 7/GameInfo.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
        fixture = b2body.createFixture(fdef);
    }

    public void update(float dt){

    }

    public int getDamage() {
        return damage;
    }

}
