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
        // Call to update the current state of each device
        for (Devices device : Devices.values()){
            device.getDeviceCurrentState(); // Call to initialize and get what the state of the devices are
        }
        // Once all requests placed, wait for a shared timeout of 10 sec... with a error print if 10 seconds pass
        int count = 0;
        long timeout = System.currentTimeMillis() + 1000; // 10 sec max wait time
        while (System.currentTimeMillis() < timeout){
            boolean finished = true;
            for (Devices device : Devices.values()){
                if (!device.isNewStateRead())
                    finished = false;
            }
            if (finished)
                break;
        }
        // Hard coded generate all of the devices as session attributes, sorted with the Device.name() as a reverence to each device
        System.out.println("----- Devices added to session attributes-----");
        for (Devices device : Devices.values()){
            HttpSession session = request.getSession();
            Devices.State state = device.getDeviceCurrentState();
            session.setAttribute(device.name(), state.toString());
            System.out.println(device.name() + ": State = " + state.toString());
        }
        System.out.println("----- ===== ===== ===== ===== ===== -----");

        // TODO
        // TODO: Then have the jsp hard coded call each device according to the session attribute for each specific device
        // TODO

        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }
}