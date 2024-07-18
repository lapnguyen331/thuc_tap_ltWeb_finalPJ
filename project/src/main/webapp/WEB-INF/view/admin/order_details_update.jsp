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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/image-uploader/image-uploader.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/DataTables/datatables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.css">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin-orders-edit.css?v=3">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-override.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datatables-override.css">
    <title>Quản lí sản phẩm</title>
</head>

<body>
<%@ include file="/WEB-INF/view/admin/shared/header.jsp" %>
<%@ include file="/WEB-INF/view/admin/shared/sidebar.jsp" %>
<input type="hidden" value="${requestScope.order.id}" id="order-item">
<section id="main-content-section">
    <div class="main-content">
        <section id="product_edit">
            <form action="${pageContext.request.contextPath}/admin/order/update" method="post" id="order-form">
                <input type="hidden" value="${requestScope.order.id}" name="orderId" id="orderId">
                <div class="container">
                    <div class="row">
                        <input type="hidden" name="items" id="order-items">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="left-container">
                                    <section id="order-info">
                                        <h4 class="form-title pb-3">Thông tin đơn hàng</h4>
                                        <h6>Mã đơn hàng</h6>
                                        <h5>#${requestScope.order.id}</h5>
                                        <label for="txt_date" class="d-block py-2 fw-semibold">Ngày tạo đơn *</label>
                                        <div class="form-outline">
                                            <input type="text" class="form-control" placeholder="Ngày tạo đơn" id="txt_date" disabled value="${requestScope.order['getDateTimeCreateAt']()}">
                                        </div>
                                        <label for="select_order_date" class="d-block py-2 fw-semibold">Tình trạng đơn hàng</label>
                                        <select name="status" class="select" id="select_order_date">
                                            <c:forEach var="i" begin="0" end="3">
                                                <option value="${i}" ${requestScope.order.status == i ? 'selected="selected"' : ''}>${requestScope.status[i]}</option>
                                            </c:forEach>
                                        </select>
                                    </section>
                                    <section id="customer-info">
                                        <h4 class="form-title pb-3">Thông tin chủ đơn</h4>
                                        <div class="input-group">
                                            <div id="search-autocomplete" class="form-outline">
                                                <input type="search" id="username" class="form-control" disabled value="${requestScope.user.username}"/>
                                                <input type="hidden" id="userid" value="${requestScope.user.id}">
                                                <label class="form-label" for="username">Tên tài khoản</label>
                                            </div>
                                            <button type="button" class="btn btn-primary d-flex align-items-center gap-2 px-2 text-wheat-color fw-semibold" disabled>
                                                <i class="fa-solid fa-arrows-rotate"></i>
                                                <span class="text-nowrap">Tải dữ liệu</span>
                                            </button>
                                        </div>
                                        <hr>
                                        <div class="customer-wrapper">
                                            <div class="avatar">
                                                <img id="user-avatar" src="${pageContext.request.contextPath}/image/customer-male-demo.png" width="100%" alt="">
                                            </div>
                                            <div class="customer-info">
                                                <div class="customer-name fw-semibold" id="user-fullname">Tên người dùng...</div>
                                                <div class="customer-role">Khách hàng</div>
                                            </div>
                                        </div>
                                        <div class="customer-contact">
                                            <ul>
                                                <li>
                                                    <i class="fa-regular fa-envelope"></i>
                                                    <span>${requestScope.user.firstName} ${requestScope.user.lastName}</span>
                                                </li>
                                                <li>
                                                    <i class="fa-solid fa-phone-volume"></i>
                                                    <span>${requestScope.user.phone}</span>
                                                </li>
                                            </ul>
                                        </div>
                                    </section>
                                </div>
                            </div>
                            <div class="col-md-9">
                                <div class="right-container">
                                    <section id="products-list-section">
                                        <c:if test="${not empty requestScope.message}">
                                            <div class="fw-semibold text-info text-center">
                                                ${requestScope.message}
                                            </div>
                                        </c:if>
                                        <h4 class="form-title">Lựa chọn sản phẩm</h4>
                                        <h6 class="form-title">Thêm sản phẩm vào đơn hàng này</h6>
                                        <div class="products-range">
                                        </div>
                                        <h4 class="form-title">Tổng tiền: <span id="total-price">0</span>₫</h4>
                                        <hr class="horizontal">
                                        <div class="filter-input">
                                            <i class="fa-solid fa-magnifying-glass"></i>
                                            <input type="text" id="search-data" placeholder="Tìm kiếm sản phẩm">
                                        </div>
                                        <table id="products_filter_table" class="display" style="width:100%">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Sản phẩm</th>
                                                <th>Tồn kho</th>
                                            </tr>
                                            </thead>
                                        </table>
                                    </section>
                                    <section id="order-details-section">
                                        <h4 class="form-title">Thông tin đơn hàng</h4>
                                        <h6 class="form-title">Nhập thông tin người nhận vào trường dưới đây</h6>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="input-field">
                                                    <label for="receiverName">Họ và tên *</label>
                                                    <input type="text" name="receiverName" placeholder="Họ và tên" value="${requestScope.order.receiverName}" id="receiverName">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="input-field">
                                                    <label for="receiverEmail">Email *</label>
                                                    <input type="text" name="receiverEmail" placeholder="Email" id="receiverEmail" value="${requestScope.order.receiverEmail}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row mt-3">
                                            <div class="col-md-6">
                                                <div class="input-field">
                                                    <label for="receiverPhone">Số điện thoại *</label>
                                                    <input type="text" name="receiverPhone" placeholder="Số điện thoại" value="${requestScope.order.receiverPhone}" id="receiverPhone">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="input-field">
                                                    <label for="receiverAddress">Địa chỉ nhận hàng *</label>
                                                    <input type="text" name="receiverAddress" placeholder="Địa chỉ" value="${requestScope.order.receiverAddress}" id="receiverAddress">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row mt-3">
                                            <div class="col-md-12">
                                                <div class="input-field">
                                                    <label for="txtArea_note">Ghi chú (tùy chọn)</label>
                                                    <textarea name="" id="txtArea_note" rows="5"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="actions-field">
                            <button type="submit" class="update-btn">Cập nhật thông tin</button>
                        </div>
                    </div>
                </div>
            </form>
        </section>
    </div>
</section>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin/sidebar.js"></script>
<script src="${pageContext.request.contextPath}/libs/DataTables/datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/daterangepicker-master/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/libs/DataTables/datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin/order-edit.js?v=26"></script>
</body>
</html>