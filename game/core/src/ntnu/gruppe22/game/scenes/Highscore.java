package ntnu.gruppe22.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.sprites.Animal;

public class Highscore implements Screen {
    AnimalWar game;
    private Animal currentAnimal;
    BitmapFont font;

    private String winner; //Skal være nicknamet


    Map<String,Integer> myMap = new HashMap<String,Integer>();


    Iterator<Animal> iteratePlayer1;
    Iterator<Animal> iteratePlayer2;

    public Highscore(AnimalWar game) {
        this.game = game;

        //font = new BitmapFont();
        myMap.put("ABC",1);
        myMap.put("ABCD",2);
        myMap.put("ABCDE",10);
        myMap.put("ABCDE",3);

        //myMap.get(0);
    }



    public void getHighscores() {
        for(Map.Entry<String, Integer> entry: myMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

    }


    //public String winner() {

    //}



    public Animal getCurrentAnimal(){
        return currentAnimal;
    }








    public void gameOver(){
        game.setScreen(new MainMenu(game));
        this.dispose();
    }


    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);




        game.getSb().begin();

        game.getSb().end();
    }


    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

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
        font.dispose();
    }

    public static void main(String args[]) {
        Highscore a = new Highscore(new AnimalWar());
        a.getHighscores();
    }
}
