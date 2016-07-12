package com.example.mobikasa.login;

/**
 * Created by mobikasa on 6/6/16.
 */
public class Contact {

    private int id,_age;
    private String _name,_uname,_password;

    public int get_id() {
        return id;
    }

    public void set_id() {

    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public String getUname() {
        return _uname;
    }

    public void setUname(String uname) {
        this._uname = uname;
    }

    public int getAge() {
        return _age;
    }

    public void setAge(int age) {
        this._age = age;
    }
}
