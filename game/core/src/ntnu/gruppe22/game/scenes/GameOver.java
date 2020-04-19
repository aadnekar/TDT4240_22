package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.CreateUserButtons;
import ntnu.gruppe22.game.huds.GameOverButtons;

public class GameOver extends Menu {
    private Texture logo;
    private GameOverButtons btns;
    private static String printWinner;
    private static int finalScore;
    BitmapFont font;


    public GameOver(AnimalWar game) {
        this.initializeMenu(game);

        logo = new Texture("animal-war.png");
        btns = new GameOverButtons(game);

        font = new BitmapFont();
    }

    public static void setWinner(String winner) {
        if(winner.equals("Player 2")) {
            if(CreateUserButtons.getNick2().equals("default")) {
                printWinner = "Player 2";
            }
            else {
                printWinner = CreateUserButtons.getNick2();
            }
        }
        else if (winner.equals("Player 1")) {
            if(CreateUserButtons.getNick1().equals("default")) {
                printWinner = "Player 1";
            }
            else {
                printWinner = CreateUserButtons.getNick1();
            }
        }
    }

    public static String getWinner() {
        return printWinner;
    }



    public static int getWinnerScore() {
        return finalScore;
    }

    public static void setWinnerScore(int score) {
        finalScore = score;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        game.getSb().draw(logo, GameInfo.WIDTH/2 - logo.getWidth()/2, GameInfo.HEIGHT - 100);
        font.draw(game.getSb(),"The winner is : " + getWinner(), GameInfo.WIDTH/2, 50);
        game.getSb().end();

        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
        btns.getStage().act();
    }

}