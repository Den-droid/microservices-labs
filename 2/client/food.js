function readFoodIds(callback){
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.responseText);
    }
    xmlHttp.open("GET", "/api/food/ids", true);
    //xmlHttp.open("GET", "http://localhost:8081/api/food/ids", true);
    xmlHttp.send(null);
}

function readFoodById(id, callback){
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.responseText);
    }
    xmlHttp.open("GET", "/api/food/" + id, true);
    //xmlHttp.open("GET", "http://localhost:8081/api/food/" + id, true);
    xmlHttp.send(null);
}

function setFoodIds(){
    let select = document.getElementById("selectFoodId");
    select.addEventListener("change", setFoodInfo);
    readFoodIds(function(text){
        let data = JSON.parse(text);

        for(let i = 0; i < data.length; i++){
            let option = new Option(data[i], data[i])
            select.add(option)
        }
    });
}

function setFoodInfo(e){
    let id = e.target.value;
    readFoodById(id, function(text){
        let data = JSON.parse(text);

        let userContent = document.getElementById("foodContent");
        userContent.innerText = "name: " + data["name"] + "; price = " + data["price"];
    });
}

setFoodIds();