//BOTON AGREGAR AL CARRO
document.addEventListener('DOMContentLoaded', function() {
    // Obtener el formulario por su ID
    const form = document.getElementById('myForm');

    // Agregar un controlador de eventos para el evento de envío del formulario
    form.addEventListener('submit', function(event) {
        // Prevenir el envío del formulario por defecto
        event.preventDefault();

        // Mostrar el SweetAlert
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Producto agregado al carro',
            showConfirmButton: false,
            timer: 1500
        }).then(() => {
            // Después de mostrar el SweetAlert, enviar el formulario
            form.submit();
        });
    });

});


