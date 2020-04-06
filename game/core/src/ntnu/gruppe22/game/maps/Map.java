package ntnu.gruppe22.game.maps;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author jane on 29.03.2020 19:00.
 * This class loads a tilemap into the MainGame-screen
 */

public class Map{
    //map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;

    public Map() {
        /* Create map */
        //create our Box2D world, setting no gravity in X, -10 gravity in Y, and allow bodies to sleep
        world = new World(new Vector2(0, -10), true); // !!NB Do not move. The world needs to be created first
        b2dr = new Box2DDebugRenderer();
        //render tile maps
        maploader = new TmxMapLoader();
        map = maploader.load("map/straightMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1);
        creator = new B2WorldCreator(this);
    }

    public void update(OrthographicCamera camera){
        /* Render map */
        renderer.render();
        renderer.setView(camera);
        b2dr.render(world, camera.combined); //Box2DDebugLines

        //Updates Box2D-world through time
        world.step(1/60f, 6, 2);
    }


    public TiledMap getMap(){
        return map;
    }
    public World getWorld(){
        return world;
    }
}
