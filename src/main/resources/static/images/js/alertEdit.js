function showConfirmationAlert() {
    Swal.fire({
        title: "¿Deseas guardar los cambios?",
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: "Guardar",
        denyButtonText: "No guardar"
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire("¡Guardado!", "", "success");
            var form = document.getElementById('saveStockForm');
            form.submit(); // Enviar el formulario
            return true; // Permitir el envío del formulario
        } else if (result.isDenied) {
            Swal.fire("Los cambios no se guardaron", "", "info");
            window.history.back(); // Redirigir al usuario a la página anterior
            return false; // Evitar el envío del formulario
        }
    });
    return false; // Evitar el envío del formulario si el usuario no hace clic en ningún botón de la alerta
}