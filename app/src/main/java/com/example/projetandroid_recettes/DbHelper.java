package com.example.projetandroid_recettes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RecetteConnex.db";
    // Table for storing user information
    public static final String TABLE_USER = "Connexion";
    public static final String COLUMN_ID = "idUser";
    public static final String COLUMN_FIRST_NAME = "nameUser";
    public static final String COLUMN_LAST_NAME = "prenomUser";
    public static final String COLUMN_Age = "ageUser";

    public static final String COLUMN_Gender = "genderUser";

    private static final String CHECK_USER_QUERY = "SELECT * FROM " + TABLE_USER +
            " WHERE " + COLUMN_FIRST_NAME + " = ? AND " + COLUMN_LAST_NAME + " = ?";



    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //this.context=context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database", "Database creation started...");


        String query = "CREATE TABLE "+
        TABLE_USER + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRST_NAME + " TEXT, " +
                COLUMN_LAST_NAME + " TEXT, " +
                COLUMN_Age + " TEXT, " +
                COLUMN_Gender + " TEXT);";
        // Execute the SQL query to create the table
        db.execSQL(query);
        Log.d("Database", "Création de la base de donnée");
    }
    // this method is use to add new user to our sqlite database.
    public void insertUserInfo(String firstName, String lastName, String age, String gender) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_LAST_NAME, lastName);
        values.put(COLUMN_Age, age);
        values.put(COLUMN_Gender, gender);

        // after adding all values we are passing
        // content values to our table.
        long resultat=db.insert(TABLE_USER, null, values);
        Log.d("bdd",resultat+"-------------------------------------------------------------");


        // at last we are closing our
        // database after adding database.
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade the database
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
        Log.d("Database", "Database upgraded successfully");

    }
    public boolean checkUserExistence(String firstName, String lastName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {firstName, lastName};

        Cursor cursor = db.rawQuery(CHECK_USER_QUERY, selectionArgs);

        boolean userExists = cursor.getCount() > 0;
        cursor.close();

        return userExists;
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
