const login = () => {
    console.log('starting login function...')
    var t_username = document.getElementById('username').value;
    var t_password = document.getElementById('password').value;
    console.log(`User logging in with username as ${t_username} and password as ${t_password}`);
    const data = {
        username: t_username,
        password: t_password
    };

    const xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            window.localStorage.removeItem('currentUser');
            window.localStorage.setItem('currentUser', t_username);
        } else if (this.status >= 400) {
            console.log('ERROR!!');
        }
    });

    xhr.open("POST", "https://localhost:8080/reimburse-me/login");
    xhr.setRequestHeader("content-type", "application/json");
    xhr.setRequestHeader("\"username\"", `'${data.username}'`);
    xhr.setRequestHeader("\"password\"", `'${data.password}'`);

    xhr.send(data);
    console.log('ending login function...')
};

document.getElementById('post-req').addEventListener('submit', login);