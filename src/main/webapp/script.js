const toggleButton = document.getElementsByClassName("toggle-button")[0];
const navbarLinks = document.getElementsByClassName("navbar-links")[0];
const booksContainer = document.getElementById("books-container");

toggleButton.addEventListener("click", () => { navbarLinks.classList.toggle("active") });

let books = new Array();

async function getBooks() {
    const apiUrl = '/books';
    try {
        const response = await fetch(apiUrl);
        books = await response.json();       
		books.forEach(book => {
			console.log(book);
			addBook(book);
			});
    } catch (error) {
        // Catch Error here
        console.log(error);
        alert("We're sorry, but we encountered an unexpected problem downloading one or more components and can't get the books right now...Please try again later");
    }
}

async function processImageArea(url, img) {
    try {
        const response = await fetch(url);
        const imageBlob = await response.blob();
        const imageObjectURL = URL.createObjectURL(imageBlob);
        console.log(imageObjectURL);
        if (response.status === 200) {
            setAttribute(img, {
                src: imageObjectURL,
                width: "225px",
                height: "300px",
                class: "cover-image"
            });
            console.log("successfully got the image => " + response.status);
        } else if (response.status === 404) {
            setAttribute(img, {
                src: "image-not-found.svg",
                width: "225px",
                height: "280px",
                class: "img-not-found"
            });
        }
        return true;
    } catch (err) {
        setAttribute(img, {
            src: "image-not-found.svg",
            width: "225px",
            height: "280px",
            class: "img-not-found"
        });
        console.log("HTTP-Error: " + response.status);
        return false;
    }
};

// Helper Function to Set Attributes on DOM Elements
function setAttribute(element, attributes) {
    for (const key in attributes) {
        element.setAttribute(key, attributes[key]);
    }
}

function addBook(book) {
        const article = document.createElement("article");
        article.className = "book";
        article.id = book.id;
        const img = document.createElement("img");
        if (book.coverImage.length > 0) {
            processImageArea(book.coverImage, img);
        } else {
            setAttribute(img, {
                src: "image-not-found.svg",
                width: "225px",
                height: "280px",
                class: "img-not-found"
            });
        }
        const p = document.createElement("p");
        const ul = document.createElement("ul");
        setAttribute(ul, {
            class: "book-details"
        });

        const bookTitle = document.createElement("li");
        setAttribute(bookTitle, {
            class: "book-title"
        });
        bookTitle.textContent = book.title;

        const author = document.createElement("li");
        setAttribute(author, {
            class: "author-name"
        });
        author.textContent = "By " + book.author;

        const buttonContainer = document.createElement("div");
        setAttribute(buttonContainer, {
            class: "button-container"
        });

        const updateButton = document.createElement("button");
        setAttribute(updateButton, {
            class: "update-book-details"
        });
        updateButton.textContent = "Update";

        const deleteButton = document.createElement("button");
        setAttribute(deleteButton, {
            class: "delete-book"
        });
        deleteButton.textContent = "Delete";

        ul.appendChild(bookTitle);
        ul.appendChild(author);
        p.appendChild(ul);
        buttonContainer.appendChild(updateButton);
        buttonContainer.appendChild(deleteButton);
        article.append(img);
        article.appendChild(p);
        article.appendChild(buttonContainer);
        booksContainer.appendChild(article);
}

getBooks();

