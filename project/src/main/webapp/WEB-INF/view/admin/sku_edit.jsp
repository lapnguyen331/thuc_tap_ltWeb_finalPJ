<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 1/22/2024
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Thư viện ngoài (Luôn để đầu tiên) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/fontawesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/image-uploader/image-uploader.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/suneditor-master/dist/css/suneditor.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/DataTables/datatables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/plugins/css/drag-and-drop.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datatables-override.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-override.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/table-tabs.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/stock_keeping_row.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/sku_edit.css">
    <title>Quản lí sản phẩm</title>'
    <style>
        #table_tabs>ul>li{
            flex: 1;
            text-align: center;
        }

        .dataTables_wrapper {
            margin-top: 0px;
        }

        #table_sku_edit_wrapper {
            --wheat-color: #444444;
            --red-light-color-main: #c5c9d2;
        }

        #table_sku_edit_wrapper .dataTables_scrollHead {
            background-image: none;
            background-color: #f5f6f9;
            border: solid #c5c9d2 1px !important;
            border-bottom: none !important;
            box-shadow: none !important;
        }

        #table_sku_edit_wrapper .dataTables_scrollHead table thead th + th {
            border-left: none;
            box-shadow: none;
        }

        #table_sku_edit_wrapper .dataTables_scrollBody {
            border-top: 0px;
        }

        .form-control:focus {
            border-color: var(--red-color-main);
            box-shadow: inset 0 0 0 1px var(--red-color-main);
        }

        #table_sku_edit tr + tr{
            border-top: 1px solid black;
        }
    </style>

    <style>
        .box-chat {
            background-color: white;
            z-index: 1000;
            width: 800px;
            padding: 20px;
            border-radius: 10px;
            position: absolute;
            height: 400px;
        }

        .box-chat-body {
            width: 400px;
            overflow-y: scroll;
            border-left: 2px solid #cccccc;
            margin-left: 20px;
            padding-left: 20px;
            padding-top: 10px;
            padding-bottom: 10px;
            margin-top: 20px;
            margin-bottom: 50px;
        }

        .view-group {
            overflow: hidden;
            padding-right: 40px;
        }
    </style>
</head>

