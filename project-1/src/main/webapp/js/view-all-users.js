function goBack(){
	console.log('go back to admin button clicked');
	window.location = "http://localhost:8080/project-1/adminstration.html";
}

// ONLOAD OF PAGE CODE
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
			let d_username = row.insertCell(1);
			let d_password = row.insertCell(2);
			let d_firstname = row.insertCell(3);
			let d_lastname = row.insertCell(4);
			let d_email = row.insertCell(5);
			let d_hiredate = row.insertCell(6);
			let d_role = row.insertCell(7);
			
			id.innerHTML = d.userId;
			d_username.innerHTML = d.username;
			d_password.innerHTML = d.password;
			d_firstname.innerHTML = d.firstName;
			d_lastname.innerHTML = d.lastName;
			d_email.innerHTML = d.email;
			d_hiredate.innerHTML = d.hireDate;
			d_role.innerHTML = `${d.userRoleName} (${d.userRoleId})`;
		});
	}
}