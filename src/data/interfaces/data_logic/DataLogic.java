package data.interfaces.data_logic;

import data.models.devices.Devices;
import data.models.statistics.StatisticsData;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface DataLogic {

    /**
     * State related.
     * Request a state change for one particular device
     */
    void changeStateTo(Devices device, int nextState) throws MqttException;

    /**
     * Statistics related.
     * Request statistics of a particular type.
     * @param type the type of the statistics
     * @param periodCode reflects the period:
     *                   1 = last 24 hours,
     *                   2 = last week,
     *                   3 = last month.
     * @return the requested statistics.
     * @throws MqttException when the mqtt connection is invalid.
     */
    StatisticsData /* statistics data*/ requestStatistics
    (StatisticsData type, int periodCode) throws MqttException;
}
