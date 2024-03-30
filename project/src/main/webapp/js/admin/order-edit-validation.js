function validateFilterForm() {
    var name = document.getElementById('txt_fullname').value.trim();
    var email = document.getElementById('txt_email').value.trim();
    var phone = document.getElementById('txt_phone').value.trim();
    var address = document.getElementById('txt_address').value.trim();

    if (name === '' ||email === ''||phone === ''||address === '') {
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
}
function displayErrorMessage(message) {
    var errorMessage = document.getElementById('product-validation-error');
    errorMessage.innerHTML = '<div class="text-danger fw-blod">' + message + '</div>';

}