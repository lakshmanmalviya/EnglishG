package com.example.englishg.SQLiteFile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper  extends SQLiteOpenHelper {
    final  static String DBNAME= "mydatabase.db";
    final static  int VERSION= 1;
    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTable = "create table orders ( id integer primary key autoincrement,name text,food text)";
        db.execSQL(creatTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists orders");
            onCreate(db);
    }
    public boolean insertOrder(String name,String foodName){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("food",foodName);
        long id = database.insert("orders",null,contentValues);
        if(id<=0)
            return  false;
        else
            return true;
    }
}
