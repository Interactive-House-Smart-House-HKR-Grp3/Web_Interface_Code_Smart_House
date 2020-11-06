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
    List<Device> mockDevices = new ArrayList<>();

    public Data(){
        generateTestData();
    }

    private void generateTestData(){
        users.add(new User("test", "pass"));
        users.add(new User("admin", "1234"));

        // 4 formats provided to the JSP
        // 2 read only  -> one with a bool, one with a int
        // 2 write/read -> one with a toggle, one with an int input

        // Only readable devices
        mockDevices.add(new Device(Device.Type.FIRE_ALARM, false, true, 0));
        mockDevices.add(new Device(Device.Type.HBREAK_ALARM, false, true, 0));
        mockDevices.add(new Device(Device.Type.LEAKAGE, false, true, 0));
        mockDevices.add(new Device(Device.Type.TEMP_INDOOR, false, true, 27));
        mockDevices.add(new Device(Device.Type.TEMP_OUTDOOR, false, true, 12));
        mockDevices.add(new Device(Device.Type.STOVE, false, false, 0));
        mockDevices.add(new Device(Device.Type.WINDOW, false, false, 0));
        mockDevices.add(new Device(Device.Type.EL_CONSUMPTION, false, false, 2));
        mockDevices.add(new Device(Device.Type.TWILIGHT, false, false, 10));
        mockDevices.add(new Device(Device.Type.POWER_CUT, false, false, 0));
        // Readable and writable devices
        mockDevices.add(new Device(Device.Type.INDOOR_LIGHT, true, false, 0));
        mockDevices.add(new Device(Device.Type.OUTDOOR_LIGHT, true, false, 0));
        mockDevices.add(new Device(Device.Type.ARMING_ALARM, true, false, 0));
        mockDevices.add(new Device(Device.Type.FAN, true, false, 0));
        mockDevices.add(new Device(Device.Type.FAN_SPEED, true, false, 2));
        mockDevices.add(new Device(Device.Type.HEATING_INDOOR, true, false, 0));
        mockDevices.add(new Device(Device.Type.HEATING_LOFT, true, false, 0));
        mockDevices.add(new Device(Device.Type.AUTO_MODE, true, false, 0));
    }

    public User addNewUser(String name, String password) {
        User newUser = new User(name, password);
        users.add(newUser);
        return newUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Device> getMockDevices() {
        return mockDevices;
    }

}