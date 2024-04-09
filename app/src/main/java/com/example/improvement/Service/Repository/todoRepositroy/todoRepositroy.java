package com.example.improvement.Service.Repository.todoRepositroy;

import android.content.Context;
import android.util.Log;

import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;

public class todoRepositroy implements todoRepoInterface{


    private static todoRepositroy todoRepositroy;
    public DatabaseHelper databaseHelper;



    public static todoRepositroy getInstance(){
        if (todoRepositroy == null){
            todoRepositroy = new todoRepositroy();
        }
        return todoRepositroy;
    }



}
