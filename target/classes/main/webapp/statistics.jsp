<!-- Uncomment this when inside the jsp as it's needed for encoding -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!Doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width= device-width, initial-scale=1.0">
    <meta name="description" content="Understand how you're using your Höme® with the statistics of each of your devices.">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Höme | Statistics</title>

    <!-- Dependencies -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.js"
            integrity="sha256-2JRzNxMJiS0aHOJjG+liqsEOuBb6++9cY4dSOyiijX4=" crossorigin="anonymous"></script>
    <script src="./js/dependencies/Jquery3.5.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"
            integrity="sha512-d9xgZrVZpmmQlfonhQUvTR7lMPtO7NkZMkA0ABN3PHCbKA5nqylQ/yWlFAyY6hYgdF1Qh6nYiuADWwKB4C2WSw=="
            crossorigin="anonymous"></script>

    <!-- Stylesheet reference -->
    <link rel="stylesheet" href="./css/navbar.css">
    <link rel="stylesheet" href="./css/popup.css">
    <link rel="stylesheet" href="./css/statistics.css">
    <link rel="stylesheet" href="./css/dropdown.css">

</head>

<header>
    <!-- ------------- NAVIGATION ------------- -->
    <nav class="nav">
        <div class="nav-bar flex-row">

            <div class="nav-brand dropdown">
                <a href="#">
                    <img class="brand-logo" src="./assets/vectors/3D_icons/029-house automation.svg" width="50">
                    <span class="brand-brandname">Höme</span>
                </a>

                <!-- Dropdown Menu -->

                <div class="dropdown-content">
                    <form action="${pageContext.request.contextPath}/button" method="post">
                        <button name="button" class="btn btn-back" type="submit" value="dashboard">
                            Dashboard
                        </button>
                    </form>
                </div>

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

<body>
<!-- ------------- Account Pop-up  ------------- -->
<div class="modal" id="modal">
    <button data-modal-close class="btn-login-close">&times;</button>
    <div class="modal-header">
        <div class="title">Welcome Höme</div>
    </div>
    <div class="modal-body">

        <form action="${pageContext.request.contextPath}/button" method="post">
            <button name="button" class="popup-btn btn-logout" type="submit" value="logout">Logout</button>
        </form>

    </div>
</div>
<div id="overlay"></div>

<!-- Popup Script -->
<script src="./js/popup.js"></script>


<!-- The page contents -->
<main>
    <div class="graph-container">
        <canvas id="chart-electricity"></canvas>
    </div>

    <!--
        This script part is what the server needs to send to each graph

        A possible solution would be the same as how we did the devices and html injection
    -->
    <script>
        // These variables are then used inside the script that is declared right after this
        const data = [65, 59, 80, 81, 56, 55, 40];
        const labels = ["January", "February", "March", "April", "May", "June", "July"];
    </script>

    <!-- This is the script that uses the above injected code -->
    <script src="./js/chart.js"></script>
</main>

</body>

</html>