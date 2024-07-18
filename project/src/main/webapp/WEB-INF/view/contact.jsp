<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Thư viện ngoài (Luôn để đầu tiên) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/fontawesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5/css/mdb.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">

    <!-- Style của project -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contact.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixed-buttons.css">

    <title>Liên hệ</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/shared/cart.jsp"></jsp:include>

<jsp:include page="/WEB-INF/view/shared/header.jsp"></jsp:include>

    <section id="contact-section">
        <div class="container">
            <form action="contact" method="post">
                <div class="row px-3 py-3">
                    <div class="col-md-6">
                        <div class="contact-info-wrap">
                            <h3 class="contact-title">Thông tin liên hệ</h3>
                            <div class="bg-gold bg-sharp">
                                <div class="info-form ">
                                    <div class="txt-field last-name-txt-field">
                                        <label for="txt-last-name">Họ *:</label>
                                        <input type="text" name="firstname" id="txt-last-name">
                                    </div>
                                    <div class="txt-field name-txt-field">
                                        <label for="txt-name">Tên *:</label>
                                        <input type="text" name="lastname" id="txt-name">
                                    </div>
                                    <div class="txt-field phone-txt-field">
                                        <label for="txt-phone">Số điện thoại *:</label>
                                        <input type="text" name="submitphone" id="txt-phone">
                                    </div>
                                    <div class="txt-field email-txt-field">
                                        <label for="txt-email">Email *:</label>
                                        <input type="text" name="submitemail" id="txt-email">
                                    </div>
                                    <div class="txt-field note-txt-field">
                                        <label for="txt-note">Nội dung *:</label>
                                        <textarea name="note" id="txt-note" rows="5"></textarea>
                                    </div>
                                    <div class="bg-gold bg-sharp d-inline-block">
                                        <button type="submit" class="action-btn">Liên hệ cho tôi</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h3 class="contact-title">Hồng sâm hàn quốc Kgin</h3>
                        <div class="contact-info-wrap">
                            <div class="contact-h">Công ty TNHH Thương mại dịch vụ KGIN:</div>
                            <div class="contact-p"><b>Mã số thuế:</b> 03172843xxx</div>
                            <div class="contact-p"><b>SĐT Liên hệ:</b> 1900.1234</div>
                            <div class="contact-p"><b>Email liên hệ:</b> nqat0919@gmail.com</div>
                            <div class="contact-h">Kênh bán hàng online được bảo trợ độc quyền</div>
                            <div class="contact-p"><b>&#xf590;</b> Tư vấn online 24/7</div>
                            <div class="contact-p">&#xf017; Thời gian mở cửa từ 8h - 21h</div>
                            <div class="contact-h">Chi nhánh tại Hồ Chí Minh:</div>
                            <div class="contact-p">&#xf14e; Showroom 1: Số 51 Võ Thị Sáu, P. Võ Thị Sáu, Quận 3, TP.HCM</div>
                            <div class="contact-h">Chi nhánh tại Hà Nội:</div>
                            <div class="contact-p">&#xf14e; Showroom 1: 21 Tây Sơn – Đống Đa – Hà Nội</div>
                            <div class="contact-p">&#xf14e; Showroom 2: 577 Nguyễn Trãi – Thanh Xuân – Hà Nội ( Đối diện Pico Trung Văn)</div>
                            <div class="contact-p">&#xf14e; Showroom 3: 121A – Phố Huế – Hai Bà Trưng – Hà Nội</div>
                            <div class="contact-p">&#xf14e; Showroom 4: 149 Cầu Giấy – Hà Nội</div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <jsp:include page="/WEB-INF/view/shared/footer.jsp"></jsp:include>

    <div id="fb-root"></div>
    <script async defer crossorigin="anonymous" 
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0" nonce="Qoebijhj">
    </script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/checkout.js"></script>
</body>
</html>