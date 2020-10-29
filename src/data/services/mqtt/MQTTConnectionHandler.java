package data.services.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTConnectionHandler {
    private MqttClient client;
    private Topics topics;

    private final MqttConnectOptions CONNECTION_OPTIONS = new MqttConnectOptions(); //Creating the options

    public MQTTConnectionHandler() throws MqttException, InterruptedException {
        connectMqtt();
        while (client.isConnected()) {
            changeStatesToClose();
            Thread.sleep(5000);
        }
    }

    public void connectMqtt() {

        try {
            final String CLIENT_ID = "Web_Interface";
            final String broker = "tcp://smart-mqtthive.duckdns.org:1883";
            client = new MqttClient(broker, CLIENT_ID);

            CONNECTION_OPTIONS.setCleanSession(true);
            CONNECTION_OPTIONS.setKeepAliveInterval(180);

            client.connect(CONNECTION_OPTIONS);
            System.out.println("broker: " + broker + " Connected");
            subscribeToTopics();
            //client.publish("smart_house/indoor_temperature", new M);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribeToTopics() throws MqttException {
        for (Topics topic : Topics.values()) {
            client.subscribe(topic.getTopicRegisteredName(), new MQTTListener());
            System.out.println("subscribed to: " + topic.getTopicRegisteredName());
        }
    }
    private void changeStatesToClose() throws MqttException {
        for (Topics topic : Topics.values()) {
            publish(topic.getTopicRegisteredName(), "false");
            System.out.println("State changed to 'false' on : " + topic.getTopicRegisteredName());
        }
    }

    public void publish(String topic, String message) throws MqttException {
        if (client.isConnected()) {
            client.publish(topic, new MqttMessage(message.getBytes()));
        }
    }
}
