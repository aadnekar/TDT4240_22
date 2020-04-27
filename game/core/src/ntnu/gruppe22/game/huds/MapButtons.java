package ntnu.gruppe22.game.huds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import ntnu.gruppe22.game.AnimalWar;
import ntnu.gruppe22.game.helpers.GameInfo;
import ntnu.gruppe22.game.helpers.GameManager;
import ntnu.gruppe22.game.scenes.MainMenu;

public class MapButtons extends Buttons{

    private ImageButton platformMap;
    private ImageButton winterMap;
    private ImageButton backButton;
    private float RectangleX;
    private float RectangleY;
    private float PositionX;
    private float PositionY;

    public MapButtons(AnimalWar game) {
        this.initializeButtons(game);

        stage.addActor(platformMap);
        stage.addActor(winterMap);
        stage.addActor(backButton);
    }

    /**
     * Initializes the image buttons of the maps and sets their position
     * also gets the proportions of the map-images into the varables RectangleX and RectangleY
     */
    protected void createAndPositionButtons(){
        //creates the image buttons
        platformMap = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("map/platform2Map.png"))
        ));
        winterMap = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("map/winterMap.png"))
        ));
        backButton = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("settings/home.png"))
        ));

        //positions the map image buttons and home button
        platformMap.setPosition(GameInfo.WIDTH/2 - platformMap.getWidth()/2, platformMap.getHeight()+ 100);
        winterMap.setPosition(GameInfo.WIDTH/2 - winterMap.getWidth()/2, 50);
        backButton.setPosition(50, GameInfo.HEIGHT - 90);

        //Add rectangle arround chosen map,
        PositionX = mapIdToImageButton(GameManager.getInstance().gameData.getChosenMap()).getX();
        PositionY = mapIdToImageButton(GameManager.getInstance().gameData.getChosenMap()).getY();
        RectangleX = mapIdToImageButton(GameManager.getInstance().gameData.getChosenMap()).getWidth();
        RectangleY = mapIdToImageButton(GameManager.getInstance().gameData.getChosenMap()).getHeight();
    }

    /**
     * Adds eventlisteners to the buttons.
     * When the maps is touched, the chosenMap-variable is changed to the id of the touched map,
     * also the position of the rectangle that is beeing drawed around the chosen map.
     */
    protected void addButtonListeners(){
        platformMap.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setChosenMap(1);
                changeChosenMap(1);
            }
        });

        winterMap.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setChosenMap(2);
                changeChosenMap(2);
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameStartedFromMainMenu = true;

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new MainMenu(game));
                    }
                });

                SequenceAction sa = new SequenceAction();
                sa.addAction(Actions.fadeOut(1f));
                sa.addAction(run);

                stage.addAction(sa);
            }
        });

    }

    //function for handling chosen map
    private void setChosenMap(int id){GameManager.getInstance().gameData.setChosenMap(id);}

    //set the position to the yellow rectangle
    private void changeChosenMap(int id ){
            PositionX = mapIdToImageButton(id).getX();
            PositionY = mapIdToImageButton(id).getY();
    }

    //get the imagebutton to the chosen map
    private ImageButton mapIdToImageButton(int id){
        if (id == 1){
            return platformMap;
        }
        else
            return winterMap;
    }


    //functions for getting positions and proportions of the map images
    public float getRectangleX(){return this.RectangleX;}
    public float getRectangleY(){return this.RectangleY;}
    public float getPositionX(){return this.PositionX;}
    public float getPositionY(){return this.PositionY;}

}
