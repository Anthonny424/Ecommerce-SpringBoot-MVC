function showDeleteAlert(event) {
    event.preventDefault(); // Prevenir el comportamiento predeterminado del enlace
    Swal.fire({
        title: "¿Estás seguro?",
        text: "¡No podrás revertir esto!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, eliminarlo"
    }).then((result) => {
        if (result.isConfirmed) {
            // Redirigir al usuario a la URL del enlace
            window.location.href = event.target.href;
        }
    });
}