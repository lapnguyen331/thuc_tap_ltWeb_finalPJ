'use strict';

const loadData = async function() {
    let products = await fetch(`${window.contextPath}/js/fake_data/products-2.txt`);
    products = await products.json();
    products = products.data.sort(() => 0.5 - Math.random()).reduce(function(previous, current) {
        if (!previous[current.category]) previous[current.category] = [];
        if (previous[current.category].length != 3) previous[current.category].push(current)
        return previous
    }, {});
    let categories = await fetch(`${window.contextPath}/js/fake_data/categories.txt`);
    categories = await categories.json();
    categories = categories.data.reduce(function(previous, current) {
        if (!previous[current.id]) previous[current.id] = current;
        return previous;
    }, {})
    for (let key in products) {
        loadRow(products[key], categories[products[key][0].category])
    }
}

const loadRow = function(product, category) {
    console.log(product)
    let html = `
        <section class="category-product">
            <div class="row">
                <div class="col-12">
                    <div class="banner-wrap bg-gold bg-sharp p-1">
                        <div class="img-wrap">
                            <img src="${window.contextPath}/inventory/images/footer-bg.jpg" alt="">
                        </div>
                        <div class="banner-title">
                            <h3>${category.name}</h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" id='cate-${category.id}'></div>
            <div class="row">
                <div class="col-md-12 d-flex justify-content-center">
                    <a href="/template/categogy-detail.html" class="btn-view-products-more">Xem thêm ${category.name}</a>
                </div>
            </div>
        </section>
    `;
    $('#category-section').append(html);
    product.forEach(p => {
        const html = `
        <div class="col-md-4">
            <div class="product-card">
                <a href="/template/details_product.html">
                    <div class="product-img">
                        <img src="${p.images[0]}" alt="${p.name}">
                    </div>

                    <div class="product-name">
                        <p>${p.name}</p>
                    </div>
                </a>
                <div class="product-price-wrapper">
                    <div class="discount-label">-${p.discount}%</div>
                    <div class="price">
                        <p class="m-price">${p.price}</p>
                        <p class="c-price">${p.price}</p>
                    </div>
                </div>
                <div class="product-button">
                    <div class="bg-gold bg-sharp-5">
                        <button class="btn-store-cart">
                            Thêm vào giỏ
                        </button>
                    </div>
                    <div class="bg-gold bg-sharp-5">
                        <button class="btn-details">
                            <a href="/template/categogy-detail.html" class="move-to-details">Xem chi tiết</a>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        `
        $(`#cate-${category.id}`).append(html)
    })
}

loadData();