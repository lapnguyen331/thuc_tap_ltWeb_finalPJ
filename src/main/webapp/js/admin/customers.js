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
    active: '<span class="badge badge-success">Hoạt động</span>',
    deactive: '<span class="badge badge-danger">Tạm ngưng</span>',
}
const verify_badget = {
    1: '<span class="badge badge-success">Đã Xác minh</span>',
    0: '<span class="badge badge-danger">Chưa xác minh</span>',
}
const data_tables = new DataTable('#table_customers', {
    ajax: '/template/admin/customers.txt',
    language: translate,
    dom: 'tip',
    scrollCollapse: true,
    width: '100%',
    scrollY: '400px',
    order: [[1, 'asc']],
    columns: [
        {
            data: 'id',
        },
        {
            data: 'name',
        },
        {
            data: 'email',
            render: function(data, type, row) {
                return `<div class="d-flex justify-content-start w-100 gap-2"><span>${row.email}</span>${verify_badget[row.verified]}</div>`
            }
        },
        {
            data: 'phone',
        },
        {
            data: 'created'
        },
        {
            orderable: false,
            data: null,
            render: function(data) {
                return status_badget[data.status];
            }
        },
        {
            className: 'dt-right',
            data: null,
            orderable: false,
            render: function() {
                const html = `
                <div class="action-btns">
                    <a href="/template/admin/customers_edit.html" data-mdb-toggle="tooltip">
                        <i class="fa-solid fa-edit view-btn" data-mdb-toggle="tooltip" title="Chỉnh sửa"></i>
                    </a>
                    <i class="fa-solid fa-circle-xmark remove-btn" data-mdb-toggle="tooltip" title="Xóa khách hàng"></i>
                </div>
                `
                return html;
            }
        },
    ],
});
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