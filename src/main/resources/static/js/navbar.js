document.addEventListener('DOMContentLoaded', function() {
    // Toggle hamburger menu
    const hamburgerMenu = document.querySelector('.hamburger-menu');
    const navbar = document.querySelector('.navbar');
    
    if (hamburgerMenu) {
        hamburgerMenu.addEventListener('click', function() {
            navbar.classList.toggle('mobile-menu-active');
        });
    }
    
    // Handle submenu on mobile
    const dropdownItems = document.querySelectorAll('.dropdown-item.has-submenu');
    
    dropdownItems.forEach(item => {
        item.addEventListener('click', function(e) {
            if (window.innerWidth <= 768) {
                e.preventDefault();
                this.classList.toggle('submenu-active');
            }
        });
    });
    
    // Close menu when clicking outside
    document.addEventListener('click', function(e) {
        if (!navbar.contains(e.target)) {
            navbar.classList.remove('mobile-menu-active');
        }
    });
});
