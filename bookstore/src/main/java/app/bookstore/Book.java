package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id; // Internal ID

    @Column(name = "ISBN", nullable = false, unique = true, length = 13)
    private String ISBN; // ISBNs are 13-digit numbers

    @Column(name = "BookName", nullable = false, length = 500)
    private String myTitle;

    @Column(name = "BookDescription", nullable = false, length = 1000)
    private String myDescription;

    @Column(name = "YearPublished", nullable = false, length = 4)
    private int myYearPublished;

    @ManyToOne
    @JoinColumn(name ="Author_ID", nullable = false)
	@JsonManagedReference
    private Author myAuthor;

    @ManyToOne
    @JoinColumn(name ="Publisher_ID", nullable = false)
    private Publisher myPublisher;

	@JsonIgnore
    @OneToMany(mappedBy = "myBook")
    private List<Review> reviews = new ArrayList<>();

	@JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "shoppingcart_books",
        joinColumns = @JoinColumn(name = "ISBN", columnDefinition = "varchar(13)"),
        inverseJoinColumns = @JoinColumn(name = "Cart_ID")
    )
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

	@JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "wishlist_books",
        joinColumns = @JoinColumn(name = "ISBN", columnDefinition = "varchar(13)"),
        inverseJoinColumns = @JoinColumn(name = "wishlist_id")
    )
    private List<Wishlist> wishlists = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "Genre", nullable = false)
    private Genre myGenre;

    @Column(name = "CopiesSold", nullable = false)
    private int myCopiesSold;

    @Column(name = "Price", nullable = false)
    private float myPrice;

    // No-Arg Constructor
    public Book() {
        setISBN ("0000000000000"); // ISBNs are 13-digit numbers
        setTitle ("The Book");
        setDescription ("Book Description");
        setYearPublished (0);
        setAuthor (new Author());
        setGenre (Genre.TEXTBOOK);
        setPublisher (new Publisher());
        setCopiesSold (0);
        setPrice (0);
    }

    // Constructor
    public Book (String ISBN, String title, String description, int yearPublished, Author author, Publisher publisher, Genre genre, int copiesSold, float price) {
        setISBN (ISBN);
        setTitle (title);
        setDescription (description);
        setYearPublished (yearPublished);
        setAuthor (author);
        setPublisher (publisher);
        setGenre (genre);
        setCopiesSold (copiesSold);
        setPrice (price);
    }

    // Copy Constructor
    public Book (Book cloneBook) {
        setISBN (cloneBook.getISBN());
        setTitle (cloneBook.getTitle());
        setDescription (cloneBook.getDescription());
        setYearPublished (cloneBook.getYearPublished());
        setAuthor (cloneBook.getAuthor());
        setPublisher (cloneBook.getPublisher());
        setGenre (cloneBook.getGenre());
        setCopiesSold (cloneBook.getCopiesSold());
        setPrice (cloneBook.getPrice());
    }

    // Getters
    public Long getID(){
        return id;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return myTitle;
    }

    public String getDescription() {
        return myDescription;
    }

    public int getYearPublished() {
        return myYearPublished;
    }

    public Author getAuthor() {
        return myAuthor;
    }

    public Publisher getPublisher() {
        return myPublisher;
    }

    public Genre getGenre() {
        return myGenre;
    }

    public int getCopiesSold() {
        return myCopiesSold;
    }

    public float getPrice() {
        return myPrice;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public List<Wishlist> getWishlists() {
        return wishlists;
    }

    // Setters
    protected void setID(Long id) {
        this.id = id;
    }

    protected void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    protected void setTitle(String title) {
        myTitle = title;
        if (myTitle.trim().isEmpty()) myTitle = "Title";
    }

    protected void setDescription(String description) {
        myDescription = description;
        if (myDescription.trim().isEmpty()) myDescription = "Description";
    }

    protected void setYearPublished(int yearPublished) {
        myYearPublished = yearPublished;
    }

    protected void setAuthor (Author author) {
        myAuthor = author;
    }

    protected void setPublisher (Publisher publisher) {
        myPublisher = publisher;
    }

    protected void setGenre (Genre genre) {
        myGenre = genre;
    }

    protected void setCopiesSold (int copiesSold) {
        myCopiesSold = copiesSold;
        if (myCopiesSold < 0) myCopiesSold = 0;
    }

    protected void setPrice (float price) {
        myPrice = price;
        if (myPrice < 0) myPrice = 0;
    }

    protected void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    protected void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    protected void setWishlists(List<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }
}