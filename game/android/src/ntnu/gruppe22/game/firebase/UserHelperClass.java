package ntnu.gruppe22.game.firebase;

public class UserHelperClass {

    String name;
    int score;
    //public Map<String, Integer> highscoreList = new HashMap<>();


    public UserHelperClass() {

    }

    public UserHelperClass(String name, int score) {
        this.name = name;
        this.score = score;
    }

   /* public UserHelperClass(HashMap map) {
        this.highscoreList = map;
    }

    public Map getHighscore() {
        return highscoreList;
    }

    public void setScore(HashMap map) {
        this.highscoreList = map;
    }*/
}
