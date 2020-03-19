package ntnu.gruppe22.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;


public class Animal {

    private int health;
    private int endurance;
    //private ArrayList<Weapon> inventory = new ArrayList<Weapon>();
    //private Weapon equippedWeapon;

    private int startX, startY;
    private int endX, endY;
    private float speedX = 5f;
    private boolean moving;

    private Texture animal;
    private Sprite spriteAnimal;
    private Rectangle AnimalRect;
    private NinePatch healthbar;

    public Animal(int x, int y) {
        startX = x;
        startY = y;
        moving = true;
        health = 100;
        endurance= 5000;
        animal = new Texture(Gdx.files.internal("player.png"));
        spriteAnimal = new Sprite(animal);
        //AnimalRect = new Rectangle(x,y, spriteAnimal.getWidth(),spriteAnimal.getHeight());
        healthbar = new NinePatch(new Texture(Gdx.files.internal("Rectangle.png")), 0, 0, 0, 0);
    }

    //moves Animal to player touch (x-coordinate only)
    public void move() {
        if (Gdx.input.justTouched()) {
            endX = Gdx.input.getX();
            endY = Gdx.input.getY();
            moving = true;
        }

        if (moving) {
            if (endX > startX) {
                startX += speedX;
            }
            if (endX < startX) {
                startX -= speedX;
            }
            if (startX == endX + 2) {
                moving = false;
                startX = endX;
            }
        }
    }

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
        animal = new Texture("dead");
        spriteAnimal = new Sprite(animal);
        // remove this Animal after ... time (maybe add a animation? )
    }


    public int getHealth() {
        return health;
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
