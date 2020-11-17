function LogIn() {
	console.log("Beginning log in request...");
	let uname = document.getElementById("username").value;
	let pword = document.getElementById("password").value;
	console.log("Username " + uname);
	console.log("Password " + pword);

	// This begins AJAX workflow
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			console.log("Success");
			sessionStorage.setItem('currentUser', this.responseText);
			window.location = "http://localhost:8080/project-1/home.html";
			console.log(sessionStorage.getItem('currentUser'));
		}
		if (this.readyState === 4 && this.status === 204) {
			console.log("Failed");
			let childDiv = document.createElement('div');
			childDiv.setAttribute('id', 'warningText');
			childDiv.textContent = "Failed to log in! Username or Password is incorrect";
		}
		console.log("Processing");

	}
	xhr.open("POST", "http://localhost:8080/project-1/login");

	let loginTemplate = {
		username : uname,
		password : pword
	};

	loginTemplate = JSON.stringify(loginTemplate).replace(/\\"/g, '"');
	console.log(loginTemplate);
	xhr.send(loginTemplate);
};