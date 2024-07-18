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

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datatables-override.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-override.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/table-tabs.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/stock_keeping.css?v=3">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/stock_keeping_row.css">
    <title>Quản lí sản phẩm</title>
</head>

<body>
<%@ include file="/WEB-INF/view/admin/shared/header.jsp" %>
<%@ include file="/WEB-INF/view/admin/shared/sidebar.jsp" %>

<c:if test="${not empty sessionScope.user}">
    <%@ include file="/WEB-INF/view/admin/shared/boxchat.jsp" %>
</c:if>
<section id="main-content-section">
    <main class="main">
        <article class="body">
            <main class="content p-3">
                <h1 class="text-uppercase fw-semibold fs-4 underline">
                    <u>Tổng quan nhập kho</u>
                </h1>
                <article class="overview">
                    <div class="square"></div>
                    <div class="square"></div>
                    <div class="square"></div>
                    <div class="square"></div>
                    <div class="square"></div>
                    <div class="square"></div>
                </article>
                <div class="table-header d-flex justify-content-between align-items-center">
                    <div>
                        <span>Trình quản lý nhập kho</span>
                        <span class="fs-6 fw-normal d-none" id="count_selected_sku_label"></span>
                    </div>
                    <div class="d-flex gap-4">
                        <div class="btn-function fs-6" id="add_new_sku_button">
                            <i class="fas fa-plus"></i>
                            Thêm SKU mới
                        </div>
                        <div class="btn-function fs-6" id="edit_selected_sku_button">
                            <i class="fas fa-plus"></i>
                            Điều chỉnh tồn kho
                        </div>
                    </div>
                </div>
                <table id="table_transaction" style="width: 100%">
                    <thead>
                    <tr>
                    </tr>
                    </thead>
                </table>
                <div class="table-header mt-lg-5">
                    <span>Lịch sử biến động tồn kho</span>
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
                <table id="table_history_transaction" class="hover" style="width: 100%">
                    <thead>
                    <tr>
                        <th>Mã lô</th>
                        <th>Sản phẩm trong lô</th>
                        <th>Số lượng biến động</th>
                        <th>Loại biến động</th>
                        <th>Thời gian</th>
                        <th>Ghi chú</th>
                    </tr>
                    </thead>
                </table>
            </main>
        </article>
    </main>
</section>
<!-- Modal -->
<article class="modal fade" id="new_sku_modal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Tồn kho ban đầu</h5>
                <button type="button" class="btn-close" data-mdb-ripple-init data-mdb-dismiss="modal" aria-label="Đóng"></button>
            </div>
            <div class="modal-body">
                <div class="d-flex gap-3">
                    <div id="search_product_by_name" class="form-outline autocomplete" data-mdb-input-init>
                        <input type="text" class="form-control form-control-sm" />
                        <input type="hidden" id="sku_newForm_productId" class="form-control form-control-sm" />
                        <label class="form-label">Tìm kiếm sản phẩm</label>
                        <span class="trailing pe-auto clear d-none" tabindex="0">✕</span>
                    </div>
                    <div class="d-flex gap-1">
                        <div class="btn-function btn-save">
                            <i class="fas fa-check"></i>
                        </div>
                        <div class="btn-function btn-delete">
                            <i class="fas fa-times"></i>
                        </div>
                    </div>
                </div>
                <div id="product-showcase" class="d-none"></div>
                <div class="fw-semibold fs-6 mt-3 mb-2">Thông tin kho</div>
                <article id="product-info-form" class="d-flex flex-column gap-3">
                    <div class="form-outline autocomplete" data-mdb-input-init>
                        <input id="sku_newForm_inStock" type="text" class="form-control form-control-sm" />
                        <label class="form-label">Số lượng trong kho</label>
                    </div>
                    <select id="sku_newForm_selectField_supplier">
                        <option value="1">Nhà cung cấp 1</option>
                        <option value="2">Nhà cung cấp 2</option>
                        <option value="3">Nhà cung cấp 3</option>
                    </select>
                    <label class="form-label select-label">Bên cung cấp</label>
                    <div class="form-outline" style="position: relative;">
                        <input id="txt-date" type="text" class="form-control form-control-sm" />
                        <label class="form-label">Hạn sử dụng</label>
                        <div id="txt-date-parent" style="position: absolute;"></div>
                    </div>
                    <div class="form-outline autocomplete" data-mdb-input-init>
                        <input id="sku_newForm_unitPrice" type="text" class="form-control form-control-sm" />
                        <label class="form-label">Giá nhập trên từng đơn vị</label>
                    </div>
                </article>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-mdb-ripple-init data-mdb-dismiss="modal">Đóng</button>
                <button type="button" class="btn btn-primary" id="sku_newForm_confirm" data-mdb-ripple-init>Thêm</button>
            </div>
        </div>
    </div>
</article>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin/sidebar.js?v=1"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="${pageContext.request.contextPath}/libs/daterangepicker-master/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/DataTables/datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/libs/suneditor-master/dist/suneditor.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin/data-table-translate.js" type="module"></script>
<script>
    let initStockIds = ${requestScope.json}
</script>
<script type="module" src="${pageContext.request.contextPath}/js/admin/stock_keeping.js?v=19"></script>
<script>
    new mdb.Select(document.getElementById('sku_newForm_selectField_supplier'), {
        container: 'body',
        size: 'sm',
    });
</script>
</body>

</html>