package com.example.projetandroid_recettes;


import java.util.ArrayList;
import java.util.List;

public class DataRecette {


    private final String nomRecipe;
    private final String nomIngredient1;
    private final String nomIngredient2;
    private final String nomIngredient3;
    private final String nomIngredient4;
    private final String step1;
    private final String step2;
    private final String step3;
    private final String step4;
    private int voters;
    private  float rating;
    private final String type;
    private final String comment;

    public DataRecette(String nomRecipe, String nomIngredient1, String nomIngredient2,
                       String nomIngredient3, String nomIngredient4, String step1, String step2,
                       String step3, String step4, float rating,  int voters, String type, String comment) {
            /*public DataRecette(String nomRecipe, String nomIngredient1, String nomIngredient2,
                String nomIngredient3, String nomIngredient4, String step1, String step2,
                String step3, String step4, float rating,  int voters, String type) {*/

        this.nomRecipe = nomRecipe;
        this.nomIngredient1 = nomIngredient1;
        this.nomIngredient2 = nomIngredient2;
        this.nomIngredient3 = nomIngredient3;
        this.nomIngredient4 = nomIngredient4;
        this.step1 = step1;
        this.step2 = step2;
        this.step3 = step3;
        this.step4 = step4;
        this.rating = rating;
        this.voters=voters;
        this.type=type;
        this.comment=comment;

    }

    public String getNomRecipe() {
        return nomRecipe;
    }

    public String getNomIngredient1() {
        return nomIngredient1;
    }

    public String getNomIngredient2() {
        return nomIngredient2;
    }

    public String getNomIngredient3() {
        return nomIngredient3;
    }

    public String getNomIngredient4() {
        return nomIngredient4;
    }

    public String getStep1() {
        return step1;
    }

    public String getStep2() {
        return step2;
    }

    public String getStep3() {
        return step3;
    }

    public String getStep4() {
        return step4;
    }

    public float getRating () {
        return rating;
    }

    public int getVoters(){return voters;}

    public String getType(){return type;}

    public String getComment(){return comment;}

   public static ArrayList<DataRecette> InfosRecette () {
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
               4,
               1,
               "Dessert", "I like it"
       ));
       listerecette.add(new DataRecette(
               "Chili con carne", "kidney beans",
               "Wheat", "Beef", "Chili sauce with spices",
               "Cut cooked beef into meatballs." ,"Add the kidney beans",
               "Add wheat", "Add the chili sauce and mix well.",
               5,
               1,
               "No Restriction", "I like it"
       ));
       listerecette.add(new DataRecette(
               "Cantonese rice", "Rice",
               "Egg", "diced ham", "peas",
               "Cook the rice" ,"Make scrambled eggs",
               "Add peas, diced ham and scrambled eggs", "Mix together and serve hot",
               4,
               1,
               "MainCourse", "I like it"
       ));
       listerecette.add(new DataRecette(
               "Vegetarian pastabox", "Pasta",
               "mushroom", "cheese", "avocado",
               "Cook pasta" ,"Add mushroom",
               "Add your favorite cheese and avocado", "Mix together and enjoy !",
               2,
               1,
               "Vegetarian", "I like it"
       ));
        return listerecette;
    }
   public void addRating (float value) {
       this.rating+=value;
       this.voters+=1;
   }
}