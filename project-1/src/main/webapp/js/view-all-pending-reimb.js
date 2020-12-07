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
			let authorId = row.insertCell(6);
			let imgBtn = row.insertCell(7);
			
			id.innerHTML = d.rId;
			amount.innerHTML = `$${d.amount}`;
			description.innerHTML = d.description;
			status.innerHTML = d.statusName;
			type.innerHTML = d.typeName;
			let subStr = d.submissionDate.replace("T", " | Time: ");
			submitDate.innerHTML = subStr;
			authorId.innerHTML = d.authorId;
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