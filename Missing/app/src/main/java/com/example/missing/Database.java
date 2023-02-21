package com.example.missing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public static final String DBname ="data.db";

    public Database(Context context) {
        super(context, DBname, null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mytable (id integer primary key AUTOINCREMENT, name TEXT, age TEXT,phone TEXT,N_ID TEXT,email TEXT,date TEXT,location TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL ("DROP TABLE IF EXISTS mytable");
        onCreate (db);
    }

    public boolean insertData (String name,String age, String phone,String n_id,String email,String date,String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("phone", phone);
        contentValues.put("n_id", n_id);
        contentValues.put("email", email);
        contentValues.put("date", date);
        contentValues.put("location", location);

        long result = db.insert("mytable", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList getallrecordes()
    {
        ArrayList arr = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from mytable" ,null);
        res.moveToFirst();
        while (res.isAfterLast() == false)
        {
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
            String t4 = res.getString(3);
            String t5 = res.getString(4);
            String t6 = res.getString(5);
            String t7 = res.getString(6);
            String t8 = res.getString(7);

            arr.add(t1 + " - " + t2 +"\n\t\t\t\t" +t3+"\n\t\t\t\t" +t4+"\n\t\t\t\t" +t5+"\n\t\t\t\t" +t6+"\n\t\t\t\t" +t7+"\n\t\t\t\t" +t8);
            res.moveToNext();
        }
        return arr;
    }
}
