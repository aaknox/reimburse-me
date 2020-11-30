function goBack(){
	console.log('return home button clicked');
	window.location = "http://localhost:8080/project-1/home.html";
}

var managerId = document.getElementById('user-id');
var managerName = document.getElementById('user-name');

let user = JSON.parse(sessionStorage.getItem('currentUser'));
console.log(user);

managerId.innerText = "";
managerName.innerText = "";

managerId.innerText = user.userId;
managerName.innerText = `${user.firstName} ${user.lastName}`;
