package data.mock.test;

import com.google.gson.Gson;
import data.models.mqtt_topics.server_database.ServerRequestsTopics;
import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static data.models.user.UserAccount.USER;

public class TestMQTTLoginRegistration {

    private static MQTTConnectionHandler mqttConnectionHandler;
    private static Scanner in;

    public TestMQTTLoginRegistration() {
    }

    public static void main(String[] args) throws MqttException {
        in = new Scanner(System.in);
        mqttConnectionHandler = MQTTConnectionHandler.getInstance();
        do {
            System.out.println("H E L L O !\n" +
                    "\n1]    Test Registration" +
                    "\n2]    Test Login" +
                    "\n5]    EXIT!");
            int testingOption = new Scanner(System.in).nextInt();
            if (testingOption == 1) {
                System.out.println("Successful registration : " + testRegistration());
            } else if (testingOption == 2) {
                System.out.println("Successful login : " + testLogin());
            } else {
                System.exit(0);
            }
            System.out.println("Repeat?\n1]    yes\n2]    no");
            boolean repeat = in.nextInt() == 2;
            if (repeat) System.exit(0);
        } while (true);
    }

    private static boolean testLogin() throws MqttException {
        String accountName;
        String password;
        Date currentTime = Calendar.getInstance().getTime();
        Date loginValidationPeriod = new Date(currentTime.getTime() + 4000);
        do {
            System.out.print("accountName = ");
            accountName = in.nextLine();

            System.out.print("password = ");
            password = in.nextLine();
            boolean registration = Login.userLogin(mqttConnectionHandler.getClient(), accountName, password);
            if (registration) {
                System.out.println(USER.toString());

                return true;
            }
            currentTime = Calendar.getInstance().getTime();
        } while (currentTime.compareTo(loginValidationPeriod) < 0);
        return false;
    }

    private static boolean testRegistration() throws MqttException {
        String accountName;
        String password;
        String name;
        String emailAccount;
        Date currentTime = Calendar.getInstance().getTime();
        Date loginValidationPeriod = new Date(currentTime.getTime() + 4000);
        do {
            System.out.print("accountName = ");
            accountName = in.nextLine();
            System.out.print("password = ");
            password = in.nextLine();
            System.out.print("name = ");
            name = in.nextLine();
            System.out.print("emailAccount = ");
            emailAccount = in.nextLine();
            boolean registration = Login.userRegistration(mqttConnectionHandler.getClient(), accountName, password, name, emailAccount);
            if (registration && USER.isUserSet()) {
                USER.setAccountName(accountName);
                USER.setPassword(password);
                USER.setName(name);
                USER.setEmailAddress(emailAccount);
                System.out.println(USER.toString());
                return true;
            }
            currentTime = Calendar.getInstance().getTime();
        } while (currentTime.compareTo(loginValidationPeriod) < 0);
        return false;
    }
}

class Login {
    public static boolean userLogin(MqttClient client, String accountName, String password) throws MqttException {
        String[] userLogin = {"accountName", "password"};
        Gson gson = new Gson();
        USER.setUserSet(false);
        client.publish(ServerRequestsTopics.USER.getTopicRegisteredName(),
                new MqttMessage(gson.toJson(userLogin).getBytes()));
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

    public static boolean userRegistration(MqttClient client, String accountName, String password, String userName, String emailAddress) throws MqttException {
        Gson gson = new Gson();
        String[] userRegistrationCredentials = {accountName, password, userName, emailAddress};
        USER.setUserSet(false);
        client.publish(ServerRequestsTopics.REGISTER_USER.getTopicRegisteredName(),
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
}