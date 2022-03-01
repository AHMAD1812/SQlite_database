package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DataBase_Name="UsersDetail";
    public static final String Table_Name="Users";
    public static final String Table_Col_1="ID";
    public static final String Table_Col_2="Name";
    public static final String Table_Col_3="Section";
    public static final String Table_Col_4="Address";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DataBase_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ Table_Name + "("+ Table_Col_1+ " INTEGER PRIMARY KEY AUTOINCREMENT , "+ Table_Col_2 + " Text , "+ Table_Col_3 +" Text , "+ Table_Col_4 + " Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + Table_Name);

        onCreate(sqLiteDatabase);
    }

    public boolean addData(userModel model){
        SQLiteDatabase mydb=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Table_Col_2,model.getName());
        values.put(Table_Col_3,model.getSection());
        values.put(Table_Col_4,model.getAddress());

        mydb.insert(Table_Name,null,values);

        mydb.close();
        return true;
    }

    public List<userModel> getAllUsers() {
        List<userModel> userList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Table_Name;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                userModel model=new userModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getString(3));
                userList.add(model);
            } while (cursor.moveToNext());
        }

        // return contact list
        return userList;
    }

    public int updateContact(userModel model,int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Table_Col_2, model.getName());
        values.put(Table_Col_3, model.getSection());
        values.put(Table_Col_4, model.getAddress());
        // updating row
        return db.update(Table_Name, values, Table_Col_1 + "= ?",
                new String[] { String.valueOf(id) });
    }

    public boolean deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_Name, Table_Col_1 + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
        return true;
    }

}
