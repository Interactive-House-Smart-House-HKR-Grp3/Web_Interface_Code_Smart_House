package data.services;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTListener implements IMqttMessageListener {

    @Override
    public void messageArrived(String var1, MqttMessage var2){

        switch (var1){
            case "smart_house/indoor_light":
                System.out.println("For " + var1 + ": " + ", value is " + var2.toString());

                break;
            case "smart_house/outdoor_light":
                System.out.println("For " + var1 + ": " + ", value is " + var2.toString());

                break;
            case "smart_house/alarm":
                System.out.println("For " + var1 + ": " + ", value is " + var2.toString());

                break;
            case "smart_house/fan":
                System.out.println("For " + var1 + ": " + ", value is " + var2.toString());

                break;
            case "smart_house/heating_indoor":
                System.out.println("For " + var1 + ": " + ", value is " + var2.toString());

                break;
            case "smart_house/heating_wind":
                System.out.println("For " + var1 + ": " + ", value is " + var2.toString());

                break;
            case "smart_house/door":
                System.out.println("For " + var1 + ": " + ", value is " + var2.toString());

                break;
        }
        System.out.println("reply topic  : " + var1);
        System.out.println("reply payload: " + var2.toString());
    }
}
