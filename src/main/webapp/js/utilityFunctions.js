
/**
 * Description - Helper Function to Set Attributes on DOM Elements.
 * 
 * @param {any} element
 * @param {any} attributes
 */
function setAttribute(element, attributes) {
    for (const key in attributes) {
        element.setAttribute(key, attributes[key]);
    }
}


/**
 * Description - Helper Function to convert normal Strings into a camelCase Strings
 * 
 * @returns {string}
 */
String.prototype.toCamelCase = function () {
    return this.replace(/^([A-Z])|\s(\w)/g, function (match, p1, p2, offset) {
        if (p2) return p2.toUpperCase();
        return p1.toLowerCase();
    });
};


/**
 * Description - Searches for the details in the books when typed in the input of the search bar.
 */
function searchBooks() {
    shouldLoad = false;
    uncheckAllCheckboxes();
    const value = event.target.value.toLowerCase();
    Array.from(allCachedBooks.values()).forEach(book => {
        const hiddenElement = document.getElementById(book.id);
        let authors = book.author.toString();
        const isVisible = !(book.title.toLowerCase().includes(value) || authors.toLowerCase().includes(value));
        hiddenElement.classList.toggle("hide", isVisible);
    });
    if (!value) {
        shouldLoad = true;
    }
}


/**
 * Description - Helper Function to Process POST Request Options.
 * 
 * @param {object} bookObj
 * @returns {object} postRequestOptions
 */
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


/**
 * Description - Helper Function to Process PUT Request Options.
 * 
 * @param {object} bookObj
 * @returns {object} putRequestOptions
 */
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

/**
 * Description - Helper Function to Process Image Update Request Options.
 * 
 * @param {object} ImagefileInput
 * @returns {object} imageUpdateRequestOptions
 */
function processImageUpdateRequestOptions(ImagefileInput) {
    const myHeaders = new Headers();
    myHeaders.append("Cache-Control", "no-store");

    const formdata = new FormData();
    formdata.append("coverImage", ImagefileInput.files[0]);

    const imageUpdateRequestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: formdata,
        redirect: 'follow'
    };
    return imageUpdateRequestOptions;
}

/**
 * Description - Helper Function to Process Delete Selected Books Request Options.
 * @param {object} selectedBooks
 * @returns {object} deleteSelectedBooksRequestOptions
 */
function processDeleteSelectedBooksRequestOptions(selectedBooks) {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const rawJsonBody = JSON.stringify({
        books: Array.from(selectedBooks)
    })
    console.log(rawJsonBody);
    const deleteSelectedBooksRequestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: rawJsonBody,
        redirect: 'follow'
    };
    return deleteSelectedBooksRequestOptions;
}

/**
 * Description - Helper Function to Construct a URL for normal GET request.
 * 
 * @param {string} baseURL
 * @param {string} endPoint
 * @param {string} firstQueryParameter
 * @param {string} secondQueryParameter
 * @returns {string} getRequestURL
 */
function getRequestUrlConstructor(baseURL, endPoint, firstQueryParameter, secondQueryParameter) {
    return `${baseURL}${endPoint}?${firstQueryParameter}&${secondQueryParameter}`;
    return baseURL + endPoint + "?" + firstQueryParameter + "&" + secondQueryParameter;
}

/**
 * Description - Helper Function to Construct a URL for GET request when using Pagination.
 * 
 * @param {string} baseURL
 * @param {string} endPoint
 * @param {string} firstQueryParameter
 * @param {string} secondQueryParameter
 * @param {string} cursor
 * @returns {string} getRequestURL
 */
function paginationRequestUrlContructor(baseURL, endPoint, firstQueryParameter, secondQueryParameter, cursor) {
    return `${baseURL}${endPoint}?${firstQueryParameter}&${secondQueryParameter}&cursor=${cursor}`;
    // return baseURL + endPoint + "?" + firstQueryParameter + "&" + secondQueryParameter + "&" + "cursor=" + cursor;
}

/**
 * Description - Helper Function to Construct a URL for POST request.
 * 
 * @param {string} baseURL
 * @param {string} endPoint
 * @returns {string} postRequestURL
 */
function postRequestUrlConstructor(baseURL, endPoint) {
    return `${baseURL}${endPoint}`;
    // return baseURL + endPoint;
}

