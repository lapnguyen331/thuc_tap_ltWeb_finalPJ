<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Thư viện ngoài (Luôn để đầu tiên) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/fontawesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/swiper/swiper-bundle-min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5/css/mdb.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">

    <!-- Style của project -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-slider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/password-forgot-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixed-buttons.css">

    <title>Trang chủ</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/shared/header.jsp"></jsp:include>

<section id="fpass">
    <div class="container">
        <div class="form-box forgot">
            <h2>Quên mật khẩu</h2>
            <form action ="#">
                <div class="input-box">
                    <span class="icon">
                        <i class="fa-solid fa-envelope fa-sm"></i>
                    </span>
                    <input type="text" id="email" name="email" required>
                    <label for="email">Email:</label>
                </div>
                <button type="submit">Gửi yêu cầu</button>
            </form>
        </div>
    </div>
</section>


<jsp:include page="/WEB-INF/view/shared/footer.jsp"></jsp:include>

<div id="fb-root"></div>
<script async defer crossorigin="anonymous"
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0" nonce="Qoebijhj">
</script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/libs/swiper/swiper-bundle.min.js"></script>
<script>
    const swiper = new Swiper('.swiper', {
        // Optional parameters
        direction: 'horizontal',
        loop: true,

        // If we need pagination
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
            bulletActiveClass: 'custom-bullet-active',
            bulletClass: 'custom-bullet'
        },

        autoheight: true,
        grabCursor: true,
    });


</script>
</body>
</html>