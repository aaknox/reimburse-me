var code = document.getElementById('status-code');
	var message = document.getElementById('message');
	var cause = document.getElementById('reason');
	//clear any previous content
	code.innerHTML = "";
	message.innerHTML = "";
	cause.innerHTML = "";
	//convert response json data into javascript object
	let confirmString = sessionStorage.getItem('successMessage');
	console.log(confirmString)
	if (confirmString === null) {
		window.location = "http://localhost:8080/project-1/index.html";
	} else {
		let myMessage = JSON.parse(confirmString);
		console.log(myMessage);

		if (myMessage != null) {
			code.innerText = 'Complete! (Status Code: ' + myMessage.code
					+ ')';
			message.innerText = myMessage.details;
			cause.innerHTML = "<strong>Thank you for using ReimburseMe! </strong>"
		}
	}

function goBack() {
	console.log('return to login button clicked...');
	window.location = "http://localhost:8080/project-1/index.html";
}