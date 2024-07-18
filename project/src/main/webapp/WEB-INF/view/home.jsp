<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Thư viện ngoài (Luôn để đầu tiên) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/fontawesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/swiper/swiper-bundle-min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">

    <!-- Style của project -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-slider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-card.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-filter.css?v=2">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-category.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-about.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-store.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-featured-products_v01.css">

    <title>Trang chủ</title>
</head>
<body>
<%@ include file="/WEB-INF/view/shared/cart.jsp" %>
<main>
    <%@ include file="/WEB-INF/view/shared/header.jsp" %>

    <section id="home-slider-section">
        <!-- Slider main container -->
        <div class="swiper">
            <!-- Additional required wrapper -->
            <div class="swiper-wrapper">
                <!-- Slides -->
                <div class="swiper-slide">
                    <img src="${pageContext.request.contextPath}/inventory/images/banner-1.jpg" width="100%" alt="">
                </div>
                <div class="swiper-slide">
                    <img src="${pageContext.request.contextPath}/inventory/images/banner-2.png" width="100%" alt="">
                </div>
                <div class="swiper-slide">
                    <img src="${pageContext.request.contextPath}/inventory/images/banner-3.jpg" width="100%" alt="">
                </div>
            </div>
            <!-- If we need pagination -->
            <div class="swiper-pagination"></div>
        </div>
    </section>

    <!-- Product Featured Section -->
    <section id="home-featured-products-section">
        <div class="container">
            <div class="row pt-3">
                <div class="col-md-12">
                    <div class="shipment-wrapper">
                        <div class="shipment-col">
                            <div class="shipment-icon">
                                <img src="${pageContext.request.contextPath}/inventory/icons/ship-toan-quoc.jpg" alt="">
                            </div>
                            <div>
                                <strong>Giao hàng toàn quốc</strong>
                                <p>Hà Nội, Hồ Chí Minh trong ngày</p>
                            </div>
                        </div>

                        <div class="shipment-col">
                            <div class="shipment-icon">
                                <img src="${pageContext.request.contextPath}/inventory/icons/nhan-hang-thanh-toan.jpg" alt="">
                            </div>
                            <div>
                                <strong>Nhận hàng thanh toán</strong>
                                <p>Trực tiếp với người giao</p>
                            </div>
                        </div>

                        <div class="shipment-col">
                            <div class="shipment-icon">
                                <img src="${pageContext.request.contextPath}/inventory/icons/mien-phi-van-chuyen.jpg" alt="">
                            </div>
                            <div>
                                <strong>Miễn phí vận chuyển</strong>
                                <p>Đơn hàng >= 500.000đ</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row gy-md-4 pt-3 featured-row mb-3">
                <!-- Title -->
                <div class="col-12 d-flex justify-content-center">
                    <div class="bg-gold bg-sharp">
                        <div class="title-primary">Sản phẩm bán chạy</div>
                    </div>
                </div>
                <c:if test="${not empty requestScope.products2}">
                    <c:forEach var="product" items="${requestScope.products2}">
                        <div class="col-md-3">
                            <div class="product-card">
                                <a href="">
                                    <div class="product-img d-flex align-items-center justify-content-center" data-public-id="${product.thumbnail}">
                                        <img style="width: 100px; height: 100px" src="${pageContext.request.contextPath}/inventory/images/loading-gif.gif" alt="">
                                    </div>

                                    <div class="product-name">
                                        <p>${product.name}</p>
                                    </div>
                                </a>
                                <div class="product-price-wrapper">
                                    <div class="discount-label">-${product.discountPercent}%</div>
                                    <div class="price">
                                        <p class="m-price">${product.priceFormat}</p>
                                        <p class="c-price">${product.discountPriceFormat}</p>
                                    </div>
                                </div>
                                <div class="product-button">
                                    <div class="bg-gold bg-sharp-5">
                                        <button class="btn-store-cart" data-cart-product="true" data-cart-action="add" data-cart-id="${product.id}" data-cart-amount="1">
                                            Thêm vào giỏ
                                        </button>
                                    </div>
                                    <div class="bg-gold bg-sharp-5">
                                        <button class="btn-details">
                                            <a href="${pageContext.request.contextPath}/product?id=${product.id}" class="move-to-details">Xem chi tiết</a>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty requestScope.products2}">
                    <div class="col-md-12 d-flex justify-content-center">
                        <div class="text-danger">Chưa có sản phẩm nào...</div>
                    </div>
                </c:if>
            </div>
        </div>
    </section>

    <div class="container">
        <div class="row">
            <div class="col-3">
                <!-- Section Product Filter -->
                <section id="section-product-filter">
                    <div class="filter-header bg-rounded bg-gold">
                        <div class="title-primary">Lọc sản phẩm</div>
                    </div>
                    <div class="filter-content">
                        <form action='search' method="get" >
                            <p class="filter-title">Lọc theo giá</p>
                            <ul class="radio-filter">
                                <li>
                                    <input type="radio" name="range" value="0-1000000" id="price_0-1m">
                                    <label for="price_0-1m">< 1 triệu</label>
                                </li>
                                <li>
                                    <input type="radio" name="range" value="1000000-2500000" id="price_1-2.5m">
                                    <label for="price_1-2.5m">1 triệu - 2.5 triệu</label>
                                </li>
                                <li>
                                    <input type="radio" name="range" value="2500000-5000000" id="price_2.5m-5m">
                                    <label for="price_2.5m-5m">2.5 triệu - 5 triệu</label>
                                </li>
                                <li>
                                    <input type="radio" name="range" value="5000000-7500000" id="price_5m-7.5m">
                                    <label for="price_5m-7.5m">5 triệu - 7.5 triệu</label>
                                </li>
                                <li>
                                    <input type="radio" name="range" value="7500000-10000000" id="price_7.5m-10m">
                                    <label for="price_7.5m-10m">7.5 triệu - 10 triệu</label>
                                </li>
                            </ul>
                            <p class="filter-title">Theo thương hiệu</p>
                            <div class="filter-type-select">
                                <select name="brand" id="select-brand">
                                    <option value="" disabled selected>(Bất kì thương hiệu nào)</option>
                                    <c:forEach items="${requestScope.brands}" var="brand">
                                        <option value="${brand}">${brand}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <p class="filter-title">Theo danh mục</p>
                            <div class="filter-type-select">
                                <select name="category" id="select-category">
                                    <option value="" disabled selected>(Bất kì danh mục nào)</option>
                                    <c:forEach var="category" items="${requestScope.categories}">
                                        <option value="${category.id}">${category.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <p class="filter-title">Theo tên sản phẩm</p>
                            <div class="filter-type-input-text">
                                <input type="text" name="name" id="text-name" placeholder="Để trống cho tên bất kì">
                            </div>
                            <input type="submit" value="Tìm kiếm sản phẩm" >
                        </form>
                    </div>
                </section>
            </div>
            <div class="col-9" id="category-section">
                <c:if test="${not empty requestScope.products1}">
                    <c:forEach var="entry" items="${requestScope.products1}">
                        <c:set var="name" value="${entry.key}"/>
                        <c:set var="list" value="${entry.value}"/>
                        <section class="category-product">
                            <div class="row">
                                <div class="col-12">
                                    <div class="banner-wrap bg-gold bg-sharp p-1">
                                        <div class="img-wrap">
                                            <img src="/WebContext/inventory/images/footer-bg.jpg" alt="">
                                        </div>
                                        <div class="banner-title">
                                            <h3>${name}</h3>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <c:if test="${empty list}">
                                    <div class="col-md-12">
                                        <div class="fw-semibold text-center text-danger">Hiện cửa hàng đã hết sản phẩm thuộc danh mục này... :(</div>
                                    </div>
                                </c:if>
                                <div class="row g-4">
                                    <c:forEach var="product" items="${list}">
                                        <div class="col-md-4">
                                            <div class="product-card">
                                                <a href="${pageContext.request.contextPath}/product?id=${product.id}">
                                                    <div class="product-img d-flex align-items-center justify-content-center" data-public-id="${product.thumbnail}">
                                                        <img style="width: 100px; height: 100px" src="${pageContext.request.contextPath}/inventory/images/loading-gif.gif" alt="${product.name}">
                                                    </div>

                                                    <div class="product-name">
                                                        <p>${product.name}</p>
                                                    </div>
                                                </a>
                                                <div class="product-price-wrapper">
                                                    <div class="discount-label">-${product.discountPercent}%</div>
                                                    <div class="price">
                                                        <p class="m-price">${product.priceFormat}</p>
                                                        <p class="c-price">${product.discountPriceFormat}</p>
                                                    </div>
                                                </div>
                                                <div class="product-button">
                                                    <div class="bg-gold bg-sharp-5">
                                                        <button class="btn-store-cart" data-cart-product="true" data-cart-action="add" data-cart-id="${product.id}" data-cart-amount="1">
                                                            Thêm vào giỏ
                                                        </button>
                                                    </div>
                                                    <div class="bg-gold bg-sharp-5">
                                                        <button class="btn-details">
                                                            <a href="${pageContext.request.contextPath}/product?id=${product.id}" class="move-to-details">Xem chi tiết</a>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <c:if test="${not empty list}">
                                <div class="row">
                                    <div class="col-md-12 d-flex justify-content-center">
                                        <a href="${pageContext.request.contextPath}/categogy-detail?id=${list[0].categoryId}" class="btn-view-products-more">Xem thêm ${name}</a>
                                    </div>
                                </div>
                            </c:if>
                        </section>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>

    <!-- About Section -->
    <section id="about-section">
        <div class="container">
            <div class="row border-bottom pb-2">
                <div class="col-6 ps-0">
                    <div class="bg-gold bg-sharp-no-left">
                        <div class="title-primary">
                            Giới thiệu về Nhân Sâm Hàn Quốc Cheong Kwan Jang
                        </div>
                    </div>
                </div>
            </div>
            <div class="row pt-3 ps-3">
                <div class="col-md-6">
                    <div class="img-about bg-gold bg-sharp">
                        <img src="${pageContext.request.contextPath}/inventory/images/about-img.jpg" alt="">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="about-content">
                        <h1>Nhân sâm hàn quốc</h1>
                        <p>Cửa hàng Cheong Kwan Jang chuyên phân phối các loại <a href="">nhân sâm Hàn Quốc</a>, hồng sâm 6 năm tuổi,
                            nấm linh chi và đông trùng hạ thảo uy tín, giá tốt nhất thị trường.</p>
                        <p>Chúng tôi chuyên bán buôn và bán lẻ các sản phẩm về thực phẩm chức năng như Sâm Hàn Quốc, Nấm linh chi và đông trùng hạ thảo của Hàn Quốc.</p>
                        <p>Sản phẩm Cheong Kwan Jang cung cấp có giá cả cạnh tranh, có hóa đơn VAT, có giấy công bố chất lượng….</p>
                        <p>Với phương châm Đảm bảo vì lợi ích cao nhất cho Quý khách hàng. Chúng tôi có giới thiệu và bán những sản phẩm Nhân Sâm Hàn Quốc chính hiệu, đảm bảo 100% về chất lượng.</p>
                        <p>Đến với chúng tôi Quý khách hàng có thể lựa chọn cho mình những sản phẩm Nhân Sâm phù hợp nhất, an toàn và đảm bảo nhất.</p>
                        <h1>Nhân sâm Hàn Quốc là gì</h1>
                        <p><a href="">Nhân sâm Hàn Quốc</a> là tên gọi một số cây thân thảo có rễ và củ được dùng trong các bài thuốc đông y, thuộc chi Sâm. Nhân sâm là loại củ sâm có hình dáng hao hao giống hình người, có hàm lượng chất dinh dưỡng cao, do đặc thù về khí hậu, thổ nhưỡng. Nhân sâm có vị đắng, không độc, tác dụng bồi bổ sức khỏe, giải độc gan, chống oxi hóa.</p>
                        <p>Một số sản phẩm từ nhân sâm Hàn Quốc như: rượu nhân sâm, nhân sâm ngâm mật ong, hồng sâm, hắc sâm. Để tìm hiểu xem nhân sâm Hàn Quốc gồm những thành phần gì và có tác dụng như thế nào đến sức khỏe hãy cùng KGIN xem phần tiếp theo nhé!</p>
                        <h3>1. Thành phần của nhân sâm Hàn Quốc</h3>
                        <p>Theo như các nghiên cứu của y học hiện đại, <a href="">nhân sâm</a> có các thành phần chính như <strong>Ginsenoside (Saponin trong nhân sâm còn có tên gọi khác là Ginsenoside)</strong>, Glycoside Panaxin, Tinh dầu (làm Nhân sâm có mùi đặc biệt), các vitamin B1 và B2, các acid béo như acid Palmitic, Stearic và Linoleic, các acid amin và hàm lượng Germanium cao. Những thành phần này có tác dụng rất tích cực với cơ thể con người, mang lại hiệu quả cao cho sức khỏe.</p>
                        <img src="${pageContext.request.contextPath}/inventory/images/about-img-content.webp" alt="" width="100%">
                        <p>Theo thống kê, trong củ và rễ sâm có tới <strong>32 loại Saponin Triterpen  trong đó có tới 30 loại là Saponin Dammaran</strong>. Trong đó phần <strong>thân rễ nhân sâm chiếm tới 10,75% hàm lượng Saponin</strong>. Một số loại Saponin quan trọng có tác dụng:</p>
                        <ul>
                            <li><strong>Saponin Ro</strong> tác dụng giải độc gan, ngăn ngừa tổn thương gan</li>
                            <li><strong>Saponin Rb1</strong> có công dụng kiềm chế hệ thần kinh trung ương, giúp khống chế các cơn đau, bảo vệ tế bào gan.</li>
                            <li><strong>Saponin Rb2</strong> có tác dụng chống tiểu đường, chống xơ gan, đẩy nhanh tốc độ hấp thụ của các tế bào gan.</li>
                            <li><strong>Saponin Rd</strong> có tác dụng tăng cường hoạt động của vỏ tuyến thượng thận.</li>
                            <li><strong>Saponin Rg1</strong> có tác dụng tăng cường sự tập trung của hệ thần kinh, chống mệt mỏi, căng thẳng.</li>
                            <li><strong>Saponin Rg2</strong> giúp hạn chế sự kết dính tiểu cầu, ngăn ngừa xơ vữa động mạch.</li>
                            <li><strong>Saponin Rh2</strong> ngăn ngừa sự hình thành phát triển và di căn của tế bào ung thư.</li>
                        </ul>
                        <h3>2. Nhân sâm Hàn Quốc có tác dụng gì?</h3>
                        <p><a href="#">Nhân sâm Hàn Quốc</a> có tác dụng gì? Và hiệu quả mang lại đối với cơ thể, sức khỏe như thế nào. Hãy cùng KGIN theo dõi một số tác dụng chính mà nhân sâm Hàn Quốc mang lại. </p>
                        <ul>
                            <li>Nhân sâm Hàn Quốc chứa hàm lượng Saponin khá lớn nên có thể giúp tăng cường sức đề kháng, phục hồi cơ thể nhanh chóng sau khi khỏi bệnh. </li>
                            <li>Nhờ thành phần Saponin nên nhân sâm còn giúp làm hạ cholesterol và triglycerid trong máu giúp ngăn được các bệnh về tim mạch, tăng cường tuần hoàn máu. </li>
                            <li>Đối với người cao tuổi hoặc người làm việc ở môi trường áp lực lớn thì nhân sâm giúp bổ sung lượng canxi và giúp kích thích trí não hoạt động tăng cường, cải thiện trí nhớ.</li>
                            <li>Thêm vào đó, nhân sâm Hàn Quốc còn có tác dụng chống lão hóa và làm đẹp da ở nữ giới. </li>
                            <li>Ngoài ra còn giúp cơ thể tránh được sự phát triển của các tế bào độc hại trong cơ thể, hỗ trợ gan tránh được độc tính của rượu và ngăn ngừa một số bệnh hiểm nghèo. </li>
                        </ul>
                        <h3>3. Đối tượng sử dụng nhân sâm Hàn Quốc</h3>
                        <p>Nhân sâm Hàn Quốc là một loại thảo dược vô cùng bổ dưỡng, tuy nhiên có những đối tượng nên sử dụng và không nên sử dụng loại thực phẩm chức năng hảo hạng này. Dưới đây là những người nên dùng và không nên dùng nhân sâm Hàn Quốc: </p>
                        <h4>3.1 Những người nên sử dụng sâm Hàn Quốc</h4>
                        <ul>
                            <li>Người lớn tuổi có sức khỏe yếu, cần bổ sung sức đề kháng.</li>
                            <li>Bệnh nhân sau phẫu thuật hoặc hóa xạ trị, điều trị kháng sinh lâu dài dẫn đến suy nhược, cần hồi phục sức khỏe nhanh.</li>
                            <li>Lao động trong môi trường áp lực cao, làm việc căng thẳng, thường xuyên mệt mỏi.</li>
                            <li>Người có các bệnh về tim mạch, tiểu đường.</li>
                            <li>Người thường xuyên phải tiếp xúc với bia rượu, chức năng gan suy yếu</li>
                            <li>Đây là những đối tượng nên sử dụng sâm Hàn Quốc để giúp cơ thể bồi bổ, ngăn ngừa các loại bệnh có hại cho cơ thể.</li>
                            <li>Ngoài ra, nam giới gặp các vấn đề về sinh lý cũng có thể sử dụng nhân sâm Hàn Quốc để cải thiện tình trạng. </li>
                            <li>Nữ giới muốn làm đẹp, cải thiện làn da và ngăn ngừa lão hóa.</li>
                        </ul>
                        <h4>3.2 Những người không nên sử dụng nhân sâm Hàn Quốc</h4>
                        <p>Nhân sâm tuy có nhiều công dụng tốt đối với sức khỏe, nhưng nên hạn chế sử dụng với những đối tượng sau:</p>
                        <ul>
                            <li>Người có các vấn đề về tiêu hóa, bị bệnh trào ngược dạ dày, xuất huyết dạ dày.</li>
                            <li>Phụ nữ mang thai và cho con bú không nên dùng vì có thể gây ảnh hưởng tới thai nhi và trẻ nhỏ.</li>
                            <li>Với trẻ em dưới 15 tuổi, chỉ nên dùng các sản phẩm nhân sâm Hàn Quốc được chiết xuất riêng phù hợp với thể trạng, như hồng sâm baby.</li>
                            <li>Người bị cảm mạo, phong hàn, ho lao phổi.</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Store Section -->
    <section id="store-section">
        <div class="container">
            <div class="row">
                <div class="col-6 ps-0 pb-3">
                    <div class="bg-gold bg-sharp-no-left">
                        <div class="title-primary">
                            Hệ thống cửa hàng Nhân Sâm Hàn Quốc KGIN
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-12 col-lg-12 border-top">
                    <div class="row row-cols-5 pb-4 store-slider pt-3 ps-2">
                        <div class="col w-20">
                            <div class="store-wrap">
                                <a href=""  >
                                    <div class="thumbnail-wrap">
                                        <img src="${pageContext.request.contextPath}/inventory/images/cua-hang-1.jpg" alt="">
                                    </div>
                                </a>
                                <div class="info-store">
                                    <a href=""  >
                                        <div class="title">
                                            <p class="h3">Cửa Hàng 51 Võ Thị Sáu</p>
                                        </div>
                                    </a>
                                    <div class="address-wrap">
                                        <i class="fas fa-map-marker-alt"></i>
                                        <div class="address">
                                            <p>51 Võ Thị Sáu - Phường VTS - Quận 3 - TP.HCM</p>
                                        </div>
                                    </div>


                                    <div class="hotline-wrap">
                                        <i class="fas fa-phone-alt"></i>
                                        <div class="hotline">
                                            <div class="">
                                                Hotline:
                                            </div>
                                            <div class="phone-number">
                                                <a href="tel:19004625" rel="nofollow"  >
                                                    <p>1900.4625</p>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="hotline-wrap zalo-sms">
                                        <i class="far fa-comment-alt"></i>
                                        <div class="hotline">
                                            <div class="">
                                                Zalo, Sms:
                                            </div>
                                            <div class="phone-number">
                                                <a href="tel:0936319818" rel="nofollow"  >
                                                    <p>0936.319.818</p>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="address-link">
                                        <a href="https://goo.gl/maps/fLkgD7rpPrknqsYu8" rel="nofollow external noopener noreferrer" data-wpel-link="external" target="_blank">
                                            <i class="fas fa-map-marker-alt"></i>
                                            <div class="address">
                                                Xem bản đồ Google maps
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col w-20">
                            <div class="store-wrap">
                                <a href="">
                                    <div class="thumbnail-wrap">
                                        <img src="${pageContext.request.contextPath}/inventory/images/cua-hang-2.jpg" alt="">
                                    </div>
                                </a>
                                <div class="info-store">
                                    <a href=" ">
                                        <div class="title">
                                            <p class="h3">Cửa Hàng 57 Nguyễn Trãi</p>
                                        </div>
                                    </a>
                                    <div class="address-wrap">
                                        <i class="fas fa-map-marker-alt"></i>
                                        <div class="address">
                                            <p>577 Nguyễn Trãi - Thanh Xuân Nam - Thanh Xuân - Hà Nội </p>
                                        </div>
                                    </div>


                                    <div class="hotline-wrap">
                                        <i class="fas fa-phone-alt"></i>
                                        <div class="hotline">
                                            <div class="">
                                                Hotline:
                                            </div>
                                            <div class="phone-number">
                                                <a href="tel:19004625" rel="nofollow"  >
                                                    <p>1900.4625</p>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="hotline-wrap zalo-sms">
                                        <i class="far fa-comment-alt"></i>
                                        <div class="hotline">
                                            <div class="">
                                                Zalo, Sms:
                                            </div>
                                            <div class="phone-number">
                                                <a href="tel:0936319818" rel="nofollow"  >
                                                    <p>0936.319.818</p>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="address-link">
                                        <a href="https://goo.gl/maps/AKRkMDbYYqb1H9U97" rel="nofollow external noopener noreferrer" data-wpel-link="external" target="_blank">
                                            <i class="fas fa-map-marker-alt"></i>
                                            <div class="address">
                                                Xem bản đồ Google maps
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col w-20">
                            <div class="store-wrap">
                                <a href="">
                                    <div class="thumbnail-wrap">
                                        <img src="${pageContext.request.contextPath}/inventory/images/cua-hang-3.jpg" alt="">
                                    </div>
                                </a>
                                <div class="info-store">
                                    <a href=" ">
                                        <div class="title">
                                            <p class="h3">Cửa Hàng 149 Cầu Giấy</p>
                                        </div>
                                    </a>
                                    <div class="address-wrap">
                                        <i class="fas fa-map-marker-alt"></i>
                                        <div class="address">
                                            <p>149 Cầu Giấy - Quan Hoa - Cầu Giấy - Hà Nội</p>
                                        </div>
                                    </div>


                                    <div class="hotline-wrap">
                                        <i class="fas fa-phone-alt"></i>
                                        <div class="hotline">
                                            <div class="">
                                                Hotline:
                                            </div>
                                            <div class="phone-number">
                                                <a href="tel:19004625" rel="nofollow"  >
                                                    <p>1900.4625</p>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="hotline-wrap zalo-sms">
                                        <i class="far fa-comment-alt"></i>
                                        <div class="hotline">
                                            <div class="">
                                                Zalo, Sms:
                                            </div>
                                            <div class="phone-number">
                                                <a href="tel:0936319818" rel="nofollow"  >
                                                    <p>0936.319.818</p>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="address-link">
                                        <a href="https://goo.gl/maps/4HEVMWuJNEg4NWs27" rel="nofollow external noopener noreferrer" data-wpel-link="external" target="_blank">
                                            <i class="fas fa-map-marker-alt"></i>
                                            <div class="address">
                                                Xem bản đồ Google maps
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col w-20">
                            <div class="store-wrap">
                                <a href="">
                                    <div class="thumbnail-wrap">
                                        <img src="${pageContext.request.contextPath}/inventory/images/cua-hang-4.jpg" alt="">
                                    </div>
                                </a>
                                <div class="info-store">
                                    <a href=" ">
                                        <div class="title">
                                            <p class="h3">Cửa Hàng 21 Tây Sơn</p>
                                        </div>
                                    </a>
                                    <div class="address-wrap">
                                        <i class="fas fa-map-marker-alt"></i>
                                        <div class="address">
                                            <p>21 Tây Sơn - Quan Trung- Đống Đa - Hà Nội</p>
                                        </div>
                                    </div>


                                    <div class="hotline-wrap">
                                        <i class="fas fa-phone-alt"></i>
                                        <div class="hotline">
                                            <div class="">
                                                Hotline:
                                            </div>
                                            <div class="phone-number">
                                                <a href="tel:19004625" rel="nofollow"  >
                                                    <p>1900.4625</p>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="hotline-wrap zalo-sms">
                                        <i class="far fa-comment-alt"></i>
                                        <div class="hotline">
                                            <div class="">
                                                Zalo, Sms:
                                            </div>
                                            <div class="phone-number">
                                                <a href="tel:0936319818" rel="nofollow"  >
                                                    <p>0936.319.818</p>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="address-link">
                                        <a href="https://goo.gl/maps/gQBEp9YJfXVxPBAg7" rel="nofollow external noopener noreferrer" data-wpel-link="external" target="_blank">
                                            <i class="fas fa-map-marker-alt"></i>
                                            <div class="address">
                                                Xem bản đồ Google maps
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col w-20">
                            <div class="store-wrap">
                                <a href=" ">
                                    <div class="thumbnail-wrap">
                                        <img src="${pageContext.request.contextPath}/inventory/images/cua-hang-5.jpg" alt="">
                                    </div>
                                </a>
                                <div class="info-store">
                                    <a href=" ">
                                        <div class="title">
                                            <p class="h3">Cửa Hàng 121A Phố Huế</p>
                                        </div>
                                    </a>
                                    <div class="address-wrap">
                                        <i class="fas fa-map-marker-alt"></i>
                                        <div class="address">
                                            <p>121A Phố Huế - Hai Bà Trưng - Hà Nội (Ngã tư Phố Huế - Tuệ Tĩnh)</p>
                                        </div>
                                    </div>

                                    <div class="hotline-wrap">
                                        <i class="fas fa-phone-alt"></i>
                                        <div class="hotline">
                                            <div class="">
                                                Hotline:
                                            </div>
                                            <div class="phone-number">
                                                <a href="tel:19004625" rel="nofollow"  >
                                                    <p>1900.4625</p>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="hotline-wrap zalo-sms">
                                        <i class="far fa-comment-alt"></i>
                                        <div class="hotline">
                                            <div class="">
                                                Zalo, Sms:
                                            </div>
                                            <div class="phone-number">
                                                <a href="tel:0936319818" rel="nofollow"  >
                                                    <p>0936.319.818</p>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="address-link">
                                        <a href="https://goo.gl/maps/NJNFZLBJZkSV5tWY6" rel="nofollow external noopener noreferrer" data-wpel-link="external" target="_blank">
                                            <i class="fas fa-map-marker-alt"></i>
                                            <div class="address">
                                                Xem bản đồ Google maps
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<jsp:include page="/WEB-INF/view/shared/footer.jsp"/>
<div id="fb-root"></div>
<script> var contextPath = "${pageContext.request.contextPath}";</script>
<script async defer crossorigin="anonymous"
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0" nonce="Qoebijhj">
</script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/libs/swiper/swiper-bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/cloudinary-core/2.11.2/cloudinary-core-shrinkwrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/cloudinary/loading_path.js"></script>

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
<script>
    let websocket = null;
    $.ajax('${pageContext.request.contextPath}/api/v1/web-socket')
        .then((resp) => {
            console.log(resp)
            websocket = new WebSocket(resp+"/chat");
            websocket.onopen = function(message) {processOpen(message);};
            websocket.onmessage = function(message) {processMessage(message);};
            websocket.onclose = function(message) {processClose(message);};
            websocket.onerror = function(message) {processError(message);};

            function processOpen(message) {
                console.log('Server connect...')
                if (websocket.readyState == WebSocket.OPEN) {
                    websocket.send(JSON.stringify({
                        action: "get_last_chat"
                    }))
                }
            }
            function processMessage(message) {
                console.log(JSON.parse(message.data));
            }
            function processClose(message) {
            }
            function processError(message) {
            }
        })
    function sendMessage(myMessage) {
        if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
            websocket.send(myMessage);
        }
    }
</script>
</body>
</html>