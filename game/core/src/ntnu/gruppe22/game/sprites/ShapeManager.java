package ntnu.gruppe22.game.sprites;

/**
 * @author aadne on 08.04.2020 14:32
 */

public class ShapeManager {

    private static final String DIR_PATH = "sprite-shapes/";

    private String name;
    private String filePath;

    public ShapeManager(int animalKey) {

        switch (animalKey) {
            case 1:
                chicken();
                break;
            case 2:
                monkey();
                break;
            case 3:
                walrus();
                break;
            case 4:
                moose();
                break;
            default:
                rabbit();
                break;

        }
    }

    private void chicken() {
        this.name = "chicken";
        this.filePath = DIR_PATH + "chicken.json";
    }

    private void monkey() {
        this.name = "monkey";
        this.filePath = DIR_PATH + "monkey.json";
    }

    private void walrus() {
        this.name = "walrus";
        this.filePath = DIR_PATH + "walrus.json";
    }

    private void moose() {
        this.name = "moose";
        this.filePath = DIR_PATH + "moose.json";
    }

    private void rabbit() {
        this.name = "rabbit";
        this.filePath = DIR_PATH + "rabbit.json";
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }
}
