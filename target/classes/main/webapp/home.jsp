<!-- Uncomment this when inside the jsp as it's needed for encoding -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html" charset="ISO-8859-1"/>
    <meta name="viewport" content="width= device-width, initial-scale=1.0">
    <meta name="description" content="Höme® is an enchanced new home, A home that is controllable from right here on the website or the comfort of your android device.">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Höme</title>

    <!-- Dependencies -->
    <link rel="stylesheet" href="./css/dependencies/owl.carousel.min.css">
    <link rel="stylesheet" href="./css/dependencies/owl.theme.default.min.css">

    <script src="./js/dependencies/Jquery3.5.1.min.js"></script>
    <script src="./js/dependencies/owl.carousel.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.js"
            integrity="sha256-2JRzNxMJiS0aHOJjG+liqsEOuBb6++9cY4dSOyiijX4=" crossorigin="anonymous"></script>

    <!-- Stylesheet reference -->
    <link rel="stylesheet" href="./css/navbar.css">
    <link rel="stylesheet" href="./css/popup.css">
    <link rel="stylesheet" href="./css/homepage.css">

</head>

<header>
    <!-- ------------- NAVIGATION ------------- -->
    <nav class="nav">
        <div class="nav-bar flex-row">

            <div class="nav-brand">
                <a href="#">
                    <img class="brand-logo" src="./assets/vectors/3D_icons/047-automation.svg" width="50">
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
                        <a href="#intro">About Höme</a>
                    </li>
                    <li class="nav-link">
                        <a href="#features">Features</a>
                    </li>
                    <li class="nav-link">
                        <a href="#setup">Setup</a>
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
<!-- ------------- Login Pop-up  ------------- -->
<div class="modal" id="modal">

    <button data-modal-close class="btn-login-close">&times;</button>

    <div class="modal-header">
        <div class="title title-login" id="title-login">Login</div>
        <div class="title title-register" id="title-register">Register</div>
    </div>

    <div class="modal-body">
        <form class="modal-form" action="${pageContext.request.contextPath}/login" method="post">

            <div class="form-section form-username">
                        <span class="form-txt txt-username">
                            Username:
                        </span>
                <input class="txt-in" type="text" name="username">
            </div>

            <div class="form-section form-password">
                        <span class="form-txt txt-password">
                            Password:
                        </span>
                <input class="txt-in" type="password" name="password">
            </div>

            <div class="form-section form-name" id="form-name">
                        <span class="form-txt txt-name">
                            Name:
                        </span>
                <input class="txt-in" type="text" name="name">
            </div>

            <div class="form-section form-email" id="form-email">
                        <span class="form-txt txt-email">
                            Email:
                        </span>
                <input class="txt-in" type="text" name="email">
            </div>

            <button class="popup-btn btn_login_register" name="btn_request" type="submit" value="register" id="btn-register">
                Register
            </button>

            <button class="popup-btn btn_login_login" name="btn_request" type="submit" value="login" id="btn-login">
                Login
            </button>
        </form>
    </div>
</div>
<div id="overlay"></div>

<!-- Popup Script -->
<script src="./js/popup.js"></script>

<!-- The Contents of the page -->
<main>

    <!-- The Intro Section -->
    <section class="site-intro" id="intro">
        <div class="site-about">
            <h3>About Höme</h3>
            <h1>Welcome to your new Höme</h1>
            <p>
                Höme® is an enchanced new home,  A home that is controllable from right here on the website or the comfort of your android device.
                Did you forget to check if you left the meatloaf cooking on the stove?
                Check, and while you're at it check if you left the door open, or the lights on, or the heater on.
                Keep updated on the status of up to 17* different devices from around your home.
                No need to go back home to change them neither that's so last century, with Höme® you can  simply turn them all* off.
                Use the website if your're at the office or just download our app you decide!
                Automate your home with advance home automation features that makes setup and control simpler than ever.
            </p>
            <button class="btn">Learn More</button>
        </div>
    </section>


    <div id="features">
        <h3>Features</h3>
        <h2>Freedom of Choice</h2>
        Control your home with the device you want!
    </div>
    <hr>


    <div id="setup">
        <h3>Setup & Install</h3>
        <h2>Cloud and Go!</h2>
        <p>
            The Höme® App communicates with a cloud-based infrastructure.
            This means that once you make an account with us and your Höme is built, you can get controlling in no time.
            No more Setup on your end required! The cloud allows us to bring security adjustments and maintenance to your home without the need for you to make a service call.
            Once your account is registered and the höme of your dreams built you will be able to download our app or get straight to controlling your höme from anywhere in the world.
            Or from right here on the website, all you have to do is choose.
        </p>
    </div>
    <hr>
</main>

<footer>
    <h3>Höme</h3>
    <ul>
        <li>
            <a href="https://www.hkr.se/" target="_blank" rel="noopener noreferrer">>
                <img src="../assets/images/logo_hkr.png" width="60">
            </a>
        </li>
        <li>
            <a href="https://github.com/Interactive-House-Smart-House-HKR-Grp3/Web_Interface_Code_Smart_House" target="_blank" rel="noopener noreferrer">>
                <img src="../assets/images/logo_github.png" width="60">
            </a>
        </li>
    </ul>
</footer>

</body>
</html>