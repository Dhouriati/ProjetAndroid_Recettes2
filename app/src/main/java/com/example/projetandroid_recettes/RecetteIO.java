package com.example.projetandroid_recettes;

import android.content.Context;
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
import java.util.List;

public class RecetteIO {

    public static void saveJsonToFile(Context context, JSONObject jsonObject) {
        try {
            // Checks if external storage is available for writing

            // Creates a folder if it doesn't exist
            File dir = new File(context.getFilesDir() + "/MyRecette");

            if (!dir.exists()) {
                dir.mkdirs();
            }
            Log.d("RecetteIO","Saved directory in : " + dir.getAbsolutePath());

            // Creates the JSON file in the folder
            File file = new File(dir, getFileName());

            // Writes the JSON to the file
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(jsonObject.toString());
            myOutWriter.close();
            fOut.close();

            Log.d("RecetteIO","File saved successfully in : " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static JSONObject readJsonFromFile(Context context) {
        JSONObject jsonObject = null;

        try {

            // Gets the JSON file in the folder
            File file = new File(context.getFilesDir() + "/MyRecette", getFileName());
            Log.d("RecetteIO","Read Json : " + file.getAbsolutePath());
            // Reads the contents of the JSON file
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            // Converts JSON string to JSONObject
            jsonObject = new JSONObject(stringBuilder.toString());

            // Closes flows
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
    // Function for saving a list of DataRecette objects in a JSON file
    public static void saveRecipesToJson(Context context, List<DataRecette> recipes) {
        try {
            // Creates a JSONArray to store recipes
            JSONArray jsonArray = new JSONArray();

            // Converts each DataRecette object into a JSONObject and adds it to the array
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

            // Converts the JSON array into a single JSONObject
            JSONObject finalJsonObject = new JSONObject();
            finalJsonObject.put("recipes", jsonArray);

            // Use the saveJsonToFile function to save the JSONObject to a file.
            saveJsonToFile(context, finalJsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Function for loading a list of DataRecette objects from a JSON file
    public static ArrayList<DataRecette> loadRecipesFromJson(Context context) {
        ArrayList<DataRecette> recipes = new ArrayList<>();
        //List<String> comments = new ArrayList<>();

        try {
            // Use the readJsonFromFile function to read the JSONObject from the
            JSONObject jsonObject = readJsonFromFile(context);

            if (jsonObject != null && jsonObject.has("recipes")) {
                // Gets the JSON table of recipes
                JSONArray jsonArray = jsonObject.getJSONArray("recipes");

                // Scans the array to convert each JSON object into a DataRecette
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject recipeJson = jsonArray.getJSONObject(i);

                    // Creates a new DataRecette object using data from the JSONObject
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

                    // Add recipe to list
                    recipes.add(recipe);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return recipes;
    }
    // Function to initialize the JSON file
    public static void init(Context context) {
        File dir = new File(context.getFilesDir()+ "/MyRecette/");
        if (!dir.exists()) {
            // if the directory doesn't exist, we create it
            dir.mkdirs();
        }

        File file = new File(dir, getFileName());
        if (!file.exists()) {
            // The file does not exist, so we create a new file with the original contents

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
            // Read existing JSON data from the file
            JSONObject jsonObject = readJsonFromFile(context);

            // Check if the JSON file is valid
            if (jsonObject != null) {
                // Retrieve the recipe array
                JSONArray recipesArray = null;
                try {
                    recipesArray = jsonObject.getJSONArray("recipes");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                // Browse all recipes to find the one with the specified name
                for (int i = 0; i < recipesArray.length(); i++) {
                    JSONObject recipeObject = recipesArray.getJSONObject(i);
                    String currentRecipeName = recipeObject.getString("nomRecipe");

                    // If name matches, update recipe data
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

                // Save the changes in the file
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