/**
 * Description - Helper Function to Construct a URL for PUT request.
 * 
 * @param {string} baseURL
 * @param {string} endPoint
 * @param {string} bookID
 * @returns {string} putRequestURL
 */
function putRequestUrlConstructor(baseURL, endPoint, bookID) {
    return `${baseURL}${endPoint}/${bookID}`
    // return baseURL + endPoint + "/" + bookID;
}

/**
 * Description - Helper Function to Construct a URL for DELETE request.
 * 
 * @param {string} baseURL
 * @param {string} endPoint
 * @param {string} bookID
 * @returns {string} deleteRequestURL
 */
function deleteRequestUrlContructor(baseURL, endPoint, bookID) {
    return `${baseURL}${endPoint}/${bookID}`;
    // return baseURL + endPoint + "/" + bookID;
}

/**
 * Description - Clears the book container in the HTML Document.
 */
function clearBookContainer() {
    booksContainer.innerHTML = '';
}

/**
 * Description - Runs the Loading Spinner.
 */
function runSpinner() {
    spinner.removeAttribute('hidden');
    document.documentElement.style.overflow = 'hidden';
}

/**
 * Description - Stops the Loading Spinner.
 */
function stopSpinner() {
    spinner.setAttribute('hidden', '');
    document.documentElement.style.overflow = 'scroll';
}



/**
 * Description - Takes the user to books page from the welcome page.
 */
function toggleViewAllBooksFromIntro() {
    runSpinner();
    searchBarContainer.style.visibility = "visible";
    sortByContainer.style.visibility = "visible";
    navbar.style.visibility = "visible";
    viewAllBooksTrigger.style.visibility = "visible";
    addBookTrigger.style.visibility = "visible";
    aboutTrigger.style.visibility = "visible";
    deleteSelectedButton.style.visibility = "visible";
    welcomeMessage.style.display = "none";
    getBooks(apiUrl);
}

/**
 * Description - Clears the form Details.
 * 
 * @param {HTMLFormElement} modalForm
 */
function clearFormDetails(modalForm) {
    const inputFields = modalForm.querySelectorAll("#book-title, #author-name, #publication, #language, #pages, #release-year, #country, #rating");
    inputFields.forEach(input => {
        input.value = ""
    });
}

/**
 * Description - Opens the form to add a new book.
 */
function toggleAddBookForm() {
    shouldLoad = false;
    const addBookFormContainer = addBookFormTemplate.content.cloneNode(true).children[0];
    const modalForm = addBookFormContainer.querySelector("#book-form");
    clearBookContainer();
    modalForm.style.display = "grid";
    modalForm.classList.add("add-book-form");
    clearFormDetails(modalForm);
    booksContainer.appendChild(modalForm);
    sortByContainer.style.visibility = "hidden";
    deleteSelectedButton.style.visibility = "hidden";
}

/**
 * Description - Opens the form to update the details of a book.
 */
function toggleUpdateBookForm(bookId) {
    shouldLoad = false;
    deleteSelectedBooks.disabled = true;
    const detailsForUpdate = document.getElementById(bookId);
    const updateBookFormContainer = updateBookFormTemplate.content.cloneNode(true).children[0];
    const modalForm = updateBookFormContainer.querySelector("#book-form");
    clearBookContainer();
    poulateFormWithBookDetails(detailsForUpdate, modalForm);
    modalForm.style.display = "grid";
    modalForm.classList.add("update-book-form");
    booksContainer.appendChild(modalForm);
    sortByContainer.style.visibility = "hidden";
    deleteSelectedButton.style.visibility = "hidden";
    currentBookId = bookId;
    console.log(currentBookId);
}

/**
 * Description - Opens the form to update cover image for a book.
 */
function toggleUpdateCoverImageForm(bookId) {
    shouldLoad = false;
    deleteSelectedBooks.disabled = true;
    console.log(document.getElementById(bookId));
    const updateCoverImageFormContainer = updateCoverImageFormTemplate.content.cloneNode(true).children[0];
    const modalForm = updateCoverImageFormContainer.querySelector("#book-form");
    clearBookContainer();
    modalForm.style.display = "grid";
    modalForm.classList.add("update-cover-image-form");
    booksContainer.appendChild(modalForm);
    console.log(modalForm);
    sortByContainer.style.visibility = "hidden";
    deleteSelectedButton.style.visibility = "hidden";
    currentBookId = bookId;
    console.log(currentBookId);
}

