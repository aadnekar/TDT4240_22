package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.GameOverButtons;

public class GameOver extends Menu {
    private Texture logo;
    private GameOverButtons btns;

    BitmapFont font;


    public GameOver(AnimalWar game) {
        this.initializeMenu(game);

        logo = new Texture("animal-war.png");
        btns = new GameOverButtons(game);

        font = new BitmapFont();
    }

    public static String getLastWinner() {
        //Returns the nickname of the winner of the game. Dette skal printes på skjermen.
        //return userBtn.getNick1();
        return "getWinnerTest";
    }

    public static int getLastScore() {
        //Returns the score of the winner.
        return 99;
    }

    /*public static void addWinnerToHighscore() {
        Highscore.highscoreList.put(getLastWinner(),getLastScore());

    }*/

    @Override
    public void render(float delta) {
        super.render(delta);
        game.getSb().begin();
        game.getSb().draw(logo, GameInfo.WIDTH/2 - logo.getWidth()/2, GameInfo.HEIGHT - 100);
        font.draw(game.getSb(),"The winner is : " + getLastWinner(), GameInfo.WIDTH/2, 50);
        game.getSb().end();

        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
        btns.getStage().act();
    }

}