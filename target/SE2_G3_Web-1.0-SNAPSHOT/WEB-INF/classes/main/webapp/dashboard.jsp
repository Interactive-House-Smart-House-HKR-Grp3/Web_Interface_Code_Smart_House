<%@ page import="data.models.devices.Devices" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html" charset="ISO-8859-1" />
    <meta name="viewport" content="width= device-width, initial-scale=1.0">
    <meta name="description"
          content="With Höme® Dashboard you will be able to control your Höme® from the comfort of your browser">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Höme | Dashboard</title>

    <!-- Dependencies -->
    <link rel="stylesheet" href="./css/dependencies/owl.carousel.min.css">
    <link rel="stylesheet" href="./css/dependencies/owl.theme.default.min.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.js"
            integrity="sha256-2JRzNxMJiS0aHOJjG+liqsEOuBb6++9cY4dSOyiijX4=" crossorigin="anonymous"></script>
    <script src="./js/dependencies/Jquery3.5.1.min.js"></script>
    <script src="./js/dependencies/owl.carousel.js"></script>

    <!-- Stylesheet reference -->
    <link rel="stylesheet" href="./css/navbar.css">
    <link rel="stylesheet" href="./css/popup.css">
    <link rel="stylesheet" href="./css/slider.css">
    <link rel="stylesheet" href="./css/dashboard.css">
</head>

<!-- Java variables -->
    <%
        Devices[] devices = new Devices[Devices.values().length];

        int count = 0;
        for (Devices device : Devices.values()){
            devices[count] = device;
            count++;
        }

        StringBuilder devicesString = new StringBuilder();
        for (int i = 0; i < devices.length; i++) {
            // Makes sure that the layouts are unique for each device that needs to be
            switch (devices[i].name()) {
                // TODO create different layouts for each device

                // A device not found will take this layout
                default: {
                    // This categorizes the devices into sections
                    devicesString.append("<section id=\"lights\">");

                    // This is needed for every device
                    devicesString.append("<div class=\"devices-container\">");

                    // Create a format to display the device
                    devicesString.append("<div class=\"device-item device-item-").append(i).append("\">")
                            .append("<h3 class=\"device-title\">")
                            .append(devices[i].name());

                    if (devices[i].isStatisticsProvider()) {
                        // The statistics button
                        // *** MUST BE INSIDE THE H3 TAG ***
                        // TODO resolve sending the user to a statistics page specific to this device
                        devicesString.append("<form class=\"form-btn\" action=\"").append(request.getContextPath()).append("/button\" method=\"post\">\n" +
                                "<button class=\"btn btn-statistics\" name=\"button\" type=\"submit\" value=\"statistics\">\n" +
                                "<i class=\"fas fa-chart-pie\"></i>\n" +
                                "</button>\n" +
                                "</form>");
                    }
                    devicesString.append("</h3>");

                    // Wait for the state of the device to be read
                    String currentState = devices[i].getDeviceCurrentState().toString();
                    count = 0;
                    while (!devices[i].isNewStateRead() && count < 10) {
                        try {
                            Thread.sleep(20);
                            count++;
                        } catch (Exception ignored) {}
                    }

                    try {
                        devicesString.append("<h6 class=\"device-state\">").append(currentState).append("</h6>");
                    } catch (Exception e) {
                        devicesString.append("<h6 class=\"device-state\">").append("N/A").append("</h6>");
                    }

                    devicesString.append("<img class=\"img-device\" src=\"./assets/vectors/3D_icons/005-lamp.svg\">");

                    if (devices[i].isChangeableState()) {
                        // The request from the button will be checked with a switch case using the value = "device.name" + "-" + "device.currentState"
                        devicesString.append("<form action=\"").append(request.getContextPath()).append("/outputRequest\" method=\"post\">")
                                .append("<button class=\"btn btn-device-toggle\" name=\"btn_deviceToggle\" type=\"submit\" value=\"").append(devices[i].name()).append("-").append(currentState).append("\">")
                                .append("Toggle State")
                                .append("</button>")
                                .append("</form>");
                    }
                    break;
                }
            }

            // Close this device section
            devicesString.append("</div>").append("</div>").append("</section>");
        }
        // Send the device information to the user page
        String query = devicesString.toString();
    %>

<header>
    <!-- ------------- NAVIGATION ------------- -->
    <nav class="nav">
        <div class="nav-bar flex-row">

            <div class="nav-brand">
                <a href="#">
                    <img class="brand-logo" src="./assets/vectors/3D_icons/006-home automation.svg" width="50">
                    <span class="brand-brandname">Höme</span>
                </a>
            </div>

            <div>
                <ul class="nav-items" id="nav-bar">
                    <li class="nav-link">
                        <a href="#alarms">Alarms</a>
                    </li>
                    <li class="nav-link">
                        <a href="#lights">Lights</a>
                    </li>
                    <li class="nav-link">
                        <a href="#temperatures">Temperatures</a>
                    </li>
                    <li class="nav-link">
                        <a href="#misc">Misc.</a>
                    </li>
                </ul>
            </div>

            <!-- ------------- Menu used if screen <= 750px  ------------- -->
            <div class="menu">
                <div class="icons">
                    <span>
                        <i class="fas fa-bars"></i>
                    </span>
                </div>
            </div>

            <button data-modal-target="#modal" class="user">
                <i class="fas fa-user-circle"></i>
            </button>
        </div>
    </nav>
