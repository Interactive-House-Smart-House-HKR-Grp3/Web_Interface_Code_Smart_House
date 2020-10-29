package data.services.mqtt;

public enum Topics {
    INDOOR_LIGHT("indoor_light"),
    OUTDOOR_LIGHT("outdoor_light"),
    ALARM("alarm"),
    FAN("fan"),
    HEATING_INDOOR("heating_indoor"),
    HEATING_WIND("heating_wind"),
    DOOR("door")
    ;
    private final static String PREFIX = "smart_house/";
    private String topicRegisteredName;

    Topics(String topicRegisteredName) {
        this.topicRegisteredName = PREFIX + topicRegisteredName;
    }

    public String getTopicRegisteredName() {
        return topicRegisteredName;
    }

    public void setTopicRegisteredName(String topicRegisteredName) {
        this.topicRegisteredName = topicRegisteredName;
    }
}
