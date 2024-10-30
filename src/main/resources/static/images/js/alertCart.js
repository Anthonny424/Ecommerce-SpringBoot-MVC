document.addEventListener('DOMContentLoaded', function() {
    // Obtener el formulario por su ID
    const form = document.getElementById('myForm');

    // Obtener el valor de la factura total desde el elemento de etiqueta
    const totalLabel = document.getElementById('total-label');
    const totalText = totalLabel.textContent || totalLabel.innerText; // Obtener el texto, compatible con varios navegadores
    const totalValue = parseFloat(totalText.replace('S/ ', '').trim());

    // Agregar un controlador de eventos para el evento de envío del formulario
    form.addEventListener('submit', function(event) {
        // Prevenir el envío del formulario por defecto
        event.preventDefault();

        if (isNaN(totalValue) || totalValue <= 0) {
            // Mostrar el SweetAlert para informar que se debe comprar algo
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Debes comprar algo!',
                showConfirmButton: false,
                timer: 1500
            });
        } else {
            // Mostrar el SweetAlert de compra realizada con éxito
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Compra realizada con éxito!',
                showConfirmButton: false,
                timer: 1500
            }).then(() => {
                // Después de mostrar el SweetAlert, enviar el formulario
                form.submit();
            });
        }
    });
});


