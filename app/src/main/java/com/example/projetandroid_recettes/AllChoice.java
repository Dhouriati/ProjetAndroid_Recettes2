package com.example.projetandroid_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AllChoice extends AppCompatActivity {
    private TextView regimeChoiceDisplay;
    private TextView tempChoiceDisplay;
    private TextView equipChoiceDisplay;
    private TextView platsChoiceDisplay;
    private TextView legumeChoiceDisplay;
    private TextView lblEquipChoice;
    private TextView lblPlatsChoice;
    private TextView lblLegumeChoice;

    private TextView idProfile;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_choice);
        sessionManager = new SessionManager(this);

        regimeChoiceDisplay = findViewById(R.id.regimeChoiceDisplay);
        tempChoiceDisplay = findViewById(R.id.tempChoiceDisplay);
        equipChoiceDisplay = findViewById(R.id.equipChoiceDisplay);
        lblEquipChoice = findViewById(R.id.lblEquipChoice);
        platsChoiceDisplay = findViewById(R.id.platsChoiceDisplay);
        legumeChoiceDisplay = findViewById(R.id.legumeChoiceDisplay);
        lblPlatsChoice = findViewById(R.id.lblPlatsChoice);
        lblLegumeChoice = findViewById(R.id.lblLegumeChoice);
        idProfile = findViewById(R.id.idProfil);


        idProfile.setText(sessionManager.getFirstName());
        Intent intent = getIntent();
        // Uses takeIntent method to display information
        takeIntent("regimeChoice", regimeChoiceDisplay);
        takeIntent("tempChoice",  tempChoiceDisplay);

        if (intent.hasExtra("equipChoice")) {
            takeIntent("equipChoice", equipChoiceDisplay);
        } else {
            // If "equipChoice" is not present, use "selectedBase" as the key.
            takeIntent("selectedBase", equipChoiceDisplay);
            lblEquipChoice.setText(R.string.lblBaseChoice);
        }

        if (intent.hasExtra("platsChoice")) {
            takeIntent("platsChoice",  platsChoiceDisplay);
        }
        else {
            // If "platsChoice" is not present, use "selectedProteins" as the key.
            takeIntent("selectedProteins", platsChoiceDisplay);
            lblPlatsChoice.setText(R.string.lblProteinChoice);
        }
        // If "selectedLegumes" is not empty, then load information into legumesChoice
        if (intent.hasExtra("selectedLegumes")) {
            takeIntent("selectedLegumes",  legumeChoiceDisplay);
        }
        // Otherwise delete the associated tables
        else {
            lblLegumeChoice.setVisibility(View.GONE);
            legumeChoiceDisplay.setVisibility(View.GONE);
        }

    }


    private void takeIntent(String key, TextView display) {
        Intent intent = getIntent();

        // Checks if the intent contains the extra specified by the key
        if (intent.hasExtra(key)) {
            // Retrieves the value associated with the key
            String value = intent.getStringExtra(key);

            // Displays information in TextView
            display.setText(value);
        }
    }
    public void recipePage (View v) {
        //Recover values of regime and temperature (hot or cold meal)
        String value = getIntent().getStringExtra("regimeChoice");
        String valueTemp = getIntent().getStringExtra("tempChoice");
        Log.d("AllChoice", "My value is : "+valueTemp);

        //if meal is hot, recover value of type of meal before see the recipe
        if (valueTemp.equals("hot_meal")) {
            String valueChoice = getIntent().getStringExtra("platsChoice");

            Intent intent = new Intent(this, Recette.class);
            intent.putExtra("platsChoice", valueChoice);
            intent.putExtra("regimeChoice", value);
            intent.putExtra("tempChoice", valueTemp);

            startActivity(intent);
        }
        //if meal is cold, go to see the recipe
        if (valueTemp.equals("cold_meal")) {
            String valueBase = getIntent().getStringExtra("selectedBase");
            String valueProt = getIntent().getStringExtra("selectedProteins");

            Intent intent = new Intent(this, Recette.class);
            intent.putExtra("regimeChoice", value);
            intent.putExtra("tempChoice", valueTemp);
            intent.putExtra("selectedBase", valueBase);
            intent.putExtra("selectedProteins", valueProt);
            //intent.putExtra("selectedLegumes", selectedLegumes.toString());

            startActivity(intent);
        }

    }

    public void onClick (View v) {
        finish();
    }
}
