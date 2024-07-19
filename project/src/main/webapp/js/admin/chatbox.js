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
    console.log(val)
    $.ajax({
        url: `${window.context}/api/v1/users/getLikeUsername?username=${val}`,
        success: function (data) {
            data.length > 0 && $('.search-row-wrap').empty();
            [...data].forEach((arr) => {
                const {avatar, username, id} = arr
                let html = `
                    <div class="search-row" data-search-id="${id}">
                        <div class="avatar" style="background-image: url(${avatar})"></div>
                        <div class="username">${username}</div>
                    </div>
                    `
                const element = $(html)
                $('.search-row-wrap').removeClass('empty').append(element)
            })
        }
    })
})

$('.search-row-wrap').on('click', '.search-row', function() {
    const id = Number($(this).data('search-id'));
    openWindowChat(id)
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
            case "open_chat_with": {
                saveChoosedUser(obj.data)
                break;
            }
            case "get_history": {
                renderChatWindow(obj.data);
                break;
            }
        }
    }

    function processClose(message) {
    }

    function processError(message) {
    }
})

function loadLastestChatMessages(chats) {
    if (chats.length >= 1 ) {
        const target = $('.center .contact-wrap');
        target.removeClass('empty').empty();
        [...chats].forEach(chat => {
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
}

$('.center .contact-wrap').on('click', '.contact.row__chat', function() {
    const targetId = Number($(this).data('user-id'))
    openWindowChat(targetId)
})

function renderChatWindow(chats) {
    $('.chat').removeClass('empty')
    $('.contact.bar').find('.pic').css({
        backgroundImage: `url(${choosedUser.avatar})`
    }).parent().find('.name .text').text(choosedUser.username)
    $('#chat').empty();
    if (chats.length >= 1) {
        [...chats].forEach(chat => {
            const isSender = chat.sender.id === userId;
            $('#chat').append(`
            <div class="message ${isSender?'me':''}">${chat.message}</div>
        `)
        })
    }
}

function openWindowChat(id) {
    sendMessage(JSON.stringify({
        action: "open_chat_window_with",
        data: id
    }))
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

function saveChoosedUser(datas) {
    choosedUser = datas[0]
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