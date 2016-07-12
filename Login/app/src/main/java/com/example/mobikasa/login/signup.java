package com.example.mobikasa.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by mobikasa on 6/6/16.
 */
public class signup extends Activity{

    EditText et3;
    EditText et4;
    EditText et5;
    EditText et6;
    EditText et8;
    mydbhandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        et5 = (EditText) findViewById(R.id.et5);
        et6 = (EditText) findViewById(R.id.et6);
        et8 = (EditText) findViewById(R.id.et8);
        et8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int age = Integer.parseInt(s.toString());
                    if (age > 60) {
                        s.replace(0, s.length(), "60");
                    }
                } catch (NumberFormatException e){}

            }
        });
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence seq, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence seq, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable seq) {
                String pass= et5.getText().toString();
                if(pass.length()>16){
                    et5.setError("excedded");
                }

            }
        });


    }

    public void signupclick(View view) {

        String name = et3.getText().toString();
        String username = et4.getText().toString();
        String pass = et5.getText().toString();
        String confirmpass = et6.getText().toString();
        String age = et8.getText().toString();

        dbhandler = new mydbhandler(this, null, null, 1);

        if (!pass.equals(confirmpass)) {
            Toast.makeText(this, "Password DO Not Match", Toast.LENGTH_SHORT).show();
        } else if (pass.equals(confirmpass)) {
            Contact contact = new Contact();
            //contact.set_id();
            contact.setName(name);
            contact.setUname(username);
            contact.setPassword(pass);
            contact.setAge(Integer.parseInt(age));

            dbhandler.addcontact(contact);

//            signin object= new signin();
//            object.populatelist();
            Intent i = new Intent(signup.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(signup.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
        }

    }
}



