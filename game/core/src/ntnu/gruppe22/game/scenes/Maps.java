package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.MapButtons;

public class Maps extends Menu {
    private ShapeRenderer sr;
    private Texture title;
    private MapButtons btns;


    public Maps(AnimalWar game) {
        this.initializeMenu(game);

        sr = new ShapeRenderer();
        btns = new MapButtons(game);

        title = new Texture("settings/choose-map.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);


       // Draw menu background and title
        game.getSb().begin();
        game.getSb().draw(title,190 , GameInfo.HEIGHT - 80, 334, 43);
        game.getSb().end();

        // Draw rectangle around chosen map
        sr.setProjectionMatrix(camera.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(new Color(255,255,0,0));
        if(isMapChosen()){
           sr.rect(btns.getPositionX() - 3, btns.getPositionY() - 3, btns.getRectangleX() + 6, btns.getRectangleY() + 6);
        }
        sr.end();

        //Draw map buttons
        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
        btns.getStage().act();


    }


    public Boolean isMapChosen(){
        if ( btns.getPositionY()> 0){
            return true;
        }
        return false;
    }
}
