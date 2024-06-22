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
    FOREIGN KEY (Publisher_ID) REFERENCES Publisher(Publisher_ID) ON UPDATE CASCADE,
    INDEX idx_book_isbn (ISBN)
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
    FOREIGN KEY (User_ID) REFERENCES `User` (User_ID) ON UPDATE CASCADE ON DELETE CASCADE,
    INDEX idx_review_isbn (ISBN)
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
    FOREIGN KEY (ISBN) REFERENCES Book(ISBN) ON UPDATE CASCADE ON DELETE CASCADE,
    INDEX idx_wishlist_books_isbn (ISBN)
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
    FOREIGN KEY (ISBN) REFERENCES Book(ISBN) ON UPDATE CASCADE ON DELETE CASCADE,
    INDEX idx_shoppingcart_books_isbn (ISBN)
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

-- Load data into tables
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Publisher.txt' INTO TABLE Publisher FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Publisher_ID, PublisherName);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Author.txt' INTO TABLE Author FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Author_ID, Publisher_ID, FirstName, LastName, Biography);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Book.txt' INTO TABLE Book FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (ISBN, BookName, Author_ID, Publisher_ID, BookDescription, Genre, YearPublished, CopiesSold, Price, id);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/User.txt' INTO TABLE `User` FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (User_ID, Username, Email, Pass);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Review.txt' INTO TABLE Review FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Review_ID, ISBN, User_ID, `Text`, Rating, `Date`);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Wishlist.txt' INTO TABLE Wishlist FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Wishlist_ID, WishlistName, User_ID);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/WishlistBook.txt' INTO TABLE wishlist_books FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Wishlist_ID, ISBN);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/ShoppingCart.txt' INTO TABLE ShoppingCart FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Cart_ID, User_ID);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/CartBook.txt' INTO TABLE shoppingcart_books FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Cart_ID, ISBN, Quantity);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/CreditCard.txt' INTO TABLE CreditCard FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Card_ID, User_ID, CardNumber, ExpirationDate, CVV);