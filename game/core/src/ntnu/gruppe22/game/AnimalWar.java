package ntnu.gruppe22.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ntnu.gruppe22.game.helpers.GameManager;
import ntnu.gruppe22.game.scenes.CreateUser;
import ntnu.gruppe22.game.utils.Publisher;
import ntnu.gruppe22.game.utils.Subscriber;

public class AnimalWar extends Game {

	public static String APP_TITLE = "Animal War";

	private SpriteBatch sb;
	private Publisher publisher;
	private boolean toPublish;

	public AnimalWar() {
		this.toPublish = false;
	}

	public AnimalWar(Subscriber s) {
		publisher = new Publisher();
		publisher.subscribe(s);
		this.toPublish = true;
	}
	
	@Override
	public void create () {
		sb = new SpriteBatch();
		GameManager.getInstance().initializeGameData();
		setScreen(new CreateUser(this));
	}

	@Override
	public void render () {
		super.render();
	}

	public SpriteBatch getSb() {
		return this.sb;
	}

	public Publisher getPublisher() {
		return this.publisher;
	}

	public boolean toPublish() {
		return this.toPublish;
	}
}
