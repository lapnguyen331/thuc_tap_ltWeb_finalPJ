'use strict';
$(document).ready(function() {
    let isConnected = false;
    let cart = $('#my-cart .cart-products');
    let toast = $('#message-toasts');
    let cartData;
    const loadData = function() {
        $.ajax({
            url: `${window.context}/cartAction`,
            method: 'POST',
            data: {
                action: 'get'
            },
            success: (data) => {
                cartData = data;
                refreshCart();
            }
        })
    }
    let websocket = null;
    $.ajax(`${window.context}/api/v1/web-socket`)
        .then((resp) => {
            websocket = new WebSocket(resp + "/cart");
            websocket.onopen = function (message) {
                processOpen(message);
            };
            websocket.onmessage = function (message) {
                processMessage(message);
            };
            websocket.onclose = function (message) {
                processClose(message);
            };
            websocket.onerror = function (message) {
                processError(message);
            };

            function processOpen(message) {
                isConnected = true;
            }
            function processMessage(message) {
                console.log(message)
                const obj = JSON.parse(message.data);
                console.log('obj', obj)
                switch (obj.action) {
                    case "sync-change": {
                        // console.log('cart', cartData)
                        // cartData = obj.data[0]
                        loadData();
                        refreshCart();
                        announce("Thông báo", "Thay đổi giỏ hàng thành công!")
                        break;
                    }
                }
            }

            function processClose(message) {
            }

            function processError(message) {
            }
        })
    function sendMessage(myMessage) {
        if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
            websocket.send(myMessage);
        }
    }
    $(document).on('click', '*[data-cart-product=true]', function(e) {
        const id = this.dataset.cartId;
        const quantity = this.dataset.cartAmount;
        switch (this.dataset.cartAction) {
            case "add":
                $.ajax({
                    url: `${window.context}/cartAction`,
                    method: 'POST',
                    data: {
                        action: 'add',
                        productId: id,
                        quantity: quantity,
                    },
                    success: ({status, msg}) => {
                        if (isConnected) {
                            sendMessage(JSON.stringify({
                                action: 'sync-change'
                            }))
                        }
                        else {
                            loadData();
                        }
                    }
                })
                break;
            case "remove":
                $.ajax({
                    url: `${window.context}/cartAction`,
                    method: 'POST',
                    data: {
                        action: 'remove',
                        productId: id,
                        quantity: quantity,
                    },
                    success: ({status, msg}) => {
                        if (isConnected) {
                            sendMessage(JSON.stringify({
                                action: 'sync-change'
                            }))
                        }
                        else {
                            loadData();
                        }
                    }
                })
                break;
        }
    })
    const announce = function(title, msg) {
        toast.find('.toast-body').text(msg);
        toast.find('.toast-header strong').text(title);
        mdb.Toast.getInstance(toast).show();
    }
    const refreshCart = function () {
        cart.empty();
        if (cartData.length == 0) {
            const html = `
                <div class="text-center text-danger">Giỏ hàng hiện đang trống...</div>
            `
            cart.append(html);
            return;
        }
        cartData.forEach(element => {
            const product = element.item.product;
            console.log(product)
            const html = `
          <div class="product-wrap d-flex align-items-center gap-2">
            <div class="btn-product-remove" data-cart-product="true" data-cart-action="remove" data-cart-id="${product.id}" data-cart-amount="${element.item.quantity}">
                <i class="fa-regular fa-trash-can"></i>
            </div>
            <div class="img-wrap border rounded d-flex align-items-center justify-content-center">
                <img src="${product.thumbnail.path}" width="100%">
            </div>
            <div class="d-flex flex-column justify-content-center">
                <div class="product-name">
                    ${product.name}
                </div>
                <div class="text-primray-red-color-main fw-bold">
                    ${Number.parseInt(product.price * 1000)}
                </div>
            </div>
            <div class="amount-btns">
                <div class="bg-primary-red-main-color text-wheat-color d-flex align-items-center arrow-wrap">
                    <div class="arrow arrow-down" data-cart-product="true" data-cart-action="remove" data-cart-id="${product.id}" data-cart-amount="1"></div>
                </div>
                <input type="text" class="text-center" disabled value="${element.item.quantity}">
                <div class="bg-primary-red-main-color text-wheat-color d-flex align-items-center arrow-wrap">
                    <div class="arrow arrow-up" data-cart-product="true" data-cart-action="add" data-cart-id="${product.id}" data-cart-amount="1"></div>
                </div>
            </div>
        </div>
        `
            cart.append(html);
        })
    }
    loadData();
})