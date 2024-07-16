<%--
  Created by IntelliJ IDEA.
  User: Tuan
  Date: 7/16/2024
  Time: 8:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/fontawesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/plugins/css/drag-and-drop.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/boxchat.css">
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
        class="draggable-element"
        data-mdb-draggable-init
>
    <div class="center" class="shadow-3">
        <div class="contacts d-none">
            <i class="fas fa-bars fa-2x"></i>
            <h2>Contacts</h2>
            <div class="contact-wrap empty">
                <div class="empty-placeholder">
                    <i class="fa-regular fa-envelope"></i>
                    <span class="msg">Danh sách chat đang trống.</span>
                </div>
                <div class="contact">
                    <div class="pic rogers"></div>
                    <div class="badge">14</div>
                    <div class="name">Steve Rogers</div>
                    <div class="message">That is America's ass 🇺🇸🍑</div>
                </div>
                <div class="contact">
                    <div class="pic stark"></div>
                    <div class="name">Tony Stark</div>
                    <div class="message">
                        Uh, he's from space, he came here to steal a necklace from a
                        wizard.
                    </div>
                </div>
                <div class="contact">
                    <div class="pic banner"></div>
                    <div class="badge">1</div>
                    <div class="name">Bruce Banner</div>
                    <div class="message">
                        There's an Ant-Man *and* a Spider-Man?
                    </div>
                </div>
                <div class="contact">
                    <div class="pic thor"></div>
                    <div class="name">Thor Odinson</div>
                    <div class="badge">3</div>
                    <div class="message">I like this one</div>
                </div>
                <div class="contact">
                    <div class="pic danvers"></div>
                    <div class="badge">2</div>
                    <div class="name">Carol Danvers</div>
                    <div class="message">
                        Hey Peter Parker, you got something for me?
                    </div>
                </div>
            </div>
        </div>
        <div class="chat d-none empty">
            <div class="empty-placeholder">
                <i class="fa-regular fa-comments fs-1"></i>
                <div class="msg">Chưa có tin nhắn nào mới</div>
            </div>
            <div class="contact bar">
                <div class="pic stark"></div>
                <div class="name d-flex justify-content-between">
                    Tony Stark
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
                <div class="message parker">
                    Hey, man! What's up, Mr Stark? 👋
                </div>
                <div class="message stark">Kid, where'd you come from?</div>
                <div class="message parker">Field trip! 🤣</div>
                <div class="message parker">
                    Uh, what is this guy's problem, Mr. Stark? 🤔
                </div>
                <div class="message stark">
                    Uh, he's from space, he came here to steal a necklace from a
                    wizard.
                </div>
                <div class="message stark">
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
    var chat = document.getElementById("chat");
    chat.scrollTop = chat.scrollHeight - chat.clientHeight;

    $("#boxchat-icon").on("click", function () {
        $(".center .contacts").removeClass("d-none");
        $(".center .chat").removeClass("d-none");
        DragAndDrop.Draggable.getInstance($('.draggable-element').get(0)).resetPosition()
    });
    $("#close_btn").on("click", function () {
        $(".center .contacts").addClass("d-none");
        $(".center .chat").addClass("d-none");
    });
</script>
</html>
