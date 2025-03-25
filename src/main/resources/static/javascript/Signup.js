document.addEventListener("DOMContentLoaded", function () {
    const fileInput = document.getElementById("fileInput");
    const signupButton = document.getElementById("submitButton");
    const progressBar = document.getElementById("progressBar");
    const progressContainer = document.getElementById("progressContainer");
    const signupForm = document.getElementById("signupForm");

    async function uploadAndSubmit(event) {
        event.preventDefault(); 

        signupButton.disabled = true;
        progressContainer.style.display = "block";
        progressBar.style.width = "0%";

        if (fileInput.files.length > 0) {
            const file = fileInput.files[0];
            const validTypes = ["image/jpeg", "image/png", "image/gif", "image/webp"];

            if (!validTypes.includes(file.type)) {
                alert("Invalid file type! Please select an image file.");
                signupButton.disabled = false;
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

                // Show full progress and update hidden field
                progressBar.style.width = "70%";
                const hiddenImageInput = document.createElement("input");
                hiddenImageInput.type = "hidden";
                hiddenImageInput.name = "imageUrl";
                hiddenImageInput.value = imageUrl;
                signupForm.appendChild(hiddenImageInput);

            } catch (error) {
                alert("Error uploading image: " + error.message);
                signupButton.disabled = false;
                progressContainer.style.display = "none"; // Hide progress bar
                return;
            }
        }

        //  Finalizing progress before submission
        progressBar.style.width = "100%";

        setTimeout(() => {
            signupForm.submit(); //  Submit form after upload
        }, 500); //  Small delay to let user see progress completion
    }

    //  Attach event listener AFTER DOM is fully loaded
    signupForm.onsubmit = uploadAndSubmit;
});
