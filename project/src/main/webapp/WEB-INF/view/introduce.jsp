<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <!-- Style của project -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixed-buttons.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-slider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button-title.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-card.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-filter.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-category.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-featured-products_v01.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-about.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-store.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/introduce.css">

    <title>Trang giới thiệu</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/shared/cart.jsp"></jsp:include>

<!-- Header -->
<jsp:include page="/WEB-INF/view/shared/header.jsp"></jsp:include>
<section class="header-about m-0" style="background-image: url('${pageContext.request.contextPath}/inventory/images/introduce/bg-about-us.jpg')">
    <div class="header-wrap">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-3 col-lg-3 name-wrap">
                    <a href="${pageContext.request.contextPath}/introduce#intro" tabindex="1" class="" rel="nofollow">
                        <div class="menu-name">Giới thiệu công ty</div>
                    </a>
                </div>
                <div class="col-12 col-md-3 col-lg-3 name-wrap">
                    <a href="${pageContext.request.contextPath}/introduce#intro-product" tabindex="2" class="" rel="nofollow">
                        <div class="menu-name">Giới thiệu sản phẩm</div>
                    </a>
                </div>
                <div class="col-12 col-md-3 col-lg-3 name-wrap">
                    <a href="${pageContext.request.contextPath}/introduce#store" tabindex="3" class="" rel="nofollow">
                        <div class="menu-name">Hệ thống cửa hàng</div>
                    </a>
                </div>
                <div class="col-12 col-md-3 col-lg-3 name-wrap">
                    <a href="${pageContext.request.contextPath}/introduce#album" tabindex="4" class="" rel="nofollow">
                        <div class="menu-name">Thư viện ảnh</div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="intro" class="m-0" style="background-image: url('${pageContext.request.contextPath}/inventory/images/introduce/bg-about-1.jpg')">
    <div class="block-content">
        <div class="container">
            <div class="entry-content">
                <h2 style="text-align: center;">Giới thiệu về thương hiệu K-GIN</h2>
                <p>Xin kính chào quý khách!<br>
                    Chào mừng quý khách đến với Hồng sâm Hàn Quốc K-GIN! Được thành lập từ năm 2008, K-GIN luôn mang trong mình sứ mệnh phục vụ quý khách hàng những sản phẩm thảo dược Hàn Quốc có chất lượng tốt nhất. Chúng tôi nỗ lực tạo dựng niềm tin nơi khách hàng bằng chất lượng sản phẩm và thái độ phục vụ tận tình nhất. Với K-GIN, khách hàng không phải là thượng đế mà là đồng nghiêp, là người bạn, người thân trong gia đình!</p>
                <p>Đến nay, công ty đã xây dựng được hệ thống gồm 5 showroom bán hàng trực tiếp tại Hà Nội và thành phố Hồ Chí Minh. Bên cạnh đó, K-GIN cam kết phục vụ quý khách hàng trên toàn quốc qua website bán hàng online Kgin.com.vn, Hotline<span style="color: #bf1e2e;"><strong>&nbsp;19004625 </strong></span>(Zalo, sms:&nbsp;<span style="color: #bf1e2e;"><strong>&nbsp;0936.319.818</strong></span>).</p>
                <p>K-GIN trực tiếp phân phối sản phẩm của các thương hiệu nổi tiếng như: Taewoong Food, Bio Science, Pocheon Insam, Daedoong,… Ngoài ra, K-GIN đang thúc đẩy việc hợp tác với các thương hiệu hồng sâm nội địa nổi tiếng tại Hàn Quốc, điển hình là Cheon Ji Yang, hứa hẹn sẽ mang tới cho khách hàng những sản phẩm có chất lượng hàng đầu, bồi bổ sức khỏe tốt nhất.</p>
                <p style="text-align: center;"><span style="color: #ff9900;"><strong>” Nếu bạn mất tiền, bạn mất rất ít.</strong></span><br>
                    <span style="color: #ff9900;"><strong>Nếu bạn mất uy tín, bạn mất rất nhiều.</strong></span><br>
                    <span style="color: #ff9900;"><strong>Nếu bạn mất sức khỏe, bạn mất tất cả”</strong></span></p>
                <p>Đây là kim chỉ nam cho mọi hoạt động của công ty, với sự nỗ lực không ngừng vì khách hàng, K-GIN tự hào mang lại sức khỏe và niềm tin cho khách hàng.</p>
            </div>
        </div>
    </div>
