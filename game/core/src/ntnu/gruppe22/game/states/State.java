package ntnu.gruppe22.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

/**
 * @author aadne on 05.03.2020 09:08
 */

public abstract class State {

    protected GameStateManager gameStateManager;
    protected Vector3 mouse;
    protected OrthographicCamera camera;


    public State (GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        this.camera = new OrthographicCamera();
        this.mouse = new Vector3();

    }



    public abstract void handleinput();
    public abstract void update (float dt);
    public abstract void render();
    public abstract void dispose();
}
