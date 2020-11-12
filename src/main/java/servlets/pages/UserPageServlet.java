package main.java.servlets.pages;

import data.models.devices.Devices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserPageServlet", urlPatterns = "/dashboard")
public class UserPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO: change to a response and redirect the user, the userPage formatter will handle filling in the data
        // TODO: Have a listener on the UserPage to update the data

        request.setAttribute("queryResult", populatePage(request));
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }

    // TODO: Move this to the userPageFormatter to be used without redirecting the user.
    public String populatePage(HttpServletRequest request){
        // Query for the devices
        StringBuilder devicesString = new StringBuilder();

        int deviceNumber = 0;
        for (Devices device : Devices.values()) {
            deviceNumber++;

            // Create a format to display the device
            devicesString.append("<div class=\"device-item device-item-").append(deviceNumber).append("\">")
                    .append("<h3 class=\"device-title\">")
                    .append(device.name());

            // Makes sure that the layouts are unique for each device that needs to be
            switch (device.name()) {
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
                                .append("<button class=\"btn btn-device-toggle\" name=\"btn_deviceToggle\" type=\"submit\" value=\"").append(device.name()).append("-").append(device.getDeviceCurrentState()).append("\">")
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
        return devicesString.toString();
    }
}

