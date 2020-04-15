package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input;


public class CreateUserInput extends ApplicationAdapter implements Input.TextInputListener {

    String text;

    public CreateUserInput() {

    }

    @Override
    public void input(String text) {
        this.text = text;
    }

    @Override
    public void canceled() {

    }


}