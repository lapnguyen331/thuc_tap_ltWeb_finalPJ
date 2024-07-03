// đã đọc tất cả
const readed = document.querySelector('.readedFlag');
const notiList = document.querySelectorAll('#notify-sec')
readed.onclick = function(){
    console.log("hehe");
    for(let i =0; i<notiList.length;i++){
        notiList[i].style.background="white";
    }
}
console.log(notiList);