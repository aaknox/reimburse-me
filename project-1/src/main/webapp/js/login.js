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
            console.log("Failed")
            //alert("Failed to log in! Username or password is incorrect")
            let childDiv= document.getElementById("warningText")
            childDiv.textContent ="Failed to log in! Username or Password is incorrect"
        }
        console.log("Processing")
        
    }
    xhr.open("POST","http://localhost:8080/project-1/login")
    xhr.send(JSON.stringify(loginTemplate))
}