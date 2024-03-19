$('.show-all-toggle').on('click', function() {
    $(this).toggleClass('active');
    $(this).text(`${$(this).hasClass('active')?'Ẩn bớt' : 'Hiện tất cả...'}`);
});


$('.product-list-body').on('change', '.collapse-toggle', function() {
    let target = $(this).siblings().filter('.product-more-info')[0];
    if ($(this).is(':checked')) mdb.Collapse.getInstance(target).show();
    else mdb.Collapse.getInstance(target).hide();
});
$('.filter-form').on('submit', (e) => {
    e.preventDefault();
    $('.product-list-body').html(`
        <div class="w-100 d-flex align-items-center justify-content-center">
            <img src="${window.context}/inventory/images/loading-gif.gif" width="200px" alt="">
        </div>
    `);
    searchProducts();
})
const searchProducts = async function(){
    await new Promise((resolve) => setTimeout(resolve, 500));
    let formData = new FormData($('.filter-form')[0]);
    $.ajax({
        url: `${window.context}/admin/product`,
        method: 'post',
        data: {
            name: formData.get('name'),
            brand: formData.get('brand'),
            category: formData.get('category'),
            action: 'filter'
        },
        success: function(data) {
            const products = JSON.parse(data).data;
            $('.product-list-body').html('');
            products.sort(() => 0.5 - Math.random()).forEach(data => {
                const html = `
                <div class="product-grid product-info">
                    <input class="form-check-input justify-self-center collapse-toggle"
                        type="checkbox">
                    <div class="img-product">
                        <img src="${window.context}/files/${data.image}" width="100%" alt="">
                    </div>
                    <span class="id-product">${data.id}</span>
                    <span>${data.name}</span>
                    <span>${data.price}</span>
                    <span>${data.quantity}</span>
                    <div class="product-more-info collapse">
                        <div class="product-wrap">
                            <div class="img-product">
                                <img src="${window.context}/files/${data.image}" width="100%" alt="">
                            </div>
                            <div class="more-stats">
                                <div class="stat">
                                    <label>Tên sản phẩm</label>
                                    <span>${data.name}</span>
                                </div>
                                <div class="stat">
                                    <label>Giá bán</label>
                                    <span>${data.price}</span>
                                </div>
                                <div class="stat">
                                    <label>Mã sản phẩm</label>
                                    <span>${data.id}</span>
                                </div>
                                <div class="stat">
                                    <label>Quy cách</label>
                                    <span>${data.specific}</span>
                                </div>
                                <div class="stat">
                                    <label>Tồn kho</label>
                                    <span>${data.quantity}</span>
                                </div>
                                <div class="stat">
                                    <label>Thương hiệu</label>
                                    <span>Seon Gold</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="action-btns">
<!--                        <i class="fa-regular fa-circle-xmark remove-btn" data-mdb-toggle="tooltip" title="Xóa sản phẩm"></i>-->
                        <a href="${window.context}/admin/product/detail?id=${data.id}" data-mdb-toggle="tooltip" title="Xem trang sản phẩm"><i class="fa-regular fa-edit view-btn"></i></a>
                        <a href="${window.context}/product?id=${data.id}" data-mdb-toggle="tooltip" title="Xem trang sản phẩm"><i class="fa-regular fa-eye remove-btn"></i></a>
                    </div>
                </div>
                `
                $('.product-list-body').append(html);
                [...$('.product-more-info')].forEach(e => new mdb.Collapse(e, {toggle: false}))
            })
        }
    })
}
searchProducts();