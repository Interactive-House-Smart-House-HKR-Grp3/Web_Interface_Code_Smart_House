package data.models.devices;

import data.models.mqtt_topics.smart_house.SMHOutputTopics;
import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

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
    /*1*/  FIRE_ALARM(false, true, StatisticsFormat.EVENT, Category.ALARMS),
    /*2*/  BURGLAR_ALARM(true, true, StatisticsFormat.EVENT, Category.ALARMS),
    /*3*/  WATER_LEAKAGE(false, true, StatisticsFormat.EVENT, Category.ALARMS),
    /*4*/  INDOOR_TEMPERATURE(false, true, StatisticsFormat.HOURLY_AVERAGE, Category.TEMPERATURES),
    /*5*/  OUTDOOR_TEMPERATURE(false, true, StatisticsFormat.HOURLY_AVERAGE, Category.TEMPERATURES),
    /*6*/  WINDOW(false, true, StatisticsFormat.EVENT, Category.MISC),
    /*7*/  DOOR(false, true, StatisticsFormat.EVENT, Category.MISC),
    /*8*/  ELECTRICITY_CONSUMPTION(false, true, StatisticsFormat.HOURLY_AVERAGE, Category.STATUS),
    /*9*/  TWILIGHT(false, true, StatisticsFormat.EVENT, Category.STATUS),
    /*10*/ POWER_CUT(false, true, StatisticsFormat.EVENT, Category.ALARMS),
    /*11*/ INDOOR_LIGHT(true, true, StatisticsFormat.EVENT, Category.LIGHTS),
    /*12*/ OUTDOOR_LIGHT(true, true, StatisticsFormat.EVENT, Category.LIGHTS),
    /*13*/ STOVE(false, true, StatisticsFormat.EVENT, Category.MISC),
    /*14*/ FAN(true, true, StatisticsFormat.EVENT, Category.MISC),
    /*16*/ HEATING_INDOOR(true, true, StatisticsFormat.EVENT, Category.TEMPERATURES),
    /*17*/ HEATING_LOFT(true, true, StatisticsFormat.EVENT, Category.TEMPERATURES),
    /*18*/ AUTO_MODE(true, true, StatisticsFormat.EVENT, Category.LIGHTS);

    // Final attributes, reflecting the Communication Protocol agreements
    private final boolean changeableState;
    private final boolean statisticsProvider;
    private final StatisticsFormat statisticsFormat;
    private final Category category;

    // Attributes that reflect states or data that may undergo transformations, changes
    private State deviceCurrentState;
    private int intValue;
    private double doubleValue;
    private boolean newStateRead;

    Devices(boolean changeableState, boolean statisticsProvider, StatisticsFormat statisticsFormat, Category category) {
        this.changeableState = changeableState;
        this.statisticsProvider = statisticsProvider;
        this.statisticsFormat = statisticsFormat;
        this.category = category;
        this.deviceCurrentState = State.NOT_IMPLEMENTED;
        this.intValue = 0;
        this.doubleValue = 0;
    }

    public boolean isNewStateRead() {
        return newStateRead;
    }

    public void setNewStateRead(boolean newStateRead) {
        this.newStateRead = newStateRead;
    }

    public int getIntValue() {
        return intValue;
    }

    public Category getCategory() {
        return category;
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

    /**
     * Used to publish a new state request for a particular device.
     * This method should be called through a device object that needs
     * to change its state.
     *
     * @param changeStateTo a state code representing the new desired state:
     *                      1 = ON, OPEN,
     *                      2 = OFF, CLOSED,
     *                      3 = TRIGGERED.
     * @throws MqttException when the mqtt connection is not functioning properly.
     */
    public void changeStateTo(int changeStateTo) throws MqttException {
        this.setNewStateRead(false);
        String topicName = null;
        for (SMHOutputTopics topic : SMHOutputTopics.values()) {
            if (this.name().equals(topic.name())) {
                topicName = topic.getTopicRegisteredName();
            }
        }
        MQTTConnectionHandler.mqttClient.publish(topicName,
                new MqttMessage((changeStateTo == 1 ? "true" : "false").getBytes()));
    }

    /**
     * Used internally to convert the state code to a corresponding State value.
     *
     * @param changeStateTo the new state code.
     */
    public void setDeviceCurrentState(int changeStateTo) {
        this.deviceCurrentState = changeStateTo == 1 ?
                (List.of(DOOR, WINDOW).contains(this) ? State.OPEN :
                        List.of(BURGLAR_ALARM, FIRE_ALARM).contains(this) ? State.ARMED : State.ON) :
                (List.of(DOOR, WINDOW).contains(this) ? State.CLOSED : State.OFF);

        // for twilight, check the values. If less than 400 night. if passes 400, day.
    }

    public StatisticsFormat getStatisticsFormat() {
        return statisticsFormat;
    }

    @Override
    public String toString() {
        return this.name() + ":" +
                "\n    deviceCurrentState = " + deviceCurrentState +
                (intValue != 0 ? ("\n    intValue = " + intValue) : "") +
                (doubleValue != 0 ? ("\n    doubleValue = " + doubleValue) : "") +
                '}';
    }

    /**
     * Reflects the current state of a device representation.
     */
    public enum State {
        ON,
        OFF,
        ARMED,
        DAY,
        NIGHT,
        TRIGGERED,
        OPEN,
        CLOSED,
        NOT_IMPLEMENTED
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

    public enum Category {
        STATUS,
        ALARMS,
        LIGHTS,
        TEMPERATURES,
        MISC
    }
}