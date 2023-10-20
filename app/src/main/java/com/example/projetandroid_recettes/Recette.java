package com.example.projetandroid_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class Recette extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private TextView textViewNomRecette;
    private TextView textViewIngredients;
    private TextView textViewEtapes;
    private TextView textViewRating;
    private TextView textViewAllComment;
    private ArrayList<DataRecette> afficheTest = DataRecette.InfosRecette();
    private DataRecette larecette;
    //private List<String> printComment = larecette.getComment();
    private String[] permissionsWriteRead = {Manifest.permission.READ_MEDIA_IMAGES,Manifest.permission.READ_MEDIA_VIDEO,Manifest.permission.READ_MEDIA_AUDIO};
    private String[] oldPermissionsWriteRead = {Manifest.permission.READ_EXTERNAL_STORAGE,  Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette);

        textViewNomRecette = findViewById(R.id.textViewNomRecette);
        textViewIngredients = findViewById(R.id.textViewIngredients);
        textViewEtapes = findViewById(R.id.textViewEtapes);
        textViewRating=findViewById(R.id.textViewRating);
        textViewAllComment=findViewById(R.id.textViewAllComment);

        // Checks if permission is granted
        demanderPermission();
    }
    public void ratingPage (View v) {
        //Go to the rating page
        Intent intent = new Intent(this, activity_rating.class);
        intent.putExtra("recetteName", larecette.getNomRecipe());
        startActivity(intent);
    }


    private void displayRecipe() {
        // Display information about the recipe
        textViewNomRecette.setText(larecette.getNomRecipe());

        // Display information about the ingredient
        String ingr;
        ingr = "First ingredient : "+larecette.getNomIngredient1() + "\n";
        ingr = ingr + "Second ingredient : "+larecette.getNomIngredient2() + "\n";
        ingr = ingr + "Third ingredient : "+larecette.getNomIngredient3() + "\n";
        ingr = ingr + "Last ingredient : "+larecette.getNomIngredient4();
        textViewIngredients.setText(ingr);

        // Display information about the step
        String step;
        step = "First step : "+larecette.getStep1() + "\n";
        step = step + "Second step : "+larecette.getStep2() + "\n";
        step = step + "Third step : "+larecette.getStep3() + "\n";
        step = step + "Last step : "+larecette.getStep4();
        textViewEtapes.setText(step);
        textViewRating.setText("Note : "+larecette.getRating()+"/"+(larecette.getVoters()*5));

        Log.d("Recipe",larecette.getVoters()+"");
        /*
        //Display list of all comment
        String tempo = "";
        for (String string : printComment) {
            tempo = tempo + "\n" + string;
        }
        textViewAllComment.setText(larecette.getComment().toString());
        */
    }

    private void choiceRecipe() {
        String value = getIntent().getStringExtra("regimeChoice");
        String valueTemp = getIntent().getStringExtra("tempChoice");

        // Launch the process
        RecetteIO.init(this);

        Log.d("Recipe", "My recipe type value is : " + valueTemp);
        ArrayList<DataRecette> filteredRecipes = new ArrayList<>();

        if (valueTemp.equals("hot_meal")) {
            String valueChoice = getIntent().getStringExtra("platsChoice");
            Log.d("Recipe", "Passage through the hot meal loop");
            Log.d("Recipe", "My choice value is " + valueChoice);

            for (DataRecette recette : afficheTest) {
                Log.d("Recipe", "Type is" + recette.getType());
                if (recette.getType().equals(valueChoice)) {
                    filteredRecipes.add(recette);
                }
            }
            //if the table is not empty (means that there is min recipe which match)
            if (!filteredRecipes.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(filteredRecipes.size()); //Generates a random number to determine which recipe to display
                larecette = filteredRecipes.get(randomIndex);
                Log.d("Recipe", "The recipe is " + larecette.getNomRecipe());
            } else {
                Log.d("Recipe", "No recipe corresponds to the choice of dish");
                larecette=afficheTest.get(0); //default value if error
            }
            displayRecipe();//display recipe selected
        } else if (valueTemp.equals("cold_meal")) {
            // Values for the base and protein
            String valueBase = getIntent().getStringExtra("selectedBase");
            String valueProt = getIntent().getStringExtra("selectedProteins");

            Log.d("Recipe", "Passage through the cold meal loop");

            for (DataRecette recette : afficheTest) {
                if (recette.getType().equals(value)) {
                    filteredRecipes.add(recette);
                }
            }
            if (!filteredRecipes.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(filteredRecipes.size());
                larecette = filteredRecipes.get(randomIndex);
                Log.d("Recipe", "The recipe is " + larecette.getNomRecipe());
            } else {
                Log.d("Recipe", "No recipe corresponds to the choice of dish");
            }
            displayRecipe();
        } else {
            Log.d("Recipe", "Passage through the error loop");
            larecette = afficheTest.get(0);//default value if error
        }
    }
    private void demanderPermission() {
        // check if we have permission to continue
        if(EasyPermissions.hasPermissions(this,  Build.VERSION.SDK_INT >=33 ? permissionsWriteRead : oldPermissionsWriteRead)){
            // if permissions are already accepted
            // Launch the process
            choiceRecipe();

        }else{
            // if not, ask for access permissions
            EasyPermissions.requestPermissions(
                    this,
                    "Accept permissions to continue",
                    100,
                    Build.VERSION.SDK_INT >=33 ? permissionsWriteRead : oldPermissionsWriteRead
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Pass request to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if( requestCode ==100 && perms.size() ==2) {
            // if permissions are granted
            choiceRecipe();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        // if all permissions are denied
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            // Adjust suggestions in settings
            new AppSettingsDialog.Builder(this).build().show();
        }else{
            // else display an error message
            Toast.makeText(getApplicationContext(), "You have not accepted the permissions. Accept them to continue", Toast.LENGTH_SHORT).show();
        }
    }
}