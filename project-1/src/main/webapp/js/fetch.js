var getBtn = document.getElementById('get-req');
var postBtn = document.getElementById('post-req');

const sendHttpRequest = (respMethod, url, data) => {
    return fetch(url, {
        method: respMethod,
        body: JSON.stringify(data),
        headers: data ? { 'Content-Type': 'application/json' } : {}
    }).then(response => {
        if (response.status >= 300) {
            return response.json().then(errRespData => {
                const error = new Error('something went wrong!');
                error.data = errRespData;
                throw error;
            });
        } else {
            return response.json();
        }
    });
};

const getData = () => {
    sendHttpRequest('GET', 'https://reqres.in/api/users')
        .then(responseData => {
            console.log(responseData);
        });
};

const sendData = () => {
    sendHttpRequest('POST', 'https://project-1/login', {
        email: 'eve.holt@reqres.in',
        //password: 'cityslicka'
    }).then(responseData => {
        console.log(responseData);
    }).catch(err => {
        console.log(err, err.data);
    });
};

getBtn.addEventListener('click', getData);
postBtn.addEventListener('click', sendData);