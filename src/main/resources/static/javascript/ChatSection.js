document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".list-column").forEach(item => {
        item.addEventListener("click", (event) => {
            // const req = event.currentTarget.getAttribute("id");
            const tenantId = item.getAttribute('data-name');
            const status = item.getAttribute('data-status');
            console.log(status)
            if(status=="ACCEPTED"){
                console.log("Navigating to chat with:", tenantId);
                window.location.href = "/chat/" + tenantId;
            }
            else{
                console.log("Not allowed to chat");
            }
        });
    });
    document.querySelectorAll(".accept").forEach(item=>{
        item.addEventListener("click",()=>{
            // const container = event.currentTarget.closest(".");
            // const statusDiv = container.querySelector(".request-status");
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