package data.services.local;

import data.interfaces.data_logic.DataLogic;
import data.models.devices.Devices;
import data.models.mqtt_topics.server_database.ServerRequestsTopics;
import data.models.mqtt_topics.smart_house.SMHOutputTopics;
import data.models.statistics.StatisticsData;
import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class DeviceController implements DataLogic {

    private final MQTTConnectionHandler conn = MQTTConnectionHandler.getInstance();
    private static DeviceController deviceController;

    public static DeviceController getInstance() throws MqttException {
        return (deviceController == null ? new DeviceController() : deviceController);
    }

    public DeviceController() throws MqttException {
    }

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

    private void handleAMRequest (int nextState) throws MqttException {
        if(nextState > 2){ // then is about the Celsius value the AM to be set at;
            conn.getClient().publish(SMHOutputTopics.AUTO_MODE_TEMPERATURE_MARK.getTopicRegisteredName(),
                    new MqttMessage(String.valueOf(nextState).getBytes()));
        }else{ // is about setting the state
            conn.getClient().publish(SMHOutputTopics.AUTO_MODE_TEMPERATURE_MARK.getTopicRegisteredName(),
                    new MqttMessage((nextState == 1 ? "true" : "false").getBytes()));
        }
    }

    private void handleFanRequest (int nextState) throws MqttException {
        if(nextState > 2){ // then is about the speed ( 3 = speed 1; 4 = speed2; ... 7 = speed 5);
            conn.getClient().publish(SMHOutputTopics.FAN_SPEED.getTopicRegisteredName(),
                    new MqttMessage(String.valueOf(nextState - 2).getBytes()));
        }else{ // is about setting the state
            conn.getClient().publish(SMHOutputTopics.FAN.getTopicRegisteredName(),
                    new MqttMessage((nextState == 1 ? "true" : "false").getBytes()));
        }
    }

    @Override
    public StatisticsData requestStatistics(StatisticsData statisticsData, int periodCode) throws MqttException {
        String topic = getServerPublishingTopic(statisticsData);
        conn.getClient().publish(topic, new MqttMessage(String.valueOf(periodCode).getBytes()));
        while(!statisticsData.isNewStatisticsArrived()){}
        return statisticsData;
    }

    private String getServerPublishingTopic(StatisticsData statisticsData){
        String topic = "";
        for (ServerRequestsTopics topics: ServerRequestsTopics.values()){
            if (statisticsData.name().equals(topics.name())){
                topic = topics.getTopicRegisteredName();
            }
        }
        // In case the for loop is not working
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