</header>
<!-- The Java Script file that controls the navbar-->
<script src="./js/navbar.js"></script>

<!--  ------------- The skipTutorial() is inside the dashboard.js  -->

<!-- ------------- Account Pop-up  ------------- -->
<div class="modal" id="modal">
    <button data-modal-close class="btn-login-close">&times;</button>
    <div class="modal-header">
        <div class="title">Welcome Höme</div>
    </div>
    <div class="modal-body">
        <button class="popup-btn btn-info" id="btn-info">
            See Info
        </button>
        <form action="${pageContext.request.contextPath}/button" method="post">
            <button name="button" class="popup-btn btn-logout" type="submit" value="logout">Logout</button>
        </form>

    </div>
</div>
<div id="overlay"></div>

<!-- Popup Script -->
<script src="./js/popup.js"></script>

<body onload="skipTutorial()">

<div class="header-padding"></div>

<section class="window-container">
    <section class="sidebar">

        <h2>Controls</h2>
        <button class="btn" id="btn-tutorial">
            See Tutorial
        </button>

        <div class="sidebar-padding"></div>


        <h2>Devices</h2>
        <div class="controls-devices">
            <div class="img-btn" id="btn-alarm">
                <i class="fas fa-bell"></i>
                <h3>Alarms</h3>
            </div>

            <div class="img-btn" id="btn-light">
                <i class="far fa-lightbulb"></i>
                <h3>Lights</h3>
            </div>

            <div class="img-btn" id="btn-temp">
                <i class="fas fa-thermometer-full"></i>
                <h3>Temps</h3>
            </div>

            <div class="img-btn" id="btn-misc">
                <i class="fas fa-database"></i>
                <h3>Misc.</h3>
            </div>
        </div>

        <div class="sidebar-padding"></div>

        <h2>Höme Status</h2>
        <div class="controls-status">

            <div class="img-status">
                <i class="fas fa-charging-station"></i>
                <h3>15 kW/Hr</h3>
            </div>

            <div class="img-status">
                <i class="fas fa-sun" id="twilightIcon"></i>
                <h3 id="twilightText">Night</h3>
            </div>
        </div>

    </section>

    <!-- The Contents of the page -->
    <main id="contents">

        <!-- The Intro Section -->
        <section class="site-intro" id="info">

            <div class="site-about">
                <h3>Dashboard</h3>
                <h1>Welcome Höme user_name!</h1>
                <p>
                    This is your Dashboard, from here you will be able to control your Höme® and see all your
                    available
                    devices.
                </p>

                <p>
                    Please take a second to read this tutorial as it will guide you on how to control your Höme® as
                    well
                    give some friendly tips!
                </p>

                <h2>
                    Once you're ready click the button below.
                </h2>
                <button class="btn btn-learn" id="btn-learn">Get Started</button>

                <div class="section-padding"></div>

                <h2>The sidebar</h2>
                <p>The side bar is the place where the website will interact with you. The sidebar contains three main sections which will be described in parts below the sidebar is made for ease and will resize with your browser window</p>

                <img src="./assets/images/Screenshot-Sidebar.jpg" alt="" srcset="">

                <h3 class="red">Controls</h3>
                <p>The controls section is shown in the screenshot highlighted in red and is located at  the top of the sidebar. This section is responsible for the controls used to control the website as of the version of the website there is only one control in this section. Clicking this "See tutorial" button will reopen this tutorial at anytime. </p>

                <h3 class="blue">Devices</h3>
                <p>The blue section in the screen shot shows the devices list. These 4 buttons will toggle which device categories you would be able to see turn one of them on or all at the same time! the choice is yours!</p>

                <img src="./assets/images/Screenshot-sidebar-devices.jpg" alt="devices screenshot" srcset="">

                <h2>Your Devices</h2>
                <p>your devices are hiding when you first start the website. Get them to come out using the previously mentioned devices section in the sidebar. Devices are the way you control your home. The following is an example of a device most devices are straight forward however, each device has a special icon on the top right (highlighted in red) clicking this button lets you discover more about the device and show some device statistics.</p>

                <img src="./assets/images/Screenshot-device.jpg" alt="" srcset="">
            </div>
        </section>

    </main>
</section>

<script src="./js/dashboard.js"></script>
</body>

</html>

<script>
    <%
        // Run a script to update the variables with the subscriptions
        // TODO: Write a java script code snippet which calls this commentated section every 5 seconds or so to update the variables used for the devices

        // TODO: might have to write them in the java code above in a different manner? calling variables to be written instead of the code chunk?

        // TODO: instead of '<6=query6>' have {$variable} for each device, which may update when the variable is updated here...

        /*
        for (Devices device : Devices.values()) {
            // Send a request to get the current device state
            device.getDeviceCurrentState();
            int count = 0;
            // Delay to try to allow for the state to be set for currently implemented devices
            while (!device.isNewStateRead() && count < 50) {
                try {
                    Thread.sleep(20);
                    count++;
                } catch (Exception ignored) {
                }
            }
        }
        */
    %>
</script>

