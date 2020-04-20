package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.graphics.Texture;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.huds.ControlsScreenButtons;

public class ControlsScreen extends Menu {
    private Texture controlImg;

    public ControlsScreen(AnimalWar game) {
        this.initializeMenu(game);
        btns = new ControlsScreenButtons(game);
        controlImg = new Texture("controls/screen.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Draw menu background and title
        game.getSb().begin();
        game.getSb().draw(bg, 0, 0, gameViewport.getWorldWidth(), gameViewport.getWorldHeight());
        game.getSb().draw(controlImg,gameViewport.getWorldWidth()/2 - (gameViewport.getWorldWidth() / 2)/2, gameViewport.getWorldHeight()/2 - (gameViewport.getWorldHeight()/2)/2, gameViewport.getWorldWidth() / 2, gameViewport.getWorldHeight() / 2);
        game.getSb().end();

        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
        btns.getStage().act();

    }

    @Override
    public void dispose() {
        super.dispose();
        controlImg.dispose();
    }
}
