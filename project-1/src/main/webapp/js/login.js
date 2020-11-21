console.log('index.html loaded...');

var postBtn = document.getElementById('post-req');
var givenUserName = document.getElementById('username').value;
var givenPassword = document.getElementById('password').value;

const sendHttpRequest = (method, url, data) => {
    //implement promise object
    const promise = new Promise((resolve, reject) => {
        //step 1: create request
        var xhr = new XMLHttpRequest();
        //step 2: prepare to send request by 
        //FIRST opening request for client-side modification
        xhr.open(method, url);

        xhr.responseType = 'json';

        //if data is present (truthy)
        if (data) {
            xhr.setRequestHeader('Content-Type', 'application/json')
        }
        //step 3: set a event listener to...access our data from resource
        //aka get JSON data from API
        xhr.onload = function() {
            if (xhr.status >= 300) {
                reject(xhr.response);
            } else {
                resolve(xhr.response);
            }
        };

        //step 3B: handle errors
        xhr.onerror = function() {
            reject("Azhya, something went wrong!");
        };
        //step 4: send the now-configured request to its destination
        //you can append data to request by passing it inside send()
        //note JavaScript data must be converted into JSON 
        //to transfer data in request
        xhr.send(JSON.stringify(data));

    });
    return promise;
};

const sendData = () => {
    console.log('starting to send request to server');
    sendHttpRequest('POST', 'http://localhost:8080/project-1/reimburse-me/login', {
        username: givenUserName,
        password: givenPassword
    }).then(responseData => {
        console.log('successful login')
        console.log(responseData);
    }).catch(err => {
        console.log('bad login')
        console.log(err);
    });
};

postBtn.addEventListener('submit', sendData);