package ntnu.gruppe22.game.firebase;

import java.util.HashMap;
import java.util.Map;

public class UserHelperClass {

    public Map<String, Integer> highscoreList = new HashMap<>();


    public UserHelperClass() {

    }

    public UserHelperClass(HashMap map) {
        this.highscoreList = map;
    }
    public Map getHighscore() {
        return highscoreList;
    }

    public void setScore(HashMap map) {
        this.highscoreList = map;
    }

}
