package main.java.servlets;

import data.mock_data.MockData;
import data.models.devices.Devices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserPageServlet", urlPatterns = "/userPage")
public class UserPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Query for the devices
        StringBuilder devicesString = new StringBuilder();
        for (Devices device : Devices.values()){
            // Create a format to display the device
            devicesString.append("<div class=\"device-container-").append(69420).append("\">")
                    .append("Device: ").append(device.getName()).append("<br/>");

            try {
                devicesString.append("State: ").append(device.getDeviceCurrentState());
            } catch (Exception e){
                devicesString.append("State: N/A");
            }

            if (device.isChangeableState()) {
                // The request from the button will be checked with a switch case using the value = "device.name" + "-" + "device.currentState"
                devicesString.append("<form action=\"").append(request.getContextPath()).append("/outputRequest\" method=\"post\">")
                        .append("<button class=\"btn_device\" name=\"btn_deviceToggle\" type=\"submit\" value=\"").append(device.getName()).append("-").append(device.getDeviceCurrentState()).append("\">")
                        .append("Toggle State")
                        .append("</button>")
                        .append("</form>");
            }

            // Close this device section
            devicesString.append("</div>");
        }
        // Send the device information to the user page
        request.setAttribute("queryResult", devicesString.toString());
        request.getRequestDispatcher("/user.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }
}

