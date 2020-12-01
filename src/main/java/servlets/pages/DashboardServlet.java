package main.java.servlets.pages;

import data.models.devices.Devices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserPageServlet", urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        HttpSession session = request.getSession();

        Devices[] devices = new Devices[Devices.values().length];
        for (int i = 0; i < Devices.values().length; i++){
            devices[i] = (Devices) session.getAttribute(Devices.values()[i].name());
        }

        request.setAttribute("queryResult", populatePage(devices, request));
        */
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }

    // This is called directly in the jsp instead

    /*
    public String populatePage(Devices[] devices, HttpServletRequest request){
        // Query for the devices
        StringBuilder devicesString = new StringBuilder();

        for (int i = 0; i < devices.length; i++) {
            // Makes sure that the layouts are unique for each device that needs to be
            switch (devices[i].name()) {
                // TODO create different layouts for each device

                // A device not found will take this layout
                default: {
                    // This categorizes the devices into sections
                    devicesString.append("<section id=\"lights\">");

                    // This is needed for every device
                    devicesString.append("<div class=\"devices-container\">");

                    // Create a format to display the device
                    devicesString.append("<div class=\"device-item device-item-").append(i).append("\">")
                            .append("<h3 class=\"device-title\">")
                            .append(devices[i].name());

                    if (devices[i].isStatisticsProvider()) {
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
                        devicesString.append("<h6 class=\"device-state\">").append(devices[i].getDeviceCurrentState()).append("</h6>");
                    } catch (Exception e) {
                        devicesString.append("<h6 class=\"device-state\">").append("N/A").append("</h6>");
                    }

                    devicesString.append("<img class=\"img-device\" src=\"./assets/vectors/3D_icons/005-lamp.svg\">");

                    if (devices[i].isChangeableState()) {
                        // The request from the button will be checked with a switch case using the value = "device.name" + "-" + "device.currentState"
                        devicesString.append("<form action=\"").append(request.getContextPath()).append("/outputRequest\" method=\"post\">")
                                .append("<button class=\"btn btn-device-toggle\" name=\"btn_deviceToggle\" type=\"submit\" value=\"").append(devices[i].name()).append("-").append(devices[i].getDeviceCurrentState()).append("\">")
                                .append("Toggle State")
                                .append("</button>")
                                .append("</form>");
                    }
                    break;
                }
            }

            // Close this device section
            devicesString.append("</div>").append("</div>").append("</section>");
        }
        // Send the device information to the user page
        return devicesString.toString();
    }
    */
}