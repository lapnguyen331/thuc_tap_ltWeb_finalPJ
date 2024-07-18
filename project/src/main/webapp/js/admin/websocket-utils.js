export function sendMessage_callback(websocket, message) {
    return new Promise((resolve, reject) => {
        if (websocket.readyState !== WebSocket.OPEN) {
            reject(new Error("WebSocket is not open: readyState " + websocket.readyState));
            return;
        }

        function onMessage(event) {
            websocket.removeEventListener('message', onMessage);
            resolve(event.data);
        }

        function onError(event) {
            websocket.removeEventListener('error', onError);
            reject(event);
        }

        websocket.addEventListener('message', onMessage);
        websocket.addEventListener('error', onError);

        websocket.send(message);
    });
}

export function openWebSocket(url) {
    return new Promise((resolve, reject) => {
        const websocket = new WebSocket(url);

        websocket.onopen = function(event) {
            resolve(websocket);
        };

        websocket.onclose = function(event) {
            console.log('WebSocket is closed now.', event);
        };

        websocket.onerror = function(event) {
            reject(new Error('WebSocket error: ' + event));
        };
    });
}