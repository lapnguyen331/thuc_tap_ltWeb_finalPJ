<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 1/22/2024
  Time: 2:11 PM
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/image-uploader/image-uploader.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/suneditor-master/dist/css/suneditor.min.css">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/details-product-template.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin-products-edit.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-override.css">
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
        <form id="products-form">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <div class="left-container d-flex flex-column gap-4">
                            <div class="thumbnail-wrap bg-white rounded">
                                <h4>Ảnh sản phẩm</h4>
                                <div id="input-images"></div>
                                <div class="fw-light text-center text-secondary upload-text">
                                    Set the category thumbnail image. Only *.png, *.jpg and *.jpeg image files are accepted
                                </div>
                            </div>
                            <div class="bg-white rounded p-3 d-flex flex-column gap-4">
                                <div class="d-flex flex-column">
                                    <h4>Ngày tạo sản phẩm</h4>
                                    <div class="form-outline">
                                        <input type="text" class="form-control" placeholder="Ngày tạo" id="txt_date">
                                    </div>
                                </div>
                                <div class="d-flex flex-column">
                                    <h4>Tình trạng</h4>
                                    <select class="select">
                                        <option value="active">Hoạt động</option>
                                        <option value="deactive">Tạm dừng</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="right-container bg-white rounded p-4">
                            <!-- Tabs navs -->
                            <ul class="nav nav-tabs mb-3 nav-fill" id="ex1" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <a
                                            class="nav-link active"
                                            id="ex1-tab-1"
                                            data-mdb-toggle="tab"
                                            href="#ex1-tabs-1"
                                            role="tab"
                                            aria-controls="ex1-tabs-1"
                                            aria-selected="true">
                                        Chỉnh sửa thông tin
                                    </a>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <a
                                            class="nav-link"
                                            id="ex1-tab-2"
                                            data-mdb-toggle="tab"
                                            href="#ex1-tabs-2"
                                            role="tab"
                                            aria-controls="ex1-tabs-2"
                                            aria-selected="false">
                                        Bài viết mô tả
                                    </a>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <a
                                            class="nav-link"
                                            id="ex1-tab-3"
                                            data-mdb-toggle="tab"
                                            href="#ex1-tabs-3"
                                            role="tab">
                                        Đánh giá sản phẩm
                                    </a>
                                </li>
                            </ul>
                            <!-- Tabs content -->
                            <div class="tab-content" id="ex1-content">
                                <div class="tab-pane fade show active" id="ex1-tabs-1" role="tabpanel" aria-labelledby="ex1-tab-1">
                                    <div class="product-info-wrap p-2 border rounded d-flex flex-column gap-3">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-outline">
                                                    <i class="fa-solid fa-hashtag trailing"></i>
                                                    <input type="text" name="id" id="input_id_product" class="form-control" maxlength="10" minlength="10" readonly/>
                                                    <label class="form-label" for="input_id_product">Mã sản phẩm</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-outline">
                                                    <input type="text" id="input_name_product" class="form-control" />
                                                    <label class="form-label" for="input_name_product">Tên sản phẩm</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-outline">
                                                    <i class="trailing">VNĐ</i>
                                                    <input type="text" id="input_price_product" class="form-control" />
                                                    <label class="form-label" for="input_price_product">Giá bán</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <select class="select" id="select_category_product">
                                                    <!-- JavaScript ở chèn option vào đây -->
                                                </select>
                                                <label class="form-label select-label">Danh mục sản phẩm</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-outline">
                                                    <input type="text" id="input_specific_product" class="form-control"/>
                                                    <label class="form-label" for="input_specific_product">Quy cách</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-outline">
                                                    <input type="text" id="input_weight_product" class="form-control"/>
                                                    <label class="form-label" for="input_weight_product">Trọng lượng (gram)</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-outline autocomplete" id="async-brands">
                                                    <input type="text" id="input_brand_product" class="form-control"/>
                                                    <label class="form-label" for="input_brand_product">Thương hiệu</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-outline">
                                                    <input type="text" id="input_remains_product" class="form-control"/>
                                                    <label class="form-label" for="input_remains_product">Tồn kho</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-outline">
                                                    <input type="text" id="input_age_product" class="form-control"/>
                                                    <label class="form-label" for="input_age_product">Độ tuổi nhỏ nhất</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <select class="select" id="select_producers_product">
                                                    <!-- JavaScript chèn option vào đây -->
                                                </select>
                                                <label class="form-label select-label">Đơn vị sản xuất</label>
                                            </div>
                                        </div>
                                        <div class="form-outline">
                                            <textarea class="form-control" id="txtArea_desc_product" rows="4"></textarea>
                                            <label class="form-label" for="txtArea_desc_product">Mô tả sản phẩm</label>
                                        </div>
                                        <label for="images_gallery_product" class="form-label">Hình ảnh thư viện</label>
                                        <div class="input-images" id="images_gallery_product"></div>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="ex1-tabs-2" role="tabpanel" aria-labelledby="ex1-tab-2">
                                    <div class="blog-wrap p-2 border rounded">
                                        <div class="fw-semibold">Mã bài viết</div>
                                        <div class="input-group mb-3">
                                            <div id="box_blog_filter">
                                                <input type="text" id="blog_filter" class="form-control" style="width: 400px;" />
                                            </div>
                                            <button class="btn btn-primary d-flex gap-1 align-items-center text-capitalize" type="button">
                                                <i class="fa-regular fa-folder-open"></i>
                                                <span>Blog có sẵn</span>
                                            </button>
                                            <button class="btn btn-success d-flex gap-1 align-items-center text-capitalize" type="button">
                                                <i class="fa-solid fa-pen-to-square"></i>
                                                <span>Viết blog mới</span>
                                            </button>
                                        </div>
                                        <div class="fw-semibold">Nội dung bài viết</div>
                                        <textarea id="suneditor">
                                            </textarea>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="ex1-tabs-3" role="tabpanel" aria-labelledby="ex1-tab-3">
                                    Tab 3 content
                                </div>
                            </div>
                        </div>
                        <div class="actions-field">
                            <button class="cancel-btn">Làm mới</button>
                            <button class="update-btn">Cập nhật thông tin</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin/sidebar.js"></script>
<script src="${pageContext.request.contextPath}/libs/image-uploader/image-uploader.js"></script>
<script src="${pageContext.request.contextPath}/libs/daterangepicker-master/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/DataTables/datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/libs/suneditor-master/dist/suneditor.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin/products-edit.js"></script>
</body>

</html>