<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Thư viện ngoài (Luôn để đầu tiên) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/fontawesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <!-- css this page -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/catagogy.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-featured-products_v01.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-card.css">
    <!-- filter this page -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-filter.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixed-buttons.css">



    <title>Document</title>
</head>
<body style="background-color: #f7f0e6;">
<jsp:include page="/WEB-INF/view/shared/cart.jsp"></jsp:include>

<!-- Header -->
<jsp:include page="/WEB-INF/view/shared/header.jsp"></jsp:include>

<!-- Sam han quoc-->
    <!-- breadcrum -->
    <div class="views-breadcrumb">
        <div class="breadcrumb p-0">
            <div class="container">
                <p id="breadcrumbs">
                    <span>
                        <a href="">Trang chủ </a>
                     » 
                    </span>
                    <span>
                        <a class="breadcrumb_last" aria-current="page">Catagogy</a>
                    </span>
                </p>
            </div>
        </div>
    </div>
    <!-- content sam han quoc -->
    <div id="product-page">
        <div class="product-categogy-container">
            <div class="row">    
                <!-- bên trái catagogy -->
                <div id="fill-product" class="col-lg-3" >
                    <!-- catagogy -->
                    <section id="section-product-filter">
                        <div class="filter-header bg-rounded bg-gold">
                            <div class="title-primary">Phân loại sản phẩm</div>
                        </div>
                        <div class="filter-content">
                            <form action="">
                                <p class="filter-title">Lọc theo tên</p>
                                <ul class="radio-filter">
                                    <li>
                                        <label for="price_0-1m">Nước Hồng Sâm Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_1-2.5m">Cao Hồng Sâm Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_2.5m-5m">Sâm Mật Ong Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_5m-7.5m">Nhân Sâm Tươi Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_7.5m-10m">Trà Hồng Sâm Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_7.5m-10m">Kẹo Sâm Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_7.5m-10m">Viên Hồng Sâm Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_7.5m-10m">Hắc Sâm Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_7.5m-10m">Sâm Khô Hàn Quốc</label>
                                    </li>
                                </ul>
                            </form>
                        </div>
                    </section>
                    <!-- Section Product Filter -->
                    <section id="section-product-filter">
                        <div class="filter-header bg-rounded bg-gold">
                            <div class="title-primary">Lọc sản phẩm</div>
                        </div>
                        <div class="filter-content">
                            <form action='${pageContext.request.contextPath}/search' method="get" >
                                <p class="filter-title">Lọc theo giá</p>
                                <ul class="radio-filter">
                                    <li>
                                        <input type="radio" name="range" value="0-1000000" id="price_0-1m">
                                        <label for="price_0-1m">< 1 triệu</label>
                                    </li>
                                    <li>
                                        <input type="radio" name="range" value="1000000-2500000" id="price_1-2.5m">
                                        <label for="price_1-2.5m">1 triệu - 2.5 triệu</label>
                                    </li>
                                    <li>
                                        <input type="radio" name="range" value="2500000-5000000" id="price_2.5m-5m">
                                        <label for="price_2.5m-5m">2.5 triệu - 5 triệu</label>
                                    </li>
                                    <li>
                                        <input type="radio" name="range" value="5000000-7500000" id="price_5m-7.5m">
                                        <label for="price_5m-7.5m">5 triệu - 7.5 triệu</label>
                                    </li>
                                    <li>
                                        <input type="radio" name="range" value="7500000-10000000" id="price_7.5m-10m">
                                        <label for="price_7.5m-10m">7.5 triệu - 10 triệu</label>
                                    </li>
                                </ul>
                                <p class="filter-title">Theo thương hiệu</p>
                                <div class="filter-type-select">
                                    <select name="brand" id="select-brand">
                                        <option value="" disabled selected>(Bất kì thương hiệu nào)</option>
                                        <c:forEach items="${requestScope.brands}" var="brand">
                                            <option value="${brand}">${brand}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <p class="filter-title">Theo danh mục</p>
                                <div class="filter-type-select">
                                    <select name="category" id="select-category">
                                        <option value="" disabled selected>(Bất kì danh mục nào)</option>
                                        <c:forEach var="category" items="${requestScope.categories}">
                                            <option value="${category.id}">${category.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <p class="filter-title">Theo tên sản phẩm</p>
                                <div class="filter-type-input-text">
                                    <input type="text" name="name" id="text-name" placeholder="Để trống cho tên bất kì">
                                </div>
                                <input type="submit" value="Tìm kiếm sản phẩm" >
                            </form>
                        </div>
                    </section>
                </div> <!--NOTEME: chưa thêm bộ lọc-->
                <div class="col-lg-9">
                    <!-- banner -->
                    <div id="banner">
                        <div class="banner-wrapper">
                            <img src="${pageContext.request.contextPath}/inventory/images/catagogy/test-banner-product-samhanquoc.html.jpg" alt=""> <!---image banner-->
                        </div>
                    </div>
                    <!-- sec sp 1 catagogy nước hồng sâm-->
<%--                    <c:if test="${not empty requestScope.productsCate}">--%>
                        <c:set var="names" value="${requestScope.cateName}"/>
                        <c:forEach var="entry" items="${requestScope.productsCate}">
                            <c:set var="name" value="${names[entry.key]}" scope="page"/>
                            <c:set var="productlist" value="${entry.value}" scope="page"/>
                            <section id="cat-1">
                                <div class="product-list">
                                <!-- title -->
                                <div class="categogy-title">
                                    <div class="row d-flex justify-content-center">
                                        <div class="col-12">
                                            <div class="bg-gold bg-sharp">
                                                <div class="cata-header d-flex">
                                                    <a href="${pageContext.request.contextPath}/categogy-detail?id=${entry.key}">${name}</a> <!---NOTE dẩn link đến danh mục sp-->
                                                    <a href="${pageContext.request.contextPath}/categogy-detail?id=${entry.key}">Xem tất cả</a> <!--NOTE dẫn link đến ctsp-->
                                                </div>

                                            </div>
                                        </div>

                                    </div>
                                    <div class="row justify-content-center"> <!--bọc các product-->
                                        <div class="col-12 col-sm-12 col-md-12 col-lg-12 row grib-product">
                                            <!--product 1-->
                                            <c:forEach var="product" items="${productlist}">
                                                <div class="col-12 col-md-4 col-lg-4 pb-4">
                                                    <div class="product-show-wrap">
                                                        <a rel="nofollow" href="${pageContext.request.contextPath}/product?id=${product.getId()}" data-wpel-link="internal"></a> <!--NOTE chưa thêm link đến chi tiết sản phẩm này-->
                                                        <div class="thumbnail">
                                                            <img src="${pageContext.request.contextPath}/files/${product.thumbnail.path}" alt="${product.name}">
                                                        </div>
                                                        <div class="product-name">
                                                            <h4 class="h3">${product.name}</h4>
                                                        </div>
                                                        <div class="row product-infor">
                                                            <div class="col-4 col-md-4 pr-0 col-lg-4 discount">
                                                                <div class="discount-percent d-flex">
                                                                    <span>-${product['getRoundPrice'](product.discount.discountPercent)}</span>
                                                                    <span class="symbol">%</span>
                                                                </div>
                                                            </div>
                                                            <div class="col-8 col-md-8 col-lg-8 pl-2">
                                                                <div class="price">
                                                                    <del>
                                                                        <c:set var="price" value="${product.price}"/>
                                                                        <c:set var="discountPrice" value="${(100 - product.discount.discountPercent) * product.price / 100}"/>
                                                                        <span class="woocommerce-Price-amount amount price pr-2">${product['getStringPrice'](price)}
                                                                            <span class="woocommerce-Price-currencySymbol">đ</span>
                                                                        </span>

                                                                    </del>
                                                                    <bdi>
                                                                            ${product['getStringPrice'](discountPrice)}
                                                                        <span class="woocommerce-Price-currencySymbol">đ</span>
                                                                    </bdi>
                                                                </div>
                                                            </div>

                                                        </div>
                                                        <div class="row m-0 pb-3">
                                                            <div class="col-6 col-md-6 col-lg-6 left">
                                                                <button class="quick-view mb-0 kgin-sharp-5 " data-cart-product="true" data-cart-action="add" data-cart-id="${product.id}" data-cart-amount="1">
                                                                    <div class="btn-wrap kgin-sharp-5 ">
                                                                        <i class="fa-solid fa-cart-plus"></i>
                                                                        <span>  Thêm giỏ hàng</span>
                                                                    </div>
                                                                </button>
                                                            </div>
                                                            <div class="col-6 col-md-6 col-lg-6 right">
                                                                <a rel="nofollow" href="${pageContext.request.contextPath}/product?id=${product.id}" data-wpel-link="internal"><!--NOTE link tới sp chi tiết-->
                                                                    <div class="detail-product btn-kgin kgin-sharp-5">
                                                                        <div class="btn-wrap kgin-sharp-5">
                                                                            <span>Xem chi tiết</span>
                                                                            <i class="fa-solid fa-angles-right xemicon"></i>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                         </div>
                                    </div>
                                </div>
                            </div>
                            </section>
                        </c:forEach>
<%--                    </c:if>--%>

                </div>
            </div>
            <!-- sp bán chạy -fearture product -->
            <section id="home-featured-products-section">
                 <div class="container">
                    <div class="row gy-md-4 pt-3">
                        <!-- Title -->
                        <div class="col-12 d-flex justify-content-center head-sec">
                            <div class="bg-gold bg-sharp">
                                <div class="title-primary">Sản phẩm bán chạy</div>
                            </div>
                        </div>
                        <c:forEach var="em" items="${requestScope.product3}">
                            <!-- Col 1 -->
                            <div class="col-md-3">
                                <div class="product-card">
                                    <a href="${pageContext.request.contextPath}/product?id=${em.id}">
                                        <div class="product-img">
                                            <img src="${pageContext.request.contextPath}/files/${em.thumbnail.path}" alt="">
                                        </div>

                                        <div class="product-name">
                                            <p>${em.name}</p>
                                        </div>
                                    </a>
                                    <div class="product-price-wrapper">
                                        <div class="discount-label">${em['getRoundPrice'](em.discount.discountPercent)}%</div>
                                        <div class="price">
                                              <c:set var="price1" value="${em.price}"/>
                                                <c:set var="discountPrice1" value="${(100 - em.discount.discountPercent) * em.price / 100}"/>
                                            <p class="m-price">${em['getStringPrice'](price1)}</p>
                                            <p class="c-price">${em['getStringPrice'](discountPrice1)}</p>
                                        </div>
                                    </div>
                                    <div class="product-button">
                                        <div class="bg-gold bg-sharp-5">
                                            <button class="btn-review">
                                                Xem nhanh
                                            </button>
                                        </div>
                                        <div class="bg-gold bg-sharp-5">
                                            <button class="btn-details" href="${pageContext.request.contextPath}/product?id=${em.id}">
                                                <a href="${pageContext.request.contextPath}/product?id=${em.id}">Xem chi tiết </a>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </section>
        </div>
    </div>
    

       <!-- Footer -->
<jsp:include page="/WEB-INF/view/shared/footer.jsp"/>
    <div id="fb-root"></div>
    <script async defer crossorigin="anonymous" 
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0" nonce="Qoebijhj">
    </script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
</body>
</html>
</body>
</html>