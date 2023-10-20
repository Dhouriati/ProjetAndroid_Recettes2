package com.example.projetandroid_recettes;


import java.util.ArrayList;

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

    public DataRecette(String nomRecipe, String nomIngredient1, String nomIngredient2,
                       String nomIngredient3, String nomIngredient4, String step1, String step2,
                       String step3, String step4, float rating,  int voters, String type) {

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

   /* public static ArrayList<DataRecette> InfosRecette () {
        ArrayList<DataRecette> listerecette= new ArrayList<>();
        listerecette.add(new DataRecette(
                "Summer salad", "Salad", "Melon",
                "Tomatoes", "Apple",
                "Put the salad on a large plate", "Add the melon",
                "Add the tomatoes and apples", "Mix all ingredient together",
                0, "Great ! I love it"
        ));
        listerecette.add(new DataRecette(
                "Vegetarian steak with spinach", "Rice",
                "onion", "spinach", "vegetable steak",
                "Cook the rice and vegetable steak in a pan." ,"Brown onion over low heat and add to rice.", "Add spinach", "Cook all together and add spices before eating",
                3, "I love curry"
        ));
        listerecette.add(new DataRecette(
                "Pancakes with mango", "Flour",
                "Eggs and milk", "Mango", "Butter and sugar",
                "Cut mango in small pieces" ,"Place the flour in a bowl and form a well. Add whole eggs, sugar, oil and butter into this well.",
                "Mix gently with a whisk, adding the milk as you go.", "Heat an oiled frying pan. Cook all the crêpes in this way over a low heat.",
                3, "Best pancakes ever"
        ));
        return listerecette;
    }*/
   public void addRating (float value) {
       this.rating+=value;
       this.voters+=1;
   }
}