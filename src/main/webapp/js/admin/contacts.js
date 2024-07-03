const translate = {
    "decimal":        "",
    "emptyTable":     "Không có dữ liệu trong table",
    "info":           "Hiển thị từ dòng _START_ đến dòng _END_ trong tổng số _TOTAL_",
    "infoEmpty":      "Showing 0 to 0 of 0 entries",
    "infoFiltered":   "(Lọc ra trong _MAX_ dòng)",
    "infoPostFix":    "",
    "thousands":      ",",
    "lengthMenu":     "Hiển thị tối đa _MENU_ dòng",
    "loadingRecords": "Đang load...",
    "processing":     "",
    "search":         "Tìm kiếm",
    "zeroRecords":    "Không tìm thấy trường nào.",
    "paginate": {
        "first":      "Đầu tiên",
        "last":       "Cuối cùng",
        "next":       "Tiếp theo",
        "previous":   "Trước đó"
    },
    "aria": {
        "sortAscending":  ": activate to sort column ascending",
        "sortDescending": ": activate to sort column descending"
    }
};
const status_badget = {
    active: '<span class="badge badge-success w-100">Đã giải quyết</span>',
    deactive: '<span class="badge badge-danger w-100">Chưa giải quyết</span>',
}
const data_tables = new DataTable('#table_contacts', {
    ajax: '/template/admin/contacts.txt',
    language: translate,
    dom: 'tip',
    scrollCollapse: true,
    scrollY: '400px',
    createdRow: function(row, data, dataIndex) {
        if (data.status === 'active') $(row).addClass('solved')
        $(row).addClass('contacts-row')
    },
    columnDefs: [
        {
            className: 'dt-nowrap',
            targets: [0, 1, 2, 3, 4, 5]
        }
    ],
    columns: [
        {
            data: 'status',
            render: function(data, type, row) {
                return `
                <div class="check-btn" data-mdb-toggle="tooltip" title="Đánh dấu đã đọc">
                    <i class="${data === 'active' ? 'fa-solid' : 'fa-regular'} fa-circle-check fs-5" style="cursor: pointer"></i>
                </div>
                `
            }
        },
        {
            data: null,
            render: function(data, type, row) {
                return `${data.first_name} ${data.last_name}`
            }
        },
        {
            data: 'phone'
        },
        {
            data: 'email'
        },
        {
            data: 'status',
            render: function(data, type, row) {
                return status_badget[data]
            }
        },
        {
            data: 'note',
            render: (data) => {
                const html = `
                    <div class="contact-note">${data}</div>
                `;
                return html
            }
        },
        {
            data: 'created',
            render: (data) => {
                const html = `
                    <div class="created-time">${data}</div>
                `;
                return html
            }
        }
    ],
});
const checkSelected = function() {
    const $row = $('#table_contacts').find('tr.solved');
    if ($row.length) {
        console.log('ok')
    }
    else {
        console.log('not ok')
    }
}
$('#table_contacts').on('click', '.check-btn i', function(e) {
    $(this.closest('tr')).toggleClass('solved');
    $(this).toggleClass('fa-regular').toggleClass('fa-solid')
    const new_data = data_tables.row(this.closest('tr')).data();
    new_data.status = new_data.status === 'active' ? 'deactive' : 'active';
    data_tables.row(this.closest('tr')).data(new_data);
    data_tables.draw();
    checkSelected();
    e.stopPropagation();
})
$('#table_contacts').on('click', '.contacts-row', function() {
    console.log('clicked')
})

new mdb.Select($('#status_filter')[0], {
    clearButton: true,
})
$('.select-clear-btn').css({display: 'none'});
$('#date_picker').daterangepicker({
    locale: {
        format: 'DD/MM/YYYY'
    },
    linkedCalendars: false,
    alwaysShowCalendars: true,
    drop: "auto",
    autoUpdateInput: false,
}, function(start, end, label) {
  console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
});

$('#date_picker').on('apply.daterangepicker', function(ev, picker) {
    $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
})

$('#date_picker').on('cancel.daterangepicker', function(ev, picker) {
    $(this).val('');
});

const customItemAutocomplete = document.querySelector('#customItem');
const async_data = async (value) => {
    const url = "/template/admin/discounts.txt";
    const response = await fetch(url);
    let {data} = await response.json();
    const product_list = data.map(o => o.product);
    return product_list.filter(product => {
        return product.name.toLowerCase().startsWith(value.toLowerCase());
    });
}
const modal_load = function(data) {
    let html = `
        <div>
            <div class="fw-semibold text-black">${data.first_name} ${data.last_name}</div>
            <div class="d-flex align-items-center gap-1 text-secondary">
                <span>tới tôi</span>
                <i class="fa-solid fa-chevron-down d-inline-block" style="font-size: 10px; padding-top: 5px;"></i>
            </div>
        </div>
        <div class="d-flex gap-3 text-secondary">
            <span>${data.created}</span>
            <div class="d-flex align-items-center gap-1">
                <i class="fa-regular fa-envelope pt-1"></i>
                <span>${data.email}</span>
            </div>
            <div class="d-flex align-items-center gap-1">
                <i class="fa-regular fa-comment-dots pt-1"></i>
                <span>${data.phone}</span>
            </div>
        </div>
    `
    $('#admin_product_modal').find('.contacts-header').html(html);
    html = '';
    data.note.split('\n').forEach(data => html += `<div class="pb-2">${data}</div>`);
    $('#admin_product_modal').find('.contacts-content').html(html);
}
const discounts_modal = new mdb.Modal($('#admin_product_modal')[0]);
$('#table_contacts_wrapper').on('click', '.contacts-row', function() {
    modal_load(data_tables.row(this).data());
    discounts_modal.show()
})

$('#input_date_range').daterangepicker({
    locale: {
        format: 'DD/MM/YYYY'
    },
    linkedCalendars: false,
    alwaysShowCalendars: true,
    drop: "auto",
    autoUpdateInput: false,
}, function(start, end, label) {
  console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
});

$('#input_date_range').on('apply.daterangepicker', function(ev, picker) {
    $(this).val(picker.startDate.format('DD/MM/YYYY') + ' ~ ' + picker.endDate.format('DD/MM/YYYY'));
})

$('#input_date_range').on('cancel.daterangepicker', function(ev, picker) {
    $(this).val('');
});