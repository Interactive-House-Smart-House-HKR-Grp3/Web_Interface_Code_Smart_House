package main.java.mock;

import main.java.mock.data.Device;
import main.java.mock.data.User;

import java.util.List;

public interface DBInterface {
    User authenticateUser(String name, String password);
    User registerNewUser(String name, String password);
    boolean isUserTaken(String name);
    List<Device> getDevices();
}
