package main.java.mock.data;

public class Device {

    public enum Type {
        // Only readable devices
        FIRE_ALARM,     // Retrieve: bool
        HBREAK_ALARM,   // Retrieve: bool
        LEAKAGE,        // Retrieve: bool
        TEMP_INDOOR,    // Retrieve: double
        TEMP_OUTDOOR,   // Retrieve: double
        STOVE,          // Retrieve: bool
        WINDOW,         // Retrieve: bool
        EL_CONSUMPTION, // Retrieve: int
        TWILIGHT,       // Retrieve: int
        POWER_CUT,      // Retrieve: bool
        // Readable and writable devices
        INDOOR_LIGHT,   // Retrieve: bool     // Send: bool
        OUTDOOR_LIGHT,  // Retrieve: bool     // Send: bool
        ARMING_ALARM,   // Retrieve: bool     // Send: bool
        FAN,            // Retrieve: bool     // Send: bool
        FAN_SPEED,      // Retrieve: int      // Send: int
        HEATING_INDOOR, // Retrieve: bool     // Send: bool
        HEATING_LOFT,   // Retrieve: bool     // Send: bool
        AUTO_MODE       // Retrieve: bool     // Send: bool
    }

    // Local only authentication of user

    // Mock Data store data like this for each device
    // Map <Date, Device.State>
    // Using this map, get stats on how long they have been on or off / Calculate it

    // ---IMPORTANT TO CONSIDER---
    // Ignore the User functionality for first implementation
    // just have all the devices and the statistics for all devices together

    public Type type;
    public boolean changeableState;
    public boolean currentState;
    public int value;

    public Device(){

    }

    public Device(Type type, boolean changeableState, boolean currentState, int value){
        this.type = type;
        this.changeableState = changeableState;
        this.currentState = currentState;
        this.value = value;
    }
}