/**
 * Description - Closes the form and renders the page with cached books if no operation like update or add is performed. If any one of them is performed page will be rendered to a normal state with updated list of books.
 */
function closeFormModal() {
    uncheckAllCheckboxes();
    const modalForm = document.querySelector("#book-form");
    modalForm.style.display = "none";
    modalForm.classList.remove("add-book-form");
    modalForm.classList.remove("update-book-form");
    document.documentElement.style.overflow = 'scroll';
    document.body.scroll = 'yes';
    sortByContainer.style.visibility = "visible";
    deleteSelectedButton.style.visibility = "visible";
    currentBookId = "";
    if (isbookAdded || isbookUpdated || isCoverImageUpdated) {
        sortByOptions.selectedIndex = "0";
        currentProperty = "";
        currentDirection = "";
        apiUrl = "";
        allCachedBooks.clear();
        getBooks(defaultApiUrl);
        isbookAdded = false;
        isbookUpdated = false;
        isCoverImageUpdated = false;
    } else {
        addBooks(Array.from(allCachedBooks.values()));
    }

}


/**
 * Description - 
 * Checks if an object is empty or not.
 * 
 * @param {object} object
 * @returns {boolean} boolean
 */
function isEmpty(object) {
    for (const property in object) {
        return false;
    }
    return true;
}


/**
 * Description - Fills the input fiels with the values from the object.
 * 
 * @param {HTMLElement} modalForm
 * @param {Object} bookDetailObj
 */
function fillModalFormWithBookDetails(modalForm, bookDetailObj) {
    modalForm.querySelector("#book-title").value = bookDetailObj["title"];
    modalForm.querySelector("#author-name").value = bookDetailObj["author"];
    modalForm.querySelector("#publication").value = bookDetailObj["publisher"];
    modalForm.querySelector("#language").value = bookDetailObj["language"];
    modalForm.querySelector("#pages").value = bookDetailObj["pages"];
    modalForm.querySelector("#release-year").value = bookDetailObj["releaseYear"];
    modalForm.querySelector("#country").value = bookDetailObj["country"];
    modalForm.querySelector("#rating").value = bookDetailObj["rating"];

}


/**
 * Description - Populates the form element present in the HTML Collection with the values of the properties of the object passed.
 * 
 * @param {HTMLAllCollection} detailsForUpdate
 * @param {HTMLElement} modalForm
 */
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
    fillModalFormWithBookDetails(modalForm, bookDetailObj);
    const updateBookButton = modalForm.querySelector("#update-book-submit");
    console.log(updateBookButton);
}


/**
 * Description - Generates a formData object from the book object.
 * 
 * @param {object} formData
 * @param {object} bookObj
 */
function formDataToBookObj(formData, bookObj) {
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
}

/**
 * Description - Adds the errors if any found when performing fron-end validation to the result container.
 * 
 * @param {HTMLElement} bookForm
 * @param {object} errorMessagesObj
 * @param {object} errorObjKeys
 */
