import { fake_transaction_data } from "./fake-data.js";
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
const data_tables = new DataTable('#table_orders', {
    language: translate,
    dom: 'tip',
    scrollCollapse: true,
    pageLength: 5,
    width: '100%',
    scrollY: '400px',
    data: fake_transaction_data.data,
    createdRow: function(row, data, index ) {
        
    },
    columns: [
        {
            data: null,
            render: function(data, type, row) {
                const html = `
                <div class="shipment-row-wrap">
                    <div class="header">
                        <div class="fw-normal">Mã lô hàng: ${row.transactionId}</div>
                        <div class="d-flex gap-1">
                            <div class="fw-normal">Mức độ phân loại lô hàng này:</div>
                            <div class="shipment-tag outdate">
                                <span>Sắp hết hạn</span>
                            </div>
                        </div>
                    </div>
                    <div class="body">
                        <div class="thumbnail-wrap">
                            <img src="https://ih1.redbubble.net/image.5452846048.9607/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.jpg" width="100%" alt="encore">
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
$('#status_filter').on('change', function(e) {
    data_tables.draw();
})
$("#filter_input").on('input', function(e) {
    data_tables.column(1).search($(this).val()).draw()
});