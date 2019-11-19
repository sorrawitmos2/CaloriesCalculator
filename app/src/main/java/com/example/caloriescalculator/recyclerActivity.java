package com.example.caloriescalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caloriescalculator.adapter.adapterRecyclerView;
import com.example.caloriescalculator.db.DatabaseHelper;
import com.example.caloriescalculator.db.food;
import com.example.caloriescalculator.db_account.account;
import com.example.caloriescalculator.room_db.FoodRepository;

import java.util.ArrayList;
import java.util.List;

public class recyclerActivity extends AppCompatActivity {
    private List<food> mFood = new ArrayList<>();


    DatabaseHelper dbHelper;
    SQLiteDatabase foodDB;
    Cursor mCursor;

    String mCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Intent intent = getIntent();
        mCalories = intent.getStringExtra("cal");

        reloadFood();


        //Reset Button
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(recyclerActivity.this)
                        .setTitle("Reset")
                        .setMessage("Do you want reset now ?")
                        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FoodRepository repo = new FoodRepository(recyclerActivity.this);
                                repo.deleteFood(new FoodRepository.deleteCallback() {
                                    @Override
                                    public void deleteFood() {
                                        onResume();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();

            }
        });

        //Ok Button
        Button okButton = findViewById(R.id.okokok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText foodEditText = findViewById(R.id.food_editText);
                String foodEdit = foodEditText.getText().toString();
                food food=null;
                for (food f : mFood) {
                    if (foodEdit.equals(f.name)) {
                        food = f;
                    }
                }
                if(food != null){
                    FoodRepository repo = new FoodRepository(recyclerActivity.this);
                    repo.insertFood(food, new FoodRepository.InsertCallback() {
                        @Override
                        public void onInsertSuccess() {
                            onResume();
                        }
                    });

                }else{
                    Toast.makeText(recyclerActivity.this,"no food in database", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(recyclerActivity.this,InsertActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void reloadFood(){
        dbHelper = new DatabaseHelper(this);
        foodDB = dbHelper.getWritableDatabase();

        mCursor = foodDB.rawQuery("SELECT " + DatabaseHelper.COL_NAME+", "+DatabaseHelper.COL_CALORIES+" FROM " + DatabaseHelper.TABLE_FOOD, null);

        mCursor.moveToFirst();
        food fd ;

        while(!mCursor.isAfterLast()){
            fd = new food(0,mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_NAME)),mCursor.getInt(Integer.valueOf(mCursor.getColumnIndex(DatabaseHelper.COL_CALORIES))));
            mFood.add(fd);
            mCursor.moveToNext();
        }

        //LOG
        Log.v("list","###LIST_FOOD###");
        for(food f :mFood){
            Log.v("DATA", f.name+" "+f.cal);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        reloadFood();
        reloadData();
    }

    private void reloadData(){
        FoodRepository repo= new FoodRepository(recyclerActivity.this);
        repo.getFood(new FoodRepository.Callback() {
            @Override
            public void onGetFood(List<food> itemList) {
                int totalCal = Integer.parseInt(mCalories);
                for (food item : itemList) {
                        totalCal -= item.cal;
                }

                if(totalCal < 0) {
                    new AlertDialog.Builder(recyclerActivity.this)
                            .setMessage("You eat too much")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .show();
                    totalCal = 0;
                }

                TextView CaloriesTextView = findViewById(R.id.calories_text_view);
                CaloriesTextView.setText("Today you have "+ totalCal +" KiloCalories");
                RecyclerView recyclerView = findViewById(R.id.ledger_recyclerview);
                adapterRecyclerView adapter = new adapterRecyclerView(
                        recyclerActivity.this,
                        R.layout.item_food,
                        itemList
                );
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerActivity.this));
                recyclerView.setAdapter(adapter);
            }
        });
    }


}