</section>

<section id="intro-product" class="m-0">
    <div class="block-content">
        <div class="container">
            <div class="entry-content">
                <p style="text-align: center;"><span style=" font-size: 18pt;"><strong>Giới thiệu về các dòng sản phẩm mà K-GIN đang kinh doanh</strong></span></p>
                <p>K-GIN hiện đang nhập khẩu trực tiếp và phục vụ quý khách hàng với 4 dòng sản phẩm chính bao gồm: Hồng sâm Hàn Quốc, Nấm linh chi Hàn Quốc, Đong trùng hạ thảo Hàn Quốc, và các chế phẩm từ thảo dược Hàn Quốc khác</p>
                <p><strong>1. Sâm Hàn Quốc</strong><br>
                    Xứ sở Kim Chi được biết đến như là thủ phủ của nhân sâm, thiên thời – địa lợi – nhân hòa đã tạo nên những củ nhân sâm tươi có chất lượng hảo hạng nhất. Với kinh nghiệm hàng ngàn năm trồng, khai thác và chế biến nhân sâm, các bổ phẩm cho sức khỏe từ nhân sâm Hàn Quốc đang được ưa chuộng trên toàn thế giới.</p>
                <p>K-GIN tự hào mang đến cho quý khách hàng các bổ phẩm cao cấp từ nhân sâm tươi Hàn Quốc: Hồng sâm khô, Cao Hồng sâm, Hồng sâm Tẩm mật ong, Tinh chất hồng sâm,… Đây đều là những sản phẩm được khách hàng lựa chọn và tin dùng, rất hiệu quả trong việc tăng cường sức khỏe, phòng chống bệnh tật và điều hòa cơ thể.</p>
                <p><strong>2. Linh chi Hàn Quốc</strong><br>
                    Nấm linh chi hay còn được gọi với nhiều tên ưu ái khác như: nấm trường thọ, vạn niên nhung,… Nấm linh chi có tác dụng làm chậm quá trình lão hóa, bảo vệ cơ thể khỏi các tác nhân độc tố từ môi trường bên ngoài. Nhiều công trình nghiên cứu và công bố khoa học đã chứng minh về thành phần dược tính cũng như tác dụng thần kỳ của nấm linh chi đối với sức khỏe.</p>
                <p>Công ty chúng tôi hiện đang phân phối tới quý khách hàng các sản phẩm bao gồm: nấm linh chi khô, cao linh chi và trà linh chi.</p>
                <p><strong>3. Đông trùng hạ thảo Hàn Quốc</strong><br>
                    Đông trùng hạ thảo Hàn Quốc được lấy giống từ Đông trùng hạ thảo Tây Tạng, nuôi trồng dựa trên trên quy trình công nghệ hiên đại và tiên tiến hàng đầu, đảm bảo chất lượng tốt nhất. Đông trùng hạ thảo có công dụng bồi bổ sức khỏe nhanh chóng, tăng cường hoạt động của các chức năng trong cơ thể. Bên cạnh đó, loại thần dược này còn được biết tới như một loại bảo vật hồi xuân sinh lực.</p>
                <p>K-GIN hiện đang kinh doanh các sản phẩm chính từ Đông trùng hạ thảo bao gồm tinh chất đông trùng hạ thảo và đông trùng hạ thảo dạng viên.</p>
                <p><strong>4. Các bổ phẩm từ thảo dược thiên nhiên Hàn Quốc</strong><br>
                    Bên cạnh các loại thảo dược như sâm, linh chi, đông trùng hạ thảo, Hàn Quốc còn có vô số các loại thảo dược và các chế phẩm có lợi cho sức khỏe khác được K-GIN kinh doanh như: An cung ngưu hoàng hoàn, bổ gan giải rượu Hàn Quốc,…</p>
            </div>
        </div>
    </div>
