package data.models.statistics;

import data.services.local.DeviceController;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Date;
import java.util.Map;

/**
 * All the devices are represented and each
 * can store the data required for the
 * device-specific statistics display.
 */
public enum StatisticsData {

    /*1*/  FIRE_ALARM(0, Type.EVENT_BASED),                // Map<Date, Devices.State>
    /*2*/  BURGLAR_ALARM(0, Type.EVENT_BASED),             // Map<Date, Devices.State>
    /*3*/  WATER_LEAKAGE(0, Type.EVENT_BASED),             // Map<Date, Devices.State>
    /*4*/  INDOOR_TEMPERATURE(0, Type.AVERAGE_DATA),       // Map<Date, Double>
    /*5*/  OUTDOOR_TEMPERATURE(0, Type.AVERAGE_DATA),      // Map<Date, Double>
    /*6*/  WINDOW(0, Type.EVENT_BASED),                    // Map<Date, Devices.State>
    /*7*/  DOOR(0, Type.EVENT_BASED),                      // Map<Date, Devices.State>
    /*8*/  STOVE(0, Type.EVENT_BASED),                     // Map<Date, Devices.State>
    /*9*/  FAN(0, Type.EVENT_BASED),                       // Map<Date, Devices.State>
    /*10*/ ELECTRICITY_CONSUMPTION(0, Type.AVERAGE_DATA),  // Map<Date, Integer>
    /*11*/ INDOOR_HEATING(0, Type.EVENT_BASED),            // Map<Date, Devices.State>
    /*12*/ LOFT_HEATING(0, Type.EVENT_BASED),              // Map<Date, Devices.State>
    /*13*/ TWILIGHT(0, Type.EVENT_BASED),                  // Map<Date, Devices.State>
    /*14*/ POWER_CUT(0, Type.EVENT_BASED),                 // Map<Date, Devices.State>
    /*15*/ INDOOR_LIGHT(0, Type.EVENT_BASED),              // Map<Date, Devices.State>
    /*16*/ OUTDOOR_LIGHT(0, Type.EVENT_BASED),             // Map<Date, Devices.State>
    /*17*/ AUTO_MODE(0, Type.EVENT_BASED),                 // Map<Date, Devices.State>
    ;

    private int forPeriod; // [1] = last day, [2] = last week, [3] = last month.
    private Type type;
    private Map<Date, Integer> averageIntegerStatistics;
    private Map<Date, Double> averageDoubleStatistics;
    private Map<Date, Integer> eventBasedStatistics; // 1 = ON; 2 = OFF; 3 = Triggered
    private boolean newStatisticsArrived;

    StatisticsData(int forPeriod, Type type) {
        this.forPeriod = forPeriod;
        this.type = type;
        this.averageIntegerStatistics = null;
        this.averageDoubleStatistics = null;
        this.eventBasedStatistics = null;
        this.newStatisticsArrived = false;
    }

    public int getForPeriod() {
        return forPeriod;
    }

    public void setForPeriod(int forPeriod) {
        this.forPeriod = forPeriod;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setAverageIntegerStatistics(Map<Date, Integer> averageIntegerStatistics) {
        this.averageIntegerStatistics = averageIntegerStatistics;
    }

    public void setAverageDoubleStatistics(Map<Date, Double> averageDoubleStatistics) {
        this.averageDoubleStatistics = averageDoubleStatistics;
    }

    public void setEventBasedStatistics(Map<Date, Integer> eventBasedStatistics) {
        this.eventBasedStatistics = eventBasedStatistics;
    }

    public Map<Date, Integer> getEventBasedStatistics() {
        return eventBasedStatistics;
    }

    public Map<Date, Integer> getAverageIntegerStatistics() {
        return averageIntegerStatistics;
    }

    public Map<Date, Double> getAverageDoubleStatistics() {
        return averageDoubleStatistics;
    }

    public boolean isNewStatisticsArrived() {
        return newStatisticsArrived;
    }

    public void setNewStatisticsArrived(boolean newStatisticsArrived) {
        this.newStatisticsArrived = newStatisticsArrived;
    }

    public StatisticsData requestStatistics(int periodIndex) throws MqttException {
        this.newStatisticsArrived = false;
        DeviceController controller = DeviceController.getInstance();
        return controller.requestStatistics(this, periodIndex);
    }

    public enum Type {
        EVENT_BASED,
        AVERAGE_DATA
    }
}