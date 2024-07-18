function validateSignupForm() {
    // Get the values from the input fields
    var username = document.getElementById('username').value.trim();
    var password = document.getElementById('password').value.trim();
    var firstName = document.getElementById('first-name').value.trim();
    var lastName = document.getElementById('last-name').value.trim();
    var dob = document.getElementById('dob').value.trim();
    var gender = document.getElementsByName('gender')[0].value.trim();
    var phoneNumber = document.getElementById('phone-number').value.trim();
    var email = document.getElementsByName('email')[0].value.trim();
    var password2 = document.getElementById('password2').value.trim();


    if (username === '' || firstName === '' || lastName === '' || dob === '' || phoneNumber === '' || email === '' || password === '' || password2 === '') {
        displayErrorMessage('Vui lòng đầy đủ thông tin.');
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

    var phoneRegex = /^\d{10}$/;
    if (!phoneRegex.test(phoneNumber)) {
        displayErrorMessage('Số điện thoại phải có đúng 10 chữ số và không chứa ký tự.');
        return false;
    }
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        displayErrorMessage('Định dạng email không hợp lệ.');
        return false;
    }

    if (password.indexOf(' ') !== -1) {
        displayErrorMessage('Định dạng mật khẩu không hợp lệ.');
        return false;
    }
    if (password !== password2) {
        displayErrorMessage('Mật khẩu và xác nhận mật khẩu không khớp.', 'password2-error');
        return false;
    }
    var agreeCheckbox = document.querySelector('input[type="checkbox"]');
    if (!agreeCheckbox.checked) {
        displayErrorMessage('Bạn phải đồng ý với các điều khoản sử dụng.');
        return false;
    }

    // If both username and password are provided, return true to submit the form
    return true;
}

function displayErrorMessage(message) {
    // Display error message in the login-status div
    var loginStatusDiv = document.getElementById('signup-status');
    loginStatusDiv.innerHTML = '<div class="text-danger fw-blod">' + message + '</div>';
}
