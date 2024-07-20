package app.bookstore.dto;

import app.bookstore.domain.Book;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;


@JsonPropertyOrder({"Book ID", "ISBN", "Book Title", "Description", "Year Published", 
"Author ID", "Publisher ID", "Genre", "Copies Sold", "Price", "Current Price"})
public class BookDTO {
    
    @JsonProperty("Book ID")
    private Long id;

    @JsonProperty("ISBN")
    private String isbn;

    @JsonProperty("Book Title")
    private String myTitle;

    @JsonProperty("Description")
    private String myDescription;

    @JsonProperty("Year Published")
    private int myYearPublished;

    @JsonProperty("Author ID")
    private Integer myAuthorId;

    @JsonProperty("Publisher ID")
    private Integer myPublisherId;

    @JsonProperty("Genre")
    private String myGenre;

    @JsonProperty("Copies Sold")
    private int myCopiesSold;

    @JsonProperty("Price")
    private float myPrice;
    
    @JsonProperty("Current Price")
    private float myCurrentPrice;

    public BookDTO(){};

    public BookDTO(Book book){
        this(book.getId(), book.getIsbn(), book.getTitle(), book.getDescription(), 
            book.getYearPublished(), book.getAuthor().getAuthorID(), book.getPublisher().getID(), 
            (book.getGenre() != null) ? book.getGenre().getLabel() : null, 
            book.getCopiesSold(), book.getPrice(), book.getSellingPrice());
    }

    private BookDTO(Long id, String isbn, String myTitle, String myDescription, int myYearPublished, 
            Integer myAuthorId, Integer myPublisherId, String myGenre, int myCopiesSold, float myPrice, float myCurrentPrice) {
        this.id = id;
        this.isbn = isbn;
        this.myTitle = myTitle;
        this.myDescription = myDescription;
        this.myYearPublished = myYearPublished;
        this.myAuthorId = myAuthorId;
        this.myPublisherId = myPublisherId;
        this.myGenre = myGenre;
        this.myCopiesSold = myCopiesSold;
        this.myPrice = myPrice;
        this.myCurrentPrice = myCurrentPrice;
        this.roundPrices();
    }

    //  Not Null Constructor
    public BookDTO(Long id, String isbn, String myTitle, Integer myAuthorId, Integer myPublisherId, 
        int myCopiesSold, float myPrice, float myCurrentPrice) {
                this(id, isbn, myTitle, null, 0000, myAuthorId, 
                    myPublisherId, null, myCopiesSold, myPrice, myCurrentPrice);
    }

    // Getters and setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getMyTitle() {
        return myTitle;
    }

    public void setMyTitle(String myTitle) {
        this.myTitle = myTitle;
    }

    public String getMyDescription() {
        return myDescription;
    }

    public void setMyDescription(String myDescription) {
        this.myDescription = myDescription;
    }

    public int getMyYearPublished() {
        return myYearPublished;
    }

    public void setMyYearPublished(int myYearPublished) {
        this.myYearPublished = myYearPublished;
    }

    public Integer getMyAuthorId() {
        return myAuthorId;
    }

    public void setMyAuthorId(Integer myAuthorId) {
        this.myAuthorId = myAuthorId;
    }

    public Integer getMyPublisherId() {
        return myPublisherId;
    }

    public void setMyPublisherId(Integer myPublisherId) {
        this.myPublisherId = myPublisherId;
    }

    public String getMyGenre() {
        return myGenre;
    }

    public void setMyGenre(String myGenre) {
        this.myGenre = myGenre;
    }

    public int getMyCopiesSold() {
        return myCopiesSold;
    }

    public void setMyCopiesSold(int myCopiesSold) {
        this.myCopiesSold = myCopiesSold;
    }

    public float getMyPrice() {
        return myPrice;
    }

    public void setMyPrice(float myPrice) {
        this.myPrice = myPrice;
    }
    
    public float getMyCurrentPrice() {
        return myCurrentPrice;
    }

    public void setMyCurrentPrice(float myCurrentPrice) {
        this.myCurrentPrice = myCurrentPrice;
    }
    
    public void roundPrices() {
        this.myPrice = roundToTwoDecimalPlaces(this.myPrice);
        this.myCurrentPrice = roundToTwoDecimalPlaces(this.myCurrentPrice);
    }

    private float roundToTwoDecimalPlaces(float value) {
        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
}