<%@ include file="common/header.jsp" %>

    <section id="telemedicine-content">
        <h2>Schedule a Telemedicine Appointment</h2>
        <div id="message" class="alert"></div>

        <form id="appointmentForm">
            <div class="form-group">
                <label for="specialty">Specialty:</label>
                <select id="specialty" name="specialty" required>
                    <option value="">Select Specialty</option>
                    <option value="General Physician">General Physician</option>
                    <option value="Pediatrician">Pediatrician</option>
                    <option value="Dermatologist">Dermatologist</option>
                    <option value="Cardiologist">Cardiologist</option>
                    <option value="Neurologist">Neurologist</option>
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
            <button type="submit" class="btn">Schedule Appointment</button>
        </form>

        <hr>

        <h3>Your Scheduled Appointments</h3>
        <div id="myAppointments">
            <p>Loading your appointments...</p>
        </div>
    </section>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const messageDiv = document.getElementById('message');

            // Handle appointment scheduling
            document.getElementById('appointmentForm').addEventListener('submit', function(event) {
                event.preventDefault();

                const specialty = document.getElementById('specialty').value;
                const appointmentDate = document.getElementById('appointmentDate').value;
                const appointmentTime = document.getElementById('appointmentTime').value;

                fetch('/api/telemedicine/schedule', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ specialty, appointmentDate, appointmentTime })
                })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        return response.text().then(text => { throw new Error(text) });
                    }
                })
                .then(data => {
                    messageDiv.className = 'alert alert-success';
                    messageDiv.textContent = 'Appointment scheduled successfully!';
                    loadAppointments(); // Reload appointments after scheduling
                    document.getElementById('appointmentForm').reset(); // Clear form
                })
                .catch(error => {
                    messageDiv.className = 'alert alert-danger';
                    messageDiv.textContent = `Error scheduling appointment: ${error.message}`;
                    console.error('Error scheduling appointment:', error);
                });
            });

            // Function to load user appointments
            function loadAppointments() {
                fetch('/api/telemedicine/my-appointments')
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        // If not authenticated or no appointments, handle gracefully
                        if (response.status === 401) {
                            return { error: "Please log in to view your appointments." };
                        }
                        return response.text().then(text => { throw new Error(text) });
                    }
                })
                .then(appointments => {
                    const appointmentsDiv = document.getElementById('myAppointments');
                    appointmentsDiv.innerHTML = ''; // Clear loading message

                    if (appointments.error) {
                        appointmentsDiv.innerHTML = `<p>${appointments.error}</p>`;
                        return;
                    }

                    if (appointments.length === 0) {
                        appointmentsDiv.innerHTML = '<p>You have no scheduled appointments.</p>';
                    } else {
                        const ul = document.createElement('ul');
                        appointments.forEach(app => {
                            const li = document.createElement('li');
                            li.innerHTML = `
                                <strong>Specialty:</strong> ${app.specialty},
                                <strong>Date:</strong> ${app.appointmentDate},
                                <strong>Time:</strong> ${app.appointmentTime},
                                <strong>Status:</strong> ${app.status}
                            `;
                            ul.appendChild(li);
                        });
                        appointmentsDiv.appendChild(ul);
                    }
                })
                .catch(error => {
                    document.getElementById('myAppointments').innerHTML = `<p style="color: red;">Error loading appointments: ${error.message}</p>`;
                    console.error('Error fetching appointments:', error);
                });
            }

            loadAppointments(); // Load appointments when the page loads
        });
    </script>

<%@ include file="common/footer.jsp" %>