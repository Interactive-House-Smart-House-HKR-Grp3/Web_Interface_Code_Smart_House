<!-- Uncomment this when inside the jsp as it's needed for encoding -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html" charset="ISO-8859-1" />
    <meta name="viewport" content="width= device-width, initial-scale=1.0">
    <meta name="description"
          content="Höme® is an enhanced new home, A home that is controllable from right here on the website or the comfort of your android device.">
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
                    <li class="nav-link" id="link-about">
                        <a href="#intro">About Höme</a>
                    </li>
                    <li class="nav-link" id="link-vision">
                        <a href="#vision">Vision</a>
                    </li>
                    <li class="nav-link" id="link-setup">
                        <a href="#setup">Setup</a>
                    </li>
                    <li class="nav-link" id="link-features">
                        <a href="#features">Features</a>
                    </li>
                    <li class="nav-link" id="link-download">
                        <a href="#download">Download</a>
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

            <button class="popup-btn btn_login_register" name="btn_request" type="submit" value="register"
                    id="btn-register">
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
<script src="./js/popup.js" onload="highlightLogin()"></script>

<!-- The Contents of the page -->
<main>

    <!-- The Intro Section -->
    <section class="site-intro" id="intro">
        <img class="img available" src="./assets/images/available-app.png"
             alt="Home is available to be controlled from all these devices: mobile, website" srcset="">

        <h1>Control your home from the comfort of your favorite device</h1>
        <p>Höme makes homes around the task accessible to everyone. control and diagnose your home including alarms
            from any device you chose.</p>
        <button class="btn download" onclick="window.location.href = '#download'">
            <i class="fas fa-cloud-download-alt"></i>
            Download App
        </button>
    </section>

    <div class="section-padding"></div>

    <section class="site-about" id="about">
        <img src="./assets/vectors/3D_icons/047-automation.svg" alt="home logo" srcset="" class="logo">
        <h3><span>About Höme</span></h3>
        <h1>Welcome to your new Höme</h1>
        <p>
            Höme® is an enhanced new home, A home that is controllable from right here on the website or the comfort
            of your android device.
            Did you forget to check if you left the meatloaf cooking on the stove?
            Check, and while you're at it check if you left the door open, or the lights on, or the heater on.
            Keep updated on the status of up to 17* different devices from around your home.
            No need to go back home to change them neither that's so last century, with Höme® you can simply turn
            them all* off.
            Use the website if you're at the office or just download our app you decide!
            Automate your home with advance home automation features that makes setup and control simpler than ever.
        </p>
    </section>

    <div class="section-padding"></div>

    <section class="site-vision" id="vision">
        <h3>Our Vision</h3>
        <h2>Home is where the heart is</h2>

        <div class="vision-container">

            <div class="vision vision-mobile">
                <h2>Mobile Development</h2>
                <i class="icon fas fa-mobile-alt"></i>

                <p>Mobile development is important to us and will allow our users to control their home with a device they already carry around. keep track of your home everywhere your phone goes.</p>

                <img src="./assets/images/Screenshot-application.jpg" alt="mobile screen showing the application" srcset="">
            </div>

            <div class="vision vision-handicap">
                <h2>Handicap Accessibility</h2>
                <i class="icon fas fa-wheelchair"></i>
                <p>Höme was started with handicap accessibility in mind, our goal was to create a home that would get rid of the needless hurdles that was still riddling household. With Höme traversing though your home is nothing but a touch away</p>
                <img src="./assets/images/woman-in-wheelchair-laptop.jpg" alt="Woman on a wheelchair holding a laptop"
                     srcset="">
            </div>

            <div class="vision vision-website">
                <h2>Website Companion App</h2>
                <i class="icon fas fa-laptop"></i>
                <p>We want to make Höme easy to access not requiring any specific device for compatibility. This is where the website comes in giving people the option to control their home from any device. With a seamless design in mind making controls for your device be exactly where you expect them</p>
                <img src="./assets/images/screen-dashboard.png" alt="screen shot of the website dashboard" srcset="">
            </div>
        </div>
    </section>

    <section class="site-setup" id="setup">
        <h3>Setup & Install</h3>
        <h2>Cloud and Go!</h2>

        <img class="gif cloud" src="./assets/gifs/cloudsync.gif" alt="animated cloud" srcset="">
        <img class="gif download-arrow download-arrow-1" src="./assets/gifs/arrow_down_bounce.gif"
             alt="animated download arrow" srcset="">
        <img class="gif download-arrow download-arrow-2" src="./assets/gifs/arrow_down_bounce.gif"
             alt="animated download arrow" srcset="">
        <img class="gif download-arrow download-arrow-3" src="./assets/gifs/arrow_down_bounce.gif"
             alt="animated download arrow" srcset="">

        <div class="destination mobile">

            <i class="fas fa-mobile-alt"></i>
        </div>
        <div class="destination laptop">

            <i class="fas fa-laptop"></i>
        </div>
        <div class="destination home">

            <i class="fas fa-home"></i>
        </div>


        <p>
            The Höme® App communicates with a cloud-based infrastructure.
            This means that once you make an account with us and your Höme is built, you can get controlling in no
            time.
            No more Setup on your end required! The cloud allows us to bring security adjustments and maintenance to
            your home without the need for you to make a service call.
            Once your account is registered and the höme of your dreams built you will be able to download our app
            or get straight to controlling your höme from anywhere in the world.
            Or from right here on the website, all you have to do is choose.
        </p>
    </section>

    <div class="section-padding"></div>

    <section class="site-features" id="features">
        <h3>Features</h3>
        <h2>Control all aspects of your Höme</h2>

        <div class="home-container">

            <div class="poi status">
                <i class="fas fa-map-marker"></i>
                <span>
                        Status
                    </span>
            </div>

            <div class="poi alarms">
                <i class="fas fa-map-marker"></i>
                <span>
                        Alarms
                    </span>
            </div>

            <div class="poi lights">
                <i class="fas fa-map-marker"></i>
                <span>
                        Lights
                    </span>
            </div>

            <div class="poi temp">
                <i class="fas fa-map-marker"></i>
                <span>
                        Temperatures
                    </span>
            </div>

            <div class="poi misc">
                <i class="fas fa-map-marker"></i>
                <span>
                        Misc.
                    </span>
            </div>
        </div>

    </section>

    <div class="section-padding"></div>

    <section id="download" class="site-download">
        <h2>Download Our App</h2>
        <h3>Take your höme with you where ever you go and make managing your life easier than ever!</h3>

        <img src="./assets/images/mobile-screen.png" alt="phone showing app logo" srcset="" class="phone">

        <div class="button-container">

            <a href="https://play.google.com/store/apps/details?id=com.google.android.apps.chromecast.app"
               target="_blank" rel="noopener noreferrer" class="btn-link">
                <i class="fab fa-google-play"></i>
                <p class="link-tag">Play Store</p>
            </a>

            <a href="https://github.com/Interactive-House-Smart-House-HKR-Grp3/Android_App_Code_Smart_House"
               target="_blank" rel="noopener noreferrer" class="btn-link github">
                <i class="fab fa-github"></i>
                <p class="link-tag">Github</p>
            </a>
        </div>
    </section>

    <div class="section-padding"></div>
