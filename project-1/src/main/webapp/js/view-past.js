function goBack(){
	console.log('return home button clicked');
	window.location = "http://localhost:8080/project-1/home.html";
}

//ONLOAD OF PAGE CODE
let author = document.getElementById('author-id');
let userString = sessionStorage.getItem('currentUser');
const table = document.getElementById('table-data');
let dataString = sessionStorage.getItem('tableData');

if (userString === null) {
	window.location = "http://localhost:8080/project-1/";
} else {
	console.log(userString);
	console.log(dataString);
	let currentUser = JSON.parse(userString);
	console.log(currentUser);
	let data = JSON.parse(dataString);
	console.log(data);
	
	if (currentUser != null) {
		author.innerText = "User ID: " + currentUser.userId;
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
			let imgBtn = row.insertCell(8);
			
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
			
			console.log(d.receipt);
			var link = document.createElement('a');
			link.href = "view-receipt.html";
			link.target = "_self";
			link.onclick = function(){
				var pictureData = {
					receipt: d.receipt	
				};
				
				sessionStorage.setItem('picture', JSON.stringify(pictureData));
				console.log(sessionStorage.getItem('picture'));
			};
			
			var img = document.createElement('img');
			img.height = "50";
			img.width = "50";
			img.src = d.receipt;
			img.alt = 'Receipt Image';
			
			link.appendChild(img);
			imgBtn.appendChild(link);
		});
	}
}