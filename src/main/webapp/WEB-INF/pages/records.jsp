<%@ include file="common/header.jsp" %>

    <section id="health-records-content">
        <h2>Your Health Records</h2>
        <div id="message" class="alert"></div>

        <div id="myHealthRecords">
            <p>Loading your health records...</p>
        </div>
    </section>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const recordsDiv = document.getElementById('myHealthRecords');
            const messageDiv = document.getElementById('message');

            function loadHealthRecords() {
                fetch('/api/records/my-records')
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        if (response.status === 401) {
                            return { error: "Please log in to view your health records." };
                        }
                        return response.text().then(text => { throw new Error(text) });
                    }
                })
                .then(records => {
                    recordsDiv.innerHTML = ''; // Clear loading message

                    if (records.error) {
                        recordsDiv.innerHTML = `<p>${records.error}</p>`;
                        return;
                    }

                    if (records.length === 0) {
                        recordsDiv.innerHTML = '<p>No health records found.</p>';
                    } else {
                        const ul = document.createElement('ul');
                        records.forEach(record => {
                            const li = document.createElement('li');
                            li.innerHTML = `
                                <strong>Date:</strong> ${record.recordDate}<br>
                                <strong>Visit Type:</strong> ${record.visitType}<br>
                                <strong>Doctor:</strong> ${record.doctorName}<br>
                                <strong>Diagnosis:</strong> ${record.diagnosis || 'N/A'}<br>
                                <strong>Medications:</strong> ${record.medications || 'N/A'}<br>
                                <strong>Notes:</strong> ${record.notes || 'N/A'}
                                <hr>
                            `;
                            ul.appendChild(li);
                        });
                        recordsDiv.appendChild(ul);
                    }
                })
                .catch(error => {
                    recordsDiv.innerHTML = `<p style="color: red;">Error loading health records: ${error.message}</p>`;
                    console.error('Error fetching health records:', error);
                });
            }

            loadHealthRecords(); // Load records when the page loads
        });
    </script>

<%@ include file="common/footer.jsp" %>