function processFormValidationEntries(bookForm, errorMessagesObj, errorObjKeys) {
    const errorContainer = bookForm.querySelector("#result-container");
    errorContainer.style.display = "grid";
    errorContainer.style.justifyContent = "space-around";
    errorContainer.innerHTML = "";
    errorContainer.classList.add("oaerror");
    errorContainer.classList.add("danger");
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

/**
 * Description - Adds the Error Message to the result container if the fetch request fails.
 * 
 * @param {HTMLElement} bookForm
 * @param {string} resultContainerMessage
 */
function errorResultContainer(bookForm, resultContainerMessage) {
    const resultContainer = bookForm.querySelector("#result-container");
    resultContainer.classList.add("oaerror");
    resultContainer.classList.add("danger");
    resultContainer.innerText = `${resultContainerMessage}`;
}

/**
 * Description - Adds the successful response to the result container when the fetch request is completed successfully. 
 * 
 * @param {HTMLElement} bookForm
 * @param {string} resultContainerMessage
 */
function positiveResultContainer(bookForm, resultContainerMessage) {
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
    resultMessage.innerHTML = `${resultContainerMessage}`;
    resultContainer.appendChild(resultMessage);
}


/**
 * Description - Adds a new book to the datastore and renders the page again along with the new book if successfull.
 * 
 * @param {object} formData
 * @param {HTMLElement} bookForm
 */
function addNewBook(formData, bookForm) {
    runSpinner();
    const bookObj = {};
    formDataToBookObj(formData, bookObj);
    console.log(JSON.stringify(bookObj));
    const errorMessagesObj = bookFormValidator(bookObj);
    console.log(Object.keys(errorMessagesObj).length);
    if (isEmpty(errorMessagesObj)) {
        console.log("valid form");
        console.log(bookObj);
        const requestOptions = processPostRequestOptions(bookObj);
        apiUrl = postRequestUrlConstructor(BASE_URL.url, ENDPOINTS.books);
        fetch(apiUrl, requestOptions)
            .then(response => response.json())
            .then(responseJson => {
                stopSpinner();
                isbookAdded = true;
                allCachedBooks.set(responseJson.id, responseJson);
                const resultContainerMessage = "<strong>Success : </strong>Added a new Book";
                positiveResultContainer(bookForm, resultContainerMessage);
            }).catch(error => {
                stopSpinner();
                console.log("addNewBook Function => ", error);
                errorResultContainer(bookForm, error);
            });
    } else {
        stopSpinner();
        const errorObjKeys = Object.keys(errorMessagesObj);
        processFormValidationEntries(bookForm, errorMessagesObj, errorObjKeys);
    }
}


/**
 * Description - Updates the details of the book to the datastore and renders the page again along with the updated details of the book if successfull.
 * 
 * @param {object} formData
 * @param {HTMLElement} bookForm
 */
function updateBookDetails(formData, bookForm) {
    runSpinner();
    const bookObj = {};
    formDataToBookObj(formData, bookObj);
    console.log(JSON.stringify(bookObj));
    const errorMessagesObj = bookFormValidator(bookObj);
    console.log(Object.keys(errorMessagesObj).length);
    if (isEmpty(errorMessagesObj)) {
        console.log("valid form");
        console.log(bookObj);
        const requestOptions = processPutRequestOptions(bookObj);
        apiUrl = putRequestUrlConstructor(BASE_URL.url, ENDPOINTS.books, currentBookId);
        fetch(apiUrl, requestOptions)
            .then(response => response.json())
            .then(responseJson => {
                stopSpinner();
                isbookUpdated = true;
                allCachedBooks.set(currentBookId, responseJson);
                const resultContainerMessage = "<strong>Success : </strong>Updated the Book Details";
                positiveResultContainer(bookForm, resultContainerMessage);
            }).catch(error => {
                stopSpinner();
                console.log("updateBookDetails Function => ", error);
                errorResultContainer(bookForm, error);
            });
    } else {
        stopSpinner();
        const errorObjKeys = Object.keys(errorMessagesObj);
        processFormValidationEntries(bookForm, errorMessagesObj, errorObjKeys);
    }
}

/**
 * Description - Updates the cover image of book and renders the page again along with the updated details of the book if successfull.
 * 
 * @param {object} formData
 * @param {HTMLElement} bookForm
 */
function updateCoverImage(ImagefileInput, bookForm) {
    runSpinner();
    const requestOptions = processImageUpdateRequestOptions(ImagefileInput);
    apiUrl = putRequestUrlConstructor(BASE_URL.url, ENDPOINTS.images, currentBookId);

    fetch(apiUrl, requestOptions)
        .then(response => response.json())
        .then(responseAsJson => {
            stopSpinner();
            isCoverImageUpdated = true;
            console.log(responseAsJson);
            const resultContainerMessage = "<strong>Success : </strong>Updated the Cover Image";
            positiveResultContainer(bookForm, resultContainerMessage);
        }).catch(error => {
            stopSpinner();
            console.log("updateCoverImage Function => ", error);
            errorResultContainer(bookForm, error);
        });
}

/**
 * Description - Deletes the book from the datastore and renders the page again with the remaining elements from the cached books if successful.
 * 
 * @param {string} bookId
 */
function deleteBook(bookId) {
    runSpinner();
    uncheckAllCheckboxes();
    console.log(deleteRequestUrlContructor(BASE_URL.url, ENDPOINTS.books, bookId));
    fetch(deleteRequestUrlContructor(BASE_URL.url, ENDPOINTS.books, bookId), {
        method: "DELETE",
        headers: {
            'Content-type': 'application/json'
        }
    }).then(response => response.json())
        .then(responseAsJson => {
            stopSpinner();
            console.log(responseAsJson);
            allCachedBooks.delete(bookId);
            clearBookContainer();
            addBooks(Array.from(allCachedBooks.values()));
        }).catch(error => {
            stopSpinner();
            console.log(error);
            apiUrl = getRequestUrlConstructor(BASE_URL.url, ENDPOINTS.books, QUERY_PARAMETERS.sortOnProperty.default, QUERY_PARAMETERS.sortDirection.default);
            getBooks(apiUrl);
        });
}
function disableDeleteSelectedButton() {
    deleteSelectedButton.disabled = true;
}

function enableDeleteSelectedButton() {
    deleteSelectedButton.disabled = false;
}


/**
 * Description - A function to map the event with the appropriate function to process the event.
 */
function processBooksOperation() {
    if (event.target.classList == 'cover-image-button') {
        toggleUpdateCoverImageForm(event.target.dataset.updateCoverImageButtonId);
    } else if (event.target.classList == 'update-book-button') {
        toggleUpdateBookForm(event.target.dataset.updateBookButtonId);
    } else if (event.target.classList == 'delete-book-button') {
        deleteBook(event.target.dataset.deleteBookButtonId);
    } else if (event.target.classList.contains('book-card-check-box')) {
        if (event.target.checked) {
            selectedBooks.add(event.target.dataset.checkBoxId);
        } else if (!event.target.checked) {
            selectedBooks.delete(event.target.dataset.checkBoxId);
        }
        if (selectedBooks.size != 0) {
            enableDeleteSelectedButton();
        } else {
            disableDeleteSelectedButton();
        }
    }
}


/**
 * Description- A function to select the appropriate URL depending on the selected sort option.
 * 
 * @param {string} selectedOption
 * @returns {string} urlForSelectedOption
 */
function processSortOption(selectedOption) {
    switch (selectedOption) {
        case "time-descending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.default;
            currentDirection = QUERY_PARAMETERS.sortDirection.default;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.default,
                QUERY_PARAMETERS.sortDirection.default,
                nextCursor.default
            )
        case "time-ascending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.default;
            currentDirection = QUERY_PARAMETERS.sortDirection.ascending;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.default,
                QUERY_PARAMETERS.sortDirection.ascending,
                nextCursor.default
            )
        case "author-descending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.author;
            currentDirection = QUERY_PARAMETERS.sortDirection.default;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.author,
                QUERY_PARAMETERS.sortDirection.default,
                nextCursor.default
            )
        case "author-ascending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.author;
            currentDirection = QUERY_PARAMETERS.sortDirection.ascending;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.author,
                QUERY_PARAMETERS.sortDirection.ascending,
                nextCursor.default
            )
        case "publication-descending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.publication;
            currentDirection = QUERY_PARAMETERS.sortDirection.default;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.publication,
                QUERY_PARAMETERS.sortDirection.default,
                nextCursor.default
            )
        case "publication-ascending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.publication;
            currentDirection = QUERY_PARAMETERS.sortDirection.ascending;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.publication,
                QUERY_PARAMETERS.sortDirection.ascending,
                nextCursor.default
            )
        case "title-descending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.title;
            currentDirection = QUERY_PARAMETERS.sortDirection.default;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.title,
                QUERY_PARAMETERS.sortDirection.default,
                nextCursor.default
            )
        case "title-ascending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.title;
            currentDirection = QUERY_PARAMETERS.sortDirection.ascending;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.title,
                QUERY_PARAMETERS.sortDirection.ascending,
                nextCursor.default
            )
        case "pages-descending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.pages;
            currentDirection = QUERY_PARAMETERS.sortDirection.default;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.pages,
                QUERY_PARAMETERS.sortDirection.default,
                nextCursor.default
            )
        case "pages-ascending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.pages;
            currentDirection = QUERY_PARAMETERS.sortDirection.ascending;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.pages,
                QUERY_PARAMETERS.sortDirection.ascending,
                nextCursor.default
            )
        case "release-year-descending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.releaseYear;
            currentDirection = QUERY_PARAMETERS.sortDirection.default;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.releaseYear,
                QUERY_PARAMETERS.sortDirection.default,
                nextCursor.default
            )
        case "release-year-ascending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.releaseYear;
            currentDirection = QUERY_PARAMETERS.sortDirection.ascending;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.releaseYear,
                QUERY_PARAMETERS.sortDirection.ascending,
                nextCursor.default
            )
        case "rating-descending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.rating;
            currentDirection = QUERY_PARAMETERS.sortDirection.default;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.rating,
                QUERY_PARAMETERS.sortDirection.default,
                nextCursor.default
            )
        case "rating-ascending":
            currentProperty = QUERY_PARAMETERS.sortOnProperty.rating;
            currentDirection = QUERY_PARAMETERS.sortDirection.ascending;
            return paginationRequestUrlContructor(
                BASE_URL.url,
                ENDPOINTS.books,
                QUERY_PARAMETERS.sortOnProperty.rating,
                QUERY_PARAMETERS.sortDirection.ascending,
                nextCursor.default
            )
        default:
            break;
    }
}


