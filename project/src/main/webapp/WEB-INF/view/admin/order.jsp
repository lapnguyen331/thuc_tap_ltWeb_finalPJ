<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 1/24/2024
  Time: 8:18 PM
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/details-product-template.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/image-uploader/image-uploader.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/DataTables/datatables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.css">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin-modal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin-orders.css?v=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-override.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datatables-override.css?v=1">
    <title>Quản lí sản phẩm</title>
</head>

<body>
<%@ include file="/WEB-INF/view/admin/shared/header.jsp" %>
<%@ include file="/WEB-INF/view/admin/shared/sidebar.jsp" %>
<section id="main-content-section">
    <div class="main-content">
        <div class="order-control">
            <div class="container rounded-2 pb-3 shadow">
                <div class="row">
                    <div class="row justify-content-between">
                        <div class="col-md-3 ps-0">
                            <div class="bg-gold bg-sharp-no-left">
                                <div class="bg-primary-red-main-color px-3 py-1">
                                    <div class="fw-bold text-wheat-color fs-5 text-center">Quản lí đơn hàng</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-auto p-0 py-2">
                            <a href="${pageContext.request.contextPath}/admin/orders_edit.html" class="btn btn-success px-3 py-2" data-mdb-ripple-duration="0s">
                                <div class="fw-semibold fs-6">
                                    <i class="fa-solid fa-plus"></i>
                                    Thêm hóa đơn
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <form id="table_filters">
                            <div class="form-outline input-field">
                                <i class="fa-solid fa-magnifying-glass"></i>
                                <input type="text" class="form-control" id="filter_input" placeholder="Theo tên khách hàng...">
                            </div>
                            <div class="form-outline input-field">
                                <input type="text" class="form-control" id="date_picker" placeholder="Theo ngày tháng" value="">
                            </div>
                            <div class="select-field">
                                <select id="status_filter">
                                    <option value="" selected>Tất cả tình trạng</option>
                                    <option value="0">Đã bị hủy</option>
                                    <option value="1">Đã giao</option>
                                    <option value="2">Đang giao</option>
                                    <option value="3">Đang trả về</option>
                                </select>
                                <select id="table_length_filter" class="select">
                                    <option value="10">Hiển thị 10 dòng</option>
                                    <option value="20">Hiển thị 20 dòng</option>
                                    <option value="50">Hiển thị 50 dòng</option>
                                    <option value="100">Hiển thị 100 dòng</option>
                                </select>
                            </div>
                            <div class="submit-field bg-gold rounded align-self-lg-stretch">
                                <button type="submit" class="d-flex align-items-center">
                                    <div><i class="fa-solid fa-sliders"></i></div>
                                    <span>Tìm kiếm</span>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
<%--                <div class="row">--%>
<%--                    <div class="col-md-12">--%>
<%--                        <nav id="table_tabs">--%>
<%--                            <ul>--%>
<%--                                <li class="active">--%>
<%--                                    <i class="fa-solid fa-warehouse"></i>--%>
<%--                                    <span>Tất cả đơn</span>--%>
<%--                                </li>--%>
<%--                                <li>--%>
<%--                                    <i class="fa-regular fa-circle-check"></i>--%>
<%--                                    <span>Đã được giao</span>--%>
<%--                                </li>--%>
<%--                                <li>--%>
<%--                                    <i class="fa-solid fa-rotate-right"></i>--%>
<%--                                    <span>Đang xử lí</span>--%>
<%--                                </li>--%>
<%--                                <li>--%>
<%--                                    <i class="fa-solid fa-truck-fast"></i>--%>
<%--                                    <span>Đang giao</span>--%>
<%--                                </li>--%>
<%--                                <li>--%>
<%--                                    <i class="fa-solid fa-right-left"></i>--%>
<%--                                    <span>Đang trả về</span>--%>
<%--                                </li>--%>
<%--                                <li>--%>
<%--                                    <i class="fa-regular fa-circle-xmark"></i>--%>
<%--                                    <span>Đã bị hủy</span>--%>
<%--                                </li>--%>
<%--                            </ul>--%>
<%--                        </nav>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="row">
                    <div class="col-md-12">
                        <table id="table_orders" style="width: 100%">
                            <thead>
                            <tr>
                                <th>Mã đơn hàng</th>
                                <th>Khách hàng</th>
                                <th>Tổng thu</th>
                                <th>SĐT</th>
                                <th>Email</th>
                                <th>Tình trạng</th>
                                <th>Ngày tạo</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="my-modal">
    </div>
    <div class="overlay"></div>
