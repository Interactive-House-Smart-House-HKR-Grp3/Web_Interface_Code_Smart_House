<!-- Uncomment this when inside the jsp as it's needed for encoding -->
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
        String query = (String) request.getAttribute("queryResult");
    %>

<header>
    <!-- ------------- NAVIGATION ------------- -->
    <nav class="nav">
        <div class="nav-bar flex-row">

            <div class="nav-brand">
                <a href="#">
                    <img class="brand-logo" src="./assets/vectors/simple_icons/027-smart home.svg" width="50">
                    <span class="brand-brandname">Höme</span>
                </a>
            </div>

            <!-- ------------- Menu used if screen <= 750px  ------------- -->
            <div class="menu">
                <div class="icons">
                    <span>
                        <i class="fas fa-bars"></i>
                    </span>
                </div>
            </div>

            <div>
                <ul class="nav-items">
                    <li class="nav-link">
                        <a href="#info">Info</a>
                    </li>
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
    <div class="modal-header">
        <div class="title">Welcome user_name</div>
        <button data-modal-close class="btn-login-close">&times;</button>
    </div>
    <div class="modal-body">
        <form action="${pageContext.request.contextPath}/button" method="post">
            <button name="button" class="btn-logout" type="submit" value="logout">Logout</button>
        </form>
    </div>
</div>
<div id="overlay"></div>
<!-- Popup Script -->
<script src="./js/popup.js"></script>

<!-- The Contents of the page -->
<main>

    <!-- The Intro Section -->
    <section class="site-intro" id="info">

        <!-- TODO: Create a 1 time tutorial message if it's the first time visiting this page  -->
        <div class="site-about">
            <h3>Dashboard</h3>
            <h1>Welcome Höme user_name</h1>
            <p>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Quam blanditiis, inventore assumenda
                distinctio molestiae vitae adipisci repellendus quae pariatur qui ipsa! Quibusdam quia fugit
                incidunt nobis praesentium earum, cum voluptas.
            </p>
        </div>
    </section>

    <!-- TODO: A floating status should show if the house has power -->
    <!-- TODO: A floating status should show the house electricity consumption -->
    <!-- TODO: A floating status should show if it is day or night out -->

    <!-- TODO: divide the devices into their categories  -->
    <section id="devices">
        <div class="devices-container">
            <!-- Uncomment this when populating it within the jsp -->
            <%=query%>

            <!-- Delete this part when inside the jsp as it's for design testing purposes only -->
        </div>
    </section>

</main>
</body>

</html>