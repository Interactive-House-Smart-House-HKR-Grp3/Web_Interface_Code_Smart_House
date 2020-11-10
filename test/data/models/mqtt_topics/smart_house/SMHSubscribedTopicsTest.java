package data.models.mqtt_topics.smart_house;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SMHSubscribedTopicsTest {

    @Test
    void getTopicRegisteredName() {
        assertEquals("smart_house/gui/fan", SMHSubscribedTopics.FAN.getTopicRegisteredName());
    }

}