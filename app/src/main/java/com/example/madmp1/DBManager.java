package com.example.madmp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

//    public static final String databasename="signupdetails";

    public DBManager(Context context) {
        super(context, "ShoppingApp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table signupdetails (name TEXT,email TEXT primary key,pass TEXT)");
//        db.execSQL("drop Table cartdetails");
        db.execSQL("create Table cartdetails (useremail TEXT,description TEXT,quantity INTEGER,amount INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists signupdetails");
        db.execSQL("drop Table if exists cartdetails");
        onCreate(db);
    }

    public boolean addproduct(String email, String desc, int quantity, int amount){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("useremail",email);
        cv.put("description",desc);
        cv.put("quantity",quantity);
        cv.put("amount",amount);
        long result = DB.insert("cartdetails",null,cv);

        //return !(result == -1);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean inputdetails(String name,String email,String pass){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("email",email);
        cv.put("pass",pass);

        long result = DB.insert("signupdetails",null,cv);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }

    }

    public boolean checkemail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from signupdetails where email = ?", new String[]{email});

        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkemailpassword(String email,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from signupdetails where email = ? and pass= ?", new String[]{email,pass});

        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Cursor fetch_cart(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from cartdetails where useremail = ?", new String[]{email});
//        db.close();
        return cursor;
    }

}
