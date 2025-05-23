<%@ include file="common/header.jsp" %> 
     <section id="financial-aid-content"> 
         <h2>Financial Aid Application</h2> 
         <div id="message" class="alert"></div> 

         <form id="financialAidForm"> 

             <div class="form-group"> 
                 <label for="age">Age:</label> 
                 <input type="number" id="age" name="age" min="0" required> 
             </div>
             <div class="form-group"> 
                 <label for="income">Annual Income ($):</label> 
                 <input type="number" id="income" name="income" min="0" step="0.01" required> 
             </div> 

             <div class="form-group"> 
                 <label for="familySize">Family Size:</label> 
                 <input type="number" id="familySize" name="familySize" min="1" required> 
             </div> 

             <div class="form-group"> 
                 <label for="medicalCondition">Medical Condition (if any):</label> 
                 <textarea id="medicalCondition" name="medicalCondition" rows="4"></textarea> 
             </div> 
             <button type="submit" class="btn">Submit Application</button> 
         </form> 

         <hr> 

         <h3>Your Financial Aid Applications</h3> 
         <div id="myApplications"> 
             <p>Loading your applications...</p> 
         </div> 
     </section> 
     <script> 
         document.addEventListener('DOMContentLoaded', function() { 
             const messageDiv = document.getElementById('message'); 
             document.getElementById('financialAidForm').addEventListener('submit', function(event) { 
                 event.preventDefault(); 
                 const age = parseInt(document.getElementById('age').value); 
                 const income = parseFloat(document.getElementById('income').value); 
                 const familySize = parseInt(document.getElementById('familySize').value); 
                 const medicalCondition = document.getElementById('medicalCondition').value.trim(); 

                 fetch('/api/financial-aid/submit-application', { 
                     method: 'POST', 
                     headers: { 
                         'Content-Type': 'application/json' 
                     }, 
                     body: JSON.stringify({ age, income, familySize, medicalCondition }) 
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
                     messageDiv.textContent = 'Application submitted successfully! Status: ' + data.status + '. Eligible Programs: ' + (data.eligiblePrograms || 'None'); 
                     loadApplications(); // Reload applications 
                     document.getElementById('financialAidForm').reset(); 
                 }) 
                 .catch(error => { 
                     messageDiv.className = 'alert alert-danger'; 
                     messageDiv.textContent = `Error submitting application: ${error.message}`; 
                     console.error('Error submitting application:', error); 
                 }); 
             }); 

             function loadApplications() { 
                 fetch('/api/financial-aid/my-applications') 
                 .then(response => { 
                     if (response.ok) { 
                         return response.json(); 
                     } else { 
                         if (response.status === 401) { 
                             return { error: "Please log in to view your applications." }; 
                         } 
                         return response.text().then(text => { throw new Error(text) }); 
                     } 
                 }) 
                 .then(applications => { 
                     const applicationsDiv = document.getElementById('myApplications'); 
                     applicationsDiv.innerHTML = ''; 

                     if (applications.error) { 
                         applicationsDiv.innerHTML = `<p>${applications.error}</p>`; 
                         return;
                     } 

                     if (applications.length === 0) { 
                         applicationsDiv.innerHTML = '<p>You have no financial aid applications.</p>'; 
                     } else { 
                         const ul = document.createElement('ul'); 
                         applications.forEach(app => { 
                             const li = document.createElement('li'); 
                             li.innerHTML = ` 
                                 <strong>Submission Date:</strong> ${app.submissionDate}<br> 
                                 <strong>Age:</strong> ${app.age}, <strong>Income:</strong> $${app.income}, <strong>Family Size:</strong> ${app.familySize}<br> 
                                 <strong>Medical Condition:</strong> ${app.medicalCondition || 'N/A'}<br> 
                                 <strong>Status:</strong> ${app.status}<br> 
                                 <strong>Eligible Programs:</strong> ${app.eligiblePrograms || 'N/A'} 
                                 <hr> 
                             `; 
                             ul.appendChild(li); 
                         }); 
                         applicationsDiv.appendChild(ul); 
                     } 
                 })
                 .catch(error => { 
                     document.getElementById('myApplications').innerHTML = `<p style="color: red;">Error loading applications: ${error.message}</p>`; 
                     console.error('Error fetching applications:', error); 
                 }); 
             } 
             loadApplications(); 
         }); 
     </script> 

 <%@ include file="common/footer.jsp" %>