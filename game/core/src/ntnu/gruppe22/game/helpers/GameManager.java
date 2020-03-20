package ntnu.gruppe22.game.helpers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class GameManager {
    private static GameManager ourInstance = new GameManager();

    public GameData gameData;
    private Json json = new Json();
    private FileHandle fileHandle = Gdx.files.local("bin/GameData.json");

    public boolean gameStartedFromMainMenu, isPaused = true;
    private Music music;

    private GameManager() {

    }


    /**
     * Either create a new game data file or load the existing one.
     */
    public void initializeGameData() {
        if(!fileHandle.exists()) {
            gameData = new GameData();
            gameData.setMusicOn(true);
            gameData.setMusicVolume(0.5f);

            saveData();
        } else {
            loadData();
        }
    }


    /**
     * Save the data to file and encrypt it.
     */
    public void saveData() {
        if(gameData != null) {
            fileHandle.writeString(
                    Base64Coder.encodeString(json.prettyPrint(gameData)),
                    false
            );
        }
    }


    /**
     * Decrypt the file content and store it as gameData.
     */
    public void loadData() {
        gameData = json.fromJson(
                GameData.class,
                Base64Coder.decodeString(fileHandle.readString())
        );
    }


    /**
     * Set the music to default if a song is not currently selected
     * Play music only if it's not already playing.
     */
    public void playMusic() {
        if(music == null) {
            music = Gdx.audio.newMusic(Gdx.files.internal("sounds/blues.ogg"));
            System.out.println("Music is playing...");
        }
        if(!music.isPlaying()) {
            music.play();
        }
    }


    /**
     * Stop the music if it's already currently playing.
     * Dispose the music to optimize memory.
     */
    public void stopMusic() {
        if(music.isPlaying()) {
            music.stop();
            music.dispose();
        }
    }


    /**
     * Return the actual instance of the class.
     * @return ourInstance
     */
    public static GameManager getInstance() {
        return ourInstance;
    }


    /**
     * Increase the sound volume by 1 unit of 0.1
     * Store the current music volume to game data.
     */
    public void incrementMusicVolume() {
        // No need to change the volume if it's at max.
        if (gameData.getMusicVolume() < 1f) {
            setMusicVolume(gameData.getMusicVolume() + 0.1f);
        } else {
            System.out.println("Music Volume is at maximum.");
        }
    }


    /**
     * Decrease the sound volume by 1 unit of 0.1
     * Store the current music volume to game data.
     */
    public void decrementMusicVolume() {
        // No need to change the volume if it's at minimum (Silent).
        if (gameData.getMusicVolume() > 0f) {
            setMusicVolume(gameData.getMusicVolume() - 0.1f);
        } else {
            System.out.println("Music volume is at minimum");
        }
    }


    /**
     * Changes the Volume of the playing music.
     * Handle the storing of the current music volume and the actual change.
     */
    public void setMusicVolume(float volume) {
        if (volume < 0f) {
            volume = 0f;
        } else if (volume > 1f) {
            volume = 1f;
        }
        System.out.println("Music volume will be set to: " + volume);
        // Finally change the music volume
        gameData.setMusicVolume(volume);
        this.music.setVolume(volume);
    }


}
