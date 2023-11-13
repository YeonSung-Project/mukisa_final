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
        if (window.scrollY === 0) {
            navbarCollapsible.classList.remove('navbar-shrink')
        } else {
            navbarCollapsible.classList.add('navbar-shrink')
        }

    };

    // Shrink the navbar 
    navbarShrink();

    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);





    // my custom
        // Navbar shrink function
        var navShrink = function () {
            const navbar = document.body.querySelector('#mainNav');
            const navbarLinks = document.body.querySelectorAll('#mainNav .navbar-nav .nav-item .nav-link:last-child');
            if (!navbar || !navbarLinks) {
                return;
            }
            if (window.scrollY === 0) {
                navbar.classList.remove('nav-shrink');
                navbarLinks.forEach(function(navbarLink) {
                    navbarLink.classList.remove('nav-shrink');
                });
            } else {
                navbar.classList.add('nav-shrink');
                navbarLinks.forEach(function(navbarLink) {
                    navbarLink.classList.add('nav-shrink');
                });
            }
        };
        // Shrink the navbar 
        navShrink();
        // Shrink the navbar when page is scrolled
        document.addEventListener('scroll', navShrink);




    // my custom 2
        // Navbar shrink function
        var navShrink = function () {
            const navbar = document.body.querySelector('#mainNav');
            const navbarLinks = document.body.querySelectorAll('#mainNav .navbar-nav .nav-item .nav-link:last-child');
            if (!navbar || !navbarLinks) {
                return;
            }
            if (window.scrollY === 0) {
                navbar.classList.remove('nav-shrink');
                navbarLinks.forEach(function(navbarLink) {
                    navbarLink.classList.remove('nav-shrink');
                });
            } else {
                navbar.classList.add('nav-shrink');
                navbarLinks.forEach(function(navbarLink) {
                    navbarLink.classList.add('nav-shrink');
                });
            }
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

    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });

});
