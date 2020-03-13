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

    public void initializeGameData() {
        if(!fileHandle.exists()) {
            gameData = new GameData();

            gameData.setMusicOn(true);

            saveData();
        } else {
            loadData();
        }
    }

    public void saveData() {
        if(gameData != null) {
            fileHandle.writeString(
                    Base64Coder.encodeString(json.prettyPrint(gameData)),
                    false
            );
        }
    }

    public void loadData() {
        gameData = json.fromJson(
                GameData.class,
                Base64Coder.decodeString(fileHandle.readString())
        );
    }

    public void playMusic() {
        if(music == null) {
            music = Gdx.audio.newMusic(Gdx.files.internal("sounds/blues.ogg"));
            System.out.println("Music is playing...");
        }
        if(!music.isPlaying()) {
            music.play();
        }
    }

    public void stopMusic() {
        if(music.isPlaying()) {
            music.stop();
            music.dispose();
        }
    }

    public static GameManager getInstance() {
        return ourInstance;
    }



}
