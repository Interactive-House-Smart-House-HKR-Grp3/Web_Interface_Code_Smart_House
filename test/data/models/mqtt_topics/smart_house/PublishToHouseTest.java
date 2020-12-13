package data.models.mqtt_topics.smart_house;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublishToHouseTest {

    @Test
    void getTopicRegisteredName() {
        assertEquals("smart_house/cmd/fan", PublishToHouse.FAN.getTopicRegisteredName());
    }

}