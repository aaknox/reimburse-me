function goBack(){
	console.log('return to login button clicked');
	window.location = "http://localhost:8080/project-1/index.html";
}

function sendRegistration(){
	console.log('submit user registration clicked!');
	//get form data
	let myUsername = document.getElementById('username').value;
	let myPassword = document.getElementById('password').value;
	let myFirstname = document.getElementById('firstname').value;
	let myLastname = document.getElementById('lastname').value;
	let myEmail = document.getElementById('email').value;
	let myHireDate = "";
	let myRoleId = 1;
	let myRoleName = "EMPLOYEE";
	
	//setup template
	let registerTemplate = {
		username : myUsername,
		password : myPassword,
		firstName : myFirstname,
		lastName : myLastname,
		email : myEmail,
		hireDate : myHireDate,
		userRoleId : myRoleId, 
		userRoleName : myRoleName
	};
	console.log(registerTemplate);
	
	// start AJAX workflow
	let xhr = new XMLHttpRequest()
	xhr.onreadystatechange = function() {
		console.log('ReadyState: ' + this.readyState);
		if (this.readyState <= 3) {
			console.log('loading');
		}
		if (this.readyState === 4 && this.status > 200) {
			console.log("Failed. Status Code: " + this.status)
			var reason = {
				code : this.status,
				issue : 'Failed to register user.'
			};
			console.log(reason);
			sessionStorage.setItem('failMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('failMessage'));
			window.location = "http://localhost:8080/project-1/error.html"
		}
		if (this.readyState === 4 && this.status === 200) {
			console.log("Success");

			var reason = {
				code : this.status,
				details : 'Your registration is complete!'
			};
			console.log(reason);
			sessionStorage.setItem('successMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('successMessage'));

			window.location = "http://localhost:8080/project-1/complete.html";
		}

		console.log("Processing");

	}
	xhr.open("POST", "user/add");
	xhr.send(JSON.stringify(registerTemplate));
}