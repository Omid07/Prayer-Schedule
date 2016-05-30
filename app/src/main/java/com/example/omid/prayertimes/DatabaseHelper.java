package com.example.omid.prayertimes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by omid on 5/27/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "prayer.db";
    private static final String TABLE_NAME = "prayer_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "Date";
    private static final String COL_3 = "Temperature";
    private static final String COL_4 = "QiblaDirection";
    private static final String COL_5 = "Fajr";
    private static final String COL_6 = "Dhuhr";
    private static final String COL_7 = "Asr";
    private static final String COL_8 = "Maghrib";
    private static final String COL_9 = "Isha";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, Date STRING, Temperature STRING, QiblaDirection STRING, Fajr STRING, Dhuhr STRING, Asr STRING, Maghrib STRING, Isha STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(ArrayList<PrayerTime> list){
        long result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(int i=0; i<list.size(); i++){
            Log.e("datalist",list.size()+"");
            contentValues.put(COL_2,list.get(i).getDateFor());
            contentValues.put(COL_3,list.get(i).getTemp());
            contentValues.put(COL_4,list.get(i).getDirection());
            contentValues.put(COL_5,list.get(i).getFajr());
            contentValues.put(COL_6,list.get(i).getDhuhr());
            contentValues.put(COL_7,list.get(i).getAsr());
            contentValues.put(COL_8,list.get(i).getMaghrib());
            contentValues.put(COL_9,list.get(i).getIsha());
            result = db.insert(TABLE_NAME, null, contentValues);
        }
        if(result == 0){
            return false;
        } else {
            return true;
        }
//        Log.e("resilt: ",result);
    }
}
