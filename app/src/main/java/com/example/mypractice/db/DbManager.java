package com.example.mypractice.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbManager extends SQLiteOpenHelper {

    //DB名とバージョン
    public static final String DB_NAME = "StudentsDB";
    public static final int DB_VERSION = 1;

    //Table名
    public static final String TABLE_NAME = "STUDENTS";

    //カラム
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final String SUBJECT = "subject";

    //Table作成
    private static final String CREATE_TABLE = "create table "+TABLE_NAME+
            "(" + _ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME+ " TEXT NOT NULL,"
                + AGE+ " INTEGER,"
                + GENDER+ " TEXT,"
                + SUBJECT+ " TEXT"
            +");";


    public DbManager(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
