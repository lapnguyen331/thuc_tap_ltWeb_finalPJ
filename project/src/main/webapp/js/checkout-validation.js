function validateCheckoutForm() {
    // Kiểm tra các điều kiện validation ở đây
    var fullName = document.getElementById('txt-full-name').value.trim();
    var email = document.getElementById('txt-email').value.trim();
    var phone = document.getElementById('txt-phone').value.trim();
    var address = document.getElementById('txt-address').value.trim();


    if (fullName === '' || email === '' || phone === '' || address === '') {
        displayErrorMessage('Vui lòng điền đầy đủ thông tin.');
        return false;
    }

    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        displayErrorMessage('Định dạng email không hợp lệ.');
        return false;
    }

    var phoneRegex = /^\d{10}$/;
    if (!phoneRegex.test(phone)) {
        displayErrorMessage('Số điện thoại phải có đúng 10 chữ số và không chứa ký tự.');
        return false;
    }

    return true;
}
function displayErrorMessage(message) {
    // Display error message in the login-status div
    var loginStatusDiv = document.getElementById('checkout-status');
    loginStatusDiv.innerHTML = '<div class="text-danger fw-blod">' + message + '</div>';
}