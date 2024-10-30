let text=document.getElementById('text');
let treeLeft=document.getElementById('tree-left');
let treeRight=document.getElementById('tree-right');
window.addEventListener('scroll', ()=>{
    let value = window.scrollY;

    text.style.marginTop = value * 2.5 + 'px';
    treeLeft.style.marginLeft = value * 1.5 + 'px';
    treeRight.style.marginLeft= value * -1.5 + 'px';

});