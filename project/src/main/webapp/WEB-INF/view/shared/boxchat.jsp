<%--
  Created by IntelliJ IDEA.
  User: Tuan
  Date: 7/16/2024
  Time: 8:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/fontawesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/plugins/css/drag-and-drop.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/boxchat.css?v=3">
</head>
<div
        style="
          width: 24rem;
          height: 38rem;
          position: absolute;
          z-index: 10;
          top: 5%;
          left: 50%;
          transform: translate(-20%, -20%);
        "
        class="draggable-element d-none"
        data-mdb-draggable-init
>
    <div class="center" class="shadow-3">
        <div class="contacts">
            <div class="d-flex flex-column h-100">
                <div class="d-flex gap-2 mb-2 align-items-center justify-content-between">
                    <div class="fw-semibold fs-5">Lịch sử chat</div>
                </div>
                <hr>
                <div class="contact-wrap empty">
                    <div class="empty-placeholder">
                        <i class="fa-regular fa-envelope"></i>
                        <span class="msg">Lịch sử chat đang trống.</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="chat empty">
            <div class="empty-placeholder">
                <i class="fa-regular fa-comments fs-1"></i>
                <div class="msg">Vui lòng chọn user muốn chat</div>
            </div>
            <div class="contact bar">
                <div class="pic stark"></div>
                <div class="name d-flex justify-content-between">
                    <span class="text">Tony Stark</span>
                    <span
                            class="fw-bold text-danger fs-4"
                            id="close_btn"
                            style="cursor: pointer; z-index: 500"
                    >X</span
                    >
                </div>
                <div class="seen">Today at 12:56</div>
            </div>
            <div class="messages" id="chat">
                <div class="time">Today at 11:41</div>
                <div class="message me">
                    Hey, man! What's up, Mr Stark? 👋
                </div>
                <div class="message target">Kid, where'd you come from?</div>
                <div class="message me">Field trip! 🤣</div>
                <div class="message me">
                    Uh, what is this guy's problem, Mr. Stark? 🤔
                </div>
                <div class="message target">
                    Uh, he's from space, he came here to steal a necklace from a
                    wizard.
                </div>
                <div class="message target">
                    <div class="typing typing-1"></div>
                    <div class="typing typing-2"></div>
                    <div class="typing typing-3"></div>
                </div>
            </div>
            <div class="input">
                <input placeholder="Type your message here!" type="text" />
                <i class="fa-regular fa-paper-plane"></i>
            </div>
        </div>
    </div>
</div>
<div class="searchPane draggable-element d-none" data-mdb-draggable-init style="position: fixed;
    bottom: 0;
    z-index: 2;
    right: 20%;
    width: 300px;
    height: 400px;
    border-radius: 10px;
    box-shadow: 0 0 8rem 0 rgba(0, 0, 0, 0.1), 0rem 2rem 4rem -3rem rgba(0, 0, 0, 0.5);
    background-color: #ffffff">
    <div style="display: flex; width: 100%; justify-content: center; gap: 20px; padding: 10px 0px">
        <div class="form-outline" style="width: 200px;">
            <input type="text" id="txt_searchUsername" class="form-control form-control-sm" />
        </div>
        <div class="bg-primary py-1 px-2 rounded text-white" id="searchButton" style="cursor: pointer">
            <i class="fa fa-search"></i>
        </div>
    </div>
    <div class="search-row-wrap" style="height: 320px; overflow: auto">

    </div>
</div>
<div
        style="
        position: fixed;
        top: 90%;
        left: 95%;
        width: 80px;
        height: 80px;
        background-color: rgb(223, 15, 15);
        transform: translate(-95%, -95%);
        border: 3px solid burlywood;
        box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.3);
        cursor: pointer;
        border-radius: 50%;
        z-index: 2;
      "
        id="boxchat-icon"
>
    <i
            class="fa-solid fa-headset"
            style="
          position: absolute;
          left: 50%;
          top: 50%;
          transform: translate(-50%, -50%);
          color: burlywood;
          font-size: 20px;
        "
    ></i>
</div>
<script src="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/plugins/js/drag-and-drop.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script>
    var context = "${pageContext.request.contextPath}"
    <c:if test="${not empty sessionScope.user}">
        var userId = ${sessionScope.user.id}
    </c:if>
    var choosedUser = undefined;
</script>
<script type="module" src="${pageContext.request.contextPath}/js/admin/chatbox.js?v=18"></script>
</html>
