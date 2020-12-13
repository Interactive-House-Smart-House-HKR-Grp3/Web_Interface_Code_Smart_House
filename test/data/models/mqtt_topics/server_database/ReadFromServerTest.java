package data.models.mqtt_topics.server_database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ReadFromServerTest {

    @Test
    void getWebRequest() {
        assertEquals("web/statistics/", ReadFromServer.getWebRequest());
    }

    @Test
    void getTopicRegisteredName() {
        assertEquals("web/statistics/door", ReadFromServer.DOOR.getTopicRegisteredName());
    }

}