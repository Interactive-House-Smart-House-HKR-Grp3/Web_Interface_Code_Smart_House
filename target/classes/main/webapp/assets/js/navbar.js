const menu = document.querySelector('.menu span');
const nav = document.querySelector('.nav');
const links = document.querySelector('.nav-items');
const user = document.querySelector('.user')


$(document).ready(function(){
    $(".menu span").click(function(){

        nav.classList.toggle("expand");

        // show hide nav links on menu click with animation
        $(links).toggle("slow", function(){
            
        });

        // show hide user icon on menu click with animation
        $(user).toggle("slow", function(){

        });
    });

    // Makes sure that the NavItems are hidden if viewport is small to being with
    if($(window).width() < 883){
        hideNavItems();
    } 
});

// This function is called very often it might be needed to throtle it somehow if it slowsdown the website
$(window).resize(function () {
   if($(window).width() < 883){
        hideNavItems();
        nav.classList.remove("expand");
    } else {
        showNavItems();
    }
});


function hideNavItems(){
    $(links).hide();
    $(user).hide();
}

function showNavItems(){
    $(links).show();
    $(user).show();
}