/**
 * Description - Validates the form details when the form data object is passed as a parameter.
 * 
 * @param {object} jsonObject
 * @returns {object} errorMessages
 */
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


/**
 * Description - A Function to sort books. It will process the selected sort option and will sort the books based on the order.
 */
function sortBooks() {
    spinner.removeAttribute('hidden');
    uncheckAllCheckboxes();
    const selectedOption = sortByOptions.value;
    apiUrl = processSortOption(selectedOption);
    allCachedBooks.clear();
    console.log(apiUrl);
    clearBookContainer();
    getBooks(apiUrl);
}

/**
 * Description - Unchecks all the checkboxes
 */
function uncheckAllCheckboxes() {
    const checkBoxes = document.querySelectorAll(".book-card-check-box");
    checkBoxes.forEach((checkBox) => {
        checkBox.checked = false;
    });
    deleteSelectedButton.disabled = true;
    selectedBooks.clear();
}


/**
 * Description - Deletes all the books that is present at the moment when the function is called by sending a request to the server which will be handled by task queues.
 */
function deleteAllBooks() {
    console.log("delete all books triggered");
    uncheckAllCheckboxes()
    var requestOptions = {
        method: 'POST',
        redirect: 'follow'
    };
    const deleteAllBooksUrl = `${BASE_URL.url}${ENDPOINTS.taskQueue}?${QUERY_PARAMETERS.deleteBooks.deleteAll}`;
    fetch(deleteAllBooksUrl, requestOptions)
        .then(response => response.json())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}

