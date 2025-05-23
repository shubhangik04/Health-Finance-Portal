<%@ include file="common/header.jsp" %>

    <section id="register-form">
        <h2>Register for Smart Health Portal</h2>
        <div id="message" class="alert"></div>
        <form id="registrationForm">
            <div class="form-group">
                <label for="regUsername">Username:</label>
                <input type="text" id="regUsername" name="username" required>
            </div>
            <div class="form-group">
                <label for="regPassword">Password:</label>
                <input type="password" id="regPassword" name="password" required>
            </div>
            <div class="form-group">
                <label for="regEmail">Email:</label>
                <input type="email" id="regEmail" name="email" required>
            </div>
            <div class="form-group">
                <label for="regFirstName">First Name:</label>
                <input type="text" id="regFirstName" name="firstName" required>
            </div>
            <div class="form-group">
                <label for="regLastName">Last Name:</label>
                <input type="text" id="regLastName" name="lastName" required>
            </div>
            <button type="submit" class="btn">Register</button>
        </form>
        <p>Already have an account? <a href="/login">Login here</a></p>
    </section>

    <script>
        document.getElementById('registrationForm').addEventListener('submit', function(event) {
            event.preventDefault(); // Prevent default form submission

            const username = document.getElementById('regUsername').value;
            const password = document.getElementById('regPassword').value;
            const email = document.getElementById('regEmail').value;
            const firstName = document.getElementById('regFirstName').value;
            const lastName = document.getElementById('regLastName').value;
            const messageDiv = document.getElementById('message');

            fetch('/api/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, password, email, firstName, lastName })
            })
            .then(response => {
                if (response.ok) {
                    messageDiv.className = 'alert alert-success';
                    messageDiv.textContent = 'Registration successful! Redirecting to login...';
                    setTimeout(() => {
                        window.location.href = '/login?registrationSuccess=true';
                    }, 2000);
                } else {
                    return response.text().then(text => { throw new Error(text) });
                }
            })
            .catch(error => {
                messageDiv.className = 'alert alert-danger';
                messageDiv.textContent = `Registration failed: ${error.message}`;
                console.error('Registration error:', error);
            });
        });
    </script>

<%@ include file="common/footer.jsp" %>