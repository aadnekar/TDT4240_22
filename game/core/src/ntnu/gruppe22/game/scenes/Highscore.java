package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.HashMap;
import java.util.Map;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.HighscoreButtons;

public class Highscore implements Screen {
    AnimalWar game;
    BitmapFont font;

    private Texture bg;
    private Texture logo;
    private HighscoreButtons btns;
    public static String highscores;


    public static Map<String,String> highscoreList = new HashMap<String,String>();


    public Highscore(AnimalWar game) {
        //GameOver.addWinnerToHighscore();
        this.game = game;

        this.game = game;
        font = new BitmapFont();

        bg = new Texture("backgrounds/menu-bg.png");

        logo = new Texture("animal-war.png");
        btns = new HighscoreButtons(game);

        //font = new BitmapFont();
        //highscoreList.put("ABC",1);
        //highscoreList.put("ABCD",2);
        //highscoreList.put("ABCDE",10);
        //highscoreList.put("ABCDE",3);

        //myMap.get(0);
    }


    public void getHighscores() {
        for(Map.Entry<String, String> entry: highscoreList.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            //String name = entry.getKey();
            //int number = entry.getValue();
        }
    }


    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        game.getSb().draw(logo, GameInfo.WIDTH/2 - logo.getWidth()/2, GameInfo.HEIGHT - 100);

        font.draw(game.getSb(),"Highscorelist: " + highscores, 200, 50);

        game.getSb().end();
        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
        btns.getStage().act();
    }

    public boolean isGameOver() {
        return true;
    }


    public void getScores(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("This is the entry: " + entry.getValue());

        }

    }

    @Override
    public void show() {

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
        bg.dispose();
        btns.disposeStage();
    }

}
