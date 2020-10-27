package data;

import data.services.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Test {
    public static void main(String[] args) throws MqttException, InterruptedException {
       new MQTTConnectionHandler();
    }
}
