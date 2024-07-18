<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Thư viện ngoài (Luôn để đầu tiên) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/fontawesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/image-uploader/image-uploader.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/suneditor-master/dist/css/suneditor.min.css">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin-customers-edit.css">
    <title>Quản lí sản phẩm</title>
    <style> 
        #input-images .iui-cloud-upload {
            font-family: 'Font Awesome 6 Free' !important;
            font-size: 60px;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #dce5f1;
        }

        #input-images .iui-cloud-upload::before {
            content: '\f302';
        }

        #input-images .upload-text span {
            display: none;
        }

        #input-images {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        #input-images .image-uploader {
            width: 150px;
            height: 150px;
            border: none;
            box-shadow: 0 0.5rem 1.5rem 0.5rem rgba(0, 0, 0, 0.075);
            border-radius: 10px;
            overflow: hidden;
            margin: 10px;
        }

        #input-images .uploaded {
            display: flex;
            padding: 0;
            align-items: center;
            justify-content: center;
            height: 100%;
            width: 100%;
            padding: 10px;
        }

        #input-images .uploaded .uploaded-image {
            width: 100%;
            height: 100%;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 0;
            background: none;
            margin: 0;
        }

        #input-images .uploaded .uploaded-image img {
            object-fit: cover;
            display: inline-block;
            position: static;
        }
    </style>
</head>

<body>
<%@ include file="/WEB-INF/view/admin/shared/header.jsp" %>
<%@ include file="/WEB-INF/view/admin/shared/sidebar.jsp" %>
    <section id="main-content-section">
        <div class="main-content">
            <form id="customers-form" action="customers_edit" method="Post">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="left-container d-flex flex-column gap-4">
                                <div class="avatar-wrap bg-white rounded">
                                    <h4>Ảnh đại diện</h4>
                                    <div id="input-images">
                                        <div class="image-uploader">
                                            <img src="${pageContext.request.contextPath}/files/${repuser.avatar.path}">
                                        </div>
                                    </div>
<%--                                    <div class="fw-light text-center text-secondary upload-text">--%>
<%--                                        Set the category thumbnail image. Only *.png, *.jpg and *.jpeg image files are accepted--%>
<%--                                    </div>--%>
                                </div>
                                <div class="bg-white rounded p-3 d-flex flex-column gap-4">
                                    <div class="d-flex flex-column">
                                        <h4>Ngày tạo tài khoản</h4>
                                        <div class="form-outline">
                                            <input disabled type="text" class="form-control" placeholder="${requestScope.repuser.createAt}" id="txt_date">
                                        </div>
                                    </div>                                       
                                    <div class="d-flex flex-column userstatus">
                                        <h4>Tình trạng</h4>
                                        <select class="select" name="userstatusvalue">
                                            <c:if  test="${repuser.status == 0}" >
                                                <option data-id="0" value="0" selected="selected">Tạm ngưng</option>
                                                <option data-id="1" value="1">Hoạt động</option>
                                            </c:if>
                                            <c:if test="${repuser.status == 1}">
                                                <option data-id="0" value="0" >Tạm ngưng</option>
                                                <option data-id="1" value="1" selected ="selected">Hoạt động</option>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-9">
                            <div class="right-container bg-white rounded p-4">
                                <h4>Thông tin tài khoản</h4>
                                <c:if test="${message != null}">
                                    <h3 style="color: red">Cập nhật thông tin khách hàng thành công</h3>
                                </c:if>
                                <input  type="hidden" name="userid" value="${repuser.id}">
                                <div class="content-wrap d-flex flex-column gap-4">
                                    <div class="d-flex flex-column gap-1">
                                        <div class="d-block fw-semibold">Tên tài khoản</div>
                                        <div class="d-flex align-items-center gap-2">
                                            <h4 class="mb-1">${repuser.username}</h4>
<%--                                            <span class="badge badge-danger">Chưa xác thực</span>--%>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="d-flex flex-column gap-1">
                                                <div class="d-block fw-semibold">Họ</div>
                                                <div class="form-outline">
                                                    <input name="userfirstname" type="text" class="form-control" placeholder="${repuser.firstName}" id="text_firstName">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="d-flex flex-column gap-1">
                                                <div class="d-block fw-semibold">Tên</div>
                                                <div class="form-outline">
                                                    <input name="userlastname" type="text" class="form-control" placeholder="${repuser.lastName}" id="txt_lastName">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="d-flex flex-column gap-1">
                                                <div class="d-block fw-semibold">Số điện thoại</div>
                                                <div class="form-outline">
                                                    <input name="userphone" type="text" class="form-control" placeholder="${repuser.phone}" id="text_phone">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="d-flex flex-column gap-1">
                                                <div class="d-block fw-semibold">Email</div>
                                                <div class="form-outline">
                                                    <input name="useremail" type="text" class="form-control" placeholder="${repuser.email}" id="txt_email">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="d-flex flex-column gap-1">
                                                <div class="d-block fw-semibold">Địa chỉ</div>
                                                <div class="form-outline">
                                                    <input name="useraddress" type="text" class="form-control" placeholder="${repuser.address}" id="text_address">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="d-flex flex-column gap-1">
                                                <div class="d-block fw-semibold">Ngày sinh</div>
                                                <div class="form-outline">
                                                    <input name="userbirth" type="text" class="form-control" placeholder="${repuser.birthDate}" id="txt_birthday">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="actions-field">
<%--                                <button class="cancel-btn">Làm mới</button>--%>
                                <button class="update-btn" >Cập nhật thông tin</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </section>  
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
<%--    <script src="${pageContext.request.contextPath}/js/admin/sidebar.js"></script>--%>
<%--    <script src="${pageContext.request.contextPath}/libs/image-uploader/image-uploader.js"></script>--%>
    <script src="${pageContext.request.contextPath}/libs/daterangepicker-master/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/libs/DataTables/datatables.min.js"></script>
    <script src="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.js"></script>
    <script src="${pageContext.request.contextPath}/libs/suneditor-master/dist/suneditor.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/admin/customers-edit.js"></script>
</body>

</html>