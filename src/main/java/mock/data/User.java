package main.java.mock.data;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    // Used to test authentication
    public String name, password;
    // Devices attached to the user
    public List<Device> devices;

    public User(){

    }

    public User(String name, String password, List<Device> devices){
        this.name = name;
        this.password = password;
        this.devices = devices;
    }
}
