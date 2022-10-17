// Helper Function to Set Attributes on DOM Elements
function setAttribute(element, attributes) {
    for (const key in attributes) {
        element.setAttribute(key, attributes[key]);
    }
}

let currentBookId = "";

String.prototype.toCamelCase = function () {
    return this.replace(/^([A-Z])|\s(\w)/g, function (match, p1, p2, offset) {
        if (p2) return p2.toUpperCase();
        return p1.toLowerCase();
    });
};

function processPostRequestOptions(bookObj) {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const rawJson = JSON.stringify(bookObj);
    const postRequestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: rawJson,
        redirect: 'follow'
    };
    return postRequestOptions;
}


function processPutRequestOptions(bookObj) {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const rawJson = JSON.stringify(bookObj);

    const putRequestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: rawJson,
        redirect: 'follow'
    };
    return putRequestOptions;
}

function getRequestUrlConstructor(domainName, endPoint, firstQueryParameter, secondQueryParameter) {
    return domainName + endPoint + "?" + firstQueryParameter + "&" + secondQueryParameter;
}

function postRequestUrlConstructor(domainName, endPoint) {
    return domainName + endPoint;
}

function putRequestUrlConstructor(domainName, endPoint, bookid) {
    return domainName + endPoint + "/" + bookid;
}

function deleteRequestUrlContructor(domainName, endPoint, bookId) {
    return domainName + endPoint + "/" + bookId;
}

function clearBookContainer() {
    booksContainer.innerHTML = '';
}

function runSpinner() {
    spinner.removeAttribute('hidden');
    document.documentElement.style.overflow = 'hidden';
}

function stopSpinner() {
    spinner.setAttribute('hidden', '');
    document.documentElement.style.overflow = 'scroll';
}

function addBooks(books) {
    stopSpinner();
    clearBookContainer();
    books.forEach(book => {
        const bookCard = bookCardTemplate.content.cloneNode(true).children[0];
        const img = bookCard.getElementsByClassName("cover-image")[0];
        const updateImageButton = bookCard.getElementsByClassName("cover-image-button")[0];
        const bookName = bookCard.getElementsByClassName("book-title")[0];
        const authorName = bookCard.getElementsByClassName("author-name")[0];
        const publication = bookCard.getElementsByClassName("publication-name")[0];
        const language = bookCard.getElementsByClassName("language")[0];
        const releaseYear = bookCard.getElementsByClassName("release-year")[0];
        const pages = bookCard.getElementsByClassName("pages")[0];
        const country = bookCard.getElementsByClassName("country")[0];
        const rating = bookCard.getElementsByClassName("rating")[0];
        const updateButton = bookCard.getElementsByClassName("update-book-button")[0];
        const deleteButton = bookCard.getElementsByClassName("delete-book-button")[0];
        bookCard.id = book.id;
        if (book.coverImage.length > 0) {
            processImageArea(book.coverImage, img);
        } else if (book.coverImage.length === 0) {
            setAttribute(img, {
                src: imgNotFoundSrc,
                class: "img-not-found"
            });
        }
        updateImageButton.textContent = "Update Cover Image";
        updateImageButton.dataset.updateCoverImageButtonId = book.id;
        bookName.textContent = "Title: " + book.title;
        authorName.textContent = book.author.length === 1 ? "Author: " + book.author : "Authors: " + book.author;
        publication.textContent = book.publication.length === 1 ? "Publisher: " + book.publication : "Publishers: " + book.publication;
        language.textContent = "Language: " + book.language;
        releaseYear.textContent = "Release Year: " + book.releaseYear;
        pages.textContent = "Pages: " + book.pages;
        country.textContent = "Country: " + book.country;
        rating.textContent = "Rating: " + book.rating;
        updateButton.textContent = "Update";
        updateButton.dataset.updateBookButtonId = book.id;
        deleteButton.textContent = "Delete Book";
        deleteButton.dataset.deleteBookButtonId = book.id;
        booksContainer.appendChild(bookCard);
    });
}


function toggleViewAllBooksFromIntro() {
    runSpinner();
    searchBarContainer.style.visibility = "visible";
    sortByContainer.style.visibility = "visible";
    navbar.style.visibility = "visible";
    viewAllBooksTrigger.style.visibility = "visible";
    addBookTrigger.style.visibility = "visible";
    aboutTrigger.style.visibility = "visible";
    welcomeMessage.style.display = "none";
    getBooks(apiUrl);
}

