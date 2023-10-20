package com.example.projetandroid_recettes;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecetteIO {

    public static void saveJsonToFile(Context context, JSONObject jsonObject) {
        try {
            // Vérifie si le stockage externe est disponible pour l'écriture

            // Crée un dossier s'il n'existe pas
            File dir = new File(context.getFilesDir() + "/MyRecette");

            if (!dir.exists()) {
                dir.mkdirs();
            }
            Log.d("RecetteIO","Repertoire sauvegarde : " + dir.getAbsolutePath());

            // Crée le fichier JSON dans le dossier
            File file = new File(dir, getFileName());

            // Écrit le JSON dans le fichier
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(jsonObject.toString());
            myOutWriter.close();
            fOut.close();

            Log.d("RecetteIO","File saved successfully: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static JSONObject readJsonFromFile(Context context) {
        JSONObject jsonObject = null;

        try {

            // Obtient le fichier JSON dans le dossier
            File file = new File(context.getFilesDir() + "/MyRecette", getFileName());
            Log.d("RecetteIO","Read Json : " + file.getAbsolutePath());
            // Lit le contenu du fichier JSON
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            // Convertit la chaîne JSON en objet JSONObject
            jsonObject = new JSONObject(stringBuilder.toString());

            // Ferme les flux
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
    // Fonction pour sauvegarder une liste d'objets DataRecette dans un fichier JSON
    public static void saveRecipesToJson(Context context, List<DataRecette> recipes) {
        try {
            // Crée un JSONArray pour stocker les recettes
            JSONArray jsonArray = new JSONArray();

            // Convertit chaque objet DataRecette en JSONObject et l'ajoute au tableau
            for (DataRecette recipe : recipes) {
                JSONObject recipeJson = new JSONObject();
                recipeJson.put("nomRecipe", recipe.getNomRecipe());
                recipeJson.put("nomIngredient1", recipe.getNomIngredient1());
                recipeJson.put("nomIngredient2", recipe.getNomIngredient2());
                recipeJson.put("nomIngredient3", recipe.getNomIngredient3());
                recipeJson.put("nomIngredient4", recipe.getNomIngredient4());
                recipeJson.put("step1", recipe.getStep1());
                recipeJson.put("step2", recipe.getStep2());
                recipeJson.put("step3", recipe.getStep3());
                recipeJson.put("step4", recipe.getStep4());
                recipeJson.put("rating", recipe.getRating());
                recipeJson.put("voters", recipe.getVoters());
                recipeJson.put("type", recipe.getType());
                recipeJson.put("comment", recipe.getComment());

                jsonArray.put(recipeJson);
            }

            // Convertit le tableau JSON en un seul JSONObject
            JSONObject finalJsonObject = new JSONObject();
            finalJsonObject.put("recipes", jsonArray);

            // Utilise la fonction saveJsonToFile pour sauvegarder le JSONObject dans un fichier
            saveJsonToFile(context, finalJsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Fonction pour charger une liste d'objets DataRecette à partir d'un fichier JSON
    public static ArrayList<DataRecette> loadRecipesFromJson(Context context) {
        ArrayList<DataRecette> recipes = new ArrayList<>();
        //List<String> comments = new ArrayList<>();

        try {
            // Utilise la fonction readJsonFromFile pour lire le JSONObject depuis le fichier
            JSONObject jsonObject = readJsonFromFile(context);

            if (jsonObject != null && jsonObject.has("recipes")) {
                // Obtient le tableau JSON des recettes
                JSONArray jsonArray = jsonObject.getJSONArray("recipes");

                // Parcourt le tableau pour convertir chaque objet JSON en DataRecette
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject recipeJson = jsonArray.getJSONObject(i);

                    // Crée un nouvel objet DataRecette en utilisant les données du JSONObject
                    DataRecette recipe = new DataRecette(
                            recipeJson.optString("nomRecipe"),
                            recipeJson.optString("nomIngredient1"),
                            recipeJson.optString("nomIngredient2"),
                            recipeJson.optString("nomIngredient3"),
                            recipeJson.optString("nomIngredient4"),
                            recipeJson.optString("step1"),
                            recipeJson.optString("step2"),
                            recipeJson.optString("step3"),
                            recipeJson.optString("step4"),
                            (float) recipeJson.optDouble("rating"),
                            recipeJson.optInt("voters"),
                            recipeJson.optString("type"),
                            recipeJson.optString("comment")
                            //comments
                    );

                    // Ajoute la recette à la liste
                    recipes.add(recipe);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return recipes;
    }
    // Fonction pour initialiser le fichier JSON
    public static void init(Context context) {
        File dir = new File(context.getFilesDir()+ "/MyRecette/");
        if (!dir.exists()) {
            // Le répertoire n'existe pas, on le crée
            dir.mkdirs();
        }

        File file = new File(dir, getFileName());
        if (!file.exists()) {
            // Le fichier n'existe pas, on crée un nouveau fichier avec le contenu initial

            try {
                ArrayList<DataRecette> listerecette= new ArrayList<>();
                listerecette.add(new DataRecette(
                        "Summer salad", "Salad", "Melon",
                        "Tomatoes", "Apple",
                        "Put the salad on a large plate", "Add the melon",
                        "Add the tomatoes and apples", "Mix all ingredient together",
                        0,
                        1,
                        "Appetizer", "I like it"
                ));
                listerecette.add(new DataRecette(
                        "Vegetarian steak with spinach", "Rice",
                        "onion", "spinach", "vegetable steak",
                        "Cook the rice and vegetable steak in a pan." ,
                        "Brown onion over low heat and add to rice.",
                        "Add spinach",
                        "Cook all together and add spices before eating",
                        3,
                        1,
                        "Vegan", "I like it"
                ));
                listerecette.add(new DataRecette(
                        "Pancakes with mango", "Flour",
                        "Eggs and milk", "Mango", "Butter and sugar",
                        "Cut mango in small pieces" ,"Place the flour in a bowl and form a well. Add whole eggs, sugar, oil and butter into this well.",
                        "Mix gently with a whisk, adding the milk as you go.", "Heat an oiled frying pan. Cook all the crêpes in this way over a low heat.",
                        3,
                        1,
                        "Dessert", "I like it"
                ));
                listerecette.add(new DataRecette(
                        "Chili con carne", "kidney beans",
                        "Wheat", "Beef", "Chili sauce with spices",
                        "Cut cooked beef into meatballs." ,"Add the kidney beans",
                        "Add wheat", "Add the chili sauce and mix well.",
                        3,
                        1,
                        "No Restriction", "I like it"
                ));
                listerecette.add(new DataRecette(
                        "Cantonese rice", "Rice",
                        "Egg", "diced ham", "peas",
                        "Cook the rice" ,"Make scrambled eggs",
                        "Add peas, diced ham and scrambled eggs", "Mix together and serve hot",
                        3,
                        1,
                        "No Restriction", "I like it"
                ));
                saveRecipesToJson(context, listerecette);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void updateRecipeByName(Context context, String recipeName, DataRecette updatedRecipe) {
        try {
            // Lire les données JSON existantes depuis le fichier
            JSONObject jsonObject = readJsonFromFile(context);

            // Vérifier si le fichier JSON est valide
            if (jsonObject != null) {
                // Récupérer l'array de recettes
                JSONArray recipesArray = null;
                try {
                    recipesArray = jsonObject.getJSONArray("recipes");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                // Parcourir toutes les recettes pour trouver celle avec le nom spécifié
                for (int i = 0; i < recipesArray.length(); i++) {
                    JSONObject recipeObject = recipesArray.getJSONObject(i);
                    String currentRecipeName = recipeObject.getString("nomRecipe");

                    // Si le nom correspond, mettre à jour les données de la recette
                    if (currentRecipeName.equals(recipeName)) {
                        recipeObject.put("nomIngredient1", updatedRecipe.getNomIngredient1());
                        recipeObject.put("nomIngredient2", updatedRecipe.getNomIngredient2());
                        recipeObject.put("nomIngredient3", updatedRecipe.getNomIngredient3());
                        recipeObject.put("nomIngredient4", updatedRecipe.getNomIngredient4());
                        recipeObject.put("step1", updatedRecipe.getStep1());
                        recipeObject.put("step2", updatedRecipe.getStep2());
                        recipeObject.put("step3", updatedRecipe.getStep3());
                        recipeObject.put("step4", updatedRecipe.getStep4());
                        recipeObject.put("rating", updatedRecipe.getRating());
                        recipeObject.put("voters", updatedRecipe.getVoters());
                        recipeObject.put("type", updatedRecipe.getType());
                        recipeObject.put("comment", updatedRecipe.getComment());


                        break;
                    }
                }

                // Enregistrez les modifications dans le fichier
                saveJsonToFile(context, jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static String getFileName(){
        return "recette.json";
    }


}