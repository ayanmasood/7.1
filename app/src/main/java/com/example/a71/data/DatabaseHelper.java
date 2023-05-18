package com.example.a71.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.a71.model.Advert;
import com.example.a71.util.Util;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

         String CREATE_ADVERT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                 + Util.NAME + " TEXT, " + Util.PHONE + " INTEGER, " + Util.DESCRIPTION + " TEXT, " + Util.DATE + " TEXT, " + Util.LOCATION + " TEXT)";

            sqLiteDatabase.execSQL(CREATE_ADVERT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_USER_TABLE = "DROP TABLE IF EXISTS";
        sqLiteDatabase.execSQL(DROP_USER_TABLE,new String[] {Util.TABLE_NAME});

        onCreate(sqLiteDatabase);

    }
    //adding advert to database
    public long insertAdvert(Advert advert){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NAME, advert.getName());
        contentValues.put(Util.PHONE, advert.getPhone());
        contentValues.put(Util.DESCRIPTION, advert.getDescription());
        contentValues.put(Util.DATE, advert.getDate());
        contentValues.put(Util.LOCATION, advert.getLocation());
        long newRowId= db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }
    public void deleteAdvert(String description) {
        //make it writeable
        SQLiteDatabase db = this.getWritableDatabase();
        //delete matching advert with description
        db.delete(Util.TABLE_NAME, Util.DESCRIPTION + " = ?", new String[]{description});
        db.close();
    }
    public Advert fetchAdvertDes(String description) {
        SQLiteDatabase db = this.getReadableDatabase();
        //get all the columns of the selected row and add to variables
        String[] columns = {Util.USER_ID, Util.NAME, Util.PHONE, Util.DESCRIPTION,Util.DATE, Util.LOCATION};
        String selection = Util.DESCRIPTION + " = ?";
        String[] selectionArgs = {description};
        Cursor cursor = db.query(Util.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        Advert advert = null;
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int phone = Integer.parseInt(cursor.getString(2));
            String desc = cursor.getString(3);
            String date = cursor.getString(4);
            String location = cursor.getString(5);
            Log.d("Advert", "Retrieved values: " + id + ", " + name + ", " + phone + ", " + desc + ", " + date + ", " + location);
            advert = new Advert(id, name, phone, desc, date, location);
        } else {
            Log.d("Advert", "No matching row found for description: " + description);
        }

        cursor.close();
        db.close();
        return advert;
    }
    //fetching the advert to see if it exists
    public boolean fetchAdvert (String name, String phone, String description, String location){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.USER_ID}, Util.NAME + "=? and " + Util.PHONE + "=? and " + Util.DESCRIPTION + "=? and " + Util.LOCATION + "=?",
                new String[]{name,phone,description,location}, null,null,null  );

        int numberOfRows= cursor.getCount();
        db.close();

        if (numberOfRows > 0)
            return true;
        else
            return false;

    }
    public Cursor viewData(){
        SQLiteDatabase db =this.getReadableDatabase();
        String query = "Select * from "+Util.TABLE_NAME;
        Cursor cursor =db.rawQuery(query,null);

        return cursor;


    }

}