/**
 * Description - Deletes all the books that is present at the moment when the function is called by sending a request to the server which will be handled by task queues.
 */
function deleteSelectedBooks(selectedBooks) {
    console.log("delete selected books triggered");
    const deleteSelectedBooksRequestOptions = processDeleteSelectedBooksRequestOptions(selectedBooks);
    const deleteSelectedBooksUrl = `${BASE_URL.url}${ENDPOINTS.taskQueue}?${QUERY_PARAMETERS.deleteBooks.deleteSelected}`;
    fetch(deleteSelectedBooksUrl, deleteSelectedBooksRequestOptions)
        .then(response => response.json())
        .then(result => {
            console.log(result);
            uncheckAllCheckboxes();
        })
        .catch(error => {
            console.log('error', error);
            uncheckAllCheckboxes();
        });
}

/**
 * Description - Redirects the user to the login page once the server invalidates the session when this function is called.
 */
function logout() {
    console.log('logout');
    uncheckAllCheckboxes();
    window.location.href = ENDPOINTS.logout;
}


/**
 * Description - Renders each book as a card and places it in the books container when an array of books is passed as an argument.
 * 
 * @param {Array} books
 */
function addBooks(books) {
    shouldLoad = true;
    stopSpinner();
    books.forEach(book => {
        allCachedBooks.set(book.id, book);
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
        const checkBox = bookCard.getElementsByClassName("book-card-check-box")[0];
        bookCard.id = book.id;
        if (book.coverImage.length > 0) {
            processImageArea(book.coverImage, img);
        } else if (book.coverImage.length === 0) {
            setAttribute(img, {
                src: imgNotFoundSrc,
                class: "img-not-found"
            });
        }
        checkBox.dataset.checkBoxId = book.id;
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