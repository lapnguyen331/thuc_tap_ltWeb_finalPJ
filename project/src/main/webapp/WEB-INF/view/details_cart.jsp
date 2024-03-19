<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Thư viện ngoài (Luôn để đầu tiên) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/fontawesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">

    <!-- Style của project -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixed-buttons.css">

    <title>Chi tiết giỏ hàng</title>
</head>
<body>
<main>
    <jsp:include page="/WEB-INF/view/shared/header.jsp"></jsp:include>
    <section id="cart-section">
        <div class="container p-3">
            <div class="row">
                <div class="col-md-6">
                    <div class="bg-gold bg-sharp-no-left">
                        <div class="title-primary"><i class="fa-solid fa-cart-arrow-down"></i> Giỏ hàng của bạn</div>
                    </div>
                </div>
            </div>
            <div class="row pt-2">
                <div class="col-md-12">
                    <table>
                        <thead>
                        <tr>
                            <th>Xóa</th>
                            <th>Sản phẩm</th>
                            <th>Giá khuyến mãi</th>
                            <th>Số lượng</th>
                            <th>Tạm tính</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="total" value="${0}"/>
                        <c:choose>
                            <c:when test="${empty requestScope.items}">
                                <tr>
                                    <td colspan="5">
                                        <div class="fw-semibold text-danger py-3">
                                            <i class="fa-solid fa-cart-shopping"></i>
                                            <span>Giỏ hàng của bạn hiện đang trống ...</span>
                                        </div>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:set var="instance" value="${requestScope.items[0].product}"/>
                                <c:forEach items="${requestScope.items}" var="item">
                                    <c:set var="product" value="${item.product}"/>
                                    <c:set var="quantity" value="${item.quantity}"/>
                                    <tr>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/cart?action=remove&id=${product.id}&quantity=${quantity}">
                                                <span class="delete-product"></span>
                                            </a>
                                        </td>
                                        <td>
                                            <div class="product-wrap">
                                                <div class="product-img">
                                                    <img src="${pageContext.request.contextPath}/files/${product.thumbnail.path}" alt="">
                                                </div>
                                                <div class="product-info">
                                                    <div class="product-name"><b>${product.name}</b></div>
                                                    <div class="product-stats">
                                                        <ul>
                                                            <li>
                                                                <span class="fw-semibold">Quy cách:</span>
                                                                <span>${product.specification}</span>
                                                            </li>
                                                            <li>
                                                                <span class="fw-semibold">Thương hiệu:</span>
                                                                <span><a href="${pageContext.request.contextPath}/search?branch=${product.brand}">${product.brand}</a></span>
                                                            </li>
                                                            <li>
                                                                <span class="fw-semibold">Danh mục:</span>
                                                                <a href="${pageContext.request.contextPath}/search?category=${product.category.id}">${product.category.name}</a>
                                                            </li>
                                                            <li>
                                                                <span class="fw-semibold">Giá gốc:</span>
                                                                <c:set var="price" value="${product.price}"/>
                                                                <span>${product['getStringPrice'](price)}₫</span>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <c:set var="discountPrice" value="${(100 - product.discount.discountPercent) * product.price / 100}"/>
                                        <c:set var="total" value="${total + discountPrice * quantity}" />
                                        <td>
                                            <div class="price">
                                                <span>${product['getStringPrice'](discountPrice)}₫</span>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="number-field">
                                                <div class="number-btn btn-left bg-gold bg-sharp-no-right">
                                                    <a href="${pageContext.request.contextPath}/cart?action=remove&id=${product.id}&quantity=1">
                                                        <span class="text-dark">&#xf068;</span>
                                                    </a>
                                                </div>
                                                <div class="bg-gold">
                                                    <input type="number" value="${quantity}" data-amount="${quantity}">
                                                </div>
                                                <div class="number-btn btn-right bg-gold bg-sharp-no-left">
                                                    <a href="${pageContext.request.contextPath}/cart?action=add&id=${product.id}&quantity=1">
                                                        <span class="text-dark">&#x2b;</span>
                                                    </a>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="price">
                                                <span>${product['getStringPrice'](quantity * discountPrice)}₫</span>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </div>
                <c:if test="${not empty requestScope.result}">
                    <div class="pt-2 col-md-12 fw-semibold text-info text-center">
                            ${requestScope.result}
                    </div>
                </c:if>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="total-wrap">
                        <div class="col">
                            <ul class="cart-guides">
                                <li class="guide">Nếu bạn đồng ý mua danh sách sản phẩm trên thì vui lòng bấm nút “Hoàn Tất đơn hàng”</li>
                                <li class="guide">Để tiếp tục mua thêm sản phẩm khác, bạn bấm nút “Tiếp tục mua sắm"</li>
                                <li class="guide">Để bỏ từng sản phẩm trong danh sách, bấm vào biểu tượng xóa phía bên trái hình sản phẩm</li>
                                <li class="guide">Để thêm số lượng cho một sản phẩm cần mua, bạn chọn số lượng</li>
                            </ul>
                        </div>
                        <div class="col text-center">
                            <div class="total-price d-inline-block">
                                <span>Tổng tiền</span>
                                <c:if test="${not empty instance}">
                                    <span class="price">${instance['getStringPrice'](total)}₫</span>
                                </c:if>
                                <c:if test="${empty instance}">
                                    <span class="price">0₫</span>
                                </c:if>
                            </div>
                            <div class="d-flex gap-3 justify-content-center">
                                <div class="bg-gold bg-sharp">
                                    <div class="title-primary">
                                        <a href="${pageContext.request.contextPath}/home" class="continue-buy">Tiếp tục mua sắm</a>
                                    </div>
                                </div>
                                <div class="bg-gold bg-sharp">
                                    <div class="title-primary">
                                        <a class="checkout" href="${pageContext.request.contextPath}/checkout">Hoàn tất đơn hàng</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<jsp:include page="/WEB-INF/view/shared/footer.jsp"></jsp:include>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous"
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0" nonce="Qoebijhj">
</script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</body>
</html>