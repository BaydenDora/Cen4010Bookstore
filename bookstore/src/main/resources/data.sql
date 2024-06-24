drop database if exists GeekText_bookstore;
create schema GeekText_bookstore;
use GeekText_bookstore;

create table Publisher (
	Publisher_ID int primary key,
    PublisherName varchar(50) unique not null
);

create table Author (
	Author_ID int primary key,
    Publisher_ID int,
    FirstName varchar(25) not null,
    LastName varchar(25) not null,
    Biography varchar(300),
    foreign key (Publisher_ID) references Publisher(Publisher_ID) 
		on update cascade on delete cascade
);

create table Book (
	ISBN char(13) primary key,
		constraint isbn_num_chk check(ISBN not like '%[^0-9]%'),
		constraint isbn_len_chk check(char_length(ISBN) = 13),
	BookName varchar(100) not null,
    Author_ID int,
    Publisher_ID int,
    BookDescription varchar(500),
	Genre enum('Textbook', 'Academic/Report', 'Biography', 'How-to/Manual', 
        'Fantasy', 'Science Fiction', 'Action/Adventure', 'Historical', 
        'Fiction', 'Non-Fiction', 'Other') default 'Other',
	YearPublished year,
	CopiesSold int unsigned not null,
	Price float not null,
    foreign key (Author_ID) references Author(Author_ID) on update cascade,
    foreign key (Publisher_ID) references Publisher(Publisher_ID) on update cascade
);

create table `User` (
	User_ID int primary key,
	User_Name varchar(50),
    Email varchar(50),
	Pass varchar(50),
    HomeAddress varchar(100),
    Wishlist_ID int,
    Cart_ID int
);

create table Review (
    ISBN char(13),
    User_ID int,
    `Text` varchar(500),
    Rating int,
    `Date` datetime,
    primary key (ISBN, User_ID),
    foreign key (ISBN) references Book(ISBN),
    foreign key (User_ID) references `User`(User_ID)
);

create table Wishlist (
	Wishlist_ID int,
    WishlistName varchar (25),
    User_ID int,
    ISBN char(13),
    primary key (Wishlist_ID, WishlistName),
	foreign key (User_ID) references `User`(User_ID) on update cascade,
    foreign key (ISBN) references Book(ISBN) on update cascade
);

create table ShoppingCart (
	Cart_ID int primary key,
    User_ID int,
    ISBN char(13),
    foreign key (User_ID) references `User`(User_ID) on update cascade,
    foreign key (ISBN) references Book(ISBN) on update cascade
);

alter table `User` add constraint Wishlist foreign key (Wishlist_ID) 
references Wishlist(Wishlist_ID) on update cascade;

alter table `User` add constraint ShoppingCart foreign key (Cart_ID) 
references ShoppingCart(Cart_ID) on update cascade;

create table CreditCard (
	Card_ID int primary key,
    User_ID int,
    CardNumber char(16) unique,
		constraint card_num_check check(CardNumber not like '%[^0-9]%'), 
		constraint card_len_chk check(char_length(CardNumber) = 16),
    ExpirationDate varchar(5),
		constraint date_chk check(ExpirationDate rlike '^[0-9]{2}/[0-9]{2}$'),
    CVV char(3) not null,
		constraint cvv_num_chk check(CVV not like '%[^0-9]%'),
		constraint cvv_len_chk check(char_length(CVV) = 3),
    foreign key (User_ID) references `User`(User_ID) 
		on update cascade on delete cascade
);


INSERT INTO Publisher(Publisher_ID, PublisherName) VALUES
    (1, 'Penguin Random House'),
    (2, 'HarperCollins'),
    (3, 'Simon & Schuster');

INSERT INTO Author (Author_ID, Publisher_ID, FirstName, LastName, Biography) VALUES
    (1, 1, 'John', 'Smith', 'John Smith is an acclaimed author known for his thrilling novels.'),
    (2, 2, 'Jane', 'Doe', 'Jane Doe is a renowned writer of non-fiction works.'),
    (3, 1, 'Alice', 'Johnson', 'Alice Johnson writes compelling science fiction stories.'),
    (4, 3, 'Bob', 'Brown', 'Bob Brown is a famous fantasy novelist.'),
    (5, 2, 'Carol', 'Davis', 'Carol Davis is a well-known biographer.');

INSERT INTO Book(ISBN, BookName, Author_ID, Publisher_ID, BookDescription, Genre, YearPublished, CopiesSold, Price) VALUES
    (9783161484100,	'Example Book Title',	1,	1,	'A thrilling adventure tale.',	'Fiction',	2023,	500000,	29.99),
    (9781234567897,	'Another Book Title',	2,	2, 'An insightful historical narrative.',	'Non-Fiction',	2021,	250000,	19.99),
    (9780140449136,	'Third Book Example',	3,	1,	'A captivating fantasy story.',	'Science Fiction', 2019,	750000,	24.99),
    (9780345391803,	'Fourth Book Here',	4,	3,	'A comprehensive how-to guide.',	'Fantasy', 2020,	100000, 34.99),
    (9780307593699,	'Fifth Book Example',	1,	2,	'An inspiring biography.',	'Biography',	2022, 125000,	22.99),
    (9780307593705,	'Sixth Book Sample',	2,	1,	'A gripping sci-fi novel.',	'Fiction',	2022,	300000,	27.99),
    (9781566199094,	'Seventh Book',	3,	2,	'A detailed academic report.',	'Non-Fiction',	2018,	450000,	18.99),
    (9780743273565,	'Eighth Book Title',	4,	3,	'A charming children\'s story.',	'Science Fiction',	2021,	550000,	26.99),
    (9780452284234,	'Ninth Book Here',	1,	2,	'An action-packed adventure.',	'Fantasy',	2020,	600000,	33.99),
    (9780743273568,	'Tenth Book Example',	2,	1,	'A heartwarming autobiography.',	'Biography',	2023,	200000,	23.99);

