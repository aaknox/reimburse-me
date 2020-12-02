function goBack(){
	console.log('go back to admin button clicked');
	window.location = "http://localhost:8080/project-1/adminstration.html";
}

function statusRadioValue() {
	var ele = document.getElementsByName('status');
	console.log(ele);
	let selectedStatus;
	for (var i = 0; i < ele.length; i++) {
		if (ele[i].checked) {
			selectedStatus = ele[i];
			break;
		}
	}
	return selectedStatus.value;
}

function sendDecision(){
	console.log('Admin is sending the reimb decision to server...');
	//get form data
	//dropdown data extraction
	let options = document.getElementsByTagName('option');
	let chosen = "";
	let data = [];
	console.log(options);
	for(var i = 0; i < options.length; i++){
		if(options[i].selected == true){
			console.log('Found It!');
			chosen = options[i].innerText.replace(`$`, " ").replace(`-`, " ");
			data = chosen.split(" ");
		}
	}
	
	console.log("Chosen...");
	console.log(chosen);
	console.log(data);
	let reimbId = data[0];
	let reimbAmt = data[4];
	//end of dropdown data extraction
	//radio button data
	let status = statusRadioValue();
	console.log('StatusID: ' + status);
	let statusName;

	if (status == 1) {
		statusName = 'APPROVED';
	}else{
		statusName = 'DENIED';
	}
	console.log('StatusName: ' + statusName);
	//get current manager id
	let managerString  = sessionStorage.getItem('currentUser');
	console.log(managerString);
	let manager = JSON.parse(managerString);
	console.log(manager);
	let mId = `${manager.userId}`;
	console.log(mId);
	//make template
	let decisionTemplate = {
			rId: reimbId,
			amount: reimbAmt,
			statusId: status,
			statusName: statusName,
			resolverId: mId
	};
	console.log(decisionTemplate);
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
				issue : 'Failed to authorize the reimbursement request.'
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
				details : 'Reimbursement request has successfully been authorized!',
			};
			console.log(reason);
			sessionStorage.setItem('successMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('successMessage'));

			window.location = "http://localhost:8080/project-1/decided.html";
		}

		console.log("Processing");

	}
	
	
	xhr.open("POST", "reimbursement/authorize/decided");
	xhr.send(JSON.stringify(decisionTemplate));
}


//PAGE ONLOAD EVENTS HERE
/***************For Table View*********************/
const table = document.getElementById('table-data');
let dataString = sessionStorage.getItem('tableData');

if (dataString === null) {
	window.location = "http://localhost:8080/project-1/";
} else {
	console.log(dataString);
	let data = JSON.parse(dataString);
	console.log(data);
	
	if (data != null) {
		table.innerHTML = "";
		//load data into table
		data.forEach(d => {
			let row = table.insertRow();
			
			let id = row.insertCell(0);
			let amount = row.insertCell(1);
			let description = row.insertCell(2);
			let status = row.insertCell(3);
			let type = row.insertCell(4);
			let submitDate = row.insertCell(5);
			let authorId = row.insertCell(6);
			
			id.innerHTML = d.rId;
			amount.innerHTML = `$${d.amount}`;
			description.innerHTML = d.description;
			status.innerHTML = d.statusName;
			type.innerHTML = d.typeName;
			let subStr = d.submissionDate.replace("T", " | Time: ");
			submitDate.innerHTML = subStr;
			authorId.innerHTML = d.authorId;
			
		});
	}
}
/***************For Dropdown Menu******************/
//target the dropdown
var dropdown = document.getElementById('dropdown-menu');

//clear any existing options
dropdown.innerHTML = "";

//insert a default option which will show as a visual cue
let defaultOption = document.createElement('option');
defaultOption.text = "Choose the reimb ID number you wish to authorize...";
defaultOption.value = 0;
dropdown.add(defaultOption);
dropdown.selectedIndex = 0;
//insert an option for each reimb found in the JSON data (from the 'dropData' sessionStorage item)

if (dataString === null) {
	window.location = "http://localhost:8080/project-1/";
} else {
	console.log(dataString);
	let data = JSON.parse(dataString);
	console.log(data);
	
	if (data != null) {
		//load data into dropdown menu
		data.forEach(d => {
			let d_option = document.createElement('option');
			d_option.text = `${d.rId} - $${d.amount}`;
			d_option.value = d.rId;
			dropdown.add(d_option);
		});
	}
}