</section>

<section id="store">
    <div class="container">
        <div class="col-12 d-flex justify-content-center">
            <div class="bg-gold bg-sharp">
                <div class="title-primary">Hệ thống cửa hàng</div>
            </div>
        </div>
        <li>
        <div class="map-wrap">
            <div class="google-maps">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.308529904534!2d106.68973631450184!3d10.787664992313925!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752f3310494837%3A0x5d289c4b36cf8f20!2zNTEgVsO1IFRo4buLIFPDoXUsIFBoxrDhu51uZyA2LCBRdeG6rW4gMywgVGjDoG5oIHBo4buRIEjhu5MgQ2jDrSBNaW5oLCBWaeG7h3QgTmFt!5e0!3m2!1svi!2s!4v1632105477929!5m2!1svi!2s" allowfullscreen="" loading="lazy">
                </iframe>
            </div>
        </div>

        <div class="row d-flex store-about m-0">
            <div class="col-12 col-md-6 col-lg-6 store-details">
                <div class="heading-store">
                    <i class="fas fa-store"></i> Cửa hàng Hồng Sâm
                    KGIN 51 Võ Thị Sáu - Phường VTS - Quận 3 - TP.HCM
                </div>
                <div class="entry-content">
                    <p>Giao hàng nội thành: Miễn phí vận chuyển với đơn hàng từ 500.000đ</p>
                    <p>Giao nhanh trong 2h – Nhận hàng thanh toán</p>
                    <p>Hotline: 19004625 (Zalo, sms: 0936.319.818)</p>
                </div>
            </div>
            <div class="col-12 col-md-6 col-lg-6">
                <div class="heading-store">
                    Hệ thống cửa hàng
                </div>
                <div class="address-showroom pr-2">
                    <div class="map-kgin" google_link="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.308529904534!2d106.68973631450184!3d10.787664992313925!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752f3310494837%3A0x5d289c4b36cf8f20!2zNTEgVsO1IFRo4buLIFPDoXUsIFBoxrDhu51uZyA2LCBRdeG6rW4gMywgVGjDoG5oIHBo4buRIEjhu5MgQ2jDrSBNaW5oLCBWaeG7h3QgTmFt!5e0!3m2!1svi!2s!4v1632105477929!5m2!1svi!2s" name-story="Cửa hàng 51 Võ Thị Sáu - Phường VTS - Quận 3 - TP.HCM" description="<p>Giao hàng nội thành: Miễn phí vận chuyển với đơn hàng từ 500.000đ</p>
<p>Giao nhanh trong 2h – Nhận hàng thanh toán</p>
<p>Hotline:&nbsp;19004625 (Zalo, sms:&nbsp;<a rel=&quot;nofollow&quot;>0936.319.818</a>)</p>
">
                        <div class="kgin-address">
                            <i class="fas fa-store"></i>   Cửa hàng Hồng Sâm KGIN
                            51 Võ Thị Sáu - Phường VTS - Quận 3 - TP.HCM                            </div>
                    </div>
                </div>
                <div class="address-showroom pr-2">
                    <div class="map-kgin" google_link="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d232.8230510408211!2d105.79543430734022!3d20.98586899999999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x46b53db68d7556fe!2zTcWpIGLhuqNvIGhp4buDbSBOZ3V54buFbiBUcsOjaSwgSMOgIE7hu5lp!5e0!3m2!1svi!2s!4v1632367768490!5m2!1svi!2s" name-story="Cửa hàng 577 Nguyễn Trãi - Thanh Xuân Nam - Thanh Xuân - Hà Nội ( Đối diện Pico Trung Văn)" description="<p>Giao hàng nội thành: Miễn phí vận chuyển với đơn hàng từ 500.000đ</p>
