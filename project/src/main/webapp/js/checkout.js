'use strict';
// $(document).ready(function() {
//     const checkoutHandle = function() {
//
//     }
//     $('#checkout-form').on('submit', function(e) {
//         e.preventDefault();
//         $.ajax({
//             url: `${window.contextPath}/checkout`,
//             method: 'post',
//             data: {
//                 receiverFirstName : $('#txt-first-name').text(),
//                 receiverLastName : $('#txt-last-name').text(),
//                 receiverEmail : $('#txt-email').text(),
//                 receiverPhone : $('#txt-phone').text(),
//                 receiverAddress : $('#txt-address').text(),
//                 note : $('#txt-note').text(),
//             }
//         })
//     })
// })
// tạo event chọn radio list chỗ phương thức
$('.payment-method-item').on('click', function(e){
    var em = $('input:radio',this);
    em.prop('checked',true);
    $('.payment-method-item').removeClass("input-choose")
    this.classList.add ("input-choose")

    // $('..forEach(e =>{
    //     e.nodeParent.style.backgroundColor = e.checked?"#7fa3a9":"#fff"
    // })

})