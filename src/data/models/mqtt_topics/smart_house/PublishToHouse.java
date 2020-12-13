package data.models.mqtt_topics.smart_house;

/**
 * These topics will be used to make
 * requests to the smart house.
 */
public enum PublishToHouse {
    // Send here true/false as a string to turn on/off the indoor light
    INDOOR_LIGHT("indoor_light"), /*boolean*/

    // Send here true/false as a string to turn on/off the outdoor light
    OUTDOOR_LIGHT("outdoor_light"), /*boolean*/

    // Send here true/false as a string to arm/disarm the alarm
    ARMING_ALARM("alarm"), /*boolean*/

    // Send here true/false as a string to turn on/off the fan
    FAN("fan"), /*boolean*/

    // Send here int value (not yet known) as a string to regulate fan speed
    FAN_SPEED("fan_speed"), /*int*/

    // Send here true/false as a string to turn on/off the indoor heating
    HEATING_INDOOR("heating_in"), /*boolean*/

    // Send here true/false as a string to turn on/off the heating at the loft (wind heating)
    HEATING_LOFT("heating_loft"), /*boolean*/

    // Send here true/false as a string to turn on/off the automatic
    // indoor temperature and outdoor light regulation
    AUTO_MODE("auto_mode"), /*boolean*/

    // Send the int value of the Celsius temperature, as a string.
    // The value should be between 5 and 30, and will be used as
    // a condition when auto mode is on.
    AUTO_MODE_TEMPERATURE_MARK("am_temp_value") /*int*/
    ;
    private final static String SMART_HOUSE_CMD = "smart_house/cmd/";
    private String topicRegisteredName;

    PublishToHouse(String topicRegisteredName) {
        this.topicRegisteredName = SMART_HOUSE_CMD + topicRegisteredName;
    }

    public String getTopicRegisteredName() {
        return topicRegisteredName;
    }

    public void setTopicRegisteredName(String topicRegisteredName) {
        this.topicRegisteredName = topicRegisteredName;
    }
}