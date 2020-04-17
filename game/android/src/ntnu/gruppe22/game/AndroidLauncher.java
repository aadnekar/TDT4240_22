package ntnu.gruppe22.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import ntnu.gruppe22.game.firebase.FirebaseHighscore;

public class AndroidLauncher extends AndroidApplication {

	public static AnimalWar game;
	public static FirebaseHighscore highscore;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		game = new AnimalWar();
		highscore = new FirebaseHighscore();
		initialize(game, config);
	}
}
