function validateFilterForm() {

    var nameProduct = document.getElementById('input_name_product').value.trim();
    var priceProduct = document.getElementById('input_price_product').value.trim();
    var specificProduct = document.getElementById('input_specific_product').value.trim();
    var weightProduct = document.getElementById('input_weight_product').value.trim();
    var brandProduct = document.getElementById('input_brand_product').value.trim();
    var remainsProduct = document.getElementById('input_remains_product').value.trim();
    var ageProduct = document.getElementById('input_age_product').value.trim();
    var descProduct = document.getElementById('txtArea_desc_product').value.trim();
    var thumbnailProduct = document.getElementById('images_thumbnail_product').querySelectorAll('img');
    var galleryProduct = document.getElementById('images_gallery_product').querySelectorAll('img');

    if (nameProduct === '' ||priceProduct=== ''||specificProduct=== ''||weightProduct=== ''||brandProduct=== ''||remainsProduct=== ''||ageProduct=== ''||descProduct=== '') {
        displayErrorMessage('Vui lòng điền đầy đủ thông tin.');
        return false;
    }
    if (thumbnailProduct.length === 0 || galleryProduct.length === 0) {
        displayErrorMessage('Vui lòng chọn ít nhất một hình ảnh cho hình ảnh đại diện và thư viện.');
        return false;
    }
    if (     parseFloat(priceProduct) <= 0) {
        displayErrorMessage('Giá sản phẩm không hợp lệ.');
        return false;
    }
    if (weightProduct !== '' && (isNaN(weightProduct) || parseFloat(weightProduct) <= 0)) {
        displayErrorMessage('Trọng lượng sản phẩm không hợp lệ.');
        return false;
    }
    if (remainsProduct !== '' && (isNaN(remainsProduct) || parseInt(remainsProduct) < 0)) {
        displayErrorMessage('Số lượng tồn kho không hợp lệ.');
        return false;
    }
    if (ageProduct !== '' && (isNaN(ageProduct) || parseInt(ageProduct) <= 0)) {
        displayErrorMessage('Độ tuổi nhỏ nhất không hợp lệ.');
        return false;
    }
}
    function displayErrorMessage(message) {
        var errorMessage = document.getElementById('product-validation-error');
        errorMessage.innerHTML = '<div class="text-danger fw-blod">' + message + '</div>';

}