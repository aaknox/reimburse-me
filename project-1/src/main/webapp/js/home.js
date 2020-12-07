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
function goPastRequests(){
	console.log('view past requests option selected!');
	sleep(1000);
	
	//begin AJAX workflow here
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState <= 3) {
			console.log('loading...')
		}
		if (this.readyState === 4 && this.status === 200) {
			console.log("Found Table Data!!")
			sessionStorage.setItem('tableData', this.responseText)
			window.location = "http://localhost:8080/project-1/view-past-requests.html"
			console.log(sessionStorage.getItem('currentUser'))
		}
		if (this.readyState === 4 && this.status > 200) {
			console.log("Failed to load table data");
			console.log("Failed. Status Code: " + this.status);
			var reason = {
				code : this.status,
				issue : 'Failed to load table data for this user.'
			};
			console.log(reason);
			sessionStorage.setItem('failMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('failMessage'));
			window.location = "http://localhost:8080/project-1/error.html";
		}
		console.log("Processing past requests view...")

	}
	let user = JSON.parse(sessionStorage.getItem('currentUser'));
	console.log(user);
	let id = user.userId;
	console.log("ID: " + id);
	xhr.open("GET", `reimbursements/view-past?id=${id}`);
	xhr.send();
}

function goPendingRequests(){
	console.log('view pending requests option selected!');
	sleep(1000);
	
	//begin AJAX workflow here
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState <= 3) {
			console.log('loading...')
		}
		if (this.readyState === 4 && this.status === 200) {
			console.log("Found Table Data!!")
			sessionStorage.setItem('tableData', this.responseText)
			window.location = "http://localhost:8080/project-1/view-pending-requests.html"
			console.log(sessionStorage.getItem('currentUser'))
		}
		if (this.readyState === 4 && this.status > 200) {
			console.log("Failed to load table data");
			console.log("Failed. Status Code: " + this.status);
			var reason = {
				code : this.status,
				issue : 'Failed to load table data for this user.'
			};
			console.log(reason);
			sessionStorage.setItem('failMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('failMessage'));
			window.location = "http://localhost:8080/project-1/error.html";
		}
		console.log("Processing past requests view...")

	}
	let user = JSON.parse(sessionStorage.getItem('currentUser'));
	console.log(user);
	let id = user.userId;
	console.log("ID: " + id);
	xhr.open("GET", `reimbursements/view-pending?id=${id}`);
	xhr.send();
}
function goAdmin(){
	console.log('admin option selected!');
	sleep(1000);
	window.location = "http://localhost:8080/project-1/adminstration.html";
}

function profile() {
	console.log('profile link clicked!');
	let xhr = new XMLHttpRequest();
	let user = sessionStorage.getItem('currentUser');
	let data = JSON.parse(user);
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
	xhr.send(JSON.stringify(data));
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