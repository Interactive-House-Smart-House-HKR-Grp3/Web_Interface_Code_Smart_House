package data.services.login;

import data.mock.mock_data.MockData;
import data.mock.mock_data.MockUserAccount;
import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Scanner;

import static data.mock.mock_data.MockUserAccount.MOCK_USER_5;

public class Login {

    public Login() {
    }

    /**
     * Using mock user, but mqtt connection for devices and statistics
     * .
     *
     * @param account  login credentials
     * @param password login credentials
     */
    public Login(String account, String password) throws MqttException {
        // TODO: implementation with server/db, when db is set
        int flag = 0;
        while (flag < 3) { // Three possible tries to login.
            if (loginValidation(account, password)) {
                MQTTConnectionHandler connectionHandler = MQTTConnectionHandler.getInstance();
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
     * @param account              login credential
     * @param password             login credential
     * @param mockStatisticsPeriod the period required
     */
    public Login(String account, String password, int mockStatisticsPeriod) {

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
    }

    public static boolean loginValidation(String account, String password) {
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

    private static boolean loginMockValidation(String account, String password, int statistics) {
        for (MockUserAccount user : MockUserAccount.values()) {
            if (user.getACCOUNT_NAME().equals(account) && user.getPASSWORD().equals(password)) {
                new MockData(account, password, statistics);
                return true;
            }
        }
        return false;
    }

    /**
     * Register a new account for a new user
     *
     * @param accountName  for the new account
     * @param name         of the user
     * @param emailAddress of the user
     */
    public static boolean registerNewAccount(String accountName, String pass, String name, String emailAddress) {
        Scanner in = new Scanner(System.in);
        // check if the user is already taken
        boolean validData;
        for (MockUserAccount user : MockUserAccount.values()) {
            validData = user.getACCOUNT_NAME().equals(accountName);
            if (validData)
                return false;
        }
        System.out.println("Enter a password: ");
        String password = pass == null ? in.nextLine() : pass;
        // update user Test5
        MOCK_USER_5.setACCOUNT_NAME(accountName);
        MOCK_USER_5.setPASSWORD(password);
        MOCK_USER_5.setNAME(name);
        MOCK_USER_5.setEMAIL(emailAddress);

        System.out.println("Enter mock statistics code:\n1]    last 24 hours\n2]    Last week\n3]    Last 30 days");
        int period = in.nextInt();

        // create the mock data.
        return loginMockValidation(accountName, password, period);
    }
}