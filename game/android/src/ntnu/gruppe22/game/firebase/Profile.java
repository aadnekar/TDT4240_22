package ntnu.gruppe22.game.firebase;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import ntnu.gruppe22.game.AndroidLauncher;
import ntnu.gruppe22.game.R;


public class Profile extends AppCompatActivity {


    TextView name, mail;
    Button logout, continueToGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        logout = findViewById(R.id.logout);
        continueToGame = findViewById(R.id.continueToGame);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);


        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            name.setText(signInAccount.getDisplayName());
            mail.setText(signInAccount.getEmail());
        }


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        continueToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AndroidLauncher.class);
                startActivity(intent);
                //Start Android Launcher som skal starte resten av spillet.
                //FirebaseAuth.getInstance().signOut();
                //Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                //startActivity(intent);
            }
        });


    }
}