drop database if exists GeekText_Bookstore;
create schema GeekText_Bookstore;
use GeekText_Bookstore;

create table Publisher (
    Publisher_ID int primary key,
    PublisherName varchar(50) unique not null
);

create table Author (
    Author_ID int primary key,
    Publisher_ID INT,
    FirstName varchar(100) not null,
    LastName varchar(100) not null,
    Biography varchar(1000) not null
);

create table Author_Publisher (
    Author_ID int,
    Publisher_ID int,
    primary key (Author_ID, Publisher_ID),
    foreign key (Author_ID) references Author(Author_ID) on update cascade on delete cascade,
    foreign key (Publisher_ID) references Publisher(Publisher_ID) on update cascade on delete cascade
);

create table Book (
    ISBN varchar(13) primary key,
    constraint isbn_num_chk check(ISBN not like '%[^0-9]%'),
    constraint isbn_len_chk check(char_length(ISBN) = 13),
    BookName varchar(500) not null,
    Author_ID int,
    Publisher_ID int,
    BookDescription varchar(1000),
    Genre enum('Textbook', 'Academic/Report', 'Biography', 'How-to/Manual', 'Fantasy', 'Science Fiction', 'Action/Adventure', 'Historical', 'Fiction', 'Non-Fiction', 'Other') default 'Other',
    YearPublished year,
    CopiesSold int unsigned not null,
    Price float not null,
    foreign key (Author_ID) references Author(Author_ID) on update cascade,
    foreign key (Publisher_ID) references Publisher(Publisher_ID) on update cascade,
    INDEX idx_book_isbn (ISBN)
);

create table `User` (
    User_ID int primary key,
    Username varchar(50),
    Email varchar(50),
    Pass varchar(50),
    HomeAddress varchar(100),
    Wishlist_ID int,
    Cart_ID int
);

create table Review (
    Review_ID int primary key,
    ISBN varchar(13),
    User_ID int,
    `Text` varchar(500),
    Rating int,
    `Date` datetime,
    foreign key (ISBN) references Book(ISBN),
    foreign key (User_ID) references `User` (User_ID) on update cascade on delete cascade,
    INDEX idx_review_isbn (ISBN)
);

create table Wishlist (
    Wishlist_ID int,
    WishlistName varchar(25),
    User_ID int,
    ISBN varchar(13),
    primary key (Wishlist_ID, WishlistName),
    foreign key (User_ID) references `User`(User_ID) on update cascade,
    foreign key (ISBN) references Book(ISBN) on update cascade,
    INDEX idx_wishlist_isbn (ISBN)
);

create table wishlist_books (
    Wishlist_ID int,
    ISBN varchar(13),
    primary key (Wishlist_ID, ISBN),
    foreign key (Wishlist_ID) references Wishlist(Wishlist_ID) on update cascade,
    foreign key (ISBN) references Book(ISBN) on update cascade,
    INDEX idx_wishlist_books_isbn (ISBN)
);

create table ShoppingCart (
    Cart_ID int primary key,
    User_ID int,
    foreign key (User_ID) references `User`(User_ID) on update cascade
);

create table shoppingcart_books (
    Cart_ID int,
    ISBN varchar(13),
    Quantity int,
    primary key (Cart_ID, ISBN),
    foreign key (Cart_ID) references ShoppingCart(Cart_ID) on update cascade on delete cascade,
    foreign key (ISBN) references Book(ISBN) on update cascade on delete cascade,
    INDEX idx_shoppingcart_books_isbn (ISBN)
);

alter table `User` add constraint Wishlist foreign key (Wishlist_ID) 
references Wishlist(Wishlist_ID) on update cascade;

alter table `User` add constraint ShoppingCart foreign key (Cart_ID) 
references ShoppingCart(Cart_ID) on update cascade;

create table CreditCard (
    Card_ID int primary key,
    User_ID int,
    CardNumber char(16) unique not null,
    constraint card_num_check check(CardNumber not like '%[^0-9]%'), 
    constraint card_len_chk check(char_length(CardNumber) = 16),
    ExpirationDate varchar(5),
    constraint date_chk check(ExpirationDate rlike '^[0-9]{2}/[0-9]{2}$'),
    CVV char(3) not null,
    constraint cvv_num_chk check(CVV not like '%[^0-9]%'),
    constraint cvv_len_chk check(char_length(CVV) = 3),
    foreign key (User_ID) references `User`(User_ID) on update cascade on delete cascade
);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Publisher.txt' INTO TABLE Publisher FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Publisher_ID, PublisherName);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Author.txt' INTO TABLE Author FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Author_ID, Publisher_ID, FirstName, LastName, Biography);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Book.txt' INTO TABLE Book FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (ISBN, BookName, Author_ID, Publisher_ID, BookDescription, Genre, YearPublished, CopiesSold, Price);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/User.txt' INTO TABLE `User` FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (User_ID, Username, Email, Pass);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Review.txt' INTO TABLE Review FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Review_ID, ISBN, User_ID, `Text`, Rating, `Date`);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Wishlist.txt' INTO TABLE Wishlist FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Wishlist_ID, WishlistName, User_ID, ISBN);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/WishlistBook.txt' INTO TABLE wishlist_books FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Wishlist_ID, ISBN);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/ShoppingCart.txt' INTO TABLE ShoppingCart FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Cart_ID, User_ID);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/CartBook.txt' INTO TABLE shoppingcart_books FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Cart_ID, ISBN, Quantity);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/CreditCard.txt' INTO TABLE CreditCard FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Card_ID,  User_ID, CardNumber, ExpirationDate, CVV);