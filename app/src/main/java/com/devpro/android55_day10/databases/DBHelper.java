package com.devpro.android55_day10.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.devpro.android55_day10.models.Product;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "product.db";
    public static final int DB_VERSION = 1;
    public static final String PRODUCT_TABLE = "product";
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_TITLE = "product_title";
    public static final String PRODUCT_PRICE = "product_price";
    public static final String PRODUCT_DISCOUNT_PERCENT = "product_discount_percent";
    public static final String PRODUCT_BRAND = "product_brand";
    public static final String PRODUCT_DES = "product_des";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + PRODUCT_TABLE + " " +
                "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "" + PRODUCT_TITLE + " TEXT NOT NULL," +
                "" + PRODUCT_PRICE + " TEXT NOT NULL," +
                "" + PRODUCT_DISCOUNT_PERCENT + " TEXT NOT NULL ," +
                "" + PRODUCT_BRAND + " TEXT NOT NULL," +
                "" + PRODUCT_DES + " TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_TITLE, product.getTitle());
        contentValues.put(PRODUCT_PRICE, product.getPrice() + "");
        contentValues.put(PRODUCT_DISCOUNT_PERCENT, product.getDiscountPercentage() + "");
        contentValues.put(PRODUCT_BRAND, product.getBrand());
        contentValues.put(PRODUCT_DES, product.getDescription());

        sqLiteDatabase
                .insert(PRODUCT_TABLE, null, contentValues);
        sqLiteDatabase.close();
    }

    public void updateProduct(int id, Product product) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_TITLE, product.getTitle());
        contentValues.put(PRODUCT_PRICE, product.getPrice() + "");
        contentValues.put(PRODUCT_DISCOUNT_PERCENT, product.getDiscountPercentage() + "");
        contentValues.put(PRODUCT_BRAND, product.getBrand());
        contentValues.put(PRODUCT_DES, product.getDescription());

        sqLiteDatabase.update(PRODUCT_TABLE, contentValues, PRODUCT_ID + " =? ", new String[]{id + ""});
        sqLiteDatabase.close();
    }

    public void deleteProduct(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(PRODUCT_TABLE, PRODUCT_ID + " =? ", new String[]{id + ""});
        sqLiteDatabase.close();
    }

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> res = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "SELECT * FROM " + PRODUCT_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
//            int idIndex = cursor.getColumnIndex(PRODUCT_ID);
//            if (idIndex > -1){
//            }
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            Double price = Double.parseDouble(cursor.getString(2));
            Double discount = Double.parseDouble(cursor.getString(3));
            String brand = cursor.getString(4);
            String des = cursor.getString(5);

            Product product = new Product();
            product.setId(id);
            product.setTitle(title);
            product.setPrice(price);
            product.setDiscountPercentage(discount);
            product.setBrand(brand);
            product.setDescription(des);

            res.add(product);

            cursor.moveToNext();
        }

        sqLiteDatabase.close();
        return res;
    }
}
