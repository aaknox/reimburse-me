function sendProfileUpdate(){
	console.log('profile update request has been submitted to server...');
	
	//get form elements
	let uId = document.getElementById('user-id').innerText;
	let myUsername = document.getElementById('username').value;
	let myPassword = document.getElementById('password').value;
	let myFirstname = document.getElementById('firstname').value;
	let myLastname = document.getElementById('lastname').value;
	let myEmail = document.getElementById('email').value;
	let myHiredate = document.getElementById('user-hire-date').innerText;
	let roleId = document.getElementById('user-role-id').innerText;
	let roleName = document.getElementById('user-role-name').innerText;
	
	//set up template
	let updateTemplate = {
			userId : uId,
			username : myUsername,
			password : myPassword,
			firstName : myFirstname,
			lastName : myLastname,
			email : myEmail,
			hireDate : myHiredate,
			userRoleId : roleId, 
			userRoleName : roleName
		};
	console.log(updateTemplate);
	
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
				issue : 'Failed to update profile.'
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
				details : 'Your profile has successfully been updated!'
			};
			console.log(reason);
			sessionStorage.setItem('successMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('successMessage'));

			window.location = "http://localhost:8080/project-1/done.html";
		}

		console.log("Processing");

	}
	xhr.open("POST", "user/update");
	xhr.send(JSON.stringify(updateTemplate));
}