DROP DATABASE IF EXISTS GeekText_Bookstore;
CREATE SCHEMA GeekText_Bookstore;
USE GeekText_Bookstore;

-- Ensure tables are created 
CREATE TABLE Publisher (
    Publisher_ID INT AUTO_INCREMENT PRIMARY KEY,
    PublisherName VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Author (
    Author_ID INT AUTO_INCREMENT PRIMARY KEY,
    Publisher_ID INT,
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    Biography VARCHAR(1000) NOT NULL,
    FOREIGN KEY (Publisher_ID) REFERENCES Publisher(Publisher_ID) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Author_Publisher (
    Author_ID INT,
    Publisher_ID INT,
    PRIMARY KEY (Author_ID, Publisher_ID),
    FOREIGN KEY (Author_ID) REFERENCES Author(Author_ID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Publisher_ID) REFERENCES Publisher(Publisher_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Book (
    ISBN VARCHAR(13) PRIMARY KEY,
    CONSTRAINT isbn_num_chk CHECK(ISBN NOT LIKE '%[^0-9]%'),
    CONSTRAINT isbn_len_chk CHECK(CHAR_LENGTH(ISBN) = 13),
    id BIGINT AUTO_INCREMENT UNIQUE NOT NULL,
    BookName VARCHAR(500) NOT NULL,
    Author_ID INT,
    Publisher_ID INT,
    BookDescription VARCHAR(1000),
    Genre ENUM('TEXTBOOK', 'ACADEMIC', 'REPORT', 'BIOGRAPHY', 'MANUAL', 'FANTASY', 'SCIENCE_FICTION', 'ACTION', 'ADVENTURE', 'HISTORICAL', 'FICTION', 'NON_FICTION', 'OTHER') DEFAULT 'OTHER',
    YearPublished INT,
    CopiesSold INT UNSIGNED NOT NULL,
    Price FLOAT NOT NULL,
    FOREIGN KEY (Author_ID) REFERENCES Author(Author_ID) ON UPDATE CASCADE,
    FOREIGN KEY (Publisher_ID) REFERENCES Publisher(Publisher_ID) ON UPDATE CASCADE
);

CREATE TABLE `User` (
    User_ID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50),
    Email VARCHAR(50),
    Pass VARCHAR(50),
    HomeAddress VARCHAR(100),
    Wishlist_ID INT,
    Cart_ID INT
);

CREATE TABLE Review (
    Review_ID INT AUTO_INCREMENT PRIMARY KEY,
    ISBN VARCHAR(13),
    User_ID INT,
    `Text` VARCHAR(500),
    Rating INT,
    `Date` DATETIME,
    FOREIGN KEY (ISBN) REFERENCES Book(ISBN),
    FOREIGN KEY (User_ID) REFERENCES `User` (User_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Wishlist (
    Wishlist_ID INT AUTO_INCREMENT,
    WishlistName VARCHAR(25),
    User_ID INT,
    PRIMARY KEY (Wishlist_ID),
    FOREIGN KEY (User_ID) REFERENCES `User`(User_ID) ON UPDATE CASCADE
);

CREATE TABLE wishlist_books (
    Wishlist_ID INT,
    ISBN VARCHAR(13),
    PRIMARY KEY (Wishlist_ID, ISBN),
    FOREIGN KEY (Wishlist_ID) REFERENCES Wishlist(Wishlist_ID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (ISBN) REFERENCES Book(ISBN) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ShoppingCart (
    Cart_ID INT AUTO_INCREMENT PRIMARY KEY,
    User_ID INT,
    FOREIGN KEY (User_ID) REFERENCES `User`(User_ID) ON UPDATE CASCADE
);

CREATE TABLE shoppingcart_books (
    Cart_ID INT,
    ISBN VARCHAR(13),
    Quantity INT,
    PRIMARY KEY (Cart_ID, ISBN),
    FOREIGN KEY (Cart_ID) REFERENCES ShoppingCart(Cart_ID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (ISBN) REFERENCES Book(ISBN) ON UPDATE CASCADE ON DELETE CASCADE
);

ALTER TABLE `User` ADD CONSTRAINT Wishlist FOREIGN KEY (Wishlist_ID) 
REFERENCES Wishlist(Wishlist_ID) ON UPDATE CASCADE;

ALTER TABLE `User` ADD CONSTRAINT ShoppingCart FOREIGN KEY (Cart_ID) 
REFERENCES ShoppingCart(Cart_ID) ON UPDATE CASCADE;

CREATE TABLE CreditCard (
    Card_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    User_ID INT,
    CardNumber CHAR(16) UNIQUE NOT NULL,
    CONSTRAINT card_num_check CHECK(CardNumber NOT LIKE '%[^0-9]%'), 
    CONSTRAINT card_len_chk CHECK(CHAR_LENGTH(CardNumber) = 16),
    ExpirationDate VARCHAR(5),
    CONSTRAINT date_chk CHECK(ExpirationDate RLIKE '^[0-9]{2}/[0-9]{2}$'),
    CVV CHAR(3) NOT NULL,
    CONSTRAINT cvv_num_chk CHECK(CVV NOT LIKE '%[^0-9]%'),
    CONSTRAINT cvv_len_chk CHECK(CHAR_LENGTH(CVV) = 3),
    FOREIGN KEY (User_ID) REFERENCES `User`(User_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Insert data from text files

-- Publishers
INSERT INTO Publisher(Publisher_ID, PublisherName) VALUES
    (1, 'Publisher 1'),
    (2, 'Publisher 2'),
    (3, 'Publisher 3'),
    (4, 'Random House'),
    (5, 'Penguin Books');

-- Authors
INSERT INTO Author (Author_ID, Publisher_ID, FirstName, LastName, Biography) VALUES
    (1, 1, 'John', 'Smith', 'John Smith is an acclaimed author known for his thrilling novels.'),
    (2, 2, 'Jane', 'Doe', 'Jane Doe is a renowned writer of non-fiction works.'),
    (3, 1, 'Alice', 'Johnson', 'Alice Johnson writes compelling science fiction stories.'),
    (4, 3, 'Bob', 'Brown', 'Bob Brown is a famous fantasy novelist.'),
    (5, 2, 'Carol', 'Davis', 'Carol Davis is a well-known biographer.');

-- Books
INSERT INTO Book(ISBN, BookName, Author_ID, Publisher_ID, BookDescription, Genre, YearPublished, CopiesSold, Price, id) VALUES
    (9783161484100, 'Example Book Title', 1, 1, 'A thrilling adventure tale.', 'FICTION', 2023, 500000, 29.99, 1),
    (9781234567897, 'Another Book Title', 2, 2, 'An insightful historical narrative.', 'NON_FICTION', 2021, 250000, 19.99, 2),
    (9780140449136, 'Third Book Example', 3, 1, 'A captivating fantasy story.', 'SCIENCE_FICTION', 2019, 750000, 24.99, 3),
    (9780345391803, 'Fourth Book Here', 4, 3, 'A comprehensive how-to guide.', 'FANTASY', 2020, 100000, 34.99, 4),
    (9780307593699, 'Fifth Book Example', 1, 2, 'An inspiring biography.', 'BIOGRAPHY', 2022, 125000, 22.99, 5),
    (9780307593705, 'Sixth Book Sample', 2, 1, 'A gripping sci-fi novel.', 'FICTION', 2022, 300000, 27.99, 6),
    (9781566199094, 'Seventh Book', 3, 2, 'A detailed academic report.', 'NON_FICTION', 2018, 450000, 18.99, 7),
    (9780743273565, 'Eighth Book Title', 4, 3, 'A charming children\'s story.', 'SCIENCE_FICTION', 2021, 550000, 26.99, 8),
    (9780452284234, 'Ninth Book Here', 1, 2, 'An action-packed adventure.', 'FANTASY', 2020, 600000, 33.99, 9),
    (9780743273568, 'Tenth Book Example', 2, 1, 'A heartwarming autobiography.', 'BIOGRAPHY', 2023, 200000, 23.99, 10);

-- Users
INSERT INTO `User` (User_ID, Username, Email, Pass, HomeAddress, Wishlist_ID, Cart_ID) VALUES
    (1, 'user1', 'user1@example.com', 'password1', '123 Main St', NULL, NULL),
    (2, 'user2', 'user2@example.com', 'password2', '456 Oak St', NULL, NULL),
    (3, 'user3', 'user3@example.com', 'password3', '789 Pine St', NULL, NULL),
    (4, 'user4', 'user4@example.com', 'password4', '101 Maple St', NULL, NULL),
    (5, 'user5', 'user5@example.com', 'password5', '202 Birch St', NULL, NULL),
    (6, 'user6', 'user6@example.com', 'password6', '303 Cedar St', NULL, NULL),
    (7, 'user7', 'user7@example.com', 'password7', '404 Elm St', NULL, NULL),
    (8, 'user8', 'user8@example.com', 'password8', '505 Walnut St', NULL, NULL),
    (9, 'user9', 'user9@example.com', 'password9', '606 Cherry St', NULL, NULL),
    (10, 'user10', 'user10@example.com', 'password10', '707 Willow St', NULL, NULL);

-- Reviews
INSERT INTO Review (Review_ID, ISBN, User_ID, `Text`, Rating, `Date`) VALUES
    (1, '9783161484100', 1, 'An excellent read!', 5, '2023-01-15 14:23:00'),
    (2, '9781234567897', 2, 'Very informative and well-written.', 4, '2023-02-20 09:45:00'),
    (3, '9780140449136', 3, 'A bit dull in parts.', 3, '2023-03-05 12:10:00'),
    (4, '9780345391803', 4, 'Loved the characters and plot.', 5, '2023-04-10 18:30:00'),
    (5, '9780307593699', 5, 'Not what I expected.', 2, '2023-05-22 11:00:00'),
    (6, '9780307593705', 6, 'A masterpiece!', 5, '2023-06-15 16:45:00'),
    (7, '9781566199094', 7, 'Great for a quick read.', 4, '2023-07-01 08:20:00'),
    (8, '9780743273565', 8, 'Couldn\'t put it down.', 5, '2023-08-11 10:15:00'),
    (9, '9780452284234', 9, 'Very dry and technical.', 3, '2023-09-30 13:55:00'),
    (10, '9780743273568', 10, 'An absolute page-turner.', 5, '2023-10-05 19:40:00');

-- Wishlist
INSERT INTO Wishlist (Wishlist_ID, WishlistName, User_ID) VALUES
    (1, 'Summer Reading', 1),
    (2, 'Holiday Wishlist', 2),
    (3, 'SciFi Favorites', 3),
    (4, 'History Buff', 4),
    (5, 'Must Reads', 5),
    (6, 'Fantasy Collection', 6),
    (7, 'Action Adventure', 7),
    (8, 'Classics', 1),
    (9, 'Best Biographies', 2),
    (10, 'To Read', 3);

-- Wishlist Books
INSERT INTO wishlist_books(Wishlist_ID, ISBN) VALUES
    (1, '9783161484100'),
    (2, '9781234567897'),
    (3, '9780140449136'),
    (4, '9780345391803'),
    (5, '9780307593699'),
    (6, '9780307593705'),
    (7, '9781566199094'),
    (8, '9780743273565'),
    (9, '9780452284234'),
    (10, '9780743273568');

-- Shopping Cart
INSERT INTO ShoppingCart (Cart_ID, User_ID) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10);

-- Shopping Cart Books
INSERT INTO shoppingcart_books(Cart_ID, ISBN, Quantity) VALUES
    (1, '9783161484100', 2), 
    (2, '9781234567897', 1),
    (3, '9780140449136', 3),
    (4, '9780345391803', 1),
    (5, '9780307593699', 2),
    (6, '9780307593705', 1),
    (7, '9781566199094', 4),
    (8, '9780743273565', 1),
    (9, '9780452284234', 3),
    (10, '9780743273568', 2);

-- Credit Cards
INSERT INTO CreditCard (Card_ID, User_ID, CardNumber, ExpirationDate, CVV) VALUES
    (1, 1, '1234567890123456', '12/25', '123'),
    (2, 2, '2345678901234567', '11/26', '234'),
    (3, 3, '3456789012345678', '10/27', '345'),
    (4, 4, '4567890123456789', '09/24', '456'),
    (5, 5, '5678901234567890', '08/25', '567'),
    (6, 6, '6789012345678901', '07/26', '678'),
    (7, 7, '7890123456789012', '06/27', '789');