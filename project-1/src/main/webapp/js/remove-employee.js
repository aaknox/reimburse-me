function goBack(){
	console.log('go back to admin button clicked');
	window.location = "http://localhost:8080/project-1/adminstration.html";
}

function removeEmployee(){
	console.log('admin clicked the remove button');
	//get selected selected data
	let options = document.getElementsByTagName('option');
	let chosen = "";
	let data = [];
	//let formData = chosen.innerText;
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
	//put data into template
	let deleteTemplate = {
		userId: data[0],
		username: data[6],
		firstName:data[5],
		lastName: data[3]
	};
	console.log(deleteTemplate);
	
    //This begins AJAX workflow
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
				issue : 'Failed to delete selected user.'
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
				details : 'User has been removed from API database!'
			};
			console.log(reason);
			sessionStorage.setItem('successMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('successMessage'));

			window.location = "http://localhost:8080/project-1/finish.html";
		}

		console.log("Processing");

	}
    xhr.open("POST","user/delete")
    xhr.send(JSON.stringify(deleteTemplate))
}


//PAGE ONLOAD EVENTS HERE
//target the dropdown
var dropdown = document.getElementById('dropdown-menu');

//clear any existing options
dropdown.innerHTML = "";

//insert a default called "Choose the employee ID number you wish to remove from API...", which will show as a visual cue
let defaultOption = document.createElement('option');
defaultOption.text = "Choose the employee ID number you wish to remove from API...";
defaultOption.value = 0;
dropdown.add(defaultOption);
dropdown.selectedIndex = 0;
//insert an option for each employee found in the JSON data (from the 'dropData' sessionStorage item)
let dataString = sessionStorage.getItem('dropData');
if (dataString === null) {
	window.location = "http://localhost:8080/project-1/";
} else {
	console.log(dataString);
	let data = JSON.parse(dataString);
	console.log(data);
	
	if (data != null) {
		//load data into dropdown menu
		var count = 1;
		data.forEach(d => {
			let d_option = document.createElement('option');
			d_option.text = `${d.userId} - ${d.lastName}, ${d.firstName} (${d.username})`;
			d_option.value = d.userId;
			dropdown.add(d_option);
		});
	}
}