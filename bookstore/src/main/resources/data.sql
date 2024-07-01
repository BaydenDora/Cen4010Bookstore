use geektext_bookstore;

INSERT INTO Publisher(Publisher_ID, PublisherName) VALUES
    (1, 'Penguin Random House'),
    (2, 'HarperCollins'),
    (3, 'Simon & Schuster');

INSERT INTO Author (Author_ID, Publisher_ID, FirstName, LastName, Biography) VALUES
    (1, 1, 'John', 'Smith', 'John Smith is an acclaimed author known for his thrilling novels.'),
    (2, 2, 'Jane', 'Doe', 'Jane Doe is a renowned writer of NonFiction works.'),
    (3, 1, 'Alice', 'Johnson', 'Alice Johnson writes compelling ScienceFiction stories.'),
    (4, 3, 'Bob', 'Brown', 'Bob Brown is a famous fantasy novelist.'),
    (5, 2, 'Carol', 'Davis', 'Carol Davis is a well-known biographer.');

INSERT INTO Book(ISBN, BookName, Author_ID, Publisher_ID, BookDescription, Genre, YearPublished, CopiesSold, Price, id) VALUES
    (9783161484100,	'Example Book Title',	1,	1,	'A thrilling adventure tale.',	'Fiction',	2023,	500000,	29.99, 1),
    (9781234567897,	'Another Book Title',	2,	2, 'An insightful historical narrative.', 'NonFiction',	2021,	250000,	19.99, 2),
    (9780140449136,	'Third Book Example',	3,	1,	'A captivating fantasy story.',	'ScienceFiction', 2019,	750000,	24.99, 3),
    (9780345391803,	'Fourth Book Here',	4,	3,	'A comprehensive how-to guide.',	'Fantasy', 2020,	100000, 34.99, 4),
    (9780307593699,	'Fifth Book Example',	1,	2,	'An inspiring biography.',	'Biography',	2022, 125000,	22.99, 5),
    (9780307593705,	'Sixth Book Sample',	2,	1,	'A gripping sci-fi novel.',	'Fiction',	2022,	300000,	27.99, 6),
    (9781566199094,	'Seventh Book',	3,	2,	'A detailed academic report.',	'NonFiction',	2018,	450000,	18.99, 7),
    (9780743273565,	'Eighth Book Title',	4,	3,	'A charming children\'s story.',	'ScienceFiction',	2021,	550000,	26.99, 8),
    (9780452284234,	'Ninth Book Here',	1,	2,	'An action-packed adventure.',	'Fantasy',	2020,	600000,	33.99, 9),
    (9780743273568,	'Tenth Book Example',	2,	1,	'A heartwarming autobiography.',	'Biography',	2023,	200000,	23.99, 10);

INSERT INTO `User` (User_ID, Username, Email, Pass, HomeAddress, Wishlist_ID, Cart_ID) VALUES
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

INSERT INTO CreditCard (Card_ID, User_ID, CardNumber, ExpirationDate, CVV) VALUES
    (1, 1, '1234567890123456', '12/25', '123'),
    (2, 2, '2345678901234567', '11/26', '234'),
    (3, 3, '3456789012345678', '10/27', '345'),
    (4, 4, '4567890123456789', '09/24', '456'),
    (5, 5, '5678901234567890', '08/25', '567'),
    (6, 6, '6789012345678901', '07/26', '678'),
    (7, 7, '7890123456789012', '06/27', '789');
