package ntnu.gruppe22.game.states.wapons;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import ntnu.gruppe22.game.scenes.MainGame;
import ntnu.gruppe22.game.states.Animal;


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
        System.out.println("contactlistener");
        fa = contact.getFixtureA();

        fb = contact.getFixtureB();

        //if(fa == null || fb == null) return;
        if (fb.getBody().getUserData() instanceof Stone && fa.getBody().getUserData() instanceof Animal) {
            System.out.println("bøøøø");
            stoneFixture = fb;
            game.DestroyStone = true;
            Stone stone = (Stone) fb.getBody().getUserData();
            Animal animal = (Animal) fa.getBody().getUserData();
            System.out.println(animal.getHealth());
            System.out.println(stone.getDamage());
            animal.setHealth(stone.getDamage());
            System.out.println(animal.getHealth());
        }
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