<p>Giao nhanh trong 2h – Nhận hàng thanh toán</p>
<p>Hotline:&nbsp;19004625 (Zalo, sms:&nbsp;<a rel=&quot;nofollow&quot;>0936.319.818</a>)</p>
">
                        <div class="kgin-address">
                            <i class="fas fa-store"></i>   Cửa hàng Hồng Sâm KGIN
                            577 Nguyễn Trãi - Thanh Xuân Nam - Thanh Xuân - Hà Nội ( Đối diện Pico Trung Văn)                            </div>
                    </div>
                </div>
                <div class="address-showroom pr-2">
                    <div class="map-kgin" google_link="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.002324676124!2d105.79649131493268!3d21.03259298599624!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab46d896898f%3A0xffcb9a9c3c5ffc27!2zMTQ5IEPhuqd1IEdp4bqleSwgUXVhbiBIb2EsIEPhuqd1IEdp4bqleSwgSMOgIE7hu5lp!5e0!3m2!1svi!2s!4v1631960913022!5m2!1svi!2s" name-story="Cửa hàng 149 Cầu Giấy - Quan Hoa - Cầu Giấy - Hà Nội" description="<p>Giao hàng nội thành: Miễn phí vận chuyển với đơn hàng từ 500.000đ</p>
<p>Giao nhanh trong 2h – Nhận hàng thanh toán</p>
<p>Hotline:&nbsp;19004625 (Zalo, sms:&nbsp;<a rel=&quot;nofollow&quot;>0936.319.818</a>)</p>
">
                        <div class="kgin-address">
                            <i class="fas fa-store"></i>   Cửa hàng Hồng Sâm KGIN
                            149 Cầu Giấy - Quan Hoa - Cầu Giấy - Hà Nội                            </div>
                    </div>
                </div>
                <div class="address-showroom pr-2">
                    <div class="map-kgin" google_link="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.4889604683403!2d105.82457541493245!3d21.013112786006896!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab7f0ff0c7df%3A0xb294769333f31a1a!2zMjEgVMOieSBTxqFuLCBRdWFuZyBUcnVuZywgxJDhu5FuZyDEkGEsIEjDoCBO4buZaQ!5e0!3m2!1svi!2s!4v1632105003241!5m2!1svi!2s" name-story="Cửa hàng 21 Tây Sơn - Quan Trung- Đống Đa - Hà Nội" description="<p>Giao hàng nội thành: Miễn phí vận chuyển với đơn hàng từ 500.000đ</p>
<p>Giao nhanh trong 2h – Nhận hàng thanh toán</p>
<p>Hotline:&nbsp;19004625 (Zalo, sms:&nbsp;<a rel=&quot;nofollow&quot;>0936.319.818</a>)</p>
">
                        <div class="kgin-address">
                            <i class="fas fa-store"></i>   Cửa hàng Hồng Sâm KGIN
                            21 Tây Sơn - Quan Trung- Đống Đa - Hà Nội                            </div>
                    </div>
                </div>
                <div class="address-showroom pr-2">
                    <div class="map-kgin" google_link="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.433289537731!2d105.84948761464824!3d21.01534218600558!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab8cdac90f65%3A0xb88969dcf1b8773b!2zMTIxYSBQaOG7kSBIdeG6vywgTmfDtCBUaMOsIE5o4bqtbSwgSGFpIELDoCBUcsawbmcsIEjDoCBO4buZaSAxMTIyMDAsIFZp4buHdCBOYW0!5e0!3m2!1svi!2s!4v1632105083076!5m2!1svi!2s" name-story="Cửa hàng 121A Phố Huế - Hai Bà Trưng - Hà Nội (Ngã tư Phố Huế - Tuệ Tĩnh)" description="<p>Giao hàng nội thành: Miễn phí vận chuyển với đơn hàng từ 500.000đ</p>
<p>Giao nhanh trong 2h – Nhận hàng thanh toán</p>
<p>Hotline:&nbsp;19004625 (Zalo, sms:&nbsp;<a rel=&quot;nofollow&quot;>0936.319.818</a>)</p>
">
                        <div class="kgin-address">
                            <i class="fas fa-store"></i>   Cửa hàng Hồng Sâm KGIN
                            121A Phố Huế - Hai Bà Trưng - Hà Nội (Ngã tư Phố Huế - Tuệ Tĩnh)                            </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>