</main>

<!-- A section with all the colors for testing purposes -->
<div class="colors">
    <div class="color accent"></div>
    <div class="color green"></div>
    <div class="color blue-green"></div>
    <div class="color blue-light"></div>
    <div class="color blue-dark"></div>
</div>
</body>
<footer>
    <div class="class">
        <h2 class="class-title">DA326A HT20</h2>
        <p class="class-par">This website was developed as part of a course in Högskolan Kristianstad Software
            Engineering Program</p>
    </div>

    <div class="disc">
        <h2 class="disc-title">Disclaimer</h2>
        <p class="disc-par">The program represented does not represent an actual product but controls a mock physical
            house model in where all implemented controls are functional</p>
    </div>

    <div class="authors">
        <h2 class="auth-title">Authors</h2>
        <h3 class="auth oj">Öjvind Nilsson</h3>
        <p class="auth-p oj">Design / Frontend ( HTML | CSS | Javascript )</p>
        <h3 class="auth pt">Peter Tenghamn</h3>
        <p class="auth-p pt">Frontend ( Java )</p>
        <h3 class="auth ao">Alex Oachesu</h3>
        <p class="auth-p ao">Backend ( Java | MQTT )</p>
    </div>

    <div class="link">
        <h2 class="link-title">Links</h2>
        <ul class="links">
            <li>
                <a href="https://www.hkr.se/" target="_blank" rel="noopener noreferrer">
                    <img src="../assets/images/logo_hkr.png" width="100">
                </a>
            </li>
            <li>
                <a href="https://github.com/Interactive-House-Smart-House-HKR-Grp3/Web_Interface_Code_Smart_House"
                   target="_blank" rel="noopener noreferrer">
                    <img src="../assets/images/logo_github.png" width="100">
                </a>
            </li>
        </ul>
    </div>
</footer>

</html>