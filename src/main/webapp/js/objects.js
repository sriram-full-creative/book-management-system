var allBooks = new Map();
var books = [];
var resultsFromServer = {
    books: "",
    cursor: ""
};
var currentProperty = "";
var currentDirection = "";
var nextCursor = {
    default: "",
    cursor: ""
};
var shouldLoad = false;
var isbookUpdated = false;
var isbookAdded = false;
var isCoverImageUpdated = false;
const imgNotFoundSrc = "./../images/image-not-found.svg";
const ENDPOINTS = {
    books: "/books",
    images: "/images",
    signup: "/users/signup",
    login: "/users/login",
    logout: "/users/logout",
    main: "/books/all"
}

const sortOnProperty = {
    default: "",
    author: "sortOnProperty=author",
    publication: "sortOnProperty=publication",
    title: "sortOnProperty=title",
    pages: "sortOnProperty=pages",
    releaseYear: "sortOnProperty=releaseYear",
    rating: "sortOnProperty=rating"
}

const sortDirection = {
    ascending: "sortDirection=ascending",
    default: "sortDirection=descending",
}

const domain = {
    name: "http://localhost:10000"
    // name: "https://book-management-system-362310.uc.r.appspot.com"
}

let apiUrl = getRequestUrlConstructor(domain.name, ENDPOINTS.books, sortOnProperty.default, sortDirection.default);
const defaultApiUrl = getRequestUrlConstructor(domain.name, ENDPOINTS.books, sortOnProperty.default, sortDirection.default);
