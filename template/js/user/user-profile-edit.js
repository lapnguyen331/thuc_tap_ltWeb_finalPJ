// 'use strict';
console.log("hehe")

$('#txt_date, #txt_birthday').daterangepicker({
    "singleDatePicker": true,
}, function(start, end, label) {
  console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
});

var imgInp = document.getElementById("#profile-file-input");
var preavartar =document.querySelector(".profile-image img");

imgInp.onchange=function (){document.querySelector('.DAM').src = window.URL.createObjectURL(this.files[1])}