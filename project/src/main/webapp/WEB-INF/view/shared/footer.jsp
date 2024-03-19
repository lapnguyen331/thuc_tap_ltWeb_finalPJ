<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/29/2023
  Time: 6:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-footer.css">
</head>
<body>
    <footer id="main-footer-section">
    <div class="footer-guide">
        <div class="guide-title">thông tin liên hệ</div>
        <div class="guide-title">hệ thống cửa hàng</div>
        <div class="guide-title">danh mục sản phẩm</div>

        <div class="guide-content">
            <div class="contact-wrap">
                <i class="fa-solid fa-phone"></i>
                <span>Hotline: 1900.1234</span>
            </div>
            <div class="contact-wrap">
                <i class="fa-regular fa-comment-dots"></i>
                <span>Zalo, Sms: 0936.565.257</span>
            </div>
            <div class="contact-wrap">
                <i class="fa-regular fa-envelope"></i>
                <span>Email: nqat0919@gmail.com</span>
            </div>
            <strong>Mua hàng trực tiếp tại cửa hàng</strong>
            <div class="contact-wrap">
                <i class="fa-regular fa-clock"></i>
                <span>Thời gian mở cửa: 8h - 21h</span>
            </div>
            <strong>Phương thức thanh toán</strong>
            <img src="${pageContext.request.contextPath}/inventory/images/payment.webp" alt="" style="width: 400px;">
        </div>
        <div class="guide-content">
            <strong>Tại Hà Nội:</strong>
            <ul class="address-list">
                <li class="address">Cửa hàng 1: 21 Tây Sơn - Quan Trung- Đống Đa - Hà Nội</li>
                <li class="address">Cửa hàng 2: 121A Phố Huế - Hai Bà Trưng - Hà Nội (Ngã tư Phố Huế - Tuệ Tĩnh)</li>
                <li class="address">Cửa hàng 3: 149 Cầu Giấy - Quan Hoa - Cầu Giấy - Hà Nội</li>
                <li class="address">Cửa hàng 4: 577 Nguyễn Trãi - Thanh Xuân Nam - Thanh Xuân - Hà Nội ( Đối diện Pico Trung Văn)</li>
            </ul>
            <strong>Tại Hồ Chí Minh:</strong>
            <ul class="address-list">
                <li class="address">Cửa hàng 5: 51 Võ Thị Sáu - Phường VTS - Quận 3 - Thành phố Hồ Chí Minh</li>
            </ul>
        </div>
        <div class="guide-content">
            <c:if test="${not empty sessionScope.categories}">
                <c:forEach var="category" items="${sessionScope.categories}">
                    <div class="portfolio-product">
                        <div class="product-img">
                            <img src="${pageContext.request.contextPath}/files/${category.thumbnail.path}" alt="${category.thumbnail.uuid}">
                        </div>
                        <div class="product-name">
                            <a href="${pageContext.request.contextPath}/categogy-detail?id=${category.id}">${category.name}</a> <!---Note sửa lại href-->
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
    <div class="footer-contact">
        <div class="contact-title">
            <ul class="title-list">
                <li class="title-item">Trang chủ</li>
                <li class="title-item">Tin tức</li>
                <li class="title-item">Liên hệ</li>
            </ul>
        </div>

        <div class="contact-title">
            <strong>Mua sắm tiện lợi</strong>
        </div>

        <div class="contact-title">
            <strong>Fanpage Facebook</strong>
        </div>

        <div class="contact-content">
            <div class="contact-info-wrapper">
                <div class="img-logo" >
                    <img src="${pageContext.request.contextPath}/inventory/icons/logo.png" alt="">
                </div>
                <div class="contact-info-content">
                    <p class="info-text">sâm hàn quốc Cheong Kwan Jang</p>
                    <div class="contact-wrap">
                        <i class="fa-solid fa-phone"></i>
                        <span>Hotline: 1900.1234</span>
                    </div>
                    <div class="contact-wrap">
                        <i class="fa-regular fa-comment-dots"></i>
                        <span>Zalo, Sms: 0936.565.257</span>
                    </div>
                    <div class="contact-wrap">
                        <i class="fa-regular fa-envelope"></i>
                        <span>Email: nqat0919@gmail.com</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="contact-content">
            <div class="intro-wrapper">
                <div class="intro-col">
                    <div class="contact-wrap">
                        <i class="fas fa-edit" aria-hidden="true"></i>
                        <a href="#">Hướng dẫn mua hàng</a>
                    </div>
                    <div class="contact-wrap">
                        <i class="fas fa-map" aria-hidden="true"></i>
                        <a href="#">Bản đồ đến showroom</a>
                    </div>
                    <div class="contact-wrap">
                        <i class="fab fa-cc-visa" aria-hidden="true"></i>
                        <a href="details_product.html">Thông tin hàng hóa chi tiết</a>
                    </div>
                    <div class="contact-wrap">
                        <i class="fas fa-calendar-check" aria-hidden="true"></i>
                        <a href="privacy.html#chinh_sach_bao_hanh_doi_tra_va_hoan_tien">Quy định về việc đổi trả hàng</a>
                    </div>
                </div>
                <div class="intro-col">
                    <div class="contact-wrap">
                        <i class="fas fa-money-bill" aria-hidden="true"></i>
                        <a href="privacy.html">Thanh toán khi nhận hàng</a>
                    </div>
                    <div class="contact-wrap">
                        <i class="fas fa-truck" aria-hidden="true"></i>
                        <a href="#">Giao hàng toàn quốc</a>
                    </div>
                    <div class="contact-wrap">
                        <i class="fas fa-thumbs-up" aria-hidden="true"></i>
                        <a href="#">Nhân sâm Hàn Quốc uy tín</a>
                    </div>
                    <div class="contact-wrap">
                        <i class="fas fa-check-circle" aria-hidden="true"></i>
                        <a href="#">Dịch vụ CSKH chu đáo</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="contact-content">
            <div class="fb-page"
                 data-href="https://www.facebook.com/kcntt.nlu"
                 data-width="400"
                 data-hide-cover="false"
                 data-show-facepile="false">
            </div>
        </div>
    </div>
    <div class="copy-right">
        <span>Copyright &copy; 2023 Đồ án lập trình web</span>
    </div>
</footer>
</body>
</html>
