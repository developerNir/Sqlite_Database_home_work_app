package com.naminopak.improvement.Service.Repository.todoRepositroy;

import com.naminopak.improvement.Service.Database.todoDatabase.DatabaseHelper;

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
