package main.java.servlets.requests;

import data.models.devices.Devices;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OutputRequestServlet", urlPatterns = "/outputRequest")
public class OutputRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the device and its current state to get the appropriate request
        String requestedDevice = request.getParameter("btn_deviceToggle");
        String currentState = "null";
        try {
            String[] requestSplit = request.getParameter("btn_deviceToggle").split("-");
            requestedDevice = requestSplit[0];
            currentState = requestSplit[1];
        } catch (Exception e){
            System.out.print("Split failed! - " + request.getParameter("btn_deviceToggle"));

        }

        // Check if the user is in debug mode?
        boolean debug = (boolean) request.getSession().getAttribute("Debug");
        System.out.println("----- ----- ----- ----- -----");
        System.out.println("Debug mode = " + debug);
        // Using the split string, send the request
        switch (requestedDevice) {
            case "INDOOR_LIGHT":
                try {
                    System.out.println("Case - INDOOR_LIGHT");
                    if (currentState.equalsIgnoreCase("ON")) {
                        System.out.println("State changed to - OFF");
                        if (debug){
                            Devices.INDOOR_LIGHT.setDeviceCurrentState(2);
                        } else {
                            Devices.INDOOR_LIGHT.changeStateTo(2);
                        }
                    } else if (currentState.equalsIgnoreCase("OFF")) {
                        System.out.println("State changed to - ON");
                        if (debug){
                            Devices.INDOOR_LIGHT.setDeviceCurrentState(1);
                        } else {
                            Devices.INDOOR_LIGHT.changeStateTo(1);
                        }
                    } else {
                        System.out.println("State not found. Attempt to set to - ON");
                        if (debug){
                            Devices.INDOOR_LIGHT.setDeviceCurrentState(1);
                        } else {
                            Devices.INDOOR_LIGHT.changeStateTo(1);
                        }
                    }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                break;
            case "OUTDOOR_LIGHT":
                try {
                    System.out.println("Case - OUTDOOR_LIGHT");
                    if (currentState.equalsIgnoreCase("ON")) {
                        System.out.println("State changed to - OFF");
                        if (debug){
                            Devices.OUTDOOR_LIGHT.setDeviceCurrentState(2);
                        } else {
                            Devices.OUTDOOR_LIGHT.changeStateTo(2);
                        }
                    } else if (currentState.equalsIgnoreCase("OFF")) {
                        System.out.println("State changed to - ON");
                        if (debug){
                            Devices.OUTDOOR_LIGHT.setDeviceCurrentState(1);
                        } else {
                            Devices.OUTDOOR_LIGHT.changeStateTo(1);
                        }
                    } else {
                        System.out.println("State not found. Attempt to set to - ON");
                        if (debug){
                            Devices.OUTDOOR_LIGHT.setDeviceCurrentState(1);
                        } else {
                            Devices.OUTDOOR_LIGHT.changeStateTo(1);
                        }
                    }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                break;
            case "AUTO_MODE":
                try {
                    System.out.println("Case - AUTO_MODE");
                    if (currentState.equalsIgnoreCase("ON")) {
                        System.out.println("State changed to - OFF");
                        if (debug){
                            Devices.AUTO_MODE.setDeviceCurrentState(2);
                        } else {
                            Devices.AUTO_MODE.changeStateTo(2);
                        }
                    } else if (currentState.equalsIgnoreCase("OFF")) {
                        System.out.println("State changed to - ON");
                        if (debug){
                            Devices.AUTO_MODE.setDeviceCurrentState(1);
                        } else {
                            Devices.AUTO_MODE.changeStateTo(1);
                        }
                    } else {
                        System.out.println("State not found. Attempt to set to - ON");
                        if (debug){
                            Devices.AUTO_MODE.setDeviceCurrentState(1);
                        } else {
                            Devices.AUTO_MODE.changeStateTo(1);
                        }
                    }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                break;
            case "HEATING_INDOOR":
                try {
                    System.out.println("Case - HEATING_INDOOR");
                    if (currentState.equalsIgnoreCase("ON")) {
                        System.out.println("State changed to - OFF");
                        if (debug){
                            Devices.HEATING_INDOOR.setDeviceCurrentState(2);
                        } else {
                            Devices.HEATING_INDOOR.changeStateTo(2);
                        }
                    } else if (currentState.equalsIgnoreCase("OFF")) {
                        System.out.println("State changed to - ON");
                        if (debug){
                            Devices.HEATING_INDOOR.setDeviceCurrentState(1);
                        } else {
                            Devices.HEATING_INDOOR.changeStateTo(1);
                        }
                    } else {
                        System.out.println("State not found. Attempt to set to - ON");
                        if (debug){
                            Devices.HEATING_INDOOR.setDeviceCurrentState(1);
                        } else {
                            Devices.HEATING_INDOOR.changeStateTo(1);
                        }
                    }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                break;
            case "HEATING_LOFT":
                try {
                    System.out.println("Case - HEATING_LOFT");
                    if (currentState.equalsIgnoreCase("ON")) {
                        System.out.println("State changed to - OFF");
                        if (debug){
                            Devices.HEATING_LOFT.setDeviceCurrentState(2);
                        } else {
                            Devices.HEATING_LOFT.changeStateTo(2);
                        }
                    } else if (currentState.equalsIgnoreCase("OFF")) {
                        System.out.println("State changed to - ON");
                        if (debug){
                            Devices.HEATING_LOFT.setDeviceCurrentState(1);
                        } else {
                            Devices.HEATING_LOFT.changeStateTo(1);
                        }
                    } else {
                        System.out.println("State not found. Attempt to set to - ON");
                        if (debug){
                            Devices.HEATING_LOFT.setDeviceCurrentState(1);
                        } else {
                            Devices.HEATING_LOFT.changeStateTo(1);
                        }
                    }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                break;
            case "DOOR":
                try {
                    System.out.println("Case - DOOR");
                    if (currentState.equalsIgnoreCase("OPEN")) {
                        System.out.println("State changed to - CLOSED");
                        if (debug){
                            Devices.DOOR.setDeviceCurrentState(2);
                        } else {
                            Devices.DOOR.changeStateTo(2);
                        }
                    } else if (currentState.equalsIgnoreCase("CLOSED")) {
                        System.out.println("State changed to - OPEN");
                        if (debug){
                            Devices.DOOR.setDeviceCurrentState(1);
                        } else {
                            Devices.DOOR.changeStateTo(1);
                        }
                    } else {
                        System.out.println("State not found. Attempt to set to - OPEN");
                        if (debug){
                            Devices.DOOR.setDeviceCurrentState(1);
                        } else {
                            Devices.DOOR.changeStateTo(1);
                        }
                    }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                break;
            case "WINDOW":
                try {
                    System.out.println("Case - WINDOW");
                    if (currentState.equalsIgnoreCase("OPEN")) {
                        System.out.println("State changed to - CLOSED");
                        if (debug){
                            Devices.WINDOW.setDeviceCurrentState(2);
                        } else {
                            Devices.WINDOW.changeStateTo(2);
                        }
                    } else if (currentState.equalsIgnoreCase("CLOSED")) {
                        System.out.println("State changed to - OPEN");
                        if (debug){
                            Devices.WINDOW.setDeviceCurrentState(1);
                        } else {
                            Devices.WINDOW.changeStateTo(1);
                        }
                    } else {
                        System.out.println("State not found. Attempt to set to - OPEN");
                        if (debug){
                            Devices.WINDOW.setDeviceCurrentState(1);
                        } else {
                            Devices.WINDOW.changeStateTo(1);
                        }
                    }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                break;
            case "STOVE":
                try {
                    System.out.println("Case - STOVE");
                    if (currentState.equalsIgnoreCase("ON")) {
                        System.out.println("State changed to - OFF");
                        if (debug){
                            Devices.STOVE.setDeviceCurrentState(2);
                        } else {
                            Devices.STOVE.changeStateTo(2);
                        }
                    } else if (currentState.equalsIgnoreCase("OFF")) {
                        System.out.println("State changed to - ON");
                        if (debug){
                            Devices.STOVE.setDeviceCurrentState(1);
                        } else {
                            Devices.STOVE.changeStateTo(1);
                        }
                    } else {
                        System.out.println("State not found. Attempt to set to - ON");
                        if (debug){
                            Devices.STOVE.setDeviceCurrentState(1);
                        } else {
                            Devices.STOVE.changeStateTo(1);
                        }
                    }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                break;


            default:
                System.out.print("Device state not found!");
        }

        request.getRequestDispatcher("/dashboard").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }
}
