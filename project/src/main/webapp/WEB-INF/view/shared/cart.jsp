<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixed-buttons.css">
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
                </div>
            </div>
            <div class="modal-footer">
                <div class="bg-gold bg-sharp-5">
                    <button class="btn-moveTo" >
                        <a href="${pageContext.request.contextPath}/cart" >
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
<div
        class="toast fade mx-auto"
        id="message-toasts"
        role="alert"
        aria-live="assertive"
        aria-atomic="true"
        data-mdb-toast-init
        data-mdb-autohide="true"
        data-mdb-delay="2000"
        data-mdb-position="bottom-right"
        data-mdb-offset="50"
        data-mdb-color="success"
>
    <div class="toast-header">
        <strong class="me-auto"></strong>
        <button type="button" class="btn-close" data-mdb-dismiss="toast" aria-label="Close"></button>
    </div>
    <div class="toast-body">Static Example</div>
</div>
<script src="${pageContext.request.contextPath}/js/cart.js?v=03" defer></script>
