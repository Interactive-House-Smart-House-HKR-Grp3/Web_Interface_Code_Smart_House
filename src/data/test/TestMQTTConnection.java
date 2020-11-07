package data.test;

import data.models.devices.Devices;
import data.models.statistics.StatisticsData;
import data.services.login.Login;
import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Scanner;

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
        } else if(testingOption == 3){
            registerNewUserAccount();
        }else{
            System.exit(0);
        }
    }

    /**
     * The user will be ask to provide credentials for a new account, and
     * if valid data, the new account will be created.
     * The user will be automatically logged in, and the mock data created.
     */
    private static void registerNewUserAccount(){
        boolean validAccount;
        do{
            Scanner in = new Scanner(System.in);
            System.out.println("Enter an account name: ");
            String account = in.nextLine();
            validAccount = Login.registerNewAccount(account, null, null);
            System.out.println(!validAccount ? "Please try another account name." : "Valid registration!");
        }while(!validAccount);
        displayMockData();
    }

    /**
     * This will generate mock user, devices, statistics data.
     * For testing, any future logic should be invoked from inside.
     */
    private static void testUsingMockData() {
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
    private static void displayMockData() {

        boolean flag = true;
        while (flag) {
            for (Devices device: Devices.values()){
                System.out.println("For device " + device.name() + ", the value is: " +device.getDeviceCurrentState());
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
                case 8 -> System.out.println("\u001B[34m" + USER.toString());
                case 9 -> System.out.println("\u001B[34m" + BURGLAR_ALARM.toString()
                        + BURGLAR_ALARM.getDeviceCurrentState() + "\u001B[0m");
                case 10 -> System.out.println("\u001B[34m" + StatisticsData.BURGLAR_ALARM.toString()
                        + StatisticsData.BURGLAR_ALARM.getEventBasedStatistics() + "\u001B[0m");
                case 11 -> System.out.println("\u001B[34m" + StatisticsData.OUTDOOR_TEMPERATURE.toString()
                        + StatisticsData.OUTDOOR_TEMPERATURE.getAverageDoubleStatistics() + "\u001B[0m");
                case 12 -> System.out.println("\u001B[34m" + StatisticsData.DOOR.toString()
                        + StatisticsData.DOOR.getEventBasedStatistics() + "\u001B[0m");
                case 13 -> changeState(DOOR, 1);
                case 14 -> changeState(DOOR, 2);
                case 15 -> changeState(BURGLAR_ALARM, 1);
                default -> flag = false;
            }
        }
    }

    private static void changeState(Devices device, int stateCode) {
        device.setDeviceCurrentState(stateCode);
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

     /*for (Devices device: Devices.values()){
            if(device == DOOR){

            }
            label.setText(device.getDeviceCurrentState().toString());
        }*/   new MQTTConnectionHandler();

        System.err.println("Connection closed!");
    }

}
