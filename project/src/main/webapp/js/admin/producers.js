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
const data_tables = new DataTable('#table_producers', {
    ajax: '/template/admin/producers.txt',
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
        },
        {
            data: 'phone',
        },
        {
            data: 'tax_code'
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
                    <i class="fa-solid fa-edit view-btn" data-mdb-toggle="tooltip" title="Chỉnh sửa" data-product-id = 'VZ120001'></i>
                    <i class="fa-solid fa-circle-xmark remove-btn" data-mdb-toggle="tooltip" title="Xóa NSX"></i>
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
$('.show-all-toggle').on('click', function() {
    $(this).toggleClass('active');
    $(this).text(`${$(this).hasClass('active')?'Ẩn bớt' : 'Hiện tất cả...'}`);
});

[...$('.product-more-info')].forEach(e => new mdb.Collapse(e, {toggle: false}))

$('.collapse-toggle').on('change', function() {
    let target = $(this).siblings().filter('.product-more-info')[0];
    if ($(this).is(':checked')) mdb.Collapse.getInstance(target).show();
    else mdb.Collapse.getInstance(target).hide();
})