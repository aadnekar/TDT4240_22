package ntnu.gruppe22.game.states.weapons;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import ntnu.gruppe22.game.scenes.MainGame;
import ntnu.gruppe22.game.sprites.animals.Animal;


public class ListenerClass implements ContactListener {

    private MainGame game;
    private World world;
    private Fixture fa;
    private Fixture fb;
    private Fixture stoneFixture;


    public Fixture getFb() {
        return stoneFixture;
    }

    public ListenerClass(MainGame game, World world) {
        this.game =game;
        this.world = world;
    }

    @Override
    public void beginContact(Contact contact) {
        fa = contact.getFixtureA();
        fb = contact.getFixtureB();
        if (fb.getBody().getUserData() instanceof Stone){
            game.DestroyWeapon = true;
            stoneFixture = fb;
            if (fa.getBody().getUserData() instanceof Animal) {
                Stone stone = (Stone) fb.getBody().getUserData();
                Animal animal = (Animal) fa.getBody().getUserData();
                animal.setHealth(stone.getDamage());
            }
        }
    }


    @Override
    public void endContact(Contact contact) {


    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


}
