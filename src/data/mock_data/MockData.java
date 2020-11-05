package data.mock_data;

import data.models.devices.Devices;
import data.models.user.UserAccount;
import data.services.login.Login;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class MockData {

    private static MockData mockData;

    public MockData(String accountName, String password) {
        // Create mock data to fill the devices enum.
        // Should be commented when connected to the Mqtt broker
        setStartingDevices();

        // Simulates the login and sets the user account with mock data.
        setUserAccount(accountName, password);
    }

    private void setUserAccount(String accountName, String password) {
        for (MockUserAccount account: MockUserAccount.values()){
            if (account.getACCOUNT_NAME().equals(accountName) && account.getPASSWORD().equals(password)){
                Login.user = new UserAccount();
                Login.user.setAccountName(account.getACCOUNT_NAME());
                Login.user.setEmailAddress(account.getEMAIL());
                Login.user.setName(account.getNAME());
                Login.user.setPassword(account.getPASSWORD());
            }
        }
    }

    private void setStartingDevices() {
        for (Devices device : Devices.values()) {
            if (device == Devices.INDOOR_TEMPERATURE || device == Devices.OUTDOOR_TEMPERATURE) {
                device.setDoubleValue(ThreadLocalRandom.current().nextDouble(0, 30));
            } else if (device == Devices.FAN) {
                device.setIntValue(ThreadLocalRandom.current().nextInt(1, 5));
            } else if (device == Devices.ELECTRICITY_CONSUMPTION) {
                device.setIntValue(ThreadLocalRandom.current().nextInt(1, 100));
            } else if (device == Devices.TWILIGHT) {
                if (Calendar.getInstance().getTime().getHours() < 17
                        && Calendar.getInstance().getTime().getHours() > 7) {
                    device.setIntValue(ThreadLocalRandom.current().nextInt(40, 100));
                } else {
                    device.setIntValue(ThreadLocalRandom.current().nextInt(0, 39));
                }
                device.setDeviceCurrentState(Devices.State.ON);
            }
            device.setDeviceCurrentState(
                    ThreadLocalRandom.current().nextInt(0, 4) < 2 ?
                            Devices.State.ON : Devices.State.OFF);
        }
    }
}
