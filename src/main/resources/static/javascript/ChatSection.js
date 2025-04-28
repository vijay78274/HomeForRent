document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".chats").forEach(item => {
        item.addEventListener("click", (event) => {
            const tenantId = item.getAttribute("id");
            console.log("Navigating to chat with:", tenantId);
            window.location.href = "/chat/" + tenantId;
        });
    });
    document.querySelectorAll(".accept").forEach(item=>{
        item.addEventListener("click",()=>{
            const statusDiv = document.querySelector(".msg")
            const id = item.getAttribute("id");
            fetch(`/request-accept?from=${encodeURIComponent(id)}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(err => { throw new Error(err) });
                }
                return response.text();
            })
            .then(data => {
                console.log(data)
                statusDiv.innerText = "✅ " + data;
                statusDiv.style.color = "green";
                window.location.href = "/chat-page"
            })
            .catch(error => {
                console.log(error.message)
                statusDiv.innerText = "❌ " + error.message;
                statusDiv.style.color = "red";
            });
        })
    })
});