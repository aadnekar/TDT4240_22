package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.GameOverButtons;

public class GameOver extends Menu {
    private Texture logo;
    private GameOverButtons btns;

    BitmapFont font;

    private String winner;
    private int score;


    public GameOver(AnimalWar game, String winner, int score) {
        this.initializeMenu(game);

        this.winner = winner;
        this.score = score;

        logo = new Texture("animal-war.png");
        btns = new GameOverButtons(game);

        font = new BitmapFont();

        if (game.toPublish()) {
            publishWinner();
        }
    }


    public String getWinner() {
        return this.winner;
    }


    private void publishWinner() {
        game.getPublisher().notifyGameover(winner, score);
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