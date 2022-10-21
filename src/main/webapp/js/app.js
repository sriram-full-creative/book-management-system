/**
 * Elements in the Welcome Page.
 */
const viewAllBooksIntro = document.querySelector(".welcome-view-books-button");
const welcomeMessage = document.querySelector(".welcome-message-container");

/**
 * Elements in the Nav Bar.
 */
const navbar = document.querySelector(".navbar");

/**
 * Elements in the Search Input Area.
 */
const searchBarContainer = document.querySelector(".search-wrapper");
const searchInput = document.querySelector("[data-search]");

/**
 * Elements in the Nav Bar Links/Buttons.
 */
const navbarLinks = document.getElementsByClassName("navbar-links")[0];
const viewAllBooksTrigger = document.querySelector(".view-all-books");
const addBookTrigger = document.querySelector(".add-book");
const aboutTrigger = document.querySelector(".about-container");
const deleteAllBooksTrigger = document.querySelector(".delete-all-books");
const toggleButton = document.getElementsByClassName("toggle-menu-button")[0];

/**
 * Elements in the Sort By Dropdown Container Area.
 */
const sortByContainer = document.querySelector(".sort-container");
const sortByOptions = document.querySelector("#sort-books");
const deleteSelectedButton = document.querySelector("#delete-selected-button");

/**
 * Elements in the books container area.
 */
const booksContainer = document.getElementById("books-container");
const bookCardTemplate = document.querySelector("[data-book-template]");
const addBookFormTemplate = document.querySelector("[data-add-book-form-template]");
const updateBookFormTemplate = document.querySelector("[data-update-book-form-template]");
const updateCoverImageFormTemplate = document.querySelector("[data-update-cover-image-form-template]");

/**
 * Spinner Element to show the loading effect.
 */
const spinnerTemplate = document.querySelector("#spinner");

/**
 * Description - A helper function to render books initially from the welcome page.
 */
function toggleViewAllBooks() {
    runSpinner();
    allCachedBooks.clear();
    clearBookContainer();
    sortByOptions.selectedIndex = "0";
    currentProperty = "";
    currentDirection = "";
    apiUrl = "";
    setTimeout(() => {
        getBooks(defaultApiUrl);
    }, 2000);
    uncheckAllCheckboxes();
}


/** 
 * EVENT LISTENERS for all the elements.
 * */
viewAllBooksIntro.addEventListener("click", toggleViewAllBooksFromIntro);

searchInput.addEventListener("input", () => {
    searchBooks();
});

viewAllBooksTrigger.addEventListener("click", toggleViewAllBooks);

addBookTrigger.addEventListener("click", toggleAddBookForm);

deleteAllBooksTrigger.addEventListener("click", deleteAllBooks);
deleteSelectedButton.addEventListener("click", () => { deleteSelectedBooks(selectedBooks) });

booksContainer.addEventListener('click', processBooksOperation);

sortByOptions.addEventListener("change", sortBooks);

toggleButton.addEventListener("click", () => {
    navbarLinks.classList.toggle("active")
});

booksContainer.addEventListener('submit', (e) => {
    e.preventDefault();
    const bookForm = e.target;
    if (e.target.classList.contains("add-book-form")) {
        const formData = new FormData(e.target);
        addNewBook(formData, bookForm);
    } else if (e.target.classList.contains("update-book-form")) {
        const updatebookForm = new FormData(e.target);
        console.log(updatebookForm);
        console.log(e.currentTarget);
        updateBookDetails(updatebookForm, bookForm);
    } else if (e.target.classList.contains("update-cover-image-form")) {
        ImagefileInput = e.currentTarget.querySelector("#cover-image");
        const formData = new FormData();
        formData.append("coverImage", ImagefileInput.files[0]);
        updateCoverImage(ImagefileInput, bookForm);
        console.log(currentBookId);
    }
});

booksContainer.addEventListener("reset", (e) => {
    const bookForm = e.target;
    const resultContainer = bookForm.querySelector("#result-container");
    resultContainer.innerHTML = "";
    resultContainer.className = "";
})


window.addEventListener('scroll', () => {
    if (window.scrollY + window.innerHeight >=
        document.documentElement.scrollHeight && shouldLoad) {
        shouldLoad = false;
        let newApiUrl = paginationRequestUrlContructor(BASE_URL.url, ENDPOINTS.books, currentProperty, currentDirection, nextCursor.cursor);
        console.log(newApiUrl);
        getBooks(newApiUrl);
        console.log("We Have Reached the End of the Page");
    }
})