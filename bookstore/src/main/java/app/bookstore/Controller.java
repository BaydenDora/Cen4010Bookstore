package app.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewBook (@RequestParam long myISBN, @RequestParam String myTitle) {
    Book n = new Book();
    n.setISBN(myISBN);
    n.setTitle(myTitle);
    bookRepository.save(n);
    return "Saved";
  }

  @PostMapping(path="/addCard")
  public @ResponseBody String addNewCard(@RequestParam long cardNumber,@RequestParam int expirationMonth,@RequestParam int expirationYear, @RequestParam int cvc) {
    CreditCard creditCard = new CreditCard();
    creditCard.setCardNumber(cardNumber);
    creditCard.setExpirationMonth(expirationMonth);
    creditCard.setExpirationYear(expirationYear);
    creditCard.setCVC(cvc);
    creditCardRepository.save(creditCard);
    return "Card Saved";
  }

  @PostMapping(path="/addPublisher")
  public @ResponseBody String addNewPublisher(@RequestParam long id, @RequestParam String name, @RequestParam String address) {
    Publisher publisher = new Publisher();
    publisher.setID(id);
    publisher.setName(name);
    publisherRepository.save(publisher);
    return "Publisher Saved";
  }

  @PostMapping(path="/addReview")
  public @ResponseBody String addNewReview(@RequestParam long id, @RequestParam String content, @RequestParam int rating, @RequestParam long bookId) {
    Review review = new Review();
    review.setId(id);
    review.setContent(content);
    review.setRating(rating);
    review.setBookId(bookId);
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

  @PostMapping(path="/addAuthor")
  public @ResponseBody String addNewAuthor (@RequestParam long id, @RequestParam String name) {
    Author author = new Author();
    author.setAuthorID(id);
    author.setName(name);
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