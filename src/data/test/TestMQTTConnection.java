package data.test;

import data.models.statistics.StatisticsData;
import data.services.login.Login;
import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Scanner;

import static data.models.devices.Devices.*;

/**
 * Test if the client object can connect to the broker and read/update from/to topics
 * that are currently open by the physical-house.
 */
public class TestMQTTConnection {
    public static void main(String[] args) throws MqttException{
        //testMQTTConnectionHandler();

        // Test access to data through login
        /*Scanner in = new Scanner(System.in);
        String account = in.nextLine();
        String pass = in.nextLine();
        new Login(account,pass);
        System.out.println(FAN.name() + " -> " + FAN.getDeviceCurrentState());
        System.out.println(FAN.name() + " -> " + FAN.getIntValue());
        System.out.println(ELECTRICITY_CONSUMPTION.name() + " -> " + ELECTRICITY_CONSUMPTION.getIntValue());
        System.out.println(HEATING_INDOOR.name() + " -> " + HEATING_INDOOR.getDeviceCurrentState());
        System.out.println(DOOR.name() + " -> " + DOOR.getDeviceCurrentState());
        System.out.println(WINDOW.name() + " -> " + WINDOW.getDeviceCurrentState());
        System.out.println(INDOOR_LIGHT.name() + " -> " + INDOOR_LIGHT.getDeviceCurrentState());
        System.out.println(Login.user.toString());
        System.out.println(StatisticsData.BURGLAR_ALARM.getAverageIntegerStatistics());
        System.out.println(StatisticsData.BURGLAR_ALARM.toString());*/

    }

    /**
     * All the logic has been implemented inside the MQTTConnectionHandler class, and it's
     * execution is triggered by the instantiation of the class.
     */
    public static void testMQTTConnectionHandler() throws MqttException{
        new MQTTConnectionHandler();
    }
}
