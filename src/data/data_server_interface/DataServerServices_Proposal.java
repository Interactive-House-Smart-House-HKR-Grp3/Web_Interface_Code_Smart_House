package data.data_server_interface;

import java.rmi.Remote;
import java.util.Date;
import java.util.HashMap;

/**
 * This Interface should be implemented / registered by a RMI server on the Database-server side and
 * should provide the services required by the web-based interface.
 * <p>
 * This would streamline communication between client and server and significantly reduce
 * the volume of data transferred over the network. These benefits would reduce the exchange
 * between entities to the minimum possible, generating greater operating efficiency.
 * At the same time, this would reduce the risk coefficient and substantially simplify
 * the communication protocol.
 * <p>
 * This interface includes the proposal of the web-based interface implementation group and
 * would satisfy the requirements identified so far.
 */

public interface DataServerServices_Proposal extends Remote {

//  Fire Alarm --->

    /**
     * boolean isFireAlarm = getFireAlarmState();
     *
     * @return true ? the_alarm_is_SET : the_alarm_is_not_set
     */
    boolean getFireAlarmState();

    /**
     * boolean isTriggered = isFireAlarmTriggered();
     *
     * @return true ? the_alarm_is_TRIGGERED : the_alarm_is_not_TRIGGERED
     */
    boolean isFireAlarmTriggered();

    /**
     * will disarm the Fire Alarm
     */
    void disarm_F_Alarm();

    /**
     * This method is used for statistics.
     * The statistics will display the number of times the alarm changed states, the
     * date when the alarm was armed, and triggered.
     * Data will be selected for the user defined time interval.
     *
     * @param from user will choose the date for statistics to start from.
     * @param to   user will choose the date for statistics to end at.
     * @return a lists with key-value pairs. The key will represent the date
     * of the event; the value will represent the type.
     */
    HashMap<Date /*day*/, Integer /* 0 = turnedOff, 1 = turnedOn, 2 = triggered*/> get_FA_Statistics(Date from, Date to);

//  <--- Fire Alarm   **************************************************************************************************

//  Housebreaking Alarm   --->

    /**
     * boolean isHousebreakingAlarm = getHousebreakingAlarmState();
     *
     * @return true ? the_alarm_is_SET : the_alarm_is_not_set
     */
    boolean getHousebreakingAlarmState();

    /**
     * boolean isTriggered = isHousebreakingAlarmTriggered();
     *
     * @return true ? the_alarm_is_TRIGGERED : the_alarm_is_not_TRIGGERED
     */
    boolean isHousebreakingAlarmTriggered();

    /**
     * will disarm the Housebreaking Alarm
     */
    void disarm_H_Alarm();

    /**
     * This method is used for statistics.
     * The statistics will display the number of times the alarm changed states, the
     * date when the alarm was armed, and triggered.
     * Data will be selected for the user defined time interval.
     *
     * @param from user will choose the date for statistics to start from.
     * @param to   user will choose the date for statistics to end at.
     * @return a lists with key-value pairs. The key will represent the date
     * of the event; the value will represent the type.
     */
    HashMap<Date /*day*/, Integer /* 0 = turnedOff, 1 = turnedOn, 2 = triggered*/> get_HA_Statistics(Date from, Date to);

//  <--- Housebreaking Alar   ******************************************************************************************

//  Water leakage --->

    /**
     * boolean isWaterLeakage = getWaterLeakageState();
     *
     * @return true ? isWaterLeakage : noWaterLeakage
     */
    boolean getWaterLeakageState();

    /**
     * The method will provide data for the statistics.
     *
     * @param from user will choose the date for statistics to start from.
     * @param to   user will choose the date for statistics to end at.
     * @return a list of pairs: key-triggeringDate, value-closedDate
     */
    HashMap<Date /*triggeringTime*/, Date /*closingTime*/> get_WL_Statistics(Date from, Date to);

//  <---  Water leakage  ***********************************************************************************************

//  Temperature Indoors  --->

    /**
     * double currentIndoorTemp = getCurrentIndoorTemp();
     *
     * @return the latest temperature value at the request time.
     */
    double getCurrentIndoorTemp();

    /**
     * This method is used for statistics.
     * The data retrieved will be used for a graphical representation of the data for a user defined period of time.
     *
     * @param from user will choose the date for statistics to start from.
     * @param to   user will choose the date for statistics to end at.
     * @return a list of key-value pairs. Key = date; value = the required data in integer form, for that specific day.
     */
    HashMap<Date /*day*/, Integer[] /*[0] = averageDailyTemp; [1] = lowestTemp; [2] = highestTemp*/>
    getIndoorTempStatistics(Date from, Date to);

//  <--- Temperature Indoors   *****************************************************************************************

//  Temperature Outdoors --->

    /**
     * double currentOutdoorTemp = getCurrentOutdoorTemp();
     *
     * @return the latest temperature value at the request time.
     */
    double getCurrentOutdoorTemp();

    /**
     * This method is used for statistics.
     * The data retrieved will be used for a graphical representation of the data for a user defined period of time.
     *
     * @param from user will choose the date for statistics to start from.
     * @param to   user will choose the date for statistics to end at.
     * @return a list of key-value pairs. Key = date; value = the required data in integer form, for that specific day.
     */
    HashMap<Date /*day*/, Integer[] /*[0] = averageDailyTemp; [1] = lowestTemp; [2] = highestTemp*/>
    getOutdoorTempStatistics(Date from, Date to);

//  <--- Temperature Outdoors  *****************************************************************************************

//  Stove  --->

    /**
     * boolean stoveState = getStoveState();
     *
     * @return true ? isON : isOFF
     */
    boolean getStoveState();

