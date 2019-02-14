package com.theolympian.alu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String batch, String branch ,String course) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.BATCH, batch);
        contentValue.put(DatabaseHelper.BRANCH, branch);
        contentValue.put(DatabaseHelper.COURSE, course);

        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper.BATCH, DatabaseHelper.BRANCH, DatabaseHelper.COURSE };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void update(String batch, String branch, String course)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.BATCH, batch);
        contentValues.put(DatabaseHelper.BRANCH, branch);
        contentValues.put(DatabaseHelper.COURSE, course);


    }


}

