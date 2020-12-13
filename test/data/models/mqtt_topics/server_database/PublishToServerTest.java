package data.models.mqtt_topics.server_database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublishToServerTest {

    @Test
    void getWebResponse() {
        assertEquals("web/request/", PublishToServer.getWebResponse());
    }

    @Test
    void getTopicRegisteredName() {
        assertEquals("web/request/door", PublishToServer.DOOR.getTopicRegisteredName());
    }
}