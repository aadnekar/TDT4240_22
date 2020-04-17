package ntnu.gruppe22.game.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import ntnu.gruppe22.game.huds.GameOverButtons;
import ntnu.gruppe22.game.scenes.GameOver;
import ntnu.gruppe22.game.scenes.Highscore;

public class FirebaseHighscore {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Highscore");


    private Map<String, String> list = new HashMap<>();
    private String valueString;

    public FirebaseHighscore() {


        basicReadWrite();
        //readAll();
        DatabaseReference newHighscoreReference = reference.push();

        //Add new data manually
        //writeNewHighscore(newHighscoreReference, "Test2", 10);

        //writeNewHighscore(newHighscoreReference, getWinnerName(), getWinnerScore());

    }

    public String getWinnerName() {
        return GameOver.getLastWinner();
    }

    public int getWinnerScore() {
        return GameOver.getLastScore();
    }

    public boolean isGameOver() {
        if(GameOverButtons.isGameOver) {
            return true;
        }
        return false;
    }

    public void writeNewHighscore(DatabaseReference ref, String username, int score) {
        //if (isGameOver()) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(username, score);
        ref.setValue(map);
      //  }
    }


    public void basicReadWrite() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                //Send Objektet herifra til Highscore klassen.
                Log.d("LOGGING MAH ACTIVITAH", "value is: " + value);
                //System.out.println("Value is" + value);
                //list.putAll(value);
                getValuesFromMap(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w("LOGGING MAH ACTIVITAH", "Failed to read value from DB", databaseError.toException());
            }
        });
    }


    public void getValuesFromMap(Map<String,Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            //String key = entry.getKey();
            Object value = entry.getValue();

            valueString = value.toString();

            String newString = valueString.substring(1,valueString.length() - 1);
            //System.out.println(newString);


            String[] parts = newString.split("=");
            System.out.println("Names and scores: " + parts[0] + "\n" +  parts[1]);

            list.put(parts[0], parts[1]);


            //Her settes highscores alle navn og scores.
            Highscore.highscores += valueString;
            //String[] parts = valueString.split("=");

        }
        System.out.println("This is the list: " + list);
        Highscore.highscoreList = list;


    }


    /*

    public void readAll() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                //value.putAll(value2);
                Log.d("LOGGING MAH ACTIVITAH", "value is: " + value);
                System.out.println("Value is" + value);
                list = value;
                //Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //Log.i("Info","Data changed"+Long.toString( dataSnapshot.getChildrenCount()));
                for (DataSnapshot dis : dataSnapshot.getChildren()) {
                    for (DataSnapshot vers : dis.getChildren()) {
                        //String value = vers.getValue(String.class);
                        Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                        //value.putAll(value2);
                        Log.d("LOGGING MAH ACTIVITAH", "value is: " + value);
                        System.out.println("Value is" + value);
                        list = value;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w("LOGGING MAH ACTIVITAH", "Failed to read value from DB", databaseError.toException());
            }
        });
    }
*/


    /*

    //Observer-pattern

    public void observe(Observable o) {
        o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        boolean gameOver = ((GameOverButtons) o).getGameOver();
        System.out.println("Is the game over?? " + gameOver);
    }
    */

}
