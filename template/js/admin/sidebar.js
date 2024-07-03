(function() {
  let arrow = document.querySelectorAll(".arrow");
for (let i = 0; i < arrow.length; i++) {
  arrow[i].addEventListener("click", (e)=>{
 let arrowParent = e.target.parentElement.parentElement;//selecting main parent of arrow
 arrowParent.classList.toggle("showMenu");
  });
}

$('#btn-toggle-sidebar').on({
  click: function() {$('.sidebar').toggleClass("close")},
})
})();

// //cập nhật tạo thông báo
// const notiNum = document.querySelector('.newNoti');
//     function notificationContact() {
//         $.ajax({
//             type: 'GET',
//             url: '${pageContext.request.contextPath}/admin/contactInform',
//             data:[ {
//                 uncheckNotiNum:'uncheckNotiNum',
//                 // action: 'checkContact'
//                 // oldContact : oldContact
//             }],
//             success: function(data) {
//                 notiNum.innerHTML = this.data.uncheckNotiNum;
//             }
//         })};
//     setInterval(notificationContact(),100000);
(function() {
    var status = $('.newNoti'),
        poll = function() {
            $.ajax({
                url: `${window.context}/admin/contactInform`,
                dataType: 'json',
                type: 'get',
                data:{get_param: 'value'} ,
                success: function(data) { // check if available
                    var json = $.parseJSON(data);
                    status.innerHTML =$(json[0]).uncheckNotiNum;
                    console.log("append")
                    // if ( data ) { // get and check data value
                    //     status.text(data.info); // get and print data string
                    // }
                },
                error: function() { // error logging
                    console.log('Error!');
                }
            });
        },
        pollInterval = setInterval(function() { // run function every 2000 ms
            poll();
        }, 2000);
    poll(); // also run function on init
})();

