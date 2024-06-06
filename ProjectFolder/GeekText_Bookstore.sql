{
    "type": "MySQLNotebook",
    "version": "1.0",
    "caption": "GeekText_Bookstore.sql",
    "content": "drop database if exists GeekText_Bookstore;\ncreate schema GeekText_Bookstore;\nuse GeekText_Bookstore;\n\ncreate table Publisher (\n\tPublisher_ID int primary key,\n    PublisherName varchar(50) unique not null\n);\n\ncreate table Author (\n\tAuthor_ID int primary key,\n    Publisher_ID int,\n    FirstName varchar(25) not null,\n    LastName varchar(25) not null,\n    Biography varchar(300),\n    foreign key (Publisher_ID) references Publisher(Publisher_ID) \n\t\ton update cascade on delete cascade\n);\n\ncreate table Book (\n\tISBN char(13) primary key,\n\t\tconstraint isbn_num_chk check(ISBN not like '%[^0-9]%'),\n\t\tconstraint isbn_len_chk check(char_length(ISBN) = 13),\n\tBookName varchar(100) not null,\n    Author_ID int,\n    Publisher_ID int,\n    BookDescription varchar(500),\n\tGenre enum('Textbook', 'Academic/Report', 'Biography/Autobiography', 'How-to/Manual', \n\t\t'Fantasy/SciFi', 'Action/Adventure', 'Historical', 'Other') default 'Other',\n\tYearPublished year,\n\tCopiesSold int unsigned not null,\n\tPrice float not null,\n    foreign key (Author_ID) references Author(Author_ID) on update cascade,\n    foreign key (Publisher_ID) references Publisher(Publisher_ID) on update cascade\n);\n\ncreate table `User` (\n\tUser_ID int primary key,\n\tUser_Name varchar(50),\n\tPass varchar(50),\n\tWishlist int,\n\tShoppingCart int\n);\n\ncreate table Review (\n    ISBN char(13),\n    User_ID int,\n    `Text` varchar(500),\n    Rating int,\n    `Date` datetime,\n    primary key (ISBN, User_ID),\n    foreign key (ISBN) references Book(ISBN)\n);\n\ncreate table Wishlist (\n\tWishlist_ID int,\n    WishlistName varchar (25),\n    User_ID int,\n    ISBN char(13),\n    primary key (Wishlist_ID, WishlistName),\n\tforeign key (User_ID) references `User`(User_ID) on update cascade,\n    foreign key (ISBN) references Book(ISBN) on update cascade\n);\n\ncreate table WishlistBook (\n\tWish_ID int,\n\tISBN char(13),\n\tprimary key (Wish_ID, ISBN),\n    foreign key (Wish_ID) references Wishlist(Wishlist_ID) on update cascade,\n    foreign key (ISBN) references Book(ISBN) on update cascade\n);\n\ncreate table ShoppingCart (\n\tCart_ID int primary key,\n    User_ID int,\n    ISBN char(13),\n    foreign key (User_ID) references `User`(User_ID) on update cascade,\n    foreign key (ISBN) references Book(ISBN) on update cascade\n);\n\ncreate table CartBook (\n\tCart_ID int,\n\tISBN char(13),\n\tQuantity int,\n\tprimary key (Cart_ID, ISBN),\n    foreign key (Cart_ID) references ShoppingCart(Cart_ID) on update cascade,\n    foreign key (ISBN) references Book(ISBN) on update cascade on delete cascade\n);\n\nalter table `User` add constraint Wishlist foreign key (Wishlist) \nreferences Wishlist(Wishlist_ID) on update cascade;\n\nalter table `User` add constraint ShoppingCart foreign key (ShoppingCart) \nreferences ShoppingCart(Cart_ID) on update cascade;\n\ncreate table CreditCard (\n\tCard_ID int primary key,\n    User_ID int,\n    CardNumber char(16) unique,\n\t\tconstraint card_num_check check(CardNumber not like '%[^0-9]%'), \n\t\tconstraint card_len_chk check(char_length(CardNumber) = 16),\n    ExpirationDate varchar(5),\n\t\tconstraint date_chk check(ExpirationDate rlike '^[0-9]{2}/[0-9]{2}$'),\n    CVV char(3) not null,\n\t\tconstraint cvv_num_chk check(CVV not like '%[^0-9]%'),\n\t\tconstraint cvv_len_chk check(char_length(CVV) = 3),\n    foreign key (User_ID) references `User`(User_ID) \n\t\ton update cascade on delete cascade\n);\n\n",
    "options": {
        "tabSize": 4,
        "indentSize": 4,
        "insertSpaces": true,
        "defaultEOL": "LF",
        "trimAutoWhitespace": true
    },
    "viewState": null,
    "contexts": [
        {
            "state": {
                "start": 1,
                "end": 111,
                "language": "mysql",
                "currentHeight": 180,
                "statements": [
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 0,
                            "length": 43
                        },
                        "contentStart": 1,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 43,
                            "length": 34
                        },
                        "contentStart": 44,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 77,
                            "length": 24
                        },
                        "contentStart": 78,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 101,
                            "length": 106
                        },
                        "contentStart": 103,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 207,
                            "length": 280
                        },
                        "contentStart": 209,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 487,
                            "length": 682
                        },
                        "contentStart": 489,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 1169,
                            "length": 128
                        },
                        "contentStart": 1171,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 1297,
                            "length": 202
                        },
                        "contentStart": 1299,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 1499,
                            "length": 290
                        },
                        "contentStart": 1501,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 1789,
                            "length": 233
                        },
                        "contentStart": 1791,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 2022,
                            "length": 229
                        },
                        "contentStart": 2024,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 2251,
                            "length": 262
                        },
                        "contentStart": 2253,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 2513,
                            "length": 120
                        },
                        "contentStart": 2515,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 2633,
                            "length": 128
                        },
                        "contentStart": 2635,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 2761,
                            "length": 569
                        },
                        "contentStart": 2763,
                        "state": 0
                    },
                    {
                        "delimiter": ";",
                        "span": {
                            "start": 3330,
                            "length": 2
                        },
                        "contentStart": 3329,
                        "state": 3
                    }
                ]
            },
            "data": []
        }
    ]
}