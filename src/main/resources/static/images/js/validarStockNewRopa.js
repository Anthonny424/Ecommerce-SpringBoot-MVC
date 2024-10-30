document.addEventListener('DOMContentLoaded', function () {
    // Función para deshabilitar la entrada de teclado y pegado
    function disableKeyboardInput(inputId) {
        const input = document.getElementById(inputId);

        input.addEventListener('keydown', function (event) {
            event.preventDefault();
        });

        input.addEventListener('paste', function (event) {
            event.preventDefault();
        });

        input.addEventListener('input', function (event) {
            event.preventDefault();
        });
    }

    // Aplicar la función a ambos campos
    disableKeyboardInput('precio');
    disableKeyboardInput('stock');
});

