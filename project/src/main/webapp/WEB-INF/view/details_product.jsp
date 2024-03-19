<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Thư viện ngoài (Luôn để đầu tiên) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/fontawesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/flickity/flickity.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">

    <!-- Style của project -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-card.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/details-product.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/breadcrumbs.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixed-buttons.css">


    <title>Slider</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/shared/cart.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/shared/header.jsp"></jsp:include>
<div class="breadcrumbs-wrap">
    <ul class="breadcrumbs">
        <li><a href="">Trang chủ</a></li>
        <li><a href="">Hồng sâm Hàn Quốc</a></li>
        <li><a href="">${requestScope.product.category.name}</a></li>
        <li><a href="">${requestScope.product.name}</a></li>
    </ul>
</div>
<section id="details-product">
    <div class="container">
        <div class="row">
            <div class="col-md-5">
                <div class="carousel-wrapper">
                    <!-- Flickity HTML init -->
                    <div class="carousel carousel-main" data-flickity='{"pageDots": false, "contain": true, "fullscreen": true, "wrapAround": true}'>
                        <c:forEach var="image" items="${requestScope.galleries}">
                            <div class="carousel-cell">
                                <img src="${pageContext.request.contextPath}/files/${image.path}" alt="">
                            </div>
                        </c:forEach>
                    </div>

                    <div class="carousel carousel-nav"
                         data-flickity='{ "asNavFor": ".carousel-main", "contain": true, "pageDots": false, "contain": true}'>
                        <c:forEach var="image" items="${requestScope.galleries}">
                            <div class="carousel-cell">
                                <img src="${pageContext.request.contextPath}/files/${image.path}" alt="">
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="col-md-7">
                <h2>${requestScope.product.name}</h2>
                <div class="rating-stars">
                    <span class="rating-score">4.8</span>
                    <div class="rating-empty">
                        <span>&#xf005;&#xf005;&#xf005;&#xf005;&#xf005;</span>
                        <div class="rating-fill" style="width: 50%;">&#xf005;&#xf005;&#xf005;&#xf005;&#xf005;</div>
                    </div>
                </div>
                <span class="sold-amount"><b>1.2k</b> Đã bán</span>
                <p>${requestScope.product.description}</p>
                <div class="row d-flex align-items-start">
                    <div class="col-md-6 pl-0">
                        <div class="products-stats">
                            <div class="stat-label">Quy cách</div>
                            <span class="stat-text">${requestScope.product.specification}</span>

                            <div class="stat-label">Mã sản phẩm</div>
                            <span class="stat-text">${requestScope.product.id}</span>

                            <div class="stat-label">Thương hiệu</div>
                            <span class="stat-text">${requestScope.product.brand}</span>
                            <c:set var="price" value="${product.price}"></c:set>
                            <c:set var="discountPrice" value="${(100 - product.discount.discountPercent) * product.price / 100}"></c:set>
                            <div class="stat-label">Giá gốc</div>
                            <span class="stat-text text-through">${product['getStringPrice'](price)}</span>
                            <div class="stat-label">Chương trình:</div>
                            <div>${requestScope.product.discount.name}</div>
                            <div class="stat-label">Giá khuyến mãi</div>
                            <h4>${product['getStringPrice'](discountPrice)}</h4>

                            <div class="stat-label">Số lượng</div>
                            <div class="number-field">
                                <div class="number-btn btn-left bg-gold bg-sharp-no-right">
                                    <span>-</span>
                                </div>
                                <div class="bg-gold">
                                    <input type="number" data-amount="1" value="1">
                                </div>
                                <div class="number-btn btn-right bg-gold bg-sharp-no-left">
                                    <span>+</span>
                                </div>
                            </div>
                        </div>
                        <div class="btn-submit bg-gold bg-sharp" data-cart-product="true" data-cart-action="add" data-cart-id="${product.id}" onclick="document.querySelector('.btn-submit').setAttribute('data-cart-amount' , $(numberField).find('input')[0].dataset.amount)" data-cart-amount="$(numberField).find('input')[0].dataset.amount">
                            <a >Mua hàng</a>
                        </div>
                    </div>
                    <div class="col-md-6 pl-0 d-flex flex-column align-items-center">
                        <div class="buying-offer bg-gold bg-sharp-5">
                            <div class="offer">
                                <div class="bg-gold icon">
                                    <i class="fa-regular fa-thumbs-up"></i>
                                </div>
                                <span class="offer-text">Cam kết hàng chính hãng 100%</span>
                            </div>
                            <div class="offer">
                                <div class="bg-gold icon">
                                    <i class="fa-solid fa-truck-fast"></i>
                                </div>
                                <span class="offer-text">Giao hàng toàn quốc</span>
                            </div>
                            <div class="offer">
                                <div class="bg-gold icon">
                                    <i class="fa-solid fa-money-bill-wave"></i>
                                </div>
                                <span class="offer-text">Miễn phí vận chuyển từ 500k</span>
                            </div>
                            <div class="offer">
                                <div class="bg-gold icon">
                                    <i class="fa-solid fa-handshake-angle"></i>
                                </div>
                                <span class="offer-text">Tư vấn nhiệt tình tận tâm</span>
                            </div>
                        </div>
                        <form action="" class="recall-form bg-gold bg-sharp" style="display: none">
                            <input class="bg-sharp-no-right input-field" type="text" placeholder="SĐT yêu cầu gọi lại">
                            <input class="bg-sharp-no-left btn-submit" type="submit" value="&#xf2a0;  Tư vấn tôi">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="tabs">
                    <div class="tablinks tablinks-is-active" data-tab-target="#tab-product-content">Mô tả sản phẩm</div>
                    <div class="tablinks" data-tab-target="#tab-product-guide">Địa chỉ mua hàng</div>
                    <div class="tablinks" data-tab-target="#tab-product-review">Đánh giá sản phẩm (${fn:length(requestScope.comments)})</div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="tab-content-wrapper">
                    <section class="tab-content tab-is-active" id="tab-product-content">
                        <c:out value="${requestScope.blog}" escapeXml="false"/>
                    </section>
                    <section class="tab-content" id="tab-product-guide">
                        <div class="container">
                            <div class="row">
                                <h3>Hướng dẫn mua hàng</h3>
                                <div class="col-md-6">
                                    <div class="guide-content">
                                        <p>Bạn có thể đặt hàng với K-GIN theo một trong các cách sau:</p>
                                        <p>1. Đặt hàng trực tiếp: thông qua chức năng Bán hàng online với trực tiếp trên website.</p>
                                        <p>2. Điện thoại trực tiếp: Hotline 19004625 (Zalo,sms: 0936.319.818)</p>
                                        <p>3. Mua hàng trực tiếp:</p>
                                        <p>Quý khách hàng có thể qua các địa chỉ sau để mua hàng trực tiếp – Mở cửa từ 8h đến 20h45′ tất cả các ngày trong tuần:</p>

                                        <b>TP Hồ Chí Minh</b>
                                        <ul class="store-addresses">
                                            <li class="address">
                                                <div class="store store-is-active" data-map-data="https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q=Th%C6%B0%20vi%E1%BB%87n%20Tr%C6%B0%E1%BB%9Dng%20%C4%90%E1%BA%A1i%20h%E1%BB%8Dc%20N%C3%B4ng%20L%C3%A2m%20TP.HCM,%20VQCR+GP6,%20Khu%20Ph%E1%BB%91%206,%20Th%E1%BB%A7%20%C4%90%E1%BB%A9c,%20Th%C3%A0nh%20ph%E1%BB%91%20H%E1%BB%93%20Ch%C3%AD%20Minh,%20Vi%E1%BB%87t%20Nam+(Tr%C6%B0%E1%BB%9Dng%20%C4%91%E1%BA%A1i%20h%E1%BB%8Dc%20n%C3%B4ng%20l%C3%A2m)&amp;t=h&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed">
                                                    51 Võ Thị Sáu - Phường VTS - Quận 3 - TP.HCM
                                                </div>
                                            </li>
                                        </ul>
                                        <b>Hà Nội</b>
                                        <ul class="store-addresses">
                                            <li class="address">
                                                <div class="store" data-map-data="https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q=V%C4%83n%20Ph%C3%B2ng%20BIDV%20Thi%C3%AAn%20L%C3%AD%20%C4%90%E1%BA%A1i%20H%E1%BB%8Dc%20N%C3%B4ng%20L%C3%A2m+(Tr%C6%B0%E1%BB%9Dng%20%C4%91%E1%BA%A1i%20h%E1%BB%8Dc%20n%C3%B4ng%20l%C3%A2m)&amp;t=h&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed">
                                                    21 Tây Sơn - Quan Trung- Đống Đa - Hà Nội
                                                </div>
                                            </li>
                                            <li class="address">
                                                <div class="store" data-map-data="https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q=B%C3%A3i%20Gi%E1%BB%AF%20Xe%20Sinh%20VI%C3%AAn%20N%C3%B4ng%20L%C3%A2m+(Tr%C6%B0%E1%BB%9Dng%20%C4%91%E1%BA%A1i%20h%E1%BB%8Dc%20n%C3%B4ng%20l%C3%A2m)&amp;t=h&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed">
                                                    121A Phố Huế - Hai Bà Trưng - Hà Nội (Ngã tư Phố Huế - Tuệ Tĩnh)
                                                </div>
                                            </li>
                                            <li class="address">
                                                <div class="store" data-map-data="https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q=Gi%E1%BA%A3ng%20%C4%91%C6%B0%E1%BB%9Dng%20R%E1%BA%A1ng%20%C4%90%C3%B4ng+(Tr%C6%B0%E1%BB%9Dng%20%C4%91%E1%BA%A1i%20h%E1%BB%8Dc%20n%C3%B4ng%20l%C3%A2m)&amp;t=h&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed">
                                                    149 Cầu Giấy - Quan Hoa - Cầu Giấy - Hà Nội
                                                </div>
                                            </li>
                                            <li class="address">
                                                <div class="store" data-map-data="https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q=Khoa%20Khoa%20H%E1%BB%8Dc%20NLU%20%C4%90%E1%BA%A1i%20H%E1%BB%8Dc%20N%C3%B4ng%20L%C3%A2m+(Tr%C6%B0%E1%BB%9Dng%20%C4%91%E1%BA%A1i%20h%E1%BB%8Dc%20n%C3%B4ng%20l%C3%A2m)&amp;t=h&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed">
                                                    577 Nguyễn Trãi - Thanh Xuân Nam - Thanh Xuân - Hà Nội ( Đối diện Pico Trung Văn)
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div style="width: 100%">
                                        <iframe class="google-map-frame" width="100%" height="600" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q=Th%C6%B0%20vi%E1%BB%87n%20Tr%C6%B0%E1%BB%9Dng%20%C4%90%E1%BA%A1i%20h%E1%BB%8Dc%20N%C3%B4ng%20L%C3%A2m%20TP.HCM,%20VQCR+GP6,%20Khu%20Ph%E1%BB%91%206,%20Th%E1%BB%A7%20%C4%90%E1%BB%A9c,%20Th%C3%A0nh%20ph%E1%BB%91%20H%E1%BB%93%20Ch%C3%AD%20Minh,%20Vi%E1%BB%87t%20Nam+(Tr%C6%B0%E1%BB%9Dng%20%C4%91%E1%BA%A1i%20h%E1%BB%8Dc%20n%C3%B4ng%20l%C3%A2m)&amp;t=h&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed">
                                        </iframe>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                    <section class="tab-content" id="tab-product-review">
                        <div class="container">
                            <div class="row">
                                <div class="col-7">
                                    <div class="review-comments">
                                        <h4>Đánh giá cho ${requestScope.product.name}</h4>
                                        <div class="comments">
                                            <c:if test="${not empty requestScope.comments}">
                                                <c:forEach var="rating" items="${requestScope.comments}">
                                                    <div class="comment-field">
                                                        <p class="comment-author"><b>${rating.user.firstName} ${rating.user.lastName}</b> - ${rating['dateTimeToString'](rating.updateAt)}</p>
                                                        <div class="rating-stars">
                                                            <span class="rating-score">${rating.star}</span>
                                                            <div class="rating-empty">
                                                                <span></span>
                                                                <div class="rating-fill" style="width: ${rating.star * 20}%;"></div>
                                                            </div>
                                                        </div>
                                                        <p class="comment-content">"${rating.content}"</p>
                                                    </div>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${empty requestScope.comments}">
                                                <div class="fw-semibold text-center text-success">
                                                    Sản phẩm này chưa có đánh giá nào...
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-5 bg-gold bg-sharp-5 align-self-start">
                                    <c:if test="${empty sessionScope.user}">
                                        <form action="rating" class="review-product-form" method="post">
                                            <div class="text-center txt-danger fw-semibold">
                                                Xin lỗi, bạn cần <a href="${pageContext.request.contextPath}/login">Đăng nhập</a> để đánh giá sản phẩm...
                                            </div>
                                        </form>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.user}">
                                        <form action="rating" class="review-product-form" method="post">
                                            <div class="form-title">Thêm đánh giá sản phẩm</div>
                                            <p class="title-field">Đánh giá của bạn *</p>
                                            <div class="rating-field">
                                                <input type="hidden" name="product" value="${requestScope.product.id}">
                                                <input type="radio" id="star5" name="rating" value="5" />
                                                <label class = "radio-star" for="star5" title="Awesome - 5 stars"></label>

                                                <input type="radio" id="star4" name="rating" value="4" />
                                                <label class = "radio-star" for="star4" title="Pretty good - 4 stars"></label>

                                                <input type="radio" id="star3" name="rating" value="3" />
                                                <label class = "radio-star" for="star3" title="Meh - 3 stars"></label>

                                                <input type="radio" id="star2" name="rating" value="2" />
                                                <label class = "radio-star" for="star2" title="Kinda bad - 2 stars"></label>

                                                <input type="radio" id="star1" name="rating" value="1" />
                                                <label class = "radio-star" for="star1" title="Sucks big time - 1 star"></label>
                                            </div>
                                            <p class="title-field">Nhận xét của bạn</p>
                                            <textarea name="content"></textarea>
                                            <div class="bg-gold bg-sharp btn-submit">
                                                <input type="submit" value="Gửi đánh giá  &#xf1d8;">
                                            </div>
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </div>
    <div class="bg-gold bg-sharp mt-3">
        <div class="title-primary">
            Sản phẩm liên quan
        </div>
    </div>
    <div class="container bg-transparent">
        <div class="row">
            <c:forEach var="product" items="${requestScope.related}">
                <div class="col-md-3">
                    <div class="product-card">
                        <a href="${pageContext.request.contextPath}/product?id=${product.id}">
                            <div class="product-img">
                                <img src="${pageContext.request.contextPath}/files/${product.thumbnail.path}" alt="">
                            </div>

                            <div class="product-name">
                                <p>${product.name}</p>
                            </div>
                        </a>
                        <div class="product-price-wrapper">
                            <div class="discount-label">-${product.discount.discountPercent}%</div>
                            <div class="price">
                                <c:set var="price" value="${product.price}"></c:set>
                                <c:set var="discountPrice" value="${(100 - product.discount.discountPercent) * product.price / 100}"></c:set>
                                <p class="m-price">${product['getStringPrice'](price)}</p>
                                <p class="c-price">${product['getStringPrice'](discountPrice)}</p>
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
</section>
<jsp:include page="/WEB-INF/view/shared/footer.jsp"></jsp:include>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous"
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0" nonce="Qoebijhj">
</script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/libs/flickity/flickity.pkgd.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
<script src="${pageContext.request.contextPath}/js/details_product.js"></script>

<script>
    const gMap = $('.google-map-frame');
    const stores = $('#tab-product-guide .address .store');
    $(stores).on('click', function() {
        stores.removeClass('store-is-active');
        $(this).addClass('store-is-active');
        const mapData = this.dataset.mapData;
        gMap[0].src = this.dataset.mapData;
    })
</script>
</body>
</html>
