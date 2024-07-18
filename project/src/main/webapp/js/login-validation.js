function validateForm() {
    // Get the values from the input fields
    var username = document.getElementById('username').value.trim();
    var password = document.getElementById('password').value.trim();

    // Validate username
    if (username === '') {
        displayErrorMessage('Vui lòng nhập tên đăng nhập hoặc email.');
        return false;
    }
    // Validate password
    if (password === '') {
        displayErrorMessage('Vui lòng nhập mật khẩu.');
        return false;
    }

    var vietnameseCharsRegex = /[^\x00-\x7F]/g;
    if (vietnameseCharsRegex.test(username)) {
        displayErrorMessage('Định dạng tài khoản không hợp lệ.');
        return false;
    }

    if (username.indexOf(' ') !== -1) {
        displayErrorMessage('Định dạng tài khoản không hợp lệ.');
        return false;
    }

    if (password.indexOf(' ') !== -1) {
        displayErrorMessage('Định dạng mật khẩu không hợp lệ.');
        return false;
    }
    // If both username and password are provided, return true to submit the form
    return true;
}

function displayErrorMessage(message) {
    // Display error message in the login-status div
    var loginStatusDiv = document.getElementById('login-status');
    loginStatusDiv.innerHTML = '<div class="text-danger fw-blod">' + message + '</div>';
}
