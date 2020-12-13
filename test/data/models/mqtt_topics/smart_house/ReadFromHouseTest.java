package data.models.mqtt_topics.smart_house;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadFromHouseTest {

    @Test
    void getTopicRegisteredName() {
        assertEquals("smart_house/gui/fan", ReadFromHouse.FAN.getTopicRegisteredName());
    }

}