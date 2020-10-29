package data.services.login;

import data.models.user.UserAccount;
import data.services.local.DeviceController;

public class Login {

    private static UserAccount user;
    private static DeviceController deviceController;

    public Login(String account, String password){

        if(loginValidation(account, password)) {
            user = UserAccount.getInstance();
            deviceController = DeviceController.getInstance();
        }else{
            // set a flag that says that the validation failed
        }

    }

    private boolean loginValidation(String account, String password) {
        // connect db, request validation for login credentials
        return false;
    }

}
