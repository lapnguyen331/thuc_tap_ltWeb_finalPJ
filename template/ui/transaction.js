import { translate } from "./data-table-translate.js";
const data_tables_transaction = new DataTable('#table_transaction', {
    language: translate,
    dom: 'tip',
    scrollCollapse: true,
    pageLength: 5,
    width: '100%',
    scrollY: '400px',
    ajax: 'fake_transaction_data.json',
    createdRow: function(row, data, index ) {
        
    },
    columns: [
        {
            data: null,
            render: function(data, type, row) {
                const html = `
                <div class="shipment-row-wrap">
                    <div class="header">
                        <div class="form-check">
                            <input class="form-check-input chbox-shipment" type="checkbox" value="${row.transactionId}" />
                            <label class="form-check-label">Mã lô hàng: ${row.transactionId}</label>
                        </div>
                        <div class="d-flex gap-1">
                            <div class="fw-normal">Mức độ phân loại lô hàng này:</div>
                            <div class="shipment-tag outdate">
                                <span>Sắp hết hạn</span>
                            </div>
                        </div>
                    </div>
                    <div class="body">
                        <div class="thumbnail-wrap">
                            <img src="${row.thumbnail}" width="100%" alt="encore">
                        </div>
                        <div class="info-grid">
                            <div class="grid-item">
                                <div class="info-label">Mã sản phẩm</div>
                                <div class="info-text">${row.productId}</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Số lượng có sẵn trong kho</div>
                                <div class="info-text">${row.soldInMonth} cái</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Bên cung cấp sản phẩm</div>
                                <div class="info-text">${row.supplier}</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Số bán trong tháng</div>
                                <div class="info-text">61 sản phẩm</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Tên sản phẩm</div>
                                <div class="info-text">This is a very long product name with fully description, Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti quos enim ducimus minima ea iusto velit laudantium est, iste inventore nobis, at aliquam adipisci magni voluptas maxime. Exercitationem, quaerat optio.</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Ngày hết hạn</div>
                                <div class="info-text">30 - 04 - 2024</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Ngày biến động gần nhất</div>
                                <div class="info-text">25 - 04 - 2024</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Doanh thu tháng này</div>
                                <div class="info-text">2.730.000 vnđ</div>
                            </div>
                        </div>
                    </div>
                </div>
                `
                return html;
            }
        }
    ],
});
const typeChange = {
    'THEM_HANG': '<div class="text-success fw-semibold">Thêm hàng</div>'
}
const data_tables_transaction_history = new DataTable('#table_history_transaction', {
    language: translate,
    ajax: 'fake_transaction_history_month4_data.json',
    dom: 'tip',
    scrollCollapse: true,
    width: '100%',
    scrollY: '200px',
    processing: true,
    serverSide: true,
    columnDefs: [
        {
            target: [0, 2],
            className: 'text-wrap',
        }
    ],
    createdRow: function(row, data, index ) {
        
    },
    columns: [
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
            data: null,
            className: 'w-25',
            render: function(data, type, row) {
                return `
                <div class="d-flex gap-2" style="max-width: 100%">
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
            data: 'change',
            className: 'w-auto',
            render: function(data, type, row) {
                return `
                <div class="d-flex gap-1">
                    <span class="fw-normal">Trong kho:</span>
                    <span class="fw-semibold">${row.inStocks}</span>
                    <span class="fw-semibold text-${data <= 0 ? 'danger' : 'success'}">(${data > 0 && '+'}${data})</span>
                </div>
                `
            }
        },
        {
            data: null,
            render: function(data, type, row) {
                return `
                    ${typeChange[row.changeType]}
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
            width: '200px',
            className: 'dt-center',
            data: 'note',
            render: function(data, type, row) {
                return `
                <span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="${data}">
                    <div style="text-align: left;
                    overflow: hidden;
                        width: 100%;
                        display: -webkit-box;
                        -webkit-line-clamp: 1;
                        -webkit-box-orient: vertical;">${data}</div>
                </span>
                
                `
            }
            
        }
    ],
});
$('#status_filter').on('change', function(e) {
    data_tables.draw();
})
$("#filter_input").on('input', function(e) {
    data_tables.column(1).search($(this).val()).draw()
});
let selected_arr = [];
$('#table_transaction').on('change', '.chbox-shipment', function(e) {
    let {value} = this;
    let isChecked = $(this).prop('checked');
    isChecked 
    && selected_arr.push(value) 
    && $('#count_selected_sku_label').removeClass('d-none')
    .text(`(Đã chọn ${selected_arr.length} lô hàng)`)
    if (!isChecked) {
        selected_arr = [...selected_arr.filter(e => e !== value)]
        $('#count_selected_sku_label').text(``).addClass('d-none')
    }
});
const modal = new mdb.Modal($('#new_sku_modal'), {});
$('#add_new_sku_button').on('click', () => {
    modal.show();
});
(async function() {
    $('#table_tabs').on('click', 'li', function() {
        const lis = $(this).siblings().filter('li');
        $(lis).removeClass('active');
        $(this).addClass('active')
        this.dataset.jsonName && data_tables_transaction_history.ajax.url(`fake_transaction_history_${this.dataset.jsonName}_data.json`).load()
    })
})();
(function() {
    const selectProductState = {
        __aInternal: undefined,
        aListener: function(prev, cur) {},
        set product(val) {
          this.aListener(this.__aInternal, val);
          this.__aInternal = val;
        },
        get product() {
          return this.__aInternal;
        },
        onChangeHandler: function(listener) {
          this.aListener = listener;
        }
    }
    selectProductState.onChangeHandler((previousValue, currentValue) => {
        const $jqueryTarget = $('#product-showcase')
        previousValue === undefined 
            && $jqueryTarget.removeClass('d-none')
            && currentValue != undefined
            && $jqueryTarget.html(`
                <div class="border-top border-warning border-2 mt-3 pt-2 px-2 product-showcase" style="background-color: #F1F1F1">
                    <div class="auto-complete-sku-product-row">
                        <div class="thumbnail-wrapper">
                            <img src="${currentValue.images[0]}" width="100%" alt="">
                        </div>
                        <div class="info">
                            <span class="product-id">Mã: ${currentValue.id}</span>
                            <span class="product-name">${currentValue.name}</span>
                        </div>
                    </div>
                </div>
            `)
        currentValue === undefined && $jqueryTarget.addClass('d-none') && $jqueryTarget.html('')
    })
    const confirmState = {
        __aInternal: false,
        aListener: function(prev, cur) {},
        set isSaved(val) {
          this.aListener(this.__aInternal, val);
          this.__aInternal = val;
        },
        get isSaved() {
          return this.__aInternal;
        },
        onChangeHandler: function(listener) {
          this.aListener = listener;
        }
    }
    confirmState.onChangeHandler((previous, current) => {
        const $asyncCompleteTextField = $('#search_product_by_name').find('input[type="text"]')
        current 
        && $asyncCompleteTextField.prop('disabled', true)
        && $('.product-showcase').removeClass('border-warning').addClass('border-success')
        !current 
        && $asyncCompleteTextField.val()
        && $asyncCompleteTextField.prop('disabled', false).val('')
        && mdb.Autocomplete.getInstance($('#search_product_by_name').get()[0]).open()
    })
    const asyncAutocomplete = document.querySelector('#search_product_by_name');
    const asyncFilter = async (query) => {
      const url = 'fake_product_data.json';
      const response = await fetch(url);
      const {data} = await response.json();
      return data.filter((item) => {
        return item.name.toLowerCase().startsWith(query.toLowerCase());
      });
    };
    new mdb.Autocomplete(asyncAutocomplete, {
      filter: asyncFilter,
      displayValue: (value) => value.name,
      itemContent: (result) => {    
        return `
            <div class="auto-complete-sku-product-row">
                <div class="thumbnail-wrapper">
                    <img src="${result.images[0]}" width="100%" alt="">
                </div>
                <div class="info">
                    <span class="product-id">Mã: ${result.id}</span>
                    <span class="product-name">${result.name}</span>
                </div>
            </div>
        `;
      }
    });
    $('#txt-date').daterangepicker({
        "singleDatePicker": true,
        locale: {
            format: 'DD/MM/YYYY'
        },
        parentEl: '#txt-date + #txt-date-parent',
        minDate: getToday(),
    }, function(start, end, label) {
        console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
    });
    asyncAutocomplete.addEventListener('itemSelect.mdb.autocomplete', function({value}) {
        selectProductState.product = value;
    });
    $('.btn-function.btn-delete').on('click', () => {
        selectProductState.product = undefined
        confirmState.isSaved = false;
    })
    $('.btn-function.btn-save').on('click', () => {
        confirmState.isSaved = true;
    })
    function getToday() {
        let today = new Date();
        const dd = String(today.getDate()).padStart(2, '0');
        const mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        const yyyy = today.getFullYear();
        return dd + '/' + mm + '/' + yyyy;
    }
})();