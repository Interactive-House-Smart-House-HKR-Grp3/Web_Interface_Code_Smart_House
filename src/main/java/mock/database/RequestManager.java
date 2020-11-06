package main.java.mock.database;

import main.java.mock.DBInterface;
import main.java.mock.Data;
import main.java.mock.data.Device;
import main.java.mock.data.User;

import java.util.List;

public class RequestManager implements DBInterface {

    private final Data data;

    public RequestManager(){
        data = new Data();
    }

    @Override
    public User authenticateUser(String name, String password) {
        for (User u : data.getUsers()){
            if (name.equals(u.name) && password.equals(u.password)){
                return u;
            }
        }
        return null;
    }

    @Override
    public User registerNewUser(String name, String password) {
        return data.addNewUser(name, password);
    }

    @Override
    public boolean isUserTaken(String name) {
        for (User users : data.getUsers()){
            if (users.name.equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Device> getDevices() {
        return data.getMockDevices();
    }
}
