
var x=window.location.search;

x=x.replace("?","");

x=x.split("_");

x[1]=parseInt(x[1]);

if(isEmpty(x[1])){
    x[1]=getFirst(d1[x[0]]);    
}

createDPlayer();

createList(x[0]);

videoJump(x[0],x[1]);


// $("html, body").animate(
//             {scrollTop: $("#page").offset().top-80}, {duration: 500}
//         );


