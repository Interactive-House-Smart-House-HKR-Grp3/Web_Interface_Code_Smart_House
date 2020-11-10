package data.models.mqtt_topics.server_database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ServerSubscribedTopicsTest {

    @Test
    void getWebRequest() {
        assertEquals("web/statistics/", ServerSubscribedTopics.getWebRequest());
    }

    @Test
    void getTopicRegisteredName() {
        assertEquals("web/statistics/door", ServerSubscribedTopics.DOOR.getTopicRegisteredName());
    }

}