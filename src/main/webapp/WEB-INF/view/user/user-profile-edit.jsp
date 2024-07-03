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
    <title>Sửa Hồ Sơ Người Dùng</title>
</head>
<body>
    <jsp:include page="/WEB-INF/view/shared/header.jsp"/>
    <section id="user-profile">
        <div class="user-profile-container">
            <div class="left-control">
                <div class="user-profile">
                    <a class="user-image" href="user-profile.jsp"></a> <!--NOTE : Link tới hồ sơ user-->
                    <div class="user-avatar">
                        <img class="avrtar-img" src="${pageContext.request.contextPath}/${userifor.avatar.path}" alt="">
                    </div>
                    <div class="user-account-name">
                        <div class="account-name">User1</div>
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
                            <a class="stardust-link" href="">
                                <img src="${pageContext.request.contextPath}/inventory/images/user-profile/account.png" alt="">
                                <span>Tài khoản của tôi</span>
                            </a> 
                        </div>
                        <div class="startdust-dropdown-item-body">
                            <div class="slitter">
                                <a class="body-down" href="${pageContext.request.contextPath}/user-profile">
                                    <span>Hồ sơ</span>
                                </a> <!--NOTE : link tới prof-->
                                <a class="body-down" href="${pageContext.request.contextPath}/user-profile">
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
                       
                    
                </div>
            </div>
            <div class="right-content">
                <section id="profile">
                    <div class="profile-wrapper">
                         <!-- head -->
                        <div class="profile-header">
                            <h1>Hồ sơ của tôi</h1>
                            <div>Sửa thông tin hồ sơ để bảo mật tài khoản</div>
                            <c:if test="${not empty message}">
                                <h3>Đã cập nhật thành công</h3>
                            </c:if>
                        </div>
                        <!-- content -->
                        <div class="profile-infor">
                            <div class="profile-infor-container">
                                <div class="profile-left-content">
                                    <form action="user-profile-edit" method="POST">
                                        <table class="profile-infor-detail">
                                            <!-- account -->
                                            <tr>
                                                <td class="label">
                                                    <label>Tên đăng nhập</label>
                                                </td>
                                                <td class="content-space">
                                                    <input type="text" name="usernamelogchange" placeholder="${userifor.username}" class="detail-content-input">
                                                </td>
                                            </tr>
                                            <!-- tên Kh -->
                                            <tr>
                                                <td class="label">
                                                    <label>Tên Khách Hàng</label>
                                                </td>
                                                <td class="content-space">
                                                    <div class="detail-content">
                                                        <input type="text" name="usernamechange" placeholder="${userifor.firstName} ${userifor.lastName}" class="detail-content-input">
                                                    </div>
                                                </td>
                                            </tr>
                                            <!-- email -->
                                            <tr>
                                                <td class="label">
                                                    <label>Email</label>
                                                </td>
                                                <td class="content-space">
                                                    <input name="useremail" type="text" placeholder="${userifor.email ==null?"nhập email":userifor.email}" >

                                                </td>
                                            </tr>
                                            <!-- số diện thoại -->
                                            <tr>
                                                <td class="label">
                                                    <label>Số điện thoại</label>
                                                </td>
                                                <td class="content-space">
                                                    <input name="userphone" type="text" placeholder="${userifor.phone == null ?"nhập số điện thoại":userifor.phone}" >
                                                   
                                                </td>
                                            </tr>
                                            <!-- địa chỉ -->
                                            <tr>
                                                <td class="label">
                                                    <label>Địa chỉ</label>
                                                </td>
                                                <td class="content-space">
                                                    <div class="detail-content">
                                                        <input type="text" name="usernameaddress" placeholder="${userifor.address}" class="detail-content-input">
                                                    </div>
                                                </td>
                                            </tr>

                                            <!-- giới tính -->
                                            <tr>
                                                <td class="label">
                                                    <label>Giới tính</label>
                                                </td>
                                                <td class="content-space">
                                                    <div class="detail-content">
                                                        <div class="radio-group" role="radiogroup">
                                                            <div class="radio-check">
                                                                <input class="form-check-input" value="q1" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                                                                <label class="form-check-label" for="flexRadioDefault1">
                                                                    Nam
                                                                </label>
                                                            </div>
                                                                <div class="radio-check">
                                                                    <input class="form-check-input" value="q2" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                                                                    <label class="form-check-label" for="flexRadioDefault1">
                                                                        Nữ
                                                                    </label>
                                                                </div>
                                                                <div class="radio-check" style="display: none">
                                                                    <input class="form-check-input" value="g3" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                                                                    <label class="form-check-label" for="flexRadioDefault1">
                                                                        Khác
                                                                    </label>
                                                                </div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <!-- Ngày sinh -->
                                            <tr>
                                                <td class="label">
                                                    <label>Ngày sinh: </label>
                                                </td>
                                                <td class="content-space">
                                                    <input type="date" name="birthday" value="${userifor.birthDate}" placeholder="${userifor.birthDate}"  />
                                                </td>
                                            </tr>
                                            <!-- Nút lưu -->
                                            <tr>
                                                <td class="label">
                                                    <label></label>
                                                </td>
                                                <td class="content-space submit-button-warpper">
                                                    <button class="change-content-but">Lưu</button>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                </div>
                                <div class="profile-right-content">
                                    <form action="">
                                        <div class="avartar-wrapper">
                                            <div class="profile-image" >
                                                <img class="DAM" src="${pageContext.request.contextPath}/${userifor.avatar.path}" alt="">
                                            </div>
                                            <input type="file"  id="profile-file-input" accept=".jpg,.JPEG,.png" )>
                                            <label for="profile-file-input" class="profile-submit-avartar">Chọn ảnh</label>
                                        </div>
                                        <div class="profile-avartar-image-rule">
                                            <div>Dung lượng file tối đa 1 MB</div>
                                            <div>Định dạng JPEG, PNG</div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <section id="adress"></section>
                <section id="account-password"></section>
                <section id="notify-modify"></section>


            </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/view/shared/footer.jsp"/>
    <div id="fb-root"></div>
    <script src="js/user/user-profile.js"></script>
    <script async defer crossorigin="anonymous" 
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0" nonce="Qoebijhj">
    </script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
    <!-- this page -->
    <script src="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.js"></script>
    <script src="${pageContext.request.contextPath}/js/user/user-profile-edit.js"></script>
    <script src="${pageContext.request.contextPath}/libs/daterangepicker-master/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/libs/DataTables/datatables.min.js"></script>

</body>
</html>