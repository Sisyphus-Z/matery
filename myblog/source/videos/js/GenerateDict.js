var l1 = data;

const d1={};

for(i=0;i<l1.length;i++){
    var temp1=Object.keys(l1[i])[0].split("_");

   
    var category1=temp1[0];
    var title1=temp1[2];
    
    if(title1==""){
        title1=category1;
    }
    
    var sort1=parseInt(temp1[1].replace(/[^0-9]/g,''));
    
    var name1=temp1[3];
    if(name1==""){
        name1=temp1[1];
    }

    var type1=temp1[4];
    
    var temp2=Object.values(l1[i])[0]
    


if(d1[category1]==undefined){
        d1[category1]=[];
}
if(d1[category1][sort1]==undefined){
    d1[category1][sort1]={};
}

d1[category1][sort1]["category1"]=category1;
d1[category1][sort1]["sort1"]=sort1;
d1[category1][sort1]["name1"]=name1;
d1[category1][sort1]["title1"]=title1;
    

if(type1=="p"){
    d1[category1][sort1]["picture1"]=temp2;
    
}else{
    d1[category1][sort1]["video1"]=temp2;
}

};

// for(var temp2 in d1){
//     var l2 = d1[temp2];
//     for(var i = 0 ;i<l2.length;i++){
//         if(l2[i] == " " || l2[i] == null || typeof(l2[i]) == "undefined")  
//         {  
//                  l2.splice(i,1);  
//                  i= i-1;  

//         }  

//     }
// }