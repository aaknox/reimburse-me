function sleep(milliseconds){
	const date = Date.now();
	let currentDate = null;
	do{
		currentDate = Date.now();
	}while(currentDate - date < milliseconds);
}



var modal = document.getElementById('helpModal');
var button = document.getElementById('helpBtn');
var span = document.getElementsByClassName("close")[0];


button.onclick = function(){
	modal.style.display = "block";
	modal.style.animation = "fadeIn 0.25s ease-in 0s 1 alternate forwards running";
};

span.onclick = function(){
	modal.style.display = "none";
};

window.onclick = function(){
	if(event.target == modal){
		modal.style.display = "none";
	}
};