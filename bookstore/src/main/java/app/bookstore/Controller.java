package app.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class Controller {
  @Autowired
  private BookRepo bookRepository;

  @Autowired
  private CreditCardRepo creditCardRepository;

  @Autowired
  private PublisherRepo publisherRepository;

  @Autowired
  private ReviewRepo reviewRepository;

  @Autowired
  private UserRepo userRepository;

  @Autowired
  private AuthorRepo authorRepository;

  @Autowired
  private ShoppingCartRepo shoppingCartRepository;

  @Autowired
  private WishlistRepo wishlistRepository;

  @PostMapping(path="/addBook") // Map ONLY POST Requests
  public @ResponseBody String addNewBook (@RequestParam long myISBN, @RequestParam String myTitle,
                                          @RequestParam String myDescription, @RequestParam int myYearPublished,
                                          @RequestParam int authorId, @RequestParam long publisherId,
                                          @RequestParam int genreIndex, @RequestParam int myCopiesSold, 
                                          @RequestParam int myRating, @RequestParam double myPrice) {
    Book book = new Book();
    book.setISBN(myISBN);
    book.setTitle(myTitle);
    book.setDescription(myDescription);
    book.setYearPublished(myYearPublished);
    book.setGenre(Genre.fromInt(genreIndex));
    book.setCopiesSold(myCopiesSold);
    book.setRating(myRating);
    book.setPrice(myPrice);

    // Fetch and set Author and Publisher
    Author author = authorRepository.findById(authorId).orElse(null);
    Publisher publisher = publisherRepository.findById(publisherId).orElse(null);

    if (author != null){
      book.setAuthor(author);
    }

    if (publisher != null){
      book.setPublisher(publisher);
    }

    // Saves book to the repo
    bookRepository.save(book);
    return "Book Saved";
  }

  @PostMapping(path="/addCard")
  public @ResponseBody String addNewCard(@RequestParam String cardBrand, @RequestParam String cardHolder,
                                         @RequestParam long cardNumber, @RequestParam int expirationMonth,
                                         @RequestParam int expirationYear, @RequestParam int cvc,
                                         @RequestParam Long userId) {
    CreditCard creditCard = new CreditCard();
    creditCard.setCardBrand(cardBrand);
    creditCard.setCardHolder(cardHolder);
    creditCard.setCardNumber(cardNumber);
    creditCard.setExpirationMonth(expirationMonth);
    creditCard.setExpirationYear(expirationYear);
    creditCard.setCVC(cvc);

    // Fetch and set the User who owns the card based on userID

    User user = userRepository.findById(userId).orElse(null);
    if (user != null){
      creditCard.setUser(user);
    }
    else {
      return "User not found";
    }

    creditCardRepository.save(creditCard);
    return "Card Saved";
  }

  @PostMapping(path="/addPublisher") // removed address as it was not needed. Publisher class does not need address
  public @ResponseBody String addNewPublisher(@RequestParam long id, @RequestParam String name) {
    Publisher publisher = new Publisher();
    publisher.setID(id);
    publisher.setName(name);
    publisherRepository.save(publisher);
    return "Publisher Saved";
  }

  @PostMapping(path="/addReview")
  public @ResponseBody String addNewReview(@RequestParam int id, @RequestParam String commentText, 
                                           @RequestParam int comment, @RequestParam long bookId,
                                           @RequestParam long userId, @RequestParam String date) {
    Review review = new Review();
    review.setID(id);
    review.setComment(commentText);
    review.setComment(comment);

    reviewRepository.save(review);
    return "Review Saved";
  }

  @PostMapping(path="/addUser")
  public @ResponseBody String addNewUser (@RequestParam long id, @RequestParam String username, @RequestParam String password, @RequestParam String email) {
    User user = new User();
    user.setUserID(id);
    user.setUsername(username);
    user.setPassword(password);
    user.setEmailAddress(email);
    userRepository.save(user);
    return "User Saved";
  }

  @PostMapping(path="/addAuthor") // Seperated name into first and last as shown in Author class
  public @ResponseBody String addNewAuthor (@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String biography) {
    Author author = new Author();
    author.setAuthorID(id);
    author.setFirstName(firstName);
    author.setLastName(lastName);
    author.setBiography(biography);
    authorRepository.save(author);
    return "Author Saved";
  }

  @PostMapping(path="/addToCart")
  public @ResponseBody String addToCart (@RequestParam long id, @RequestParam Long userId, @RequestParam Long bookId) {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.setCartID(id);
    shoppingCart.setBooksInCart(booksInCart);
    shoppingCart.setBookID(bookId);
    shoppingCartRepository.save(shoppingCart);
    return "Added to Cart";
  }

  @PostMapping(path="/addToWishlist")
  public @ResponseBody String addToWishlist (@RequestParam long id, @RequestParam Long userId, @RequestParam Long bookId) {
    Wishlist wishlist = new Wishlist();
    wishlist.setWishlistID(id);
    wishlist.setUserID(userId);
    wishlist.setBookID(bookId);
    wishlistRepository.save(wishlist);
    return "Added to Wishlist";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  @GetMapping(path="/allCards")
  public @ResponseBody Iterable<CreditCard> getAllCards() {
    return creditCardRepository.findAll();
  }

  @GetMapping(path="/allPublishers")
  public @ResponseBody Iterable<Publisher> getAllPublishers() {
    return publisherRepository.findAll();
  }

  @GetMapping(path="/allReviews")
  public @ResponseBody Iterable<Review> getAllReviews() {
    return reviewRepository.findAll();
  }

  @GetMapping(path="/allUsers")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping(path="/allAuthors")
  public @ResponseBody Iterable<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  @GetMapping(path="/getCart")
  public @ResponseBody Iterable<ShoppingCart> getShoppingCart() {
    return shoppingCartRepository.findAll();
  }
  @GetMapping(path="/getWishlist")
  public @ResponseBody Iterable<Wishlist> getWishlist() {
    return wishlistRepository.findAll();
  }
}