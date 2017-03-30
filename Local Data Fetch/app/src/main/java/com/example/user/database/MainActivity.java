package com.example.user.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText t;
    EditText t1;
    EditText t2;
    String user="username",pass="password",id="id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t=(EditText)findViewById(R.id.editText);
         t1=(EditText)findViewById(R.id.editText2);
        t2=(EditText)findViewById(R.id.editText3);

    }
    public void fun(View v){
        String s=t.getText().toString();
        String s1=t1.getText().toString();
        String s2=t2.getText().toString();
        if(!s1.equals("")&&!s2.equals("")&&!s.equals("")){
            ContentValues val=new ContentValues();
            val.put(user,s);
            val.put(pass,s1);
            val.put(id, s2);
            ContentResolver contentResolver=getContentResolver();
            Uri uri=contentResolver.insert(Uri.parse("content://com.example.user.database.db.DatabaseAdapter/user"), val);
            Toast.makeText(this,uri.toString(),Toast.LENGTH_LONG).show();

        }



    }
    public  void fun1(View v1){
        ContentResolver contentResolver=getContentResolver();
        Uri uri1= Uri.parse("content://com.example.user.database.db.DatabaseAdapter/user/id");
        Cursor c=contentResolver.query(uri1, null, null, null, null);
       int useri= c.getColumnIndex(user);
        int passi=c.getColumnIndex(pass);
        int idi=c.getColumnIndex(id);
        while(c.moveToNext()){
            String display=c.getString(useri)+"\t"+c.getString(passi)+"\t"+c.getString(idi)+"\n";
            Toast.makeText(this,display,Toast.LENGTH_LONG).show();
        }
    }
}
