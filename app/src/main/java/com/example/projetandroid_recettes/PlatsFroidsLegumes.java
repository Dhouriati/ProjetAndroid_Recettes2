package com.example.projetandroid_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class PlatsFroidsLegumes extends AppCompatActivity {
    private Chip tomatoes, corn, pomegranate, concombres, avocado, mango, mushrooms;
    private Button continueButton, backButton;
    private int selectedChipCount = 0;
    private List<Chip> legumeChips;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plats_froids_legumes);

        tomatoes = findViewById(R.id.tomatoes);
        corn = findViewById(R.id.corn);
        pomegranate = findViewById(R.id.pomegranate);
        concombres = findViewById(R.id.cucumbers);
        avocado = findViewById(R.id.avocado);
        mango = findViewById(R.id.mango);
        mushrooms = findViewById(R.id.mushrooms);
        legumeChips = new ArrayList<>();

        legumeChips.add(tomatoes);
        tomatoes.setOnCheckedChangeListener(chipCheckedChangeListener);
        legumeChips.add(corn);
        corn.setOnCheckedChangeListener(chipCheckedChangeListener);
        legumeChips.add(pomegranate);
        pomegranate.setOnCheckedChangeListener(chipCheckedChangeListener);

        legumeChips.add(concombres);
        concombres.setOnCheckedChangeListener(chipCheckedChangeListener);

        legumeChips.add(avocado);
        avocado.setOnCheckedChangeListener(chipCheckedChangeListener);

        legumeChips.add(mango);
        mango.setOnCheckedChangeListener(chipCheckedChangeListener);

        legumeChips.add(mushrooms);
        mushrooms.setOnCheckedChangeListener(chipCheckedChangeListener);


    }
    // Earphones for chip status changes
    private CompoundButton.OnCheckedChangeListener chipCheckedChangeListener =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // If the chip is checked, check the number of selected chips
                        if (selectedChipCount < 3) {
                            selectedChipCount++;
                        } else {
                            // If the user tries to check more than three chips, uncheck the last one.
                            buttonView.setChecked(false);
                            Toast.makeText(PlatsFroidsLegumes.this, "You can only select between 1 and 3 items.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // If the chip is unchecked, decrements the number of selected chips.
                        selectedChipCount--;
                    }
                }
            };

    public void Rating (View v) {
        if (selectedChipCount <= 3) {
            StringBuilder selectedLegumes = new StringBuilder();
            // Add each selected chip to the list
            for (Chip chip : legumeChips) {
                if (chip.isChecked()) {
                    selectedLegumes.append(chip.getText()).append(", ");
                }
            }
            if (selectedLegumes.length() > 0) {
                selectedLegumes.delete(selectedLegumes.length() - 2, selectedLegumes.length());
            }
            Intent intent = new Intent(this, AllChoice.class);
            // Add extras to intent
            intent.putExtra("selectedLegumes", selectedLegumes.toString());
            intent.putExtra("selectedProteins", this.getIntent().getStringExtra("selectedProteins"));
            intent.putExtra("selectedBase", this.getIntent().getStringExtra("selectedBase"));
            intent.putExtra("tempChoice", this.getIntent().getStringExtra("tempChoice"));
            intent.putExtra("regimeChoice", this.getIntent().getStringExtra("regimeChoice"));
            startActivity(intent);
        } else
            Toast.makeText(this, "You can only select between 1 and 3 items.", Toast.LENGTH_SHORT).show();
    }

    public void onClick (View v) {
        finish();
    }
}