package com.naminopak.improvement.Service.Database.BlogDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class BlogDatabase extends SQLiteAssetHelper {



    public BlogDatabase(Context context) {
        super(context, "business.db", null, 1);
    }



    public Cursor getAllData(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM investment";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }


}
