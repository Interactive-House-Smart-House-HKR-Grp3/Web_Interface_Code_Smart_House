package main.java.servlets.pages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatisticsPageServlet", urlPatterns = "/statisticsPage")
public class StatisticsPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO: Retrieve the device statistics requested to be viewed, add it as parameters (this should be handled by a statisticsPageFormatter like how the UserPage does it)
        // TODO: Change to sending using a response method

        request.getRequestDispatcher("/statistics.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }
}