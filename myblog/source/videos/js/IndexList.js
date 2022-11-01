

    var l1 = Object.keys(d1);

    var a1 = document.getElementsByClassName("stui-vodlist")[0];
    
    a1.innerHTML = '';
    for (i = 0; i < l1.length; i++) {

        var a2 = document.createElement("li");        
        a2.setAttribute("class", "col-md-6 col-sm-4 col-xs-3")
        a1.appendChild(a2);        

        var a3 = document.createElement("div");
        a3.setAttribute("class", "stui-vodlist__box")
        a2.appendChild(a3);

        var a4 = document.createElement("a");
        a3.appendChild(a4);
        a4.setAttribute("class", "stui-vodlist__thumb lazyload");
        a4.setAttribute("href", "page.html?" + l1[i]);
        // a4.setAttribute("title", getFirstElement(d1[l1[i]])["title1"]);
        
        a4.setAttribute("style", "background-image: url('"+getFirstElement(d1[l1[i]])["picture1"]+"')");

        var a5 = document.createElement("div");
        a3.appendChild(a5);
        a5.setAttribute("class","stui-vodlist__detail");

        var a6=document.createElement("h4");
        a5.appendChild(a6);
        a6.setAttribute("class","title text-overflow");

        var a7=document.createElement("a");
        a6.appendChild(a7);
        a7.setAttribute("href","page.html?" +l1[i]);
        a7.innerHTML=getFirstElement(d1[l1[i]])["title1"];


    }


    // 改matery
    document.getElementById("articleContent").setAttribute("id","articleContent111")