package com.example.mobikasa.login;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by mobikasa on 6/6/16.
 */
public class mydbhandler extends SQLiteOpenHelper{


    private static final int database_verion=1;
    private static final String database_name="contact.db";
    public static final String table_name="contact";
    public static final String column_id="_id";
    public static final String column_name="name";
    public static final String column_uname="uname";
    public static final String column_password="password";
    public static final String column_age="age";




    public mydbhandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, database_name, factory, database_verion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE " + table_name + "("
                + column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + column_name + " TEXT, "
                + column_uname + " TEXT, " +  column_password + " TEXT, " + column_age + " TEXT " + ");";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    //add product to the database

    public void addcontact(Contact contact){

        SQLiteDatabase db= getWritableDatabase();
        ContentValues values= new ContentValues();
        //values.put(column_id,contact.get_id());
        values.put(column_name,contact.getName());
        values.put(column_uname,contact.getUname());
        values.put(column_password,contact.getPassword());
        values.put(column_age,contact.getAge());
        db.insert(table_name,null,values);
        db.close();
    }

    public Cursor getsinglerow(String usrname)
    {
        SQLiteDatabase db= getWritableDatabase();
        String query= "SELECT _id, name, age FROM " + table_name + " WHERE " + column_uname + "=\"" + usrname + "\";";
        Cursor c= db.rawQuery(query,null);
        if(c!=null)
        {
            c.moveToFirst();
        }
        return c;
    }



    public String[] details(String usrname){

        SQLiteDatabase db= getReadableDatabase();
        String query= "SELECT name, age FROM " + table_name + " WHERE " + column_uname + "=\"" + usrname + "\";";
        String ar[]= new String[2];
        ar[0]="not found";
        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            if(cursor.getString(0)!=null && cursor.getString(1)!=null)
            {
                String d, e;
                d = cursor.getString(0);
                e = cursor.getString(1);
                ar[0] = d;
                ar[1] = e;
                return ar;
            }
        }

        return ar;
    }

    public String search(String username){

    SQLiteDatabase db= getWritableDatabase();
    String query= "SELECT name FROM " + table_name + " WHERE " + column_uname + "=\"" + username + "\";";
    Cursor cursor= db.rawQuery(query,null);
    String c;
    if(cursor.moveToFirst())
    {
        if(cursor.getString(0)!=null)
        {
            c=cursor.getString(0);
            return c;
        }
    }
        return "Not Found";
    }



    public String searchpass(String username)
    {
        SQLiteDatabase db= getReadableDatabase();
        String query= "SELECT uname, password FROM " + table_name;
        Cursor cursor= db.rawQuery(query, null);
        String a,b;
        b= "Password Not Found";

        if(cursor.moveToFirst())
        {

            do {
                a=cursor.getString(0);
                if(a.equals(username))
                {
                    b=cursor.getString(1);
                    break;
                }

            }
            while (cursor.moveToNext());

        }

        return b;

    }



}
