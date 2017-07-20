package com.example.n.simplediary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by n on 17/07/20.
 */

public class DiaryOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "diary.db";
    private static final int DB_VERSION = 1;

    public DiaryOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL("create table if not exists diary_book("
                    + "_id integer primary key autoincrement not null,"
                    + "date text not null"
                    + "diary text);");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
