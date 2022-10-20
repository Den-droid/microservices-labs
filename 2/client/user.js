function readUserIdsRequest(callback) {
  let xmlHttp = new XMLHttpRequest();
  xmlHttp.onreadystatechange = function () {
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
      callback(xmlHttp.responseText);
  };
  xmlHttp.open("GET", "/api/users/ids", true);
  //xmlHttp.open("GET", "http://localhost:8080/api/users/ids", true);
  xmlHttp.send(null);
}

function readUserByIdRequest(id, callback) {
  let xmlHttp = new XMLHttpRequest();
  xmlHttp.onreadystatechange = function () {
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
      callback(xmlHttp.responseText);
  };
  xmlHttp.open("GET", "/api/users/" + id, true);
  //xmlHttp.open("GET", "http://localhost:8080/api/users/" + id, true);
  xmlHttp.send(null);
}

function addUserRequest(callback) {
  let xmlHttp = new XMLHttpRequest();
  xmlHttp.onreadystatechange = function () {
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) callback();
  };
  xmlHttp.open("POST", "/api/users", true);
  //xmlHttp.open("POST", "http://localhost:8080/api/users", true);
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.send(
    JSON.stringify({
      name: document.getElementsByName("addUserName")[0].value,
      address: document.getElementsByName("addUserAddress")[0].value,
    })
  );
}

function changeUserRequest(id) {
  let xmlHttp = new XMLHttpRequest();
  xmlHttp.open("PUT", "/api/users/" + id, true);
  //xmlHttp.open("PUT", "http://localhost:8080/api/users/" + id, true);
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.send(
    JSON.stringify({
      name: document.getElementsByName("changeUserName")[0].value,
      address: document.getElementsByName("changeUserAddress")[0].value,
    })
  );
}

function deleteUserRequest(id, callback) {
  let xmlHttp = new XMLHttpRequest();
  xmlHttp.onreadystatechange = function () {
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) callback();
  };
  xmlHttp.open("DELETE", "/api/users/" + id, true);
  //xmlHttp.open("DELETE", "http://localhost:8080/api/users/" + id, true);
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.send(null);
}

function clearSelect(selectElement) {
  while (selectElement.options.length > 0) {
    selectElement.remove(0);
  }
}

function setUserIds() {
  let select = document.getElementsByName("selectUserId")[0];
  let changeSelect = document.getElementsByName("selectForChangeUser")[0];
  let deleteSelect = document.getElementsByName("selectForDeleteUser")[0];
  clearSelect(select);
  clearSelect(changeSelect);
  clearSelect(deleteSelect);
  readUserIdsRequest(function (text) {
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

function setUserInfo(e) {
  let id = e.target.value;
  readUserByIdRequest(id, function (text) {
    let data = JSON.parse(text);

    let userContent = document.getElementById("userContent");
    userContent.innerText =
      "name: " + data["name"] + "; address = " + data["address"];
  });
}

function setChangeUser(e) {
  let id = e.target.value;
  readUserByIdRequest(id, function (text) {
    let data = JSON.parse(text);

    let username = document.getElementsByName("changeUserName")[0];
    username.value = data["name"];
    let address = document.getElementsByName("changeUserAddress")[0];
    address.value = data["address"];
  });
}

function addUser() {
  addUserRequest(function () {
    setUserIds();
  });
}

function changeUser() {
  let removeSelect = document.getElementsByName("selectForChangeUser")[0];
  let id = removeSelect.options[removeSelect.selectedIndex].value;
  changeUserRequest(id);
}

function deleteUser() {
  let editSelect = document.getElementsByName("selectForDeleteUser")[0];
  let id = editSelect.options[editSelect.selectedIndex].value;
  deleteUserRequest(id, function(){
    setUserIds();
  });
}

let select = document.getElementsByName("selectUserId")[0];
select.addEventListener("change", setUserInfo);

let changeSelect = document.getElementsByName("selectForChangeUser")[0];
changeSelect.addEventListener("change", setChangeUser);

let addUserButton = document.getElementById("addUser");
addUserButton.addEventListener("click", addUser);

let changeUserButton = document.getElementById("changeUser");
changeUserButton.addEventListener("click", changeUser);

let deleteUserButton = document.getElementById("deleteUser");
deleteUserButton.addEventListener("click", deleteUser);

setUserIds();
