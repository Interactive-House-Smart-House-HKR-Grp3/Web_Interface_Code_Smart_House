package data.models.statistics;

import java.util.Date;
import java.util.Map;

/**
 * All the devices are represented and each
 * can store the data required for the
 * device-specific statistics display.
 */
public enum StatisticsData {

    /*1*/  FIRE_ALARM(0, Type.EVENT_BASED, null, null, null),                // Map<Date, Devices.State>
    /*2*/  BURGLAR_ALARM(0, Type.EVENT_BASED, null, null, null),             // Map<Date, Devices.State>
    /*3*/  WATER_LEAKAGE(0, Type.EVENT_BASED, null, null, null),             // Map<Date, Devices.State>
    /*4*/  INDOOR_TEMPERATURE(0, Type.AVERAGE_DATA, null, null, null),       // Map<Date, Double>
    /*5*/  OUTDOOR_TEMPERATURE(0, Type.AVERAGE_DATA, null, null, null),      // Map<Date, Double>
    /*6*/  WINDOW(0, Type.EVENT_BASED, null, null, null),                    // Map<Date, Devices.State>
    /*7*/  DOOR(0, Type.EVENT_BASED, null, null, null),                      // Map<Date, Devices.State>
    /*8*/  STOVE(0, Type.EVENT_BASED, null, null, null),                     // Map<Date, Devices.State>
    /*9*/  FAN(0, Type.EVENT_BASED, null, null, null),                       // Map<Date, Devices.State>
    /*10*/ ELECTRICITY_CONSUMPTION(0, Type.AVERAGE_DATA, null, null, null),  // Map<Date, Integer>
    /*11*/ INDOOR_HEATING(0, Type.EVENT_BASED, null, null, null),            // Map<Date, Devices.State>
    /*12*/ LOFT_HEATING(0, Type.EVENT_BASED, null, null, null),              // Map<Date, Devices.State>
    /*13*/ TWILIGHT(0, Type.EVENT_BASED, null, null, null),                  // Map<Date, Devices.State>
    /*14*/ POWER_CUT(0, Type.EVENT_BASED, null, null, null),                 // Map<Date, Devices.State>
    /*15*/ INDOOR_LIGHT(0, Type.EVENT_BASED, null, null, null),              // Map<Date, Devices.State>
    /*16*/ OUTDOOR_LIGHT(0, Type.EVENT_BASED, null, null, null),             // Map<Date, Devices.State>
    /*17*/ AUTO_MODE(0, Type.EVENT_BASED, null, null, null),                 // Map<Date, Devices.State>
    ;

    private int forPeriod; // [1] = last day, [2] = last week, [3] = last month.
    private Type type;
    private Map<Date, Integer> averageIntegerStatistics;
    private Map<Date, Double> averageDoubleStatistics;
    private Map<Date, Integer> eventBasedStatistics; // 1 = ON; 2 = OFF; 3 = Triggered

    StatisticsData(int forPeriod, Type type, Map<Date, Integer> averageIntegerStatistics,
                   Map<Date, Double> averageDoubleStatistics, Map<Date, Integer> eventBasedStatistics) {
        this.forPeriod = forPeriod;
        this.type = type;
        this.averageIntegerStatistics = averageIntegerStatistics;
        this.averageDoubleStatistics = averageDoubleStatistics;
        this.eventBasedStatistics = eventBasedStatistics;
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

    ///////////////////
    public Map<Date, Integer> getEventBasedStatistics() {
        // connect to mqtt broker

        // publish the request on the corresponding topic

        // wait for the listener to provide new data on the subscribed corresponding topic

        // format the data ...... HashMap<Date, int[stateCode, value]>

        // set the statistics

        // return the statistics.


        return eventBasedStatistics;
    }

    public Map<Date, Integer> getAverageIntegerStatistics() {
        return averageIntegerStatistics;
    }

    public Map<Date, Double> getAverageDoubleStatistics() {
        return averageDoubleStatistics;
    }
    ///////////////////

    public enum Type {
        EVENT_BASED,
        AVERAGE_DATA
    }
}