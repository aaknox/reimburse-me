var code = document.getElementById('status-code');
	var message = document.getElementById('message');
	var cause = document.getElementById('reason');
	//clear any previous content
	code.innerHTML = "";
	message.innerHTML = "";
	cause.innerHTML = "";
	//convert response json data into javascript object
	let successString = sessionStorage.getItem('successMessage');
	console.log(successString)
	if (successString === null) {
		window.location = "http://localhost:8080/project-1/home.html";
	} else {
		let requestMessage = JSON.parse(successString);
		console.log(requestMessage);
		let reimb = JSON.parse(requestMessage.reimbRequest);

		if (requestMessage != null) {
			code.innerText = 'Success! (Status Code: ' + requestMessage.code
					+ ')';
			message.innerText = requestMessage.details;
			cause.innerHTML = "<strong>Please note your new reimbursement request info below: </strong><ul>"
					+ `<li>ReimbID: ${reimb.rId}</li>`
					+ `<li>Amount: $${reimb.amount}</li>`
					+ `<li>Submitted on: ${reimb.submissionDate.replace("T", " | Time: ")}</li>`
					+ `<li>Description: ${reimb.description}</li>`
					+ `<li>Type: ${reimb.typeName}</li></ul>`
					+ `<br>Your request is currently ${reimb.statusName}. Please allow 5-7 business days to process your request. Thank you for using ReimburseMe!`
		}
	}

function goBack() {
	console.log('return button clicked...');
	window.location = "http://localhost:8080/project-1/home.html";
}