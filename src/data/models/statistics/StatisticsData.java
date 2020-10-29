package data.models.statistics;

import data.models.devices.Devices;

import java.util.Date;
import java.util.HashMap;

/**
 * All the devices are represented and each can store the data required
 * for the device-specific statistics display.
 */
public enum StatisticsData {
    /*1*/  FIRE_ALARM,               // event based
    /*2*/  HOUSEBREAKING_ALARM,      // event based
    /*3*/  WATER_LEAKAGE,            // event based
    /*4*/  INDOOR_TEMPERATURE,       // avg data
    /*5*/  OUTDOOR_TEMPERATURE,      // avg data
    /*6*/  WINDOW,                   // event based
    /*7*/  DOOR,                     // event based
    /*8*/  ELECTRICITY_CONSUMPTION,  // avg data
    /*9*/  TWILIGHT,                 // event based
    /*10*/ POWER_CUT,                // event based
    /*11*/ INDOOR_LIGHT,             // avg data
    /*12*/ OUTDOOR_LIGHT             // avg data
    ;

    private Date startingWith;
    private Date untilIncluding;
    private Type type;
    private HashMap<Date, Integer> averageDataStatistics;
    private HashMap<Date, Devices.State> eventBasedStatistics;

    public Date getStartingWith() {
        return startingWith;
    }

    public void setStartingWith(Date startingWith) {
        this.startingWith = startingWith;
    }

    public Date getUntilIncluding() {
        return untilIncluding;
    }

    public void setUntilIncluding(Date untilIncluding) {
        this.untilIncluding = untilIncluding;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public HashMap<Date, Integer> getAverageDataStatistics() {
        return averageDataStatistics;
    }

    public void setAverageDataStatistics(HashMap<Date, Integer> averageDataStatistics) {
        this.averageDataStatistics = averageDataStatistics;
    }

    public HashMap<Date, Devices.State> getEventBasedStatistics() {
        return eventBasedStatistics;
    }

    public void setEventBasedStatistics(HashMap<Date, Devices.State> eventBasedStatistics) {
        this.eventBasedStatistics = eventBasedStatistics;
    }

    enum Type {
        EVENT_BASED,
        AVERAGE_DATA
    }
}
