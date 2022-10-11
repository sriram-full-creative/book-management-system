var books = [];
const imgNotFoundSrc = "./images/image-not-found.svg";
const ENDPOINTS = {
    getBooks: "/books",
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
    default: "sortDirection=descending"
}

const domain = {
    name: "https://book-management-system-362310.uc.r.appspot.com"
}

let apiUrl = requestUrlConstructor(domain.name, ENDPOINTS.getBooks, sortOnProperty.default, sortDirection.default);
