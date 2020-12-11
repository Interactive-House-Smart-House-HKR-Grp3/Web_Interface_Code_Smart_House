package data.models.mqtt_topics.smart_house;

/**
 * These topics will only be read
 * and used as live-data providers.
 * All the data is read as a String
 * and needs to be parsed to it's
 * correct type.
 */
public enum SmartHouseSubscribedTopics {
    // Send here true/false as a string to turn on/off the indoor light
    INDOOR_LIGHT("indoor_light"), /*boolean*/

    // Send here true/false as a string to turn on/off the outdoor light
    OUTDOOR_LIGHT("outdoor_light"), /*boolean*/

    // When alarm is fired, you will get true.
    FIRE_ALARM("fire_alarm"), /*boolean*/

    // When alarm is armed, and door is open this topic will get a message true.
    HOUSE_BREAKING_ALARM("hbreak_alarm"), /*boolean*/

    // When water leakage switch is active this topic will get message true.
    WATER_LEAKAGE("leakage"), /*boolean*/

    // This is a constant output that will send temperature data each second.
    TEMP_INDOOR("temp_indoor"), /*Celsius as a Double*/

    // This is a constant output that will send temperature data each second.
    TEMP_OUTDOOR("temp_outdoor"), /*Celsius as a Double*/

    // When a switch stove is activated this topic will receive true.
    STOVE("stove"), /*boolean*/

    // When a switch window is activated this topic will receive true.
    WINDOW("window"), /*boolean*/

    // This is a constant output that will send el. consumption data each second.
    ELECTRICITY_CONSUMPTION("el_consumption"), /*watt in int*/

    // This is a constant output that will send light to voltage data (how dark it is outside)
    TWILIGHT("twilight"), /*int*/

    // When a power is cut to the house, the sensor will publish false to the topic
    POWER_CUT("power_cut"), /*boolean*/

    // Send here true/false as a string to arm/disarm the alarm
    BURGLAR_ALARM_ARMED("alarm"), /*boolean*/

    // Send here int value (not yet known) as a string to regulate fan speed
    FAN_SPEED("fan_speed"), /*int*/

    // Send here true/false as a string to turn on/off the fan
    FAN("fan"), /*boolean*/

    // Send here true/false as a string to turn on/off the indoor heating
    HEATING_INDOOR("heating_in"), /*boolean*/

    // Send here true/false as a string to turn on/off the heating at the loft (wind heating)
    HEATING_LOFT("heating_loft"), /*boolean*/

    // Publishes the current state of the door
    DOOR("door"), /*boolean*/

    // Send here true/false as a string to turn on/off the automatic indoor temperature
    // and outdoor light regulation
    AUTO_MODE("auto_mode"), /*boolean*/
    ;
    private final String SMART_HOUSE_GUI = "smart_house/gui/";
    private final String TOPIC_REGISTERED_NAME;

    SmartHouseSubscribedTopics(String topicRegisteredName) {
        this.TOPIC_REGISTERED_NAME = SMART_HOUSE_GUI + topicRegisteredName;
    }

    public String getTopicRegisteredName() {
        return TOPIC_REGISTERED_NAME;
    }

    public String getSMART_HOUSE_GUI() {
        return SMART_HOUSE_GUI;
    }
}
