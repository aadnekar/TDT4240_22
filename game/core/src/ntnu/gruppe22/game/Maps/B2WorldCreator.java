package ntnu.gruppe22.game.Maps;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import ntnu.gruppe22.game.scenes.MainGame;

/**
 * @author Jane. Created on 29.03.2020 18:40
 * this Class creates renders the object-layers of the map
 * which creates boxes around the tiles for collision detection
 */

public class B2WorldCreator {

    public B2WorldCreator(Map map) {
        World world = map.getWorld();
        TiledMap TileMap = map.getMap();

        //create body and fixture variables
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        Body body;

        //create ground bodies/fixtures
        for (MapObject object : TileMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2), (rect.getY() + rect.getHeight() / 2));
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 , rect.getHeight() / 2);
            body.createFixture(shape,0.0f);
        }
    }
}
