/*!
* Start Bootstrap - Agency v7.0.12 (https://startbootstrap.com/theme/agency)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-agency/blob/master/LICENSE)
*/
//
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Navbar shrink function
    var navbarShrink = function () {
        const navbarCollapsible = document.body.querySelector('#mainNav');
        if (!navbarCollapsible) {
            return;
        }
        navbarCollapsible.classList.toggle('navbar-shrink', window.scrollY > 0);
    };

    // Shrink the navbar 
    navbarShrink();

    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);

    // my custom
    // Navbar shrink function
    var navShrink = function () {
        const navbar = document.body.querySelector('#mainNav');
        const navbarLinks = document.querySelectorAll('#mainNav .navbar-nav .nav-link:not(.dropdown)');
        if (!navbar || !navbarLinks) {
            return;
        }
        navbar.classList.toggle('nav-shrink', window.scrollY > 0);
        navbarLinks.forEach(function(navbarLink) {
            navbarLink.classList.toggle('nav-shrink', window.scrollY > 0);
        });
    };
    // Shrink the navbar 
    navShrink();
    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navShrink);

    //  Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            rootMargin: '0px 0px -40%',
        });
    };

});