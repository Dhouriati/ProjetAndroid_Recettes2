package com.example.projetandroid_recettes;

import static com.example.projetandroid_recettes.DbHelper.TABLE_USER;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Inscription extends AppCompatActivity {
    private RadioGroup radioGpGender;
    private EditText editAge;
    private DbHelper dbHelper;
    private String firstName;
    private String lastName;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        dbHelper = new DbHelper(this);
        sessionManager = new SessionManager(this);
        Intent intent = getIntent();
        firstName = intent.getStringExtra("firstName");
        lastName = intent.getStringExtra("lastName");

        //RadioGroup
        radioGpGender = findViewById(R.id.radioGpGender);
        // Uncheck or reset the radio buttons initially
        radioGpGender.clearCheck();
        editAge = findViewById(R.id.editAge);
        // Add the Listener to the RadioGroup
        radioGpGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button has been clicked
                RadioButton radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    String selectedGender = radioButton.getText().toString();
                    Toast.makeText(Inscription.this, "Option sélectionné " + selectedGender, Toast.LENGTH_SHORT).show();
                }
            }
        });
        Log.d("Inscription", "Prenom " + firstName);
        Log.d("Inscription", "Nom " + lastName);
    }

    public void submitUserInfo(View view) {
        // Age et sexe
        String age = editAge.getText().toString();
        int selectedGenderId = radioGpGender.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedGenderId);
        String gender = selectedGender != null ? selectedGender.getText().toString() : "";
        // Log des valeurs avant l'insertion
        Log.d("Inscription", "FirstName: " + firstName);
        Log.d("Inscription", "LastName: " + lastName);
        Log.d("Inscription", "Age: " + age);
        Log.d("Inscription", "Gender: " + gender);
        // Vérifiez si les informations obligatoires sont remplies

        if (!age.isEmpty() && !gender.isEmpty()) {
            // Insertiion des informations dans la base de données
            dbHelper.insertUserInfo(firstName, lastName, age, gender);
            sessionManager.insertUser(firstName, lastName);

            // Log après l'insertion
            Log.d("Inscription", "Profil ajouté à la base de données");
            // Redirigez l'utilisateur vers l'activité suivante (par exemple, la page de régime)
            Intent intent = new Intent(this, RegimeDiet.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.fill_information, Toast.LENGTH_SHORT).show();
        }
    }


    public void onClick(View v) {
        finish();
    }


}
