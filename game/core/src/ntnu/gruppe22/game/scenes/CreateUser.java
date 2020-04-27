package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.CreateUserButtons;

public class CreateUser extends Menu {

    private Texture logo;
    private CreateUserButtons btns;

    BitmapFont font;

    public CreateUser(AnimalWar game){
        this.initializeMenu(game);

        logo = new Texture("backgrounds/choose-player-names.png");
        btns = new CreateUserButtons(game);

        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        game.getSb().draw(logo, GameInfo.WIDTH/2 - logo.getWidth()/2, GameInfo.HEIGHT - 100);
        font.draw(game.getSb(),"Player 1: " + btns.getNick1(), 50, 50);
        font.draw(game.getSb(),"Player 2: " + btns.getNick2(), 200, 50);
        game.getSb().end();

        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
        btns.getStage().act();
    }

}