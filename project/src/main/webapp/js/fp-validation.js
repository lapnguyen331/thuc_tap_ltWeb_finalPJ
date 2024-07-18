function validateFpForm() {
    // Get the values from the input fields
    var email = document.getElementsByName('email')[0].value.trim();


    if (email === '') {
        displayErrorMessage('Vui lòng nhập email.');
        return false;
    }

    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        displayErrorMessage('Định dạng email không hợp lệ.');
        return false;
    }

    // If both username and password are provided, return true to submit the form
    return true;
}

function displayErrorMessage(message) {
    // Display error message in the login-status div
    var fpStatusDiv = document.getElementById('fp-status');
    fpStatusDiv.innerHTML = '<div class="text-danger fw-blod">' + message + '</div>';
}
