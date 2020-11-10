package data.models.mqtt_topics.server_database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerRequestsTopicsTest {

    @Test
    void getWebResponse() {
        assertEquals("web/request/", ServerRequestsTopics.getWebResponse());
    }

    @Test
    void getTopicRegisteredName() {
        assertEquals("web/request/door", ServerRequestsTopics.DOOR.getTopicRegisteredName());
    }
}