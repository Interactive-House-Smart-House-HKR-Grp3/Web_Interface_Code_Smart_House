package data.services.local;

import data.interfaces.data_logic.DataLogic;
import data.models.devices.Devices;
import data.models.mqtt_topics.server_database.ServerRequestsTopics;
import data.models.mqtt_topics.smart_house.SMHOutputTopics;
import data.models.statistics.StatisticsData;
import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Used to publish state or statistics requests on the corresponding mqtt topics.
 * For this, is using a MQTTConnectionHandler instance.
 */
public class RequestManager implements DataLogic {

    private final MQTTConnectionHandler conn = MQTTConnectionHandler.getInstance();
    private static RequestManager requestManager;

    public static RequestManager getInstance() throws MqttException {
        return (requestManager == null ? new RequestManager() : requestManager);
    }

    public RequestManager() throws MqttException {
    }

    /**
     * Publishes a message containing the new user required state for a particular
     * device, on it's corresponding 'cmd' topic.
     * @param device to change the state of
     * @param nextState a state code:
     *                  1 = "true"
     *                  2 = "false"
     * @throws MqttException when mqtt connection errors.
     */
    @Override
    public void changeStateTo(Devices device, int nextState) throws MqttException {
        switch (device) {
            case INDOOR_LIGHT -> conn.getClient().publish(SMHOutputTopics.INDOOR_LIGHT.getTopicRegisteredName(),
                    new MqttMessage((nextState == 1 ? "true" : "false").getBytes()));
            case OUTDOOR_LIGHT -> conn.getClient().publish(SMHOutputTopics.OUTDOOR_LIGHT.getTopicRegisteredName(),
                    new MqttMessage((nextState == 1 ? "true" : "false").getBytes()));
            case FIRE_ALARM, BURGLAR_ALARM -> conn.getClient().publish(SMHOutputTopics.ARMING_ALARM.getTopicRegisteredName(),
                    new MqttMessage((nextState == 1 ? "true" : "").getBytes()));
            case FAN -> handleFanRequest(nextState);
            case HEATING_INDOOR -> conn.getClient().publish(SMHOutputTopics.HEATING_INDOOR.getTopicRegisteredName(),
                    new MqttMessage((nextState == 1 ? "true" : "false").getBytes()));
            case HEATING_LOFT -> conn.getClient().publish(SMHOutputTopics.HEATING_LOFT.getTopicRegisteredName(),
                    new MqttMessage((nextState == 1 ? "true" : "false").getBytes()));
            case AUTO_MODE -> handleAMRequest(nextState);
        }
    }

    /**
     * The AUTO-MODE feature can take two different types of requests:
     * change state or set a temp mark value.
     *
     * @param nextState can contain:
     *                  change state (1 or 2)
     *                  set a temperature mark (5 to 25, Celsius)
     * @throws MqttException when mqtt connection error.
     */
    private void handleAMRequest (int nextState) throws MqttException {
        if(nextState > 2){ // then is about the Celsius value the AM to be set at;
            conn.getClient().publish(SMHOutputTopics.AUTO_MODE_TEMPERATURE_MARK.getTopicRegisteredName(),
                    new MqttMessage(String.valueOf(nextState).getBytes()));
        }else{ // is about setting the state
            conn.getClient().publish(SMHOutputTopics.AUTO_MODE_TEMPERATURE_MARK.getTopicRegisteredName(),
                    new MqttMessage((nextState == 1 ? "true" : "false").getBytes()));
        }
    }

    /**
     * A FAN device can request two different types of requests:
     * change the state, or set a speed.
     *
     * @param nextState can contain:
     *                  change state (1 or 2)
     *                  set a speed (3 to 7, representing 5 speeds)
     * @throws MqttException
     */
    private void handleFanRequest (int nextState) throws MqttException {
        if(nextState > 2){ // then is about the speed ( 3 = speed 1; 4 = speed2; ... 7 = speed 5);
            conn.getClient().publish(SMHOutputTopics.FAN_SPEED.getTopicRegisteredName(),
                    new MqttMessage(String.valueOf(nextState - 2).getBytes()));
        }else{ // is about setting the state
            conn.getClient().publish(SMHOutputTopics.FAN.getTopicRegisteredName(),
                    new MqttMessage((nextState == 1 ? "true" : "false").getBytes()));
        }
    }

