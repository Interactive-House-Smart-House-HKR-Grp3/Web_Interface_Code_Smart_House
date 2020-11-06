const menu = document.querySelector('.menu span');
const nav = document.querySelector('.nav');

menu.onclick = () =>{
    nav.classList.toggle('collapse');
}