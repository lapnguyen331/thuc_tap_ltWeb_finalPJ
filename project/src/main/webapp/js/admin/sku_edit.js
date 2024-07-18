import { translate } from "./data-table-translate.js";
const data_tables_transaction_history = new DataTable('#table_sku_edit', {
    language: translate,
    ajax: {
        url: `${window.context}/api/v1/stock-keeping/doGetSession-stockIds`,
        method: 'POST',
    },
    dom: 'tip',
    scrollCollapse: true,
    width: '100%',
    scrollY: '200px',
    bDeferRender: true,
    columnDefs: [
        {
            target: [0, 2],
            className: 'text-wrap',
        },
        {
            target: '_all',
            className: 'border-top border-1 my-3'
        },
        {
            target: [5],
            className: 'text-center w-auto',
        }
    ],
    createdRow: function(row, data, index ) {
        console.log(row)
    },
    columns: [
        {
            data: null,
            render: function(data, type, row) {
                return `
                <div class="d-flex gap-2 py-3" style="width: 200px">
                    <div class="flex-shrink-0 d-flex justify-content-center align-items-center overflow-hidden border border-1 rounded-1" style="height: 50px; aspect-ratio: 1/1;">
                        <img src="${row.product.thumbnail}" width="100%" alt="">
                    </div>
                    <div class="d-flex flex-column">
                        <div>Mã sản phẩm: ${row.product.id}</div>
                        <div style="overflow: hidden;
                        width: 100%;
                        display: -webkit-box;
                        -webkit-line-clamp: 1;
                        -webkit-box-orient: vertical;">Tên: ${row.product.name}</div>
                    </div>
                </div>
                `
            }
        },
        {
            data: 'stockId',
            render: function(data, type, row) {
                return `
                <div class="d-flex align-items-center stock-id" data-stock-id="${data}">
                    <a href="#">#${data}</a>
                </div>
                `
            }
        },
        {
            data: 'availableQuantity',
            render: function(data, type, row) {
                return `
                    <input type="text" 
                    class="form-control form-control-sm" 
                    placeholder="Hiện có ${data} trong lô"
                    style="width: 150px" />
                `
            }

        },
        {
            data: 'producer.id',
            render: function(data, type, row) {
                return `
                    <select class="sku_edit_select_supplier">
                        <option value="${data}">${row.producer.name}</option>
                </select>
                `
            }

        },
        {
            data: 'expiredDate',
            render: function(data, type, row) {
                return `
                    <div>${data}</div>
                `
            }

        },
        {
            width: '150px',
            className: 'dt-center',
            data: 'note',
            render: function(data, type, row) {
                return `
                <div class="d-flex align-items-center gap-2" data-stock-id="${row.stockId}">
                    <select class="sku_edit_select_reason">
                            <option value="HET_HAN">Hết hạn</option>
                            <option value="SL_THAY_DOI">Thay đổi số lượng</option>
                            <option value="THEM_HANG">Thêm hàng</option>
                    </select>
                    <i class="far fa-times-circle text-danger delete-button" style="cursor:pointer;"></i>
                </div>
                `;
            }
        },
        {
            width: '150px',
            className: 'dt-center',
            data: 'note',
            render: function(data, type, row) {
                return `
                <div class="d-flex align-items-center gap-2" data-stock-id="${row.stockId}">
                    <textarea class="form-control" name="" id="" cols="30" 
                    style="resize: none; font-size: 12px" rows="3"></textarea>
                </div>
                `;
            }
        }
    ],
    preDrawCallback: function() {
        $('.sku_edit_select_reason').remove()
        $('.sku_edit_select_supplier').remove()
    },
    fnDrawCallback: function() {
        [...document.getElementsByClassName('sku_edit_select_reason')].forEach(select => {
            new mdb.Select(select, {
                container: 'body',
                size: 'sm'
            });
        });
        [...document.getElementsByClassName('sku_edit_select_supplier')].forEach(select => {
            new mdb.Select(select, {
                container: 'body',
                size: 'sm',
                disabled: true,
            });
        })
    }
});
$('#btn_updateAll').on('click', function() {
    try {
        const errors = [];
        const data = [...data_tables_transaction_history.$('tr')].map(row => {
            const inStockStr = $(row).find("input[type='text']").val()
            if (inStockStr === "" || inStockStr === undefined) {
                let error = new Error("Lỗi xảy ra")
                error.data = {cause: row, msg: 'Trường này không được để trống!'}
                errors.push(error)
            }
            const inStock = Number(inStockStr)
            const stockId = $(row).find('.stock-id').data('stock-id')
            const note = $(row).find('textarea').val()
            const type = $(row).find('.sku_edit_select_reason').val()
            return {inStock, stockId, note, type}
        })
        if (errors.length > 0) {
            throw errors;
        }
        const swalStatus = Swal.mixin({})
        swalStatus.fire({
            title: "Đang gửi yêu cầu...",
            html: "Xin chờ giây lát.",
            icon: 'info',
            didOpen: () => {
                Swal.showLoading();
            }
        })
        new Promise(resolve => setTimeout(() => resolve(), 1500))
        .then(() => {
            return $.ajax({
                url: `${window.context}/api/v1/stock-keeping`,
                method: 'PUT',
                data: JSON.stringify(data),
            })
        }).then(data => {
            initStockIds = []
            return $.ajax({
                url: `${window.context}/api/v1/stock-keeping/doUpdateSession-stockIds`,
                method: 'POST',
                data: JSON.stringify({
                    stockIds: initStockIds
                })
            })
        }).then(data => {
            swalStatus.fire({
                icon: "success",
                title: "Cập nhật thành công",
                showConfirmButton: false,
                timer: 1500
            })
            data_tables_transaction_history.ajax.url(`${window.context}/api/v1/stock-keeping/doGetSession-stockIds`).load()
        }).catch(error => {
            console.log(error)
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: error.responseJSON.message,
            });
        })
    } catch (errors) {
        errors.forEach(({data}) => {
            const {cause} = data;
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Bạn chưa nhập hết các trường dữ liệu cần thiết!",
            });
        })
    }
})
$('#status_filter').on('change', function(e) {
    data_tables.draw();
})
$("#filter_input").on('input', function(e) {
    data_tables.column(1).search($(this).val()).draw()
});
const mySelect = new mdb.Select(document.getElementById('sku_upload_select'), {
    container: 'body'
});

