// JavaScript for handling form submission
document.getElementById('submitBtn').addEventListener('click', () => {
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;
    const source = document.getElementById('source').value;
    const destination = document.getElementById('destination').value;
    const date = document.getElementById('date').value;

    if (!name || !email || !phone || !source || !destination || !date) {
        showMessage('Please fill all fields.', 'error');
        return;
    }

    // Send data to backend (Spring Boot API)
    fetch('http://localhost:8080/book-ticket', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, email, phone, source, destination, date }),
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                showMessage('Booking request sent successfully!', 'success');
                document.getElementById('bookingForm').reset();
            } else {
                showMessage('Failed to send booking request.', 'error');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showMessage('An error occurred  . Please try again later.', 'error');
        });
});

function showMessage(message, type) {
    const messageDiv = document.getElementById('message');
    messageDiv.className = type;
    messageDiv.innerHTML = message;
    messageDiv.style.display = 'block';
}

