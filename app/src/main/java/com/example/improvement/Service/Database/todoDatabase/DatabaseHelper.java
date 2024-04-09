package com.example.improvement.Service.Database.todoDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MY_DATABASE";
    public static final String TODO_TABLE = "TODO_TABLE";

    private static final String COL_2 = "title";
    private static final String COL_3 = "description";
    private static final String COL_4 = "endDate";
    private static final String COL_5 = "createDate";
    private static final String COL_6 = "status";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 5);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE NOTE_TABLE (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, startTime TEXT )");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ TODO_TABLE+" (id INTEGER PRIMARY KEY AUTOINCREMENT , title TEXT, description TEXT, endDate TEXT, createDate TEXT, status TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NOTE_TABLE " );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TODO_TABLE );
        // Problem One that is my main problem ======================
        onCreate(sqLiteDatabase);
    }


    // insert Data ----------------------------------------
    public Boolean insertData(String title, String description, String endDate, String createDate, String status){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, description);
        contentValues.put(COL_4, endDate);
        contentValues.put(COL_5, createDate);
        contentValues.put(COL_6, status);

        long var = db.insert(TODO_TABLE, null, contentValues );


        if(var == -1){
            return false;
        }else{
            return true;
        }

    }

    // note table data insert -------------------------------------
    public Boolean noteInsert(String title, String description, String startTime){

        SQLiteDatabase mydb = this.getWritableDatabase();

        ContentValues Values = new ContentValues();
        Values.put("title", title);
        Values.put("description", description);
        Values.put("startTime", startTime);


        long var1 = mydb.insert("NOTE_TABLE", null, Values );


        if(var1 == -1){
            return false;
        }else{
            return true;
        }

    }


}