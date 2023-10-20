package com.example.projetandroid_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

                //if the user exist
                if (checkUserExistence(firstName, lastName)) {
                    // The user exist, redirect to RegimeDiet activity
                    Intent myIntent = new Intent(MainActivity.this, RegimeDiet.class);
                    startActivity(myIntent);
                    Log.d("MainActivity", "User exist");

                    //Insert information on SessionManager
                    sessionManager.insertUser(firstName, lastName);
                } else {
                    // If the user doesn't exist, redirect to registration activity
                    Intent intent = new Intent(MainActivity.this, Inscription.class);
                    intent.putExtra("firstName", firstName);
                    intent.putExtra("lastName", lastName);
                    Log.i("MainActivity", "User doesn't exist");
                    startActivity(intent);

                }
            }
        });
    }

    private boolean checkUserExistence(String firstName, String lastName) {
        // Verify on the BDD
        // Boolean true if the user is present
        return dbHelper.checkUserExistence(firstName, lastName);
    }

}
