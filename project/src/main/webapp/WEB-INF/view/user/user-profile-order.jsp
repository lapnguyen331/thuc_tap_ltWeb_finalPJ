<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/user-profile-order1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/user-profile-order-popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/DataTables/datatables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.css">
    <title>Slider</title>
  </head>
  <body>
    <jsp:include page="/WEB-INF/view/shared/header.jsp"/>
    <section id="user-profile">
      <div class="user-profile-container">
        <div class="left-control">
          <div class="user-profile">
            <a class="user-image" href="user-profile.jsp"></a>
            <!--NOTE : Link tới hồ sơ user-->
            <div class="user-avatar">
              <img
                class="avrtar-img" src="${pageContext.request.contextPath}/${userifor.avatar.path}"
                alt=""
              />
            </div>
            <div class="user-account-name">
              <div class="account-name">${userifor.username}</div>
              <div>
                <a class="user-profile-modify" href="${pageContext.request.contextPath}/user-profile-edit">
                  <!--NOTE: thiếu link tới profile của user-->
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
                    <a class="stardust-link" >
                        <img src="${pageContext.request.contextPath}/inventory/images/user-profile/account.png" alt="">
                        <span>Tài khoản của tôi</span>
                    </a> 
                </div>
                <div class="startdust-dropdown-item-body">
                    <div class="slitter">
                        <a class="body-down" href="${pageContext.request.contextPath}/user-profile">
                            <span>Hồ sơ</span>
                        </a> <!--NOTE : link tới prof-->
                        <a class="body-down" href="${pageContext.request.contextPath}/user-profile-changePass">
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
                <h1>Đơn hàng của tôi</h1>
              </div>
              <!-- quan li order -->
              <div>
                <div class="order-control">
                  <div class="container rounded-2 pb-3 shadow">
                      <div class="row">
                          <div class="col-md-12">
                              <form id="table_filters">
                                  <div class="form-outline input-field">
                                      <i class="fa-solid fa-magnifying-glass"></i>
                                      <input type="text" class="form-control" id="filter_input" placeholder="Theo mã đơn hàng, tên khách hàng hoặc tương tự...">
                                  </div>
                                  <div class="form-outline input-field">
                                      <input type="text" class="form-control" id="date_picker" placeholder="Theo ngày tháng" value="">
                                  </div>
                                  <div class="select-field">
                                      <select id="status_filter">
                                          <option value="none" disabled selected>Theo tình trạng</option>
                                          <option value="pending">Đã được giao</option>
                                          <option value="success">Đang giao</option>
                                          <option value="return">Đang trả về</option>
                                          <option value="cancel">Đã bị hủy</option>
                                      </select>
                                      <select id="table_length_filter" class="select">
                                          <option value="10">Hiển thị 10 dòng</option>
                                          <option value="20">Hiển thị 20 dòng</option>
                                          <option value="50">Hiển thị 50 dòng</option>
                                          <option value="100">Hiển thị 100 dòng</option>
                                      </select>
                                  </div>
                                  <div class="submit-field bg-gold rounded align-self-lg-stretch">
                                      <button type="submit" class="d-flex align-items-center">
                                          <div><i class="fa-solid fa-sliders"></i></div>
                                          <span>Tìm kiếm</span>
                                      </button>
                                  </div>
                              </form>
                          </div>
                      </div>
                      <div class="row">
                          <div class="col-md-12">
                              <nav id="table_tabs">
                                  <ul>
                                      <li class="order-all1 active">
                                          <i class="fa-solid fa-warehouse"></i>
                                          <span >Tất cả đơn</span>
                                      </li>
                                      <li class="order-complete">
                                          <i class="fa-regular fa-circle-check"></i>
                                          <span >Đã được giao</span>
                                      </li>
                                      <li class="order-pending">
                                          <i class="fa-solid fa-truck-fast"></i>
                                          <span >Đang giao</span>
                                      </li>
                                      <li class="order-return">
                                          <i class="fa-solid fa-right-left"></i>
                                          <span class="order-return">Đang trả về</span>
                                      </li>
                                      <li class="order-cancel">
                                          <i class="fa-regular fa-circle-xmark"></i>
                                          <span>Đã bị hủy</span>
                                      </li>
                                  </ul>
                              </nav>
                          </div>
                      </div>
                      <div class="row">
                          <div class="col-md-12">
                              <table id="table_orders" style="width: 100%">
                                  <thead>
                                      <tr>
                                          <th>Mã đơn hàng</th>
