package ntnu.gruppe22.game.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ntnu.gruppe22.game.scenes.Highscore;
import ntnu.gruppe22.game.utils.Subscriber;

public class FirebaseHighScore implements Subscriber {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Highscore");

    private Map<String, String> list = new HashMap<>();
    private String valueString;


    public FirebaseHighScore() {
        basicRead();
    }

    @Override
    public void update(String username, int score) {
        DatabaseReference newHighScoreReference = reference.push();
        writeNewHighScore(newHighScoreReference, username, score);
    }
    

    public void writeNewHighScore(DatabaseReference ref, String username, int score) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(username, score);
        ref.setValue(map);
    }


    public void basicRead() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                Log.d("Read from DB", "value is: " + value);
                getValuesFromMap(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w("Read from DB", "Failed to read value from DB", databaseError.toException());
            }
        });
    }


    public void getValuesFromMap(Map<String,Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();

            valueString = value.toString();
            String newString = valueString.substring(1,valueString.length() - 1);
            String[] parts = newString.split("=");
            list.put(parts[1], parts[0]);

        }
        getTopThree(list);
    }


    public void getTopThree(Map<String, String> map) {
        List<String> sortedList = new ArrayList<>(map.keySet());
        Collections.sort(sortedList);

        int size = sortedList.size() -1;

        String winnerScore = sortedList.get(size);
        String secondScore = sortedList.get(size -1);
        String thirdscore = sortedList.get(size -2);

        String winner = map.get(winnerScore);
        String second = map.get(secondScore);
        String third = map.get(thirdscore);

        Map<String, String> topThree = new LinkedHashMap<>();
        topThree.put(winner, winnerScore);
        topThree.put(second, secondScore);
        topThree.put(third, thirdscore);

        Highscore.highscoreList = topThree;
    }
}
