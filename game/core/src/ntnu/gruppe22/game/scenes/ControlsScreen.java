package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.graphics.Texture;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
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
        game.getSb().draw(bg, 0, 0);
        game.getSb().draw(controlImg,(GameInfo.WIDTH/2)-(controlImg.getWidth()/2/1.45f), GameInfo.HEIGHT/2-controlImg.getHeight()/2/1.45f, controlImg.getWidth()/1.45f, controlImg.getHeight()/1.45f);
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
