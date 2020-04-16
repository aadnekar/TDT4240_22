package ntnu.gruppe22.game.firebase;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHighscore extends AppCompatActivity {

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


    FirebaseDatabase rootNode;
    DatabaseReference reference;

    // Read from the database
    @Override
    protected void onCreate(Bundle savedInstance) {
    super.onCreate(savedInstance);
        rootNode = FirebaseDatabase.getInstance();
        System.out.println(rootNode);
    }


}
