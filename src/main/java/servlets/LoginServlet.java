package main.java.servlets;

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
        RequestManager rm = new RequestManager();

        // Authenticate the user then send them to their destination with the user attached
        User result = rm.authenticateUser(request.getParameter("username"), request.getParameter("password"));
        if (result != null) {
            // Send the user to their destination
            request.setAttribute("authUser", result);
            request.getRequestDispatcher("/userPage").forward(request, response);
        } else {
            System.err.println("Incorrect Username or Password!");
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
