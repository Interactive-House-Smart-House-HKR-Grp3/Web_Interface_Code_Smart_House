package data.services;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.Arrays;

public class MQTTConnectionHandler {
    private MqttClient client;
    private static final ArrayList<String> TOPICS = new ArrayList<>(
            Arrays.asList("indoor_light", "outdoor_light", "alarm",
                    "fan", "heating_indoor", "heating_wind", "door"));
    private final static String PREFIX = "smart_house/";

    private final MqttConnectOptions CONNECTION_OPTIONS = new MqttConnectOptions(); //Creating the options

    public void connectMqtt() {

        try {
            final String CLIENT_ID = "Web_Interface";
            final String broker = "tcp://smart-mqtthive.duckdns.org:1883";
            client = new MqttClient(broker, CLIENT_ID);

            CONNECTION_OPTIONS.setCleanSession(true);
            CONNECTION_OPTIONS.setKeepAliveInterval(180);

            client.connect(CONNECTION_OPTIONS);
            System.out.println("broker: " + broker + " Connected");
            subscribeToTopics(client);
            //client.publish("smart_house/indoor_temperature", new M);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribeToTopics(MqttClient client) throws MqttException {
        for (String s : TOPICS) {
            String topic = PREFIX + s;
            client.subscribe(topic, new MQTTListener());
            System.out.println("subscribed to: " + topic);
        }
    }

    public MQTTConnectionHandler() throws MqttException, InterruptedException {

        connectMqtt();
        while (client.isConnected()) {
            changeStatesToClose();
            Thread.sleep(5000);
        }
    }

    private void changeStatesToClose() throws MqttException {
        publish(PREFIX + TOPICS.get(0), "false");
        publish(PREFIX + TOPICS.get(1), "false");
        publish(PREFIX + TOPICS.get(2), "false");
        publish(PREFIX + TOPICS.get(3), "false");
        publish(PREFIX + TOPICS.get(4), "false");
        publish(PREFIX + TOPICS.get(5), "false");
        publish(PREFIX + TOPICS.get(6), "false");
    }

    public void publish(String topic, String message) throws MqttException {
        if (client.isConnected()) {
            client.publish(topic, new MqttMessage(message.getBytes()));
        }
    }
}

