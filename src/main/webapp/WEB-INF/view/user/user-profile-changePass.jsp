<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Thư viện ngoài (Luôn để đầu tiên) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/fontawesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">

    <!-- Style của project -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <!-- this page -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/user-profile.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/user-profile-changePass.css">

    <title>Thay Đổi Mật Khẩu</title>
</head>
<body>
    <jsp:include page="/WEB-INF/view/shared/header.jsp"/>
    
    <section id="user-profile">
        <div class="user-profile-container">
            <div class="left-control">
                <div class="user-profile">
                    <a class="user-image" href="user-profile.jsp"></a> <!--NOTE : Link tới hồ sơ user-->
                    <div class="user-avatar">
                        <img class="avrtar-img" src="${pageContext.request.contextPath}/files/${userifor.avatar.path}" alt="">
                    </div>
                    <div class="user-account-name">
                        <div class="account-name">${userifor.username}</div>
                        <div>
                            <a class="user-profile-modify" href="${pageContext.request.contextPath}/user-profile-edit">  <!--NOTE: thiếu link tới profile của user-->
                                <i class="fa-solid fa-pen"></i>
                                Sửa Hồ Sơ
                            </a>
                        </div>
                       
                    </div>
                </div>
                <div class="left-controller-but">
                    <!-- tài khoản cũa tôi -->
                    <div class="account-height startdust-dropdown">
                        <div class="startdust-dropdown-item-header">
                            <a class="stardust-link" >
                                <img src="${pageContext.request.contextPath}/inventory/images/user-profile/account.png" alt="">
                                <span>Tài khoản của tôi</span>
                            </a> 
                        </div>
                        <div class="startdust-dropdown-item-body">
                            <div class="slitter">
                                <a class="body-down" href="${pageContext.request.contextPath}/user-profile">
                                    <span>Hồ sơ</span>
                                </a> <!--NOTE : link tới prof-->
                                <a class="body-down" href="${pageContext.request.contextPath}/user-profile-changePass">
                                    <span>Đổi mật khẩu</span>
                                </a> 
                                <a class="body-down" href="${pageContext.request.contextPath}/user-profile-notification-modify" style="display: none">
                                    <span>Cài đặt thông báo</span>
                                </a> 

                            </div>
                        </div>

                    </div>
                     <!-- Đơn mua -->
                     <div class="startdust-dropdown--open">
                        <div class="startdust-dropdown-item-header">
                            <a class="stardust-link" href="${pageContext.request.contextPath}/user-profile-order">
                                <img src="${pageContext.request.contextPath}/inventory/images/user-profile/donmua-icon.png" alt="">
                                <span>Đơn Mua</span>
                            </a> <!--NOTE: link tới profile-->
                        </div>
                    </div>
                     <!-- thông báo-->
                     <div class="startdust-dropdown--open" style="display: none">
                        <div class="startdust-dropdown-item-header">
                            <a class="stardust-link" href="${pageContext.request.contextPath}/user-profile-notification">
                                <img src="${pageContext.request.contextPath}/inventory/images/user-profile/thongbao-icon.png" alt="">
                                <span>Thông Báo</span>
                            </a> <!--NOTE: link tới profile-->
                        </div>
                    </div>
                       <!-- Khuyến mãi-->
                       <div class="startdust-dropdown--open" style="display: none">
                        <div class="startdust-dropdown-item-header">
                            <a class="stardust-link" href="${pageContext.request.contextPath}/user-profile-discount">
                                <img src="${pageContext.request.contextPath}/inventory/images/user-profile/voucher-icon.png" alt="">
                                <span>Khuyến Mại</span>
                            </a> <!--NOTE: link tới profile-->
                        </div>
                    </div>
                </div>
            </div>
            <div class="right-content">
                <section id="profile">
                    <div class="profile-wrapper">
                         <!-- head -->
                        <div class="profile-header">
                            <h1>Hồ sơ của tôi</h1>
                            <div>Thay đổi mật khẩu bảo mật tài khoản</div>
                        </div>
                        <!-- content -->
                        <c:if test="${not empty requestScope.message}">
                            <h3 style="color: red">${requestScope.message}</h3>
                        </c:if>
                        <form action="user-profile-changePass" method="post">
                        <div class="user-profile-change-pass">
                            <div class="user-profile-change-pass-wrapper">
                                <div class="user-prfile-change-pass-label-left">
                                    <label for="#em">Tên đăng nhập</label>
                                    <label for="#e">Mật khẩu</label>
                                </div>
                                <div class="user-profile-change-pass-input-right">
                                        <input type="text" name="cusername" >
                                        <input type="password" name="cpassword">
                                </div>
                            </div>
                            <div class="user-profile-submit-pass">
                                <input type="submit" value="Lưu">
                            </div>
                        </div>
                        </form>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/view/shared/footer.jsp"/>
    <div id="fb-root"></div>
    <script src="js/user/user-profile.js"></script>
    <script async defer crossorigin="anonymous" 
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0" nonce="Qoebijhj">
    </script>
    <script src="js/jquery.js"></script>
    <script src="libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
</body>
</html>