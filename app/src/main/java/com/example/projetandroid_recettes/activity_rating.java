package com.example.projetandroid_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class activity_rating extends AppCompatActivity {
    private RatingBar ratingBar;
    private EditText Comment;
    private Button btnSend;
    private ArrayList<DataRecette> afficheTest;
    private DataRecette larecette;
    private float tempRating;
    private final ArrayList<Float> ratingsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ratingBar = findViewById(R.id.ratingBar);
        btnSend = findViewById(R.id.btnSend);
        Comment = findViewById(R.id.Comment);

        btnSend.setOnClickListener(v -> saveFeedback());
        afficheTest=RecetteIO.loadRecipesFromJson(this);
        String value = getIntent().getStringExtra("recetteName");
        for (DataRecette recette :
                afficheTest) {
            if (recette.getNomRecipe().equals(value)) {
                larecette=recette;
                break;
            }}
        //if the recipe is null, the first one is taken
        larecette= larecette==null ? afficheTest.get(0) : larecette;
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tempRating = rating;
                Log.d("Rating", "temporary rating "+tempRating);
            }
        });
    }

    public float getRatingBar() {
        return ratingBar.getRating();
    }

    public String getUserComment() {
        return Comment.getText().toString();
    }
    private void saveFeedback() {
        // User rating display
        ratingsList.add(getRatingBar());
        updateRating();
        Toast.makeText(this, "Rating: " + getRatingBar() + "\nComment: " + getUserComment(), Toast.LENGTH_SHORT).show();
    }


    //To calculate the average, we'll take the user's rating + add the old rating of the other users.
    private float calculateAverageRating(float newRating) {
        float totalR = newRating;

        for (float rating : ratingsList) {
            totalR += rating;
        }

        return totalR / (ratingsList.size() + 1);
    }
    private void updateRating() {
        Log.d("RecetteIO", "rating "+tempRating);
        this.larecette.addRating(tempRating);
        // met à jour les données
        RecetteIO.updateRecipeByName(this, this.larecette.getNomRecipe(), this.larecette);
    }

}

