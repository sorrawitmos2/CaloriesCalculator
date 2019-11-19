package com.example.caloriescalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caloriescalculator.db.DatabaseHelper;
import com.example.caloriescalculator.db_account.DatabaseHelper2;
import com.example.caloriescalculator.db_account.account;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private List<account> mAccount = new ArrayList<>();
    DatabaseHelper2 dbHelper2;
    SQLiteDatabase accountDB;
    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reloadAccount();



        //loginButton
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameEditText = findViewById(R.id.username_editText);
                String username = usernameEditText.getText().toString();

                EditText passwordEditText = findViewById(R.id.password_editText);
                String password = passwordEditText.getText().toString();


                if(username.length() > 0 && password.length() > 0) {
                    boolean check = false;
                    for (account ac : mAccount) {
                        if (username.equals(ac.username) && password.equals(ac.password)) {
                            Toast.makeText(LoginActivity.this,/*String*/"Welcome " + ac.fullname, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, recyclerActivity.class);
                            intent.putExtra("cal",ac.Calories);
                            startActivity(intent);
                            check = true;
                            break;
                        }
                    }
                    if(check == false){
                        Toast.makeText(LoginActivity.this,"Invalid username or password", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(LoginActivity.this,"All fields are required", Toast.LENGTH_SHORT).show();

                }

            }
        });

        //registerButton
        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void reloadAccount(){
        dbHelper2 = new DatabaseHelper2(LoginActivity.this);
        accountDB = dbHelper2.getWritableDatabase();

        mCursor = accountDB.rawQuery("SELECT " + DatabaseHelper2.COL_FULLNAME+","+DatabaseHelper2.COL_USERNAME+", "+DatabaseHelper2.COL_PASSWORD+","+
                DatabaseHelper2.COL_AGE+","+DatabaseHelper2.COL_WEIGHT+","+DatabaseHelper2.COL_HEIGHT+","+DatabaseHelper2.COL_GENDER+","+DatabaseHelper2.COL_CALORIES+" FROM " + DatabaseHelper2.TABLE_ACCOUNT, null);

        mCursor.moveToFirst();
        account ac ;

        while(!mCursor.isAfterLast()){
            ac = new account(0,mCursor.getString(mCursor.getColumnIndex(DatabaseHelper2.COL_FULLNAME)),
                    mCursor.getString(mCursor.getColumnIndex(DatabaseHelper2.COL_USERNAME)),
                    mCursor.getString(mCursor.getColumnIndex(DatabaseHelper2.COL_PASSWORD)),
                    mCursor.getString(mCursor.getColumnIndex(DatabaseHelper2.COL_AGE)),
                    mCursor.getString(mCursor.getColumnIndex(DatabaseHelper2.COL_WEIGHT)),
                    mCursor.getString(mCursor.getColumnIndex(DatabaseHelper2.COL_HEIGHT)),
                    mCursor.getString(mCursor.getColumnIndex(DatabaseHelper2.COL_GENDER)),
                    mCursor.getString(mCursor.getColumnIndex(DatabaseHelper2.COL_CALORIES))
            );
            mAccount.add(ac);
            mCursor.moveToNext();
        }

        //LOG
        Log.v("list","###LIST_USERNAME###");
        for(account c :mAccount){
            Log.v("DATA", c.fullname+" "+c.username+" "+c.password+" "+c.age+" "+c.weight+" "+c.height+" "+c.gender+" "+c.Calories);
        }
    }

}
