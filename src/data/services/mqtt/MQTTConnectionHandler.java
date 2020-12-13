package data.services.mqtt;

import data.models.mqtt_topics.server_database.ReadFromServer;
import data.models.mqtt_topics.smart_house.ReadFromHouse;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Instantiate a valid mqtt client that is invoked to publish to, or read from, the mqtt topics.
 */
public class MQTTConnectionHandler {

    private static MQTTConnectionHandler connectionHandler; // used for handling requests

    public static MqttClient mqttClient;

    private final MqttConnectOptions CONNECTION_OPTIONS = new MqttConnectOptions();

    public static MQTTConnectionHandler getInstance() throws MqttException {
        return (connectionHandler == null ? new MQTTConnectionHandler() : connectionHandler);
    }

    public MQTTConnectionHandler() throws MqttException {
            connectMqtt(); // create connection
            subscribeToSHTopics(); // subscribe to the smart house topics
            subscribeToServerTopics(); // subscribe to the server topics
    }

    /**
     * Instantiate a valid client
     */
    public void connectMqtt() {
        try {
            final String CLIENT_ID = "Web_Interface";
            final String broker = "tcp://smart-mqtthive.duckdns.org:1883";
            mqttClient = new MqttClient(broker, CLIENT_ID);

            CONNECTION_OPTIONS.setCleanSession(true);
            CONNECTION_OPTIONS.setKeepAliveInterval(180);

            mqttClient.connect(CONNECTION_OPTIONS);
            System.out.println("Broker: " + broker + ", connected!");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * Subscribes the mqtt client to all the 'gui' topics on which the smart house
     * publishes, and the web listen.
     * @throws MqttException when mqtt error
     */
    private void subscribeToSHTopics() throws MqttException {
        for (ReadFromHouse topic : ReadFromHouse.values()) {
            mqttClient.subscribe(topic.getTopicRegisteredName(), new MQTTSmartHouseListener());
            System.out.println("Subscribed to: " + topic.getTopicRegisteredName());
        }
    }

    /**
     * Subscribes the mqtt client to all the 'statistics' topics on which the server
     * publishes and the web listen.
     * @throws MqttException when mqtt error
     */
    private void subscribeToServerTopics() throws MqttException {
        for (ReadFromServer topic : ReadFromServer.values()) {
            mqttClient.subscribe(topic.getTopicRegisteredName(), new MQTTServerListener());
            System.out.println("Subscribed to: " + topic.getTopicRegisteredName());
        }
    }

    /**
     * Used only for incipient tests.
     * @throws MqttException when mqtt error
     */
    private void changeStatesToClose() throws MqttException {
        for (ReadFromHouse topic : ReadFromHouse.values()) {
            publish(topic.getTopicRegisteredName(), "false");
            System.out.println("State changed to 'false' on : " + topic.getTopicRegisteredName());
        }
    }

    /**
     * Takes a topic and a message string and publish it.
     * @param topic to publish to.
     * @param message to be wrapped in an mqtt message and published.
     * @throws MqttException when mqtt error.
     */
    public void publish(String topic, String message) throws MqttException {
        if (mqttClient.isConnected()) {
            mqttClient.publish(topic, new MqttMessage(message.getBytes()));
        }
    }

    /**
     * Used by the static instance to manage user requests implying mqtt trafficked data.
     * @return a valid Mqtt client object.
     */
    public MqttClient getClient() {
        return mqttClient;
    }
}