<section id="album">
    <div class="container">
        <div class="col-12 d-flex justify-content-center">
            <div class="bg-gold bg-sharp">
                <div class="title-primary">Thư viện ảnh</div>
            </div>
        </div>
        <li>
        <div class="row d-flex">
            <div class="col-6 col-md-3 col-lg-3 pb-5">
                <div class="album-item">
                    <a href="${pageContext.request.contextPath}/#" data-wpel-link="internal">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/inventory/images//introduce/le-hoi-sam-kgin-1.jpg" alt="">
                        </div>
                        <div class="name-album pt-3">
                            Hội chợ Nhân Sâm Geumsan Hàn Quốc                            </div>
                    </a>

                </div>
            </div>

            <div class="col-6 col-md-3 col-lg-3 pb-5">
                <div class="album-item">
                    <a href="${pageContext.request.contextPath}/#" data-wpel-link="internal">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/inventory/images//introduce/sam-tuoi-ngam-mat-ong-1.jpg" alt="">
                        </div>
                        <div class="name-album pt-3">
                            Cách làm nhân sâm tươi ngâm mật ong                            </div>
                    </a>

                </div>
            </div>

            <div class="col-6 col-md-3 col-lg-3 pb-5">
                <div class="album-item">
                    <a href="${pageContext.request.contextPath}/#" data-wpel-link="internal">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/inventory/images//introduce/cach-lam-sam-chien-1.jpg" alt="">
                        </div>
                        <div class="name-album pt-3">
                            Cách làm món nhân sâm chiên Hàn Quốc                            </div>
                    </a>

                </div>
            </div>

            <div class="col-6 col-md-3 col-lg-3 pb-5">
                <div class="album-item">
                    <a href="${pageContext.request.contextPath}/home" data-wpel-link="internal">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/inventory/images//introduce/mon-an-tu-dtht.jpg" alt="">
                        </div>
                        <div class="name-album pt-3">
                            Món ăn từ Đông Trùng Hạ Thảo                            
                        </div>
                    </a>

                </div>
            </div>

            <div class="col-6 col-md-3 col-lg-3 pb-5">
                <div class="album-item">
                    <a href="${pageContext.request.contextPath}/home" data-wpel-link="internal">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/inventory/images//introduce/chao-sam.jpg" alt="">
                        </div>
                        <div class="name-album pt-3">
                            Món ăn từ Nhân Sâm Hàn Quốc                            </div>
                    </a>

                </div>
            </div>

            <div class="col-6 col-md-3 col-lg-3 pb-5">
                <div class="album-item">
                    <a href="${pageContext.request.contextPath}/home" data-wpel-link="internal">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/inventory/images//introduce/cao-3-lo-14.jpg" alt="">
                        </div>
                        <div class="name-album pt-3">
                            Review về sản phẩm Cao Hồng Sâm lên men Hàn Quốc cao cấp                            </div>
                    </a>

                </div>
            </div>

            <div class="col-6 col-md-3 col-lg-3 pb-5">
                <div class="album-item">
                    <a href="${pageContext.request.contextPath}/home" data-wpel-link="internal">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/inventory/images//introduce/nuoc-sam-100-3-1.jpg" alt="">
                        </div>
                        <div class="name-album pt-3">
                            Review về sản phẩm Nước hồng sâm Pocheon Hàn Quốc 30 gói                            </div>
                    </a>

                </div>
            </div>

            <div class="col-6 col-md-3 col-lg-3 pb-5">
                <div class="album-item">
                    <a href="${pageContext.request.contextPath}/#" data-wpel-link="internal">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/inventory/images//introduce/VTS-1.jpg" alt="">
                        </div>
                        <div class="name-album pt-3">
                            Cửa hàng Hồng Sâm Hàn Quốc K-Gin 51 Võ Thị Sáu, P. Võ Thị Sáu, Q3, HCM                            </div>
                    </a>

                </div>
            </div>

            <div class="col-6 col-md-3 col-lg-3 pb-5">
                <div class="album-item">
                    <a href="${pageContext.request.contextPath}/#" data-wpel-link="internal">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/inventory/images//introduce/577-nguyen-trai-1.jpg" alt="">
                        </div>
                        <div class="name-album pt-3">
                            Cửa hàng Hồng Sâm Hàn Quốc K-Gin 577 Nguyễn Trãi, HN                            </div>
                    </a>

                </div>
            </div>

            <div class="col-6 col-md-3 col-lg-3 pb-5">
                <div class="album-item">
                    <a href="${pageContext.request.contextPath}/#" data-wpel-link="internal">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/inventory/images//introduce/149-cau-giay.jpg" alt="">
                        </div>
                        <div class="name-album pt-3">
                            Cửa hàng Hồng Sâm Hàn Quốc K-Gin 149 Cầu Giấy, HN                            </div>
                    </a>

                </div>
            </div>

            <div class="col-6 col-md-3 col-lg-3 pb-5">
                <div class="album-item">
                    <a href="${pageContext.request.contextPath}/#" data-wpel-link="internal">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/inventory/images//introduce/21-tay-son.jpg" alt="">
                        </div>
                        <div class="name-album pt-3">
                            Cửa hàng Hồng Sâm Hàn Quốc K-Gin 21 Tây Sơn, HN                            </div>
                    </a>

                </div>
            </div>

            <div class="col-6 col-md-3 col-lg-3 pb-5">
                <div class="album-item">
                    <a href="${pageContext.request.contextPath}/#" data-wpel-link="internal">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/inventory/images//introduce/cuahangsamhanquococaugiau.jpg" alt="">
                        </div>
                        <div class="name-album pt-3">
                            Cửa hàng Hồng Sâm Hàn Quốc K-Gin 121A Phố Huế, HN                            </div>
                    </a>

                </div>
            </div>

        </div>


    </div>
