package com.example.projetandroid_recettes;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PlatsChaudsEqui extends AppCompatActivity {
    private ImageButton ImageBtnOnde;
    private ImageButton ImageBtnStove;
    private ImageButton imageBtnOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plat_chaud_equipements);

        ImageBtnOnde = findViewById(R.id.ImageBtnOnde);
        ImageBtnStove = findViewById(R.id.ImageBtnStove);
        imageBtnOver = findViewById(R.id.imageBtnOver);

        imageBtnOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPlatChaud("Over");
            }
        });
        ImageBtnStove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPlatChaud("Stove");
            }
        });
        ImageBtnOnde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPlatChaud("Microwave");
            }
        });


    }

    public void nextPlatChaud(String value_cold) {
        Log.d("Chaud_Froid", "imageBtnCold clicked");
        // Handle logic for imageBtnCold click
        Intent intent = new Intent(PlatsChaudsEqui.this, PlatsChaudsChoix.class);
        intent.putExtra("equipChoice", value_cold);
        intent.putExtra("tempChoice", this.getIntent().getStringExtra("tempChoice"));
        intent.putExtra("regimeChoice", this.getIntent().getStringExtra("regimeChoice"));
        startActivity(intent);
    }

    public void onClick (View v) {
        finish();
    }

}
