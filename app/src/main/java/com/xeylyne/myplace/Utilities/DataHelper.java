package com.xeylyne.myplace.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.Nullable;

import com.xeylyne.myplace.Models.Place;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class DataHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "place.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "tab_place";


    public static final String COL_1 = "ID_PLACE";
    public static final String COL_2 = "NAME_PLACE";
    public static final String COL_3 = "ADDRESS_PLACE";
    public static final String COL_4 = "FAV_FOOD";
    public static final String COL_5 = "AVG_PRICE";
    public static final String COL_6 = "REVIEW";
    public static final String COL_7 = "IMAGE";

    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "( ID_PLACE INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME_PLACE TEXT, " +
                "ADDRESS_PLACE TEXT, " +
                "FAV_FOOD TEXT, " +
                "AVG_PRICE INTEGER, " +
                "REVIEW TEXT," +
                "IMAGE BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Place> getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Place> accounts = new ArrayList<>();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        if (res.getCount() == 0) {
            Log.d("onGetAll", "DB Empty");
        } else {
            if (res.moveToFirst()) {
                do {
                    Place place = new Place();
                    place.setID_PLACE(res.getInt(0));
                    place.setNAME_PLACE(res.getString(1));
                    place.setADDRESS_PLACE(res.getString(2));
                    place.setFAV_FOOD(res.getString(3));
                    place.setAVG_PRICE(res.getString(4));
                    place.setREVIEW(res.getString(5));
                    place.setImage(res.getBlob(6));
                    accounts.add(place);
                } while (res.moveToNext());
            }
        }
        return accounts;
    }

    public boolean AddPlace(String name_place, String address_place, String fav_food, int avg_price, String review, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name_place);
        contentValues.put(COL_3, address_place);
        contentValues.put(COL_4, fav_food);
        contentValues.put(COL_5, avg_price);
        contentValues.put(COL_6, review);
        contentValues.put(COL_7, image);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            Log.d("onInsert", "GagalInsert");
            return false;
        } else {
            Log.d("onInsert", "Insert Success");
            return true;
        }
    }

    public boolean deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID_PLACE = " + id, null);
        return true;
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
