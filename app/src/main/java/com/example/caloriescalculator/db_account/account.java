package com.example.caloriescalculator.db_account;

public class account {

    public int id;
    public String fullname;
    public String username;
    public String password;
    public String age;
    public String weight;
    public String height;
    public String gender;
    public String Calories;


    public account(int id, String fullname, String username, String password, String age, String weight, String height, String gender, String calories) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        Calories = calories;
    }
}
