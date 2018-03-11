package com.example.n.simplediary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.n.simplediary.Diary.DiaryContent;
import com.example.n.simplediary.Diary.DiaryContent.DiaryItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by n on 18/03/11.
 */

public class DiaryDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "simple_diary.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "DIARY";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DIARY_YEAR = "year";
    public static final String COLUMN_DIARY_MONTH = "month";
    public static final String COLUMN_DIARY_DAY = "day";
    public static final String COLUMN_DIARY_CONTENT = "image";


    public DiaryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DIARY_YEAR + " INTEGER NOT NULL, " +
                COLUMN_DIARY_MONTH + " INTEGER NOT NULL, " +
                COLUMN_DIARY_DAY + " INTEGER NOT NULL, " +
                COLUMN_DIARY_CONTENT + " TEXT NOT NULL, " +
                "UNIQUE(" +
                COLUMN_DIARY_YEAR + ", "  +
                COLUMN_DIARY_MONTH + ", " +
                COLUMN_DIARY_DAY + ") ON CONFLICT REPLACE);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    /**
     * create record
     **/
    public void saveNewDiary(DiaryItem diary) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DIARY_YEAR, diary.year);
        values.put(COLUMN_DIARY_MONTH, diary.month);
        values.put(COLUMN_DIARY_DAY, diary.day);
        values.put(COLUMN_DIARY_CONTENT, diary.content);

        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Query records, give options to filter results
     **/
    public List<DiaryItem> diaryList(String filter) {
        String query;
        if (filter.equals("")) {
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME;
        } else {
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " + filter;
        }

        List<DiaryItem> diaryItemLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        DiaryItem diary;

        if (cursor.moveToFirst()) {
            do {
                diary = new DiaryItem();

                diary.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                diary.year = cursor.getInt(cursor.getColumnIndex(COLUMN_DIARY_YEAR));
                diary.month = cursor.getInt(cursor.getColumnIndex(COLUMN_DIARY_MONTH));
                diary.day = cursor.getInt(cursor.getColumnIndex(COLUMN_DIARY_DAY));
                diary.content = cursor.getString(cursor.getColumnIndex(COLUMN_DIARY_CONTENT));
                diaryItemLinkedList.add(diary);
            } while (cursor.moveToNext());
        }

        return diaryItemLinkedList;
    }

    /**
     * Query only 1 record
     **/
    public DiaryItem getDiaryItem(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE _id=" + id;
        Cursor cursor = db.rawQuery(query, null);

        DiaryItem receivedDiary = new DiaryItem();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedDiary.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
            receivedDiary.year = cursor.getInt(cursor.getColumnIndex(COLUMN_DIARY_YEAR));
            receivedDiary.month = cursor.getInt(cursor.getColumnIndex(COLUMN_DIARY_MONTH));
            receivedDiary.day = cursor.getInt(cursor.getColumnIndex(COLUMN_DIARY_DAY));
            receivedDiary.content = cursor.getString(cursor.getColumnIndex(COLUMN_DIARY_CONTENT));
        }


        return receivedDiary;
    }

    /**
     * delete record
     **/
    public void deleteDiaryRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE _id='" + id + "'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();
    }

    /**
     * update record
     **/
    public void updateDiaryRecord(long id, Context context, DiaryItem updatedDiary) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  " + TABLE_NAME +
                " SET "+ COLUMN_DIARY_YEAR + " ='" + updatedDiary.year +
                "', " + COLUMN_DIARY_MONTH + " ='" + updatedDiary.month +
                "', " + COLUMN_DIARY_DAY+ " ='" + updatedDiary.day +
                "', " + COLUMN_DIARY_CONTENT + " ='" + updatedDiary.content +
                "'  WHERE _id='" + id + "'");
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();
    }
}


