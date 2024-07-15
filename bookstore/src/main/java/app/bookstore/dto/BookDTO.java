package app.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BookDTO {
    private Long id;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("myTitle")
    private String myTitle;

    @JsonProperty("myDescription")
    private String myDescription;

    @JsonProperty("myYearPublished")
    private int myYearPublished;

    @JsonProperty("myAuthorId")
    private Integer myAuthorId;

    @JsonProperty("myPublisherId")
    private Integer myPublisherId;

    @JsonProperty("myGenre")
    private String myGenre;

    @JsonProperty("myCopiesSold")
    private int myCopiesSold;

    @JsonProperty("myPrice")
    private float myPrice;
    
    @JsonProperty("myCurrentPrice")
    private float myCurrentPrice;

    public BookDTO(){};

    public BookDTO(Long id, String isbn, String myTitle, String myDescription, int myYearPublished, 
            Integer myAuthorId, Integer myPublisherId, String myGenre, int myCopiesSold, float myPrice) {
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
    }

    //  Not Null Constructor
    public BookDTO(Long id, String isbn, String myTitle, Integer myAuthorId, Integer myPublisherId, 
        int myCopiesSold, float myPrice) {
                this(id, isbn, myTitle, null, 0000, myAuthorId, 
                    myPublisherId, null, myCopiesSold, myPrice);
    }

    // Getters and setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
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