drop database if exists GeekText_Bookstore;
create schema GeekText_Bookstore;
use GeekText_Bookstore;

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
	Genre enum('Textbook', 'Academic/Report', 'Biography', 'How-to/Manual', 'Fantasy', 'Science Fiction', 'Action/Adventure', 'Historical', 'Fiction', 'Non-Fiction', 'Other') default 'Other',
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
	foreign key (Wishlist_ID) references Wishlist(Wishlist_ID) on update cascade,
    foreign key (Cart_ID) references ShoppingCart(Cart_ID) on update cascade
);

create table Review (
    ISBN char(13),
    User_ID int,
    `Text` varchar(500),
    Rating int,
    `Date` datetime,
    primary key (ISBN, User_ID),
    foreign key (ISBN) references Book(ISBN)
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

alter table `User` add constraint Wishlist foreign key (Wishlist) 
references Wishlist(Wishlist_ID) on update cascade;

alter table `User` add constraint ShoppingCart foreign key (ShoppingCart) 
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


LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Publisher.txt' INTO TABLE Publisher FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Publisher_ID, PublisherName);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Author.txt' INTO TABLE Author FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Author_ID, Publisher_ID, FirstName, LastName, Biography);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Book.txt' INTO TABLE Book FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (ISBN, BookName, Author_ID, Publisher_ID, BookDescription, Genre, YearPublished, CopiesSold, Price);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/User.txt' INTO TABLE `User` FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (User_ID, User_Name, Email, Pass);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Review.txt' INTO TABLE Review FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (ISBN, User_ID, `Text`, Rating, `Date`);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/Wishlist.txt' INTO TABLE Wishlist FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Wishlist_ID, WishlistName, User_ID, ISBN);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/WishlistBook.txt' INTO TABLE WishlistBook FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Wish_ID, ISBN);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/ShoppingCart.txt' INTO TABLE ShoppingCart FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Cart_ID, User_ID, ISBN);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/CartBook.txt' INTO TABLE CartBook FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Cart_ID, ISBN, Quantity);
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SQLData/CreditCard.txt' INTO TABLE CreditCard FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (Card_ID,  User_ID, CardNumber, ExpirationDate, CVV);


