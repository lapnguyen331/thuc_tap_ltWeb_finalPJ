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
const categories = {
    'cao_hong_sam' : 'Cao Hồng Sâm'
}
const badget_status = [
    '<span class="badge badge-danger">Đã bị hủy</span>',
    '<span class="badge badge-success">Đã được giao</span>',
    '<span class="badge badge-primary">Đang giao</span>',
    '<span class="badge badge-warning">Đang trả về</span>',
]
DataTable.ext.search.push(function (settings, data, dataIndex, row) {
    const status = $('#status_filter').find(':selected').val();
    if (!status) return true;
    return row.status == status;
})
DataTable.ext.search.push(function (settings, data, dataIndex, row) {
    if (!$('#date_picker').val()) return true;
    const start = $('#date_picker').data('daterangepicker').startDate.format('DD-MM-YYYY');
    const end = $('#date_picker').data('daterangepicker').endDate.format('DD-MM-YYYY');
    const rowDate = moment(data[6], 'DD/MM/YYYY');
    const startDate = moment(start, 'DD/MM/YYYY');
    const endDate = moment(end, 'DD/MM/YYYY');
    return rowDate.isAfter(startDate) && rowDate.isBefore(endDate)
})
const data_tables = new DataTable('#table_orders', {
    ajax: `${window.context}/api/order/getAll`,
    language: translate,
    dom: 'tip',
    scrollCollapse: true,
    pageLength: 5,
    width: '100%',
    scrollY: '400px',
    columns: [
        {
            data: 'id'
        },
        {
            data: 'name'
        },
        {
            data: 'price',
            render: function(data) {
                return new Intl.NumberFormat('en-US').format(data)
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
            render: function(data) {
                return badget_status[data]
            }
        },
        {
            data: 'create'
        },
        {
            data: null,
            render: function(data, type, row) {
                const html = `
                <div class="action-btns">
                    <a href= "${window.context}/admin/order/update?id=${row.id}" data-mdb-toggle="tooltip">
                    <i class="fa-solid fa-edit view-btn" data-mdb-toggle="tooltip" title="Chỉnh sửa"></i>
                    </a>
                </div>
                `
                return html;
            }
        },
    ],
});
$('#status_filter').on('change', function(e) {
    data_tables.draw();
})
$("#filter_input").on('input', function(e) {
    data_tables.column(1).search($(this).val()).draw()
});
//init
(function() {
    $('#table_tabs').on('click', 'li', function() {
        const lis = $(this).siblings().filter('li');
        $(lis).removeClass('active');
        $(this).addClass('active')
    })
    
    $('#date_picker').daterangepicker({
        locale: {
            format: 'DD/MM/YYYY'
        },
        linkedCalendars: false,
        alwaysShowCalendars: true,
        drop: "auto",
        autoUpdateInput: false,
    }, function(start, end, label) {
        $(this).data('start', start.format('YYYY-MM-DD'))
        $(this).data('end', end.format('YYYY-MM-DD'))
    });
    
    $('#date_picker').on('apply.daterangepicker', function(ev, picker) {
        $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
        data_tables.draw();
    })
    
    $('#date_picker').on('cancel.daterangepicker', function(ev, picker) {
        $(this).val('');
        data_tables.draw();
    });
    new mdb.Select($('#status_filter')[0])
})();