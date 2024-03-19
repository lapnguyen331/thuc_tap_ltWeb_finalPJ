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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog-detail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/breadcrumbs.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixed-buttons.css">

    <title>Chi tiết blog</title>
</head>
<body style="background-color: #f7f0e6;">
    <div id="fixed-btns">
        <div class="cart-btn" type="button" data-mdb-toggle="modal" data-mdb-target="#my-cart">
            <i class="fa-solid fa-cart-plus d-inline-block pt-1 pe-none"></i>
        </div>
    </div>
    <div class="modal fade right" id="my-cart" tabindex="-1">
        <div class="modal-dialog modal-side modal-top-right">
            <div class="modal-content bg-gold">
                <div class="modal-header">
                    <div class="modal-title">
                        <div class="fw-semibold fs-5 text-up">
                            <span>Giỏ hàng của tôi</span>
                            <i class="fa-solid fa-basket-shopping"></i>
                        </div>
                    </div>
                    <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="cart-products d-flex flex-column">
                        <div class="product-wrap d-flex align-items-center gap-2">
                            <div class="btn-product-remove">
                                <i class="fa-regular fa-trash-can"></i>
                            </div>
                            <div class="img-wrap border rounded d-flex align-items-center justify-content-center">
                                <img src="/template/image/cao-hong-sam-hong-seon-gold-hop-2-lo-250g-1.webp" width="100%">
                            </div>
                            <div class="d-flex flex-column justify-content-center">
                                <div class="product-name">
                                    Cao hồng sâm Hong Seon Gold hộp 2 lọ 250g
                                </div>
                                <div class="text-primray-red-color-main fw-bold">
                                    980.000₫
                                </div>
                            </div>
                            <div class="amount-btns">
                                <div class="bg-primary-red-main-color text-wheat-color d-flex align-items-center arrow-wrap">
                                    <div class="arrow arrow-down"></div>
                                </div>
                                <input type="text">
                                <div class="bg-primary-red-main-color text-wheat-color d-flex align-items-center arrow-wrap">
                                    <div class="arrow arrow-up"></div>
                                </div>
                            </div>
                        </div>
                        <div class="product-wrap d-flex align-items-center gap-2">
                            <div class="btn-product-remove">
                                <i class="fa-regular fa-trash-can"></i>
                            </div>
                            <div class="img-wrap border rounded d-flex align-items-center justify-content-center">
                                <img src="/template/image/cao-sam-hu-doi-1.jpg" width="100%">
                            </div>
                            <div class="d-flex flex-column justify-content-center">
                                <div class="product-name">
                                    Cao Sâm Hũ Đôi Arirang Hàn Quốc
                                </div>
                                <div class="text-primray-red-color-main fw-bold">
                                    2.100.000₫
                                </div>
                            </div>
                            <div class="amount-btns">
                                <div class="bg-primary-red-main-color text-wheat-color d-flex align-items-center arrow-wrap">
                                    <div class="arrow arrow-down"></div>
                                </div>
                                <input type="text">
                                <div class="bg-primary-red-main-color text-wheat-color d-flex align-items-center arrow-wrap">
                                    <div class="arrow arrow-up"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="bg-gold bg-sharp-5">
                        <button class="btn-moveTo" >
                            <a href="${pageContext.request.contextPath}/cart.html" >
                                <span >Xem chi tiết giỏ hàng</span>
                            </a>
                        </button>
                    </div>

                    <div class="bg-gold bg-sharp-5">
                        <button class="btn-closeModal" type="button" data-mdb-dismiss="modal">
                            <span>Hủy bỏ</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/shared/header.jsp"></jsp:include>
    <!-- Sam han quoc-->
    <!-- breadcrum -->
    <div class="breadcrumbs-wrap">
        <ul class="breadcrumbs">
            <li><a href="">Tin tức </a></li>
            <li><a href="">Chi tiết blog </a></li>

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
                   <section id="blog-detail">
                        <div class="blog-detail-container">
                            <div class="col-md-12">
                                <div class="tab-content-wrapper">
                                    <section class="tab-content tab-is-active" id="tab-product-content">
                                        <h1 style="padding-bottom: 10px;">${resBlog.title}</h1>
