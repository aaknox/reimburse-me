function goBack(){
	console.log('return home button clicked');
	window.location = "http://localhost:8080/project-1/home.html";
}

function showReciept(){
	console.log('user submitted a receipt!');
}

function roleRadioValue(){
	var ele = document.getElementsByName('type');
	let selectedType;
	for(var i = 0; i < ele.length; i++){
		if(ele[i].checked){
			selectedType = ele[i];
			break;
		}
	}
	return selectedType;
}


function sendReimbRequest(){
	console.log('reimbursement submitted to server...');
	//get form data
	let myAmount = document.getElementById('amount').value;
	let myDescription = document.getElementById('description').value;
	let myReceipt = document.getElementById('reciept-file').value;
	let myAuthorId = document.getElementById('author-id').value;
	let type = roleRadioValue();
	let typeName;
	
	if(type.value === 1){
		typeName = 'LODGING';
	}else if(type.value === 2){
		typeName = 'TRAVEL';
	}else if(type.value === 3){
		typeName = 'FOOD';
	}else{
		typeName = 'OTHER';
	}
	
	let reimbTemplate = {
			amount: myAmount,
			submissionDate: "",
			resolutionDate: "",
			description: myDescription,
			receipt: myReceipt,
			authorId: myAuthorId,
			resolverId: 0,
			reimbursementStatus: [ 2, 'PENDING' ],
			reimbursementType: [type.value, typeName]
	};
	console.log(reimbTemplate);
	//start AJAX workflow
	 let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function(){
    	console.log('ReadyState: ' + this.readyState);
    	if(this.readyState <= 3){
    		console.log('loading');
    	}
    	if(this.readyState === 4 && this.status > 200)
        {
            console.log("Failed. Status Code: " + this.status)
            var reason = {
            	code: this.status,
            	issue: 'Failed to submit a reimbursement request.'
            };
            console.log(reason);
            sessionStorage.setItem('failMessage', JSON.stringify(reason));
            console.log(sessionStorage.getItem('failMessage'));
            window.location = "http://localhost:8080/project-1/error.html"
        }
        if(this.readyState === 4 && this.status === 200)
        {
            console.log("Success")
            
            var reason = {
            	code: this.status,
            	details: 'Your reimbursement request has successfully been submitted!',
            	reimbRequest: this.responseText
            };
            console.log(reason);
            sessionStorage.setItem('successMessage', JSON.stringify(reason));
            console.log(sessionStorage.getItem('successMessage'));
            
            window.location = "http://localhost:8080/project-1/success.html";
        }
        
        console.log("Processing")
        
    }
    xhr.open("POST","http://localhost:8080/project-1/reimbursement/submit")
    xhr.send(JSON.stringify(reimbTemplate));
}


//ONLOAD OF PAGE CODE
let author = document.getElementById('author-id');
let userString = sessionStorage.getItem('currentUser');

if (userString === null) {
	window.location = "http://localhost:8080/project-1/";
} else {
	console.log(userString);
	let currentUser = JSON.parse(userString);
	console.log(currentUser);
	
	if (currentUser != null) {
		author.value = currentUser.userId;
	}
}