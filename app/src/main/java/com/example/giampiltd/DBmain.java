package com.example.giampiltd;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class DBmain extends SQLiteOpenHelper {

    protected Context context;
    private static final String DBFILE = "giampidb4.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "shoestore_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NUMBER_SIZE = "number_size";
    private static final String COLUMN_LENGTH_SIZE = "length_size";
    private static final String COLUMN_UPPER_MATERIAL = "upper_material";
    private static final String COLUMN_OUTSOLE_MATERIAL = "outsole_material";
    private static final String COLUMN_PRICE = "price";



    public DBmain(@Nullable Context context) {
        super(context, DBFILE, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME +" TEXT, " +
                COLUMN_NUMBER_SIZE + " INTEGER, " +
                COLUMN_LENGTH_SIZE + " INTEGER, " +
                COLUMN_UPPER_MATERIAL + " TEXT, " +
                COLUMN_OUTSOLE_MATERIAL + " TEXT, " +
                COLUMN_PRICE + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addShoe(String name, int numberSize, int lengthSize, String upperMaterial,
                 String outsoleMaterial, int price) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_NUMBER_SIZE, numberSize);
        cv.put(COLUMN_LENGTH_SIZE, lengthSize);
        cv.put(COLUMN_UPPER_MATERIAL, upperMaterial);
        cv.put(COLUMN_OUTSOLE_MATERIAL, outsoleMaterial);
        cv.put(COLUMN_PRICE, price);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("Recycle")
    public Cursor getAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void UpdateData(String row_id, String name, String numberSize, String lengthSize, String upperMaterial,
                    String outsoleMaterial, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_NUMBER_SIZE, numberSize);
        cv.put(COLUMN_LENGTH_SIZE, lengthSize);
        cv.put(COLUMN_UPPER_MATERIAL, upperMaterial);
        cv.put(COLUMN_OUTSOLE_MATERIAL, outsoleMaterial);
        cv.put(COLUMN_PRICE, price);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

    void DeleteRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

}