    /**
     * Requires device specific statistics on  the corresponding topic,
     * waits for the new statistics to be read/set,
     * and returns the statistics with the data set.
     * @param statisticsData requested.
     * @param periodCode reflects the period:
     *                   1 = last 24 hours,
     *                   2 = last week,
     *                   3 = last month.
     * @return statisticsData that has been updated with the last statistics.
     * @throws MqttException when mqtt errors.
     */
    @Override
    public StatisticsData requestStatistics(StatisticsData statisticsData, int periodCode) throws MqttException {
        String topic = getServerPublishingTopic(statisticsData); // get the corresponding topic
        conn.getClient().publish(topic, new MqttMessage(String.valueOf(periodCode).getBytes())); // publish the request
        // waits until the listener corresponding case
        // sets the newStatisticsArrived to true
        while(!statisticsData.isNewStatisticsArrived()){}

        return statisticsData;
    }

    /**
     * Checks the match between the statistics and the corresponding topic.
     * @param statisticsData required.
     * @return the corresponding topic String value.
     */
    private String getServerPublishingTopic(StatisticsData statisticsData){
        String topic = "";
        for (ServerRequestsTopics topics: ServerRequestsTopics.values()){
            if (statisticsData.name().equals(topics.name())){
                topic = topics.getTopicRegisteredName();
            }
        }
        // In case the loop is not working
    /*    switch (statisticsData){
            case FIRE_ALARM -> topic = ServerRequestsTopics.FIRE_ALARM.getTopicRegisteredName();
            case BURGLAR_ALARM-> topic = ServerRequestsTopics.BURGLAR_ALARM.getTopicRegisteredName();
            case WATER_LEAKAGE -> topic = ServerRequestsTopics.WATER_LEAKAGE.getTopicRegisteredName();
            case INDOOR_TEMPERATURE -> topic = ServerRequestsTopics.INDOOR_TEMPERATURE.getTopicRegisteredName();
            case OUTDOOR_TEMPERATURE -> topic = ServerRequestsTopics.OUTDOOR_TEMPERATURE.getTopicRegisteredName();
            case WINDOW-> topic = ServerRequestsTopics.WINDOW.getTopicRegisteredName();
            case DOOR -> topic = ServerRequestsTopics.DOOR.getTopicRegisteredName();
            case STOVE -> topic = ServerRequestsTopics.STOVE.getTopicRegisteredName();
            case FAN -> topic = ServerRequestsTopics.FAN.getTopicRegisteredName();
            case ELECTRICITY_CONSUMPTION -> topic = ServerRequestsTopics.ELECTRICITY_CONSUMPTION.getTopicRegisteredName();
            case INDOOR_HEATING -> topic = ServerRequestsTopics.INDOOR_HEATING.getTopicRegisteredName();
            case LOFT_HEATING -> topic = ServerRequestsTopics.LOFT_HEATING.getTopicRegisteredName();
            case TWILIGHT -> topic = ServerRequestsTopics.TWILIGHT.getTopicRegisteredName();
            case POWER_CUT -> topic = ServerRequestsTopics.POWER_CUT.getTopicRegisteredName();
            case INDOOR_LIGHT -> topic = ServerRequestsTopics.INDOOR_LIGHT.getTopicRegisteredName();
            case OUTDOOR_LIGHT -> topic = ServerRequestsTopics.OUTDOOR_LIGHT.getTopicRegisteredName();
            case AUTO_MODE -> topic = ServerRequestsTopics.AUTO_MODE.getTopicRegisteredName();
        }*/
        return topic;
    }

}
