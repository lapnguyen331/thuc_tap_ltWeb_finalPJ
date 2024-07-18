const chat = document.getElementById("chat");
chat.scrollTop = chat.scrollHeight - chat.clientHeight;
$("#boxchat-icon").on("click", function () {
    $(".draggable-element").removeClass("d-none");
    DragAndDrop.Draggable.getInstance($('.draggable-element').get(0)).resetPosition()
})
$("#close_btn").on("click", function () {
    $(".draggable-element").addClass("d-none");
})

$('#searchButton').on('click', () => {
    const val = $('#txt_searchUsername').val()
    $.ajax({
        url: `${window.context}/api/v1/users/getLikeUsername?username=${val}`,
        success: function (data) {
            data.length > 0 && $('.contact-wrap').removeClass('empty').find('.contact').remove();
            [...data].forEach((arr) => {
                const {avatar, username} = arr
                let html = `
                        <div class="contact">
                            <div class="pic" style="background-image: url(${avatar})"></div>
                            <div class="badge">0</div>
                            <div class="name">${username}</div>
                            <div class="message">Hello</div>
                        </div>
                    `
                const element = $(html)
                $('.contact-wrap').removeClass('empty').append(element)
            })
        }
    })
})

let websocket = null;
$.ajax(`${window.context}/api/v1/web-socket`)
.then((resp) => {
    websocket = new WebSocket(resp + "/chat");
    websocket.onopen = function (message) {
        processOpen(message);
    };
    websocket.onmessage = function (message) {
        processMessage(message);
    };
    websocket.onclose = function (message) {
        processClose(message);
    };
    websocket.onerror = function (message) {
        processError(message);
    };

    function processOpen(message) {
        console.log('Server connect...')
        if (websocket.readyState == WebSocket.OPEN) {
            websocket.send(JSON.stringify({action: "get_last_chat"}))
        }
    }

    function processMessage(message) {
        const obj = JSON.parse(message.data);
        console.log(obj)
        switch (obj.action) {
            case "get_last_chat": {
                loadLastestChatMessages(obj.data);
                break;
            }
            case "receive_chat": {
                renderNewChat(obj.data);
                break;
            }
            case "mark_read": {
                renderMarkRead(obj.data);
                break;
            }
            case "get_history": {
                renderChatWindow(obj.data);
                break;
            }
            case "send_chat": {
                renderNewChat(obj.data);
                break;
            }
        }
    }

    function processClose(message) {
    }

    function processError(message) {
    }
})

function renderMarkRead(resp) {
    const targetId = resp[0];
    $(`.contact.row__chat[data-user-id="${targetId}"]`)
        .find('.badge').remove()
}

function loadLastestChatMessages(chats) {
    $('.contact-wrap').removeClass('empty')
    const target = $('.contact-wrap');
    [...chats].forEach(chat => {
        console.log(chat)
        const isSender = chat.sender.id === userId
        const userTarget = !isSender ? chat.sender : chat.receiver
        target.append(`
            <div class="contact row__chat" data-user-id="${userTarget.id}"">
                <div class="pic" style="background-image: url(${userTarget.avatar})"></div>
                ${!isSender && chat.isNew ? '<div class="badge">!</div>' : ""}
                <div class="name">${userTarget.username}</div>
                <div class="message">${isSender && 'Bạn: ' || ""}${chat.message}</div>
            </div>
        `)
    })
}

$('.contact-wrap').on('click', '.contact.row__chat', function() {
    const targetId = Number($(this).data('user-id'))
    openWindowChat(targetId)
})

function renderChatWindow(chats) {
    const chat = chats[0]
    const isSender = chat.sender.id === userId
    const userTarget = !isSender ? chat.sender : chat.receiver
    $('.chat').removeClass('empty')
    $('.contact.bar').find('.pic').css({
        backgroundImage: `url(${userTarget.avatar})`
    }).parent().find('.name .text').text(userTarget.username)

    $('#chat').empty();
    [...chats].forEach(chat => {
        $('#chat').append(`
            <div class="message ${isSender?'me':''}">${chat.message}</div>
        `)
    })
}

function renderNewChat(chats) {
    const chat = chats[0]
    const isSender = chat.sender.id === userId
    const userTarget = !isSender ? chat.sender : chat.receiver
    if (userTarget.id === chooseId) {
        $('#chat').append(`
            <div class="message ${isSender?'me':''}">${chat.message}</div>
        `)
    }
    $(`.contact[data-user-id="${userTarget.id}"]`)
        .find('.message')
        .text(isSender ? 'Bạn: '+chat.message : chat.message);
    chat.isNew && !isSender
        && $(`.contact[data-user-id="${userTarget.id}"]`).find('.badget').remove()
        && $(`.contact[data-user-id="${userTarget.id}"]`).append(`<div class="badge">!</div>`);
}

function openWindowChat(id) {
    sendMessage(JSON.stringify({
        action: "open_chat_window_with",
        data: id
    }))
    chooseId = id;
    sendMessage(JSON.stringify({
        action: "get_history",
    }))
    sendMessage(JSON.stringify({
        action: "mark_read",
    }))
}

function sendMessage(myMessage) {
    if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
        websocket.send(myMessage);
    }
}

$('.input i').on('click', async function() {
    const msg = $(this).parent().find('input').val()
    sendMessage(JSON.stringify({
        action: 'send_chat',
        data: msg
    }))

    sendMessage(JSON.stringify({
        action: "mark_read",
    }))
})