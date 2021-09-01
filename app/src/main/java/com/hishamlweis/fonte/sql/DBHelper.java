package com.hishamlweis.fonte.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hishamlweis.fonte.model.User;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "UserManager.db";

    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean checkUser(String email) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " =?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}

//    public DBHelper(Context context) {
//        super(context, "Login.db", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase myDB) {
//        myDB.execSQL("create Table users(name TEXT, email TEXT primary key, mobile TEXT, password TEXT)");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
//        myDB.execSQL("drop Table if exists users");
//    }
//
//    public Boolean insertData(String name, String email, String mobile, String password) {
//        SQLiteDatabase myDB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("email", email);
//        contentValues.put("mobile", mobile);
//        contentValues.put("password", password);
//        long result = myDB.insert("users", null, contentValues);
//
//        if (result == -1) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    public Boolean checkEmail(String email) {
//        SQLiteDatabase myDB = this.getWritableDatabase();
//        Cursor cursor = myDB.rawQuery("select * from users where email = ?", new String[]{email});
//
//        if (cursor.getCount() > 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public Boolean checkEmailPassword(String email, String password) {
//        SQLiteDatabase myDB = this.getWritableDatabase();
//        Cursor cursor = myDB.rawQuery("select * from users where email = ? and password = ?", new String[]{email, password});
//
//        if (cursor.getCount() > 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//}
