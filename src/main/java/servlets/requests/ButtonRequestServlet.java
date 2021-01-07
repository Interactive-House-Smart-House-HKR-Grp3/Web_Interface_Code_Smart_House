package main.java.servlets.requests;

import data.models.devices.Devices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ButtonRequestServlet", urlPatterns = "/button")
public class ButtonRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String button = request.getParameter("button");

        if (button.toLowerCase().contains("statistics")){
            // Split the value to know which device is being requested
            String[] requestedString = button.split("-");
            switch (requestedString[1].toLowerCase()) {
                case "fire_alarm" -> request.getSession().setAttribute("statisticsDevice", Devices.FIRE_ALARM);
                case "burglar_alarm" -> request.getSession().setAttribute("statisticsDevice", Devices.BURGLAR_ALARM);
                case "water_leakage" -> request.getSession().setAttribute("statisticsDevice", Devices.WATER_LEAKAGE);
                case "power_cut" -> request.getSession().setAttribute("statisticsDevice", Devices.POWER_CUT);
                case "indoor_light" -> request.getSession().setAttribute("statisticsDevice", Devices.INDOOR_LIGHT);
                case "outdoor_light" -> request.getSession().setAttribute("statisticsDevice", Devices.OUTDOOR_LIGHT);
                case "auto_mode" -> request.getSession().setAttribute("statisticsDevice", Devices.AUTO_MODE);
                case "indoor_temperature" -> request.getSession().setAttribute("statisticsDevice", Devices.INDOOR_TEMPERATURE);
                case "outdoor_temperature" -> request.getSession().setAttribute("statisticsDevice", Devices.OUTDOOR_TEMPERATURE);
                case "heating_indoor" -> request.getSession().setAttribute("statisticsDevice", Devices.HEATING_INDOOR);
                case "heating_loft" -> request.getSession().setAttribute("statisticsDevice", Devices.HEATING_LOFT);
                case "door" -> request.getSession().setAttribute("statisticsDevice", Devices.DOOR);
                case "stove" -> request.getSession().setAttribute("statisticsDevice", Devices.STOVE);
                case "window" -> request.getSession().setAttribute("statisticsDevice", Devices.WINDOW);
                case "fan" -> request.getSession().setAttribute("statisticsDevice", Devices.FAN);
                default -> {
                    System.out.println(requestedString[1]);
                    request.getSession().setAttribute("statisticsDevice", null);
                }
            }
            // Send the user to their destination
            request.getRequestDispatcher("/statisticsPage").forward(request, response);
        } else if (button.equalsIgnoreCase("dashboard")){
            // Send the user to their destination
            request.getRequestDispatcher("/dashboard").forward(request, response);
        } else if (button.equalsIgnoreCase("logout")){
            // Send the user to their destination
            request.getRequestDispatcher("/homePage").forward(request, response);
        } else {
            // if the request doesnt match any given above, print an error for now
            System.err.println("Button servlet missing function: " + request.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("A request has been made to /button without pressing a button...");
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }
}
