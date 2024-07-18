<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin-contacts.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-override.css">
    <title>Quản lí liên hệ</title>
</head>

<body>
<%@ include file="/WEB-INF/view/admin/shared/header.jsp" %>
<%@ include file="/WEB-INF/view/admin/shared/sidebar.jsp" %>

    <section id="main-content-section">
        <div class="main-content">
            <div class="contacts-control">
                <div class="container rounded-2">
                    <div class="row">
                        <div class="row justify-content-between">
                            <div class="col-auto ps-0">
                                <div class="bg-gold bg-sharp-no-left">
                                    <div class="bg-primary-red-main-color px-3 py-1">
                                        <div class="fw-bold text-wheat-color fs-5 text-center">Quản lí liên hệ</div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-auto p-0 py-2">
                                <a href="${pageContext.request.contextPath}/#admin_product_modal"
                                   class="btn btn-success px-3 py-2"
                                   data-mdb-ripple-duration="0s">
                                    <div class="fw-semibold fs-6">
                                        <span>Thêm liên hệ</span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form id="table_filters">
                                <div class="form-outline input-field">
                                    <i class="fa-solid fa-magnifying-glass"></i>
                                    <input type="text" class="form-control" id="filter_input" placeholder="Theo tên người gửi, SĐT, email và tương tự ...">
                                </div>
                                <div class="form-outline input-field">
                                    <input type="text" class="form-control" id="date_picker" placeholder="Theo thời gian tạo" value="">
                                </div>
                                <div class="select-field">
                                    <select id="status_filter">
                                        <option value="none" disabled selected>Theo tình trạng</option>
                                        <option value="true">Đã giải quyết</option>
                                        <option value="false">Chưa giải quyết</option>
                                    </select>
                                    <select id="table_length_filter" class="select">
                                        <option value="10">Hiển thị 10 dòng</option>
                                        <option value="20">Hiển thị 20 dòng</option>
                                        <option value="50">Hiển thị 50 dòng</option>
                                        <option value="100">Hiển thị 100 dòng</option>
                                    </select>
                                </div>
                                <div class="submit-field">
                                    <button type="submit">
                                        <div><i class="fa-solid fa-sliders"></i></div>
                                        <span>Tìm kiếm</span>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <table id="table_contacts" style="width: 100%">
                                <thead>
                                    <tr>
                                        <th>id</th>
                                        <th>họ</th>
                                        <th>tên</th>
                                        <th>điện thoại</th>
                                        <th>email</th>
                                        <th>trạng thái</th>
                                        <th>nội dung</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Modal -->
    <div class="modal fade" id="admin_product_modal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Yêu cầu liên lạc</h5>
                    <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="d-flex flex-column">
                        <div class="d-flex gap-2 justify-content-between align-items-start contacts-header">
                        </div>
                        <hr class="hr hr-blurry" />
                        <div class="contacts-content p-2 border border-primary rounded"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
<%--    <script src="${pageContext.request.contextPath}/js/admin/sidebar.js"></script>--%>
    <script src="${pageContext.request.contextPath}/libs/daterangepicker-master/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/libs/DataTables/datatables.min.js"></script>
    <script src="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.js"></script>
    <script src="${pageContext.request.contextPath}/libs/DataTables/Select-1.7.0/js/dataTables.select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/admin/contacts.js?v=gfg"></script>
</body>

</html>