    /**
     * This method is used for statistics.
     * The data retrieved will be used for a graphical representation of the data for a user defined period of time.
     *
     * @param from user will choose the date for statistics to start from.
     * @param to   user will choose the date for statistics to end at.
     * @return a list of key-value pairs. Key = date; value = the value switched to (true ? ON : OFF).
     */
    HashMap<Date /*dateFrom*/, Boolean /*state*/> getStoveStatistics(Date from, Date to);
//  **************************************************************************************************

//  Window  --->

    /**
     * boolean windowState = getWindowState();
     *
     * @return true ? isOPEN : isCLOSED
     */
    boolean getWindowState();

    /**
     * This method is used for statistics.
     * The data retrieved will be used for a graphical representation of the data for a user defined period of time.
     *
     * @param from user will choose the date for statistics to start from.
     * @param to   user will choose the date for statistics to end at.
     * @return a list of key-value pairs. Key = date; value = the value switched to (true ? OPEN : CLOSED).
     */
    HashMap<Date /*dateFrom*/, Boolean /*state*/> getWindowStatistics(Date from, Date to);
//  <---  Window   *****************************************************************************************************

//  Door  --->

    /**
     * boolean doorState = getDoorState();
     *
     * @return true ? isOPEN : isCLOSED
     */
    boolean getDoorState();

    /**
     * This method is used for statistics.
     * The data retrieved will be used for a graphical representation of the data for a user defined period of time.
     *
     * @param from user will choose the date for statistics to start from.
     * @param to   user will choose the date for statistics to end at.
     * @return a list of key-value pairs. Key = date; value = the value switched to (true ? OPEN : CLOSED).
     */
    HashMap<Date /*dateFrom*/, Boolean /*state*/> getDoorStatistics(Date from, Date to);
//  <---  Door   *******************************************************************************************************

//  Electricity consumption  --->

    /**
     * double currentElectricityConsumption = getElectricityConsumption();
     *
     * @return the latest value at the request time.
     */
    double getElectricityConsumption();

    /**
     * This method is used for statistics.
     * The data retrieved will be used for a graphical representation of the data for a user defined period of time.
     *
     * @param from user will choose the date for statistics to start from.
     * @param to   user will choose the date for statistics to end at.
     * @return a list of key-value pairs. Key = date; value = the average electricity consumption of that day.
     */
    HashMap<Date /*day*/, Integer /*averageElConsumption / day*/> getElectricityStatistics(Date from, Date to);
//  <---  Electricity consumption  *************************************************************************************

//  Twilight  --->

    /**
     * boolean twilightState = getTwilightState();
     *
     * @return true ? isNight : isDay
     */
    boolean getTwilightState();

    /**
     * This method is used for statistics.
     * The data retrieved will be used for a graphical representation of the data for a user defined period of time.
     *
     * @param from user will choose the date for statistics to start from.
     * @param to   user will choose the date for statistics to end at.
     * @return a list of key-value pairs. Key = date of a sunrise; value = date of the sunset.
     */
    HashMap<Date /*sunrise*/, Date /*sunset*/> getTwilightStatistics(Date from, Date to);
//  <---  Twilight   ***************************************************************************************************

//  Power Cut  --->

    /**
     * boolean isPowerCut = getPowerCutState();
     *
     * @return true ? isPowerCut : noPowerCut
     */
    boolean getPowerCutState();

    /**
     * This method is used for statistics.
     * The data retrieved will be used for a graphical representation of the data for a user defined period of time.
     *
     * @param from user will choose the date for statistics to start from.
     * @param to   user will choose the date for statistics to end at.
     * @return a list of key-value pairs. Key = date of a power cut; value = date of the fix.
     */
    HashMap<Date /*powerCutOn*/, Date /*powerCutOff*/> getPowerCutStatistics(Date from, Date to);
//  <---  Power Cut   **************************************************************************************************

//  Indoor Light  --->

    /**
     * boolean isLampOn = getIndoorLampState();
     *
     * @return true ? isON : isOFF
     */
    boolean getIndoorLampState();

    /**
     * This method will ask the database to change the lamp status.
     *
     * @param toState true ? ON : OFF
     */
    boolean changeIndoorLampState(boolean toState);

    /**
     * This method is used for statistics.
     * The data retrieved will be used for a graphical representation of the data for a user defined period of time.
     *
     * @param from user will choose the date for statistics to start from.
     * @param to   user will choose the date for statistics to end at.
     * @return a list of key-value pairs. Key = date when the lamp status is changed; value = true ? ON : OFF.
     */
    HashMap<Date /*day*/, Boolean /*state*/> getIndoorLightningStatistics(Date from, Date to);

//  <--- Indoor Light  *************************************************************************************************

//  Outdoor Light  --->  IS THERE AN OUTSIDE LAMP, OR IS THE TWILIGHT FUNCTION?

    /**
     * boolean isOutdoorLampOn = getOutdoorLampState();
     *
     * @return true ? isON : isOFF
     */
    boolean getOutdoorLampState();

    /**
     * This method will ask the database to change the lamp status.
     *
     * @param toState true ? ON : OFF
     */
    boolean changeOutdoorLampState(boolean toState);

    /**
     * This method is used for statistics.
     * The data retrieved will be used for a graphical representation of the data for a user defined period of time.
     *
     * @param from user will choose the date for statistics to start from.
     * @param to   user will choose the date for statistics to end at.
     * @return a list of key-value pairs. Key = date when the lamp status is changed; value = true ? ON : OFF.
     */
    HashMap<Date /*day*/, Boolean /*state*/> getOutdoorLightningStatistics(Date from, Date to);

//  <--- Outdoor Light  *************************************************************************************************
}
