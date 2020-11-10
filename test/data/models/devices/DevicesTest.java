package data.models.devices;

import org.junit.jupiter.api.Test;

import static data.models.devices.Devices.INDOOR_LIGHT;
import static org.junit.jupiter.api.Assertions.*;

class DevicesTest {

    @Test
    void isNewStateRead() {
        INDOOR_LIGHT.setNewStateRead(true);
        assertEquals(true, INDOOR_LIGHT.isNewStateRead() );
    }

    @Test
    void getIntValue() {
        INDOOR_LIGHT.setIntValue(7);
        assertEquals(7, INDOOR_LIGHT.getIntValue() );
    }

    @Test
    void setDoubleValue() {
        INDOOR_LIGHT.setDoubleValue(7);
        assertEquals(7, INDOOR_LIGHT.getDoubleValue());
    }

    @Test
    void isChangeableState() {
        assertEquals(true, INDOOR_LIGHT.isChangeableState());
    }

    @Test
    void isStatisticsProvider() {
        assertEquals(true, INDOOR_LIGHT.isStatisticsProvider());
    }

    @Test
    void getDeviceCurrentState() {
        INDOOR_LIGHT.setDeviceCurrentState(1);
        assertEquals(Devices.State.ON, INDOOR_LIGHT.getDeviceCurrentState());
    }

    @Test
    void getStatisticsFormat() {
        assertEquals(Devices.StatisticsFormat.HOURLY_AVERAGE, INDOOR_LIGHT.getStatisticsFormat());
    }
}