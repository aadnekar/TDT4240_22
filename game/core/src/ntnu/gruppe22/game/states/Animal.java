package ntnu.gruppe22.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import javax.xml.soap.Text;


public class Animal extends Sprite {

    private int health;
    private int endurance;
    //private ArrayList<Weapon> inventory = new ArrayList<Weapon>();
    //private Weapon equippedWeapon;

    private float speed = 5f;

    private Texture animalTexture;
    private NinePatch healthbar;

    public Animal(int animalKey) {
        super(new Texture(Gdx.files.internal(GameRules.getAnimalTexture(animalKey))));
        health = 100;
        endurance= 5000;
        healthbar = new NinePatch(new Texture(Gdx.files.internal("animals/rectangle.png")), 0, 0, 0, 0);
    }


    /**
     * Move animal in the target direction where the player is touching
     * the screen.
     */
    public void move() {
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() > this.getX()) {
                this.setX( this.getX() + speed );
            } else if (Gdx.input.getX() < this.getX()) {
                this.setX( this.getX() - speed);
            }
        }
    }

    public Texture getAnimalTexture(){
        return animalTexture;
    }
    public Texture getBarTexture(){return healthbar.getTexture();}

    private void updateEndurance() {
        //??
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


    public void draw(Batch batch) {
        super.draw(batch);
        batch.draw(healthbar.getTexture(), this.getX() + 5, this.getY() + 110);
    }


/*
    public ArrayList<Weapon> getInventory() {
        return inventory;
    }

    public void addToInventory(Weapon weapon) {
        inventory.add(weapon);
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

 */

}
