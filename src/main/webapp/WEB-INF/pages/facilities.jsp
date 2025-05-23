<%@ include file="common/header.jsp" %>

    <section id="facilities-content">
        <h2>Medical Facilities Near You</h2>

        <div class="search-bar">
            <input type="text" id="facilitySearchInput" placeholder="Search by name, type, or address...">
            <button id="searchFacilitiesBtn" class="btn btn-primary">Search</button>
        </div>

        <div id="facilitiesList">
            <p>Loading medical facilities...</p>
        </div>
    </section>

    <div id="facilityDetailsModal" class="modal">
        <div class="modal-content">
            <span class="close-button">&times;</span>
            <h3 id="modalFacilityName"></h3>
            <p><strong>Type:</strong> <span id="modalFacilityType"></span></p>
            <p><strong>Address:</strong> <span id="modalFacilityAddress"></span></p>
            <p><strong>Phone:</strong> <span id="modalFacilityPhone"></span></p>
            <p><strong>Hours:</strong> <span id="modalFacilityHours">N/A</span></p> <p><strong>Services:</strong> <span id="modalFacilityServices">N/A</span></p> <a id="getDirectionsBtn" class="btn btn-primary" target="_blank" style="margin-top: 20px;">Get Directions</a>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const facilitiesListDiv = document.getElementById('facilitiesList');
            const facilitySearchInput = document.getElementById('facilitySearchInput');
            const searchFacilitiesBtn = document.getElementById('searchFacilitiesBtn');

            const modal = document.getElementById('facilityDetailsModal');
            const closeButton = document.querySelector('.close-button');
            const modalFacilityName = document.getElementById('modalFacilityName');
            const modalFacilityType = document.getElementById('modalFacilityType');
            const modalFacilityAddress = document.getElementById('modalFacilityAddress');
            const modalFacilityPhone = document.getElementById('modalFacilityPhone');
            const modalFacilityHours = document.getElementById('modalFacilityHours');
            const modalFacilityServices = document.getElementById('modalFacilityServices');
            const getDirectionsBtn = document.getElementById('getDirectionsBtn');

            let allFacilities = []; // Store all fetched facilities for filtering

            // Function to display facilities
            function displayFacilities(facilitiesToDisplay) {
                facilitiesListDiv.innerHTML = ''; // Clear current list

                if (facilitiesToDisplay.length === 0) {
                    facilitiesListDiv.innerHTML = '<p>No medical facilities found matching your search.</p>';
                } else {
                    facilitiesToDisplay.forEach(facility => {
                        const facilityCard = document.createElement('div');
                        facilityCard.classList.add('facility-card');
                        // Add a data attribute to store facility ID or full object for easy access
                        facilityCard.dataset.facility = JSON.stringify(facility); // Store full facility object as string
                        facilityCard.innerHTML = `
                            <h3>${facility.name}</h3>
                            <p><strong>Type:</strong> ${facility.type || 'N/A'}</p>
                            <p><strong>Address:</strong> ${facility.address || 'N/A'}</p>
                            <p><strong>Phone:</strong> ${facility.phoneNumber || 'N/A'}</p>
                            <button class="btn btn-secondary view-details-btn">View Details</button>
                        `;
                        facilitiesListDiv.appendChild(facilityCard);
                    });
                }
            }

            // Function to load all facilities initially
            function loadAllFacilities() {
                facilitiesListDiv.innerHTML = '<p>Loading medical facilities...</p>'; // Show loading message
                fetch('/api/facilities/all')
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        return response.text().then(text => { throw new Error(text) });
                    }
                })
                .then(facilities => {
                    allFacilities = facilities; // Store all facilities
                    displayFacilities(allFacilities); // Display all initially
                })
                .catch(error => {
                    facilitiesListDiv.innerHTML = `<p style="color: red;">Error loading facilities: ${error.message}</p>`;
                    console.error('Error fetching facilities:', error);
                });
            }

            // Function to filter facilities based on search input
            function filterFacilities() {
                const searchTerm = facilitySearchInput.value.toLowerCase().trim();
                const filtered = allFacilities.filter(facility =>
                    facility.name.toLowerCase().includes(searchTerm) ||
                    (facility.type && facility.type.toLowerCase().includes(searchTerm)) ||
                    (facility.address && facility.address.toLowerCase().includes(searchTerm))
                );
                displayFacilities(filtered);
            }

            // Event Listeners
            searchFacilitiesBtn.addEventListener('click', filterFacilities);
            facilitySearchInput.addEventListener('keyup', function(event) {
                // Optional: Live search as user types, or only on Enter key
                if (event.key === 'Enter') {
                    filterFacilities();
                } else if (facilitySearchInput.value.trim() === '' && allFacilities.length > 0) {
                    // If search box is cleared, show all facilities again
                    displayFacilities(allFacilities);
                }
            });


            // Event delegation for "View Details" buttons
            facilitiesListDiv.addEventListener('click', function(event) {
                if (event.target.classList.contains('view-details-btn')) {
                    const facilityCard = event.target.closest('.facility-card');
                    const facility = JSON.parse(facilityCard.dataset.facility);

                    modalFacilityName.textContent = facility.name || 'N/A';
                    modalFacilityType.textContent = facility.type || 'N/A';
                    modalFacilityAddress.textContent = facility.address || 'N/A';
                    // Handle 'false' or null for phone number
                    modalFacilityPhone.textContent = (facility.phoneNumber && facility.phoneNumber !== 'false') ? facility.phoneNumber : 'N/A';
                    modalFacilityHours.textContent = facility.hours || 'N/A'; // Populate if your backend provides 'hours'
                    modalFacilityServices.textContent = facility.services || 'N/A'; // Populate if your backend provides 'services'

                    // Generate Google Maps URL for directions
                    if (facility.address) {
                        // Encode the address for the URL
                        const encodedAddress = encodeURIComponent(facility.address);
                        // Optional: Get user's current location for origin (requires Geolocation API)
                        // For simplicity, we'll just open to the destination for now.
                        // If you want to add user location, you'd use navigator.geolocation.getCurrentPosition()
                        getDirectionsBtn.href = `https://www.google.com/maps/dir/?api=1&destination=${encodedAddress}`;
                        getDirectionsBtn.style.display = 'inline-block'; // Show the button
                    } else {
                        getDirectionsBtn.style.display = 'none'; // Hide if no address
                    }

                    modal.style.display = 'block'; // Show the modal
                }
            });

            // Close modal when close button is clicked
            closeButton.addEventListener('click', function() {
                modal.style.display = 'none';
            });

            // Close modal when clicking outside of it
            window.addEventListener('click', function(event) {
                if (event.target === modal) {
                    modal.style.display = 'none';
                }
            });

            // Initial load of all facilities
            loadAllFacilities();
        });
    </script>

<%@ include file="common/footer.jsp" %>