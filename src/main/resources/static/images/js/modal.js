const container = document.getElementById("container");
const registerBtn = document.getElementById("register");
const loginBtn = document.getElementById("login");

document
    .getElementById("iniciarSesionBtn")
    .addEventListener("click", function () {
    var modal = document.getElementById("modalInicioSesion");
    modal.style.display = "block";
});

document.getElementById("cerrarModal").addEventListener("click", function () {
    var modal = document.getElementById("modalInicioSesion");
    modal.style.display = "none";
});


registerBtn.addEventListener("click", () => {
    container.classList.add("active");
    container.querySelector(".toggle-left").style.backgroundImage =
    "url(https://img.freepik.com/fotos-premium/tienda-fotografia-ropa-tienda-ropa-percha-boutique-tienda-moderna_131346-613.jpg)";
    container.querySelector(".toggle-right").style.backgroundImage = "none";
});

loginBtn.addEventListener("click", () => {
    container.classList.remove("active");
    container.querySelector(".toggle-left").style.backgroundImage = "none";
    container.querySelector(".toggle-right").style.backgroundImage =
    "url(https://assets.architecturaldigest.in/photos/6412e4337c9d58b0688a5af3/master/w_1600%2Cc_limit/8.jpg)";
});