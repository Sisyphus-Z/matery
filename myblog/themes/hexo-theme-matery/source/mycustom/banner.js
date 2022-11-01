// zzz
document.scrollingElement.scrollTop = 0;

document.getElementById("myhead").onclick=shrink;
window.onscroll = shrink;

var cWidth = document.body.clientWidth || document.documentElement.clientWidth;//页面可视区域宽度
var iWidth = window.innerWidth;//浏览器窗口大小
var temp2 = iWidth - cWidth

function shrink(){   

    var temp1=document.getElementsByClassName("carousel carousel-slider center index-cover")[0];
    
    if(temp1.style.height!="200px"){                
        temp1.style.height="200px";
        document.body.style.overflow="hidden";
        document.body.style.paddingRight=temp2+"px";        
        setTimeout(tempfunction1,1200);        
    }    
}

function tempfunction1(){
    document.body.style.overflow="";
    document.body.style.paddingRight="0px";
    document.getElementById("myhead").onclick=null;
}