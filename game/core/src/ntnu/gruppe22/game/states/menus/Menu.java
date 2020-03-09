package ntnu.gruppe22.game.states.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.states.GameStateManager;
import ntnu.gruppe22.game.states.State;

/**
 * @author aadne on 06.03.2020 21:47
 */

public abstract class Menu extends State {

    final AnimalWar game;

    public Menu(final AnimalWar game, GameStateManager gameStateManager) {
        super(gameStateManager);
        this.game = game;

        this.camera.setToOrtho(false, 800, 400);
    }


    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.camera.update();
        sb.setProjectionMatrix(camera.combined);

        this.game.sb.begin();
        // Should be some font to user.
        // font.draw(sb, "Welcome to AnimalWar", pos.x, pos.y);
        this.game.sb.end();

        /*if (Button.clicked()) {
        this.gameStateManager.set(new MainGame());
        this.dispose();
        }*/
    }


}
