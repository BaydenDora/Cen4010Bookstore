package app.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDTO {
    private Long id;

    @JsonProperty("ISBN")
    private String ISBN;

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

    public BookDTO(){};

    public BookDTO(Long id, String ISBN, String myTitle, String myDescription, int myYearPublished, 
            Integer myAuthorId, Integer myPublisherId, String myGenre, int myCopiesSold, float myPrice) {
        this.id = id;
        this.ISBN = ISBN;
        this.myTitle = myTitle;
        this.myDescription = myDescription;
        this.myYearPublished = myYearPublished;
        this.myAuthorId = myAuthorId;
        this.myPublisherId = myPublisherId;
        this.myGenre = myGenre;
        this.myCopiesSold = myCopiesSold;
        this.myPrice = myPrice;
    }

    // Getters and setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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
}
