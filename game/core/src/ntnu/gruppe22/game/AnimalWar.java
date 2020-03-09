package ntnu.gruppe22.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import ntnu.gruppe22.game.states.GameStateManager;

public class AnimalWar extends ApplicationAdapter {

	public static String APP_TITLE = "Animal War";
	public static final int HEIGHT = 480;
	public static final int WIDTH = 800;

	private SpriteBatch sb;
	private GameStateManager gsm;
	
	@Override
	public void create () {
		sb = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime()); //difference between render times
		gsm.render(sb);
	}
	
	@Override
	public void dispose () { sb.dispose(); }


}
