package data.mock_data;

import data.models.devices.Devices;
import data.models.statistics.StatisticsData;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static data.models.user.UserAccount.USER;

/**
 * Create mock data that reflects the real data types, and fill the enumerations with it.
 */
public class MockData {

    /**
     * Used concurrently with a mqtt connection.
     * @param accountName login
     * @param password login
     */
    public MockData(String accountName, String password) {
        // Simulates the login and sets the user account with mock data.
        setUserAccount(accountName, password);
    }

    /**
     * Creates mock data for all purposes.
     * @param accountName login
     * @param password login
     * @param statisticsPeriod 1 = last 24h,
     *                         2 = last week,
     *                         3 = last 30 days.
     */
    public MockData(String accountName, String password, int statisticsPeriod) {
        // Create mock data to fill the devices enum.
        // Should be commented when connected to the Mqtt broker
        setStartingDevices();

        // Simulates the login and sets the user account with mock data.
        setUserAccount(accountName, password);

        // Set mock Statistics data
        setStatisticsData(statisticsPeriod);
    }

    /**
     * Set mock data for testing purposes.
     * Statistics can contain 1 of 2 states, 1 of 3 states, integer values(electricity),
     * or doubles (temperature).
     *
     * All the mock statistics are hourly provided (including the even-based ones).
     *
     * @param statisticsPeriod 1 = last 24h
     *                         2 = last week
     *                         3 = last 30 days
     */
    private void setStatisticsData(int statisticsPeriod) {
        if (List.of(1, 2, 3).contains(statisticsPeriod)){
            // 1 or 2 states
            for (StatisticsData statisticsData :
                    List.of(StatisticsData.WATER_LEAKAGE, StatisticsData.WINDOW, StatisticsData.STOVE,
                    StatisticsData.DOOR, StatisticsData.FAN, StatisticsData.ELECTRICITY_CONSUMPTION, StatisticsData.INDOOR_HEATING,
                    StatisticsData.LOFT_HEATING, StatisticsData.TWILIGHT, StatisticsData.POWER_CUT, StatisticsData.INDOOR_LIGHT,
                    StatisticsData.OUTDOOR_LIGHT, StatisticsData.AUTO_MODE)) {
                statisticsData.setEventBasedStatistics(mockEventBasedStatistics(statisticsPeriod, 3));
            }

            // 1 - 3 states
            StatisticsData.FIRE_ALARM.setEventBasedStatistics(mockEventBasedStatistics(statisticsPeriod, 4));
            StatisticsData.BURGLAR_ALARM.setEventBasedStatistics(mockEventBasedStatistics(statisticsPeriod,4));

            // doubles
            StatisticsData.INDOOR_TEMPERATURE.setAverageDoubleStatistics(mockAverageDoubleStatistics(statisticsPeriod));
            StatisticsData.OUTDOOR_TEMPERATURE.setAverageDoubleStatistics(mockAverageDoubleStatistics(statisticsPeriod));

            // integer
            StatisticsData.ELECTRICITY_CONSUMPTION.setAverageIntegerStatistics(mockAverageIntegerStatistics(statisticsPeriod));
        }
    }

    /**
     * Invoked by the electricity_consumption statistics.
     * Integer values between 1 and 2000.
     * Starting from the requested date, every next hourly date till present time.
     *
     * @param forPeriod is a code for a
     *                  day(1),
     *                  week(2),
     *                  month(3).
     * @return a Map of pairs <Date, Integer>.
     */
    private Map<Date, Integer> mockAverageIntegerStatistics(int forPeriod) {
        // One day ? starting from yesterday :
        // one week ? starting 6 days ago (today is the 7th) :
        // last month ? starting 29 days ago (today is the 30st).
        int days = forPeriod == 1 ? -1 : forPeriod == 2 ? -6 : forPeriod == 3 ? -29 : 0;
        Map<Date, Integer> statistics = new HashMap<>();

        // get all dates for the period
        List<Date> dates = getAllHourlyDates(days);
        Collections.sort(dates);

        // Add random values and fill the Map with mock data
        for (Date date : dates) {
            statistics.put(date, ThreadLocalRandom.current().nextInt(1, 2000));
        }
        return statistics;
    }

