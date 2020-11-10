package data.services.mqtt;

import data.models.devices.Devices;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.Test;

import static data.models.mqtt_topics.smart_house.SMHOutputTopics.INDOOR_LIGHT;
import static org.junit.jupiter.api.Assertions.*;

class MQTTSHListenerTest {

    @Test
    void messageArrived() throws MqttException {
        MQTTConnectionHandler instance = MQTTConnectionHandler.getInstance();
        instance.getClient().publish(INDOOR_LIGHT.getTopicRegisteredName(), new MqttMessage("false".getBytes()));
        assertEquals(Devices.State.OFF, Devices.INDOOR_LIGHT.getDeviceCurrentState());
    }
}