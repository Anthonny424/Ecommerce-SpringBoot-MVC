// Obtener el enlace de logout
var logoutLink = document.getElementById('logoutLink');

// Agregar un evento de click al enlace
logoutLink.addEventListener('click', function(event) {
    // Prevenir el comportamiento predeterminado del enlace
    event.preventDefault();

    // Mostrar la alerta de SweetAlert
    Swal.fire({
        title: '¿Cerrar sesión?',
        text: '¿Estás seguro de que quieres cerrar sesión?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, cerrar sesión',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        // Si el usuario confirma, redirigir al enlace de cierre de sesión
        if (result.isConfirmed) {
            // Obtener la URL del enlace de cierre de sesión
            var logoutUrl = logoutLink.getAttribute('href');
            window.location.href = logoutUrl;
        }
    });
});