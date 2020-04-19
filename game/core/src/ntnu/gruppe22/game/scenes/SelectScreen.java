package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.huds.CreateUserButtons;
import ntnu.gruppe22.game.huds.SelectScreenButtons;

/**
 * Denne filen inneholder en screen som gir deg mulighet til å velge characters
 * Importerer metoder som render(), update(), dispose(), handleInput()
 */

public class SelectScreen extends Menu{
    private BitmapFont font;

    public SelectScreen(AnimalWar game) {
        this.initializeMenu(game);
        btns = new SelectScreenButtons(game);
        font = new BitmapFont();
    }

    @Override
    public void render ( float delta){
        super.render(delta);

        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);

        font.draw(game.getSb(),"Player 1: " + CreateUserButtons.getNick1(), 50, 50);
        font.draw(game.getSb(),"Player 2: " + CreateUserButtons.getNick2(), 200, 50);
        game.getSb().end();

        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);

        btns.getStage().draw();
        btns.getStage().act();
    }

}