</section>
<!-- Modal -->
<div class="modal fade" id="admin-product-modal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title">
                    <div class="admin-modal-tabs active">
                        <button class="tab active" data-modal-tab="#mdal-tab-setting">
                            <i class="fa-solid fa-sliders"></i>
                            <span>Chỉnh sửa thông tin</span>
                        </button>
                        <button class="tab" data-modal-tab="#mdal-tab-details">
                            <i class="fa-regular fa-pen-to-square"></i>
                            <span>Bài viết mô tả</span>
                        </button>
                    </div>
                </div>
                <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="modal-content">
                    <div class="container">
                        <div class="row tab-content show" id="mdal-tab-setting">
                            <form action="" method="post" class="form-product-setting" enctype="multipart/form-data">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-outline">
                                            <i class="fa-solid fa-hashtag trailing"></i>
                                            <input type="text" id="input_id_product" class="form-control" maxlength="10" minlength="10" readonly/>
                                            <label class="form-label" for="input_id_product">Mã sản phẩm</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-outline">
                                            <input type="text" id="input_name_product" class="form-control" />
                                            <label class="form-label" for="input_name_product">Tên sản phẩm</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-outline">
                                            <i class="trailing">VNĐ</i>
                                            <input type="text" id="input_price_product" class="form-control" />
                                            <label class="form-label" for="input_price_product">Giá bán</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <select class="select" id="select_category_product">
                                            <!-- JavaScript ở chèn option vào đây -->
                                        </select>
                                        <label class="form-label select-label">Danh mục sản phẩm</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-outline">
                                            <input type="text" id="input_specific_product" class="form-control"/>
                                            <label class="form-label" for="input_specific_product">Quy cách</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-outline">
                                            <input type="text" id="input_weight_product" class="form-control"/>
                                            <label class="form-label" for="input_weight_product">Trọng lượng (gram)</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-outline autocomplete" id="async-brands">
                                            <input type="text" id="input_brand_product" class="form-control"/>
                                            <label class="form-label" for="input_brand_product">Thương hiệu</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-outline">
                                            <input type="text" id="input_remains_product" class="form-control"/>
                                            <label class="form-label" for="input_remains_product">Tồn kho</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-outline">
                                            <input type="text" id="input_age_product" class="form-control"/>
                                            <label class="form-label" for="input_age_product">Độ tuổi nhỏ nhất</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <select class="select" id="select_producers_product">
                                            <!-- JavaScript chèn option vào đây -->
                                        </select>
                                        <label class="form-label select-label">Đơn vị sản xuất</label>
                                    </div>
                                </div>
                                <div class="form-outline">
                                    <textarea class="form-control" id="txtArea_desc_product" rows="4"></textarea>
                                    <label class="form-label" for="txtArea_desc_product">Mô tả sản phẩm</label>
                                </div>
                                <label for="images_thumbnail_product" class="form-label">Hình ảnh đại diện</label>
                                <div class="input-images" id="images_thumbnail_product"></div>
                                <label for="images_gallery_product" class="form-label">Hình ảnh thư viện</label>
                                <div class="input-images" id="images_gallery_product"></div>
                                <button type="submit" class="btn-confirm-update">Cập nhập thông tin sản phẩm</button>
                            </form>
                        </div>
                        <div class="row tab-content" id="mdal-tab-details">
                            <div class="blog">
                                <div id="sample">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal top fade" id="confirm-modal" tabindex="-1" aria-hidden="true" data-mdb-backdrop="static" data-mdb-keyboard="false">
    <div class="modal-dialog modal-sm  modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Lưu thay đổi</h5>
            </div>
            <div class="modal-body">Bạn có chắc muốn lưu thay đổi bài viết?</div>
            <div class="modal-footer">
                <button type="button" class="btn-confirm-cancel" data-mdb-dismiss="modal">
                    <i class="fa-solid fa-xmark"></i>
                    <span>Hủy bỏ</span>
                </button>
                <button type="button" class="btn-confirm-changed" data-mdb-dismiss="modal">
                    <span>Lưu</span>
                    <i class="fa-solid fa-floppy-disk"></i>
                </button>
            </div>
        </div>
    </div>
</div>
<!-- Template Preview Bài viết -->
<!-- <section id="details-product">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="tabs">
                    <div class="tablinks tablinks-is-active" data-tab-target="#tab-product-content">Mô tả sản phẩm</div>
                    <div class="tablinks"> Địa chỉ mua hàng</div>
                    <div class="tablinks"> Đánh giá sản phẩm</div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="tab-content-wrapper">
                    <section class="tab-content tab-is-active" id="tab-product-content">
                        {{contents}}
                    </section>
                </div>
            </div>
        </div>
    </div>
</section> -->
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin/sidebar.js"></script>
<script src="${pageContext.request.contextPath}/libs/DataTables/datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/daterangepicker-master/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/admin/order.js?v=3"></script>
</body>

</html>