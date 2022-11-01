// zzz

$(document).ready(function(){

    if(document.getElementById("dplayer")==null){
        console.warn("未找到#dplayer,不进行dplayer修改");
        return;
    }

try{


var temp4=document.getElementById("dplayer").parentNode;
var temp5=document.createElement("div");
var temp6=`
<style>
#fullscreen{
    height:70px;
    margin: 2px auto 0px auto;

}

#fullscreen>div{
    width:150px;

    margin: auto;

    height: auto;
    

    background-color: #f5712c;

    border-radius: 5px;

    cursor:pointer;

    // position: absolute;
    // left:50%;
    // transform:translate(-50%,0);

}

#fullscreen>div>div{
    
    text-align: center;

    font-size: 1.4rem;
    color: #fff;

    padding: 0.6rem 0rem;

    line-height: 1.7;
    
}

</style>

<div id="fullscreen">
<div id="bar"><div>点击全屏 <i class="fa-solid fa-expand"></i></div></div>
</div>
`


temp5.innerHTML=temp6;

var next_node = document.getElementById("dplayer").nextSibling;

// temp4.insertBefore(temp5,next_node);

temp4.appendChild(temp5);
document.getElementById("bar").onclick=tempfunction1;

function tempfunction1(){
    dp.fullScreen.request('browser');
}


console.warn("添加全屏按钮成功")
}catch(e){
    console.warn(e);
}



try{
var temp1 = document.getElementsByClassName("dplayer-video-current")[0];
var temp2= window.getComputedStyle(temp1).getPropertyValue('width');
var temp3=parseFloat(temp2)*9/16
temp1.setAttribute("style","min-height:"+temp3+"px");
console.warn("更改DPlayer长宽比成功");
console.warn(temp1);
}catch(e){
    console.warn("更改DPlayer长宽比失败");
    console.warn(e);
}
});