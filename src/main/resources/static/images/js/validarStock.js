document.addEventListener('DOMContentLoaded', function() {
    var cantidadInput = document.getElementById('cantidad');
    var submitButton = document.getElementById('submitButton');
    var stock = parseInt('${producto.stock}');

    cantidadInput.addEventListener('keydown', function(event) {
        // Deshabilitar la entrada directa de teclado
        event.preventDefault();
        // Manejar el evento de las flechas
        if (event.keyCode === 38 || event.keyCode === 40) {
            // Flecha arriba o abajo presionada, aumentar o disminuir en 1
            if (event.keyCode === 38 && parseInt(cantidadInput.value) < stock) {
                cantidadInput.value++;
            } else if (event.keyCode === 40 && parseInt(cantidadInput.value) > 1) {
                cantidadInput.value--;
            }
        }
    });

    // Deshabilitar el botón de submit si el stock es cero
    if (stock === 0) {
        submitButton.disabled = true;
        Swal.fire("Este producto no cuenta con stock!");
    }
});
