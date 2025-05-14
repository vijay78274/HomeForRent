document.addEventListener("DOMContentLoaded", () => {
let stompClient;
const sender = document.getElementById('sender').value;
const receiver = document.getElementById('receiver').value;
const role = document.getElementById('role').value;
const contract = document.getElementById("contract");
if(role=="Tenet"){
    contract.style.display="none"
}
console.log("Sender:", sender);
console.log("Receiver:", receiver);
function connect() {
    const socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        console.log('Connected');

        // Subscribe to receiver's personal topic
        stompClient.subscribe('/topic/messages/' + sender, function (message) {
            const msg = JSON.parse(message.body);
            displayMessage(msg, false);
        });

        // Load chat history
        fetch(`/messages?sender=${sender}&receiver=${receiver}`)
            .then(res => res.json())
            .then(messages => {
                console.log("Messages:", messages); 
                messages.forEach(m => displayMessage(m, m.sender === sender));
                scrollToBottom();
            });
    });
}

function sendMessage() {
    const content = document.getElementById("messageInput").value.trim();
    if (!content) return;

    const msg = {
        sender: sender,
        receiver: receiver,
        content: content
    };

    stompClient.send("/app/send", {}, JSON.stringify(msg));
    displayMessage(msg, true);
    document.getElementById("messageInput").value = "";
    scrollToBottom();
}

function displayMessage(message, isSender) {
    const chatBox = document.getElementById("chat-box");
    const div = document.createElement("div");
    div.className = "chat-message " + (isSender ? "sent" : "received");
    div.innerText = message.content;
    chatBox.appendChild(div);
}

function scrollToBottom() {
    const chatBox = document.getElementById("chat-box");
    chatBox.scrollTop = chatBox.scrollHeight;
}
window.sendMessage = sendMessage;
connect();
    contract.addEventListener("click",()=>{
        window.location.href=`/landlordcontract?to=${receiver}`;
    });
});

