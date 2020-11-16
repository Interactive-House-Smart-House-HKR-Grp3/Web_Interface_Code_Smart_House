package data.services.mqtt;

import com.google.gson.Gson;
import data.models.statistics.StatisticsData;
import data.models.user.UserAccount;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static data.models.statistics.StatisticsData.*;
import static data.models.user.UserAccount.USER;

/**
 * This is a listener set for each subscription to a topic on which
 * the server is publishing and the web is listening.
 * <p>
 * When a new MqttMessage is published on those topic, the listener
 * will take the corresponding actions.
 */
public class MQTTServerListener implements IMqttMessageListener {

    /**
     * Every new message published will come with two attributes: the topic and the message.
     *
     * @param topic       on which the new message was published
     * @param mqttMessage published
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        switch (topic) {
            /*1*/
            case "web/statistics/fire_alarm" -> updateDeviceStatistics(FIRE_ALARM, mqttMessage);
            /*2*/
            case "web/statistics/burglar_alarm" -> updateDeviceStatistics(BURGLAR_ALARM, mqttMessage);
            /*3*/
            case "web/statistics/water_leakage" -> updateDeviceStatistics(WATER_LEAKAGE, mqttMessage);
            /*4*/
            case "web/statistics/indoor_temperature" -> updateDeviceStatistics(INDOOR_TEMPERATURE, mqttMessage);
            /*5*/
            case "web/statistics/outdoor_temperature" -> updateDeviceStatistics(OUTDOOR_TEMPERATURE, mqttMessage);
            /*6*/
            case "web/statistics/window" -> updateDeviceStatistics(WINDOW, mqttMessage);
            /*7*/
            case "web/statistics/door" -> updateDeviceStatistics(DOOR, mqttMessage);
            /*8*/
            case "web/statistics/stove" -> updateDeviceStatistics(STOVE, mqttMessage);
            /*9*/
            case "web/statistics/fan" -> updateDeviceStatistics(FAN, mqttMessage);
            /*10*/
            case "web/statistics/electricity_consumption" -> updateDeviceStatistics(ELECTRICITY_CONSUMPTION, mqttMessage);
            /*11*/
            case "web/statistics/indoor_heating" -> updateDeviceStatistics(INDOOR_HEATING, mqttMessage);
            /*12*/
            case "web/statistics/loft_heating" -> updateDeviceStatistics(LOFT_HEATING, mqttMessage);
            /*13*/
            case "web/statistics/twilight" -> updateDeviceStatistics(TWILIGHT, mqttMessage);
            /*14*/
            case "web/statistics/power_cut" -> updateDeviceStatistics(POWER_CUT, mqttMessage);
            /*15*/
            case "web/statistics/indoor_light" -> updateDeviceStatistics(INDOOR_LIGHT, mqttMessage);
            /*16*/
            case "web/statistics/outdoor_light" -> updateDeviceStatistics(OUTDOOR_LIGHT, mqttMessage);
            /*17*/
            case "web/statistics/auto_mode" -> updateDeviceStatistics(AUTO_MODE, mqttMessage);
            /*18*/
            case "web/statistics/user" -> setUserCredentials(mqttMessage);
            /*19*/
            case "web/statistics/registration" -> registerUserCredentials(mqttMessage);

            default -> throw new IllegalStateException("Unexpected value: " + topic);
        }
        System.out.println("\u001b[33m************************************************\u001b[0m");
    }

    private void registerUserCredentials(MqttMessage mqttMessage) {
        USER.setUserSet(mqttMessage.toString().equals("true"));
    }

    private void setUserCredentials(MqttMessage mqttMessage) {
        String[] userCredentials = new Gson().fromJson(mqttMessage.toString(), String[].class);
        if (userCredentials[0] != null) {
            USER.setAccountName(userCredentials[0]);
            USER.setPassword(userCredentials[1]);
            USER.setName(userCredentials[2]);
            USER.setEmailAddress(userCredentials[3]);
            USER.setUserSet(true);
        }
    }

    /**
     * Is splitting the possibilities in two: event_based, or average hourly data.
     *
     * @param device         specific statistics
     * @param statisticsData received from server
     */
    private void updateDeviceStatistics(StatisticsData device, MqttMessage statisticsData) {
        switch (device.getType()) {
            case EVENT_BASED -> updateEventBasedStatistics(device, statisticsData);
            case AVERAGE_DATA -> updateAverageDataBasedStatistics(device, statisticsData);
        }
        device.setNewStatisticsArrived(true);
    }

    /**
     * Handles the event based statistics by setting the receiver statistics on the corresponding
     * attribute.
     *
     * @param device         the statistics type.
     * @param statisticsData received from the server
     */
    private void updateEventBasedStatistics(StatisticsData device, MqttMessage statisticsData) {
        Gson gson = new Gson();
        Map<Date, Integer> statistics = gson.fromJson(String.valueOf(statisticsData), Map.class);
        if (List.of(FIRE_ALARM, BURGLAR_ALARM, WATER_LEAKAGE, POWER_CUT,
                WINDOW, DOOR, STOVE, FAN, INDOOR_HEATING, LOFT_HEATING, TWILIGHT,
                INDOOR_LIGHT, OUTDOOR_LIGHT, AUTO_MODE).contains(device)) {
            device.setEventBasedStatistics(statistics);
        }
    }

    /**
     * Handles the avg data based statistics by setting the receiver statistics on the corresponding
     * attribute.
     *
     * @param device         the statistics type
     * @param statisticsData receiver from the server
     */
    private void updateAverageDataBasedStatistics(StatisticsData device, MqttMessage statisticsData) {
        Gson gson = new Gson();
        if (List.of(INDOOR_TEMPERATURE, OUTDOOR_TEMPERATURE).contains(device)) { // 'double' values
            Map<Date, Double> statistics = gson.fromJson(String.valueOf(statisticsData), Map.class);
            device.setAverageDoubleStatistics(statistics);
        } else if (ELECTRICITY_CONSUMPTION == device) {
            Map<Date, Integer> statistics = gson.fromJson(String.valueOf(statisticsData), Map.class);
            device.setAverageIntegerStatistics(statistics);
        }
    }
}
