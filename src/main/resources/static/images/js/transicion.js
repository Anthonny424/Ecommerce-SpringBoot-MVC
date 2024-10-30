function delay(n) {
    // Setting the time to be 2 seconds || 1 second = 1000
    n = n || 2000;

    // loading it
    return new Promise((done) => {
        setTimeout(() => {
            done();
        }, n)
    })
}

function pageTransition() {
    const tl = gsap.timeline();
    // making the loading screen animation
    tl.to(".loading-screen", {
        duration: 1.2,
        width: "100%",
        left: "0%",
        ease: "Expo.easeInOut",
    });

    tl.to(".loading-screen", {
        duration: 1,
        width: "100%",
        left: "100%",
        ease: "Expo.easeInOut",
        delay: 0.3,
    });


    // loading screen to be -100% ( left )
    tl.set(".loading-screen", { left: "-100%" });
}


// Here comes the main js part

$(function () {
    // Initializing barba
    barba.init({
        sync: true,

        transitions: [
            {
                async leave (data) {
                    const done = this.async();
                    pageTransition();
                    await delay(1000);
                    done();
                },
            }
        ]
    })
});
function redirectToPage() {
    // Cambia la URL a la que deseas redirigir
    window.location.href = "/admin/logout";
}


