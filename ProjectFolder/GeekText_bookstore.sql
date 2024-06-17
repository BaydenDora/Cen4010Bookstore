drop database if exists GeekText_bookstore;
create schema GeekText_bookstore;
use GeekText_bookstore;

create user if not exists 'user'@'localhost' identified by 'password';
grant all on GeekText_bookstore.* to 'user'@'localhost';

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
	Genre enum('Textbook', 'Academic/Report', 'Biography/Autobiography', 'How-to/Manual', 
		'Fantasy/SciFi', 'Action/Adventure', 'Historical', 'Other') default 'Other',
	YearPublished year,
	CopiesSold int unsigned not null,
	Price float not null,
    foreign key (Author_ID) references Author(Author_ID) on update cascade,
    foreign key (Publisher_ID) references Publisher(Publisher_ID) on update cascade
);

create table `User` (
	User_ID int primary key,
	User_Name varchar(50) not null,
	Password varchar(50) not null,
    Email varchar(100) not null,
    HomeAddress varchar(100),
	Wishlist int,
	ShoppingCart int
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

create table WishlistBook (
	Wish_ID int,
	ISBN char(13),
	primary key (Wish_ID, ISBN),
    foreign key (Wish_ID) references Wishlist(Wishlist_ID) on update cascade,
    foreign key (ISBN) references Book(ISBN) on update cascade
);

create table ShoppingCart (
	Cart_ID int primary key,
    User_ID int,
    ISBN char(13),
    foreign key (User_ID) references `User`(User_ID) on update cascade,
    foreign key (ISBN) references Book(ISBN) on update cascade
);

create table CartBook (
	Cart_ID int,
	ISBN char(13),
	Quantity int,
	primary key (Cart_ID, ISBN),
    foreign key (Cart_ID) references ShoppingCart(Cart_ID) on update cascade,
    foreign key (ISBN) references Book(ISBN) on update cascade on delete cascade
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