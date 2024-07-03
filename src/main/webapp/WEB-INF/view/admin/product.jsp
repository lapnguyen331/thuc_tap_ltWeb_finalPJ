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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/suneditor-master/dist/css/suneditor.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/flickity/flickity.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/details-product-template.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/image-uploader/image-uploader.min.css">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/sidebar.css?v=2">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin-modal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin-product.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-override.css">
    <title>Quản lí sản phẩm</title>
</head>

<body>
<%@ include file="/WEB-INF/view/admin/shared/header.jsp" %>
<%@ include file="/WEB-INF/view/admin/shared/sidebar.jsp" %>
<section id="main-content-section">
    <div class="main-content">
        <div class="product-control">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 px-0">
                        <form class="filter-form">
                            <div class="action-field">
                                <div class="form-title">Bộ lọc</div>
                                <button type="submit">Tìm kiếm sản phẩm</button>
                            </div>
                            <div class="filter-button-field">
                                <i class="fa-solid fa-magnifying-glass"></i>
                                <input type="text" name="name">
                            </div>
                            <div class="filter-radio-field">
                                <div class="form-title">Danh mục</div>
                                <ul class="field-show">
                                    <c:forEach var="i" begin="0" end="2">
                                        <li>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="category" value="${sessionScope.categories[i].id}"
                                                       id="cate-${sessionScope.categories[i].id}" />
                                                <label class="form-check-label" for="cate-${sessionScope.categories[i].id}">${sessionScope.categories[i].name}</label>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <div class="field-others collapse" id="categories">
                                    <ul>
                                        <c:forEach var="i" begin="3" end="${sessionScope.categories.size() - 1}">
                                            <li>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" name="category" value="${sessionScope.categories[i].id}"
                                                           id="cate-${sessionScope.categories[i].id}" />
                                                    <label class="form-check-label" for="cate-${sessionScope.categories[i].id}">${sessionScope.categories[i].name}</label>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <a href="#categories" data-mdb-toggle="collapse" aria-expanded="false"
                                   class="show-all-toggle">Hiện tất cả...</a>
                            </div>
                            <div class="filter-radio-field">
                                <div class="form-title">Thương hiệu</div>
                                <ul class="field-show">
                                    <c:forEach var="i" begin="0" end="3">
                                        <c:set var="brand" value="${requestScope.brands[i]}"/>
                                        <li>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="brand" value="${brand}" id="${brand}" />
                                                <label class="form-check-label" for="${brand}">${brand}</label>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <div class="field-others collapse" id="brands">
                                    <ul>
                                        <c:forEach var="i" begin="4" end="${requestScope.brands.size() - 1}">
                                            <c:set var="brand" value="${requestScope.brands[i]}"/>
                                            <li>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" name="brand" value="${brand}" id="${brand}" />
                                                    <label class="form-check-label" for="${brand}">${brand}</label>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <a href="#brands" data-mdb-toggle="collapse" aria-expanded="false"
                                   class="show-all-toggle">Hiện tất cả...</a>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-9 px-0">
                        <div class="product-list">
                            <button class="add-btn view-btn" data-product-id = 'new'>
                                <i class="fa-solid fa-plus"></i>
                                <span>Thêm sản phẩm</span>
                            </button>
                            <div class="product-grid product-list-head">
                                <span class="justify-self-center">#</span>
                                <span>Hình ảnh</span>
                                <span>Mã sản phẩm</span>
                                <span>Tên sản phẩm</span>
                                <span>Giá bán</span>
                                <span>Tồn kho</span>
                            </div>
                            <!-- Body -->
                            <div class="product-list-body">
                                <div class="w-100 d-flex align-items-center justify-content-center">
                                    <img src="${pageContext.request.contextPath}/inventory/images/loading-gif.gif" width="200px" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="my-modal">
    </div>
    <div class="overlay"></div>
</section>
<!-- Modal -->
<div class="modal fade" id="admin-product-modal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title">
                    <div class="admin-modal-tabs active">
                        <button class="tab active" data-modal-tab="#mdal-tab-setting">
                            <i class="fa-solid fa-sliders"></i>
                            <span>Chỉnh sửa thông tin</span>
                        </button>
                        <button class="tab" data-modal-tab="#mdal-tab-details">
                            <i class="fa-regular fa-pen-to-square"></i>
                            <span>Bài viết mô tả</span>
                        </button>
                    </div>
                </div>
                <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="modal-content">
                    <div class="container">
                        <div class="row tab-content show" id="mdal-tab-setting">
                            <form action="" method="post" class="form-product-setting" enctype="multipart/form-data">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-outline">
                                            <i class="fa-solid fa-hashtag trailing"></i>
                                            <input type="text" id="input_id_product" class="form-control" maxlength="10" minlength="10" readonly/>
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
                                <label for="images_thumbnail_product" class="form-label">Hình ảnh đại diện</label>
                                <div class="input-images" id="images_thumbnail_product"></div>
                                <label for="images_gallery_product" class="form-label">Hình ảnh thư viện</label>
                                <div class="input-images" id="images_gallery_product"></div>
                                <button type="submit" class="btn-confirm-update">Cập nhập thông tin sản phẩm</button>
                            </form>
                        </div>
                        <div class="row tab-content" id="mdal-tab-details">
                            <div class="blog">
                                <div id="sample">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal top fade" id="confirm-modal" tabindex="-1" aria-hidden="true" data-mdb-backdrop="static" data-mdb-keyboard="false">
    <div class="modal-dialog modal-sm  modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Lưu thay đổi</h5>
            </div>
            <div class="modal-body">Bạn có chắc muốn lưu thay đổi bài viết?</div>
            <div class="modal-footer">
                <button type="button" class="btn-confirm-cancel" data-mdb-dismiss="modal">
                    <i class="fa-solid fa-xmark"></i>
                    <span>Hủy bỏ</span>
                </button>
                <button type="button" class="btn-confirm-changed" data-mdb-dismiss="modal">
                    <span>Lưu</span>
                    <i class="fa-solid fa-floppy-disk"></i>
                </button>
            </div>
        </div>
    </div>
</div>
<!-- Template Preview Bài viết -->
<!-- <section id="details-product">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="tabs">
                    <div class="tablinks tablinks-is-active" data-tab-target="#tab-product-content">Mô tả sản phẩm</div>
                    <div class="tablinks"> Địa chỉ mua hàng</div>
                    <div class="tablinks"> Đánh giá sản phẩm</div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="tab-content-wrapper">
                    <section class="tab-content tab-is-active" id="tab-product-content">
                        {{contents}}
                    </section>
                </div>
            </div>
        </div>
    </div>
</section> -->
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/flickity/flickity.pkgd.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/suneditor-master/dist/suneditor.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/image-uploader/image-uploader.js"></script>
<script src="${pageContext.request.contextPath}/js/admin/sidebar.js"></script>
<script src="${pageContext.request.contextPath}/js/admin/product.js?v=11"></script>
<script src="${pageContext.request.contextPath}/js/admin/modal.js"></script>
</body>

</html>