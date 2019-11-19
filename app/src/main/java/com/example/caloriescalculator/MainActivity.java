package com.example.caloriescalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.example.caloriescalculator.db_account.DatabaseHelper2;
import com.example.caloriescalculator.room_db.FoodRepository;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper2 dbHelper2;
    SQLiteDatabase accountDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper2 = new DatabaseHelper2(MainActivity.this);
        accountDB = dbHelper2.getWritableDatabase();

        //Register Button
        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextFullname = findViewById(R.id.fullname_editText);
                String fullname = editTextFullname.getText().toString();

                EditText editTextUsername = findViewById(R.id.username_editText);
                String username = editTextUsername.getText().toString();

                EditText editTextPassword = findViewById(R.id.password_editText);
                String password = editTextPassword.getText().toString();

                EditText editTextAge = findViewById(R.id.Age_editText);
                String age = editTextAge.getText().toString();

                EditText editTextWeight = findViewById(R.id.weight_editText);
                String weight = editTextWeight.getText().toString();

                EditText editTextHeight = findViewById(R.id.height_editText);
                String height = editTextHeight.getText().toString();

                CheckBox checkboxMale = findViewById(R.id.Male_checkBox);
                CheckBox checkboxFemale = findViewById(R.id.Female_checkBox);

                if(fullname.length() > 0 && username.length() > 0 && password.length() > 0 && (age.length() > 0 && age.length() <= 3)
                        && (height.length() > 0 && height.length() <= 3)  && (weight.length() > 0 && weight.length() <= 3) && (checkboxMale.isChecked() || checkboxFemale.isChecked())){
                    int inputAge = Integer.parseInt(age);
                    int inputWeight = Integer.parseInt(weight);
                    int inputHeight = Integer.parseInt(height);
                    if (checkboxFemale.isChecked() && checkboxMale.isChecked()){
                        Toast.makeText(MainActivity.this,"Incomplete Data", Toast.LENGTH_LONG).show();
                    }
                    else if(checkboxMale.isChecked()){
                        int BMR = (int) (66 + (13.7 * inputWeight) + (5 * inputHeight) - (6.8 * inputAge));
                        String strBMR = String.valueOf(BMR);
                        ContentValues cv = new ContentValues();
                        cv.put("fullname",fullname);
                        cv.put("username",username);
                        cv.put("password",password);
                        cv.put("age",age);
                        cv.put("weight",weight);
                        cv.put("height",height);
                        cv.put("gender","male");
                        cv.put("calories",strBMR);
                        accountDB.insert("account",null,cv);
                        Toast.makeText(MainActivity.this,"Register successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else if(checkboxFemale.isChecked()){
                        int BMR = (int) (665 + (9.6 * inputWeight) + (1.8 * inputHeight) - (4.7 * inputAge));
                        String strBMR = String.valueOf(BMR);
                        ContentValues cv = new ContentValues();
                        cv.put("fullname",fullname);
                        cv.put("username",username);
                        cv.put("password",password);
                        cv.put("age",age);
                        cv.put("weight",weight);
                        cv.put("height",height);
                        cv.put("gender","female");
                        cv.put("calories",strBMR);
                        accountDB.insert("account",null,cv);
                        Toast.makeText(MainActivity.this,"Register successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"Incomplete Data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

