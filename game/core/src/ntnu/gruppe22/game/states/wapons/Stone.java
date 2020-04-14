package ntnu.gruppe22.game.states.wapons;

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
import ntnu.gruppe22.game.states.GameRules;

public class Stone extends Sprite {

    private TextureRegion stone;
    public Texture stoneTexture;
    private MainGame screen;
    private World world;
    public Body b2body;
    private int damage;
    public FixtureDef fdef;
    public Fixture fixture;

    public Stone(MainGame screen) {
        super(new Texture(Gdx.files.internal("weapons/rock.png")));
        stoneTexture = new Texture(Gdx.files.internal("weapons/rock.png"));

        this.screen = screen;
        this.world = screen.getWorld();

        damage = 1;

        stone = new TextureRegion(getTexture(), 0, 0, 100/ GameInfo.PPM, 100/GameInfo.PPM);
        setBounds(0, 0, 40/ GameInfo.PPM, 40/GameInfo.PPM);
        setRegion(stone);
        defineStone();

    }

    public void defineStone() {
        Random rand = new Random();
        BodyDef bdef = new BodyDef();
        bdef.position.set(screen.getCurrentAnimal().getX()+ screen.getCurrentAnimal().getWidth(), screen.getCurrentAnimal().getY() + 0.7f);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.bullet = true;
        b2body = world.createBody(bdef);
        b2body.setUserData(this);

        fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(20 /GameInfo.PPM, 20/GameInfo.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
        fixture = b2body.createFixture(fdef);

    }

    public void update(float dt){
        //connect texture with box2D fixture
        //setPosition(screen.getCurrentAnimal().getX() + 10, screen.getCurrentAnimal().getY() + 55);
        //setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);

    }

    public void throwStone() {

    }

    public int getDamage() {
        return damage;
    }


}