<%--                                        <h4>Cao hồng sâm Hong Seon Gold hộp 2 lọ 250g</h4><div class="se-component se-image-container __se__float-none"><figure><img src="/template/image/cao-hong-sam-hong-seon-gold-hop-2-lo-250g-1.webp" alt="" data-proportion="true" data-align="none" data-file-name="cao-hong-sam-hong-seon-gold-hop-2-lo-250g-1.webp" data-file-size="0" data-origin="," data-size="," data-rotate="" data-percentage="auto,auto" style=""></figure></div><h4>Thành phần:</h4><p>15% Hồng sâm cô đặc 6 năm tuổi (Ginsenosides Rg1, Rb1 và Rg3 tổng 4mg/g, chất rắn 70% trở lên), 70% hỗn hợp thực vật cô đặc (táo tàu 9%, rễ bạch chỉ 9%, rễ hoa cúc 9%, ngải cứu, Gujeolcho, Rễ Jihwang (Sukjihwang), lá non Artemisia, rễ hoa cúc, thân rễ sapju, rễ mẫu đơn, rễ xương cựa, hạch nấm bokryeong, nấm linh chi, cam thảo, chất rắn 60% trở lên), isomaltooligosaccharide.</p><h4>Công dụng:</h4><ul><li>Bồi bổ cơ thể, tăng sức lao động trí óc và chân tay, chống mệt mỏi.</li><li>Chống lão hóa, cải thiện chức năng não bộ ở người lớn tuổi, tăng khả năng tập trung trí tuệ, tăng trí nhớ</li><li>Tăng cường khả năng miễn dịch, giúp cơ thể tăng khả năng thích nghi phòng vệ đối với những kích thích có hại</li><li>Điều hòa huyết áp, giúp hồi phục huyết áp ở cơ thể.</li><li>Giảm tác hại của chất phóng xạ, cải thiện và phòng ngừa sự phát triển của các tế bào ung thư.</li><li>Ngăn ngừa nguy cơ phát sinh cholesterol cao, từ đó hạn chế sự hình thành các mảng xơ vữa động mạch</li><li>Thành phần chính Saponin có trong cao sâm có tác dụng loại bỏ chất Alloxan và chất Streptozotocin là nguyên nhân làm tăng đường huyết do đó cao sâm cũng hỗ trợ trong việc giảm đường huyết, tiểu đường</li><li>Saponin trong hồng sâm làm tăng hoạt động enzym liên quan tới sự thoái hóa ethanol và acetaldehyd, do đó đối với người hay uống rượu, để giảm gan tránh được độc tính</li></ul><h4>Đối tượng sử dụng: </h4><ul><li>Người suy kiệt cơ thể hậu điều trị cần tăng cường đề kháng và bồi bổ sức khỏe.</li><li>Người làm việc trí óc hoặc học tập cường độ cao.</li><li>Người bị ung thư, mắc các bệnh về rối loạn chuyển hóa đường trong nước tiểu, mỡ, tim, huyết áp…</li><li>Người già cần bồi bổ sức khỏe, tăng cường trí nhớ.</li><li>Phụ nữ hơn 30 tuổi mong muốn duy trì một cơ thể khỏe mạnh và một làn da tươi trẻ.</li></ul><div class="se-component se-image-container __se__float-none"><figure><img src="/template/image/cao-hong-sam-hong-seon-gold-hop-2-lo-250g.webp" alt="" data-proportion="true" data-align="none" data-file-name="cao-hong-sam-hong-seon-gold-hop-2-lo-250g.webp" data-file-size="0" data-origin="," data-size="," data-rotate="" data-percentage="auto,auto" style=""></figure></div><h4>Cách dùng:</h4><ul><li><strong>Phòng bệnh:</strong> Mỗi ngày uống 2 lần, mỗi lần uống lấy 1 thìa cao nhỏ (có trong hộp)= 5mg, pha với nước ấm rồi uống trước bữa ăn 15-20 phút.</li><li><strong>Chữa bệnh:</strong> Mỗi ngày uống 3 lần. Mỗi lần uống lấy 2 thìa cao nhỏ, pha với nước ấm, uống trước bữa ăn 15-20 phút.</li></ul><p><strong>Quy cách đóng gói:</strong> Hộp 2 lọ 250g</p><p><strong>Hạn sử dụng:</strong> 2 năm kể từ ngày sản xuất</p><p><strong>Lưu ý:</strong> Thực phẩm không là thuốc, không thay thế thuốc chữa bệnh</p><p><strong>Nhà sản xuất:</strong> KOREA GINSENG CENTER</p><p><strong>Điện thoại:</strong> 0934 138 985</p>--%>
                                            ${requestScope.blogcontent}
                                    </section>
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