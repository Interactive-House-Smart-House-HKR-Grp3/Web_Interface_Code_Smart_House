package main.java.servlets;

import data.models.devices.Devices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OutputRequestServlet", urlPatterns = "/outputRequest")
public class OutputRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // Check the device type wanted to be changed
        // then send a request to set the state/value
        Devices.AUTO_MODE.setDeviceCurrentState(2);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }
}
