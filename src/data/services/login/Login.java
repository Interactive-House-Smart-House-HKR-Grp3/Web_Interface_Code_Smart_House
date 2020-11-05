package data.services.login;

import data.mock_data.MockData;
import data.mock_data.MockUserAccount;
import data.models.user.UserAccount;
import data.services.local.DeviceController;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Login {

    public static UserAccount user;
    public static DeviceController deviceController;

    public Login(String account, String password) throws MqttException {

        if(loginValidation(account, password)) {
            //deviceController = new DeviceController();

        }else{
            // set a flag that says that the validation failed
        }

    }

    private boolean loginValidation(String account, String password) {
        // connect db, request validation for login credentials,
        // and user account data

        // IF true, use the received data to create the user account
        // read data publish by smart house to set all devices

        // Create mock data
       for (MockUserAccount user: MockUserAccount.values()){
           if (user.getACCOUNT_NAME().equals(account) && user.getPASSWORD().equals(password)){
               new MockData(account, password);
               return true;
           }
       }
       return false;
    }

}
