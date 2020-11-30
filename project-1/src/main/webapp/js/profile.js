function goBack(){
	console.log('return home button clicked');
	window.location = "http://localhost:8080/project-1/home.html";
}

let fields = document.getElementsByClassName('field');
let userString = sessionStorage.getItem('currentUser');

if (userString === null) {
	window.location = "http://localhost:8080/project-1/home.html";
} else {
	console.log(userString);
	let currentUser = JSON.parse(userString);
	console.log(currentUser);
	if (currentUser != null) {
		fields[0].innerText = currentUser.userId;
		fields[1].value = currentUser.username;
		fields[2].value = currentUser.password;
		fields[3].value = currentUser.firstName;
		fields[4].value = currentUser.lastName;
		fields[5].value = currentUser.email;
		fields[6].innerHTML = currentUser.hireDate;
		fields[7].innerText = currentUser.userRoleName;
		fields[8].innerText = currentUser.userRoleId;
	}
	
	
}