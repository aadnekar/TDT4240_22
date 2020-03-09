package ntnu.gruppe22.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Settings extends AbstractMenu {

    private int SoundLevel;
    private Music music;

    public Settings() {
        music = Gdx.audio.newMusic(Gdx.files.internal("Glorious Morning.mp3"));
    }

    public void setMusic(boolean play) {
        if(play && !isMusic()) {
            //turn music on
            music.play();
            music.setLooping(true);
        }

        else if(!play && isMusic()) {
            //turn music off
            music.stop();
        }
    }

    public void changeVolume(int volume) {
        // change volume
        music.setVolume(volume);
    }

    public boolean isMusic() {
        return music.isPlaying();
    }

    public int getSoundLevel() {
        return SoundLevel;
    }

    public void setSoundLevel(int soundLevel) {
        SoundLevel = soundLevel;
    }

}
