package com.example.caloriescalculator.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_FOOD = "food";

    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_CALORIES ="calories";

    private static final String SQL_CREATE_FOOD =
            "CREATE TABLE " + TABLE_FOOD + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT, "
            + COL_CALORIES + " TEXT )";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FOOD);

        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, "ผัดกะเพราหมู");
        cv.put(COL_CALORIES, "580");
        db.insert(TABLE_FOOD,null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ผัดกะเพราไก่ไข่ดาว");
        cv.put(COL_CALORIES, "630");
        db.insert(TABLE_FOOD,null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ข้าวผัดกุ้งใส่ไข่");
        cv.put(COL_CALORIES, "595");
        db.insert(TABLE_FOOD,null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ผ้าวผัดไส้กรอก");
        cv.put(COL_CALORIES, "520");
        db.insert(TABLE_FOOD,null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ข้าวผัดหมูใส่ไข่");
        cv.put(COL_CALORIES, "557");
        db.insert(TABLE_FOOD,null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ผ้าวผัดอเมริกัน");
        cv.put(COL_CALORIES, "790");
        db.insert(TABLE_FOOD,null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "สุกี้แห้งทะเล");
        cv.put(COL_CALORIES, "280");
        db.insert(TABLE_FOOD,null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ส้มตำไทย");
        cv.put(COL_CALORIES, "55");
        db.insert(TABLE_FOOD,null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ส้มตำปู");
        cv.put(COL_CALORIES, "35");
        db.insert(TABLE_FOOD,null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ไก่ทอด");
        cv.put(COL_CALORIES, "345");
        db.insert(TABLE_FOOD,null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ก๋วยเตี๋ยวลูกชิ้น");
        cv.put(COL_CALORIES, "250");
        db.insert(TABLE_FOOD,null,cv);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
