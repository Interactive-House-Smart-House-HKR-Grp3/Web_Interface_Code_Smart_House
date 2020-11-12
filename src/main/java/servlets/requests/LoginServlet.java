package main.java.servlets.requests;

import data.services.login.Login;
import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is trying to login or register a new user
        String function = request.getParameter("btn_request");
        if (function.equalsIgnoreCase("login")) {
            // Authenticate the user then send them to their destination with the user attached
            if (Login.loginValidation(request.getParameter("username"), request.getParameter("password"))) {
                // TODO: instantiate on the home page
                // Instantiate connection
                try {
                    MQTTConnectionHandler.getInstance();
                } catch (MqttException e) {
                    e.printStackTrace();
                }

                // Send the user to their destination
                request.getRequestDispatcher("/dashboard").forward(request, response);
            } else {
                System.err.println("Incorrect Username or Password!");
                request.getRequestDispatcher("/homePage").forward(request, response);
            }
        } else {
            if (request.getParameter("username").length() > 0 && request.getParameter("password").length() > 0 && request.getParameter("name").length() > 0 && request.getParameter("email").length() > 0) {
                if (Login.registerNewAccount(request.getParameter("username"), request.getParameter("password"), request.getParameter("name"), request.getParameter("email"))){
                    // TODO: instantiate on the home page
                    // Instantiate connection
                    try {
                        MQTTConnectionHandler.getInstance();
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }

                    // Send the new user to their destination
                    request.getRequestDispatcher("/dashboard").forward(request, response);
                } else {
                    System.err.println("Username is already take!");
                    request.getRequestDispatcher("/homePage").forward(request, response);
                }
            } else {
                System.err.println("Empty field for registration!");
                request.getRequestDispatcher("/homePage").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }
}