function clearFormDetails(modalForm) {
    const inputFields = modalForm.querySelectorAll("#book-title, #author-name, #publication, #language, #pages, #release-year, #country, #rating");
    inputFields.forEach(input => {
        input.value = ""
    });
}

function toggleAddBookForm() {
    const addBookFormContainer = addBookFormTemplate.content.cloneNode(true).children[0];
    const modalForm = addBookFormContainer.querySelector("#book-form");
    clearBookContainer();
    modalForm.style.display = "grid";
    modalForm.classList.add("add-book-form");
    clearFormDetails(modalForm);
    booksContainer.appendChild(modalForm);
    sortByContainer.style.visibility = "hidden";
}

function closeModel() {
    const modalForm = document.querySelector("#book-form");
    modalForm.style.display = "none";
    modalForm.classList.remove("add-book-form");
    modalForm.classList.remove("update-book-form");
    document.documentElement.style.overflow = 'scroll';
    document.body.scroll = 'yes';
    sortByContainer.style.visibility = "visible";
    currentBookId = "";
    apiUrl = getRequestUrlConstructor(domain.name, ENDPOINTS.books, sortOnProperty.default, sortDirection.default);
    getBooks(apiUrl);
}

function isEmpty(object) {
    for (const property in object) {
        return false;
    }
    return true;
}

function bookFormValidator(jsonObject) {
    const errorMessages = {};
    const hasNumber = /\d/;
    if (jsonObject["title"] == "") {
        errorMessages["TITLE_FIELD_EMPTY"] = "Title Can't be Empty";
    }
    if (jsonObject["author"] == "") {
        errorMessages["AUTHOR_NAME_EMPTY"] = "Author name Can't be Empty";
    }
    if (hasNumber.test(jsonObject["author"])) {
        errorMessages["AUTHOR_NAME_FORMAT_ERROR"] = "Author name Should container Only Alphabets";
    }
    if (jsonObject["publication"] == "") {
        errorMessages["PUBLICATION_FIELD_EMPTY"] = "Publication Field Can't be Empty";
    }

    if (jsonObject["language"] == "") {
        errorMessages["Language_FIELD_EMPTY"] = "Language Field Can't be Empty";
    }
    if (hasNumber.test(jsonObject["language"])) {
        errorMessages["LANGUAGE_FORMAT_ERROR"] = "Language Should container Only Alphabets";
    }

    if (jsonObject["pages"] == "") {
        errorMessages["PAGES_FIELD_EMPTY"] = "Pages Field Can't be Empty";
    } else if (!(jsonObject["pages"] == "")) {
        if (jsonObject["pages"] < 20) {
            errorMessages["PAGES_MIN_COUNT_ERROR"] = "Book must have a minimum of 20 Pages";
        } else if (jsonObject["pages"] < 0) {
            errorMessages["PAGES_FORMAT_ERROR"] = "Pages Can't be negative";
        }
    }

    if (jsonObject["releaseYear"] == "") {
        errorMessages["YEAR_FIELD_EMPTY"] = "Release Year Field Can't be Empty";
    } else if (!(jsonObject["releaseYear"] == "")) {
        if (jsonObject["releaseYear"] <= 0) {
            errorMessages["YEAR_FIELD_FORMAT_ERROR"] = "Release Year Field Can't be Negative";
        } else if (jsonObject["releaseYear"] > (new Date().getFullYear())) {
            errorMessages["YEAR_FIELD_FUTURE_ERROR"] = "Release Year can't be set in future";
        }
    }

    if (jsonObject["country"] == "") {
        errorMessages["COUNTRY_FIELD_EMPTY"] = "Country Field Can't be Empty";
    }
    if (hasNumber.test(jsonObject["country"])) {
        errorMessages["COUNTRY_FORMAT_ERROR"] = "Country Should container Only Alphabets";
    }

    if (jsonObject["rating"] === "" || jsonObject["rating"] === NaN || jsonObject["rating"] === 0 || jsonObject["rating"] === null) {
        jsonObject["rating"] = 0;
    }

    return errorMessages;
};

