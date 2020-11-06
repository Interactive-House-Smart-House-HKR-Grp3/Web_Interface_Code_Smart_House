package data.services.login;

import data.mock_data.MockData;
import data.mock_data.MockUserAccount;
import data.models.user.UserAccount;
import data.services.local.DeviceController;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Scanner;

public class Login {

    public static UserAccount user;
    public static DeviceController deviceController;

    /**
     * Using mock user, but mqtt connection for devices and statistics
     * .
     *
     * @param account
     * @param password
     */
    public Login(String account, String password){

        int flag = 0;
        while (flag < 3) {// Three possible tries to login.
            if (loginValidation(account, password)) {
                break;
            } else {
                System.out.println("Didn't work.\nTry it again!");
                flag++;
                Scanner in = new Scanner(System.in);
                account = in.nextLine();
                password = in.nextLine();
            }
        }
    }

    /**
     * Using mock user, devices, statistics data.
     *
     * @param account login credential
     * @param password login credential
     * @param mockStatisticsPeriod the period required
     */
    public Login(String account, String password, int mockStatisticsPeriod){

        int flag = 0;
        while (flag < 3) {// Three possible tries to login.
            if (loginMockValidation(account, password, mockStatisticsPeriod)) {
                break;
            } else {
                System.out.println("Didn't work.\nTry it again!");
                flag++;
                Scanner in = new Scanner(System.in);
                account = in.nextLine();
                password = in.nextLine();
            }
        }
/*
        // Using mock user and mqtt sourced data
        if (loginValidation(account, password)) {
            //deviceController = new DeviceController();

        } else {

            // set a flag that says that the validation failed
        }*/

    }

    private boolean loginValidation(String account, String password) {
        // connect db, request validation for login credentials,
        // and user account data

        // IF true, use the received data to create the user account
        // read data publish by smart house to set all devices

        // Create mock data
        for (MockUserAccount user : MockUserAccount.values()) {
            if (user.getACCOUNT_NAME().equals(account) && user.getPASSWORD().equals(password)) {
                new MockData(account, password);
                return true;
            }
        }
        return false;
    }

    private boolean loginMockValidation(String account, String password, int statistics) {
        for (MockUserAccount user : MockUserAccount.values()) {
            if (user.getACCOUNT_NAME().equals(account) && user.getPASSWORD().equals(password)) {
                new MockData(account, password, statistics);
                return true;
            }
        }
        return false;
    }

}
