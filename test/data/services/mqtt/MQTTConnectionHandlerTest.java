package data.services.mqtt;

import data.models.devices.Devices;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.Test;

import static data.models.mqtt_topics.smart_house.SMHOutputTopics.INDOOR_LIGHT;
import static org.junit.jupiter.api.Assertions.*;

class MQTTConnectionHandlerTest {
    MQTTConnectionHandler instance;

    MQTTConnectionHandlerTest() throws MqttException {
        instance = MQTTConnectionHandler.getInstance();
    }

    @Test
    void getInstance(){
        assertNotNull(instance);
    }

    @Test
    void connectMqtt() {
        assertTrue(instance.getClient().isConnected());
    }

    @Test
    void publish() throws MqttException, InterruptedException {
        Thread.sleep(1000);
        instance.getClient().publish(INDOOR_LIGHT.getTopicRegisteredName(), new MqttMessage("true".getBytes()));
        assertEquals(Devices.State.ON, Devices.INDOOR_LIGHT.getDeviceCurrentState());
    }

    @Test
    void getClient() throws InterruptedException {
        Thread.sleep(1000);
        assertNotNull(instance.getClient());
    }
}