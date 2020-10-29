package data.test;

import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Test if the client object can connect to the broker and read/update from/to topics
 * that are currently open by the physical-house.
 */
public class TestMQTTConnection {
    public static void main(String[] args) throws MqttException, InterruptedException {
        testMQTTConnectionHandler();
    }

    /**
     * All the logic has been implemented inside the MQTTConnectionHandler class, and it's
     * execution is triggered by the instantiation of the class.
     */
    public static void testMQTTConnectionHandler() throws MqttException, InterruptedException {
        new MQTTConnectionHandler();
    }
}
