package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import ntnu.gruppe22.game.helpers.GameManager;

/**
 * @author aadne on 05.03.2020 09:08
 */

public abstract class State {

    protected GameManager gameManager;
    protected Vector3 mouse;
    protected OrthographicCamera camera;
    protected SpriteBatch sb;


    public State (GameManager gameManager, SpriteBatch sb){
        this.gameManager = gameManager;
        this.camera = new OrthographicCamera();
        this.mouse = new Vector3();
        this.sb = sb;
    }


    public abstract void show();
    public abstract void handleinput();
    public abstract void update (float dt);
    public abstract void render();
    public abstract void dispose();
}
