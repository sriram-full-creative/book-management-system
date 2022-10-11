const viewAllBooksIntro = document.querySelector(".welcome-view-books-button");
const toggleButton = document.getElementsByClassName("toggle-menu-button")[0];
const navbarLinks = document.getElementsByClassName("navbar-links")[0];
const booksContainer = document.getElementById("books-container");
const bookCardTemplate = document.querySelector("[data-book-template]");
const searchInput = document.querySelector("[data-search]")

/** Navbar Buttons */
const viewAllBooksTrigger = document.querySelector(".view-all-books");
const addBookTrigger = document.querySelector(".add-book");
const aboutTrigger = document.querySelector(".about-container");
const searchBarContainer = document.querySelector(".search-wrapper");
const sortByContainer = document.querySelector(".sort-container");
const sortByOptions = document.querySelector("#sort-books");
const navbar = document.querySelector(".navbar");
// const bookFormTemplate = document.querySelector("[data-book-form-template]");
const modalForm = document.getElementById("form-container");

function toggleViewAllBooks() {
    getBooks(apiUrl);
}

/** EVENT LISTENERS */
viewAllBooksIntro.addEventListener("click", toggleViewAllBooksFromIntro);
toggleButton.addEventListener("click", () => { navbarLinks.classList.toggle("active") });
searchInput.addEventListener("input", searchBooks);
viewAllBooksTrigger.addEventListener("click", toggleViewAllBooks);
addBookTrigger.addEventListener("click", toggleAddBookForm);
booksContainer.addEventListener('click', processBooks);
sortByOptions.addEventListener("change", sortBooks);


