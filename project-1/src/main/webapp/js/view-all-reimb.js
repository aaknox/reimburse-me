function goBack(){
	console.log('go back to admin button clicked');
	window.location = "http://localhost:8080/project-1/adminstration.html";
}

//ONLOAD OF PAGE CODE
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
			let resolveDate = row.insertCell(6);
			let managerId = row.insertCell(7);
			
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
			
			if(d.resolverId === 0){
				let emptyMgr = "N/A";
				managerId.innerHTML = emptyMgr;
			}else{
				managerId.innerHTML = d.resolverId;
			}
		});
	}
}