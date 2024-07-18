$(document).ready(function() {
    let customer = undefined;
    let order_map = new Map();
    let product_map = new Map();
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
        console.log(mapToJSON());
    };
    const loadAllProducts = async function () {
        const json = await $.ajax({
            url: `${window.context}/api/product/getAll`,
            method: 'get',
            dataType: 'json'
        })
        json.data.forEach(p => product_map.set(p.id, p))
    };
    (async function() {
        await loadAllProducts();

        renderDataTable();
        renderOrderItems();
    })();
    const renderDataTable = function () {
        dataTable = new DataTable('#products_filter_table', {
            dom: 'ti',
            paging: false,
            scrollCollapse: true,
            scrollY: '400px',
            width: '100%',
            data: [...product_map.values()],
            columnDefs: [
                {
                    width: 1,
                    className: 'dt-nowrap',
                    target: 0,
                    className: 'select-checkbox',
                    orderable: false,
                    data: null,
                    render: function(data, type, row) {
                        return `<input class="form-check-input product-check" data-product-id="${row.id}" type="checkbox" ${order_map.has(row.id) ? 'checked' : ''}/>`
                    }
                },
                {
                    target: 0,
                    className: 'dt-center'
                },
                {
                    target: 1,
                    data: null,
                    render: function(data, type, product) {
                        const html = `
                <div class="product-wrap">
                    <div class="img-wrap">
                        <img src="${window.context}/files/${data.image}" width="100%" alt="">
                    </div>
                    <div class="info-wrap">
                        <div class="product-title">${data.name}</div>
                        <div class="product-price">Giá bán: ${formatCurrency(getDiscountPrice(product))}đ</div>
                        <div class="id-wrap">
                            <div class="product-id">Mã SP: ${data.id}</div>
                        </div>
                    </div>
                </div>
                `;
                        if (type === 'display') return html;
                        else
                            return data.name;
                    }
                },
                {
                    data: 'quantity',
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
        console.log(order_map.size);
        if (order_map.size == 0) {
            $('.products-range').html(`
            <div class="row position-absolute top-50 start-50 translate-middle">
                <div class="fw-semibold text-success text-center col-md-12">Đơn hàng này đang trống...</div>
            </div>
        `);
        } else {
            $('.products-range').html(``);
        }
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
            $('.products-range').append(html);
        }
        $('#total-price').text(formatCurrency(price))
        $('#order-items').val(JSON.stringify(mapToJSON()))
    };
    const renderInfoCustomer = function() {
        $('#username').val(customer[0].username)
        $('#user-avatar').attr('src', `${window.context}/files/${customer[0].avatar}`)
        $('#user-fullname').text(customer[0].name)
        $('#user-email').text(customer[0].email);
        $('#user-phone').text(customer[0].phone);
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
    const basicAutocomplete = document.querySelector('#search-autocomplete');
    const asyncFilter = async (query) => {
        const url = `${window.context}/api/customer/getAll`;
        let response = await fetch(url);
        response  = await response.json();
        const data = response.data;
        return data;
    };
    $('#products_filter_table').on('change', '.product-check', function(e) {
        console.log($(this))
        const productId = $(this).data('product-id');
        if (this.checked) {
            order_map.set(productId, 1)
            renderOrderItems();
        } else {
            order_map.delete(productId)
            renderOrderItems();
        }
    });
    const doUp = function(id) {
        let quantity = order_map.get(id);
        order_map.set(id, ++quantity);
        renderOrderItems();
    }
    const doDown = function(id) {
        let quantity = order_map.get(id);
        if (quantity - 1 <= 0) {
            order_map.delete(id)
            dataTable.rows().invalidate().draw()
        } else {
            order_map.set(id, --quantity);
        }
        renderOrderItems();
    }
    const mapToJSON = function() {
        return [...order_map.entries()].map(([key, value]) => {
            return {productId: key, quantity: value}
        });
    }
    $('.products-range').on('click', '.remove-btn', function(e) {
        const id = $(this).closest('.product-card').data('product-id');
        order_map.delete(id)
        dataTable.rows().invalidate().draw()
        renderOrderItems();
    })
    $('.products-range').on('click', '.action-btn', function(e) {
        const action = $(this).data('btn-action')
        const id = $(this).closest('.product-card').data('product-id');
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
    $('#btn-load-data').click(async function (e) {
        await loadCustomerData();
        renderInfoCustomer();
    })
    new mdb.Autocomplete(basicAutocomplete, {
        filter: asyncFilter,
        displayValue: (value) =>  {
            $('#userid').val(value.id)
            return value.username
        }
    });
})