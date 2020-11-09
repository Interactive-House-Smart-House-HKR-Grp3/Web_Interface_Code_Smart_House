package data.test;

import data.models.devices.Devices;
import data.models.mqtt_topics.smart_house.SMHOutputTopics;
import data.models.statistics.StatisticsData;
import data.services.local.RequestManager;
import data.services.login.Login;
import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.*;

import static data.models.devices.Devices.*;
import static data.models.user.UserAccount.USER;

/**
 * Test if the client object can connect to the broker and read/update from/to topics
 * that are currently open by the physical-house.
 */
public class TestMQTTConnection {

    public static void main(String[] args) throws MqttException {
        System.out.println("H E L L O !\n" +
                "\n1]    Test using mock user and mqtt connection" +
                "\n2]    Test using mock user,devices, statistics" +
                "\n3]    Register new account" +
                "\n5]    EXIT!");
        int testingOption = new Scanner(System.in).nextInt();
        if (testingOption == 1) {
            testMQTTConnectionHandler();
        } else if (testingOption == 2) {
            testUsingMockData();
        } else if (testingOption == 3) {
            registerNewUserAccount();
        } else {
            System.exit(0);
        }
    }

    /**
     * The user will be ask to provide credentials for a new account, and
     * if valid data, the new account will be created.
     * The user will be automatically logged in, and the mock data created.
     */
    private static void registerNewUserAccount() throws MqttException {
        boolean validAccount;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter an account name: ");
            String account = in.nextLine();
            validAccount = Login.registerNewAccount(account, null, null, null);
            System.out.println(!validAccount ? "Please try another account name." : "Valid registration!");
        } while (!validAccount);
        displayMockData();
        System.err.println("Connection closed!");
    }

    /**
     * This will generate mock user, devices, statistics data.
     * For testing, any future logic should be invoked from inside.
     */
    private static void testUsingMockData() throws MqttException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter account name: ");
        String account = in.nextLine();
        System.out.println("Enter password: ");
        String pass = in.nextLine();
        System.out.println("Enter mock statistics code:\n1]    last 24 hours\n2]    Last week\n3]    Last 30 days");
        int period = in.nextInt();
        new Login(account, pass, period);

        // Print some data to see if it has been instantiated correctly
        displayMockData();

        /*
        HERE CAN BE ADDED MORE LOGIC THAT NEEDS TO BE TESTED USING THE MOCK DATA
        */

        System.err.println("Connection closed!");
    }

    /**
     * Here are checked different components and their mock data.
     */
    private static void displayMockData() throws MqttException {

        boolean flag = true;
        while (flag) {
            for (Devices device : Devices.values()) {
                System.out.println("For device " + device.name() + ", the value is: " + device.getDeviceCurrentState());
            }

            System.out.println("\n\nCHOOSE ACTION:\n1]    Get FAN current state" +
                    "\n2]    Get FAN speed value" +
                    "\n3]    Get ELECTRICITY_CONSUMPTION value" +
                    "\n4]    Get HEATING_INDOOR current state" +
                    "\n5]    Get DOOR current state" +
                    "\n6]    Get WINDOW current state" +
                    "\n7]    Get INDOOR_LIGHT current state" +
                    "\n8]    Get USER data" +
                    "\n9]    Get BURGLAR_ALARM state" +
                    "\n10]   Get BURGLAR_ALARM statistics" +
                    "\n11]   Get OUTDOOR_TEMPERATURE statistics" +
                    "\n12]   Get DOOR statistics" +
                    "\n13]   Set DOOR status to On(Open)" +
                    "\n14]   Set DOOR status to Off(Closed)" +
                    "\n15]   ARM the BURGLAR_ALARM" +
                    "\n16]   Turn OFF the indoor light" +
                    "\n17]   Turn ON the indoor light" +
                    "\nFor testing you can add multiple option programmatically.." +
                    "\n\n Any other value (integer) will close this loop."
            );
            switch (new Scanner(System.in).nextInt()) {
                case 1 -> System.out.println("\u001B[34m" + FAN.name() + " -> "
                        + FAN.getDeviceCurrentState() + "\u001B[0m");
                case 2 -> System.out.println("\u001B[34m" + FAN.name() + " -> " + FAN.getIntValue() + "\u001B[0m");
                case 3 -> System.out.println("\u001B[34m" + ELECTRICITY_CONSUMPTION.name() + " -> "
                        + ELECTRICITY_CONSUMPTION.getIntValue() + "\u001B[0m");
                case 4 -> System.out.println("\u001B[34m" + HEATING_INDOOR.name() + " -> "
                        + HEATING_INDOOR.getDeviceCurrentState() + "\u001B[0m");
                case 5 -> System.out.println("\u001B[34m" + DOOR.name() + " -> "
                        + DOOR.getDeviceCurrentState() + "\u001B[0m");
                case 6 -> System.out.println("\u001B[34m" + WINDOW.name() + " -> "
                        + WINDOW.getDeviceCurrentState() + "\u001B[0m");
                case 7 -> System.out.println("\u001B[34m" + INDOOR_LIGHT.name()
                        + " -> " + INDOOR_LIGHT.getDeviceCurrentState() + "\u001B[0m");
                case 8 -> System.out.println("\u001B[34m" + USER.toString() + "\u001B[0m");
                case 9 -> System.out.println("\u001B[34m" + BURGLAR_ALARM.toString()
                        + BURGLAR_ALARM.getDeviceCurrentState() + "\u001B[0m");
                case 10 -> printSortedStatistics(StatisticsData.BURGLAR_ALARM);
                case 11 -> printSortedStatistics(StatisticsData.OUTDOOR_TEMPERATURE);
                case 12 -> printSortedStatistics(StatisticsData.DOOR);
                case 13 -> changeState(DOOR, 1);
                case 14 -> changeState(DOOR, 2);
                case 15 -> changeState(BURGLAR_ALARM, 1);
                case 16 -> changeState(INDOOR_LIGHT, 2);
                case 17 -> changeState(INDOOR_LIGHT, 1);
                default -> flag = false;
            }
        }
    }

    /**
     * Prints specific type statistics, based on the Device.
     *
     * @param statisticsData for a specific device.
     */
    private static void printSortedStatistics(StatisticsData statisticsData) {
        List<Date> dates = new ArrayList<>();
        int type = statisticsData.getEventBasedStatistics() != null ? 1 :
                statisticsData.getAverageDoubleStatistics() != null ? 2 : 3;
        System.out.println("\u001B[35mStatistics for " + statisticsData.name() + "\u001B[0m");
        if (type == 1) {
            Map<Date, Integer> eventBasedStatistics = statisticsData.getEventBasedStatistics();
            dates.addAll(eventBasedStatistics.keySet());
            Collections.sort(dates);
            for (Date date : dates) {
                System.out.println("\u001B[34mFor: " + date + ", value >>> " + statisticsData.getEventBasedStatistics().get(date) + "\u001B[0m");
            }
        } else if (type == 2) {
            Map<Date, Double> averageDoubleStatistics = statisticsData.getAverageDoubleStatistics();
            dates.addAll(averageDoubleStatistics.keySet());
            Collections.sort(dates);
            for (Date date : dates) {
                System.out.println("\u001B[34mFor: " + date + ", value >>> " + statisticsData.getAverageDoubleStatistics().get(date) + "\u001B[0m");
            }
        } else {
            Map<Date, Integer> averageIntegerStatistics = statisticsData.getAverageIntegerStatistics();
            dates.addAll(averageIntegerStatistics.keySet());
            Collections.sort(dates);
            for (Date date : dates) {
                System.out.println("\u001B[34mFor: " + date + ", value >>> " + statisticsData.getAverageIntegerStatistics().get(date) + "\u001B[0m");
            }
        }
        System.out.println();
    }

    /**
     * @param device    to modify state
     * @param stateCode new state
     *                  1 = on,
     *                  2 = off.
     */
    private static void changeState(Devices device, int stateCode) throws MqttException {
        String topicName = null;

        for (SMHOutputTopics topic: SMHOutputTopics.values()){
            if(topic.name().equals(device.name())){
                topicName = topic.getTopicRegisteredName();
                break;
            }
        }
        MQTTConnectionHandler.mqttClient.publish(topicName,
                new MqttMessage((stateCode == 1 ? "true" : "false").getBytes()));

        // device.changeStateTo(stateCode);
        System.out.println("\u001B[34m" + device.name() + " state is: " + device.getDeviceCurrentState() + "\u001B[0m");
    }

    /**
     * All the logic has been implemented inside the MQTTConnectionHandler class, and it's
     * execution is triggered by the instantiation of the class.
     * <p>
     * The method created a mock user account, used for testing purposes.
     */
    public static void testMQTTConnectionHandler() throws MqttException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter account name: ");
        String account = in.nextLine();
        System.out.println("Enter password: ");
        String pass = in.nextLine();
        new Login(account, pass);
        System.out.println(USER.getName());
        displayMockData();

        System.err.println("Connection closed!");
    }
}