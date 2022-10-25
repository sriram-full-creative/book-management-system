const signupForm = document.querySelector('#signup-form');
const signUpResultContainer = document.querySelector("#result-container");

const signupFormDataToJSON = (FormElement) => {
    var formData = new FormData(FormElement);
    var ConvertedJSON = {};
    for (const [key, value] of formData.entries()) {
        ConvertedJSON[key] = value;
    }
    return ConvertedJSON;
}

function signupHandler(signUpForm) {
    const signUpFormData = signupFormDataToJSON(signupForm);
    const errorObj = signUpFormValidator(signUpFormData);
    if (isEmpty(errorObj)) {
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        var raw = JSON.stringify(signUpFormData);
        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: raw,
            redirect: 'follow'
        };
        const signUpApiUrl = `${BASE_URL.url}${ENDPOINTS.signup}`;
        fetch(signUpApiUrl, requestOptions)
            .then(response => response.json())
            .then(result => {
                console.log(result);
                if (result["ERROR_MESSAGE"] == RESPONSES.userExists["ERROR_MESSAGE"]) {
                    errorResultContainer(signUpForm, RESPONSES.userExists["ERROR_MESSAGE"]);
                } else if (result["MESSAGE"] == RESPONSES.signUpSuccess) {
                    window.location.href = ENDPOINTS.main;
                }
            })
            .catch(error => {
                errorResultContainer(signUpForm, error);
            });
    } else {
        const errorObjKeys = Object.keys(errorObj);
        processFormValidationEntries(signupForm, errorObj, errorObjKeys);
    }
}

signupForm.addEventListener("submit", (e) => {
    e.preventDefault();
    signupHandler(e.target);
});