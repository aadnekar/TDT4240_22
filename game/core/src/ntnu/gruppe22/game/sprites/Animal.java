package ntnu.gruppe22.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.scenes.MainGame;
import ntnu.gruppe22.game.helpers.GameRules;
import ntnu.gruppe22.game.utils.BodyEditorLoader;


public class Animal extends Sprite {
    private static float MAX_VELOCITY = 1f;

    private int health;
    private int endurance;
    private float speed = 5f;

    private TextureRegion animalStand;
    private NinePatch healthbar;

    //creating fixture for Box2D collision detection
    private MainGame screen;
    public World world;
    public Body body;

    /**
     * when flipped is true animal points to the left
     */
    private boolean flipped;


    public Animal(MainGame screen, int animalKey) {
        super(new Texture(Gdx.files.internal(GameRules.getAnimalTexture(animalKey))));

        flipped = false;
        health = 30;
        endurance= 5000;
        healthbar = new NinePatch(new Texture(Gdx.files.internal("animals/rectangle.png")), 0, 0, 0, 0);

        //box2D
        this.screen = screen;
        this.world = screen.getWorld();

        defineAnimal(animalKey);

        //Animal texture region
        animalStand = new TextureRegion(getTexture(), 0, 0, 100/ GameInfo.PPM, 102/GameInfo.PPM);
        setBounds(0, 0, getWidth()/1.5f/ GameInfo.PPM, getHeight()/1.5f/GameInfo.PPM);
        setRegion(animalStand);
    }

    /**
     * Create animal fixture in box2d-world
     */
    public void defineAnimal(int animalKey){
        Random rand = new Random();

        ShapeManager shapeManager = new ShapeManager(animalKey);
        BodyEditorLoader bodyEditorLoader = new BodyEditorLoader(Gdx.files.internal(shapeManager.getFilePath()));

        // Defines a body for box2d
        BodyDef bodyDef = new BodyDef();


        // Since animals move we need it to be dynamic, the opposite would be ground which would be static.
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(rand.nextInt(1800) /GameInfo.PPM, rand.nextInt(600) / GameInfo.PPM);

        // Create the body in the world defined in MainGame.
        this.body = world.createBody(bodyDef);
        this.body.setUserData(this);
        // Restrict rotation to make sure the animal does not tilt.
        this.body.setFixedRotation(true);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1f;      // Absolute unit, innit?
        fixtureDef.friction = 0.1f;     // Friction against other objects
        //fixtureDef.restitution = 0.4f;  // Bounciness

        bodyEditorLoader.attachFixture(body, shapeManager.getName(), fixtureDef, 33.5f/ GameInfo.PPM);

    }


    public void update(float dt){
        setX(getPositionX() - getWidth()/2);
        setY(getPositionY() - getHeight()/2);
    }

    private float getPositionX() {
        return this.body.getPosition().x;
    }

    private float getPositionY() {
        return this.body.getPosition().y;
    }

    private void moveRight() {
        flipAnimal(true);
        this.body.applyLinearImpulse(0.05f, 0, getPositionX(), getPositionY(), true);
    }

    private void moveLeft() {
        flipAnimal(false);
        this.body.applyLinearImpulse(-0.05f, 0, getPositionX(), getPositionY(), true);
    }

    private boolean hasMaxVelocity() {
        return Math.abs(this.body.getLinearVelocity().x) >= MAX_VELOCITY;
    }

    public boolean onGround() {
        return this.body.getLinearVelocity().y == 0;
    }


    /**
     * Move animal in the target direction where the player is touching
     * the screen.
     * Need to scale the input location to the Pixel Per Meter (PPM).
     */
    public void move(OrthographicCamera camera){


        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x > getPositionX() && !hasMaxVelocity()) {
                if(getPositionX() < (1920/GameInfo.PPM)-getWidth()){
                    moveRight();
                }
            } else if (touchPos.x <= getPositionX() && !hasMaxVelocity()) {
                if(getPositionX() > 1){
                    moveLeft();
                }

            }
        }
    }

    public void jump() {
        this.body.applyLinearImpulse(0f, 0.5f, getPositionX(), getPositionY(), true);

    }

    public void throwRight(AnimalWar game, float dt) {
        screen.setStone(110);
        flipAnimal(true);
        screen.getStone().b2body.applyLinearImpulse(new Vector2(2f, 2f), screen.getStone().b2body.getWorldCenter(), true);
        drawStone(game);
        screen.getStone().update(dt);
    }


    public void throwLeft(AnimalWar game, float dt) {
        screen.setStone(-5);
        flipAnimal(false);
        screen.getStone().b2body.applyLinearImpulse(new Vector2(-2f, 2f), screen.getStone().b2body.getWorldCenter(), true);
        drawStone(game);
        screen.getStone().update(dt);
    }

    private void drawStone(AnimalWar game){
        game.getSb().begin();
        screen.getStone().draw(game.getSb());
        game.getSb().end();
    }


    private void flipAnimal(boolean right){
        if(flipped){
            if(right){
                this.flip(true, false);
                flipped = false;
            } else{
                this.flip(false, false);
            }
        } else{
            if(right){
                this.flip(false, false);
            } else{
                this.flip(true, false);
                flipped = true;
            }
        }

    }

    public boolean isFlipped(){
        return flipped;
    }

    //when hit by weapon that deals x damage
    public void setHealth(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            die();
        }
    }

    private void die() {
        this.flip(false, true);
        screen.removeAnimal(this);


    }

    public int getHealth() {
        return health;
    }
    
    public int getEndurance() {
        return endurance;
    }

   public void draw(Batch batch) {
        super.draw(batch);
        //batch.draw(this.getTexture(), getPositionX(), getPositionY());
        //batch.draw(healthbar.getTexture(), this.getX() + 5, this.getY() + 110);
    }
}
