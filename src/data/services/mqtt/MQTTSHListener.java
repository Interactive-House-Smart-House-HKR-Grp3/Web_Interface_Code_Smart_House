package data.services.mqtt;

import data.models.devices.Devices;
import data.test.TestMQTTConnection;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import static data.models.devices.Devices.*;
/**
 * This is a listener set for each subscription to a topic on which
 * the smart house is publishing and the web is listening.
 *
 * When a new MqttMessage is published on those topic, the listener
 * will take the corresponding actions.
 */
public class MQTTSHListener implements IMqttMessageListener {

    /**
     * Every new message published will come with two attributes: the topic and the message.
     * @param topic on which the new message was published
     * @param mqttMessage published
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        switch (topic) {
            /*1*/
            case "smart_house/gui/indoor_light" -> updateDeviceState(INDOOR_LIGHT, mqttMessage.toString().equals("true"));
            /*2*/
            case "smart_house/gui/outdoor_light" -> updateDeviceState(OUTDOOR_LIGHT, mqttMessage.toString().equals("true"));
            /*3*/
            case "smart_house/gui/fire_alarm" -> updateDeviceState(FIRE_ALARM, mqttMessage.toString().equals("true"));
            /*4*/
            case "smart_house/gui/hbreak_alarm" -> updateDeviceState(BURGLAR_ALARM, mqttMessage.toString().equals("true"));
            /*5*/
            case "smart_house/gui/leakage" -> updateDeviceState(WATER_LEAKAGE, mqttMessage.toString().equals("true"));
            /*6*/
            case "smart_house/gui/temp_indoor" -> updateDeviceState(INDOOR_TEMPERATURE, mqttMessage.toString().equals("true"));
            /*7*/
            case "smart_house/gui/temp_outdoor" -> updateDeviceState(OUTDOOR_TEMPERATURE, mqttMessage.toString().equals("true"));
            /*8*/
            case "smart_house/gui/stove" -> updateDeviceState(STOVE, mqttMessage.toString().equals("true"));
            /*9*/
            case "smart_house/gui/window" -> updateDeviceState(WINDOW, mqttMessage.toString().equals("true"));
            /*10*/
            case "smart_house/gui/el_consumption" -> updateDeviceState(ELECTRICITY_CONSUMPTION, mqttMessage.toString().equals("true"));
            /*11*/
            case "smart_house/gui/twilight" -> updateDeviceState(TWILIGHT, mqttMessage.toString().equals("true"));
            /*12*/
            case "smart_house/gui/power_cut" -> updateDeviceState(POWER_CUT, mqttMessage.toString().equals("true"));
            /*13*/
            case "smart_house/gui/alarm" -> updateDeviceState(BURGLAR_ALARM, mqttMessage.toString().equals("true"));
            /*14*/
            case "smart_house/gui/fan_speed" -> updateDeviceState(FAN, mqttMessage.toString().equals("true"));
            /*15*/
            case "smart_house/gui/fan" -> updateDeviceState(FAN, mqttMessage.toString().equals("true"));
            /*16*/
            case "smart_house/gui/heating_in" -> updateDeviceState(HEATING_INDOOR, mqttMessage.toString().equals("true"));
            /*17*/
            case "smart_house/gui/heating_loft" -> updateDeviceState(HEATING_LOFT, mqttMessage.toString().equals("true"));
            /*18*/
            case "smart_house/gui/door" -> updateDeviceState(DOOR, mqttMessage.toString().equals("true"));
            /*19*/
            case "smart_house/gui/auto_mode" -> updateDeviceState(AUTO_MODE, mqttMessage.toString().equals("true"));

            default -> throw new IllegalStateException("Unexpected value: " + topic);
        }
        System.out.println("\u001b[33m***********************************************************************\u001b[0m");
    }

    /**
     * Based on the message content, setts the corresponding state code.
     *
     * @param device to set the state
     * @param isON true ? 1 : 2
     *             1 = ON, OPEN
     *             2 = OFF, CLOSED
     */
    private void updateDeviceState(Devices device, boolean isON) {
        device.setDeviceCurrentState(isON ? 1 : 2);
        System.out.println("For: " + device.name() + ", new state is -> " + device.getDeviceCurrentState());
        System.out.println("Device check:\n" + device.toString());
        device.setNewStateRead(true);
    }

}