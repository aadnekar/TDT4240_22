package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.CreateUserButtons;
import ntnu.gruppe22.game.huds.MainMenuButtons;


/**
 * @author aadne on 12.03.2020 09:53
 */

public class MainMenu extends Menu {
    private Texture logo;
    private BitmapFont font;

    public MainMenu(AnimalWar game){
        this.initializeMenu(game);

        font = new BitmapFont();
        logo = new Texture("animal-war.png");
        btns = new MainMenuButtons(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        game.getSb().draw(logo, GameInfo.WIDTH/2 - logo.getWidth()/2, GameInfo.HEIGHT - 100);


        font.draw(game.getSb(),"Player 1: " + CreateUserButtons.getNick1(), 50, 50);
        font.draw(game.getSb(),"Player 2: " + CreateUserButtons.getNick2(), 200, 50);
        game.getSb().end();

        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
        btns.getStage().act();

    }

}
