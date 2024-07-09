package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "ISBN", nullable = false, unique = true, length = 13)
    @JsonProperty("ISBN")
    private String ISBN;

    @Column(name = "BookName", nullable = false, length = 500)
    private String myTitle;

    @Column(name = "BookDescription", nullable = false, length = 1000)
    private String myDescription;

    @Column(name = "YearPublished", nullable = false, length = 4)
    private int myYearPublished;

    @ManyToOne
    @JoinColumn(name = "Author_ID", nullable = false)
    @JsonBackReference(value = "author-books")
    private Author myAuthor;

    @ManyToOne
    @JoinColumn(name = "Publisher_ID", nullable = false)
    @JsonBackReference(value = "publisher-books")
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
    
    @Column(name = "SellingPrice", nullable = false)
    private float mySellingPrice;
    
    private Float myDiscountPercent; 
    

    public Book() {
        setISBN("0000000000000");
        setTitle("The Book");
        setDescription("Book Description");
        setYearPublished(0);
        setGenre(Genre.TEXTBOOK);
        setCopiesSold(0);
        setPrice(0); 
        this.mySellingPrice = 0; 
    }

    public Book(String ISBN, String title, String description, int yearPublished, Author author, Publisher publisher, Genre genre, int copiesSold, float price) {
        setISBN(ISBN);
        setTitle(title);
        setDescription(description);
        setYearPublished(yearPublished);
        setAuthor(author);
        setPublisher(publisher);
        setGenre(genre);
        setCopiesSold(copiesSold);
        setPrice(price); 
        this.mySellingPrice = price; 
    }

    public Long getId() {
        return id;
    }

    @JsonProperty("ISBN")
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
    
    public float getSellingPrice() {
        if (this.mySellingPrice == 0) {
            return this.myPrice; // Return myPrice if mySellingPrice is not set
        }
        return mySellingPrice;
    }
    
    public Float getDiscount() { 
        return myDiscountPercent;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.myTitle = title;
        if (myTitle.trim().isEmpty()) myTitle = "Title";
    }

    public void setDescription(String description) {
        this.myDescription = description;
        if (myDescription.trim().isEmpty()) myDescription = "Description";
    }

    public void setYearPublished(int yearPublished) {
        this.myYearPublished = yearPublished;
    }

    public void setAuthor(Author author) {
        this.myAuthor = author;
    }

    public void setPublisher(Publisher publisher) {
        this.myPublisher = publisher;
    }

    public void setGenre(Genre genre) {
        this.myGenre = genre;
    }

    public void setCopiesSold(int copiesSold) {
        this.myCopiesSold = copiesSold;
        if (myCopiesSold < 0) myCopiesSold = 0;
    }

    public void setPrice(float price) {
        this.myPrice = price;
        if (myPrice < 0) myPrice = 0;
        if (this.mySellingPrice == 0) {
            this.mySellingPrice = price; // Set mySellingPrice when myPrice is set
        }
    }
    
    public void setSellingPrice(float price) {
        this.mySellingPrice = price;
        if (mySellingPrice < 0) mySellingPrice = 0; 
    }
    
    public void setDiscount(Float discountPercent) { 
        if (discountPercent < 0) discountPercent = (float) 0;
        this.myDiscountPercent = discountPercent;
        setSellingPrice(myPrice * (1 - discountPercent / 100));
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public void setWishlists(List<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }
}