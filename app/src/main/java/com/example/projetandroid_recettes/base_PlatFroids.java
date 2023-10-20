package com.example.projetandroid_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class base_PlatFroids extends AppCompatActivity {
    private Spinner choiceBase;
    private Button buttonC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_plat_froids);

        choiceBase=findViewById(R.id.choiceBase);
        buttonC=findViewById(R.id.buttonC);
        String[] data = getResources().getStringArray(R.array.basePlats);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);


        // Specifies the scrolling layout used when the Spinner is opened
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply adapter to Spinner
        choiceBase.setAdapter(adapter);
    }
    public void ChoiceProt (View v) {
        String selectedBase = choiceBase.getSelectedItem().toString();
        Intent intent = new Intent(this, PlatsFroidsProt.class);
        intent.putExtra("selectedBase", selectedBase);
        intent.putExtra("tempChoice", this.getIntent().getStringExtra("tempChoice"));
        intent.putExtra("regimeChoice", this.getIntent().getStringExtra("regimeChoice"));
        startActivity(intent);
    }

    public void onClick (View v) {
        finish();
    }
}