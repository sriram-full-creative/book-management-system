var allCachedBooks = new Map();
var books = [];

var selectedBooks = new Set();

var resultsFromServer = {
    books: "",
    cursor: ""
};
var currentBookId = "";
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


const BASE_URL = {
    url: "http://localhost:10000"
    //url: "https://book-management-system-362310.uc.r.appspot.com"
}

const ENDPOINTS = {
    books: "/books",
    images: "/images",
    signup: "/users/signup",
    login: "/users/login",
    logout: "/users/logout",
    taskQueue: "/taskqueues/enqueue",
    main: "/books/all"
}

const QUERY_PARAMETERS = {
    sortOnProperty: {
        default: "",
        author: "sortOnProperty=author",
        publication: "sortOnProperty=publication",
        title: "sortOnProperty=title",
        pages: "sortOnProperty=pages",
        releaseYear: "sortOnProperty=releaseYear",
        rating: "sortOnProperty=rating"
    },
    sortDirection: {
        ascending: "sortDirection=ascending",
        default: "sortDirection=descending",
    },
    deleteBooks: {
        deleteAll: "delete=all",
        deleteSelected: "delete=selected"
    }
}


const imgNotFoundSrc = "./../images/image-not-found.svg";

let apiUrl = getRequestUrlConstructor(BASE_URL.url, ENDPOINTS.books, QUERY_PARAMETERS.sortOnProperty.default, QUERY_PARAMETERS.sortDirection.default);
const defaultApiUrl = getRequestUrlConstructor(BASE_URL.url, ENDPOINTS.books, QUERY_PARAMETERS.sortOnProperty.default, QUERY_PARAMETERS.sortDirection.default);
