package data.mock.test;

import data.models.statistics.StatisticsData;
import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.*;

public class TestStatisticsImplementation {
    private static MQTTConnectionHandler mqttConnectionHandler;
    private static Scanner in;
    public static void main(String[] args) throws MqttException {
        in = new Scanner(System.in);
        mqttConnectionHandler = MQTTConnectionHandler.getInstance();
        do {
            System.out.println("H E L L O !\n" +
                    "\n1]    Indoor light statistics" +
                    "\n2]    Door statistics" +
                    "\n3]    Electricity statistics" +
                    "\n4]    Fire Alarm statistics" +
                    "\n5]    Outdoor Temperature statistics" +
                    "\n7]    EXIT!");
            int request = new Scanner(System.in).nextInt();
            switch (request){
                case 1 -> StatisticsData.INDOOR_LIGHT.requestStatistics(1); // stats for last 24 h
                case 2 -> StatisticsData.DOOR.requestStatistics(1); // stats for last 24 h
                case 3 -> StatisticsData.ELECTRICITY_CONSUMPTION.requestStatistics(2); // stats for last week
                case 4 -> StatisticsData.FIRE_ALARM.requestStatistics(2); // stats for last week
                case 5 -> StatisticsData.OUTDOOR_TEMPERATURE.requestStatistics(3); // stats for last month

                default -> System.exit(0);
            }
            printStats(request);
            System.out.println("Repeat?\n1]    yes\n2]    no");
            boolean repeat = in.nextInt() == 2;
            if (repeat) System.exit(0);
        } while (true);
    }

    private static void printStats(int request) {
        StatisticsData statisticsData = request == 1 ? StatisticsData.INDOOR_LIGHT : request == 2 ? StatisticsData.DOOR : request == 3 ?
                StatisticsData.ELECTRICITY_CONSUMPTION : request == 4 ? StatisticsData.FIRE_ALARM : StatisticsData.OUTDOOR_TEMPERATURE;
        List<Date> dates = new ArrayList<>();
        int type = statisticsData.getEventBasedStatistics() != null ? 1 :
                statisticsData.getAverageDoubleStatistics() != null ? 2 : 3;
        System.out.println("\u001B[35mStatistics for " + statisticsData.name() + "\u001B[0m");
        if (type == 1) {
            Map<Date, Integer> eventBasedStatistics = statisticsData.getEventBasedStatistics();
            dates.addAll(eventBasedStatistics.keySet());
            Collections.sort(dates);
            for (Date date : dates) {
                System.out.println("\u001B[34mFor: " + date + ", value >>> " + statisticsData.getEventBasedStatistics().get(date) + "\u001B[0m");
            }
        } else if (type == 2) {
            Map<Date, Double> averageDoubleStatistics = statisticsData.getAverageDoubleStatistics();
            dates.addAll(averageDoubleStatistics.keySet());
            Collections.sort(dates);
            for (Date date : dates) {
                System.out.println("\u001B[34mFor: " + date + ", value >>> " + statisticsData.getAverageDoubleStatistics().get(date) + "\u001B[0m");
            }
        } else {
            Map<Date, Integer> averageIntegerStatistics = statisticsData.getAverageIntegerStatistics();
            dates.addAll(averageIntegerStatistics.keySet());
            Collections.sort(dates);
            for (Date date : dates) {
                System.out.println("\u001B[34mFor: " + date + ", value >>> " + statisticsData.getAverageIntegerStatistics().get(date) + "\u001B[0m");
            }
        }
    }

}
