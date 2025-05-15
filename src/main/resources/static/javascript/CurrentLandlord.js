document.getElementById('download').addEventListener('click', function () {
    const button = this;
    button.style.display = 'none'; // Hide button

    const element = document.getElementById('pdf-content');

    html2pdf()
      .set({
        margin: 0.5,
        filename: 'contract.pdf',
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'in', format: 'a4', orientation: 'landscape' }
      })
      .from(element)
      .save()
      .then(() => {
        button.style.display = 'block'; // Restore button
      });
  });