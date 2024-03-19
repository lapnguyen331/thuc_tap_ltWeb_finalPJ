<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 1/7/2024
  Time: 2:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Thư viện ngoài (Luôn để đầu tiên) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/fontawesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/swiper/swiper-bundle-min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">

    <!-- Style của project -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-card.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css?v=2">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixed-buttons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/breadcrumbs.css">

    <title>Trang chủ</title>
</head>
<body>
<%@ include file="/WEB-INF/view/shared/cart.jsp" %>
<main>
    <%@ include file="/WEB-INF/view/shared/header.jsp" %>
    <!-- Section tìm kiếm sản phẩm -->
    <section id="search-products-section">
        <div class="breadcrumbs-wrap">
            <ul class="breadcrumbs">
                <li><a href="${pageContext.request.contextPath}/">Trang chủ</a></li>
                <li><a href="${pageContext.request.contextPath}/search.html" class="current">Kết quả tìm kiếm cho "<span>${requestScope.searchName}</span>"</a></li>
            </ul>
        </div>

        <div class="container">
            <div class="row">
                <!-- Section Product Filter -->
                <div class="col-3">
                    <section id="section-category-sidebar">
                        <div class="filter-header bg-sharp bg-gold">
                            <div class="title-primary">Danh mục sản phẩm</div>
                        </div>
                        <div class="filter-content shadow">
                            <ul class="categories-wrap">
                                <a href="${pageContext.request.contextPath}/categogy?id=1">
                                    <li>Nước hồng sâm</li>
                                </a>
                                <a href="${pageContext.request.contextPath}/categogy?id=2">
                                    <li>Cao Hồng Sâm</li>
                                </a>
                                <a href="${pageContext.request.contextPath}/categogy?id=3">
                                    <li>Sâm Khô</li>
                                </a>
                                <a href="${pageContext.request.contextPath}/categogy?id=4">
                                    <li>Sâm Mật Ong</li>
                                </a>
                                <a href="${pageContext.request.contextPath}/categogy?id=5">
                                    <li>
                                        Hắc Sâm
                                    </li>
                                </a>
                                <a href="${pageContext.request.contextPath}/categogy?id-6">
                                    <li>
                                        Nhân Sâm Tươi
                                    </li>
                                </a>
                                <a href="${pageContext.request.contextPath}/categogy?id=7">
                                    <li>
                                        Rượu Sâm Linh Chi
                                    </li>
                                </a>
                                <a href="${pageContext.request.contextPath}/categogy?id=8">
                                    <li>
                                        Trà Hồng Sâm
                                    </li>
                                </a>
                                <a href="${pageContext.request.contextPath}/categogy?id=9">
                                    <li>
                                        Viên Hồng Sâm
                                    </li>
                                </a>
                            </ul>
                        </div>
                    </section>
                </div>
                <div class="col-9">
                    <!-- Danh mục kết quả tìm kiếm -->
                    <section class="searched-product">
                        <div class="row">
                            <div class="col-12">
                                <div class="banner-wrap bg-gold p-1 shadow bg-sharp">
                                    <div class="img-wrap">
                                        <img src="${pageContext.request.contextPath}/inventory/images/footer-bg.jpg" alt="">
                                    </div>
                                    <div class="banner-title">Kết quả tìm kiếm cho từ khóa: "
                                        <div class="search-value">
                                            ${requestScope.searchName}
                                        </div>
                                        "
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row product-show-content d-flex flex-column gap-3">
                            <c:forEach var="entry" items="${requestScope.products}">
                                <c:set var="list" value="${entry.value}"></c:set>
                                <div class="row">
                                    <c:forEach var="product" items="${list}">
                                        <div class="col-4">
                                            <div class="product-card">
                                                <a href="#">
                                                    <div class="product-img">
                                                        <img src="${pageContext.request.contextPath}/files/${product.thumbnail.path}" alt="">
                                                    </div>

                                                    <div class="product-name">
                                                        <p>${product.name}</p>
                                                    </div>
                                                </a>
                                                <div class="product-price-wrapper">
                                                    <div class="discount-label">-${product.discount.discountPercent}%</div>
                                                    <div class="price">
                                                        <c:set var="price" value="${product.price}"/>
                                                        <c:set var="discountPrice" value="${(100 - product.discount.discountPercent) * product.price / 100}"/>
                                                        <p class="m-price">${product['getStringPrice'](price)}</p>
                                                        <p class="c-price">${product['getStringPrice'](discountPrice)}</p>
                                                    </div>
                                                </div>
                                                <div class="product-button">
                                                    <div class="bg-gold bg-sharp-5">
                                                        <button class="btn-store-cart" data-cart-product="true" data-cart-action="add" data-cart-id="${product.id}">
                                                            Thêm vào giỏ
                                                        </button>
                                                    </div>
                                                    <div class="bg-gold bg-sharp-5">
                                                        <button class="btn-details">
                                                            <a href="${pageContext.request.contextPath}/product?id=${product.id}" class="move-to-details">Xem chi tiết</a>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:forEach>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </section>
</main>
<jsp:include page="/WEB-INF/view/shared/footer.jsp"/>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous"
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0" nonce="Qoebijhj">
</script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/libs/swiper/swiper-bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
<%--<script src="${pageContext.request.contextPath}/js/products-cards-loading.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/search-page.js"></script>--%>
</body>
</html>