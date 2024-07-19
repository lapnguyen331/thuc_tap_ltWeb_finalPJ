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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/suneditor-master/dist/css/suneditor.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/flickity/flickity.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/details-product-template.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/image-uploader/image-uploader.min.css">

    <link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
    <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
    <script src="https://code.highcharts.com/maps/highmaps.js"></script>
    <script src="https://code.highcharts.com/maps/modules/exporting.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/dashboard.css?v1">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/DataTables/datatables.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin-modal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin-product.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <title>DashBoard</title>
</head>

<body>
<%@ include file="/WEB-INF/view/admin/shared/header.jsp" %>
<%@ include file="/WEB-INF/view/admin/shared/sidebar.jsp" %>

<section id="main-content-section">
    <div class="main-content">
            <div class="dashboard">
                <main>
                    <div class="header">
                        <div class="left">
                            <h1>Dashboard</h1>
                        </div>
                    </div>

                    <!-- Insights -->
                    <ul class="insights">
                        <li class="shadow">
                            <i class="fa-regular fa-calendar-check"></i>
                            <span class="info">
                        <h3>
                            1,074
                        </h3>
                        <p>Đơn hàng đã bán</p>
                    </span>
                        </li >
                        <li class="shadow"><i class="fa-regular fa-eye"></i>
                            <span class="info">
                        <h3>
                            3,944
                        </h3>
                        <p>Truy cập trang web</p>
                    </span>
                        </li>
                        <li class="shadow"><i class="fa-regular fa-user fa-sm"></i>
                            <span class="info">
                        <h3>
                            2,721
                        </h3>
                        <p>Người đăng ký</p>
                    </span>
                        </li>
                        <li class="shadow"><i class="fa-regular fa-dollar-sign"></i>
                            <span class="info">
                        <h3>
                            $6,742
                        </h3>
                        <p>Tổng doanh thu</p>
                    </span>
                        </li>
                    </ul>
                    <!-- End of Insights -->

                    <div class="bottom-data">
                        <div class="orders">
                            <div class="header">
                                <i cl   ass="fa-solid fa-file-invoice"></i>
                                <h3>Đơn hàng gần đây</h3>
                                <i class="fa-solid fa-filter"></i>
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </div>
                            <table>
                                <thead>.
                                <tr>
                                    <th>Mã đơn hàng</th>
                                    <th>Khách hàng</th>
                                    <th>Ngày tạo</th>
                                    <th>Tình trạng</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>
                                        <p>VZ120001</p>
                                    </td>
                                    <td>
                                        <p>Phạm Nhật Hùng</p>
                                    </td>
                                    <td>14-08-2023</td>
                                    <td><span class="status completed">Completed</span></td>
                                </tr>
                                <tr>
                                    <td>
                                        <p>VZ120002</p>
                                    </td>
                                    <td>
                                        <p>Phạm Nhật Hùng</p>
                                    </td>
                                    <td>14-08-2023</td>
                                    <td><span class="status pending">Pending</span></td>
                                </tr>
                                <tr>
                                    <td>
                                        <p>VZ120003</p>
                                    </td>
                                    <td>
                                        <p>Phạm Nhật Hùng</p>
                                    </td>
                                    <td>14-08-2023</td>
                                    <td><span class="status process">Processing</span></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <!-- Reminders -->
                        <div class="chart">
                            <canvas
                                    data-mdb-chart="line"
                                    data-mdb-dataset-label="Doanh thu"
                                    data-mdb-labels="['Thứ 2', 'Thứ 3' , 'Thứ 4' , 'Thứ 5' , 'Thứ 6' , 'Thứ  7' , 'Chủ nhật ']"
                                    data-mdb-dataset-data="[2112, 2343, 2545, 3423, 2365, 1985, 987]"
                            ></canvas>
                        </div>

                    </div>
                    <%--logs --%>
                    <div class="header">
                        <div class="left">
                            <h1>Báo cáo hệ thống</h1>
                        </div>
                    </div>
                    <div class="bottom-data container mt-5">
                        <div class="log-data">
                            <div class="header">
                                <h3>Báo cáo logs</h3>
                            </div>
                            <table id="logTable" class=" table  table-striped" style="width:100%">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Level</th>
                                    <th>IP</th>
                                    <th>Message</th>
                                    <th>Create At</th>
                                    <th></th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
<%--                    map visitor--%>
                    <div id="map">
                        <div id="container"></div>
                    </div>

                </main>
            </div>
    </div>
</section>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/flickity/flickity.pkgd.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/suneditor-master/dist/suneditor.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/image-uploader/image-uploader.js"></script>



<script src="${pageContext.request.contextPath}/js/admin/mapvisitor.js?v5"></script>

<script src="${pageContext.request.contextPath}/libs/DataTables/datatables.min.js"></script>

<script src="${pageContext.request.contextPath}/js/admin/data-log.js"></script>

<%--<script src="${pageContext.request.contextPath}/js/admin/modal.js"></script>--%>
</body>

</html>