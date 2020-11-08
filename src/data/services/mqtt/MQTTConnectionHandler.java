package data.services.mqtt;

import data.models.mqtt_topics.server_database.ServerSubscribedTopics;
import data.models.mqtt_topics.smart_house.SMHSubscribedTopics;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTConnectionHandler {

    private static MQTTConnectionHandler connectionHandler;

    private MqttClient client;

    //Creating the options
    private final MqttConnectOptions CONNECTION_OPTIONS = new MqttConnectOptions();

    public static MQTTConnectionHandler getInstance() throws MqttException {
        return (connectionHandler == null ? new MQTTConnectionHandler() : connectionHandler);
    }

    public MQTTConnectionHandler() throws MqttException {
        connectMqtt();

        subscribeToSHTopics();
        subscribeToServerTopics();
/*
        // Testing read / publish  ...
        //changeStatesToClose();
        while (client.isConnected()) {
            System.out.println("1]    Test Mqtt\n2]    Quit");
            if (new Scanner(System.in).nextInt() == 1) {
               *//* boolean flag = true;
                while (flag) {
                    System.out.println("1] Turn indoor light ON\n2] Turn indoor light OFF");
                    Scanner in = new Scanner(System.in);
                    int cmd = in.nextInt();
                    flag = List.of(1, 2).contains(cmd);
                    client.publish(SMHOutputTopics.INDOOR_LIGHT.getTopicRegisteredName(),
                            new MqttMessage(( cmd == 1 ? "true" : cmd == 2 ? "false" : "").getBytes()));
                }*//*





                // Testing Json objects over MQTT
                *//*Gson gson = new Gson();
                Map<Date, Date> hash_map_test = new HashMap<>();
                Date now = Calendar.getInstance().getTime();
                Thread.sleep(5000);
                Date next = Calendar.getInstance().getTime();
                hash_map_test.put(now, next);

                String[] test = {"test1", "test2", "test3", "teat4", "test5", "test6", "test7", "teat8"};

                client.publish("smart_house/json_test", new MqttMessage(gson.toJson(hash_map_test).getBytes()));
                client.publish("smart_house/json_test", new MqttMessage(gson.toJson(test).getBytes()));
                client.publish("smart_house/json_test", new MqttMessage("test_for_json".getBytes()));

                case "smart_house/json_test":
                                System.out.println("test");
                                Gson gson = new Gson();
                                String[] str = gson.fromJson(var2.toString(), String[].class);
                                Map<Date, Date> retMap = gson.fromJson(var2.toString(), Map.class);

                                System.out.println(Arrays.toString(str));
                                System.out.println(str[0]);
                                System.out.println(str[1]);
                                System.out.println(str[2]);
                                break;*//*
            } else {
                client.close();
            }
        }*/
    }

    public void connectMqtt() {

        try {
            final String CLIENT_ID = "Web_Interface";
            final String broker = "tcp://smart-mqtthive.duckdns.org:1883";
            client = new MqttClient(broker, CLIENT_ID);

            CONNECTION_OPTIONS.setCleanSession(true);
            CONNECTION_OPTIONS.setKeepAliveInterval(180);

            client.connect(CONNECTION_OPTIONS);
            System.out.println("Broker: " + broker + ", connected!");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void subscribeToSHTopics() throws MqttException {
        for (SMHSubscribedTopics topic : SMHSubscribedTopics.values()) {
            client.subscribe(topic.getTopicRegisteredName(), new MQTTSHListener());
            System.out.println("Subscribed to: " + topic.getTopicRegisteredName());
        }
    }

    private void subscribeToServerTopics() throws MqttException {
        for (ServerSubscribedTopics topic : ServerSubscribedTopics.values()) {
            client.subscribe(topic.getTopicRegisteredName(), new MQTTServerListener());
            System.out.println("Subscribed to: " + topic.getTopicRegisteredName());
        }
    }

    private void changeStatesToClose() throws MqttException {
        for (SMHSubscribedTopics topic : SMHSubscribedTopics.values()) {
            publish(topic.getTopicRegisteredName(), "false");
            System.out.println("State changed to 'false' on : " + topic.getTopicRegisteredName());
        }
    }

    public void publish(String topic, String message) throws MqttException {
        if (client.isConnected()) {
            client.publish(topic, new MqttMessage(message.getBytes()));
        }
    }

    public MqttClient getClient() {
        return client;
    }
}
