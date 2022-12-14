<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Manager</title>
    <link rel="icon" href="./../images/book-manager-logo.svg" type="image/svg">
    <link rel="stylesheet" href="./../styles/welcomepage.css">
    <link rel="stylesheet" href="./../styles/sortcontainer.css">
    <link rel="stylesheet" href="./../styles/navbar.css">
    <link rel="stylesheet" href="./../styles/form.css">
    <link rel="stylesheet" href="./../styles/main.css">
    <script src="./../js/utilityFunctions.js" defer></script>
    <script src="./../js/objects.js" defer></script>
    <script src="./../js/asyncFunctions.js" defer></script>
    <script src="./../js/app.js" defer></script>
</head>

<body>
    <nav class="navbar">
        <div class="brand-title"><img src="./../images/book-manager-logo.svg" alt="logo" class="brand-logo">&nbsp;Book
            Manager
        </div>
        <a href="#" class="toggle-menu-button">
            <span class="bar"></span>
            <span class="bar"></span>
            <span class="bar"></span>
        </a>
        <div class="search-wrapper">
            <input type="search" id="search" placeholder="Search Title or Author" data-search>
        </div>
        <div class="navbar-links">
            <ul>
                <li class="view-all-books"><a href="#books">View Books</a></li>
                <li class="add-book"><a href="#add-book">Add a Book</a></li>
                <li class="logout" onclick="logout()"><a href="#logout" onclick="logout()">Logout</a></li>
                <li class="about-container">
                    <a class="btn" href="#open-about">About</a>
                    <div id="open-about" class="about-window">
                        <div>
                            <a href="#" title="Close" class="about-close">Close</a>
                            <h1>Book Manager</h1>
                            <div>
                                <img src="./../images/book-manager-logo.svg" alt="logo" width="50px">
                                <p>Voilaa! This is a Pocket library. Hope you find it helpful.</p>
                                <p class="copyright-info">? Sriram S</p>
                            </div>
                            <br>
                        </div>
                    </div>
                </li>
                <li class="delete-all-books"><a href="#books">Delete All</a></li>
            </ul>
        </div>
    </nav>

    <div class="sort-container-block">
        <div class="sort-container">
            <label for="sort-books">Sort by: </label>
            <select name="sort-books" id="sort-books">
                <option value="time-descending">Recently Updated</option>
                <option value="time-ascending">First Updated</option>

                <option value="author-descending">Author (High to Low)</option>
                <option value="author-ascending">Author (Low to High)</option>

                <option value="publication-descending">Publication (High to Low)</option>
                <option value="publication-ascending">Publication (Low to High)</option>

                <option value="title-descending">Title (High to Low)</option>
                <option value="title-ascending">Title (Low to High)</option>

                <option value="pages-descending">Pages (High to Low)</option>
                <option value="pages-ascending">Pages (Low to High)</option>

                <option value="release-year-descending">Release Year (High to Low)</option>
                <option value="release-year-ascending">Release Year (Low to High)</option>

                <option value="rating-descending">Rating (High to Low)</option>
                <option value="rating-ascending">Rating (Low to High)</option>
            </select>
        </div>
        <div id="delete-selected-field" class="delete-selected">
            <button id="delete-selected-button" disabled>Delete Selected</button>
        </div>
    </div>
    <div class="books-container" id="books-container">
        <template data-book-template>
            <article class="book">
                <div class="data-container">
                    <div class="cover-image-container" data-cover-image-container>
                        <input type="checkbox" class="book-card-check-box">
                        <img src="" class="cover-image" width="190px" height="270px">
                    </div>
                    <div class="book-details-container" data-book-details-container>
                        <ul class="book-details-list">
                            <li class="book-title"></li>
                            <li class="author-name"></li>
                            <li class="publication-name"></li>
                            <li class="language"></li>
                            <li class="release-year"></li>
                            <li class="pages"></li>
                            <li class="country"></li>
                            <li class="rating"></li>
                        </ul>
                    </div>
                </div>
                <div class="button-container" data-button-container>
                    <div class="update-buttons-container">
                        <button class="cover-image-button"><a href="#update-cover-image"></a></button>
                        <button class="update-book-button"><a href="#update-book"></a></button>
                    </div>
                    <div class="delete-button-container">
                        <button class="delete-book-button"></button>
                    </div>

                </div>
            </article>
        </template>
        <div class="welcome-message-container">
            <h1 class="hello-Message">
                <span style="color: #97C774">H</span>
                <span style="color: #B63E98">e</span>
                <span style="color: #D18E62">l</span>
                <span style="color: #DB3E41">l</span>
                <span style="color: #1BABA5">o</span>.
            </h1>
            <button class="welcome-view-books-button"><a class="welcome-view-books-link" href="#books">Click
                    Me!!</a></button>
        </div>

        <template data-add-book-form-template>
            <div id="add-book-form-container" class="add-book-form-container">
                <form action="" method="" id="book-form" class="book-form">
                    <div class="close-button-container"><a href="#" onclick="closeFormModal()" title="Close"
                            class="close-button"></a>
                    </div>
                    <h2 class="heading">Enter Book Details</h2>
                    <div class="book-details">
                        <div class="input-field">
                            <label for="book-title">Title:</label>
                            <input id="book-title" name="title" type="text" placeholder=" " required>
                        </div>
                        <div class="input-field">
                            <label for="author-name">Author:</label>
                            <input id="author-name" name="author" type="text" placeholder=" " required>
                        </div>
                        <div class="input-field">
                            <label for="publication">Publication:</label>
                            <input id="publication" name="publication" type="text" placeholder=" " required>
                        </div>
                        <div class="input-field">
                            <label for="language">Language:</label>
                            <input id="language" name="language" type="text" placeholder=" " required>
                        </div>
                        <div class="input-field">
                            <label for="pages">Pages:</label>
                            <input id="pages" name="pages" type="number" min="20" max="9999" placeholder=" " required>
                        </div>
                        <div class="input-field">
                            <label for="release-year">Release Year:</label>
                            <input id="release-year" name="releaseYear" type="number" min="1000" max="9999"
                                placeholder=" " required>
                        </div>
                        <div class="input-field">
                            <label for="country">Country:</label>
                            <input id="country" name="country" type="text" placeholder=" " required>
                        </div>
                        <div class="input-field">
                            <label for="rating">Rating:</label>
                            <input id="rating" name="rating" type="number" min="0" max="5" placeholder=" " required>
                        </div>
                    </div>
                    <div id="input-field" class="form-buttons">
                        <button id="add-book-submit" type="submit">Add Book</button>
                        <button id="add-book-reset" type="reset">Clear Details</button>
                    </div>
                    <div class="text-base text-center text-gray-400" id="result-container">
                </form>
            </div>
        </template>

        <template data-update-book-form-template>
            <div id="update-book-form-container" class="update-book-form-container">
                <form action="" method="" id="book-form" class="book-form">
                    <div class="close-button-container"><a href="#" onclick="closeFormModal()" title="Close"
                            class="close-button"></a>
                    </div>
                    <h2 class="heading">Enter Book Details</h2>
                    <div class="book-details">
                        <div class="input-field">
                            <label for="book-title">Title:</label>
                            <input id="book-title" name="title" type="text" placeholder=" " required>
                        </div>
                        <div class="input-field">
                            <label for="author-name">Author:</label>
                            <input id="author-name" name="author" type="text" placeholder=" " required>
                        </div>
                        <div class="input-field">
                            <label for="publication">Publication:</label>
                            <input id="publication" name="publication" type="text" placeholder=" " required>
                        </div>
                        <div class="input-field">
                            <label for="language">Language:</label>
                            <input id="language" name="language" type="text" placeholder=" ">
                        </div>
                        <div class="input-field">
                            <label for="pages">Pages:</label>
                            <input id="pages" name="pages" type="number" min="20" max="9999" placeholder=" " required>
                        </div>
                        <div class="input-field">
                            <label for="release-year">Release Year:</label>
                            <input id="release-year" name="releaseYear" type="number" min="1000" max="9999"
                                placeholder=" " required>
                        </div>
                        <div class="input-field">
                            <label for="country">Country:</label>
                            <input id="country" name="country" type="text" placeholder=" " required>
                        </div>
                        <div class="input-field">
                            <label for="rating">Rating:</label>
                            <input id="rating" name="rating" type="number" min="0" max="5" placeholder=" " required>
                        </div>
                    </div>
                    <div id="input-field" class="form-buttons">
                        <button id="update-book-submit" type="submit">Update Book</button>
                        <button id="update-book-reset" type="reset">Clear Details</button>
                    </div>
                    <div class="text-base text-center text-gray-400" id="result-container">

                    </div>
                </form>
            </div>
        </template>

        <template data-update-cover-image-form-template>
            <div id="update-book-form-container" class="update-cover-image-form-container">
                <form action="" method="" id="book-form" class="book-form" enctype="multipart/form-data">
                    <div class="close-button-container"><a href="#" onclick="closeFormModal()" title="Close"
                            class="close-button"></a>
                    </div>
                    <h2 class="heading">Choose An Image</h2>
                    <div class="book-details">
                        <div class="cover-image-input-field">
                            <input id="cover-image" name="cover-image" type="file"
                                accept="image/png, image/jpg, image/jpeg" required>
                        </div>
                    </div>
                    <div id="input-field" class="form-buttons">
                        <button id="cover-image-submit" type="submit">Update Image</button>
                        <button id="cover-image-reset" type="reset">Clear</button>
                    </div>
                    <div class="text-base text-center text-gray-400" id="result-container">

                    </div>
                </form>
            </div>
        </template>
    </div>
    <!-- this will show our spinner -->
    <div class="loader">
        <div hidden class="spinner" id="spinner"></div>
    </div>

</body>

</html>