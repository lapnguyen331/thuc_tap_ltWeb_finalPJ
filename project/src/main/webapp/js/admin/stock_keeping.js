import { translate } from "./data-table-translate.js";
$(document).ready(() => {
    const selectStockIdsState = {
        __aInternal: new Set(initStockIds),
        aListener: function(stateValue) {},
        get ids() {
            return this.__aInternal;
        },
        add: function (item) {
            this.__aInternal.add(Number(item))
            this.aListener(this.__aInternal)
        },
        remove: function (item) {
            this.__aInternal.delete(Number(item))
            this.aListener(this.__aInternal)
        },
        onChangeHandler: function(listener) {
            this.aListener = listener;
        }
    }
    selectStockIdsState.onChangeHandler((idsSet) => {
        const size = idsSet.size
        size > 0 && $('#count_selected_sku_label')
            .text(`Đã chọn ${size} lô hàng`).removeClass('d-none')
            && console.log(idsSet)
        size == 0 && $('#count_selected_sku_label')
            .text(``).addClass('d-none')
    })
    const data_tables_transaction = new DataTable('#table_transaction', {
        language: translate,
        scrollCollapse: true,
        bDeferRender: true,
        searching: false,
        lengthMenu: [5, 10, 15, 25, 50],
        width: '100%',
        scrollY: '400px',
        ajax: {
            url: `${window.context}/api/v1/stock-keeping/doGetAll-SKURowData`,
            method: 'POST'
        },
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
                            <input class="form-check-input chbox-shipment" type="checkbox" ${selectStockIdsState.ids.has(row.stockId) && 'checked'} 
                            value="${row.stockId}" />
                            <label class="form-check-label">Mã lô hàng: ${row.stockId}</label>
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
                            <img src="${row.product.thumbnail}" width="100%" alt="encore">
                        </div>
                        <div class="info-grid">
                            <div class="grid-item">
                                <div class="info-label">Mã sản phẩm</div>
                                <div class="info-text">${row.product.id}</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Số lượng có sẵn trong kho</div>
                                <div class="info-text">${row.availableQuantity} cái</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Bên cung cấp sản phẩm</div>
                                <div class="info-text">${row.producer.name}</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Số bán trong tháng</div>
                                <div class="info-text">${row.stat.sellQuantity} sản phẩm</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Tên sản phẩm</div>
                                <div class="info-text">${row.product.name}.</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Ngày hết hạn</div>
                                <div class="info-text">${row.expiredDate}</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Ngày biến động gần nhất</div>
                                <div class="info-text">${row.lastChange}</div>
                            </div>
                            <div class="grid-item">
                                <div class="info-label">Doanh thu tháng này</div>
                                <div class="info-text">${row.stat.totalRevenue} vnđ</div>
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
        'THEM_HANG': '<div class="text-success fw-semibold">Thêm hàng</div>',
        'THEM_SKU_MOI': '<div class="text-success fw-semibold">Thêm SKU mới</div>'
    }
    const data_tables_transaction_history = new DataTable('#table_history_transaction', {
        language: translate,
        bDeferRender: true,
        lengthMenu: [5, 10, 15, 25, 50],
        ajax: {
            url: `${window.context}/api/v1/stock-keeping/doGetAll-SKUHistoryData`,
            type: 'POST',
            dataType: 'json',
            data: (data) => JSON.stringify(data)
        },
        scrollCollapse: true,
        width: '100%',
        scrollY: '200px',
        processing: true,
        searching: false,
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
                name: "dt_id",
                data: 'id',
                render: function(data, type, row) {
                    return `
                <div class="d-flex align-items-center">
                    <a href="#">#${data}</a>
                </div>
                `
                }
            },
            {
                orderable: false,
                data: null,
                className: 'w-25',
                render: function(data, type, row) {
                    return `
                <div class="d-flex gap-2" style="max-width: 100%">
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
                name: "dt_changeValue",
                data: 'changeValue',
                className: 'w-auto',
                render: function(data, type, row) {
                    return `
                <div class="d-flex gap-1">
                    <span class="fw-normal">Trong kho:</span>
                    <span class="fw-semibold">${row.prevValue}</span>
                    <span class="fw-semibold text-${data <= 0 ? 'danger' : 'success'}">(${data > 0 && '+'}${data})</span>
                </div>
                `
                }
            },
            {
                orderable: false,
                name: "dt_type",
                data: 'type',
                render: function(data, type, row) {
                    return `
                    ${typeChange[data]}
                `
                }

            },
            {
                name: "dt_createAt",
                data: 'createAt',
                render: function(data, type, row) {
                    return `
                    <div>${data}</div>
                `
                }

            },
            {
                orderable: false,
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
    $('#table_transaction').on('change', '.chbox-shipment', function(e) {
        let {value} = this;
        let isChecked = $(this).prop('checked');
        isChecked && selectStockIdsState.add(value)
        !isChecked && selectStockIdsState.remove(value)
    });
    $('#edit_selected_sku_button').on('click', function() {
        $.ajax({
            url: `${window.context}/api/v1/stock-keeping/doUpdateSession-stockIds`,
            method: 'POST',
            data: JSON.stringify({
                stockIds: [... selectStockIdsState.ids]
            }),
            success: () => {
                window.location.href =`${window.context}/admin/sku-edit`;
            }
        })
    })
    const modal = new mdb.Modal($('#new_sku_modal'), {});
    $('#add_new_sku_button').on('click', () => {
        modal.show();
    });
    (async function() {
        $('#table_tabs').on('click', 'li', function() {
            const lis = $(this).siblings().filter('li');
            $(lis).removeClass('active');
            $(this).addClass('active')
            this.dataset.jsonName && data_tables_transaction_history.ajax.url(`${window.context}/api/v1/stock-keeping/doGetAll-SKUHistoryData`).load()
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
                            <img src="${currentValue.thumbnail}" width="100%" alt="">
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
            && $('#sku_newForm_productId').val(selectProductState.product.id)
            !current
            && $asyncCompleteTextField.val()
            && $asyncCompleteTextField.prop('disabled', false).val('')
            && mdb.Autocomplete.getInstance($('#search_product_by_name').get()[0]).open()
        })
        const asyncAutocomplete = document.querySelector('#search_product_by_name');
        const asyncFilter = async (query) => {
            const url = `${window.context}/api/v1/products/getByName?name=${query}`;
            const response = await fetch(url);
            const data = await response.json();
            return data;
        };
        new mdb.Autocomplete(asyncAutocomplete, {
            filter: asyncFilter,
            displayValue: (value) => value.name,
            itemContent: (result) => {
                return `
            <div class="auto-complete-sku-product-row">
                <div class="thumbnail-wrapper">
                    <img src="${result.thumbnail}" width="100%" alt="">
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
        $.ajax({
            url: `${window.context}/api/v1/producer/getAll_producerIDName`,
            success: (data) => {
                const $select = $(`#sku_newForm_selectField_supplier`).html('')
                const arr = [... data.map((e) => {
                    return $(`<option value="${e.id}">${e.name}</option>`)
                })]
                arr.forEach(a => $select.append(a))
            }
        })
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
        $('#sku_newForm_confirm').on('click', () => {
            const productId = Number($('#sku_newForm_productId').val());
            const inStock = Number($('#sku_newForm_inStock').val())
            const expiredDate = $('#txt-date').val()
            const producerId = Number($('#sku_newForm_selectField_supplier').val())
            const unitPrice = Number($('#sku_newForm_unitPrice').val())
            const data = {producerId, inStock, expiredDate, unitPrice, productId};
            $.ajax({
                url: `${window.context}/api/v1/stock-keeping`,
                data: JSON.stringify(data),
                type: 'POST',
                success: (data) => {
                    Swal.fire({
                        title: "Thành công!",
                        text: "Đã thêm SKU mới vào cửa hàng!",
                        icon: "success"
                    });
                    data_tables_transaction.ajax.reload()
                    data_tables_transaction_history.ajax.reload()
                    selectProductState.product = undefined
                    confirmState.isSaved = false
                    $('#sku_newForm_productId').val('')
                    $('#sku_newForm_inStock').val('')
                    $('#txt-date').val('')
                    $('#sku_newForm_unitPrice').val('')
                }
            })
        })
        function getToday() {
            let today = new Date();
            const dd = String(today.getDate()).padStart(2, '0');
            const mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            const yyyy = today.getFullYear();
            return dd + '/' + mm + '/' + yyyy;
        }
    })();
})