package com.example.caloriescalculator.room_db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.caloriescalculator.db.food;

import java.util.List;

@Dao
public interface foodDAO {
    @Query("SELECT * FROM food")
    List<food> getAll();

    @Insert
    void insert(food fd);

    @Query("DELETE FROM food")
    void deleteAll();
}