function poulateFormWithBookDetails(detailsForUpdate, modalForm) {
    const bookDetailsContainer = detailsForUpdate.getElementsByClassName("book-details-list")[0].childNodes;
    const bookDetailObj = {};
    bookDetailsContainer.forEach(data => {
        if (data.innerText) {
            const key = data.textContent.split(":").shift();
            if (key === "Rating" || key === "Release Year" || key === "Pages") {
                bookDetailObj[key.toCamelCase()] = parseInt(data.textContent.split(":").pop().trim());
            } else {
                bookDetailObj[key.toCamelCase()] = data.textContent.split(":").pop().trim();
            }
        }
    });
    console.log(bookDetailObj);
    modalForm.querySelector("#book-title").value = bookDetailObj["title"];
    modalForm.querySelector("#author-name").value = bookDetailObj["author"];
    modalForm.querySelector("#publication").value = bookDetailObj["publisher"];
    modalForm.querySelector("#language").value = bookDetailObj["language"];
    modalForm.querySelector("#pages").value = bookDetailObj["pages"];
    modalForm.querySelector("#release-year").value = bookDetailObj["releaseYear"];
    modalForm.querySelector("#country").value = bookDetailObj["country"];
    modalForm.querySelector("#rating").value = bookDetailObj["rating"];

    const updateBookButton = modalForm.querySelector("#update-book-submit");
    console.log(updateBookButton);
}


function toggleUpdateCoverImageForm(bookId) {
    console.log(document.getElementById(bookId));
    const updateCoverImageFormContainer = updateCoverImageFormTemplate.content.cloneNode(true).children[0];
    const modalForm = updateCoverImageFormContainer.querySelector("#book-form");
    clearBookContainer();
    modalForm.style.display = "grid";
    modalForm.classList.add("update-cover-image-form");
    booksContainer.appendChild(modalForm);
    console.log(modalForm);
    sortByContainer.style.visibility = "hidden";
    currentBookId = bookId;
    console.log(currentBookId);
}

function toggleUpdateBookForm(bookId) {
    const detailsForUpdate = document.getElementById(bookId);
    const updateBookFormContainer = updateBookFormTemplate.content.cloneNode(true).children[0];
    const modalForm = updateBookFormContainer.querySelector("#book-form");
    clearBookContainer();
    poulateFormWithBookDetails(detailsForUpdate, modalForm);
    modalForm.style.display = "grid";
    modalForm.classList.add("update-book-form");
    booksContainer.appendChild(modalForm);
    sortByContainer.style.visibility = "hidden";
    currentBookId = bookId;
    console.log(currentBookId);
}


function addNewBook(formData, bookForm) {
    runSpinner();
    const bookObj = {};
    formData.forEach((value, key) => {
        if (key == "author") {
            const authors = new Array();
            authors.push(value);
            bookObj[key] = authors;
        } else if (key == "publication") {
            const publication = new Array();
            publication.push(value);
            bookObj[key] = publication;
        } else if (key == "pages" || key == "releaseYear" || key == "rating") {
            bookObj[key] = parseInt(value);
        } else {
            bookObj[key] = value;
        }
    });
    const errorMessagesObj = bookFormValidator(bookObj);
    console.log(Object.keys(errorMessagesObj).length);
    if (isEmpty(errorMessagesObj)) {
        console.log("valid form");
        console.log(bookObj);
        const requestOptions = processPostRequestOptions(bookObj);
        apiUrl = postRequestUrlConstructor(domain.name, ENDPOINTS.books);
        fetch(apiUrl, requestOptions)
            .then(response => {
                stopSpinner();
                response.text()
            }).then(responseJson => {
                stopSpinner();
                console.log(responseJson);
                const resultContainer = bookForm.querySelector("#result-container");
                resultContainer.classList.add("oaerror");
                resultContainer.classList.add("success");
                resultContainer.style.display = "grid";
                resultContainer.style.justifyContent = "space-around";
                resultContainer.innerHTML = "";
                const resultMessage = document.createElement("p");
                resultMessage.classList.add("result-message-entries")
                resultMessage.style.padding = "0";
                resultMessage.style.margin = "0";
                resultMessage.style.fontFamily = `apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", sans-serif`;
                resultMessage.innerHTML = "<strong>Success : </strong>Added a new Book";
                resultContainer.appendChild(resultMessage);
            }).catch(error => {
                stopSpinner();
                console.log("addNewBook Function => ", error);
                responseJson = error;
                const resultContainer = bookForm.querySelector("#result-container");
                resultContainer.classList.add("oaerror");
                resultContainer.classList.add("danger");
                resultContainer.innerHTML = "";
                resultContainer.innerText = responseJson;
            });
    } else {
        stopSpinner();
        const errorContainer = bookForm.querySelector("#result-container");
        errorContainer.style.display = "grid";
        errorContainer.style.justifyContent = "space-around";
        errorContainer.innerHTML = "";
        errorContainer.classList.add("oaerror");
        errorContainer.classList.add("danger");
        errorObjKeys = Object.keys(errorMessagesObj);
        errorObjKeys.forEach((key) => {
            const errorMessage = document.createElement("p");
            errorMessage.classList.add("result-message-entries");
            errorMessage.style.padding = "0";
            errorMessage.style.margin = "0";
            errorMessage.style.fontFamily = `apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", sans-serif`;
            errorMessage.innerHTML = "<strong>Error : </strong>" + errorMessagesObj[key];
            errorContainer.appendChild(errorMessage);
        });
    }
}

