package com.example.mobikasa.login;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mobikasa on 6/6/16.
 */
public class signin extends Activity {
    TextView tv5;
    EditText et8;
    TextView tv8;
    TextView tv9;
    ListView lv;
    mydbhandler dbhandler = new mydbhandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        tv5 = (TextView) findViewById(R.id.tv5);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv9 = (TextView) findViewById(R.id.tv9);
        et8 = (EditText) findViewById(R.id.et8);
        lv  = (ListView) findViewById(R.id.lv);

        String name = getIntent().getStringExtra("username");
        tv5.setText(name);


    }


    public void showdetails(View view)
    {
        String username = et8.getText().toString();
        String arr[]= new String[2];
        arr= dbhandler.details(username);
        if(arr[0].equals("not found"))
        {
            Toast.makeText(signin.this,"Username not yet registered",Toast.LENGTH_SHORT).show();
        }
        else
        {
            tv8.setText(arr[0]);
            tv9.setText(arr[1]);
            populatelist(username);
        }
    }

    public void populatelist(String username){
        Cursor cursor= dbhandler.getsinglerow(username);
        String[] fields = new String[] {mydbhandler.column_id,mydbhandler.column_name,mydbhandler.column_age};
        int[] values = new int[] {R.id.tvid,R.id.tvname,R.id.tvage};

        SimpleCursorAdapter mycursoradapter;
        mycursoradapter = new SimpleCursorAdapter(this,R.layout.single_row,cursor,fields,values,0);
        lv.setAdapter(mycursoradapter);
    }



}
