package main.java.servlets;

import main.java.mock.database.RequestManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ButtonServlet", urlPatterns = "/button")
public class ButtonRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestManager rm = new RequestManager();
        String button = request.getParameter("button");

        if (button.equalsIgnoreCase("statistics")){
            // Send the user to their destination
            request.getRequestDispatcher("/statisticsPage").forward(request, response);
        } else if (button.equalsIgnoreCase("userPage")){
            // Send the user to their destination
            request.getRequestDispatcher("/userPage").forward(request, response);
        } else if (button.equalsIgnoreCase("logout")){
            // Send the user to their destination
            request.getRequestDispatcher("/homePage").forward(request, response);
        } else {
            // if the request doesnt match any given above, print an error for now
            System.err.println("Button servlet missing function: " + request.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("A request has been made to /button without pressing a button...");
    }
}
