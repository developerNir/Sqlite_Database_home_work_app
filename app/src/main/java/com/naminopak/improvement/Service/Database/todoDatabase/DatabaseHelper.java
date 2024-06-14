package com.naminopak.improvement.Service.Database.todoDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        super(context, DATABASE_NAME, null, 3);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE dream (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, image BLOB, createDate TEXT, endDate TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE expense (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, location TEXT, time TEXT,  amount DOUBLE )");
        sqLiteDatabase.execSQL("CREATE TABLE income (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, location TEXT, time TEXT,  amount DOUBLE )");
        sqLiteDatabase.execSQL("CREATE TABLE NOTE_TABLE (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, startTime TEXT )");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TODO_TABLE + " (id INTEGER PRIMARY KEY AUTOINCREMENT , title TEXT, description TEXT, endDate TEXT, createDate TEXT, status TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS dream ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS expense ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS income ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NOTE_TABLE ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        // Problem One that is my main problem ======================
        onCreate(sqLiteDatabase);
    }


    // insert Data ---------------------todo-------------------
    public Boolean insertData(String title, String description, String endDate, String createDate, String status) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, description);
        contentValues.put(COL_4, endDate);
        contentValues.put(COL_5, createDate);
        contentValues.put(COL_6, status);

        long var = db.insert(TODO_TABLE, null, contentValues);


        if (var == -1) {
            return false;
        } else {
            return true;
        }

    }

    // todo ===================== getData By id============================

    public Cursor getData(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TODO_TABLE + " WHERE ID='" + id + "'";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    // todo ================== getAll Data =================================

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TODO_TABLE, null);
        return cursor;
    }


    // todo ==================== delete data by Id ============================
    public Integer deleteTodoById(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TODO_TABLE, "ID=?", new String[]{id});
    }

    //================================ Note =================================
    // note table data insert -------------------------------------
    public Boolean noteInsert(String title, String description, String startTime) {

        SQLiteDatabase mydb = this.getWritableDatabase();

        ContentValues Values = new ContentValues();
        Values.put("title", title);
        Values.put("description", description);
        Values.put("startTime", startTime);


        long var1 = mydb.insert("NOTE_TABLE", null, Values);


        if (var1 == -1) {
            return false;
        } else {
            return true;
        }

    }

    // note all data get form database ===================================

    public Cursor getAllDataFromNote() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT * FROM NOTE_TABLE", null);
        return cursor1;
    }

    // note Update ===============================================

    public boolean updateNoteData(String id, String title, String des, String startTime) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("title", title);
        contentValues.put("description", des);
        contentValues.put("startTime", startTime);

        db.update("NOTE_TABLE", contentValues, "ID=?", new String[]{id});
        return true;
    }

    // delete note ==========================================

    public Integer deleteNoteById(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("NOTE_TABLE", "ID=?", new String[]{id});
    }

    // Wallet ====================== wallet ===========================================


    // Wallet ====================== wallet ===========================================


    // insert Data ---------------------todo-------------------
    public Boolean insertIncomeData(String title, String where, String time, Double product) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("location", where);
        contentValues.put("time", time);
        contentValues.put("amount", product);


        long var = db.insert("income", null, contentValues);


        if (var == -1) {
            return false;
        } else {
            return true;
        }

    }

    // Get all data form Wallet =====================================

    public Cursor getAllDataIcome() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor3 = db.rawQuery("SELECT * FROM income", null);
        return cursor3;
    }

    // Delete wallet income item by id =================================

    public Integer deleteIncomeById(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("income", "ID=?", new String[]{id});
    }

    // calculate Total expense ================================

    public Double calculateTotalInCome() {

        double totalIncome = 0;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from income", null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                double income = cursor.getDouble(4);
                totalIncome = totalIncome + income;
            }
        }

        return totalIncome;


    }


    // add data expense Table ================== expense =====================================

    public Boolean insertExpenseData(String title, String where, String time, Double product) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("location", where);
        contentValues.put("time", time);
        contentValues.put("amount", product);


        long var = db.insert("expense", null, contentValues);


        if (var == -1) {
            return false;
        } else {
            return true;
        }


    }


    public Cursor getAllDataExpense() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor4 = db.rawQuery("SELECT * FROM expense", null);
        return cursor4;
    }


    public Double calculateTotalExpense() {

        double totalIncome = 0;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from expense", null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                double income = cursor.getDouble(4);
                totalIncome = totalIncome + income;
            }
        }

        return totalIncome;


    }

    // delete expense form Database by id =======================

    public Integer deleteExpenseById(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("expense", "ID=?", new String[]{id});
    }

    // my Deram Table Create and Insert ==================================================

    public Boolean DreamDataInsert(String title, String description, byte[] image, String createDate, String endDate) {

        if (image != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            Log.d("DreamLog", "DreamDataInsert: " + image);

            ContentValues contentValues = new ContentValues();
            contentValues.put("title", title);
            contentValues.put("description", description);
            contentValues.put("image", image);
            contentValues.put("createDate", createDate);
            contentValues.put("endDate", endDate);

            long var = db.insert("dream", null, contentValues);


            if (var == -1) {
                return false;
            } else {
                return true;
            }

            // if image is null ==================================================
        }else{
            return false;
        }
    }
    // Get all data form dream =====================================

    public Cursor getAllDreamData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorDream = db.rawQuery("SELECT * FROM dream" , null);
        return cursorDream;
    }

    public Integer DeleteDreamById(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("dream", "ID=?", new String[]{id});
    }

}
