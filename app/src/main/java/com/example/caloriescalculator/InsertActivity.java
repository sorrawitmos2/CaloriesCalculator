package com.example.caloriescalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caloriescalculator.db.DatabaseHelper;
import com.example.caloriescalculator.db.food;
import com.example.caloriescalculator.room_db.FoodRepository;

public class InsertActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    SQLiteDatabase foodDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        dbHelper = new DatabaseHelper(InsertActivity.this);
        foodDB = dbHelper.getWritableDatabase();

        Button addMenuButton = findViewById(R.id.addmenu_button);
        addMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText foodnameEditText = findViewById(R.id.addfoodname_editText);
                String foodname = foodnameEditText.getText().toString();

                EditText caloriesEditText = findViewById(R.id.addcalories_editText);
                String scal = caloriesEditText.getText().toString();

                if (foodname.length() > 0 && scal.length() > 0) {
                    food item = new food(0, foodname, Integer.parseInt(scal));

                    FoodRepository repo = new FoodRepository(InsertActivity.this);
                    repo.insertFood(item, new FoodRepository.InsertCallback() {
                        @Override
                        public void onInsertSuccess() {
                            finish();
                        }
                    });


                    ContentValues cv = new ContentValues();
                    cv.put("name",foodname);
                    cv.put("calories",scal);
                    foodDB.insert("food",null,cv);

                    Toast.makeText(InsertActivity.this,/*String*/"Add Successfully", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(InsertActivity.this,/*String*/"Incomplete Data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
