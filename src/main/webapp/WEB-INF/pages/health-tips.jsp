<%@ include file="common/header.jsp" %>

    <section id="health-tips-content">
        <h2>Health Tips for a Healthier You</h2>
        <div id="tips-container">
            <p>Loading health tips...</p>
        </div>
    </section>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const tipsContainer = document.getElementById('tips-container');

            fetch('/api/health-tips/all')
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    return response.text().then(text => { throw new Error(text) });
                }
            })
            .then(tips => {
                tipsContainer.innerHTML = '';

                if (tips.length === 0) {
                    tipsContainer.innerHTML = '<p>No health tips available at the moment. Check back later!</p>';
                } else {
                    tips.forEach(tip => {
                        const tipDiv = document.createElement('div');
                        tipDiv.classList.add('health-tip-item');
                        tipDiv.innerHTML = `
                            <h3>${tip.title}</h3>
                            <p><strong>Category:</strong> ${tip.category || 'General'}</p>
                            <p><strong>Published:</strong> ${tip.publicationDate}</p>
                            <p>${tip.content}</p>
                            <p class="author">By: ${tip.author || 'Smart Health Portal'}</p>
                        `;
                        tipsContainer.appendChild(tipDiv);
                    });
                }
            })
            .catch(error => {
                tipsContainer.innerHTML = `<div class="alert alert-danger">Error loading health tips: ${error.message}</div>`;
                console.error('Error fetching health tips:', error);
            });
        });
    </script>

<%@ include file="common/footer.jsp" %>