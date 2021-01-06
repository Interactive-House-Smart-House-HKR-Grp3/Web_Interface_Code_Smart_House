<%@ page import="data.models.devices.Devices" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
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
        // These are the Global Variables used in the devices section of the JSP
        Double consumption = 0.0;
        String dayNight = "";
        String imgDayNight = "";

        //  Store the device states to use
        HashMap<String, String> devicesStates = new HashMap<>();
        session = request.getSession();

        System.out.println("----- Devices read from session attributes -----");
        for (Devices device : Devices.values()){
            devicesStates.put(device.name(), session.getAttribute(device.name()).toString());
            System.out.println(device.name() + ": State = " + session.getAttribute(device.name()).toString());

            // Set images
            switch (device.name()){
                case "TWILIGHT":{
                    // TODO if day use sun image else use moon image
                    imgDayNight = "sun"; // else moon
                    break;
                }
            }
            // TODO: Hard code statistics providing link with button
        }
        System.out.println("----- ===== ===== ===== ===== ===== -----");
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

<!--  ------------- The skipTutorial() is inside the dashboard.js  -->
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
                <h3>
                    <%=devicesStates.get(Devices.TWILIGHT.name())%>
                </h3>
            </div>

            <div class="img-status">
                <i class="fas fa-<%=imgDayNight%>" id="twilightIcon"></i>
                <h3 id="twilightText">
                    <%=devicesStates.get(Devices.TWILIGHT.name())%>
                </h3>
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

        <!-- This is the start of the devices -->
        <!-- Each device requires a State and a Stats Button generated from above -->

        <section id="alarms">
            <div class="devices-container">

                <div class="device-item device-item-2">
                    <h3 class="device-title">Fire Alarm
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.FIRE_ALARM.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/042-fire alarm.svg">
                </div>

                <div class="device-item device-item-3">
                    <h3 class="device-title">Housebreak Alarm
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.BURGLAR_ALARM.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/011-insurance.svg">
                </div>

                <div class="device-item device-item-3">
                    <h3 class="device-title">Water Leakage
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.WATER_LEAKAGE.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/041-water tap.svg">
                </div>

                <div class="device-item device-item-4">
                    <h3 class="device-title">Power Cut
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.POWER_CUT.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/008-power button.svg">
                </div>

            </div>
        </section>

        <section id="lights">
            <div class="devices-container">

                <div class="device-item device-item-1">
                    <h3 class="device-title">Indoor Light
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.INDOOR_LIGHT.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/005-lamp.svg">

                    <div class="device-controls">
                        <button class="btn btn-device-toggle" onclick="requestStateChange('INDOOR_LIGHT', '<%=devicesStates.get(Devices.INDOOR_LIGHT.name())%>')">
                            Toggle State
                        </button>
                    </div>
                </div>

                <div class="device-item device-item-2">
                    <h3 class="device-title">Outdoor Light
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.OUTDOOR_LIGHT.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/044-lamp.svg">

                    <div class="device-controls">
                        <button class="btn btn-device-toggle" onclick="requestStateChange('OUTDOOR_LIGHT', '<%=devicesStates.get(Devices.OUTDOOR_LIGHT.name())%>')">
                            Toggle State
                        </button>
                    </div>
                </div>

                <div class="device-item device-item-3">
                    <h3 class="device-title">Auto Lights
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.AUTO_MODE.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/050-automation.svg">

                    <div class="device-controls">
                        <button class="btn btn-device-toggle" onclick="requestStateChange('AUTO_MODE', '<%=devicesStates.get(Devices.AUTO_MODE.name())%>')">
                            Toggle State
                        </button>
                    </div>
                </div>
            </div>
        </section>

        <section id="temperatures">
            <div class="devices-container">

                <div class="device-item device-item-1">
                    <h3 class="device-title">Indoor Temp
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.INDOOR_TEMPERATURE.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/014-temperature.svg">
                </div>

                <div class="device-item device-item-2">
                    <h3 class="device-title">Outdoor Temp
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.OUTDOOR_TEMPERATURE.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/036-garage.svg">
                </div>

                <div class="device-item device-item-3">
                    <h3 class="device-title">Heating Indoor
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.HEATING_INDOOR.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/simple_icons/002-smartphone.svg">

                    <div class="device-controls">
                        <button class="btn btn-device-toggle" onclick="requestStateChange('HEATING_INDOOR', '<%=devicesStates.get(Devices.HEATING_INDOOR.name())%>')">
                            Toggle State
                        </button>
                    </div>
                </div>

                <div class="device-item device-item-4">
                    <h3 class="device-title">Heating Loft
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.HEATING_LOFT.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/007-air conditioner.svg">

                    <div class="device-controls">
                        <button class="btn btn-device-toggle" onclick="requestStateChange('HEATING_LOFT', '<%=devicesStates.get(Devices.HEATING_LOFT.name())%>')">
                            Toggle State
                        </button>
                    </div>
                </div>
            </div>
        </section>

        <section id="misc">
            <div class="devices-container">

                <div class="device-item device-item-1">
                    <h3 class="device-title">Door
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.DOOR.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/039-smart%20door.svg">

                    <div class="device-controls">
                        <button class="btn btn-device-toggle" onclick="requestStateChange('DOOR', '<%=devicesStates.get(Devices.DOOR.name())%>')">
                            Toggle State
                        </button>
                    </div>
                </div>

                <div class="device-item device-item-1">
                    <h3 class="device-title">Stove
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.STOVE.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/009-solar panel.svg">

                    <div class="device-controls">
                        <button class="btn btn-device-toggle" onclick="requestStateChange('STOVE', '<%=devicesStates.get(Devices.STOVE.name())%>')">
                            Toggle State
                        </button>
                    </div>
                </div>

                <div class="device-item device-item-2">
                    <h3 class="device-title">Window
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.WINDOW.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/033-window.svg">

                    <div class="device-controls">
                        <button class="btn btn-device-toggle" onclick="requestStateChange('WINDOW', '<%=devicesStates.get(Devices.WINDOW.name())%>')">
                            Toggle State
                        </button>
                    </div>
                </div>

                <div class="device-item device-item-3">
                    <h3 class="device-title">Fan
                        <form class="form-btn" action="${pageContext.request.contextPath}/button" method="post">
                            <button class="btn btn-statistics" name="button" type="submit" value="statistics">
                                <i class="fas fa-chart-pie"></i>
                            </button>
                        </form>
                    </h3>

                    <h4 class="device-state">
                        <%=devicesStates.get(Devices.FAN.name())%>
                    </h4>

                    <img class="img-device" src="./assets/vectors/3D_icons/043-fan.svg">

                    <div class="device-controls">
                        <input type="range" min="1" max="100" value="50" class="slider" id="myRange">
                    </div>
                </div>
            </div>
        </section>

    </main>
</section>

<script src="./js/dashboard.js"></script>
</body>

</html>

<script>
    // Function called to update the page dynamically with a state change request
    function requestStateChange(device, state){
        $.ajax({
            url: "${pageContext.request.contextPath}/outputRequest",
            type: "POST",
            data: {btn_deviceToggle : device+"-"+state},

            success: function () {
                alert("Success");
                getDeviceStates();
            },

            error: function() {
                alert ("Sorry, there was a problem!");
            }
        });
        return false;
    }

    // When the page is loaded, every 3 seconds call a function to update the state of the devices
    $(document).ready(function() {
        getDeviceStates()
    });

    function getDeviceStates() {
        // Retrieve the device states and update the local variables to match
        <%
        for (Devices devices : Devices.values()){
            devicesStates.replace(devices.name(), devices.getDeviceCurrentState().toString());
        }
        %>
    }
</script>

