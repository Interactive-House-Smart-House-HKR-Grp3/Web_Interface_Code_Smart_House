package data.models.devices;

import java.util.List;

/**
 * These devices are representing the mechanisms or the actions that are taken/performed.
 * Example: The device 'window' does not represent the window itself but the actions ->
 * states in which this object is. It can be on or off.
 * <p>
 * There are attributes that are final, and used to instantiate the object, and others
 * there are not, and can be set or changed according to the context.
 */
public enum Devices {
    /*1*/  FIRE_ALARM("FIRE_ALARM", false, true, StatisticsFormat.EVENT, null, 0, 0),
    /*2*/  BURGLAR_ALARM("BURGLAR_ALARM", true, true, StatisticsFormat.EVENT, null, 0, 0),
    /*3*/  WATER_LEAKAGE("WATER_LEAKAGE", false, true, StatisticsFormat.EVENT, null, 0, 0),
    /*4*/  INDOOR_TEMPERATURE("INDOOR_TEMPERATURE", false, true, StatisticsFormat.HOURLY_AVERAGE/*?*/, null, 0, 0),
    /*5*/  OUTDOOR_TEMPERATURE("OUTDOOR_TEMPERATURE", false, true, StatisticsFormat.HOURLY_AVERAGE/*?*/, null, 0, 0),
    /*6*/  WINDOW("WINDOW", false, true, StatisticsFormat.EVENT/*?*/, null, 0, 0),
    /*7*/  DOOR("DOOR", false, true, StatisticsFormat.EVENT/*? or an daily average ? ... */, null, 0, 0),
    /*8*/  ELECTRICITY_CONSUMPTION("ELECTRICITY_CONSUMPTION", false, true, StatisticsFormat.HOURLY_AVERAGE/*?*/, null, 0, 0),
    /*9*/  TWILIGHT("TWILIGHT", false, true, StatisticsFormat.EVENT, null, 0, 0),
    /*10*/ POWER_CUT("POWER_CUT", false, true, StatisticsFormat.EVENT, null, 0, 0),
    /*11*/ INDOOR_LIGHT("INDOOR_LIGHT", true, true, StatisticsFormat.HOURLY_AVERAGE/*?*/, null, 0, 0),
    /*12*/ OUTDOOR_LIGHT("OUTDOOR_LIGHT", true, true, StatisticsFormat.HOURLY_AVERAGE/*?*/, null, 0, 0),
    /*13*/ STOVE("STOVE", false, true, StatisticsFormat.EVENT/*?*/, null, 0, 0),
    /*14*/ FAN("FAN", true, true, StatisticsFormat.EVENT/*?*/, null, 0, 0),
    /*16*/ HEATING_INDOOR("HEATING_INDOOR", true, true, StatisticsFormat.EVENT/*?*/, null, 0, 0),
    /*17*/ HEATING_LOFT("HEATING_LOFT", true, true, StatisticsFormat.EVENT/*?*/, null, 0, 0),
    /*18*/ AUTO_MODE("AUTO_MODE", true, true, StatisticsFormat.EVENT/*?*/, null, 0, 0)
    ;

    // Final attributes, reflecting the Communication Protocol agreements
    private final String name;
    private final boolean changeableState;
    private final boolean statisticsProvider;
    private final StatisticsFormat statisticsFormat;

    // Attributes that reflect states or data that may undergo transformations, changes
    private State deviceCurrentState;
    private int intValue;
    private double doubleValue;

    Devices(String name, boolean changeableState, boolean statisticsProvider, StatisticsFormat statisticsFormat,
            State deviceCurrentState, int intValue, double doubleValue) {
        this.name = name;
        this.changeableState = changeableState;
        this.statisticsProvider = statisticsProvider;
        this.statisticsFormat = statisticsFormat;
        this.deviceCurrentState = deviceCurrentState;
        this.intValue = intValue;
        this.doubleValue = doubleValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public boolean isChangeableState() {
        return changeableState;
    }

    public boolean isStatisticsProvider() {
        return statisticsProvider;
    }

    public State getDeviceCurrentState() {
        // connect to mqtt broker

        // read topic for that specific device

        // set current state

        // return this.device.current state

        return deviceCurrentState;
    }

    public void setDeviceCurrentState(int deviceCurrentState) {
        // connect to the mqtt broker

        // publish on the corresponding topic the new state

        // set the new state.

        this.deviceCurrentState = deviceCurrentState == 1 ?
                (List.of(DOOR, WINDOW).contains( this) ? State.OPEN :
                        List.of(BURGLAR_ALARM, FIRE_ALARM).contains(this) ? State.ARMED : State.ON) :
                (List.of(DOOR, WINDOW).contains( this) ? State.CLOSED : State.OFF);
    }

    public StatisticsFormat getStatisticsFormat() {
        return statisticsFormat;
    }

    @Override
    public String toString() {
        return "Devices{" +
                "\n    name = " + name +
                "\n    intValue = " + intValue +
                "\n    doubleValue = " + doubleValue +
                "\n    changeableState = " + changeableState +
                "\n    statisticsProvider = " + statisticsProvider +
                "\n    deviceCurrentState = " + deviceCurrentState +
                "\n    statisticsFormat = " + statisticsFormat +
                '}';
    }

    /**
     * Reflects the current state of a device representation.
     */
    public enum State {
        ON,
        OFF,
        ARMED,
        TRIGGERED,
        OPEN,
        CLOSED
    }

    /**
     * Represents the frequency at which the web-based interface requires
     * data related to a certain device, in order to be able to generate an
     * answer to a user-defined statistics request.
     */
    public enum StatisticsFormat {
        DAILY_AVERAGE,        // explain ...
        HOURLY_AVERAGE,       // Used _ The average value for one hour
        WEEKLY_AVERAGE,       // explain ...
        HOURLY,               // explain ...
        DAILY,                // explain ...
        WEEKLY,               // explain ...
        EVERY_MINUTE,         // explain ...
        EVERY_SECOND,         // explain ...
        EVERY_HALF_MINUTE,    // explain ...
        EVENT,                // Used _ When a state is changed
        CLOSING_TIME,         // explain ...
        STARTING_TIME         // explain ...
    }
}
