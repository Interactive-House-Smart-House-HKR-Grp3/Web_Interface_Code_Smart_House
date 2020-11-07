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

        // TODO: Retrieve the devices and assign them as parameters to use on the user page.

        // Query for the devices
        // Devices.values();
        StringBuilder string = new StringBuilder();
        for (Devices device : Devices.values()){
            // set attribute of the devices
            string.append(device.getDeviceCurrentState().toString());
        }
        request.setAttribute("queryResult", string.toString());

        request.getRequestDispatcher("/user.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }
}
