package com.example.caloriescalculator.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food")
public class food {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "cal")
    public int cal;


    public food(int id, String name, int cal) {
        this.id = id;
        this.name = name;
        this.cal = cal;
    }
}
