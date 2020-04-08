package ntnu.gruppe22.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

import javax.xml.soap.Text;

import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.scenes.MainGame;


public class Animal extends Sprite {
    private int health;
    private int endurance;
    private float speed = 5f;

    private Texture animalTexture;
    private TextureRegion animalStand;
    private NinePatch healthbar;

    //creating fixture for Box2D collision detection
    private MainGame screen;
    public World world;
    public Body b2body;


    public Animal(MainGame screen, int animalKey) {
        super(new Texture(Gdx.files.internal(GameRules.getAnimalTexture(animalKey))));
        animalTexture = new Texture(Gdx.files.internal(GameRules.getAnimalTexture(animalKey)));

        health = 100;
        endurance= 5000;
        healthbar = new NinePatch(new Texture(Gdx.files.internal("animals/rectangle.png")), 0, 0, 0, 0);

        //box2D
        this.screen = screen;
        this.world = screen.getWorld();
        defineAnimal();

        //Animal texture region
        animalStand = new TextureRegion(getTexture(), 0, 0, 100/ GameInfo.PPM, 104/GameInfo.PPM);
        setBounds(0, 0, 100/ GameInfo.PPM, 104/GameInfo.PPM);
        setRegion(animalStand);
    }

    /**
     * Create animal fixture in box2d-world
     */
    public void defineAnimal(){
        Random rand = new Random();
        BodyDef bdef = new BodyDef();
        bdef.position.set(rand.nextInt(500) /GameInfo.PPM, 335 / GameInfo.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        b2body.setUserData(this);


        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(47 /GameInfo.PPM, 52/GameInfo.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public void update(float dt){
        //connect texture with box2D fixture
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
    }

    /**
     * Move animal in the target direction where the player is touching
     * the screen.
     */
    public void move() {
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() > 0) {
                this.setX( this.getX() + speed );
            } else if (Gdx.input.getX() < 0) {
                this.setX( this.getX() - speed);
            }
        }
    }

    //when hit by weapon that deals x damage
    public void setHealth(int damage) {
        this.health = damage;
        if (damage <= 0) {
            die();
        }
    }

    private void die() {
        //set new "dying" sprite
        animalTexture = new Texture("dead");
//        spriteAnimal = new Sprite(animalTexture);
        // remove this Animal after ... time (maybe add a animation? )
    }

    public int getHealth() {
        return health;
    }
    
    public int getEndurance() {
        return endurance;
    }


   /*public void draw(Batch batch) {
        super.draw(batch);
        //batch.draw(animalTexture, 200 /GameInfo.PPM, 200 /GameInfo.PPM);
        batch.draw(healthbar.getTexture(), this.getX() + 5, this.getY() + 110);
    }*/
}
