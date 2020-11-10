package main.java.servlets;

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

        int deviceNumber = 0;
        for (Devices device : Devices.values()) {

            deviceNumber++;

            // Create a format to display the device
            devicesString.append("<div class=\"device-item device-item-").append(deviceNumber).append("\">")
                    .append("<h3 class=\"device-title\">")
                    .append(device.getName());

            // Makes sure that the layouts are unique for each device that needs to be
            switch (device.getName()) {

                // TODO create different layouts for each device


                // A device not found will take this layout
                default: {

                    if (device.isStatisticsProvider()) {
                        // The statistics button
                        // *** MUST BE INSIDE THE H3 TAG ***
                        // TODO resolve sending the user to a statistics page specific to this device
                        devicesString.append("<form class=\"form-btn\" action=\"").append(request.getContextPath()).append("/button\" method=\"post\">\n" +
                                "<button class=\"btn btn-statistics\" name=\"button\" type=\"submit\" value=\"statistics\">\n" +
                                "<i class=\"fas fa-chart-pie\"></i>\n" +
                                "</button>\n" +
                                "</form>");
                    }
                    devicesString.append("</h3>");

                    try {
                        devicesString.append("<h6 class=\"device-state\">").append(device.getDeviceCurrentState()).append("</h6>");
                    } catch (Exception e) {
                        devicesString.append("<h6 class=\"device-state\">").append("N/A").append("</h6>");
                    }

                    devicesString.append("<img class=\"img-device\" src=\"./assets/vectors/3D_icons/005-lamp.svg\">");

                    if (device.isChangeableState()) {
                        // The request from the button will be checked with a switch case using the value = "device.name" + "-" + "device.currentState"
                        devicesString.append("<form action=\"").append(request.getContextPath()).append("/outputRequest\" method=\"post\">")
                                .append("<button class=\"btn btn-device-toggle\" name=\"btn_deviceToggle\" type=\"submit\" value=\"").append(device.getName()).append("-").append(device.getDeviceCurrentState()).append("\">")
                                .append("Toggle State")
                                .append("</button>")
                                .append("</form>");
                    }

                    break;
                }
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

