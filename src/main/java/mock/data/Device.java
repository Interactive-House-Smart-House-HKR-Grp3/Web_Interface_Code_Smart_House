package main.java.mock.data;

import java.io.Serializable;

public class Device implements Serializable {
    public String type;
    public String info;

    public Device(){

    }

    public Device(String type, String info){
        this.type = type;
        this.info = info;
    }
}
