<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 1/19/2024
  Time: 7:53 PM
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">

    <!-- Style của project -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkout.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixed-buttons.css">


    <title>Xác nhận đơn hàng</title>
</head>
<body>
<%@ include file="/WEB-INF/view/shared/header.jsp" %>
<section id="checkout-section">
    <div class="container">
        <form action="checkout" method="post" id="checkout-form">
            <div class="row">
                <div class="col-md-12 d-flex justify-content-between px-3 py-3">
                    <div class="checkout-info-wrap">
                        <h3 class="checkout-title">Thông tin người nhận</h3>
                        <div class="bg-gold bg-sharp">
                            <div class="info-form ">
                                <div class="txt-field full-name-txt-field">
                                    <label for="txt-first-name">Họ *:</label>
                                    <input type="text" id="txt-first-name" name="receiverFirstName">
                                </div>
                                <div class="txt-field full-name-txt-field">
                                    <label for="txt-last-name">Tên *:</label>
                                    <input type="text" id="txt-last-name" name="receiverLastName">
                                </div>
                                <div class="txt-field email-txt-field">
                                    <label for="txt-email">Email *:</label>
                                    <input type="text" id="txt-email" name="receiverEmail">
                                </div>
                                <div class="txt-field phone-txt-field">
                                    <label for="txt-phone">Số điện thoại *:</label>
                                    <input type="text" id="txt-phone" name="receiverPhone">
                                </div>
                                <div class="txt-field address-txt-field">
                                    <label for="txt-address">Địa chỉ nhận *:</label>
                                    <input type="text" id="txt-address" name="receiverAddress">
                                </div>
                                <div class="txt-field note-txt-field">
                                    <label for="txt-note">Thông tin bổ sung (tùy chọn):</label>
                                    <textarea name="note" id="txt-note" rows="5" placeholder="Ghi chú về đơn hàng, ví dụ: thời gian hay chỉ dẫn địa điểm giao hàng chi tiết hơn"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="checkout-cart-wrap">
                        <h3 class="checkout-title">Đơn hàng</h3>
                        <div class="info-checkout">
                            <div class="cart-checkout-content">
                                <c:set var="currentPrice" value="${0}"/>
                                <c:set var="discountPrice" value="${0}"/>
                                <c:forEach var="item" items="${requestScope.items}">
                                    <c:set var="product" value="${item.product}"/>
                                    <c:set var="quantity" value="${item.quantity}"/>
                                    <div class="product">
                                        <div class="product-img">
                                            <img src="${pageContext.request.contextPath}/files/${product.thumbnail.path}" alt="">
                                            <span class="product-discount-badget shadow">- ${product.discount.discountPercent}%</span>
                                        </div>
                                        <div class="product-info">
                                            <div class="product-name">${product.name}</div>
                                            <div class="product-discount-wrap d-flex gap-2 align-items-center">
                                                <div class="fw-semibold text-nowrap">Khuyến mãi:</div>
                                                <div class="discount-box">
                                                    <input type="text" value="${product.discount.name}" readonly class="border-0 fw-semibold text-uppercase fst-italic bg-transparent">
                                                </div>
                                            </div>

                                            <div class="product-price has-discount">
                                                <span class="current_price">${product['getStringPrice'](product.price * quantity)}₫</span>
                                                <c:set var="currentPrice" value="${currentPrice + (product.price * quantity)}"/>
                                                <span class="new_price">${product['getStringPrice'](product['getDiscountPrice']() * quantity)}₫</span>
                                                <c:set var="discountPrice" value="${discountPrice + (product['getDiscountPrice']() * quantity)}"/>
                                            </div>
                                        </div>
                                        <div class="product-amount">
                                            <div class="checkout-text"><b>Số lượng</b>: ${quantity}</div>
                                        </div>
                                    </div>
                                </c:forEach>
<%--                                <div class="product">--%>
<%--                                    <span class="btn-product-remove"></span>--%>
<%--                                    <div class="product-img">--%>
<%--                                        <img src="image/cao-hong-sam-hong-seon-gold-hop-2-lo-250g-1.webp" alt="">--%>
<%--                                    </div>--%>
<%--                                    <div class="product-info">--%>
<%--                                        <div class="product-name">Cao hồng sâm Hong Seon Gold hộp 2 lọ 250g</div>--%>
<%--                                        <div class="product-discount-wrap d-flex gap-2 align-items-center">--%>
<%--                                            <div class="fw-semibold text-nowrap">Khuyến mãi:</div>--%>
<%--                                            <div class="fw-normal text-danger">Không có</div>--%>
<%--                                        </div>--%>
<%--                                        <div class="product-price">--%>
<%--                                            <span class="current_price">980.000₫</span>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                    <div class="product-amount">--%>
<%--                                        <div class="checkout-text"><b>Số lượng</b>: 1</div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
                            </div>
                            <div class="number-total-cart">
                                <span class="checkout-text">
                                    Tạm tính
                                </span>
                                <span class="amount">
                                        ${requestScope.items[0].product['getStringPrice'](currentPrice)}₫
                                </span>
                            </div>

                            <div class="number-total-cart">
                                    <span class="checkout-text">
                                        Thành tiền
                                    </span>
                                <span class="amount">
                                        ${requestScope.items[0].product['getStringPrice'](discountPrice)}₫
                                    </span>
                            </div>

                            <div class="call-action">
                                <span class="checkout-text">(Chưa bao gồm VAT)</span>
                                <div class="d-flex gap-2">
                                    <div class="bg-gold bg-sharp-5">
                                        <a href="${pageContext.request.contextPath}/template/cart.html" type="submit" class="action-btn">Quay lại</a>
                                    </div>
                                    <div class="bg-gold bg-sharp-5">
                                        <button type="submit" class="action-btn">Hoàn tất thanh toán</button>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${not empty requestScope.result}">
                                <div class="pt-2 fw-semibold text-danger text-center">
                                    ${requestScope.result}
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>
<%@ include file="/WEB-INF/view/shared/footer.jsp" %>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous"
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0" nonce="Qoebijhj">
</script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
</body>
</html>
