package data.services.local;

import data.interfaces.data_logic.DataLogic;
import data.models.devices.Devices;
import data.models.statistics.StatisticsData;
import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DeviceController implements DataLogic {

    MQTTConnectionHandler mqttConnectionHandler = new MQTTConnectionHandler();

    public DeviceController() throws MqttException {
        setDevices();
        // ...
    }

    private void setDevices() {
        // read all input topics from smart house, and set the devices data. State + value (where one exists).

    }

    @Override
    public void changeStateTo(Devices device, boolean onOrOpen) {
        // call MQTT client obj and publish
        // the required new state for the device.
    }

    @Override
    public Devices.State getStateOfDevice(Devices device) {
        // call MQTT client obj and read the topic
        // related to the device, and convert + return
        // it's state value (in local format)
        return null;
    }

    @Override
    public List<Devices.State> getStateOfDevices() {
        // call MQTT client obj and read all the topics
        // related to the devices, and convert + return
        // their state values (in local format)
        return null;
    }

    @Override
    public List<Devices.State> getStateOfDevices(List<Devices> devicesList) {
        // call MQTT client obj and read the topics
        // related to the deviceList's devices, and
        // convert + return their state values (in local format)
        return null;
    }

    @Override
    public HashMap<Devices, StatisticsData> getStatistics(List<Devices> devicesList, Date from, Date until) {
        // call MQTT client obj and read the topics
        // related to the deviceList's devices, and
        // convert + return their statistics values (in local format)

        // this will return a list of device:statisticsData pairs.
        return null;
    }

    @Override
    public StatisticsData getStatistics(Devices device, Date from, Date until) {
        // call MQTT client obj and read the topics
        // related to the device, and convert + return
        // it's statistics values (in local format)

        // this will return a StatisticsData obj.
        return null;
    }
}
