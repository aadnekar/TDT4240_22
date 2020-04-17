package ntnu.gruppe22.game.helpers;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class GameRules {

    /**
     * @author hildegun 30.03.20
     */

    public static final int BUFFER_TIME = 2;
    public static final int ROUND_TIME = 10;
    public static final int NUMBER_OF_CHARS = 1;
    public static final int MAX_NUMBER_OF_ROUNDS = 20;

    public enum Animal {
        CHICKEN,
        MONKEY,
        MOOSE,
        RABBIT,
        WALRUS
    }

    public static final Map<Animal, Integer> animalInstanceToId = new EnumMap<Animal, Integer>(Animal.class) {{
       put(Animal.CHICKEN, 1);
       put(Animal.MONKEY, 2);
       put(Animal.MOOSE, 3);
       put(Animal.RABBIT, 4);
       put(Animal.WALRUS, 5);
    }};

    private static final Map<Integer,String> animalIdToTexture = new HashMap<Integer, String>() {{
        put(1,  "animals/chicken.png");
        put(2,  "animals/monkey.png");
        put(3,  "animals/moose.png");
        put(4,  "animals/rabbit.png");
        put(5,  "animals/walrus.png");
    }};

    public static String getAnimalTexture(int animalKey) {
        return animalIdToTexture.get(animalKey);
    }


}
