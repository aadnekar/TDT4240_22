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

import ntnu.gruppe22.game.scenes.GameOver;

public class FirebaseHighscore {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Highscore");

    public FirebaseHighscore() {

        basicReadWrite();
        DatabaseReference newHighscoreReference = reference.push();

    }


    public void getWinnerData() {
        String username = GameOver.getWinner();
        int score = GameOver.getScore();
    }

    public void writeNewHighscore(DatabaseReference ref, String username, int score) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(username, score);
        ref.setValue(map);
    }



    public void basicReadWrite() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                Log.d("LOGGING MAH ACTIVITAH", "value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w("LOGGING MAH ACTIVITAH", "Failed to read value from DB", databaseError.toException());
            }
        });
    }


 //   FirebaseDatabase rootNode;
 //   DatabaseReference reference;
 //   Highscore h;
 //   GameOver g;




/*    public FirebaseHighscore() {

        //if(h.isGameOver()) {
            //(write) Vinner legges til i databasen med highscorelista.
            //(read) Highscorelista, med den nye scoren, printes ut på highscore scenen.
        //}
    }


    public static void main(String args[]) {
        FirebaseHighscore f = new FirebaseHighscore();
    }*/



/*

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog/posts");



    // Read from the database
    @Override
    protected void onCreate(Bundle savedInstance) {
    super.onCreate(savedInstance);
        database = FirebaseDatabase.getInstance();
        System.out.println(database);
    }



    // Get a reference to our posts


    DatabaseReference usersRef = ref.child("Highscore");
*/






}
