package com.example.projetandroid_recettes;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import java.io.File;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;
    public final static String PREFERENCES_NAME = "recette_prefs";
    private static int MODE;

    private final static String KEY_ISLOGGED = "isLogged";
    private final static String KEY_FIRST_NAME = "firstName";
    private final static String KEY_LAST_NAME = "lastName";
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        this.MODE = Context.MODE_PRIVATE;
        //récupère le fichier
        this.sharedPreferences = context.getSharedPreferences(this.PREFERENCES_NAME, this.MODE);
        this.sharedPreferencesEditor = this.sharedPreferences.edit();
    }
    public boolean isLogged() {
        return this.sharedPreferences.getBoolean(this.KEY_ISLOGGED, false);
    }

    public String getFirstName() {
        return this.sharedPreferences.getString(this.KEY_FIRST_NAME, null);
    }

    public String getLastName() {
        return this.sharedPreferences.getString(this.KEY_LAST_NAME, null);
    }
    public void insertUser(String firstName, String lastName) {
        this.sharedPreferencesEditor.putBoolean(this.KEY_ISLOGGED, true);
        this.sharedPreferencesEditor.putString(this.KEY_FIRST_NAME, firstName);
        this.sharedPreferencesEditor.putString(this.KEY_LAST_NAME, lastName);
        this.sharedPreferencesEditor.commit();
    }

    public void logout() {
        this.sharedPreferencesEditor.clear().commit();
    }

}