$(document).ready(() => {
    let websocket;
    function initWebSocket(url) {
        console.log(url)
        websocket = new WebSocket(url);
        websocket.onopen = function(message) {processOpen(message);};
        websocket.onmessage = function(message) {processMessage(message);};
        websocket.onclose = function(message) {processClose(message);};
        websocket.onerror = function(message) {processError(message);};

        function processOpen() {
            console.log('Server connect...')
        }
        function processMessage({data}) {
            let obj
            try {
                obj = JSON.parse(data)
            } catch (e) {
                console.log(e.message)
            }
            if (obj['actions'] == null) {
                console.log(obj)
            } else {
                obj.actions.forEach(action => {
                    switch (action) {
                        case "sender": {
                            const {sender} = obj;
                            $('#box-chat_viewGroup').append(`
                            <div class="d-flex flex-column align-items-end" >
                                <div class="fw-semibold fs-6 mb-2 d-inline">${sender.username}</div>
                                <div class="d-flex gap-2 flex-row-reverse justify-content-start flex-shrink-0">
                                    <img src="https://img.freepik.com/free-vector/illustration-user-avatar-icon_53876-5907.jpg?size=338&ext=jpg&ga=GA1.1.2008272138.1721001600&semt=ais_user" class="rounded-circle me-2"
                                         style="width: 30px; height: 30px" alt="" />
                                    <p class="p-3 bg-primary text-white rounded-4 shadow-1">
                                        ${sender.message}
                                    </p>
                                </div>
                            </div>
                        `)
                        }
                        break;
                        case "receiver": {
                            const {receiver} = obj;
                            $('#box-chat_viewGroup').append(`
                                <div class="d-flex flex-column align-items-start" >
                                    <div class="fw-semibold fs-6 mb-2">${receiver.username}</div>
                                    <div class="d-flex gap-2 justify-content-start flex-shrink-0">
                                        <img src="https://img.freepik.com/free-vector/illustration-user-avatar-icon_53876-5907.jpg?size=338&ext=jpg&ga=GA1.1.2008272138.1721001600&semt=ais_user" class="rounded-circle me-2"
                                             style="width: 30px; height: 30px" alt="" />
                                        <p class="p-3 bg-light rounded-4 shadow-1">
                                            ${receiver.message}
                                        </p>
                                    </div>
                                </div>
                            `)
                        }
                        break;
                    }
                })
            }
        }
        function processClose(message) {
        }
        function processError(message) {
        }
    }
    function sendMessage(myMessage) {
        if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
            websocket.send(myMessage);
        }
    }
    function doWebSocketConnect() {
        const target = $('#box-chat_usernameInput').val()
        console.log(websocket)
        if (!websocket) {
            $.ajax(`${window.context}/api/v1/web-socket`).then(url => initWebSocket(url+"/chat/"+target))
        }
    }

    $('#send-button').on('click', () => {
        doWebSocketConnect()
        const msg = $('#box-chat_txtAreaMessage').val()
        sendMessage(msg)
    })
})

$('#table_sku_edit').on('click', '.delete-button', function() {
    const stockId = Number($(this).parent().data('stock-id'))
    $.ajax({
        url: `${window.context}/api/v1/stock-keeping/doUpdateSession-stockIds`,
        method: 'POST',
        data: JSON.stringify({
            stockIds: [... initStockIds.filter(data => data !== stockId)]
        }),
        success: () => {
            initStockIds = [... initStockIds.filter(data => data !== stockId)]
            data_tables_transaction_history.ajax.url(`${window.context}/api/v1/stock-keeping/doGetSession-stockIds`).load()
        }
    })
});

(async function() {
    $('#table_tabs').on('click', 'li', function() {
        const lis = $(this).siblings().filter('li');
        $(lis).removeClass('active');
        $(this).addClass('active')
        this.dataset.jsonName && data_tables_transaction_history.ajax.url(`fake_transaction_history_${this.dataset.jsonName}_data.json`).load()
    })
})();