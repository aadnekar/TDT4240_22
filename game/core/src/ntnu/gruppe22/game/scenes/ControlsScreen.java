package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.ControlsScreenButtons;

public class ControlsScreen extends Menu {
    private Texture bg;
    private ControlsScreenButtons backBtn;
    private Texture controlImg;

    public ControlsScreen(AnimalWar game) {
        this.initializeMenu(game);
        backBtn = new ControlsScreenButtons(game);
        bg = new Texture("backgrounds/menu-bg.png");
        controlImg = new Texture("controls/screen.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw menu background and title
        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        game.getSb().draw(controlImg,(GameInfo.WIDTH/2)-(controlImg.getWidth()/2/1.45f), GameInfo.HEIGHT/2-controlImg.getHeight()/2/1.45f, controlImg.getWidth()/1.45f, controlImg.getHeight()/1.45f);
        game.getSb().end();

        game.getSb().setProjectionMatrix(backBtn.getStage().getCamera().combined);
        backBtn.getStage().draw();
        backBtn.getStage().act();

    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bg.dispose();
        backBtn.disposeStage();
        controlImg.dispose();
    }
}
