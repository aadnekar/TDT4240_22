package ntnu.gruppe22.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import ntnu.gruppe22.game.firebase.FirebaseHighScore;

public class AndroidLauncher extends AndroidApplication {

	public static AnimalWar game;
	public static FirebaseHighScore highscore;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		highscore = new FirebaseHighScore();
		game = new AnimalWar(highscore);
		initialize(game, config);
	}
}
