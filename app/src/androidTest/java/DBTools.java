package com.mulder.contactsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

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
        String query = "CREATE TABLE contacts (contactId INTEGER PRIMARY KEY, firstName TEXT, "+
                "lastName TEXT, phoneNumber TEXT, emailAddress TEXT, homeAddress TEXT)";

        db.execSQL(query);
    }

    //use to drop table, add table ,delete table, pretty much anything
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXIST Contacts";

        db.execSQL(query);
        onCreate(db);
    }

    public void insertContact(HashMap<String, String> queryValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("firstName",queryValues.get("firstName"));
        values.put("lastName",queryValues.get("lastName"));
        values.put("phoneNumber",queryValues.get("phoneNumber"));
        values.put("emailAddress",queryValues.get("emailAddress"));
        values.put("homeAddress",queryValues.get("homeAddress"));

        db.insert("Contacts", null, values);
        db.close();
    }

    public int updateContact(HashMap<String, String> queryValues){

        //Create new database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("firstName",queryValues.get("firstName"));
        values.put("lastName",queryValues.get("lastName"));
        values.put("phoneNumber",queryValues.get("phoneNumber"));
        values.put("emailAddress", queryValues.get("emailAddress"));
        values.put("homeAddress", queryValues.get("homeAddress"));

        return db.update("Contacts", values, "contactId"+"=?", new String[]{queryValues.get("contactId")});
    }

    public void deleteContact(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM contacts WHERE contactId ='" + id + "'";

        db.execSQL(deleteQuery);
    }

    public ArrayList<HashMap<String, String>> getAllContacts(){

        ArrayList<HashMap<String, String>> contactArrayList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT * FROM contacts";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){

            do{
                HashMap<String, String> contactMap = new HashMap<String, String>();

                contactMap.put("contactId", cursor.getString(0));
                contactMap.put("firstName", cursor.getString(1));
                contactMap.put("lastName", cursor.getString(2));
                contactMap.put("phoneNumber", cursor.getString(3));
                contactMap.put("emailAddress", cursor.getString(4));
                contactMap.put("homeAddress", cursor.getString(5));

                contactArrayList.add(contactMap);

            }while (cursor.moveToNext());
        }

        return contactArrayList;
    }

    public HashMap<String, String> getContactInfo(String id){

        HashMap<String, String> contactMap = new HashMap<String, String>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM contacts WHERE contactId = '" + id + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){

            do{

                contactMap.put("contactId", cursor.getString(0));
                contactMap.put("firstName", cursor.getString(1));
                contactMap.put("lastName", cursor.getString(2));
                contactMap.put("phoneNumber", cursor.getString(3));
                contactMap.put("emailAddress", cursor.getString(4));
                contactMap.put("homeAddress", cursor.getString(5));

            }while (cursor.moveToNext());
        }

        return contactMap;
    }
}
