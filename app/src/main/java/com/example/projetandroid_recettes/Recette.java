package com.example.projetandroid_recettes;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
        // Vérifie si la permission est accordée

        textViewNomRecette = findViewById(R.id.textViewNomRecette);
        textViewIngredients = findViewById(R.id.textViewIngredients);
        textViewEtapes = findViewById(R.id.textViewEtapes);
        textViewRating=findViewById(R.id.textViewRating);
        textViewAllComment=findViewById(R.id.textViewAllComment);

        //Button btnNext = findViewById(R.id.btnNext);
        demanderPermission();
    }
    public void ratingPage (View v) {

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

        String step;
        step = "First step : "+larecette.getStep1() + "\n";
        step = step + "Second step : "+larecette.getStep2() + "\n";
        step = step + "Third step : "+larecette.getStep3() + "\n";
        step = step + "Last step : "+larecette.getStep4();
        textViewEtapes.setText(step);
        textViewRating.setText("Note : "+larecette.getRating()+"/"+(larecette.getVoters()*5));
        textViewAllComment.setText(larecette.getComment().toString());

        Log.d("RecetteIO",larecette.getVoters()+"");
        /*
        String tempo = "";
        for (String string : printComment) {
            tempo = tempo + "\n" + string;
        }*/
    }
    private void choiceRecipe() {
        String value = getIntent().getStringExtra("regimeChoice");
        String valueTemp = getIntent().getStringExtra("tempChoice");

        // on lance le processus
        RecetteIO.init(this);
        //afficheTest = RecetteIO.loadRecipesFromJson(this);
        Log.d("RecetteIO", "type recette " + "ma valeur est : " + valueTemp);
        ArrayList<DataRecette> filteredRecipes = new ArrayList<>();

        if (valueTemp.equals("hot_meal")) {
            String valueChoice = getIntent().getStringExtra("platsChoice");
            Log.d("RecetteIO", "Passage dans la boucle chaud");
            Log.d("RecetteIO", "Ma valeur de plat est " + valueChoice);

            for (DataRecette recette : afficheTest) {
                Log.d("RecetteIO", "Le type est" + recette.getType());
                if (recette.getType().equals(valueChoice)) {
                    filteredRecipes.add(recette);
                }
            }

            if (!filteredRecipes.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(filteredRecipes.size());
                larecette = filteredRecipes.get(randomIndex);
                Log.d("RecetteIO", "La Recette " + larecette.getNomRecipe());
            } else {
                Log.d("RecetteIO", "Aucune recette ne correspond au choix de plat.");
                larecette=afficheTest.get(0);
                // Vous pouvez ajouter un message d'erreur ou une logique de secours ici.
            }
        } else if (valueTemp.equals("cold_meal")) {
            Log.d("RecetteIO", "Passage dans la boucle froid");

            for (DataRecette recette : afficheTest) {
                if (recette.getType().equals(value)) {
                    filteredRecipes.add(recette);
                }
            }

            if (!filteredRecipes.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(filteredRecipes.size());
                larecette = filteredRecipes.get(randomIndex);
                Log.d("RecetteIO", "La Recette " + larecette.getNomRecipe());
            } else {
                Log.d("RecetteIO", "Aucune recette ne correspond au choix de régime.");
                // Vous pouvez ajouter un message d'erreur ou une logique de secours ici.
            }
        } else {
            Log.d("RecetteIO", "Passage dans la boucle erreur");
            larecette = afficheTest.get(3);
        }

        displayRecipe();
    }
    private void demanderPermission() {
        // verifie si nous avons les permissions de continuer
        if(EasyPermissions.hasPermissions(this,  Build.VERSION.SDK_INT >=33 ? permissionsWriteRead : oldPermissionsWriteRead)){
            // si les permissions sont deja accepter
            // on lance le processus
            choiceRecipe();

        }else{
            // sinon on demande les permissions d'y acceder
            EasyPermissions.requestPermissions(
                    this,
                    "Accepter les permissions pour pouvoir continuer",
                    100,
                    Build.VERSION.SDK_INT >=33 ? permissionsWriteRead : oldPermissionsWriteRead
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // nous capturons la requette et la passons a EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if( requestCode ==100 && perms.size() ==2) {
            // si les permissions nous sont accorder
            choiceRecipe();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        // sil nous refuses toutes les permissions
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            // nous lui proposons d'aller nous les accorder dans ses parametres
            new AppSettingsDialog.Builder(this).build().show();
        }else{
            // sinon nous lui affichons un message
            Toast.makeText(getApplicationContext(), "Vous n'avez pas accepter les permissions", Toast.LENGTH_SHORT).show();
        }
    }
}