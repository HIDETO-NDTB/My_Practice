package com.example.mypractice.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DbHelper {

    public DbManager dbManager;
    public SQLiteDatabase sqLiteDatabase;
    public Context context;

    public DbHelper(Context context) {
        this.context = context;
    }


    public DbHelper open() throws SQLException {
        dbManager = new DbManager(context);
        sqLiteDatabase = dbManager.getWritableDatabase();
        return this ;
    }

    public void close(){
        dbManager.close();
    }

    public void insert(String name, Integer age, String gender, String subject) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbManager.NAME,name);
        contentValues.put(DbManager.AGE,age);
        contentValues.put(DbManager.GENDER,gender);
        contentValues.put(DbManager.SUBJECT,subject);

        sqLiteDatabase.insert(DbManager.TABLE_NAME,null,contentValues);

    }

    public Cursor fetch() {
        String[] columns = new String[]{DbManager._ID, DbManager.NAME, DbManager.AGE, DbManager.GENDER, DbManager.SUBJECT};
        Cursor cursor = sqLiteDatabase.query(DbManager.TABLE_NAME,columns,null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, Integer age, String gender, String subject) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbManager.NAME, name);
        contentValues.put(DbManager.AGE, age);
        contentValues.put(DbManager.GENDER, gender);
        contentValues.put(DbManager.SUBJECT, subject);

        int i = sqLiteDatabase.update(DbManager.TABLE_NAME, contentValues, DbManager._ID+"="+_id,null);
        return i;
    }

    public void delete(long _id) {
        sqLiteDatabase.delete(DbManager.TABLE_NAME, DbManager._ID+"="+_id,null);
    }
}
