package data.interfaces.data_logic;

import data.models.devices.Devices;
import data.models.statistics.StatisticsData;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface DataLogic {

    /**State related*/
    void changeStateTo(Devices device, int nextState) throws MqttException;

    /**Data Statistics related*/
    StatisticsData /* statistics data*/ requestStatistics
            (StatisticsData type, int periodCode) throws MqttException;
}
