package data.services.mqtt;

import data.models.devices.Devices;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

import static data.models.devices.Devices.*;

public class MQTTSHListener implements IMqttMessageListener {

    @Override
    public void messageArrived(String topic, MqttMessage var2) {
        switch (topic) {
            // var1 represents the topic
            // var2 represents the message
            // based on the topic and value, take the required action

            /*1*/
            case "smart_house/gui/indoor_light" -> updateDeviceState(INDOOR_LIGHT, var2.toString().equals("true"));
            /*2*/
            case "smart_house/gui/outdoor_light" -> updateDeviceState(OUTDOOR_LIGHT, var2.toString().equals("true"));
            /*3*/
            case "smart_house/gui/fire_alarm" -> updateDeviceState(FIRE_ALARM, var2.toString().equals("true"));
            /*4*/
            case "smart_house/gui/hbreak_alarm" -> updateDeviceState(BURGLAR_ALARM, var2.toString().equals("true"));
            /*5*/
            case "smart_house/gui/leakage" -> updateDeviceState(WATER_LEAKAGE, var2.toString().equals("true"));
            /*6*/
            case "smart_house/gui/temp_indoor" -> updateDeviceState(INDOOR_TEMPERATURE, var2.toString().equals("true"));
            /*7*/
            case "smart_house/gui/temp_outdoor" -> updateDeviceState(OUTDOOR_TEMPERATURE, var2.toString().equals("true"));
            /*8*/
            case "smart_house/gui/stove" -> updateDeviceState(STOVE, var2.toString().equals("true"));
            /*9*/
            case "smart_house/gui/window" -> updateDeviceState(WINDOW, var2.toString().equals("true"));
            /*10*/
            case "smart_house/gui/el_consumption" -> updateDeviceState(ELECTRICITY_CONSUMPTION, var2.toString().equals("true"));
            /*11*/
            case "smart_house/gui/twilight" -> updateDeviceState(TWILIGHT, var2.toString().equals("true"));
            /*12*/
            case "smart_house/gui/power_cut" -> updateDeviceState(POWER_CUT, var2.toString().equals("true"));
            /*13*/
            case "smart_house/gui/alarm" -> updateDeviceState(BURGLAR_ALARM, var2.toString().equals("true"));
            /*14*/
            case "smart_house/gui/fan_speed" -> updateDeviceState(FAN, var2.toString().equals("true"));
            /*15*/
            case "smart_house/gui/fan" -> updateDeviceState(FAN, var2.toString().equals("true"));
            /*16*/
            case "smart_house/gui/heating_in" -> updateDeviceState(HEATING_INDOOR, var2.toString().equals("true"));
            /*17*/
            case "smart_house/gui/heating_loft" -> updateDeviceState(HEATING_LOFT, var2.toString().equals("true"));
            /*18*/
            case "smart_house/gui/door" -> updateDeviceState(DOOR, var2.toString().equals("true"));
            /*19*/
            case "smart_house/gui/auto_mode" -> updateDeviceState(AUTO_MODE, var2.toString().equals("true"));

            default -> throw new IllegalStateException("Unexpected value: " + topic);
        }
        System.out.println("\u001b[33m***********************************************************************\u001b[0m");
    }

    private void updateDeviceState(Devices device, boolean isON) {
        if (List.of(STOVE, WINDOW).contains(device)) {
            device.setDeviceCurrentState(isON ? State.OPEN : State.CLOSED);
            System.out.println("For: " + device.name() + ", new value is -> " + (isON ? "OPEN" : "CLOSED"));
            System.out.println("Device check:\n" + device.toString());
        } else if (List.of(BURGLAR_ALARM, FIRE_ALARM).contains(device)) {
            device.setDeviceCurrentState(isON ? State.ARMED : State.OFF);
            System.out.println("For: " + device.name() + ", new value is -> " + (isON ? "ARMED" : "OFF"));
            System.out.println("Device check:\n" + device.toString());
        } else {
            device.setDeviceCurrentState(isON ? State.ON : State.OFF);
            System.out.println("For: " + device.name() + ", new value is -> " + (isON ? "ON" : "OFF"));
            System.out.println("Device check:\n" + device.toString());
        }
    }
}
