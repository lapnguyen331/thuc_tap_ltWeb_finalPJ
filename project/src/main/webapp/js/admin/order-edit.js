$(document).ready(function() {
    const id = $('#order-item').val();
    let customer = undefined;
    let order_map = new Map();
    let order_map_sku = new Map()
    let product_map = new Map();
    let sku_map = new Map();
    let dataTable = undefined;
    const loadCustomerData = async function() {
        const userid = $('#userid').val();
        const json = await $.ajax({
            url: `${window.context}/api/customer/getUser?id=${userid}`,
            method: 'get',
            dataType: 'json'
        })
        customer = json.data
    };
    const loadOrderItemsData = async function () {
        const json = await $.ajax({
            url: `${window.context}/api/order/getOrderItems?id=${id}`,
            method: 'get',
            dataType: 'json'
        })
        json.data.forEach(o => order_map.set(o.product.id, o.quantity))
        renderOrderItems()
        checkIfOrderDone()
    };
    const loadAllProducts = async function () {
        const json = await $.ajax({
            url: `${window.context}/api/product/getAll`,
            method: 'get',
            dataType: 'json',
        })
        json.data.forEach(p => product_map.set(p.id, p))
    };
    (async function() {
        await loadAllProducts();
        await loadOrderItemsData();
        await loadCustomerData();

        renderDataTable();
        renderInfoCustomer();
    })();
    const renderDataTable = function () {
        dataTable = new DataTable('#products_filter_table', {
            dom: 'ti',
            paging: false,
            scrollCollapse: true,
            scrollY: '400px',
            width: '100%',
            ajax: {
                url: `${window.context}/api/v1/stock-keeping/doGetAll-SKURowData`,
                method: 'post',
                data: (d) => JSON.stringify(d),
                dataSrc: (response) => {
                    [... response.data].forEach(sku => sku_map.set(sku.stockId, sku))
                    return response.data
                }
            },
            columnDefs: [
                {
                    width: 1,
                    className: 'dt-nowrap',
                    target: 0,
                    className: 'select-checkbox',
                    orderable: false,
                    data: null,
                    render: function(data, type, row) {
                        return `<input class="form-check-input product-check" data-stock-id="${row.stockId}" type="checkbox" ${order_map_sku.has(row.stockId) ? 'checked' : ''}/>`
                    }
                },
                {
                    target: 0,
                    className: 'dt-center'
                },
                {
                    target: 1,
                    data: null,
                    render: function(data, type, row) {
                        const html = `
                <div class="product-wrap">
                    <div class="img-wrap">
                        <img src="${row.product.thumbnail}" width="100%" alt="">
                    </div>
                    <div class="info-wrap">
                        <div class="product-title">${row.product.name}</div>
                        <div class="fw-normal">Ngày hết hạn <span class="text-info">${row.expiredDate}</span></div>
                        <div class="id-wrap">
                            <div class="product-id">Mã lô hàng: #<a href="#">${row.stockId}</a></div>
                        </div>
                    </div>
                </div>
                `;
                        if (type === 'display') return html;
                        else
                            return row.product.name;
                    }
                },
                {
                    data: 'availableQuantity',
                    target: 2,
                    className: 'dt-center dt-nowrap',
                    width: 1
                },
            ],
            select: {
                style: 'single',
                selector: 'td:first-child .form-check-input'
            },
            order: [[1, 'asc']],
        });
    };
    const renderOrderItems = function() {
        let price = 0;
        $('.products-range.requirement').html(``)
        for (let [id, quantity] of order_map.entries()) {
            const product = product_map.get(id);
            price += getDiscountPrice(product) * quantity;
            const html = `
                <div class="product-card" data-product-id="${product.id}">
                    <div class="img-wrap">
                        <img src="${window.context}/files/${product.image}" width='100%' alt="">
                    </div>
                    <div class="info-wrap">
                        <div class="product-title">${product.name}</div>
                        <div class="product-price">Giá bán: ${formatCurrency(getDiscountPrice(product) * quantity)}đ</div>
                        <div class="id-wrap">
                            <div class="product-id">Mã SP: ${product.id}</div>
                            <div class="number-field">
                                <button type="button" class="action-btn" data-btn-action="down">
                                    <span>-</span>
                                </button>
                                <input type="text" name="quantity" id="" value=${quantity} disabled/>
                                <button type="button" class="action-btn" data-btn-action="up">
                                    <span>+</span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="remove-btn">
                        <i class="fa-solid fa-circle-xmark"></i>
                    </div>
                </div>
                `
            $('.products-range.requirement').append(html);
        }
        $('#order-items').val(JSON.stringify(mapToJSON()))
    };

    const renderOrderItemsSKU = function() {
        console.log(sku_map)
        console.log(order_map_sku)
        $('.products-range.from-sku').html(``)
        for (let [id, quantity] of order_map_sku.entries()) {
            const sku = sku_map.get(id);
            const html = `
                <div class="product-card" data-stock-id="${sku.stockId}">
                    <div class="img-wrap">
                        <img src="${sku.product.thumbnail}" width='100%' alt="">
                    </div>
                    <div class="info-wrap">
                        <div class="product-title">${sku.product.name}</div>
                        <div class="fw-light text-info fs-6">${sku.expiredDate}</div>
                        <div class="id-wrap">
                            <div class="product-id">Mã lô: ${sku.stockId}</div>
                            <div class="number-field">
                                <button type="button" class="action-btn" data-btn-action="down">
                                    <span>-</span>
                                </button>
                                <input type="text" name="quantity" id="" value=${quantity} disabled/>
                                <button type="button" class="action-btn" data-btn-action="up">
                                    <span>+</span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="remove-btn">
                        <i class="fa-solid fa-circle-xmark"></i>
                    </div>
                </div>
                `
            $('.products-range.from-sku').append(html);
        }
        $('#order-items').val(JSON.stringify(mapToJSON()))
    };

    const renderInfoCustomer = function() {
        $('#username').val(customer[0].username)
        $('#user-avatar').attr('src', `${window.context}/files/${customer[0].avatar}`)
        $('#user-fullname').text(customer[0].name)
    };
    const getDiscountPrice = function(product) {
        return (100 - product.discount) / 100 * product.price;
    }
    const formatCurrency = function(price) {
        return String(price).replace(/(?<=\d)(?=(\d{3})+(?!\d))/g, ".");
    }
    $('#txt_date').daterangepicker({
        "singleDatePicker": true,
        locale: {
            format: 'DD/MM/YYYY hh:mm A'
        }
    }, function(start, end, label) {
        console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
    });
    $('#products_filter_table').on('change', '.product-check', function(e) {
        console.log($(this))
        const stockId = $(this).data('stock-id');
        if (this.checked) {
            order_map_sku.set(stockId, 1)
            renderOrderItemsSKU();
        } else {
            order_map_sku.delete(stockId)
            renderOrderItemsSKU();
        }
    });
    const doUp = function(id) {
        let quantity = order_map_sku.get(id);
        order_map_sku.set(id, ++quantity);
        renderOrderItemsSKU();
    }
    const doDown = function(id) {
        let quantity = order_map_sku.get(id);
        if (quantity - 1 <= 0) {
            order_map_sku.delete(id)
            dataTable.rows().invalidate().draw()
        } else {
            order_map_sku.set(id, --quantity);
        }
        renderOrderItemsSKU();
    }
    const mapToJSON = function() {
        return [...order_map_sku.entries()].map(([key, value]) => {
            return {stockId: key, quantity: value, orderId: id}
        });
    }
    $('.products-range.from-sku').on('click', '.remove-btn', function(e) {
        const id = $(this).closest('.product-card').data('stock-id');
        order_map_sku.delete(id)
        dataTable.rows().invalidate().draw()
        renderOrderItemsSKU();
    })
    $('.products-range.from-sku').on('click', '.action-btn', function(e) {
        const action = $(this).data('btn-action')
        const id = $(this).closest('.product-card').data('stock-id');
        switch (action) {
            case 'up':
                doUp(id);
                break;
            case 'down':
                doDown(id);
                break;
        }
    })
    $('#search-data').on('input', function(e) {
        dataTable.column(1).search($(this).val()).draw();
    })
    function checkIfOrderDone() {
        $.ajax({
            url: `${window.context}/api/v1/order/getUnhandled?id=${id}`,
            success: function(data) {
                data.length === 0
                    && $('.products-range.requirement').html('').css({
                        backgroundColor: 'rgba(20, 164, 77, 0.2)',
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'center'
                }).append(`<div class="fw-semibold">Đơn hàng này không cần xử lí gì thêm :)</div>`)
            }
        })
    }
    $('.update-btn').on('click', function (e){
        e.preventDefault()
        $.ajax({
            url: `${window.context}/api/v1/stock-keeping/doAddNew-Handle-OrderDetails`,
            data: $('#order-items').val(),
            method: 'post',
            success: () => {
                location.reload();
            }
        })
    })
})