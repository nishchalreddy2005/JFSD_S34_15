// Function to open the modal and display event details
let currentEventCard;

function openModal(title, date) {
    // Show modal
    document.getElementById("modal-title").textContent = title;
    document.getElementById("modal-date").textContent = date;
    document.getElementById("modal").style.display = "flex";

    // Store current event card
    currentEventCard = event.target.closest('.event-card');
}

// Function to close the modal
function closeModal() {
    document.getElementById("modal").style.display = "none";
}

// Function to show the success popup
function showSuccessPopup() {
    const successPopup = document.getElementById("successPopup");
    successPopup.classList.add("show-success");

    // Hide the success popup after 3 seconds
    setTimeout(() => {
        successPopup.classList.remove("show-success");
    }, 3000);
}

// Add event listener to the "Confirm Booking" button
document.querySelector(".confirm-button").addEventListener("click", function() {
    // Mark event as booked
    currentEventCard.classList.add("booked");
    currentEventCard.querySelector(".book-button").textContent = "Already Booked";

    // Close the modal
    closeModal();

    // Show success popup
    showSuccessPopup();
});