
package ntnu.gruppe22.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * @author aase and hildegun on 17.03.20
 */


public class GameMusic {

    private float volume;
    private Music music;

    public GameMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("Glorious Morning.mp3"));
        volume = 0;
        music.setVolume(volume);
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

    public void changeVolume(float volume) {
        // change volume
        setNewVolume(volume);
        System.out.println(this.volume);
        music.setVolume(this.volume);
    }

    public boolean isMusic() {
        return music.isPlaying();
    }

    public void setNewVolume(float volume) {
        if(this.volume + volume < 0 || this.volume + volume > 1){
            System.out.println("volume not changed");
        } else {
            this.volume += volume;
        }

    }


}

