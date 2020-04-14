package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.Input;

import java.awt.Label;

public class CreateUserInput implements Input.TextInputListener {
    private Label label;
    private static String name;

    public CreateUserInput(Label targetLabel) {
        label = targetLabel;
    }

    @Override
    public void input(String text) {
        label.setText(text);
        name = label.getText();
        System.out.println(name);
    }

    @Override
    public void canceled() {
        label.setText("canceled by user");
    }
}
