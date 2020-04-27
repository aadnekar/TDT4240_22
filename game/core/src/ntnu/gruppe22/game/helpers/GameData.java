package ntnu.gruppe22.game.helpers;

/**
 * @author aadne on 12.03.2020 12:51
 * @Coauthors aase and hildegun
 */

public class GameData {

    private boolean musicOn;
    private float musicVolume;
    private int chosenMap = 1;

    public boolean isMusicOn() {
        return musicOn;
    }

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }

    public float getMusicVolume() {
        return this.musicVolume;
    }

    public void setMusicVolume(float volume) {
        this.musicVolume = volume;
    }

    public void setChosenMap(int mapID){ this.chosenMap = mapID; }

    public int getChosenMap(){ return this.chosenMap;}
}
