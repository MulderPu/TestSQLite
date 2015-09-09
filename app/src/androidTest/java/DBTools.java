package com.mulder.contactsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mulder on 9/9/15.
 */

public class DBTools extends SQLiteOpenHelper{

    public DBTools(Context applicationContext)
    {
        super(applicationContext, "contactbook.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
