package ntnu.gruppe22.game.states.wapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

import ntnu.gruppe22.game.scenes.MainGame;
import ntnu.gruppe22.game.states.Animal;


public class ListenerClass implements ContactListener {

    private MainGame game;
    private World world;
    private Fixture fa;
    private Fixture fb;


    public Fixture getFa() {
        return fa;
    }

    public Fixture getFb() {
        return fb;
    }

    public ListenerClass(MainGame game, World world) {
        this.game =game;
        this.world = world;
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println("contactlistener");
        fa = contact.getFixtureA();
        fb = contact.getFixtureB();

        //if(fa == null || fb == null) return;
        if (fb.getBody().getUserData() instanceof Stone && fa.getBody().getUserData() instanceof Animal) {
            System.out.println("bøøøø");
            game.ShowStone = false;

        }
        if (fa.getBody().getUserData() instanceof Stone && fb.getBody().getUserData() instanceof Animal) {
            System.out.println("iiiiii");
            game.ShowStone = false;

        }


    }

    private void shootUpInAir(Fixture staticFixture, Fixture otherFixture){
        System.out.println("Adding Force");
        otherFixture.getBody().applyForceToCenter(new Vector2(-100000,-100000), true);
    }

    @Override
    public void endContact(Contact contact) {
/*
        if (fb.getUserData() instanceof Stone && fa.getUserData() instanceof Animal) {
            System.out.println("bøøøø");
            fb.getBody().destroyFixture(fb);
            world.destroyBody(fb.getBody());
        } else if (fa.getUserData() instanceof Stone && fb.getUserData() instanceof Animal) {
            System.out.println("bøøøø2");
            //fa.getBody().destroyFixture(fa);
            world.destroyBody(fa.getBody());


        }*/
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
