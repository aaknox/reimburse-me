function goBack(){
	console.log('go back to admin button clicked');
	window.location = "http://localhost:8080/project-1/adminstration.html";
}

function sendSearch(){
	console.log('search button selected!');
	
	// begin AJAX workflow here
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState <= 3) {
			console.log('loading...')
		}
		if (this.readyState === 4 && this.status === 200) {
			console.log("Found Table Data!!")
			sessionStorage.setItem('tableData', this.responseText)
			// populate table data to webpage
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
					// load data into table
					data.forEach(d => {
						let row = table.insertRow();
						
						let id = row.insertCell(0);
						let amount = row.insertCell(1);
						let description = row.insertCell(2);
						let status = row.insertCell(3);
						let type = row.insertCell(4);
						let submitDate = row.insertCell(5);
						let resolveDate = row.insertCell(6);
						let authorId = row.insertCell(7);
						let managerId = row.insertCell(8);
						
						id.innerHTML = d.rId;
						amount.innerHTML = `$${d.amount}`;
						description.innerHTML = d.description;
						status.innerHTML = d.statusName;
						type.innerHTML = d.typeName;
						let subStr = d.submissionDate.replace("T", " | Time: ");
						submitDate.innerHTML = subStr;
						if(d.resolutionDate === ""){
							let emptyDate = "-";
							resolveDate.innerHTML = emptyDate;
						}else{
							let resStr = d.resolutionDate.replace("T", " | Time: ");
							resolveDate.innerHTML = resStr;
						}
						
						authorId.innerHTML = d.authorId;
						
						if(d.resolverId === 0){
							let emptyMgr = "N/A";
							managerId.innerHTML = emptyMgr;
						}else{
							managerId.innerHTML = d.resolverId;
						}
					});
				}
			}
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
		console.log("Processing search...")

	}
	// get selected selected data
	let options = document.getElementsByTagName('option');
	let chosen = "";
	let data = [];
	// let formData = chosen.innerText;
	console.log(options);
	for(var i = 0; i < options.length; i++){
		if(options[i].selected == true){
			console.log('Found It!');
			chosen = options[i].innerText.replace(`,`, " ").replace(`-`, " ").replace(`(`, "").replace(`)`, "");
			data = chosen.split(" ");
		}
	}
	
	console.log("Chosen...");
	console.log(chosen);
	console.log(data);
	let id = data[0];
	console.log("ID: " + id);
	xhr.open("GET", `reimbursements/view-all-by-user-id/search?id=${id}`, true);
	xhr.send();
}

// PAGE ONLOAD EVENTS HERE
// target the dropdown
var dropdown = document.getElementById('dropdown-menu');

// clear any existing options
dropdown.innerHTML = "";

// insert a default called "Choose the employee ID number you wish to remove
// from API...", which will show as a visual cue
let defaultOption = document.createElement('option');
defaultOption.text = "Choose the employee...";
defaultOption.value = 0;
dropdown.add(defaultOption);
dropdown.selectedIndex = 0;
// insert an option for each employee found in the JSON data (from the
// 'dropData' sessionStorage item)
let dataString = sessionStorage.getItem('dropData');
if (dataString === null) {
	window.location = "http://localhost:8080/project-1/";
} else {
	console.log(dataString);
	let data = JSON.parse(dataString);
	console.log(data);
	
	if (data != null) {
		// load data into dropdown menu
		var count = 1;
		data.forEach(d => {
			let d_option = document.createElement('option');
			d_option.text = `${d.userId} - ${d.lastName}, ${d.firstName} (${d.username})`;
			d_option.value = d.userId;
			dropdown.add(d_option);
		});
	}
}