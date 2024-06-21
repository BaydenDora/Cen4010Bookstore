package app.bookstore;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.Year;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "book")
public class Book 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String ISBN; // ISBNs are 13-digit numbers

	@Column(name = "BookName", nullable = false, length = 500)
	private String myTitle;
	
	@Column(name = "BookDescription", nullable = false, length = 1000)
	private String myDescription;
	
	@Column(name = "YearPublished", nullable = false, length = 4)
	private Year myYearPublished;
	
	@ManyToOne
	@JoinColumn(name ="Author_ID", nullable = false)
	private Author myAuthor;
	
	@ManyToOne
	@JoinColumn(name ="Publisher_ID", nullable = false)
	private Publisher myPublisher;
	
	@OneToMany(mappedBy = "ISBN")
    private List<Review> reviews = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
        name = "book_shopping_cart",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "shopping_cart_id")
    )
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
        name = "book_wishlist",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "wishlist_id")
    )
    private List<Wishlist> wishlists = new ArrayList<>();
	
	@Column(name = "Genre", nullable = false, length = 100)
	private Genre myGenre; //enum take examples from MySQL

	@Column(name = "CopiesSold", nullable = false, length = 1000)
	private int myCopiesSold;
	
	@Column(name = "Review", nullable = false, length = 100)
	private int myRating; // Ratings are item based need class for reviews
	
	@Column(name = "Price", nullable = false, length = 100)
	private float myPrice;
	
	// No-Arg Constructor
	public Book()
	{
		setISBN ("0000000000000"); // ISBNs are 13-digit numbers
		setTitle ("The Book");
		setDescription ("Book Description");
		setYearPublished (Year.of(0000));
		setAuthor (new Author());
		setGenre (Genre.TEXTBOOK);
		setPublisher (new Publisher());
		setCopiesSold (0);
		setRating (0);
		setPrice (00);
	}
	
	
	
	// Constructor
	public Book (String ISBN, String title, String description, Year yearPublished, Author author, Publisher publisher, Genre genre, int copiesSold, int rating, float price)
	{
		setISBN (ISBN);
		setTitle (title);
		setDescription (description);
		setYearPublished (yearPublished);
		setAuthor (author);
		setPublisher (publisher);
		setGenre (genre);
		setCopiesSold (copiesSold);
		setRating (rating);
		setPrice (price);
	}
	
	// Copy Constructor
	public Book (Book cloneBook)
	{
		setISBN (cloneBook.getISBN());
		setTitle (cloneBook.getTitle());
		setDescription (cloneBook.getDescription());
		setYearPublished (cloneBook.getYearPublished());
		setAuthor (cloneBook.getAuthor());
		setPublisher (cloneBook.getPublisher());
		setGenre (cloneBook.getGenre());
		setCopiesSold (cloneBook.getCopiesSold());
		setRating (cloneBook.getRating());
		setPrice (cloneBook.getPrice());
	}
	
	
	
	// Getters
	public String getISBN()
	{
		return ISBN;
	}
	
	public String getTitle()
	{
		return myTitle;
	}
	
	public String getDescription()
	{
		return myDescription;
	}
	
	public Year getYearPublished()
	{
		return myYearPublished;
	}
	
	public Author getAuthor()
	{
		return myAuthor;
	}
	
	public Publisher getPublisher()
	{
		return myPublisher;
	}
	
	public Genre getGenre()
	{
		return myGenre;
	}
	
	public int getCopiesSold()
	{
		return myCopiesSold;
	}
	
	public int getRating()
	{
		return myRating;
	}
	
	public float getPrice()
	{
		return myPrice;
	}

	public List<Review> getReviews()
    {
        return reviews;
    }

	public List<ShoppingCart> getShoppingCarts()
    {
        return shoppingCarts;
    }
    
    public List<Wishlist> getWishlists()
    {
        return wishlists;
    }
	
	
	// Setters
	protected void setISBN(String ISBN)
	{
		this.ISBN = ISBN;
	}
	
	protected void setTitle(String title)
	{
		myTitle = title;
		if (myTitle.trim() == "" || myTitle == null) myTitle = "Title";
	}
	
	protected void setDescription(String description)
	{
		myDescription = description;
		if (myDescription.trim() == "" || myDescription == null) myDescription = "Description";
	}
	
	protected void setYearPublished(Year yearPublished)
	{
		myYearPublished = yearPublished;
	}
	
	protected void setAuthor (Author author)
	{
		myAuthor = author;
	}
		
	protected void setPublisher (Publisher publisher)
	{
		myPublisher = publisher;
	}
	
	protected void setGenre (Genre genre)
	{
		myGenre = genre;
	}
	
	protected void setCopiesSold (int copiesSold)
	{
		myCopiesSold = copiesSold;
		if (myCopiesSold < 0) myCopiesSold = 0;
	}
		
	protected void setRating (int rating)
	{
		myRating = rating;
		if (myRating > 10) myRating = 10;
		if (myRating < 0) myRating = 0;
	}
	
	protected void setPrice (float price)
	{
		myPrice = price;
		if (myPrice < 0) myPrice = 0;
	}

	protected void setReviews(List<Review> reviews)
    {
        this.reviews = reviews;
    }
    
    protected void setShoppingCarts(List<ShoppingCart> shoppingCarts)
    {
        this.shoppingCarts = shoppingCarts;
    }
    
    protected void setWishlists(List<Wishlist> wishlists)
    {
        this.wishlists = wishlists;
    }
}