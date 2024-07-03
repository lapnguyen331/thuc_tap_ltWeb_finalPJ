<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/4/2023
  Time: 12:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-featured-products_v01.css">
</head>
<body>
    <div class="row gy-md-4 pt-3 featured-row mb-3">
        <!-- Title -->
        <div class="col-12 d-flex justify-content-center">
            <div class="bg-gold bg-sharp">
              <div class="title-primary">Sản phẩm bán chạy</div>
            </div>
        </div>
        <c:if test="${not empty requestScope.products}">
            <c:forEach var="product" items="${requestScope.products}">
                <div class="col-md-3">
                    <div class="product-card">
                        <a href="#">
                            <div class="product-img">
                                <img src="${product.avatar.url}" alt="">
                            </div>

                            <div class="product-name">
                                <p>${product.name}</p>
                            </div>
                        </a>
                        <div class="product-price-wrapper">
                            <div class="discount-label">-${product.discount}%</div>
                            <div class="price">
                                <p class="m-price">${product['getRealPrice']()}</p>
                                <p class="c-price">${product['getRealDiscountPrice']()}</p>
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
                                    <a href="/template/details_product.html" class="move-to-details">Xem chi tiết</a>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty requestScope.products}">
            <div class="col-md-12 d-flex justify-content-center">
                <div class="text-danger">Chưa có sản phẩm nào...</div>
            </div>
        </c:if>
    </div>
</body>
</html>
