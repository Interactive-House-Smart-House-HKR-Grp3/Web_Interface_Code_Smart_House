package data.models.mqtt_topics.server_database;

/**
 * These topics will be used to read inputs from the server.
 * These are the channels through which the server sends device related statistics, as a reply to a
 *
 *
 * The server should publish a MqttMessage containing a Map<Date, Integer>
 * or a Map<Date, Double> object (depending on the topic), in a JSON format.
 *
 * The formatting can be done in the following way (on the server side):
 *      ..........................................
 *      Gson gson = new Gson();
 *      Map<Date, Integer> map = new HashMap<>();
 * //   Map<Date, Double> map = new HashMap<>();
 *
 *      map.put(date, integerValue); // starting with the oldest date // double value where needed
 *      ...
 *      map.put(date, integerValue); // ending with the newest date // double value where needed
 *
 *      client.publish("web/statistics/fire_alarm", new MqttMessage(gson.toJson(map).getBytes()));
 *      ..........................................
 *
 * Double values for: "indoor_temperature", "outdoor_temperature".
 *
 * Integer values (representing watts) for: "electricity_consumption".
 *
 * Integer values (representing the states, 1=ON, 2=OFF, 3=TRIGGERED), for: "fire_alarm",
 *      "burglar_alarm", "water_leakage", "window", "door", "stove", "fan", "indoor_heating",
 *      "loft_heating", "twilight", "power_cut", "indoor_light", "outdoor_light", "auto_mode".
 *
 *      !Obs.: state '3' is possible only for the "fire_alarm" and the "burglar_alarm" items
 *      (it will be needed to know how to process data read(and stored) from the physical house topics).
 *
 */
public enum ReadFromServer {

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
    /*19*/ REGISTRATION("registration")
    ;
    private final static String WEB_REQUEST = "web/statistics/";
    private final String topicRegisteredName;

    ReadFromServer(String topicRegisteredName) {
        this.topicRegisteredName = WEB_REQUEST + topicRegisteredName;
    }

    public static String getWebRequest() {
        return WEB_REQUEST;
    }

    public String getTopicRegisteredName() {
        return topicRegisteredName;
    }
}
