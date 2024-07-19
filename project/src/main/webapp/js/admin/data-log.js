console.log("mới")
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
$(document).ready(function() {
    const table = $('#logTable').DataTable({
        ajax: `${window.context}/admin/logAPI`,
        language: translate,
        dom: 'tip',
        scrollCollapse: true,
        pageLength: 5,
        searching: false,
        paging: true,
        width: '100%',
        scrollX: '400px',
        order: [[3, 'asc']],
        info: false,
        processing: true,
        columns: [
            { "data": "id" },
            {
                "data": "level",
                render:function(data, type, row) {
                    return badget_level[data];
                }
            },
            { "data": "message" },
            { "data": "createAt",
                render: function (data, type, row){
                    return formatDateTime(data)
                }
            },
            {
                data: null,
                render: function(data, type, row) {
                    const html = `
                    <div class="action-btns">
                        <button class="delete-btn" data-id="${row.id}" data-mdb-toggle="tooltip">
        <!--                         <i class="fa-solid fa-circle-xmark remove-btn" data-mdb-toggle="tooltip" title="Xóa Log"></i>-->
                         <i class="fa-regular fa-trash-can" title="Xóa log"></i>
                        </button>
                    </div>
                    `
                    return html;
                }
            },
        ]
    });
    //ajax xóa log
    $('#logTable tbody').on('click', '.delete-btn', function() {
        const logId = $(this).data('id');
        if (confirm('Bạn có chắc chắn muốn xóa log này không?')) {
            $.ajax({
                url: `${window.context}/admin/logAPI?id=${logId}`,
                type: 'DELETE',
                success: function(result) {
                    table.ajax.reload();
                },
                error: function(xhr, status, error) {
                    alert('Đã xảy ra lỗi khi xóa log');
                }
            });
        }
    });
});
const badget_level = {
    "ERROR": '<span class="badge badge-danger">ERROR</span>',
    "INFO": '<span class="badge badge-primary">INFO</span>',
    "WARNING": '<span class="badge badge-warning">WARNING</span>'
}
//fomat ngày tháng
function formatDateTime(dateTimeString) {
    // Tạo đối tượng Date từ chuỗi đầu vào
    const date = new Date(dateTimeString);

    // Định dạng lại ngày giờ
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-indexed
    const year = date.getFullYear();
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    // Tạo chuỗi ngày giờ theo định dạng mong muốn
    return `${day}-${month}-${year} ${hours}:${minutes}:${seconds}`;
}
