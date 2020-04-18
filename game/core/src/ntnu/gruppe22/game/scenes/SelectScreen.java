package ntnu.gruppe22.game.scenes;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.huds.SelectScreenButtons;

/**
 * Denne filen inneholder en screen som gir deg mulighet til å velge characters
 * Importerer metoder som render(), update(), dispose(), handleInput()
 */

public class SelectScreen extends Menu{

    public SelectScreen(AnimalWar game) {
        this.initializeMenu(game);
        btns = new SelectScreenButtons(game);
    }

    @Override
    public void render ( float delta){
        super.render(delta);

        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        game.getSb().end();

        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
        btns.getStage().act();
    }

}

