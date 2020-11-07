package main.java.mock;

import main.java.mock.data.Device;
import main.java.mock.data.User;

import java.util.List;

public interface DBInterface {
    User authenticateUser(String name, String password);
    List<Device> getDevices(User user);
}
