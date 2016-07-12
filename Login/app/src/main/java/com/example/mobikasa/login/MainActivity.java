package com.example.mobikasa.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;
    mydbhandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1= (EditText) findViewById(R.id.et1);
        et2= (EditText) findViewById(R.id.et2);

        dbhandler= new mydbhandler(this,null,null,1);

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                String pass= et2.getText().toString();
                if(pass.length()>16){
                    et2.setError("excedded");
            }
        }
            });
    }

    public void onclickbutton(View view)
    {

        if(view.getId() == R.id.bt1)
        {


            String name = et1.getText().toString();
            String password = et2.getText().toString();
            String pass= dbhandler.searchpass(name);

            String mainname= dbhandler.search(name);

            if(!validate_uname(name))
            {
                et1.setError("username not valid");
            }

            if(!validate_password(password))
            {
             et2.setError("password not valid");
            }


            if(password.equals(pass) && !mainname.equals("Not Found"))
            {

                Intent i = new Intent(MainActivity.this, signin.class);
                i.putExtra("username", mainname);
                startActivity(i);
            }
            else
            {
                Toast.makeText(this,"Username and password do not match",Toast.LENGTH_SHORT).show();
            }
        }


        else if(view.getId()==R.id.bt2)
        {
            Intent i = new Intent(MainActivity.this, signup.class);
            startActivity(i);

        }
    }

    private boolean validate_password(String password)
    {
        if(password.length()>8 && password.length()<16)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean validate_uname(String uname)
    {
        String email_pattern= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern patern= Pattern.compile(email_pattern);
        Matcher matcher= patern.matcher(uname);

        return matcher.matches();
    }

}
