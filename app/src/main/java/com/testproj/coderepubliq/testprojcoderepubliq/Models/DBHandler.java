package com.testproj.coderepubliq.testprojcoderepubliq.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diodelgerona on 03/05/2017.
 */

public class DBHandler extends SQLiteOpenHelper{
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "dbEntries";
    //Table Name
    private static final String TABLE_ENTRY = "entry";

    //Columns
    private static final String KEY_ID = "id";
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ENTRY_TABLE = "CREATE TABLE " + TABLE_ENTRY + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRST_NAME + " TEXT," + KEY_LAST_NAME + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_ENTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST" + TABLE_ENTRY);
    }
    //Add Entry
    public void addSubject(Entry entry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, entry.firstName);
        values.put(KEY_LAST_NAME, entry.lastName);

        // Inserting Row
        db.insert(TABLE_ENTRY, null, values);
        db.close(); // Closing database connection
    }
    //GET ALL ENTRY
    public List<Entry> getEntries() {
        List<Entry> entries = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ENTRY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Entry entry = new Entry();
                entry.setId(cursor.getInt(cursor.getColumnIndex("id")));
                entry.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
                entry.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));

        // Adding entry to list
                entries.add(entry);
            } while (cursor.moveToNext());
        }

        // return entries
        return entries;
    }
    //Update Entry
    public int updateEntry(int id,Entry entry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, entry.firstName);
        values.put(KEY_LAST_NAME, entry.lastName);

// updating row
        return db.update(TABLE_ENTRY, values, KEY_ID + "= ?",
                new String[]{String.valueOf(id)});
    }
    //Delete Entry
    public void deleteEntry(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ENTRY, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
}
