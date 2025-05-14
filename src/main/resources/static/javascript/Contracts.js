function goToContract(event) {
    const from = event.currentTarget.getAttribute("id"); 
    window.location.href = `/tenet/getContract?to=${from}`;
}

document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".grid-item").forEach(item => {
        item.addEventListener("click", goToContract);
    });
});