    /**
     * Invoked by the indoor/outdoor temperature statistics.
     * Double values between 10 and 35, expressing the Celsius.
     * Starting from the requested date, every next hourly date till present time.
     *
     * @param forPeriod is a code for a
     *                  day(1),
     *                  week(2),
     *                  month(3).
     * @return a Map of pairs <Date, Double>.
     */
    private Map<Date, Double> mockAverageDoubleStatistics(int forPeriod) {
        // One day ? starting from yesterday :
        // one week ? starting 6 days ago (today is the 7th) :
        // last month ? starting 29 days ago (today is the 30st).
        int days = forPeriod == 1 ? -1 : forPeriod == 2 ? -6 : forPeriod == 3 ? -29 : 0;
        Map<Date, Double> statistics = new HashMap<>();

        // get all dates for the period
        List<Date> dates = getAllHourlyDates(days);
        Collections.sort(dates);

        // Add random values and fill the Map with mock data
        for (Date date : dates) {
            statistics.put(date, ThreadLocalRandom.current().nextDouble(10, 35));
        }
        return statistics;
    }
    /**
     * Invoked by the event based statistics.
     * Double values between 10 and 35, expressing the Celsius.
     * Starting from the requested date, every next hourly date till present time.
     *
     * @param forPeriod is a code for a
     *                  day(1),
     *                  week(2),
     *                  month(3).
     * @param bound is a code for states
     *              ONN(1),
     *              OFF(2),
     *              TRIGGERED(3).
     * @return a Map of pairs <Date, Integer>.
     */
    private Map<Date, Integer> mockEventBasedStatistics(int forPeriod, int bound/*3 or 4*/) {
        // One day ? starting from yesterday :
        // one week ? starting 6 days ago (today is the 7th) :
        // last month ? starting 29 days ago (today is the 30st).
        int days = forPeriod == 1 ? -1 : forPeriod == 2 ? -6 : forPeriod == 3 ? -29 : 0;
        Map<Date, Integer> statistics = new HashMap<>();

        // get all dates for the period and sort them
        List<Date> dates = getAllHourlyDates(days);
        Collections.sort(dates);

        // Add random values and fill the Map with mock data
        for (Date date : dates) {
            statistics.put(date, ThreadLocalRandom.current().nextInt(1, bound));
        }
        return statistics;
    }

    /**
     * Starting with a number of days back, defined by the startingFrom parameter,
     * will fill an array with every hour (Date format) until present time.
     *
     * @param startingFrom the number of days back, to start from.
     * @return an array of every hour of the period, in a Date format.
     */
    private List<Date> getAllHourlyDates(int startingFrom) {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime()); // set current date
        calendar.add(Calendar.DATE, startingFrom); // move the date to where is the statistic's starting point
        Date startingDate =  calendar.getTime(); // convert the info to Date format

        boolean notCurrentTimeYet = true;
        while (notCurrentTimeYet) { // if current time is reached -> false

            //System.out.println(startingDate); // add it to the Map obj
            dates.add(startingDate);
            long nextHour = startingDate.getTime() + 3600 * 1000; // increase the date with one hour
            startingDate = new Date(nextHour); // convert the next hour's millis to Date format
            notCurrentTimeYet = new Date().getTime() > startingDate.getTime(); // stop if current time is reached
        }
        return dates;
    }

    /**
     * Takes the valid account credentials and sets the user data,
     * based on the user's (mock) existing data.
     * @param accountName valid
     * @param password valid
     */
    private void setUserAccount(String accountName, String password) {
        for (MockUserAccount account : MockUserAccount.values()) {
            if (account.getACCOUNT_NAME().equals(accountName) && account.getPASSWORD().equals(password)) {
                USER.setAccountName(account.getACCOUNT_NAME());
                USER.setEmailAddress(account.getEMAIL());
                USER.setName(account.getNAME());
                USER.setPassword(account.getPASSWORD());
            }
        }
    }

    /**
     * Sets random data to all devices, that can be manipulated for testing purposes.
     */
    private void setStartingDevices() {
        for (Devices device : Devices.values()) {
            if (List.of(Devices.INDOOR_TEMPERATURE, Devices.OUTDOOR_TEMPERATURE).contains(device)) {
                device.setDoubleValue(ThreadLocalRandom.current().nextDouble(1, 30));
                device.setDeviceCurrentState(ThreadLocalRandom.current().nextInt(1, 2));
            } else if (device == Devices.FAN) {
                device.setIntValue(ThreadLocalRandom.current().nextInt(1, 5));
                device.setDeviceCurrentState(ThreadLocalRandom.current().nextInt(1, 3));
            } else if (device == Devices.ELECTRICITY_CONSUMPTION) {
                device.setIntValue(ThreadLocalRandom.current().nextInt(1, 100));
                device.setDeviceCurrentState(1);
            } else if (device == Devices.TWILIGHT) {
                if (Calendar.getInstance().getTime().getHours() < 17
                        && Calendar.getInstance().getTime().getHours() > 7) {
                    device.setIntValue(ThreadLocalRandom.current().nextInt(40, 100));
                    device.setDeviceCurrentState(1); // is day
                } else {
                    device.setIntValue(ThreadLocalRandom.current().nextInt(0, 39));
                    device.setDeviceCurrentState(2); // is night
                }
            }else if (List.of(Devices.BURGLAR_ALARM, Devices.FIRE_ALARM).contains(device)){
                device.setDeviceCurrentState(ThreadLocalRandom.current().nextInt(1, 4));
            }else{
                device.setDeviceCurrentState(ThreadLocalRandom.current().nextInt(1, 3));
            }

        }
    }
}