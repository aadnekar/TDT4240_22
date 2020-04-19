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

    private Map<Integer, String> list = new HashMap<>();
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

            int score = Integer.parseInt(parts[1]);
            list.put(score, parts[0]);

        }
        getTopThree(list);
    }


    public void getTopThree(Map<Integer, String> map) {
        List<Integer> sortedList = new ArrayList<>(map.keySet());
        Collections.sort(sortedList);

        int size = sortedList.size() -1;

        int winnerScore = sortedList.get(size);
        int secondScore = sortedList.get(size -1);
        int thirdscore = sortedList.get(size -2);

        String winner = map.get(winnerScore);
        String second = map.get(secondScore);
        String third = map.get(thirdscore);

        Map<String, String> topThree = new LinkedHashMap<>();
        topThree.put(winner, Integer.toString(winnerScore));
        topThree.put(second, Integer.toString(secondScore));
        topThree.put(third, Integer.toString(thirdscore));

        Highscore.highscoreList = topThree;
    }
}
