<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
   
    <!-- css this page -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog-item.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog-cate-filter.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/breadcrumbs.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixed-buttons.css">

    <title>Blog</title>
</head>
<body style="background-color: #f7f0e6;">
<jsp:include page="/WEB-INF/view/shared/cart.jsp"></jsp:include>

<jsp:include page="/WEB-INF/view/shared/header.jsp"></jsp:include>

    <!-- Sam han quoc-->
    <!-- breadcrum -->
    <div class="breadcrumbs-wrap">
        <ul class="breadcrumbs">
            <li><a href="${pageContext.request.contextPath}/blog">>> Blog</a></li>
        </ul>
    </div>
    <!-- content sam han quoc -->
    <div id="blog-page">
        <div class="blog-categogy-container">
            <div class="row">    
                <!-- bên trái catagogy -->
                <div id="fill-product" class="col-lg-3" >
                    <!-- catagogy -->
                    <section id="section-product-filter">
                        <div class="filter-header bg-rounded bg-gold">
                            <div class="title-primary">Phân loại sản phẩm</div>
                        </div>
                        <div class="filter-content">
                            <form action="">
                                <p class="filter-title">Lọc theo tên</p>
                                <ul class="radio-filter">
                                    <li>
                                        <label for="price_0-1m">Nước Hồng Sâm Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_1-2.5m">Cao Hồng Sâm Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_2.5m-5m">Sâm Mật Ong Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_5m-7.5m">Nhân Sâm Tươi Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_7.5m-10m">Trà Hồng Sâm Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_7.5m-10m">Kẹo Sâm Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_7.5m-10m">Viên Hồng Sâm Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_7.5m-10m">Hắc Sâm Hàn Quốc</label>
                                    </li>
                                    <li>
                                        <label for="price_7.5m-10m">Sâm Khô Hàn Quốc</label>
                                    </li>
                                </ul>
                            </form>
                        </div>
                    </section>
                   
                </div> <!--NOTEME: chưa thêm bộ lọc-->
                <div class="col-lg-9">
                    
                    <!-- sec blog  -->
                    <section id="blog-detail-1">
                        <div class="blog-list">
                        <!-- title -->
                        <div class="blog-title">
                            <div class="row justify-content-center"> <!--bọc các blog-->
                                <div class="col-12 col-sm-12 col-md-12 col-lg-12 row grib-blog">

                                        <!--blog 1-->
                                    <c:forEach var="blog" items="${requestScope.blogList}">
                                        <div class="col-12 col-md-12 col-lg-12 pb-4 blog-item">
                                            <div class="blog-show-wrap horizontal">
                                                <a rel="nofollow" href="${pageContext.request.contextPath}/blogDetail?id=${blog.id}" data-wpel-link="internal"> <!--NOTE link đến chi tiết bài blog này-->
                                                <div class="thumbnail">
                                                    <img src="${pageContext.request.contextPath}/files/${blog.thumbnail.path}" alt="Tại Sao Ăn Nhân Sâm Lại Chảy Máu Mũi? Lưu Ý Gì Khi Dùng?">
                                                </div>
                                                </a>
                                                <div class="row blog-detail-infor">
                                                    <a rel="nofollow" href="${pageContext.request.contextPath}/blogDetail?id=${blog.id}">
                                                        <h4 class="h3">${blog.title}</h4>
                                                    </a>
                                                    <div class="expect-blog" >
                                                        <p >${blog.description}</p>
                                                    </div>
                                                    <div class="row cta m-0 pb-3">
                                                        <div class="col-6 col-md-6 col-lg-6 right">
                                                            <a rel="nofollow" href="${pageContext.request.contextPath}/blogDetail?id=${blog.id}" data-wpel-link="internal"><!--NOTE link tới sp chi tiết-->
                                                                <div class="detail-blog btn-kgin kgin-sharp-5">
                                                                    <div class="btn-wrap kgin-sharp-5">
                                                                        <span>Xem chi tiết</span>
                                                                        <i class="fa-solid fa-angles-right xemicon"></i>
                                                                    </div>
                                                                </div>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                 </div>
                            </div>
                    </div>
                    </section>
                    
                </div>
            </div>
        </div>
        
    </div>
    

       <!-- Footer -->
    <jsp:include page="/WEB-INF/view/shared/footer.jsp"></jsp:include>

    <div id="fb-root"></div>
    <script async defer crossorigin="anonymous" 
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0" nonce="Qoebijhj">
    </script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
<%--    <script src="${pageContext.request.contextPath}/js/load_featured_products_home.js"></script>--%>
</body>
</html>
</body>
</html>