package com.example.projetandroid_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Chaud_Froid extends AppCompatActivity {
    private ImageButton imageBtnCold;
    private ImageButton imageBtnHot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chaud_ou_froid);

        imageBtnHot = findViewById(R.id.imageBtnHot);
        imageBtnCold= findViewById(R.id. imageBtnCold);

        Log.d("Chaud_Froid", "onCreate executed");

        imageBtnCold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivityFroid("You want cold meal");
            }
        });
        imageBtnHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity("You want hot meal");
            }
        });
    }
    //Intent pour passer à aux équipements
    public void nextActivity(String value_cold) {
        Log.d("Chaud_Froid", "imageBtnCold clicked");
        // Handle logic for imageBtnCold click
        Intent intent = new Intent(Chaud_Froid.this, PlatsChaudsEqui.class);
        intent.putExtra("tempChoice", value_cold);
        intent.putExtra("regimeChoice", this.getIntent().getStringExtra("regimeChoice"));
        startActivity(intent);
    }

    public void nextActivityFroid(String value_cold) {
        Log.d("Chaud_Froid", "imageBtnCold clicked");
        // Handle logic for imageBtnCold click
        Intent intent = new Intent(Chaud_Froid.this, base_PlatFroids.class);
        intent.putExtra("tempChoice", value_cold);
        intent.putExtra("regimeChoice", this.getIntent().getStringExtra("regimeChoice"));
        startActivity(intent);
    }



    public void onClick (View v) {
        finish();
    }
}
