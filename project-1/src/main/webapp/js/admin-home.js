function goBack(){
	console.log('return home button clicked');
	window.location = "http://localhost:8080/project-1/home.html";
}

function sleep(milliseconds){
	const date = Date.now();
	let currentDate = null;
	do{
		currentDate = Date.now();
	}while(currentDate - date < milliseconds);
}

function goViewAllReimb(){
	console.log('reimbursement master list view option selected');
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
			window.location = "http://localhost:8080/project-1/view-all-reimbursements.html"
			console.log(sessionStorage.getItem('currentUser'))
		}
		if (this.readyState === 4 && this.status > 200) {
			console.log("Failed to load master table data");
			console.log("Failed. Status Code: " + this.status);
			var reason = {
				code : this.status,
				issue : 'Failed to load master table data as admin requested.'
			};
			console.log(reason);
			sessionStorage.setItem('failMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('failMessage'));
			window.location = "http://localhost:8080/project-1/error.html";
		}
		console.log("Processing view-all-reimb request now...")

	}
	xhr.open("GET", 'reimbursements/view-all');
	xhr.send();
}

function goViewAllUsers(){
	console.log('user master list view option selected');
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
			window.location = "http://localhost:8080/project-1/view-all-users.html"
			console.log(sessionStorage.getItem('currentUser'))
		}
		if (this.readyState === 4 && this.status > 200) {
			console.log("Failed to load master table data");
			console.log("Failed. Status Code: " + this.status);
			var reason = {
				code : this.status,
				issue : 'Failed to load master table data as admin requested.'
			};
			console.log(reason);
			sessionStorage.setItem('failMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('failMessage'));
			window.location = "http://localhost:8080/project-1/error.html";
		}
		console.log("Processing view-all-users request now...")

	}
	xhr.open("GET", 'users/view-all');
	xhr.send();
}

function goAuthorize(){
	console.log('authorization option selected');
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
			window.location = "http://localhost:8080/project-1/authorize.html"
			console.log(sessionStorage.getItem('currentUser'))
		}
		if (this.readyState === 4 && this.status > 200) {
			console.log("Failed to load dropdown data");
			console.log("Failed. Status Code: " + this.status);
			var reason = {
				code : this.status,
				issue : 'Failed to load dropdown data as admin requested.'
			};
			console.log(reason);
			sessionStorage.setItem('failMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('failMessage'));
			window.location = "http://localhost:8080/project-1/error.html";
		}
		console.log("Processing authorization form view now...")

	}
	xhr.open("GET", 'reimbursements/authorize');
	xhr.send();
}
function goSingleSearch(){
	console.log('user chose the single search option');
	sleep(1000);
	//begin AJAX workflow here
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState <= 3) {
			console.log('loading...')
		}
		if (this.readyState === 4 && this.status === 200) {
			console.log("Found Dropdown Menu Data!!")
			sessionStorage.setItem('dropData', this.responseText)
			window.location = "http://localhost:8080/project-1/find-reimb-by-user-id.html"
			console.log(sessionStorage.getItem('currentUser'))
		}
		if (this.readyState === 4 && this.status > 200) {
			console.log("Failed to load dropdown data");
			console.log("Failed. Status Code: " + this.status);
			var reason = {
				code : this.status,
				issue : 'Failed to load dropdown data as admin requested.'
			};
			console.log(reason);
			sessionStorage.setItem('failMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('failMessage'));
			window.location = "http://localhost:8080/project-1/error.html";
		}
		console.log("Processing...")

	}
	xhr.open("GET", 'reimbursements/view-all-by-user-id');
	xhr.send();
}
function goDeleteEmployee(){
	console.log('delete employee option selected');
	sleep(1000);
	//begin AJAX workflow here
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState <= 3) {
			console.log('loading...')
		}
		if (this.readyState === 4 && this.status === 200) {
			console.log("Found Dropdown Data!!")
			sessionStorage.setItem('dropData', this.responseText)
			window.location = "http://localhost:8080/project-1/remove-employee.html"
			console.log(sessionStorage.getItem('currentUser'))
		}
		if (this.readyState === 4 && this.status > 200) {
			console.log("Failed to load dropdown data");
			console.log("Failed. Status Code: " + this.status);
			var reason = {
				code : this.status,
				issue : 'Failed to load dropdown data as admin requested.'
			};
			console.log(reason);
			sessionStorage.setItem('failMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('failMessage'));
			window.location = "http://localhost:8080/project-1/error.html";
		}
		console.log("Processing remove employee view now...")

	}
	xhr.open("GET", 'users/view-employees');
	xhr.send();
}
function goViewPendingReimb(){
	console.log('pending reimbursement list view option selected');
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
			window.location = "http://localhost:8080/project-1/view-all-pending-reimbursements.html"
			console.log(sessionStorage.getItem('currentUser'))
		}
		if (this.readyState === 4 && this.status > 200) {
			console.log("Failed to load master table data");
			console.log("Failed. Status Code: " + this.status);
			var reason = {
				code : this.status,
				issue : 'Failed to load master table data as admin requested.'
			};
			console.log(reason);
			sessionStorage.setItem('failMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('failMessage'));
			window.location = "http://localhost:8080/project-1/error.html";
		}
		console.log("Processing view-all-pending-reimb request now...")

	}
	xhr.open("GET", 'reimbursements/view-all-pending');
	xhr.send();
}
var managerId = document.getElementById('user-id');
var managerName = document.getElementById('user-name');

let user = JSON.parse(sessionStorage.getItem('currentUser'));
console.log(user);

managerId.innerText = "";
managerName.innerText = "";

managerId.innerText = user.userId;
managerName.innerText = `${user.firstName} ${user.lastName}`;
