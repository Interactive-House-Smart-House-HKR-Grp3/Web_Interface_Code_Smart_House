package main.java.servlets.requests;

import data.models.devices.Devices;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
            System.out.print("Split failed!");

        }

        // Using the split string, send the request
        switch (requestedDevice) {
            case "INDOOR_LIGHT":
                try {
                    if (currentState.equalsIgnoreCase("ON")) {
                        Devices.INDOOR_LIGHT.changeStateTo(2);
                    } else if (currentState.equalsIgnoreCase("OFF")) {
                        Devices.INDOOR_LIGHT.changeStateTo(1);
                    } else {
                        Devices.INDOOR_LIGHT.changeStateTo(1);
                    }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.print("Device state not found!");
        }

        // TODO: Temp fix, should have listener later maybe?
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Send the user back to the user page to view the devices
        request.getRequestDispatcher("/dashboard").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }
}
