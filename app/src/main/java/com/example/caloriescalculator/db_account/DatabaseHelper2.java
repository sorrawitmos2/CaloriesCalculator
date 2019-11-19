package com.example.caloriescalculator.db_account;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper2 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db2";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ACCOUNT = "account";

    public static final String COL_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD ="password";
    public static final String COL_FULLNAME = "fullname";
    public static final String COL_AGE = "age";
    public static final String COL_WEIGHT = "weight";
    public static final String COL_HEIGHT = "height";
    public static final String COL_GENDER = "gender";
    public static final String COL_CALORIES = "calories";



    private static final String SQL_CREATE_ACCOUNT =
            "CREATE TABLE " + TABLE_ACCOUNT + " ("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_FULLNAME + " TEXT, "
                    + COL_USERNAME + " TEXT, "
                    + COL_PASSWORD + " TEXT, "
                    + COL_AGE + " TEXT, "
                    + COL_WEIGHT + " TEXT, "
                    + COL_HEIGHT + " TEXT, "
                    + COL_GENDER + " TEXT, "
                    + COL_CALORIES + " TEXT )";

    public DatabaseHelper2(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ACCOUNT);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
