package com.example.projetandroid_recettes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class PlatsChaudsChoix extends AppCompatActivity {
    private CheckBox saladCheck;
    private CheckBox dessertCheck;
    private CheckBox mainCourseCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plat_chaud_choix);

        // Declare CheckBox
        mainCourseCheck = findViewById(R.id.MainCourseCheck);
        saladCheck = findViewById(R.id.SaladCheck);
        dessertCheck = findViewById(R.id.DessertCheck);

        saladCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAllChoice("Appetizer");
            }
        });
        dessertCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAllChoice("Dessert");
            }
        });
        mainCourseCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAllChoice("MainCourse");
            }
        });
    }
    //
    public void nextAllChoice(String value_choice) {
        Log.d("CheckedBox", "Type of recipe clicked");
        // Handle logic for type of recipe click
        Intent intent = new Intent(PlatsChaudsChoix.this, AllChoice.class);
        intent.putExtra("platsChoice", value_choice);
        intent.putExtra("equipChoice", this.getIntent().getStringExtra("equipChoice"));
        intent.putExtra("tempChoice", this.getIntent().getStringExtra("tempChoice"));
        intent.putExtra("regimeChoice", this.getIntent().getStringExtra("regimeChoice"));
        startActivity(intent);
    }

    public void onClick (View v) {
        finish();
    }
}