</section>



<footer id="main-footer-section">
    <div class="footer-guide">
        <div class="guide-title">thông tin liên hệ</div>
        <div class="guide-title">hệ thống cửa hàng</div>
        <div class="guide-title">danh mục sản phẩm</div>

        <div class="guide-content">
            <div class="contact-wrap">
                <i class="fa-solid fa-phone"></i>
                <span>Hotline: 1900.1234</span>
            </div>
            <div class="contact-wrap">
                <i class="fa-regular fa-comment-dots"></i>
                <span>Zalo, Sms: 0936.565.257</span>
            </div>
            <div class="contact-wrap">
                <i class="fa-regular fa-envelope"></i>
                <span>Email: nqat0919@gmail.com</span>
            </div>
            <strong>Mua hàng trực tiếp tại cửa hàng</strong>
            <div class="contact-wrap">
                <i class="fa-regular fa-clock"></i>
                <span>Thời gian mở cửa: 8h - 21h</span>
            </div>
            <strong>Phương thức thanh toán</strong>
            <img src="${pageContext.request.contextPath}/inventory/images//payment.webp" alt="" style="width: 400px;">
        </div>
        <div class="guide-content">
            <strong>Tại Hà Nội:</strong>
            <ul class="address-list">
                <li class="address">Cửa hàng 1: 21 Tây Sơn - Quan Trung- Đống Đa - Hà Nội</li>
                <li class="address">Cửa hàng 2: 121A Phố Huế - Hai Bà Trưng - Hà Nội (Ngã tư Phố Huế - Tuệ Tĩnh)</li>
                <li class="address">Cửa hàng 3: 149 Cầu Giấy - Quan Hoa - Cầu Giấy - Hà Nội</li>
                <li class="address">Cửa hàng 4: 577 Nguyễn Trãi - Thanh Xuân Nam - Thanh Xuân - Hà Nội ( Đối diện Pico Trung Văn)</li>
            </ul>
            <strong>Tại Hồ Chí Minh:</strong>
            <ul class="address-list">
                <li class="address">Cửa hàng 5: 51 Võ Thị Sáu - Phường VTS - Quận 3 - Thành phố Hồ Chí Minh</li>
            </ul>
        </div>
        <div class="guide-content">
            <div class="portfolio-product">
                <div class="product-img">
                    <img src="${pageContext.request.contextPath}/inventory/images//nuoc-hong-sam.png" alt="">
                </div>
                <div class="product-name">
                    <a href="${pageContext.request.contextPath}/categogy-detail.html">Nước hồng sâm</a> <!---Note sửa lại href-->
                </div>
            </div>
            <div class="portfolio-product">
                <div class="product-img">
                    <img src="${pageContext.request.contextPath}/inventory/images//linh-chi-han-quoc.webp" alt="">
                </div>
                <div class="product-name">
                    <a href="${pageContext.request.contextPath}/categogy-detail.html">Cao hồng sâm</a> <!---Note sửa lại href-->
                </div>
            </div>
            <div class="portfolio-product">
                <div class="product-img">
                    <img src="${pageContext.request.contextPath}/inventory/images//sam-kho.png" alt="">
                </div>
                <div class="product-name">
                    <a href="${pageContext.request.contextPath}/categogy-detail.html">Sâm khô</a> <!---Note sửa lại href-->
                </div>
            </div>
            <div class="portfolio-product">
                <div class="product-img">
                    <img src="${pageContext.request.contextPath}/inventory/images//sam-mat-ong.png" alt="">
                </div>
                <div class="product-name">
                    <a href="${pageContext.request.contextPath}/categogy-detail.html">Sâm mật ong</a> <!---Note sửa lại href-->
                </div>
            </div>
            <div class="portfolio-product">
                <div class="product-img">
                    <img src="${pageContext.request.contextPath}/inventory/images//hac-sam-han-quoc.png" alt="">
                </div>
                <div class="product-name">
                    <a href="${pageContext.request.contextPath}/categogy-detail.html">Hắc sâm</a> <!---Note sửa lại href-->
                </div>
            </div>
            <div class="portfolio-product">
                <div class="product-img">
                    <img src="${pageContext.request.contextPath}/inventory/images//sam-tuoi.png" alt="">
                </div>
                <div class="product-name">
                    <a href="${pageContext.request.contextPath}/categogy-detail.html">nhân sâm tươi</a> <!---Note sửa lại href-->
                </div>
            </div>
            <div class="portfolio-product">
                <div class="product-img">
                    <img src="${pageContext.request.contextPath}/inventory/images//ruou-sam.png" alt="">
                </div>
                <div class="product-name">
                    <a href="${pageContext.request.contextPath}/categogy-detail.html">Rượu sâm Linh Chi</a> <!---Note sửa lại href-->
                </div>
            </div>
            <div class="portfolio-product">
                <div class="product-img">
                    <img src="${pageContext.request.contextPath}/inventory/images//tra-sam.webp" alt="">
                </div>
                <div class="product-name">
                    <a href="${pageContext.request.contextPath}/categogy-detail.html">Trà hồng sâm</a> <!---Note sửa lại href-->
                </div>
            </div>
            <div class="portfolio-product">
                <div class="product-img">
                    <img src="${pageContext.request.contextPath}/inventory/images//vien-hong-sam.webp" alt="">
                </div>
                <div class="product-name">
                    <a href="${pageContext.request.contextPath}/categogy-detail.html">viên hồng sâm</a> <!---Note sửa lại href-->
                </div>
            </div>
        </div>
    </div>
    <div class="footer-contact">
        <div class="contact-title">
            <ul class="title-list">
                <li class="title-item">Trang chủ</li>
                <li class="title-item">Tin tức</li>
                <li class="title-item">Liên hệ</li>
            </ul>
        </div>

        <div class="contact-title">
            <strong>Mua sắm tiện lợi</strong>
        </div>

        <div class="contact-title">
            <strong>Fanpage Facebook</strong>
        </div>

        <div class="contact-content">
            <div class="contact-info-wrapper">
                <div class="img-logo" >
                    <img src="icon/logo.png" alt="">
                </div>
                <div class="contact-info-content">
                    <p class="info-text">sâm hàn quốc Cheong Kwan Jang</p>
                    <div class="contact-wrap">
                        <i class="fa-solid fa-phone"></i>
                        <span>Hotline: 1900.1234</span>
                    </div>
                    <div class="contact-wrap">
                        <i class="fa-regular fa-comment-dots"></i>
                        <span>Zalo, Sms: 0936.565.257</span>
                    </div>
                    <div class="contact-wrap">
                        <i class="fa-regular fa-envelope"></i>
                        <span>Email: nqat0919@gmail.com</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="contact-content">
            <div class="intro-wrapper">
                <div class="intro-col">
                    <div class="contact-wrap">
                        <i class="fas fa-edit" aria-hidden="true"></i>
                        <a href="${pageContext.request.contextPath}/#">Hướng dẫn mua hàng</a>
                    </div>
                    <div class="contact-wrap">
                        <i class="fas fa-map" aria-hidden="true"></i>
                        <a href="${pageContext.request.contextPath}/#">Bản đồ đến showroom</a>
                    </div>
                    <div class="contact-wrap">
                        <i class="fab fa-cc-visa" aria-hidden="true"></i>
                        <a href="${pageContext.request.contextPath}/details_product.html">Thông tin hàng hóa chi tiết</a>
                    </div>
                    <div class="contact-wrap">
                        <i class="fas fa-calendar-check" aria-hidden="true"></i>
                        <a href="${pageContext.request.contextPath}/privacy.html#chinh_sach_bao_hanh_doi_tra_va_hoan_tien">Quy định về việc đổi trả hàng</a>
                    </div>
                </div>
                <div class="intro-col">
                    <div class="contact-wrap">
                        <i class="fas fa-money-bill" aria-hidden="true"></i>
                        <a href="${pageContext.request.contextPath}/privacy.html">Thanh toán khi nhận hàng</a>
                    </div>
                    <div class="contact-wrap">
                        <i class="fas fa-truck" aria-hidden="true"></i>
                        <a href="${pageContext.request.contextPath}/#">Giao hàng toàn quốc</a>
                    </div>
                    <div class="contact-wrap">
                        <i class="fas fa-thumbs-up" aria-hidden="true"></i>
                        <a href="${pageContext.request.contextPath}/#">Nhân sâm Hàn Quốc uy tín</a>
                    </div>
                    <div class="contact-wrap">
                        <i class="fas fa-check-circle" aria-hidden="true"></i>
                        <a href="${pageContext.request.contextPath}/#">Dịch vụ CSKH chu đáo</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="contact-content">
            <div class="fb-page" 
                data-href="${pageContext.request.contextPath}/https://www.facebook.com/kcntt.nlu"
                data-width="400" 
                data-hide-cover="false"
                data-show-facepile="false">
            </div>
        </div>
    </div>
    <div class="copy-right">
        <span>Copyright &copy; 2023 Đồ án lập trình web</span>
    </div>
</footer>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous"
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0" nonce="Qoebijhj">
</script>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>

<script src="${pageContext.request.contextPath}/libs/swiper/swiper-bundle.min.js"></script>
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

    document.addEventListener('DOMContentLoaded', function() {
        const storeAddresses = document.querySelectorAll('.map-kgin');

        storeAddresses.forEach(address => {
            address.addEventListener('click', function() {
                // Lấy địa chỉ và thông tin của cửa hàng
                const storeAddress = this.getAttribute('google_link');
                const storeDescription = this.getAttribute('description');

                // Hiển thị địa chỉ cửa hàng
                const mapFrame = document.querySelector('.google-maps iframe');
                mapFrame.src = storeAddress;

                // Hiển thị thông tin cửa hàng
                const storeDetailElement = document.querySelector('.store-details .entry-content');
                storeDetailElement.innerHTML = storeDescription;
            });
        });
    });
</script>
</body>
</html>