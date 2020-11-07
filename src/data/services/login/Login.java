package data.services.login;

import data.mock_data.MockData;
import data.mock_data.MockUserAccount;

import java.util.Scanner;

import static data.mock_data.MockUserAccount.MOCK_USER_5;

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
    public Login(String account, String password) {

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
    public static boolean registerNewAccount(String accountName, String name, String emailAddress) {
        Scanner in = new Scanner(System.in);
        // check if the user is already taken
        boolean validData = false;
        for (MockUserAccount user : MockUserAccount.values()) {
            validData = user.getACCOUNT_NAME().equals(accountName);
            if (validData)
                return false;
        }
        System.out.println("Enter a password: ");
        String password = in.nextLine();
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


    /**
     * Peter Created methods...
     * These align more with how the servlets would like to call on the methods needed for verification on web run time
     *
     * Verify a login attempt by the user
     * Return whether or not it succeeded
     *
     * @param accountName  for the new account
     * @param password     of the user
     */
    public static boolean verifyLogin(String accountName, String password, int mockStatisticsPeriod) {
        new MockData(accountName, password); // calling this to make sure info wont be null (might not be necessary?)
        return loginMockValidation(accountName, password, mockStatisticsPeriod);
    }

    /**
     * Peter Created methods...
     * These align more with how the servlets would like to call on the methods needed for verification on web run time
     *
     * Register a new account for a new user
     * Return whether or not it succeeded
     *
     * @param accountName          for the new account
     * @param password             of the user
     * @param name                 of the user
     * @param emailAddress         of the user
     * @param mockStatisticsPeriod the period required
     */
    public static boolean registerNewAccount(String accountName, String password, String name, String emailAddress, int mockStatisticsPeriod) {
        new MockData(accountName, password); // calling this to make sure info wont be null (might not be necessary?)
        // check if the user is already taken
        boolean validData = false;
        for (MockUserAccount user : MockUserAccount.values()) {
            validData = user.getACCOUNT_NAME().equals(accountName);
            if (validData)
                return false;
        }
        // update user Test5
        MOCK_USER_5.setACCOUNT_NAME(accountName);
        MOCK_USER_5.setPASSWORD(password);
        MOCK_USER_5.setNAME(name);
        MOCK_USER_5.setEMAIL(emailAddress);

        // create the mock data.
        return loginMockValidation(accountName, password, mockStatisticsPeriod);
    }
}