<%--                                          <th>Khách hàng</th>--%>
                                          <th>Tổng thu</th>
<%--                                          <th>SĐT</th>--%>
<%--                                          <th>Email</th>--%>
                                          <th>Tình trạng</th>
                                          <th>Ngày tạo</th>
                                      </tr>
                                  </thead>
                              </table>
                          </div>
                      </div>
                  </div>
              </div>
              </div>
            </div>
          </section>
        </div>
      </div>   
    </section>

 <!-- Modal popup đánh giá sp order -->
 <div class="modal top fade" id="rating-product-order" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-mdb-backdrop="static" data-mdb-keyboard="true">
    <div class="modal-dialog modal-lg ">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Đánh giá sản phẩm</h5>
          <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Đóng"></button>
        </div>
        <div class="modal-body">
          <div class="rating-wrapper">
            <div class="service-rating">
                <div class="label-left">
                    <div>Tư vấn:</div>
                    <div>Chất lượng sản phẩm</div>
                </div>
                <div class="rating-start-right">
                    <div class="service container">
                        <ul class="rating" data-mdb-toggle="rating">
                            <i class="far fa-star fa-sm text-primary"></i>
                            <i class="far fa-star fa-sm text-primary"></i>
                            <i class="far fa-star fa-sm text-primary"></i>
                            <i class="far fa-star fa-sm text-primary"></i>
                            <i class="far fa-star fa-sm text-primary"></i>
                        </ul>
                    </div>
                    <div class="quality container">
                        <ul class="rating" data-mdb-toggle="rating">
                            <i class="far fa-star fa-sm text-primary"></i>
                            <i class="far fa-star fa-sm text-primary"></i>
                            <i class="far fa-star fa-sm text-primary"></i>
                            <i class="far fa-star fa-sm text-primary"></i>
                            <i class="far fa-star fa-sm text-primary"></i>
                        </ul>
                    </div> 
                </div>
            </div>
            <div class="raing-comment">
              <label for="">Đánh giá sản phẩm</label>
              <textarea name="" id="" cols="70" rows="20"></textarea>
            </div>
          </div>  
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">
            Đóng
          </button>
          <button type="button" class="btn btn-primary">Lưu thay đổi</button>
        </div>
      </div>
    </div>
  </div>
 <!-- Modal popup khiếu nại sp order -->
 <div class="modal top fade" id="complaint-product-order" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-mdb-backdrop="static" data-mdb-keyboard="true">
    <div class="modal-dialog modal-lg ">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Khiếu nại sản phẩm</h5>
          <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
            <h5 class="complaint-tittle">Thông tin liên hệ</h5>
            
           <div class="complaint-wrapper">
            <div class="complaint-customer-infor">
              <div class="label-left">
                <label for="">Họ tên</label>
                <label for="">Số diện thoại</label>
                <label for="">Email</label>
              </div>
              <div class="in-infor-right">
                <input type="text">
                <input type="text">
                <input type="text">
              </div>
            </div>
             <div class="complaint-comment">
              <label for="">Khiếu nại của khách hàng</label>
              <textarea name="" id="" cols="70" rows="20"></textarea>
            </div>
           </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">
            Close
          </button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>
    <jsp:include page="/WEB-INF/view/shared/footer.jsp" />
    <div id="fb-root"></div>
    <script
      async
      defer
      crossorigin="anonymous"
      src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v18.0"
      nonce="Qoebijhj"
    ></script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="libs/mdb-bootstrap-5-pro/js/mdb.min.js"></script>
    <!-- for this page -->
    <script src="${pageContext.request.contextPath}/libs/DataTables/datatables.min.js"></script>
    <script src="${pageContext.request.contextPath}/libs/daterangepicker-master/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/libs/daterangepicker-master/daterangepicker.js"></script>
    <script src="${pageContext.request.contextPath}/js/user/user-profile.js"></script>
    <script src="${pageContext.request.contextPath}/js/user/user-profile-order.js"></script>
    <script src="${pageContext.request.contextPath}/js/user/user-profile-order-startRating.js"></script>
  </body>
</html>
