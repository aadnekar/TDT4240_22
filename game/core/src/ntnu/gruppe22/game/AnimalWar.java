package ntnu.gruppe22.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import ntnu.gruppe22.game.helpers.GameManager;
import ntnu.gruppe22.game.scenes.MainMenu;

public class AnimalWar extends Game {

	public static String APP_TITLE = "Animal War";
	public static final int HEIGHT = 480;
	public static final int WIDTH = 800;

	private SpriteBatch sb;
	
	@Override
	public void create () {
		sb = new SpriteBatch();
		GameManager.getInstance().initializeGameData();
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}

	public SpriteBatch getSb() {
		return this.sb;
	}
}
