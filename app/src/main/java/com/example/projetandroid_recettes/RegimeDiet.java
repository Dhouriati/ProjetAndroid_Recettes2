package com.example.projetandroid_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

public class RegimeDiet extends AppCompatActivity {
    private TextView printRegime;
    private Switch switchRegime;
    private TextView questionRegime;
    private TextView repVegetarian;
    private TextView repVegan;
    private CheckBox anyRegime;
    private Button buttonContinue;
    private Button buttonExit;
    private String regimeChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regime_diet);

        printRegime = findViewById(R.id.printRegime);
        switchRegime = findViewById(R.id.switchRegime);
        questionRegime = findViewById(R.id.questionRegime);
        repVegetarian = findViewById(R.id.repVegetarian);
        repVegan = findViewById(R.id.repVegan);
        anyRegime = findViewById(R.id.anyRegime);
        buttonContinue = findViewById(R.id.buttonContinue);
        buttonExit = findViewById(R.id.buttonExit);

    }
    public void ChoiceHot (View v) {
        // Recover user's choices
        boolean isSwitchOn = switchRegime.isChecked();
        boolean isCheckBoxChecked = anyRegime.isChecked();

        // If there is a checkbox, there is not regime
        // Determine regime according to user's choice
        if (isCheckBoxChecked ) {
            regimeChoice = "No Restriction";
        } else if (isSwitchOn) {

            regimeChoice = "Vegan";
        } else {
            regimeChoice = "Vegetarian";
        }

        Intent intent = new Intent(this, Chaud_Froid.class);
        intent.putExtra("regimeChoice", regimeChoice);
        startActivity(intent);
    }
    public void onClick (View v) {
        finish();
    }
}