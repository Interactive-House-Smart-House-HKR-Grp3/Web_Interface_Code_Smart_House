package main.java.mock;

import main.java.mock.data.Device;
import main.java.mock.data.User;

import java.util.ArrayList;
import java.util.List;

public class Data {
    // Mock class to fill data for local testing to see if everything is working correctly on our end without having to strain the remote DB.
    private static Data data;
    private User loggedInUser;

    List<User> users = new ArrayList<>();
    List<Device> deviceList = new ArrayList<>();

    public Data(){
        generateTestData();
    }

    private void generateTestData(){
        deviceList.add(new Device("", ""));
        deviceList.add(new Device("Door", "The door should have an on and off state..."));
        users.add(new User("test", "pass", deviceList));

        deviceList = new ArrayList<>();
        deviceList.add(new Device("Window", "The Window should have an on and off state..."));
        deviceList.add(new Device("Temp", "The temperature should be able to vary?"));
        users.add(new User("admin", "1234", deviceList));
    }

    public List<User> getUsers() { return users; }
}