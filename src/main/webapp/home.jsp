<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="./css/all.css">

    <!-- Stylesheet reference -->
    <link rel="stylesheet" href="./css/homepage.css">

    <!-- Jquery Java Script Extension -->
    <script src="./js/Jquery3.5.1.min.js"></script>
</head>
<header>
    <!-- ------------- NAVIGATION ------------- -->
    <nav class="nav">
        <div class="nav-bar flex-row">

            <div class="nav-brand">
                <a href="#" class="text-brand">
                    <img src="Vectors/027-smart home.svg" width="50">
                    <div>Höme</div>
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
                        <a href="#">About Höme</a>
                    </li>
                    <li class="nav-link">
                        <a href="#">Features</a>
                    </li>
                    <li class="nav-link">
                        <a href="#">Setup</a>
                    </li>
                </ul>
            </div>

            <button data-modal-target="#modal" class="user">
                <i class="fas fa-user-circle"></i>
            </button>
        </div>
    </nav>
</header>
<!-- The Java Script file that controls the header-->
<script src="./js/homepage_header.js"></script>
<body>
<!-- ------------- Login Pop-up  ------------- -->
<div class="modal" id="modal">
    <div class="modal-header">
        <div class="title">Please Login or Register</div>
        <button data-modal-close class="btn-login-close">&times;</button>
    </div>
    <div class="modal-body">
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div>
                Username: <input type="text" name="username">
            </div>
            <div>
                Password: <input type="password" name="password">
            </div>

            <button class="btn_login_login" name="btn_request" type="submit" value="login">
                Login
            </button>

            <div>
                Name: <input type="text" name="name">
            </div>
            <div>
                Email: <input type="text" name="email">
            </div>

            <button class="btn_login_register" name="btn_request" type="submit" value="register">
                Register
            </button>
        </form>
    </div>
</div>
<div id="overlay"></div>

<!-- Popup Script -->
<script src="./js/popup.js"></script>

<main>
    <section class="title">
        <div id="About">
            <h3>Welcome to your new Höme</h3>
            <h2>About Höme</h2>
            <p>
                Höme® is an enchanced new home,  A home that is controllable from right here on the website or the comfort of your android device.
                Did you forget to check if you left the meatloaf cooking on the stove?
                Check, and while you're at it check if you left the door open, or the lights on, or the heater on.
                Keep updated on the status of up to 17* different devices from around your home.
                No need to go back home to change them neither that's so last century, with Höme® you can  simply turn them all* off.
                Use the website if your at the office or just download our app you decide!
                Automate your home with advance home automation features that makes setup and control simpler than ever.


            </p>
        </div>
    </section>

    <div id="Features">
        <h3>Features</h3>
        <h2>Freedom of Choice</h2>
        Control your home with the device you want!
    </div>
    <hr>


    <div id="Setup">
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
                <img src="Images/HKR-logga.png" width="60">
            </a>
        </li>
        <li>
            <a href="https://github.com/Interactive-House-Smart-House-HKR-Grp3/Web_Interface_Code_Smart_House" target="_blank" rel="noopener noreferrer">>
                <img src="Images/github_logo.png" width="60">
            </a>
        </li>
    </ul>
</footer>

</body>
</html>