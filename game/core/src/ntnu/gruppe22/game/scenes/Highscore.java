package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.HighscoreButtons;

public class Highscore extends Menu {
    BitmapFont font;
    private Texture logo;

    public static Map<String,String> highscoreList = new HashMap<>();


    public Highscore(AnimalWar game) {
        this.initializeMenu(game);

        font = new BitmapFont();
        font.getData().setScale(1, 1);
        logo = new Texture("animal-war.png");

        btns = new HighscoreButtons(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        game.getSb().draw(logo, GameInfo.WIDTH/2 - logo.getWidth()/2, GameInfo.HEIGHT - 100);
        List<String> names = new ArrayList<>(highscoreList.keySet());
        List<String> scores = new ArrayList<>(highscoreList.values());


        font.draw(game.getSb(), "First place: " + names.get(0) + " " + scores.get(0), GameInfo.WIDTH/2-90, GameInfo.HEIGHT/2 + 100);
        font.draw(game.getSb(), "Second place: " + names.get(1) + " " + scores.get(1), GameInfo.WIDTH/2-90, GameInfo.HEIGHT/2 + 50);
        font.draw(game.getSb(), "Third place: " + names.get(2) + " " + scores.get(2), GameInfo.WIDTH/2-90, GameInfo.HEIGHT/2);


        game.getSb().end();

        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
        btns.getStage().act();
    }


}
