const loginFormData = document.querySelector('#login-form');

const loginFormDataToJSON = (FormElement) => {
    var formData = new FormData(FormElement);
    var ConvertedJSON = {};
    for (const [key, value] of formData.entries()) {
        ConvertedJSON[key] = value;
    }
    return ConvertedJSON
}

const loginHandler = async (event) => {
    event.preventDefault();
    const result = loginFormDataToJSON(loginFormData);
    const response = await fetch("/users/login", {
        method: 'POST',
        body: JSON.stringify(result)
    })
    const responseData = response.json();
    console.log(responseData);
    if (!response.ok) {
        alert("Invalid Email or Password");
    } else {
        console.log("redirect");
        window.location.href = "/books/all";
    }
}

loginFormData.addEventListener("submit", loginHandler);