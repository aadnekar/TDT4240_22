package ntnu.gruppe22.game.maps;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameManager;
import ntnu.gruppe22.game.huds.MapButtons;
import ntnu.gruppe22.game.scenes.Maps;

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

    //map paths
    private final String winterMap = "map/winterMap.tmx";
    private final String platformMap = "map/platform2Map.tmx";
    private String chosenMap;

    public Map(World world) {
        this.world = world; // !!NB Do not move. The world needs to be created first
        b2dr = new Box2DDebugRenderer();
        setChosenMap();

        //render tile maps
        maploader = new TmxMapLoader();
        map = maploader.load(chosenMap);
        renderer = new OrthogonalTiledMapRenderer(map,1/GameInfo.PPM);
        creator = new B2WorldCreator(this);
    }

    //render map
    public void update(OrthographicCamera camera){
        renderer.render();
        renderer.setView(camera);
        b2dr.render(world, camera.combined); //Box2DDebugLines
    }

    private void setChosenMap(){
        int currentMap = GameManager.getInstance().gameData.getChosenMap();
        if(currentMap == 1){
            chosenMap = platformMap;
        }else if (currentMap == 2){
            chosenMap = winterMap;
        }
    }


    public TiledMap getMap(){
        return map;
    }
    public World getWorld(){
        return world;
    }
}
