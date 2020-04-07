package ntnu.gruppe22.game.states.wapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import ntnu.gruppe22.game.scenes.MainGame;
import ntnu.gruppe22.game.states.Animal;


public class ListenerClass implements ContactListener {

    MainGame game;
    World world;

    public ListenerClass(MainGame game, World world) {
        this.game =game;
        this.world = world;
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println("contactlistener");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        //if(fb.getBody().getUserData() instanceof Stone && fa.getBody().getUserData() instanceof Animal){
        System.out.println("bøøøø");


    }

    private void shootUpInAir(Fixture staticFixture, Fixture otherFixture){
        System.out.println("Adding Force");
        otherFixture.getBody().applyForceToCenter(new Vector2(-100000,-100000), true);
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
