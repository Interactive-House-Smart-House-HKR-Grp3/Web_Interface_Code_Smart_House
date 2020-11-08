package data.models.mqtt_topics.server_database;

/**
 * These topics will be PUBLISHED by the web
 * and will be read by the server.
 * The published data will contain the
 * statistics requested for statistics.
 *
 * All the requests (MqttMessage) will contain a string representation
 * of an integer value, symbolising:
 * [1] = last day,
 * [2] = last week,
 * [3] = last month.
 *
 */
public enum ServerRequestsTopics {

    /*1*/  FIRE_ALARM("fire_alarm"),
    /*2*/  BURGLAR_ALARM("burglar_alarm"),
    /*3*/  WATER_LEAKAGE("water_leakage"),
    /*4*/  INDOOR_TEMPERATURE("indoor_temperature"),
    /*5*/  OUTDOOR_TEMPERATURE("outdoor_temperature"),
    /*6*/  WINDOW("window"),
    /*7*/  DOOR("door"),
    /*8*/  STOVE("stove"),
    /*9*/  FAN("fan"),
    /*10*/ ELECTRICITY_CONSUMPTION("electricity_consumption"),
    /*11*/ INDOOR_HEATING("indoor_heating"),
    /*12*/ LOFT_HEATING("loft_heating"),
    /*13*/ TWILIGHT("twilight"),
    /*14*/ POWER_CUT("power_cut"),
    /*15*/ INDOOR_LIGHT("indoor_light"),
    /*16*/ OUTDOOR_LIGHT("outdoor_light"),
    /*17*/ AUTO_MODE("auto_mode"),

    /*18*/ USER("user"),
    /*19*/ CREATE_NEW_USER("create_new_user")
    ;

    private final static String WEB_RESPONSE= "web/request/";
    private final String topicRegisteredName;

    ServerRequestsTopics(String topicRegisteredName) {
        this.topicRegisteredName = WEB_RESPONSE + topicRegisteredName;
    }

    public static String getWebResponse() {
        return WEB_RESPONSE;
    }

    public String getTopicRegisteredName() {
        return topicRegisteredName;
    }
}