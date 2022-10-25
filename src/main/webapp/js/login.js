const loginForm = document.querySelector('#login-form');
const loginResultContainer = document.querySelector("#result-container");


const loginFormDataToJSON = (FormElement) => {
    var formData = new FormData(FormElement);
    var ConvertedJSON = {};
    for (const [key, value] of formData.entries()) {
        ConvertedJSON[key] = value;
    }
    return ConvertedJSON
}


function loginHandler(loginForm) {
    const loginFormData = loginFormDataToJSON(loginForm);
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const raw = JSON.stringify(loginFormData);
    const requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };
    const loginApiUrl = `${BASE_URL.url}${ENDPOINTS.login}`;
    fetch(loginApiUrl, requestOptions)
        .then(response => response.json())
        .then(result => {
            console.log(result);
            if (result["ERROR_MESSAGE"] == RESPONSES.userNotExists["ERROR_MESSAGE"]) {
                errorResultContainer(loginForm, "User does not exists. Please Signup");
            } else if (result["ERROR_MESSAGE"] == RESPONSES.incorrectPassword["ERROR_MESSAGE"] || result["ERROR_MESSAGE"] == RESPONSES.invalidPassword["ERROR_MESSAGE"]) {
                errorResultContainer(loginForm, "Incorrect Password");
            } else if (result["MESSAGE"] == RESPONSES.loginSucess) {
                window.location.href = ENDPOINTS.main;
            }
        })
        .catch(error => {
            errorResultContainer(loginForm, error);
        });
}

loginForm.addEventListener("submit", (e) => {
    e.preventDefault();
    loginHandler(e.target);
});