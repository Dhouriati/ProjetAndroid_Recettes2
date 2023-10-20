package com.example.projetandroid_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
                    Toast.makeText(Inscription.this, "Selected option " + selectedGender, Toast.LENGTH_SHORT).show();
                }
            }
        });
        Log.d("Inscription", "First name " + firstName);
        Log.d("Inscription", "Last name " + lastName);
    }

    public void submitUserInfo(View view) {
        // Age and gender
        String age = editAge.getText().toString();
        int selectedGenderId = radioGpGender.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedGenderId);
        String gender = selectedGender != null ? selectedGender.getText().toString() : "";
        // Log values before insert
        Log.d("Inscription", "FirstName: " + firstName);
        Log.d("Inscription", "LastName: " + lastName);
        Log.d("Inscription", "Age: " + age);
        Log.d("Inscription", "Gender: " + gender);
        // Verify if mandatory information are fulfilled

        if (!age.isEmpty() && !gender.isEmpty()) {
            // Insert information on database
            dbHelper.insertUserInfo(firstName, lastName, age, gender);
            sessionManager.insertUser(firstName, lastName);

            // Log after insert
            Log.d("Inscription", "Profile added to the database");
            //  Redirect user to next activity RegimeDiet
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
