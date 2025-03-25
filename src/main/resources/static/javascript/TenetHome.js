function goToTenantDetails(event) {
    const tenantId = event.currentTarget.getAttribute("id"); 
    window.location.href = "/tenet/getbyId/" + tenantId;
}

document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".grid-item").forEach(item => {
        item.addEventListener("click", goToTenantDetails);
    });
});