async function processImageArea(url, img) {
    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'image/*',
            }
        });
        const imageBlob = await response.blob()
        const imageObjectURL = URL.createObjectURL(imageBlob);
        if (response.status === 200) {
            setAttribute(img, {
                src: imageObjectURL,
                width: "300px",
                height: "400px",
                class: "cover-image"
            });
        } else if (response.status === 404) {
            setAttribute(img, {
                src: imgNotFoundSrc,
                class: "img-not-found"
            });
        }
        return true;
    } catch (err) {
        setAttribute(img, {
            src: imgNotFoundSrc,
            class: "img-not-found"
        });
        return false;
    }
};

async function getBooks(url) {
    try {
        const response = await fetch(url);
        books = await response.json();
        addBooks(books);
    } catch (error) {
        stopSpinner();
        console.log(error);
        alert("We're sorry, but we encountered an unexpected problem downloading one or more components and can't get the books right now...Please try again later");
    }
}