<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css?a=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-slider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login-style.css">
    <title>Trang đăng nhập</title>
</head>
<body>
<%@include file="/WEB-INF/view/shared/header.jsp" %>
<section id="login">
    <div class="container">
        <div class="form-box login">
            <h2>Đăng nhập</h2>
            <form id="login-form" method="post">
                <div class="input-box">
                <span class="icon">
                  <i class="fa-solid fa-user fa-sm"></i>
                </span>
                    <input type="text" id="username" name="username">
                    <label>Tài khoản hoặc Email</label>
                </div>

                <div class="input-box">
                <span class="icon" id="toggle-login-password">
                  <i class="fa-solid fa-eye cursor-pointer"></i>
                  <i class="fa-solid fa-lock fa-sm"></i>
                </span>
                    <input type="password" id="password" name="password">
                    <label>Mật khẩu</label>
                </div>

                <div class="remember-forgot">
                    <label><input type="checkbox">Lưu mật khẩu</label>
                    <a href="${pageContext.request.contextPath}/forgotpassword">Quên mật khẩu?</a>
                </div>
                <button type="submit" class="btn d-flex align-items-center justify-content-center gap-2">
                    <span>Đăng nhập</span>
                </button>
                <div class="d-flex justify-content-center" id="login-status">
                    <div class="text-danger fw-light"></div>
                </div>
                <div class="icons">
                    <p>Hoặc đăng nhập với:</p>
                    <a href="#" class="icons-btn">
                        <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="20" height="20" viewBox="0,0,256,256">
                            <g fill="#2f539b" fill-rule="nonzero" stroke="none" stroke-width="1" stroke-linecap="butt" stroke-linejoin="miter" stroke-miterlimit="10" stroke-dasharray="" stroke-dashoffset="0" font-family="none" font-weight="none" font-size="none" text-anchor="none" style="mix-blend-mode: normal"><g transform="scale(8.53333,8.53333)"><path d="M12,27v-12h-4v-4h4v-2.148c0,-4.067 1.981,-5.852 5.361,-5.852c1.619,0 2.475,0.12 2.88,0.175v3.825h-2.305c-1.435,0 -1.936,0.757 -1.936,2.291v1.709h4.205l-0.571,4h-3.634v12z"></path></g></g>
                        </svg></a>
                    <a href="#" class="icons-btn">
                        <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="20" height="20" viewBox="0 0 48 48">
                            <path fill="#FFC107" d="M43.611,20.083H42V20H24v8h11.303c-1.649,4.657-6.08,8-11.303,8c-6.627,0-12-5.373-12-12c0-6.627,5.373-12,12-12c3.059,0,5.842,1.154,7.961,3.039l5.657-5.657C34.046,6.053,29.268,4,24,4C12.955,4,4,12.955,4,24c0,11.045,8.955,20,20,20c11.045,0,20-8.955,20-20C44,22.659,43.862,21.35,43.611,20.083z"></path><path fill="#FF3D00" d="M6.306,14.691l6.571,4.819C14.655,15.108,18.961,12,24,12c3.059,0,5.842,1.154,7.961,3.039l5.657-5.657C34.046,6.053,29.268,4,24,4C16.318,4,9.656,8.337,6.306,14.691z"></path><path fill="#4CAF50" d="M24,44c5.166,0,9.86-1.977,13.409-5.192l-6.19-5.238C29.211,35.091,26.715,36,24,36c-5.202,0-9.619-3.317-11.283-7.946l-6.522,5.025C9.505,39.556,16.227,44,24,44z"></path><path fill="#1976D2" d="M43.611,20.083H42V20H24v8h11.303c-0.792,2.237-2.231,4.166-4.087,5.571c0.001-0.001,0.002-0.001,0.003-0.002l6.19,5.238C36.971,39.205,44,34,44,24C44,22.659,43.862,21.35,43.611,20.083z"></path>
                        </svg>
                    </a>
                </div>
                <div class = "login-register">
                    <p>Bạn chưa có tài khoản? <a href="${pageContext.request.contextPath}/signup" class="register-link">Đăng ký ngay!</a>
                    </p>
                </div>
            </form>
        </div>
    </div>
</section>
<jsp:include page="/WEB-INF/view/shared/footer.jsp"></jsp:include>
<div id="fb-root"></div>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script async defer crossorigin="anonymous"
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0" nonce="Qoebijhj">
</script>
<script src="${pageContext.request.contextPath}/js/login-page_v06.js"></script>
<script src="${pageContext.request.contextPath}/libs/swiper/swiper-bundle.min.js"></script>
<script>
    $(document).ready(function() {
        let swiper = new Swiper('.swiper', {
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

        document.getElementById('toggle-login-password').addEventListener('click', function() {
            var passwordField = document.getElementById('password');
            var eyeIcon = this.querySelector('i');

            if (passwordField.type === 'password') {
                passwordField.type = 'text';
                eyeIcon.classList.remove('fa-eye');
                eyeIcon.classList.add('fa-eye-slash');
            } else {
                passwordField.type = 'password';
                eyeIcon.classList.remove('fa-eye-slash');
                eyeIcon.classList.add('fa-eye');
            }
        });
    });
</script>
</body>
</html>
