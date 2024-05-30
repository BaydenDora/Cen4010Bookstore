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
	ISBN char(13) primary key check(CVV not like '%[^0-9]%'),
		constraint cvv_len_chk check(char_length(CVV) = 3),
	BookName varchar(100) not null,
    Author_ID int,
    Pubisher_ID int,
    BookDescription varchar(500),
	Genre enum('Textbook', 'Academic/Report', 'Biography/Autobiography', 'How-to/Manual', 
		'Fantasy/SciFi', 'Action/Adventure', 'Historical', 'Other') default 'Other',
	YearPublished year,
	CopiesSold int unsigned not null,
	Price float unsigned not null,
    foreign key (Author_ID) references Author(Author_ID) on update cascade,
    foreign key (Publisher_ID) references Publisher(Publisher_ID) on update cascade
);

create table Review (
    ISBN char(13) check(CVV not like '%[^0-9]%'),
		constraint cvv_len_chk check(char_length(CVV) = 3),
    User_ID int,
    `Text` varchar(500),
    Rating int,
    `Date` datetime,
    primary key (ISBN, User_ID),
    foreign key (User_ID) references `User`(User_ID) 
		on update cascade on delete set null
);

create table `User` (
	User_ID int primary key,
	User_Name varchar(50),
	Pass varchar(50),
	Wishlists int,
	ShoppingCart int,
	foreign key (Wishlists) references Wishlists(Wishlist_ID) on update cascade,
    foreign key (ShoppingCart) references ShoppingCart(Cart_ID) on update cascade
);

create table Wishlist (
	Wishlist_ID int,
    WishlistName varchar (25),
    User_ID int,
    ISBN bigint,
    primary key (Wishlist_ID, WishlistName),
	foreign key (User_ID) references `User`(User_ID) on update cascade,
    foreign key (ISBN) references Book(ISBN) on update cascade
);

create table WishlistBook (
	Wish_ID int,
	ISBN bigint,
	primary key (Wish_ID, ISBN),
    foreign key (Wish_ID) references Wishlist(Wishlist_ID) on update cascade,
    foreign key (ISBN) references Book(ISBN) on update cascade
);

create table ShoppingCart (
	Cart_ID int primary key,
    User_ID int,
    ISBN bigint,
    foreign key (User_ID) references `User`(User_ID) on update cascade,
    foreign key (ISBN) references Book(ISBN) on update cascade
);

create table CartBook (
	Cart_ID int,
	ISBN bigint,
	Quantity int,
	primary key (Cart_ID, ISBN),
    foreign key (Cart_ID) references ShoppingCart(Cart_ID) on update cascade,
    foreign key (ISBN) references Book(ISBN) on update cascade on delete cascade
);

create table CreditCard (
	Card_ID int primary key,
    User_ID int,
    CardNumber char(16) unique check(CardNumber not like '%[^0-9]%'), 
		constraint card_len_chk check(char_length(CardNumber) = 16),
    ExpirationDate datetime not null,
    CVV char(3) not null check(CVV not like '%[^0-9]%'),
		constraint cvv_len_chk check(char_length(CVV) = 3),
    foreign key (User_ID) references `User`(User_ID) 
		on update cascade on delete cascade
);

