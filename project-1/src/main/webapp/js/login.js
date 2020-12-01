function sleep(milliseconds){
	const date = Date.now();
	let currentDate = null;
	do{
		currentDate = Date.now();
	}while(currentDate - date < milliseconds);
}

function sendLogin()
{
    console.log("sendLogin() started.")
    const loader = document.querySelector('#loader');
	console.log(loader);
	const displayer = document.querySelector('#displayer');
	console.log(displayer);
	const link = document.querySelector('#link');
	console.log(link);
    let uName = document.getElementById("username").value;
    let pWord = document.getElementById("password").value;
    console.log("Username " + uName)
    console.log("Password " + pWord)
    let loginTemplate = {
        username: uName,
        password: pWord
    }
    //This begins AJAX workflow
    let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function(){
    	console.log('ReadyState: ' + this.readyState);
    	if(this.readyState <= 3){
    		console.log('loading');
    		//remove hide class from elements
    		link.classList.add("hide");
    		loader.classList.remove("hide");
    		displayer.classList.remove("hide");
    		//add show and loading classes to elements
    		loader.classList.add("loading");
    		displayer.classList.add("show");
    	}
        if(this.readyState === 4 && this.status === 200)
        {
            console.log("Success")
            sleep(3000);
            //add show and loading classes to elements
            loader.classList.remove("loading");
            displayer.classList.remove("show");
            //remove hide class from elements
    		loader.classList.add("hide");
    		displayer.classList.add("hide");
    		sleep(1000);
            sessionStorage.setItem('currentUser', this.responseText)
            window.location = "http://localhost:8080/project-1/home.html"
            console.log(sessionStorage.getItem('currentUser'))
        }
        if(this.readyState ===4 && this.status ===204)
        {
            console.log("Failed. Status Code: " + this.status)
			var reason = {
				code : this.status,
				issue : 'Failed to log in. Incorrect Username or Password.'
			};
			console.log(reason);
			sessionStorage.setItem('failMessage', JSON.stringify(reason));
			console.log(sessionStorage.getItem('failMessage'));
			window.location = "http://localhost:8080/project-1/badlogin.html"
        }
        console.log("Processing")
        
    }
    xhr.open("POST","http://localhost:8080/project-1/login")
    xhr.send(JSON.stringify(loginTemplate))
}