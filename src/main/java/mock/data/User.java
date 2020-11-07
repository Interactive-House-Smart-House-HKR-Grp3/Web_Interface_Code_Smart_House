package main.java.mock.data;

import java.io.Serializable;

public class User implements Serializable {
    // Used to test authentication
    public String name, password;

    public User(){

    }

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
}
