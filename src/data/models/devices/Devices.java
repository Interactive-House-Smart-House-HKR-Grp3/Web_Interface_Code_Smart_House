package data.models.devices;

/**
 * These devices are representing the mechanisms or the actions that are taken/performed.
 * Example: The device 'window' does not represent the window itself but the actions ->
 * states in which this object is. It can be on or off.
 * <p>
 * There are attributes that are final, and used to instantiate the object, and others
 * there are not, and can be set or changed according to the context.
 */
public enum Devices {
    /*1*/  FIRE_ALARM(true/*?*/, true, Statistics.EVENT, null, 0, 0),
    /*2*/  HOUSEBREAKING_ALARM(true /*?*/, true, Statistics.EVENT, null, 0, 0),
    /*3*/  WATER_LEAKAGE(false/*?*/, true, Statistics.EVENT, null, 0, 0),
    /*4*/  INDOOR_TEMPERATURE(true, true, Statistics.DAILY_AVERAGE/*?*/, null, 0, 0),
    /*5*/  OUTDOOR_TEMPERATURE(true, true, Statistics.DAILY_AVERAGE/*?*/, null, 0, 0),
    /*6*/  WINDOW(false/*?*/, true, Statistics.EVENT/*?*/, null, 0, 0),
    /*7*/  DOOR(false/*?*/, true, Statistics.EVENT/*? or an daily average ? ... */, null, 0, 0),
    /*8*/  ELECTRICITY_CONSUMPTION(false, true, Statistics.DAILY_AVERAGE/*?*/, null, 0, 0),
    /*9*/  TWILIGHT(false/*?*/, true, Statistics.EVENT, null, 0, 0),
    /*10*/ POWER_CUT(false/*?*/, true, Statistics.EVENT, null, 0, 0),
    /*11*/ INDOOR_LIGHT(true, true, Statistics.DAILY_AVERAGE/*?*/, null, 0, 0),
    /*12*/ OUTDOOR_LIGHT(true, true, Statistics.DAILY_AVERAGE/*?*/, null, 0, 0),
    ;

    // Final attributes, reflecting the Communication Protocol agreements
    private final boolean changeableState;
    private final boolean statisticsProvider;
    private final Statistics statisticsFormat;

    // Attributes that reflect states or data that may undergo transformations, changes
    private State deviceCurrentState;
    private int intValue;
    private double doubleValue;

    Devices(boolean changeableState, boolean statisticsProvider, Statistics statisticsFormat,
                   State deviceCurrentState, int intValue, double doubleValue) {
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
        return deviceCurrentState;
    }

    public void setDeviceCurrentState(State deviceCurrentState) {
        this.deviceCurrentState = deviceCurrentState;
    }

    public Statistics getStatisticsFormat() {
        return statisticsFormat;
    }

    @Override
    public String toString() {
        return "Devices{" +
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
        TRIGGERED,
        OPEN,
        CLOSED

    }

    /**
     * Represents the frequency at which the web-based interface requires
     * data related to a certain device, in order to be able to generate an
     * answer to a user-defined statistics request.
     */
    public enum Statistics {
        DAILY_AVERAGE,        // explain ...
        HOURLY_AVERAGE,       // explain ...
        WEEKLY_AVERAGE,       // explain ...
        HOURLY,               // explain ...
        DAILY,                // explain ...
        WEEKLY,               // explain ...
        EVERY_MINUTE,         // explain ...
        EVERY_SECOND,         // explain ...
        EVERY_HALF_MINUTE,    // explain ...
        EVENT,                // explain ...
        CLOSING_TIME,         // explain ...
        STARTING_TIME         // explain ...
        ;

        // The number of days SINCE the data should be provided.
        // Ex: '14' - means starting with 14 days ago.
        private int startingWith;

        // The number of days ago, UNTIL data should be provided.
        // EX: '0' - means until the current date.
        private int until;

        public int getStartingWith() {
            return startingWith;
        }

        public void setStartingWith(int startingWith) {
            this.startingWith = startingWith;
        }

        public int getUntil() {
            return until;
        }

        public void setUntil(int until) {
            this.until = until;
        }
    }
}
