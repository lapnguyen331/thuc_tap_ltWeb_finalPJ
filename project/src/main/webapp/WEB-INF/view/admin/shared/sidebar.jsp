<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css?a=2">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/sidebar.css?v=2">
    <title>Header</title>
</head>
<body>
<nav id="sidebar-nav" class="close">
    <div class="sidebar close">
        <div class="logo-details">
            <i class="fa-brands fa-shopify"></i>
            <span class="logo_name">Thanh quản lý</span>
        </div>
        <ul class="nav-links">
            <li class="nav-link-page">
                <a href="${pageContext.request.contextPath}/admin/dashboard.html">
                    <i class="fa-solid fa-table-cells-large"></i>
                    <span class="link_name">Điều khiển</span>
                </a>
                <ul class="sub-menu">
                    <li><a class="link_name" href="${pageContext.request.contextPath}/admin/dashboard.html">Điều khiển</a></li>
                </ul>
            </li>
            <li class="nav-link-page">
                <div class="icon-link">
                    <a href="${pageContext.request.contextPath}/admin/categories.html">
                        <i class="fa-solid fa-layer-group"></i>
                        <span class="link_name">Danh mục</span>
                    </a>
                    <i class="fa-solid fa-angle-down arrow"></i>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="${pageContext.request.contextPath}/admin/categories.html">Danh mục</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/categories_edit.html">Thêm danh mục</a></li>
                </ul>
            </li>
            <li class="nav-link-page">
                <div class="icon-link">
                    <a href="${pageContext.request.contextPath}/admin/products.html">
                        <i class="fa-solid fa-box"></i>
                        <span class="link_name">Sản phẩm</span>
                    </a>
                    <i class="fa-solid fa-angle-down arrow"></i>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="${pageContext.request.contextPath}/admin/products.html">Sản phẩm</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/products_edit.html">Thêm sản phẩm</a></li>
                </ul>
            </li>
            <li class="nav-link-page">
                <div class="icon-link">
                    <a href="${pageContext.request.contextPath}/admin/discounts.html">
                        <i class="fa-solid fa-tags"></i>
                        <span class="link_name">Khuyến mãi</span>
                    </a>
                    <i class="fa-solid fa-angle-down arrow"></i>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="${pageContext.request.contextPath}/admin/discounts.html">Khuyến mãi</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/discounts.html">Thêm khuyến mãi</a></li>
                </ul>
            </li>
            <li class="nav-link-page">
                <div class="icon-link">
                    <a href="${pageContext.request.contextPath}/admin/customers.html">
                        <i class="fa-solid fa-user-group"></i>
                        <span class="link_name">Khách hàng</span>
                    </a>
                    <i class="fa-solid fa-angle-down arrow"></i>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="${pageContext.request.contextPath}/admin/customers.html">Khách hàng</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/customers_edit.html">Thêm khách hàng</a></li>
                </ul>
            </li>
            <li class="nav-link-page">
                <div class="icon-link">
                    <a href="${pageContext.request.contextPath}/admin/producers.html">
                        <i class="fa-solid fa-warehouse"></i>
                        <span class="link_name">Nhà cung cấp</span>
                    </a>
                    <i class="fa-solid fa-angle-down arrow"></i>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="${pageContext.request.contextPath}/admin/producers.html">Nhà cung cấp</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/producers_edit.html">Thêm nhà cung cấp</a></li>
                </ul>
            </li>
            <li class="nav-link-page">
                <div class="icon-link">
                    <a href="${pageContext.request.contextPath}/admin/blogs.html">
                        <i class="fa-solid fa-book"></i>
                        <span class="link_name">Bài viết</span>
                    </a>
                    <i class="fa-solid fa-angle-down arrow"></i>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="${pageContext.request.contextPath}/admin/blogs.html">Bài viết</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/blog-edit.html">Thêm bài viết</a></li>
                </ul>
            </li>
            <li class="nav-link-page">
                <div class="icon-link">
                    <a href="${pageContext.request.contextPath}/admin/orders.html">
                        <i class="fa-solid fa-clipboard"></i>
                        <span class="link_name">Đơn hàng</span>
                    </a>
                    <i class="fa-solid fa-angle-down arrow"></i>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="${pageContext.request.contextPath}/admin/orders.html">Đơn hàng</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/orders_edit.html">Thêm đơn hàng</a></li>
                </ul>
            </li>
            <li class="nav-link-page">
                <a href="${pageContext.request.contextPath}/admin/contacts.html">
                    <i class="fa-solid fa-headset new-info">
                        <span>1</span>
                    </i>
                    <span class="link_name">Liên hệ</span>
                </a>
                <ul class="sub-menu">
                    <li><a class="link_name" href="${pageContext.request.contextPath}/admin/contacts.html">Liên hệ</a></li>
                </ul>

            </li>
            <!-- <li class="nav-link-page">
                <a href="#">
                    <i class="fa-solid fa-bell"></i>
                    <span class="link_name">Thông báo</span>
                </a>
                <ul class="sub-menu">
                    <li><a class="link_name" href="#">Thông báo</a></li>
                </ul>
            </li> -->
            <li class="nav-link-page">
                <div class="profile-details">
                    <div class="profile-content">
                        <img src="${pageContext.request.contextPath}/icon/ship-toan-quoc.jpg" alt="profileImg">
                    </div>
                    <div class="name-job">
                        <div class="greeting">Xin chào Admin</div>
                        <div class="profile_name">Nguyễn Tuấn</div>
                    </div>
                    <i class='bx bx-log-out'></i>
                </div>
            </li>
        </ul>
        <div id="btn-toggle-sidebar">
            <i class="fa-solid fa-angles-right"></i>
        </div>
    </div>
</nav>
</body>
</html>