function updateBookDetails(formData, bookForm) {
    runSpinner();
    const bookObj = {};
    formData.forEach((value, key) => {
        if (key == "author") {
            const authors = new Array();
            authors.push(value);
            bookObj[key] = authors;
        } else if (key == "publication") {
            const publication = new Array();
            publication.push(value);
            bookObj[key] = publication;
        } else if (key == "pages" || key == "releaseYear" || key == "rating") {
            if (key == "rating" && bookObj[key] == "") {
                bookObj[key] = parseInt("0");
            } else {
                bookObj[key] = parseInt(value);
            }
        } else {
            bookObj[key] = value;
        }
    });
    console.log(JSON.stringify(bookObj));
    const errorMessagesObj = bookFormValidator(bookObj);
    console.log(Object.keys(errorMessagesObj).length);
    if (isEmpty(errorMessagesObj)) {
        console.log("valid form");
        console.log(bookObj);
        const requestOptions = processPutRequestOptions(bookObj);
        apiUrl = putRequestUrlConstructor(domain.name, ENDPOINTS.books, currentBookId);
        fetch(apiUrl, requestOptions)
            .then(response => {
                stopSpinner();
                response.text()
            }).then(responseJson => {
                stopSpinner();
                console.log(responseJson);
                const resultContainer = bookForm.querySelector("#result-container");
                resultContainer.classList.add("success");
                resultContainer.classList.add("oaerror");
                resultContainer.style.display = "grid";
                resultContainer.style.justifyContent = "space-around";
                resultContainer.innerHTML = "";
                const resultMessage = document.createElement("p");
                resultMessage.classList.add("result-message-entries")
                resultMessage.style.padding = "0";
                resultMessage.style.margin = "0";
                resultMessage.style.fontFamily = `apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", sans-serif`;
                resultMessage.innerHTML = "<strong>Success : </strong>Updated the Book Details";
                resultContainer.appendChild(resultMessage);
            }).catch(error => {
                stopSpinner();
                console.log("updateBookDetails Function => ", error);
                responseJson = error;
                const resultContainer = bookForm.querySelector("#result-container");
                resultContainer.classList.add("oaerror");
                resultContainer.classList.add("danger");
                resultContainer.innerHTML = "";
                resultContainer.innerText = responseJson;
            });
    } else {
        stopSpinner();
        const errorContainer = bookForm.querySelector("#result-container");
        errorContainer.style.display = "grid";
        errorContainer.style.justifyContent = "space-around";
        errorContainer.innerHTML = "";
        errorContainer.classList.add("danger");
        errorContainer.classList.add("oaerror");
        errorObjKeys = Object.keys(errorMessagesObj);
        errorObjKeys.forEach((key) => {
            const errorMessage = document.createElement("p");
            errorMessage.classList.add("result-message-entries")
            errorMessage.style.padding = "0";
            errorMessage.style.margin = "0";
            errorMessage.style.fontFamily = `apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", sans-serif`;
            errorMessage.innerHTML = "<strong>Error : </strong>" + errorMessagesObj[key];
            errorContainer.appendChild(errorMessage);
        });
    }
}

