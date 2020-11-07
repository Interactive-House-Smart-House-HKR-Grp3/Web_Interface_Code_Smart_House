package main.java.servlets;

import data.mock_data.MockData;
import data.services.login.Login;
import main.java.mock.database.RequestManager;
import main.java.mock.data.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Generate data to use, for mock data, temporarily
        new MockData("test1", "pass1", 1);

        // Login loginClass = new Login("test1", "pass1", 1);
        // if registration, check for name and email
        // Login loginClass = new Login("little_billy", "1234", "bill", "bill@hotmail.com", 1);
        // boolean validRegistration = Login.registerNewAccount("little_billy", "1234", "bill", "bill@hotmail.com", 1);

        RequestManager rm = new RequestManager();
        String function = request.getParameter("btn_request");
        if (function.equalsIgnoreCase("login")) {
            // Authenticate the user then send them to their destination with the user attached
            User result = rm.authenticateUser(request.getParameter("username"), request.getParameter("password"));
            if (result != null) {
                // Send the user to their destination
                request.setAttribute("authUser", result);
                request.getRequestDispatcher("/userPage").forward(request, response);
            } else {
                System.err.println("Incorrect Username or Password!");
                request.getRequestDispatcher("/homePage").forward(request, response);
            }
        } else {
            if (request.getParameter("username").length() > 0 && request.getParameter("password").length() > 0 && request.getParameter("name").length() > 0 && request.getParameter("email").length() > 0) {
                if (!rm.isUserTaken(request.getParameter("username"))){
                    // Send the new user to their destination
                    User result = rm.registerNewUser(request.getParameter("username"), request.getParameter("password"));
                    request.setAttribute("authUser", result);
                    request.getRequestDispatcher("/userPage").forward(request, response);
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

    }
}
