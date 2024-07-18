import { translate } from "./data-table-translate.js";
const data_tables_transaction_history = new DataTable('#table_sku_edit', {
    language: translate,
    ajax: 'fake_transaction_history_month4_data.json',
    dom: 'tip',
    scrollCollapse: true,
    width: '100%',
    scrollY: '200px',
    processing: true,
    bDeferRender: true,
    serverSide: true,
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
                        <img src="https://ih1.redbubble.net/image.5452846048.9607/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.jpg" width="100%" alt="">
                    </div>
                    <div class="d-flex flex-column">
                        <div>Mã sản phẩm: ${row.productId}</div>
                        <div style="overflow: hidden;
                        width: 100%;
                        display: -webkit-box;
                        -webkit-line-clamp: 1;
                        -webkit-box-orient: vertical;">Tên: ${row.productName}</div>
                    </div>
                </div>
                `
            }
        },
        {
            data: 'transactionId',
            render: function(data, type, row) {
                return `
                <div class="d-flex align-items-center">
                    <a href="#">#${data}</a>
                </div>
                `
            }
        },
        {
            data: 'change',
            className: 'w-auto',
            render: function(data, type, row) {
                return `
                <div class="d-flex gap-1">
                    <span class="fw-normal">Trong lô:</span>
                    <span class="fw-semibold">${row.inStocks}</span>
                </div>
                `
            }
        },
        {
            data: null,
            render: function(data, type, row) {
                return `
                    <input type="text" 
                    class="form-control form-control-sm" 
                    placeholder="Nhập số lượng" 
                    style="width: 130px" />
                `
            }
            
        },
        {
            data: null,
            render: function(data, type, row) {
                return `
                    <select class="sku_edit_select_supplier">
                        <option value="NCC_1">Nhà cung cấp 1</option>
                        <option value="NCC_2">Nhà cung cấp 2</option>
                        <option value="NCC_3">Nhà cung cấp 3</option>
                        <option value="NCC_4">Nhà cung cấp 4</option>
                </select>
                `
            }
            
        },
        {
            data: 'dateTime',
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
                <div class="d-flex align-items-center gap-2">
                    <select class="sku_edit_select_reason">
                            <option value="HET_HAN">Hết hạn</option>
                            <option value="SL_THAY_DOI">Thay đổi số lượng</option>
                    </select>
                    <i class="far fa-times-circle text-danger"></i>
                </div>
                `;
            }
            
        }
    ],
    initComplete: function(settings, json) {
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
$('#status_filter').on('change', function(e) {
    data_tables.draw();
})
$("#filter_input").on('input', function(e) {
    data_tables.column(1).search($(this).val()).draw()
});
const mySelect = new mdb.Select(document.getElementById('sku_upload_select'), {
    container: 'body'
});
(async function() {
    $('#table_tabs').on('click', 'li', function() {
        const lis = $(this).siblings().filter('li');
        $(lis).removeClass('active');
        $(this).addClass('active')
        this.dataset.jsonName && data_tables_transaction_history.ajax.url(`fake_transaction_history_${this.dataset.jsonName}_data.json`).load()
    })
    await $.ajax({
        url: 'fake_transaction_history_month4_data.json',
        success: (data) => {
            console.log(data)
        }
    })
})();