function deleteBook(bookId) {
    runSpinner();
    console.log(deleteRequestUrlContructor(domain.name, ENDPOINTS.books, bookId));
    fetch(deleteRequestUrlContructor(domain.name, ENDPOINTS.books, bookId), {
        method: "DELETE",
        headers: {
            'Content-type': 'application/json'
        }
    }).then(res => {
        stopSpinner();
        res.json()
    }).then(data => {
        stopSpinner();
        console.log(data);
        apiUrl = getRequestUrlConstructor(domain.name, ENDPOINTS.books, sortOnProperty.default, sortDirection.default);
        getBooks(apiUrl);
    }).catch(error => {
        stopSpinner();
        console.log(error);
        apiUrl = getRequestUrlConstructor(domain.name, ENDPOINTS.books, sortOnProperty.default, sortDirection.default);
        getBooks(apiUrl);
    });
}

function updateCoverImage(ImagefileInput, bookForm) {
    runSpinner();

    const myHeaders = new Headers();
    myHeaders.append("Cache-Control", "no-store");

    const formdata = new FormData();
    formdata.append("coverImage", ImagefileInput.files[0]);

    const requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: formdata,
        redirect: 'follow'
    };

    apiUrl = putRequestUrlConstructor(domain.name, ENDPOINTS.images, currentBookId);

    fetch(apiUrl, requestOptions)
        .then(response => {
            stopSpinner();
            response.text()
        }).then(responseJson => {
            stopSpinner();
            console.log(responseJson);
            const resultContainer = bookForm.querySelector("#result-container");
            resultContainer.classList.add("oaerror");
            resultContainer.classList.add("success");
            resultContainer.style.display = "grid";
            resultContainer.style.justifyContent = "space-around";
            resultContainer.innerHTML = "";
            const resultMessage = document.createElement("p");
            resultMessage.classList.add("result-message-entries")
            resultMessage.style.padding = "0";
            resultMessage.style.margin = "0";
            resultMessage.style.fontFamily = `apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", sans-serif`;
            resultMessage.innerHTML = "<strong>Success : </strong>Updated the Cover Image";
            resultContainer.appendChild(resultMessage);
        }).catch(error => {
            stopSpinner();
            console.log("updateBookDetails Function => ", error);
            responseJson = error;
            const resultContainer = bookForm.querySelector("#result-container");
            resultContainer.classList.add("oaerror");
            resultContainer.classList.add("danger");
            resultContainer.innerHTML = "";
            resultContainer.innerText = responseJson;
        });

}

function processBooks() {
    if (event.target.classList == 'cover-image-button') {
        toggleUpdateCoverImageForm(event.target.dataset.updateCoverImageButtonId);
    } else if (event.target.classList == 'update-book-button') {
        toggleUpdateBookForm(event.target.dataset.updateBookButtonId);
    } else if (event.target.classList == 'delete-book-button') {
        deleteBook(event.target.dataset.deleteBookButtonId);
    }
}

function searchBooks() {
    const value = event.target.value.toLowerCase()
    books.forEach(book => {
        const hiddenElement = document.getElementById(book.id);
        let authors = book.author.toString();
        const isVisible = !(book.title.toLowerCase().includes(value) || authors.toLowerCase().includes(value));
        hiddenElement.classList.toggle("hide", isVisible);
    });
}

function processOption(selectedOption) {
    switch (selectedOption) {
        case "time-descending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.default,
                sortDirection.default
            )
        case "time-ascending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.default,
                sortDirection.ascending
            )
        case "author-descending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.author,
                sortDirection.default
            )
        case "author-ascending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.author,
                sortDirection.ascending
            )
        case "publication-descending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.publication,
                sortDirection.default
            )
        case "publication-ascending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.publication,
                sortDirection.ascending
            )
        case "title-descending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.title,
                sortDirection.default
            )
        case "title-ascending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.title,
                sortDirection.ascending
            )
        case "pages-descending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.pages,
                sortDirection.default
            )
        case "pages-ascending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.pages,
                sortDirection.ascending
            )
        case "release-year-descending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.releaseYear,
                sortDirection.default
            )
        case "release-year-ascending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.releaseYear,
                sortDirection.ascending
            )
        case "rating-descending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.rating,
                sortDirection.default
            )
        case "rating-ascending":
            return getRequestUrlConstructor(
                domain.name,
                ENDPOINTS.books,
                sortOnProperty.rating,
                sortDirection.ascending
            )
        default:
            break;
    }
}

function sortBooks() {
    spinner.removeAttribute('hidden');
    const selectedOption = sortByOptions.value;
    apiUrl = processOption(selectedOption);
    console.log(apiUrl);
    getBooks(apiUrl);
}


function logout() {
    console.log('logout');
    window.location.href = "/users/logout";
}