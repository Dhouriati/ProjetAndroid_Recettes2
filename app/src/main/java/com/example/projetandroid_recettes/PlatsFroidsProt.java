package com.example.projetandroid_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PlatsFroidsProt extends AppCompatActivity {
    private RadioGroup proteinRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plats_froids_prot);

        proteinRadioGroup = findViewById(R.id.proteinRadioGroup);
        String regimeChoice = getIntent().getStringExtra("regimeChoice");
        Log.d("PlatsFroidsProt", "Regime Choice: " + regimeChoice);


        // Désactive certaines options en fonction du choix de régime
        if ("vegan".equals(regimeChoice)) {
            disableRadioButton(R.id.salmon);
            disableRadioButton(R.id.tuna);
            disableRadioButton(R.id.beef);
            disableRadioButton(R.id.marinated_chicken);
        } else if ("vegetarien".equals(regimeChoice)) {
            disableRadioButton(R.id.beef);
            disableRadioButton(R.id.marinated_chicken);
        }

    }
    public void ChoiceLeg (View v) {
        int selectedRadioButtonId = proteinRadioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            Log.d("PlatsFroidsProt", "RadioGroup Proteins");
            if (selectedRadioButton != null) {

                String selectedProteins = selectedRadioButton.getText().toString();
                Intent intent = new Intent(this, PlatsFroidsLegumes.class);
                intent.putExtra("selectedProteins", selectedProteins.toString());
                intent.putExtra("selectedBase", this.getIntent().getStringExtra("selectedBase"));
                intent.putExtra("tempChoice", this.getIntent().getStringExtra("tempChoice"));
                intent.putExtra("regimeChoice", this.getIntent().getStringExtra("regimeChoice"));
                startActivity(intent);
            }
        } else {

            Toast.makeText(this, "Veuillez sélectionner une option", Toast.LENGTH_SHORT).show();
        }
    }
    private void disableRadioButton(int radioButtonId) {
        RadioButton radioButton = findViewById(radioButtonId);
        radioButton.setEnabled(false);
    }

    public void onClick (View v) {
        finish();
    }
}