function sleep(milliseconds){
	const date = Date.now();
	let currentDate = null;
	do{
		currentDate = Date.now();
	}while(currentDate - date < milliseconds);
}

function logout() {
	console.log('logout link clicked')
	let xhr = new XMLHttpRequest();

	xhr.open("POST", "http://localhost:8080/project-1/logout");
	xhr.send();

	sessionStorage.removeItem('currentUser');
	window.location = "http://localhost:8080/project-1/";
}

function goSubmit(){
	console.log('submit option selected!');
	sleep(1000);
	window.location = "http://localhost:8080/project-1/submit-request.html";
}

function profile() {
	console.log('profile link clicked!');
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState <= 3) {
			console.log('loading...')
		}
		if (this.readyState === 4 && this.status === 200) {
			console.log("Found Profile!!")
			sessionStorage.setItem('currentUser', this.responseText)
			window.location = "http://localhost:8080/project-1/profile.html"
			console.log(sessionStorage.getItem('currentUser'))
		}
		if (this.readyState === 4 && this.status === 204) {
			console.log("Failed to load profile")
			alert("No profile can be found for this user");
		}
		console.log("Processing profile view...")

	}
	xhr.open("POST", "user");
	xhr.send();

	window.location = "http://localhost:8080/project-1/user/profile.html"
}

let welcome = document.getElementById('welcome-message');
let userString = sessionStorage.getItem('currentUser');

if (userString === null) {
	window.location = "http://localhost:8080/project-1/";
} else {
	console.log(userString);
	let currentUser = JSON.parse(userString);
	console.log(currentUser);
	var adminOption = document.querySelector('.admin');
	console.log(adminOption);
	if (currentUser != null) {
		//a quick check to see if currentUser is a manager
		if(currentUser.userRoleId === 2){
			adminOption.classList.remove('disabled');
		}
		welcome.innerHTML = "Welcome, " + currentUser.firstName + "!";
	}
}