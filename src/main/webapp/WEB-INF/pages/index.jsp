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
        <section id="hero">
            <div class="hero-content">
                <h2>Your Health, Our Priority.</h2>
                <p>SmartHealth provides comprehensive digital health solutions, connecting you to care, records, and support when you need it most.</p>
                <a href="/register" class="btn btn-primary hero-btn">Get Started</a>
            </div>
            <div class="hero-image">
                <img src="/images/healthy lifestyle.jpeg" alt="A person enjoying a healthy lifestyle, representing well-being.">
            </div>
        </section>

        <section id="services">
            <h2>Our Services</h2>
            <div class="service-cards">
                <div class="card">
                    <i class="fas fa-laptop-medical"></i>
                    <h3>Telemedicine</h3>
                    <p>Connect with doctors online from the comfort of your home.</p>
                </div>
                <div class="card">
                    <i class="fas fa-notes-medical"></i>
                    <h3>Health Records</h3>
                    <p>Securely access and manage your personal health information.</p>
                </div>
                <div class="card">
                    <i class="fas fa-hand-holding-usd"></i>
                    <h3>Financial Aid</h3>
                    <p>Explore options for healthcare financial assistance programs.</p>
                </div>
                <div class="card">
                    <i class="fas fa-users"></i>
                    <h3>Crowdfunding</h3>
                    <p>Raise funds for medical treatments through community support.</p>
                </div>
                 <div class="card">
                    <i class="fas fa-hospital"></i>
                    <h3>Medical Facilities</h3>
                    <p>Locate and learn about medical facilities near you.</p>
                </div>
                <div class="card">
                    <i class="fas fa-lightbulb"></i>
                    <h3>Health Tips</h3>
                    <p>Get valuable advice for maintaining a healthy lifestyle.</p>
                </div>
            </div>
        </section>

        <section id="telemedicine-content" style="display:none;"> <h2>Telemedicine Appointments</h2>
            <form action="#" method="POST">
                <div class="form-group">
                    <label for="patientName">Patient Name:</label>
                    <input type="text" id="patientName" name="patientName" required>
                </div>
                <div class="form-group">
                    <label for="doctor">Preferred Doctor:</label>
                    <select id="doctor" name="doctor">
                        <option value="">Select a Doctor</option>
                        <option value="dr-smith">Dr. Jane Smith</option>
                        <option value="dr-doe">Dr. John Doe</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="appointmentDate">Date:</label>
                    <input type="date" id="appointmentDate" name="appointmentDate" required>
                </div>
                <div class="form-group">
                    <label for="appointmentTime">Time:</label>
                    <input type="time" id="appointmentTime" name="appointmentTime" required>
                </div>
                <div class="form-group">
                    <label for="reason">Reason for Visit:</label>
                    <textarea id="reason" name="reason" rows="4"></textarea>
                </div>
                <button type="submit" class="btn btn-primary form-submit-btn">Schedule Appointment</button>
            </form>
            <div id="myAppointments" style="margin-top: 30px;">
                <h3>My Scheduled Appointments</h3>
                <ul>
                    <li>Dr. Jane Smith - 2024-05-25, 10:00 AM (Confirmed)</li>
                    <li>Dr. John Doe - 2024-06-01, 02:30 PM (Pending)</li>
                </ul>
            </div>
        </section>

        <section id="login-form" style="display:none;">
            <h2>Login to SmartHealth</h2>
            <form action="/login" method="POST">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary form-submit-btn">Login</button>
            </form>
            <p style="text-align: center; margin-top: 20px;">Don't have an account? <a href="/register" style="color: var(--secondary-color); text-decoration: none;">Register here</a></p>
        </section>

    </main>

    <footer>
        <p>&copy; 2024 SmartHealth Portal. All rights reserved.</p>
    </footer>
</body>
</html>