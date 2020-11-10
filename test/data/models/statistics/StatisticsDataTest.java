package data.models.statistics;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsDataTest {

    @Test
    void getForPeriod() {
        StatisticsData.INDOOR_LIGHT.setForPeriod(1);
        assertEquals(1, StatisticsData.INDOOR_LIGHT.getForPeriod());
    }

    @Test
    void getType() {
        StatisticsData.INDOOR_LIGHT.setType(StatisticsData.Type.EVENT_BASED);
        assertEquals(StatisticsData.Type.EVENT_BASED, StatisticsData.INDOOR_LIGHT.getType());
    }


    @Test
    void getEventBasedStatistics() {
        Map<Date, Integer> map = new HashMap<>();
        StatisticsData.INDOOR_LIGHT.setEventBasedStatistics(map);
        assertEquals(map, StatisticsData.INDOOR_LIGHT.getEventBasedStatistics());
    }

    @Test
    void getAverageIntegerStatistics() {
        Map<Date, Integer> map = new HashMap<>();
        StatisticsData.INDOOR_LIGHT.setAverageIntegerStatistics(map);
        assertEquals(map, StatisticsData.INDOOR_LIGHT.getAverageIntegerStatistics());
    }

    @Test
    void getAverageDoubleStatistics() {
        Map<Date, Double> map = new HashMap<>();
        StatisticsData.INDOOR_LIGHT.setAverageDoubleStatistics(map);
        assertEquals(map, StatisticsData.INDOOR_LIGHT.getAverageDoubleStatistics());
    }

    @Test
    void isNewStatisticsArrived() {
        StatisticsData.INDOOR_LIGHT.setNewStatisticsArrived(true);
        assertEquals(true, StatisticsData.INDOOR_LIGHT.isNewStatisticsArrived());
    }
}