package data.services.login;

import com.google.gson.Gson;
import data.mock.mock_data.MockData;
import data.mock.mock_data.MockUserAccount;
import data.models.mqtt_topics.server_database.ServerRequestsTopics;
import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static data.mock.mock_data.MockUserAccount.MOCK_USER_5;
import static data.models.user.UserAccount.USER;

public class Login {
    private static MQTTConnectionHandler mqttConnectionHandler;

    public Login(){}

    /**
     * This method is used to login a new user. It will publish the user credentials
     * and wait for 4 seconds for a valid login. If validation doesn't happen in 4 seconds,
     * it will declare the login operation Unsuccessful.
     * @param accountName user credential
     * @param password user credential
     * @return the state of the login
     * @throws MqttException when publishing/reading to/from mqtt broker
     */
    public static boolean userLogin(String accountName, String password) throws MqttException {
        mqttConnectionHandler = MQTTConnectionHandler.getInstance();
        String[] userLogin = {accountName, password};
        Gson gson = new Gson();
        USER.setUserSet(false);
        mqttConnectionHandler.getClient().publish(ServerRequestsTopics.USER.getTopicRegisteredName(),
                new MqttMessage(gson.toJson(userLogin).getBytes()));
        Date currentTime = Calendar.getInstance().getTime();
        Date loginValidationPeriod = new Date(currentTime.getTime() + 4000);
        while (!USER.isUserSet()) {
            if (currentTime.compareTo(loginValidationPeriod) > 0) {
                break;
            }
            currentTime = Calendar.getInstance().getTime();
        }
        return USER.isUserSet();
    }

    /**
     * Uses the four user attributes to request a new user account registration.
     * If the registration is successful, the user is directly login and the user credentials are set.
     * It has a 5 sec timer that waits the registration confirmation. If it expire,
     * then the registration is invalidated.
     *
     * @param accountName credential
     * @param password credential
     * @param userName credential
     * @param emailAddress credential
     * @return the state of the registration
     * @throws MqttException when interacting with the mqtt broker
     */
    public static boolean userRegistration(String accountName, String password, String userName, String emailAddress) throws MqttException {
        mqttConnectionHandler = MQTTConnectionHandler.getInstance();
        Gson gson = new Gson();
        String[] userRegistrationCredentials = {accountName, password, userName, emailAddress};
        USER.setUserSet(false);
        mqttConnectionHandler.getClient().publish(ServerRequestsTopics.REGISTER_USER.getTopicRegisteredName(),
                new MqttMessage(gson.toJson(userRegistrationCredentials).getBytes()));
        Date currentTime = Calendar.getInstance().getTime();
        Date loginValidationPeriod = new Date(currentTime.getTime() + 5000);
        while (!USER.isUserSet()) {
            if (currentTime.compareTo(loginValidationPeriod) > 0) {
                break;
            }
            currentTime = Calendar.getInstance().getTime();
        }
        return USER.isUserSet();
    }
//    /**
//     * Using mock user, but mqtt connection for devices and statistics
//     * .
//     *
//     * @param account  login credentials
//     * @param password login credentials
//     */
//    public Login(String account, String password) throws MqttException {
//        int flag = 0;
//        while (flag < 3) { // Three possible tries to login.
//            if (loginValidation(account, password)) {
//                MQTTConnectionHandler connectionHandler = MQTTConnectionHandler.getInstance();
//                break;
//            } else {
//                System.out.println("Didn't work.\nTry it again!");
//                flag++;
//                Scanner in = new Scanner(System.in);
//                account = in.nextLine();
//                password = in.nextLine();
//            }
//        }
//    }

//    /**
//     * Using mock user, devices, statistics data.
//     *
//     * @param account              login credential
//     * @param password             login credential
//     * @param mockStatisticsPeriod the period required
//     */
//    public Login(String account, String password, int mockStatisticsPeriod) {
//
//        int flag = 0;
//        while (flag < 3) {// Three possible tries to login.
//            if (loginMockValidation(account, password, mockStatisticsPeriod)) {
//                break;
//            } else {
//                System.out.println("Didn't work.\nTry it again!");
//                flag++;
//                Scanner in = new Scanner(System.in);
//                account = in.nextLine();
//                password = in.nextLine();
//            }
//        }
//    }
//
//    public static boolean loginValidation(String account, String password) {
//        // connect db, request validation for login credentials,
//        // and user account data
//
//        // IF true, use the received data to create the user account
//        // read data publish by smart house to set all devices
//
//        // Create mock data
//        for (MockUserAccount user : MockUserAccount.values()) {
//            if (user.getACCOUNT_NAME().equals(account) && user.getPASSWORD().equals(password)) {
//                new MockData(account, password);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean loginMockValidation(String account, String password, int statistics) {
//        for (MockUserAccount user : MockUserAccount.values()) {
//            if (user.getACCOUNT_NAME().equals(account) && user.getPASSWORD().equals(password)) {
//                new MockData(account, password, statistics);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Register a new account for a new user
//     *
//     * @param accountName  for the new account
//     * @param name         of the user
//     * @param emailAddress of the user
//     */
//    public static boolean registerNewAccount(String accountName, String pass, String name, String emailAddress) {
//        Scanner in = new Scanner(System.in);
//        // check if the user is already taken
//        boolean validData;
//        for (MockUserAccount user : MockUserAccount.values()) {
//            validData = user.getACCOUNT_NAME().equals(accountName);
//            if (validData)
//                return false;
//        }
//        System.out.println("Enter a password: ");
//        String password = pass == null ? in.nextLine() : pass;
//        // update user Test5
//        MOCK_USER_5.setACCOUNT_NAME(accountName);
//        MOCK_USER_5.setPASSWORD(password);
//        MOCK_USER_5.setNAME(name);
//        MOCK_USER_5.setEMAIL(emailAddress);
//
//        System.out.println("Enter mock statistics code:\n1]    last 24 hours\n2]    Last week\n3]    Last 30 days");
//        int period = in.nextInt();
//
//        // create the mock data.
//        return loginMockValidation(accountName, password, period);
//    }
}