package main.java.servlets;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: send a MQTT query to the server with the device requesting
        String requestedDevice = request.getParameter("btn_deviceToggle");
        switch (requestedDevice) {
            case "INDOOR_LIGHT-ON":
                try {
                    Devices.INDOOR_LIGHT.changeStateTo(2);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                break;
            case "INDOOR_LIGHT-OFF":
                try {
                    Devices.INDOOR_LIGHT.changeStateTo(1);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.print("Device state not found!");
        }

        // Send the user back to the user page to view the devices
        request.getRequestDispatcher("/userPage").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }
}
