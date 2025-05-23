<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SmartHealth Portal</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <header>
        <nav>
            <div class="logo">
                <a href="/">
                    <i class="fas fa-heartbeat"></i> SmartHealth
                </a>
            </div>
            <ul class="nav-links">
                <li><a href="/">Home</a></li>
                <li><a href="/about">About Us</a></li>
                <li><a href="/contact">Contact</a></li>
                <li class="dropdown">
                    <a href="#" class="dropbtn">Services <i class="fas fa-chevron-down dropdown-arrow"></i></a>
                    <div class="dropdown-content">
                        <a href="/telemedicine"><i class="fas fa-laptop-medical"></i> Telemedicine</a>
                        <a href="/records"><i class="fas fa-notes-medical"></i> Health Records</a>
                        <a href="/financial"><i class="fas fa-hand-holding-usd"></i> Financial Aid</a>
                        <a href="/crowdfunding"><i class="fas fa-users"></i> Crowdfunding</a>
                        <a href="/facilities"><i class="fas fa-hospital"></i> Medical Facilities</a>
                        <a href="/tips"><i class="fas fa-lightbulb"></i> Health Tips</a>
                    </div>
                </li>
            </ul>
            <div class="auth-buttons">
                <a href="/login" class="btn btn-secondary">Login</a>
                <a href="/register" class="btn btn-primary">Register</a>
            </div>
        </nav>
    </header>
    <main>
        </main>
    <footer>
        </footer>
</body>
</html>