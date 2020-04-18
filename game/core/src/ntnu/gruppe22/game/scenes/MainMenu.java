package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.MainMenuButtons;


/**
 * @author aadne on 12.03.2020 09:53
 */

public class MainMenu extends Menu {
    private Texture logo;

    public MainMenu(AnimalWar game){
        this.initializeMenu(game);

        logo = new Texture("animal-war.png");
        btns = new MainMenuButtons(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        game.getSb().draw(logo, GameInfo.WIDTH/2 - logo.getWidth()/2, GameInfo.HEIGHT - 100);
        game.getSb().end();

        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
        btns.getStage().act();

    }

}
