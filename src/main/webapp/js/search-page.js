'use strict';
$(function() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const searchValue = urlParams.get('search');
    $('.search-value').append(searchValue)
    $('#search-products-section .current span').append(searchValue);
    const loading = async () => {
        const loading = `
            <div class="row">
                <div class="loading-wrap d-flex justify-content-center align-items-center">
                    <img width="100px" src="/template/image/loading-gif.gif" alt="">
                </div>
            </div>
        `
        $('.load-more').parent().before(loading);
        const products = new Product('/template/products-card.txt');
        await products.load();
        console.log(products.data)
        products.data.forEach(product => load_card(product));
        $('.loading-wrap').remove();
    }
    const percent = (quantity, percent) => (quantity * percent) / 100;
    const load_card = function(product) {
        const html = `
        <div class="col-md-4">
            <div class="product-card">
                <a href="/template/details_product.html">
                    <div class="product-img">
                        <img src="${product.image}" alt="">
                    </div>
                    <div class="product-name">
                        <p>${product.name}</p>
                    </div>
                </a>
                <div class="product-price-wrapper">
                    <div class="discount-label">-${product.discount}</div>
                    <div class="price">
                        <p class="m-price">${product.price.replace(/\B(?=(\d{3})+(?!\d))/g, ".")}</p>
                        <p class="c-price">${percent(product.price, 100 - Number(product.discount)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")}</p>
                    </div>
                </div>
                <div class="product-button">
                    <div class="bg-gold bg-sharp-5">
                        <button class="btn-store-cart">Thêm vào giỏ</button>
                    </div>
                    <div class="bg-gold bg-sharp-5">
                        <button class="btn-details">Xem chi tiết</button>
                    </div>
                </div>
            </div>
        </div>
        `;
        $('.loading-wrap').addClass('d-none').before(html);
    }
    $('.searched-product').on('click', '.load-more', loading)
    loading();
})