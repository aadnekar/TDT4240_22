package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.huds.GameOverButtons;

public class GameOver implements Screen {
    private AnimalWar game;
    private OrthographicCamera camera;
    private Viewport gameViewport;
    private Texture bg;
    private Texture logo;
    private GameOverButtons btns;

    BitmapFont font;


    public GameOver(AnimalWar game) {
        this.game = game;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        this.camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);

        bg = new Texture("backgrounds/menu-bg.png");

        logo = new Texture("animal-war.png");
        btns = new GameOverButtons(game);

        font = new BitmapFont();
    }

    public static String getWinner() {
        //Returns the nickname of the winner of the game. Dette skal printes på skjermen.
        return "winner!!";
    }

    public static int getScore() {
        //Returns the score of the winner.
        return 123;
    }

    public static void addWinnerToHighscore() {
        Highscore.highscoreList.put(getWinner(),getScore());

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getSb().begin();
        game.getSb().draw(bg, 0, 0);
        game.getSb().draw(logo, GameInfo.WIDTH/2 - logo.getWidth()/2, GameInfo.HEIGHT - 100);

        font.draw(game.getSb(),"The winner is : " + getWinner(), GameInfo.WIDTH/2, 50);


        game.getSb().end();
        game.getSb().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
        btns.getStage().act();
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
        btns.disposeStage();
    }
}