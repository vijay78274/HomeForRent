document.addEventListener("DOMContentLoaded", function () {
    const fileInput = document.getElementById("fileInput");
    const contractSubmit = document.getElementById("submitButton");
    const progressBar = document.getElementById("progressBar");
    const progressContainer = document.getElementById("progressContainer");
    const contractForm = document.getElementById("contractForm");

    async function uploadAndSubmit(event) {
        event.preventDefault();
        contractSubmit.disabled = true;
        progressContainer.style.display = "block";
        progressBar.style.width = "0%";

        if (fileInput.files.length > 0) {
            const file = fileInput.files[0];
            const validTypes = ["image/jpeg", "image/png", "image/gif", "image/webp"];

            if (!validTypes.includes(file.type)) {
                alert("Invalid file type! Please select an image file.");
                contractSubmit.disabled = false;
                progressContainer.style.display = "none";
                return;
            }

            const formData = new FormData();
            formData.append("file", file);

            try {
                progressBar.style.width = "30%";

                const response = await fetch("/cloudinary/upload", {
                    method: "POST",
                    body: formData
                });

                if (!response.ok) throw new Error("Image upload failed");

                const imageUrl = await response.text();
                progressBar.style.width = "70%";

                let hiddenImageInput = document.getElementById("hiddenImageUrl");
                if (!hiddenImageInput) {
                    hiddenImageInput = document.createElement("input");
                    hiddenImageInput.type = "hidden";
                    hiddenImageInput.name = "adhaarImage";
                    hiddenImageInput.id = "hiddenImageUrl";
                    contractForm.appendChild(hiddenImageInput);
                }
                hiddenImageInput.value = imageUrl;

            } catch (error) {
                alert("Error uploading image: " + error.message);
                contractSubmit.disabled = false;
                progressContainer.style.display = "none";
                return;
            }
        }

        progressBar.style.width = "100%";

        setTimeout(() => {
            contractForm.submit();
        }, 500);
    }

    contractForm.addEventListener("submit", uploadAndSubmit);
});