INSERT INTO `User` (User_ID, User_Name, Email, Pass, HomeAddress, Wishlist_ID, Cart_ID) VALUES
    (1, 'user1', 'user1@example.com', 'password1', NULL, NULL, NULL),
    (2, 'user2', 'user2@example.com', 'password2', NULL, NULL, NULL),
    (3, 'user3', 'user3@example.com', 'password3', NULL, NULL, NULL),
    (4, 'user4', 'user4@example.com', 'password4', NULL, NULL, NULL),
    (5, 'user5', 'user5@example.com', 'password5', NULL, NULL, NULL),
    (6, 'user6', 'user6@example.com', 'password6', NULL, NULL, NULL),
    (7, 'user7', 'user7@example.com', 'password7', NULL, NULL, NULL),
    (8, 'user8', 'user8@example.com', 'password8', NULL, NULL, NULL),
    (9, 'user9', 'user9@example.com', 'password9', NULL, NULL, NULL),
    (10, 'user10', 'user10.@example.com', 'password10', NULL, NULL, NULL);

INSERT INTO Review (ISBN, User_ID, `Text`, Rating, `Date`) VALUES
    ('9783161484100', 1, 'An excellent read!', 5, '2023-01-15 14:23:00'),
    ('9781234567897', 2, 'Very informative and well-written.', 4, '2023-02-20 09:45:00'),
    ('9780140449136', 3, 'A bit dull in parts.', 3, '2023-03-05 12:10:00'),
    ('9780345391803', 4, 'Loved the characters and plot.', 5, '2023-04-10 18:30:00'),
    ('9780307593699', 5, 'Not what I expected.', 2, '2023-05-22 11:00:00'),
    ('9780307593705', 6, 'A masterpiece!', 5, '2023-06-15 16:45:00'),
    ('9781566199094', 7, 'Great for a quick read.', 4, '2023-07-01 08:20:00'),
    ('9780743273565', 8, 'Couldn\'t put it down.', 5, '2023-08-11 10:15:00'),
    ('9780452284234', 9, 'Very dry and technical.', 3, '2023-09-30 13:55:00'),
    ('9780743273568', 10, 'An absolute page-turner.', 5, '2023-10-05 19:40:00');

INSERT INTO Wishlist (Wishlist_ID, WishlistName, User_ID, ISBN) VALUES
    (1, 'Summer Reading', 1, '9783161484100'),
    (2, 'Holiday Wishlist', 2, '9781234567897'),
    (3, 'SciFi Favorites', 3, '9780140449136'),
    (4, 'History Buff', 4, '9780345391803'),
    (5, 'Must Reads', 5, '9780307593699'),
    (6, 'Fantasy Collection', 6, '9780307593705'),
    (7, 'Action Adventure', 7, '9781566199094'),
    (8, 'Classics', 1, '9780743273565'),
    (9, 'Best Biographies', 2, '9780452284234'),
    (10, 'To Read', 3, '9780743273568');

INSERT INTO ShoppingCart (Cart_ID, User_ID, ISBN) VALUES
    (1, 1, '9783161484100'),
    (2, 2, '9781234567897'),
    (3, 3, '9780140449136'),
    (4, 4, '9780345391803'),
    (5, 5, '9780307593699'),
    (6, 6, '9780307593705'),
    (7, 7, '9781566199094'),
    (8, 1, '9780743273565'),
    (9, 2, '9780452284234'),
    (10, 3, '9780743273568');

INSERT INTO CreditCard (Card_ID, User_ID, CardNumber, ExpirationDate, CVV) VALUES
    (1, 1, '1234567890123456', '12/25', '123'),
    (2, 2, '2345678901234567', '11/26', '234'),
    (3, 3, '3456789012345678', '10/27', '345'),
    (4, 4, '4567890123456789', '09/24', '456'),
    (5, 5, '5678901234567890', '08/25', '567'),
    (6, 6, '6789012345678901', '07/26', '678'),
    (7, 7, '7890123456789012', '06/27', '789');


-- LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Publisher.txt' INTO TABLE Publisher FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Publisher_ID, PublisherName);
-- LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Author.txt' INTO TABLE Author FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Author_ID, Publisher_ID, FirstName, LastName, Biography);
-- LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Book.txt' INTO TABLE Book FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (ISBN, BookName, Author_ID, Publisher_ID, BookDescription, Genre, YearPublished, CopiesSold, Price);
-- LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/User.txt' INTO TABLE `User` FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (User_ID, User_Name, Email, Pass);
-- LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Review.txt' INTO TABLE Review FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (ISBN, User_ID, `Text`, Rating, `Date`);
-- LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Wishlist.txt' INTO TABLE Wishlist FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Wishlist_ID, WishlistName, User_ID, ISBN);
-- LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/WishlistBook.txt' INTO TABLE WishlistBook FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Wish_ID, ISBN);
-- LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/ShoppingCart.txt' INTO TABLE ShoppingCart FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Cart_ID, User_ID, ISBN);
-- LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/CartBook.txt' INTO TABLE CartBook FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Cart_ID, ISBN, Quantity);
-- LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/CreditCard.txt' INTO TABLE CreditCard FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Card_ID,  User_ID, CardNumber, ExpirationDate, CVV);   