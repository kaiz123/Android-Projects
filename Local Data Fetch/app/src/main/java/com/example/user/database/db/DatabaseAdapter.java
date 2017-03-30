package com.example.user.database.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by user on 8/6/2016.
 */
public class DatabaseAdapter extends ContentProvider {
    private static final String name="userdb";
    private static final int version=2;
    SQLiteDatabase database;
    String user="username",pass="password",id="id",tablename="userdata";
    public DatabaseAdapter(){

    }
        class Dbhelper extends SQLiteOpenHelper{


            public Dbhelper(Context context) {
                super(context, name, null, version);


            }

            @Override
            public void onCreate(SQLiteDatabase database) {
                String query=String.format("create table %s( %s text not null, %s text not null,  %s text not null)",tablename,user,pass,id);
            database.execSQL(query);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        }
    @Override
    public boolean onCreate() {
        SQLiteOpenHelper dbhelper= new Dbhelper(getContext());
        database=dbhelper.getWritableDatabase();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

         return database.query(tablename,projection,selection,selectionArgs,null,null,sortOrder);

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
       long a= database.insert(tablename,null,values);

        return Uri.withAppendedPath(uri,a+"");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
