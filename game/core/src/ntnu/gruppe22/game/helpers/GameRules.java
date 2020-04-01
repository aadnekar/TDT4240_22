package ntnu.gruppe22.game.helpers;

import java.util.HashMap;
import java.util.Map;

public class GameRules {

    /**
     * @author hildegun 30.03.20
     */

    public static final int BUFFER_TIME = 5;
    public static final int ROUND_TIME = 30;
    public static final int NUMBER_OF_CHARS = 1;
    public static final int MAX_NUMBER_OF_ROUNDS = 20;


     // Map med alle animals (sprites) man kan velge mellom og deres tilhørende id
    private static final Map<Integer,String> animalMap = new HashMap<Integer, String>() {{
        put(1,  "animals/chicken.png");
        put(2,  "animals/monkey.png");
        put(3,  "animals/walrus.png");
        put(4,  "animals/moose.png");
        put(5,  "animals/rabbit.png");
    }};

}