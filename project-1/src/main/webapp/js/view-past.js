function goBack(){
	console.log('return home button clicked');
	window.location = "http://localhost:8080/project-1/home.html";
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
		author.innerText = "User ID: " + currentUser.userId;
	}
}