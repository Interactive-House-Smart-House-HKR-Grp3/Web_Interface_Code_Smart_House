package main.java.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatisticsPageServlet", urlPatterns = "/statisticsPage")
public class StatisticsPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // include a check to see that user login exists for this page to load, otherwise redirect to the home page?
        //if (request.getAttribute("authUser") == null)
        //    response.sendRedirect("/home.jsp");

        // TODO: this should be switched out with a response, but need a way to keep user authentication checked on sub directories
        // response.sendRedirect("/user.jsp");
        request.getRequestDispatcher("/statistics.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }
}
