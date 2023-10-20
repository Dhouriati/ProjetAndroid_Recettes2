package com.example.projetandroid_recettes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
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

    private TextView idProfil;
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
        idProfil = findViewById(R.id.idProfil);


        idProfil.setText(sessionManager.getFirstName());
        Intent intent = getIntent();
        // Utilise la méthode takeIntent pour afficher les informations
        takeIntent("regimeChoice", regimeChoiceDisplay);
        takeIntent("tempChoice",  tempChoiceDisplay);

        if (intent.hasExtra("equipChoice")) {
            takeIntent("equipChoice", equipChoiceDisplay);
        } else {
            // Si "equipChoice" n'est pas présent, utilisez "selectedBase" comme clé
            takeIntent("selectedBase", equipChoiceDisplay);
            lblEquipChoice.setText(R.string.lblBaseChoice);
        }

        if (intent.hasExtra("platsChoice")) {
            takeIntent("platsChoice",  platsChoiceDisplay);
        }
        else {
            // Si "platsChoice" n'est pas présent, utilisez "selectedProteins" comme clé
            takeIntent("selectedProteins", platsChoiceDisplay);
            lblPlatsChoice.setText(R.string.lblProteinChoice);
        }
        // Si "selectedLegumes" n'est pas vide alors charge les informations dans legumesChoice
        if (intent.hasExtra("selectedLegumes")) {
            takeIntent("selectedLegumes",  legumeChoiceDisplay);
        }
        // Sinon supprime les lables associé
        else {
            lblLegumeChoice.setVisibility(View.GONE);
            legumeChoiceDisplay.setVisibility(View.GONE);
        }

    }


    private void takeIntent(String key, TextView display) {
        Intent intent = getIntent();

        // Vérifie si l'intent contient l'extra spécifié par la clé
        if (intent.hasExtra(key)) {
            // Récupére la valeur associée à la clé
            String value = intent.getStringExtra(key);

            // Affiche l'information dans le TextView
            display.setText(value);
        }
    }
    public void recipePage (View v) {

        String value = getIntent().getStringExtra("regimeChoice");
        Intent intent = new Intent(this, Recette.class);
        intent.putExtra("regimeChoice", value);
        startActivity(intent);
    }
}
