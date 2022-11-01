function createDPlayer() {
    const dp = new DPlayer({
        container: document.getElementById('dplayer'),
        video: {

        },
    });

    window.dp = dp;
}

function createList(category1) {

    var l2 = d1[category1];

    var a1 = document.getElementById("mylist");

    a1.innerHTML = '';
    for (i = 1; i < l2.length; i++) {

        if (isEmpty(l2[i])) {
            continue;
        }
        a2 = document.createElement("a");
        a2.innerHTML = l2[i]["name1"];
        a3 = document.createElement("li");
        a3.appendChild(a2);
        a3.setAttribute("id", l2[i]["sort1"]);
        a1.appendChild(a3);
        a2.setAttribute("category1", l2[i]["category1"]);
        a2.setAttribute("sort1", l2[i]["sort1"]);
        a2.setAttribute("onclick", "videoJump('" + category1 + "'," + l2[i]["sort1"] + ")");



    }
}


function videoJump(category1, sort1) {

    dp.switchVideo({
        url: d1[category1][sort1]["video1"],
        pic: d1[category1][sort1]["picture1"],
    });
    dp.notice("",10);

    // dp.seek(0);
    // dp.play();

    var ilist = document.getElementById("mylist").children;
    for (var i = 0; i < ilist.length; i++) {
        ilist[i].classList.remove("active");
    };
    document.getElementById(sort1).classList.add("active");

    document.getElementById("aaa1").innerHTML = d1[category1][sort1]["title1"]+" "+d1[category1][sort1]["name1"];
}; 



function getFirst(l1) {    
    for (var i=0; i < l1.length; i++) {
        if (isEmpty(l1[i])) {
            continue;           
        }
        return i;
    }
}

function getFirstElement(l1) {
        return l1[getFirst(l1)];
}

function isEmpty(temp1){
    if(temp1 == "" ||temp1!=temp1 ||temp1 == null || typeof (temp1) == "undefined"){
        return true;        
    }
    return false;
}