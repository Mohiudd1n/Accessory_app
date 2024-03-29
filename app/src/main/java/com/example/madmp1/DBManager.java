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
        db.execSQL("create Table signupdetails (name TEXT,email TEXT primary key,pass TEXT,state TEXT)");
        db.execSQL("create Table cartdetails (useremail TEXT,description TEXT,quantity INTEGER,amount INTEGER)");
        db.execSQL("create Table orderdetails (useremail TEXT,description TEXT,quantity INTEGER,amount INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists signupdetails");
        db.execSQL("drop Table if exists cartdetails");
        db.execSQL("drop Table if exists orderdetails");
        onCreate(db);
    }

    public void update_quantity(String desc, String email,int quantity, int amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("quantity",quantity);
        values.put("amount",amount);

        // Updating row
        db.update("cartdetails", values,  "useremail = ? and description = ?", new String[]{email,desc});
        db.close();
    }

    public boolean check_item(String desc, String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from cartdetails where description = ? and useremail = ?", new String[]{desc,email});
        if(cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public void remove_product(String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("cartdetails","description=?",new String[]{desc});
    }

    public String get_email(){
        String state = "active";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from signupdetails where state = ?", new String[]{state});
        if(cursor.getCount()>0){
            cursor.moveToNext();
            String email = cursor.getString(1);
            return email;
        }else{
            return null;
        }
    }

    public String get_name(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select name from signupdetails where email = ?", new String[]{email});
        if(cursor.getCount()>0){
            cursor.moveToNext();
            String email1 = cursor.getString(0);
            return email1;
        }else{
            return null;
        }
    }

    public void insert_order(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "INSERT INTO orderdetails (useremail, description, quantity, amount) " +
                "SELECT useremail, description, quantity, amount " +
                "FROM cartdetails where useremail = ?";
        db.execSQL(sqlQuery,new String[]{email});
    }
    public void clear_cart(String email){
        String deleteQuery = "DELETE FROM cartdetails WHERE useremail = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(deleteQuery,new String[]{email});
    }
    public void logout(){
        String state_toadd = "inactive";
        String state_toremove = "active";

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("state",state_toadd);

        // Updating row
        db.update("signupdetails", values,  "state = ?", new String[]{state_toremove});
        db.close();
    }

    public boolean checklogin(){
        String state = "active";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from signupdetails where state = ?", new String[]{state});
        if (cursor.getCount()>0){return true;}else{return false;}
    }

    public void addstate(String email){
        String state = "active";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("state",state);

        // Updating row
        db.update("signupdetails", values,  "email = ?", new String[]{email});
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
    public boolean inputdetails(String name,String email,String pass,String state){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("email",email);
        cv.put("pass",pass);
        cv.put("state",state);

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

    public Cursor fetch_orders(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from orderdetails where useremail = ?", new String[]{email});
//        db.close();
        return cursor;
    }
}
