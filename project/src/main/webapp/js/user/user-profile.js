// tài khoản colapse
var coll = document.querySelector(".startdust-dropdown-item-header");
coll.onclick= function(){
    colapseCustom();
};
function colapseCustom(){
    var content = document.querySelector(".startdust-dropdown-item-body");
    var bur =document.querySelector(".account-height");
    bur.style.height ="150px"
    content.style.overflow ='inherit';
    content.style.opacity = "1";
}
