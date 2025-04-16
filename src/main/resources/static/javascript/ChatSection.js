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
});