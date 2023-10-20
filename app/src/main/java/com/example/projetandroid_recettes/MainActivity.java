package com.example.projetandroid_recettes;

import static com.example.projetandroid_recettes.DbHelper.TABLE_USER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText SurnameUser;
    private EditText FirstNameUser;
    private Button btnConnexion;
    private SessionManager sessionManager;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        dbHelper = new DbHelper(this);
        FirstNameUser = findViewById(R.id.FirstnameUser);
        SurnameUser = findViewById(R.id.SurnameUser);
        btnConnexion = findViewById(R.id.btnConnexion);

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = FirstNameUser.getText().toString();
                String lastName = SurnameUser.getText().toString();

                Log.d("MainActivity", "Button clicked with firstName: " + firstName + " and lastName: " + lastName);

                //Utilisateur existe
                if (checkUserExistence(firstName, lastName)) {
                    // L'utilisateur existe, redirige vers l'activité RegimeDiet
                    Intent myIntent = new Intent(MainActivity.this, RegimeDiet.class);
                    startActivity(myIntent);
                    Log.d("MainActivity", "L'utilisateur existe");

                    //Insérer les information dans la sessionManager
                    sessionManager.insertUser(firstName, lastName);
                } else {
                    // L'utilisateur n'existe pas, redirige vers l'activité d'inscription
                    Intent intent = new Intent(MainActivity.this, Inscription.class);
                    //Intent intent = new Intent(MainActivity.this, RegimeDiet.class);
                    intent.putExtra("firstName", firstName);
                    intent.putExtra("lastName", lastName);
                    Log.i("MainActivity", "utilisateur n'existe pas");
                    startActivity(intent);

                }
            }
        });
    }

    /*public void connection(View v) {
        Intent intent = new Intent(this, Inscription.class);
        startActivity(intent);
    }*/

    private boolean checkUserExistence(String firstName, String lastName) {
        // Vérification dans la bdd
        // Boolean true si utilisateur présent
        return dbHelper.checkUserExistence(firstName, lastName);
    }

}
