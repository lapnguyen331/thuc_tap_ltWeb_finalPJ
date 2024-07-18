
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

const status_badget = {
    active: '<span class="badge badge-success">Hoạt động</span>',
    deactive: '<span class="badge badge-danger">Tạm ngưng</span>',
}
const data_tables = new DataTable('#table_categories', {
    ajax: '/template/admin/categories.txt',
    language: translate,
    dom: 'tip',
    scrollCollapse: true,
    scrollY: '400px',
    columns: [
        {
            data: 'name',
            render: function(data, type, row) {
                const html = `
                    <div class="category-wrap d-flex align-items-center gap-2">
                        <div class="category-img">
                            <img src="${row['image']}" alt="">
                        </div>
                        <div class="category-info d-flex flex-column justify-content-center">
                            <div class="fw-semibold">${row['name']}</div>
                            <div class="fw-light">Mã danh mục: ${row['id']}</div>
                        </div>
                    </div>
                `
                return html;
            }
        },
        {
            data: null,
            orderable: false,
            className: 'dt-center',
            render: function(data) {
                return status_badget[data['status']]
            }
        },
        {
            data: 'create',
            className: 'dt-center',
            render: function(data) {
                return `<span>${data}</span>`
            }
        },
        {
            className: 'dt-right',
            data: null,
            orderable: false,
            render: function(data) {
                const html = `
                <div class="action-btns">
                    <a href="/template/admin/categories_edit.html" data-mdb-toggle="tooltip" title="Chỉnh sửa danh mục">
                        <i class="fa-solid fa-edit view-btn" data-product-id = 'VZ120001'></i>
                    </a>
                    <i class="fa-solid fa-circle-xmark remove-btn" data-mdb-toggle="tooltip" title="Xóa đơn hàng"></i>
                </div>
                `
                return html;
            }
        },
    ],
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
      console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
    });
    
    $('#date_picker').on('apply.daterangepicker', function(ev, picker) {
        $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
    })
    
    $('#date_picker').on('cancel.daterangepicker', function(ev, picker) {
        $(this).val('');
    });
    
    new mdb.Select($('#status_filter')[0], {
        clearButton: true,
    })
    $('.select-clear-btn').css({display: 'none'});
})();