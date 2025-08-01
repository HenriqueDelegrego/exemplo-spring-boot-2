
function loadHeader() {
    fetch('../../components/header/header.html')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            document.getElementById('header').innerHTML = data;
        })
        .catch(error => {
            console.error('Error loading header:', error);
            document.getElementById('header').innerHTML = '<p>Error loading header.</p>';
        });
}

// Run the function when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', loadHeader);
