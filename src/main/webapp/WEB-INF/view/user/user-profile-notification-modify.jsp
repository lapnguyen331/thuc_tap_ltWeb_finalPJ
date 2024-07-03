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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/user-profile-notify-modify.css">
    <title>Cài Đặt Thông Báo</title>
</head>
<body>
    <jsp:include page="/WEB-INF/view/shared/header.jsp"/>
    <section id="user-profile">
        <div class="user-profile-container">
            <div class="left-control">
                <div class="user-profile">
                    <a class="user-image" href="user-profile.jsp"></a> <!--NOTE : Link tới hồ sơ user-->
                    <div class="user-avatar">
                        <img class="avrtar-img" src="${pageContext.request.contextPath}/inventory/images/user-profile/use-avatar-header-default.jpg" alt="">
                    </div>
                    <div class="user-account-name">
                        <div class="account-name">User1</div>
                        <div>
                            <a class="user-profile-modify" href="user-profile-edit.jsp">  <!--NOTE: thiếu link tới profile của user-->
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
                            <a class="stardust-link" href="user-profile.jsp">
                                <img src="${pageContext.request.contextPath}/inventory/images/user-profile/account.png" alt="">
                                <span>Tài khoản của tôi</span>
                            </a> 
                        </div>
                        <div class="startdust-dropdown-item-body">
                            <div class="slitter">
                                <a class="body-down" href="user-profile.jsp">
                                    <span>Hồ sơ</span>
                                </a> <!--NOTE : link tới prof-->
                                <a class="body-down" href="user-profile-changePass.jsp">
                                    <span>Đổi mật khẩu</span>
                                </a> 
                                <a class="body-down" href="user-profile-notification-modify.html">
                                    <span>Cài đặt thông báo</span>
                                </a> 

                            </div>
                        </div>

                    </div>
                     <!-- Đơn mua -->
                     <div class="startdust-dropdown--open">
                        <div class="startdust-dropdown-item-header">
                            <a class="stardust-link" href="user-profile-order.jsp">
                                <img src="${pageContext.request.contextPath}/inventory/images/user-profile/donmua-icon.png" alt="">
                                <span>Đơn Mua</span>
                            </a> <!--NOTE: link tới profile-->
                        </div>
                    </div>
                     <!-- thông báo-->
                     <div class="startdust-dropdown--open">
                        <div class="startdust-dropdown-item-header">
                            <a class="stardust-link" href="user-profile-notification.jsp">
                                <img src="${pageContext.request.contextPath}/inventory/images/user-profile/thongbao-icon.png" alt="">
                                <span>Thông Báo</span>
                            </a> <!--NOTE: link tới profile-->
                        </div>
                    </div>
                       
                   
                </div>
            </div>
            <div class="right-content">
                <section id="profile-user-notify-modify">
                    <div class="profile-wrapper">
                         <!-- head -->
                        <div class="profile-header">
                            <h1>Cài đặt thông báo</h1>
                        </div>
                        <!-- content -->
                        <div class="user-profile-notify-modify">
                           <div class="user-notify-modify-wrapper">
                            <div class="notify-modify-left">
                                <div class="notify-modify-label">
                                    <label for="">Email thông báo</label>
                                    <span class="nottify-midify-des">Gửi cho tôi tất cảthông báo về email</span>
                                </div>
                                <div class="notify-modify-label">
                                    <label for="">Cập nhật đơn hàng</label>
                                    <span class="nottify-midify-des">Gửi cho tôi khi có cập nhật về đơn hàng</span>

                                </div>
                                <div class="notify-modify-label">
                                    <label for="">Nội dung cá nhân</label>
                                    <span class="nottify-midify-des">Gửi cho tôi thông báo khi có đăng nhập mới vào tài khoản này</span>
                                </div>
                            </div>
                            <div class="notify-modify-right">
                                <div class="notify-modify-tickbox">
                                    <input type="checkbox" class="notify-checkbox">
                                     <span>Kích hoạt</span>                   
                                </div>
                                <div class="notify-modify-tickbox">
                                    <input type="checkbox" class="notify-checkbox">
                                     <span>Kích hoạt</span>                   
                                </div>
                                <div class="notify-modify-tickbox">
                                    <input type="checkbox" class="notify-checkbox" aria-checked="false">
                                     <span>Kích hoạt</span>                   
                                </div>
                            </div>
                           </div>
                        </div>
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
    <!-- this page -->
</body>
</html>