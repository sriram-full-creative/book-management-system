const signupFormData = document.querySelector('#signup-form');

const signupFormDataToJSON = (FormElement) => {
    var formData = new FormData(FormElement);
    var ConvertedJSON = {};
    for (const [key, value] of formData.entries()) {
        ConvertedJSON[key] = value;
    }
    return ConvertedJSON;
}

const signupHandler = async (event) => {
    event.preventDefault();
    console.log("redirect1");
    const result = signupFormDataToJSON(signupFormData);
    console.log(result);
    const response = await fetch("/users/signup", {
        method: 'POST',
        body: JSON.stringify(result)
    })
    const responseData = response.json();
    console.log(responseData);
    console.log("redirect2");
    if (!response.ok) {
        alert("Error");
    } else {
        window.location.href = "/books.html";
    }
}

signupFormData.addEventListener("submit", signupHandler);