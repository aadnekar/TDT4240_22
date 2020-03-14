
package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;

public class Settings implements Screen {

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

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

