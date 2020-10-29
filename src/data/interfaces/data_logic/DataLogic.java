package data.interfaces.data_logic;

import data.models.devices.Devices;
import data.models.statistics.StatisticsData;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface DataLogic {

    /**State related*/
    void changeStateTo(Devices device, boolean onOrOpen);
    void changeStateTo(boolean onOrOpen);

    Devices.State getStateOfDevice();
    Devices.State getStateOfDevice(Devices device);

    List<Devices.State> getStateOfDevices();
    List<Devices.State> getStateOfDevices(List<Devices> devicesList);

    /**Data Statistics related*/
    HashMap<Devices /*device*/, StatisticsData /* statistics data*/> getStatistics
            (List<Devices> devicesList, Date from, Date until);

    StatisticsData /* statistics data*/ getStatistics
            (Devices device, Date from, Date until);
}