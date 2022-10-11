// Helper Function to Set Attributes on DOM Elements
function setAttribute(element, attributes) {
    for (const key in attributes) {
        element.setAttribute(key, attributes[key]);
    }
}

String.prototype.toCamelCase = function () {
    return this.replace(/^([A-Z])|\s(\w)/g, function (match, p1, p2, offset) {
        if (p2) return p2.toUpperCase();
        return p1.toLowerCase();
    });
};

function requestUrlConstructor(domainName, endPoint, firstQueryParameter, secondQueryParameter) {
    return domainName + endPoint + "?" + firstQueryParameter + "&" + secondQueryParameter;
}

function clearBookContainer() {
    booksContainer.innerHTML = '';
}

function addBooks(books) {
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
    searchBarContainer.style.visibility = "visible";
    sortByContainer.style.visibility = "visible";
    navbar.style.visibility = "visible";
    viewAllBooksTrigger.style.visibility = "visible";
    addBookTrigger.style.visibility = "visible";
    aboutTrigger.style.visibility = "visible";
    getBooks(apiUrl);
}

function clearFormDetails(modalForm) {
    const inputFields = modalForm.querySelectorAll("#book-title, #author-name, #publication, #language, #pages, #release-year, #country, #rating");
    inputFields.forEach(input => {
        input.value = ""
    });
    const addBookButton = modalForm.querySelector("#submit");
    addBookButton.value = "Add Book";
    addBookButton.innerText = "Add Book";
}

function toggleAddBookForm() {
    clearBookContainer();
    modalForm.style.display = "grid";
    modalForm.children[0].classList.add("add-book-form");
    clearFormDetails(modalForm);
    booksContainer.appendChild(modalForm);
    sortByContainer.style.visibility = "hidden";
    // prevent  background scroll
    document.documentElement.style.overflow = 'hidden';
    document.body.scroll = 'no';
}

function closeModel() {
    modalForm.style.display = "none";
    modalForm.children[0].classList.remove("add-book-form");
    modalForm.children[0].classList.remove("update-book-form");
    modalForm.querySelector("#submit").innerHTML = ""
    modalForm.querySelector("#submit").value = ""
    document.documentElement.style.overflow = 'scroll';
    document.body.scroll = 'yes';
    sortByContainer.style.visibility = "visible";
    getBooks(apiUrl);
}

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

    const updateBookButton = modalForm.querySelector("#submit");
    console.log(updateBookButton);
    updateBookButton.value = "Update Book";
    updateBookButton.innerText = "Update Book";
}


function updateCoverImage(bookId) {
    console.log(document.getElementById(bookId));
}

function updateBook(bookId) {
    const detailsForUpdate = document.getElementById(bookId);
    clearBookContainer();
    poulateFormWithBookDetails(detailsForUpdate, modalForm);
    modalForm.style.display = "grid";
    modalForm.children[0].classList.add("update-book-form");
    booksContainer.appendChild(modalForm);
    sortByContainer.style.visibility = "hidden";
    document.documentElement.style.overflow = 'hidden';
    document.body.scroll = 'no';
}

function deleteBook(bookId) {

    console.log(document.getElementById(bookId));
}

function processBooks() {
    if (event.target.classList == 'cover-image-button') {
        updateCoverImage(event.target.dataset.updateCoverImageButtonId);
    } else if (event.target.classList == 'update-book-button') {
        updateBook(event.target.dataset.updateBookButtonId);
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
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.default,
                sortDirection.default
            )
        case "time-ascending":
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.default,
                sortDirection.ascending
            )
            break;
        case "author-descending":
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.author,
                sortDirection.default
            )
            break;
        case "author-ascending":
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.author,
                sortDirection.ascending
            )
            break;
        case "publication-descending":
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.publication,
                sortDirection.default
            )
            break;
        case "publication-ascending":
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.publication,
                sortDirection.ascending
            )
            break;
        case "title-descending":
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.title,
                sortDirection.default
            )
            break;
        case "title-ascending":
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.title,
                sortDirection.ascending
            )
            break;
        case "pages-descending":
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.pages,
                sortDirection.default
            )
            break;
        case "pages-ascending":
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.pages,
                sortDirection.ascending
            )
            break;
        case "release-year-descending":
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.releaseYear,
                sortDirection.default
            )
            break;
        case "release-year-ascending":
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.releaseYear,
                sortDirection.ascending
            )
            break;
        case "rating-descending":
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.rating,
                sortDirection.default
            )
            break;
        case "rating-ascending":
            return requestUrlConstructor(
                domain.name,
                ENDPOINTS.getBooks,
                sortOnProperty.rating,
                sortDirection.ascending
            )
            break;
        default:
            break;
    }
}

function sortBooks() {
    const selectedOption = sortByOptions.value;
    apiUrl = processOption(selectedOption);
    console.log(apiUrl);
    getBooks(apiUrl);
}