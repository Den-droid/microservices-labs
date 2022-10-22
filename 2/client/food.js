function readFoodIdsRequest(callback) {
  let xmlHttp = new XMLHttpRequest();
  xmlHttp.onreadystatechange = function () {
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
      callback(xmlHttp.responseText);
  };
  xmlHttp.open("GET", "/api/food/ids", true);
  //xmlHttp.open("GET", "http://localhost:8080/api/food/ids", true);
  xmlHttp.send(null);
}

function readFoodByIdRequest(id, callback) {
  let xmlHttp = new XMLHttpRequest();
  xmlHttp.onreadystatechange = function () {
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
      callback(xmlHttp.responseText);
  };
  xmlHttp.open("GET", "/api/food/" + id, true);
  //xmlHttp.open("GET", "http://localhost:8080/api/food/" + id, true);
  xmlHttp.send(null);
}

function addFoodRequest(callback) {
  let xmlHttp = new XMLHttpRequest();
  xmlHttp.onreadystatechange = function () {
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) callback();
  };
  xmlHttp.open("POST", "/api/food", true);
  //xmlHttp.open("POST", "http://localhost:8080/api/food", true);
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.send(
    JSON.stringify({
      name: document.getElementsByName("addFoodName")[0].value,
      price: document.getElementsByName("addFoodPrice")[0].value,
    })
  );
}

function changeFoodRequest(id) {
  let xmlHttp = new XMLHttpRequest();
  xmlHttp.open("PUT", "/api/food/" + id, true);
  //xmlHttp.open("PUT", "http://localhost:8080/api/food/" + id, true);
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.send(
    JSON.stringify({
      name: document.getElementsByName("changeFoodName")[0].value,
      price: document.getElementsByName("changeFoodPrice")[0].value,
    })
  );
}

function deleteFoodRequest(id, callback) {
  let xmlHttp = new XMLHttpRequest();
  xmlHttp.onreadystatechange = function () {
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) callback();
  };
  xmlHttp.open("DELETE", "/api/food/" + id, true);
  //xmlHttp.open("DELETE", "http://localhost:8080/api/food/" + id, true);
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.send(null);
}

function clearSelect(selectElement) {
  while (selectElement.options.length > 0) {
    selectElement.remove(0);
  }
}

function setFoodIds() {
  let select = document.getElementsByName("selectFoodId")[0];
  let changeSelect = document.getElementsByName("selectForChangeFood")[0];
  let deleteSelect = document.getElementsByName("selectForDeleteFood")[0];
  clearSelect(select);
  clearSelect(changeSelect);
  clearSelect(deleteSelect);
  readFoodIdsRequest(function (text) {
    let data = JSON.parse(text);

    for (let i = 0; i < data.length; i++) {
      let option = new Option(data[i], data[i]);
      select.add(option);

      let changeOption = new Option(data[i], data[i]);
      changeSelect.add(changeOption);

      let deleteOption = new Option(data[i], data[i]);
      deleteSelect.add(deleteOption);
    }
  });
}

function setFoodInfo(e) {
  let id = e.target.value;
  readFoodByIdRequest(id, function (text) {
    let data = JSON.parse(text);

    let userContent = document.getElementById("foodContent");
    userContent.innerText =
      "name: " + data["name"] + "; price = " + data["price"];
  });
}

function setChangeFood(e) {
  let id = e.target.value;
  readFoodByIdRequest(id, function (text) {
    let data = JSON.parse(text);

    let username = document.getElementsByName("changeFoodName")[0];
    username.value = data["name"];
    let address = document.getElementsByName("changeFoodPrice")[0];
    address.value = data["price"];
  });
}

function addFood() {
  addFoodRequest(function () {
    setFoodIds();
  });
}

function changeFood() {
  let removeSelect = document.getElementsByName("selectForChangeFood")[0];
  let id = removeSelect.options[removeSelect.selectedIndex].value;
  changeFoodRequest(id);
}

function deleteFood() {
  let editSelect = document.getElementsByName("selectForDeleteFood")[0];
  let id = editSelect.options[editSelect.selectedIndex].value;
  deleteFoodRequest(id, function () {
    setFoodIds();
  });
}

let selectFood = document.getElementsByName("selectFoodId")[0];
selectFood.addEventListener("change", setFoodInfo);

let changeSelectFood = document.getElementsByName("selectForChangeFood")[0];
changeSelectFood.addEventListener("change", setChangeFood);

let addFoodButton = document.getElementById("addFood");
addFoodButton.addEventListener("click", addFood);

let changeFoodButton = document.getElementById("changeFood");
changeFoodButton.addEventListener("click", changeFood);

let deleteFoodButton = document.getElementById("deleteFood");
deleteFoodButton.addEventListener("click", deleteFood);

setFoodIds();
