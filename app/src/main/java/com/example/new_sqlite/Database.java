package com.example.new_sqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class Database extends SQLiteOpenHelper {

    private static final String DATABASE = "Database.db";
    private static final String TABLE = "Database_Table";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String AGE = "age";

    Database(@Nullable Context context) {
        super( context, DATABASE, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "create table " + TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, AGE INTEGER)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + TABLE );
//        onCreate( sqLiteDatabase );
    }

    boolean add_data(String Name, String Email, String Age) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( "Name", Name );
        contentValues.put( "Email", Email );
        contentValues.put( "Age", Age );
        long result = sqLiteDatabase.insert( TABLE, null, contentValues );
        return result > 0;
    }
    
    boolean check_data(String Name, String Email, String Age  ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {ID};
        String selction = NAME + "=?" + " AND " + EMAIL + "=?" + " AND " + AGE + "=?";
        String[] selectionArgs = {Name, Email, Age};
        
        Cursor cursor = sqLiteDatabase.query( TABLE, columns, selction, selectionArgs,
                                              null, null, null);
        
        int cur = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        return cur > 0;
    }

    Cursor view_data() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//                                          "select *(all data) from " + TABLE, 
        return sqLiteDatabase.rawQuery( "select * from " + TABLE, null );
    }

    boolean update_data(String ID, String Name, String Email, String Age) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( "ID", ID );
        contentValues.put( "Name", Name );
        contentValues.put( "Email", Email );
        contentValues.put( "Age", Age );
        sqLiteDatabase.update( TABLE, contentValues, "ID = ?", new String[]{ID} );
        return true;
    }

    Integer delete_data(String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete( TABLE, "ID = ?", new String[]{ID} );
    }
}