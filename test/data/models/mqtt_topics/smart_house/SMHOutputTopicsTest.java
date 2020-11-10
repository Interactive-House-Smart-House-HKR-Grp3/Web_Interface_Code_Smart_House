package data.models.mqtt_topics.smart_house;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SMHOutputTopicsTest {

    @Test
    void getTopicRegisteredName() {
        assertEquals("smart_house/cmd/fan", SMHOutputTopics.FAN.getTopicRegisteredName());
    }

}