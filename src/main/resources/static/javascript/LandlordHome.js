document.addEventListener("DOMContentLoaded", () => {
    // const statusDiv = document.getElementById("request-status");
    document.querySelectorAll(".chat").forEach(item => {
        item.addEventListener("click", (event) => {
            const id = event.currentTarget.getAttribute("id");
            const container = event.currentTarget.closest(".grid-item");
            const statusDiv = container.querySelector(".request-status");
            console.log("Navigating to chat with:", id);
            fetch(`/request-send?to=${encodeURIComponent(id)}`, {
                method: 'POST'
            })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(err => { throw new Error(err) });
                }
                return response.text();
            })
            .then(data => {
                statusDiv.innerText = "✅ " + data;
                statusDiv.style.color = "green";
            })
            .catch(error => {
                if(error.message=="Request already exits"){
                    window.location.href="/chat-page"
                }
                statusDiv.innerText = "❌ " + error.message;
                statusDiv.style.color = "red";
            });
        });
    });
});