<body>
<%@ include file="/WEB-INF/view/admin/shared/header.jsp" %>
<%@ include file="/WEB-INF/view/admin/shared/sidebar.jsp" %>
<section id="main-content-section">
    <main class="main" id="sku_edit">
        <div class="box-chat draggable-element shadow-1-strong"
             data-mdb-draggable-init data-mdb-drag-handle=".draggable-drag-ico">
            <i class="fas fa-arrows-alt draggable-drag-ico"></i>
            <div class="d-flex h-100">
                <div class="d-flex flex-fill flex-column">
                    <div class="fw-semibold fs-6 mt-3 mb-2">Tên user:</div>
                    <div class="form-outline mt-2 w-50" data-mdb-input-init>
                        <input id="box-chat_usernameInput" type="text" class="form-control form-control-sm" />
                    </div>
                    <div class="fw-semibold fs-6 mt-3 mb-2">Tin nhắn:</div>
                    <div class="form-outline" data-mdb-input-init>
                        <textarea class="form-control" id="box-chat_txtAreaMessage" rows="4"></textarea>
                    </div>
                    <div class="d-flex flex-row-reverse mt-3">
                        <div class="bg-success fw-semibold fs-6 rounded-1 py-1 px-2 text-white"
                             id="send-button" style="cursor: pointer"
                        >Gửi tin nhắn</div>
                    </div>
                </div>
                <div class="box-chat-body">
                    <div class="view-group" id="box-chat_viewGroup">

                    </div>
                </div>
            </div>
        </div>
        <article class="body">
            <main class="content p-3">
                <header class="header d-flex justify-content-between">
                    <div class="d-flex align-items-center gap-2">
                        <div class="fw-semibold fs-4 underline d-inline-block">
                            SKU Thực tế được điều chỉnh
                        </div>
                        <span class="text-secondary fw-semibold fs-5">(10/1,000)</span>
                    </div>
                    <style>
                        .form-outline:has(+#sku_upload_select) {
                            width: 250px;
                        }
                    </style>
                    <select id="sku_upload_select">
                        <option value="1">Đăng tải biểu mẫu</option>
                        <option value="2">Tải tệp chứa toàn bộ SKU</option>
                        <option value="3">Tải tệp với các SKU đã chọn</option>
                    </select>
                </header>

                <section class="d-inline-flex gap-2 mb-2">
                    <div class="form-outline" data-mdb-input-init style="width: 150px;">
                        <input type="text" id="form12" class="form-control form-control-sm" />
                        <label class="form-label" for="form12">Nhập mã SKU</label>
                    </div>
                    <div class="btn-add-sku bg-success text-white fw-semibold px-2 px-1 border border-1 rounded">
                        Thêm
                    </div>
                </section>

                <div class="table-header">
                    <span>Danh sách các SKU cần chỉnh sửa</span>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <nav id="table_tabs">
                            <ul class="w-100 justify-content-between">
                                <li class="active" data-json-name="month4">
                                    <i class="fa-solid fa-arrow-trend-up"></i>
                                    <span>Tháng 04/2024</span>
                                </li>
                                <li data-json-name="month5">
                                    <i class="fa-solid fa-arrow-trend-up"></i>
                                    <span>Tháng 05/2024</span>
                                </li>
                                <li>
                                    <i class="fa-solid fa-arrow-trend-up"></i>
                                    <span>Tháng 06/2024</span>
                                </li>
                                <li>
                                    <i class="fa-solid fa-arrow-trend-up"></i>
                                    <span>Tháng 07/2024</span>
                                </li>
                                <li>
                                    <i class="fa-solid fa-arrow-trend-up"></i>
                                    <span>Tháng 08/2024</span>
                                </li>
                                <li>
                                    <i class="fa-solid fa-arrow-trend-up"></i>
                                    <span>Tháng 09/2024</span>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <table id="table_sku_edit" class="hover" style="width: 100%">
                    <thead>
                    <tr>
                        <th>
                            <div class="d-flex gap-2 align-items-center">
                                <i class="fa-solid fa-gear"></i>
                                <span>Sản phẩm trong lô</span>
                            </div>
                        </th>
                        <th>
                            <span>Mã SKU</span>
                        </th>
                        <th>
                            <div class="d-flex gap-2 align-items-center">
                                <i class="fas fa-wrench d-inline-block"></i>
                                <span>Cập nhật</span>
                            </div>
                        </th>
                        <th>
                            <span>Nhà cung cấp</span>
                        </th>
                        <th>
                            <div class="d-flex gap-2 align-items-center">
                                <i class="far fa-clock"></i>
                                <span>Hạn sử dụng</span>
                            </div>
                        </th>
                        <th>
                            <span>Lý do điều chỉnh</span>
                        </th>
                        <th>
                            <span>Ghi chú</span>
                        </th>
                    </tr>
                    </thead>
                </table>
                <div class="border-top border-1 mt-3 border-info">
                    <div class="d-flex align-items-center justify-content-between pt-3 px-2">
                        <div class="px-2 py-1 border border-1 rounded-1 fw-semibold d-inline-block">Thoát</div>
                        <div id="btn_updateAll" style="cursor: pointer"
                             class="px-2 py-1 text-white border border-1 rounded-1 fw-semibold d-inline-block bg-success">Xác nhận</div>
                    </div>
                </div>
            </main>
        </article>
    </main>
</section>
<script>
    let initStockIds = ${requestScope.stockIdsJSON}
</script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/plugins/js/drag-and-drop.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin/sidebar.js?v=1"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="${pageContext.request.contextPath}/libs/daterangepicker-master/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/DataTables/datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/libs/suneditor-master/dist/suneditor.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin/data-table-translate.js" type="module"></script>
<script src="${pageContext.request.contextPath}/js/admin/sku_edit.js?v=18" type="module"></script>
</body>

</html>