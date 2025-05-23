<%@ include file="common/header.jsp" %>

    <section id="crowdfunding-content">
        <h2>Medical Crowdfunding Projects</h2>
        <div id="message" class="alert"></div>

        <c:if test="${pageContext.request.userPrincipal != null}">
            <h3>Create a New Project</h3>
            <form id="createProjectForm">
                <div class="form-group">
                    <label for="projectName">Project Name:</label>
                    <input type="text" id="projectName" name="projectName" required>
                </div>
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" name="description" rows="5" required></textarea>
                </div>
                <div class="form-group">
                    <label for="targetAmount">Target Amount ($):</label>
                    <input type="number" id="targetAmount" name="targetAmount" min="1" step="0.01" required>
                </div>
                <button type="submit" class="btn">Create Project</button>
            </form>
            <hr>
        </c:if>

        <h3>Active Projects</h3>
        <div id="activeProjects">
            <p>Loading projects...</p>
        </div>
    </section>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const messageDiv = document.getElementById('message');

            // Handle project creation
            const createProjectForm = document.getElementById('createProjectForm');
            if (createProjectForm) { // Only available if user is logged in
                createProjectForm.addEventListener('submit', function(event) {
                    event.preventDefault();

                    const projectName = document.getElementById('projectName').value;
                    const description = document.getElementById('description').value;
                    const targetAmount = parseFloat(document.getElementById('targetAmount').value);

                    fetch('/api/crowdfunding/create-project', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ projectName, description, targetAmount })
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
                        messageDiv.textContent = 'Project created successfully!';
                        loadProjects(); // Reload projects
                        createProjectForm.reset();
                    })
                    .catch(error => {
                        messageDiv.className = 'alert alert-danger';
                        messageDiv.textContent = `Error creating project: ${error.message}`;
                        console.error('Error creating project:', error);
                    });
                });
            }

            // Function to load projects
            function loadProjects() {
                fetch('/api/crowdfunding/projects')
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        return response.text().then(text => { throw new Error(text) });
                    }
                })
                .then(projects => {
                    const projectsDiv = document.getElementById('activeProjects');
                    projectsDiv.innerHTML = '';

                    if (projects.length === 0) {
                        projectsDiv.innerHTML = '<p>No active crowdfunding projects at the moment.</p>';
                    } else {
                        projects.forEach(project => {
                            const projectCard = document.createElement('div');
                            projectCard.classList.add('project-card');
                            projectCard.innerHTML = `
                                <h3>${project.campaignName}</h3>
                                <p>${project.description}</p>
                                <p><strong>Target:</strong> $${project.targetAmount.toFixed(2)}</p>
                                <p><strong>Raised:</strong> $${project.raisedAmount.toFixed(2)}</p>
                                <p><strong>Status:</strong> ${project.status}</p>
                                <c:if test="${pageContext.request.userPrincipal != null}">
                                    <div class="contribute-section">
                                        <input type="number" class="contribution-amount" placeholder="Amount" min="1" step="0.01">
                                        <button class="btn contribute-btn" data-project-id="${project.id}">Contribute</button>
                                    </div>
                                </c:if>
                            `;
                            projectsDiv.appendChild(projectCard);
                        });

                        // Add event listeners for contribute buttons
                        document.querySelectorAll('.contribute-btn').forEach(button => {
                            button.addEventListener('click', function() {
                                const projectId = this.dataset.projectId;
                                const amountInput = this.previousElementSibling;
                                const amount = parseFloat(amountInput.value);

                                if (isNaN(amount) || amount <= 0) {
                                    alert('Please enter a valid contribution amount.');
                                    return;
                                }

                                fetch(`/api/crowdfunding/contribute/${projectId}?amount=${amount}`, {
                                    method: 'POST',
                                    headers: {
                                        'Content-Type': 'application/json'
                                    }
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
                                    messageDiv.textContent = `Successfully contributed $${amount.toFixed(2)} to ${data.campaignName}!`;
                                    loadProjects(); // Reload projects to reflect new amount
                                })
                                .catch(error => {
                                    messageDiv.className = 'alert alert-danger';
                                    messageDiv.textContent = `Error contributing: ${error.message}`;
                                    console.error('Error contributing:', error);
                                });
                            });
                        });
                    }
                })
                .catch(error => {
                    document.getElementById('activeProjects').innerHTML = `<p style="color: red;">Error loading projects: ${error.message}</p>`;
                    console.error('Error fetching crowdfunding projects:', error);
                });
            }

            loadProjects(); // Load projects when the page loads
        });
    </script>

<%@ include file="common/footer.jsp" %>