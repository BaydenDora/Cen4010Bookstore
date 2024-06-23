package app.bookstore.dto;

public class BookDTO {
    private Long id;
    private String ISBN;
    private String myTitle;
    private String myDescription;
    private int myYearPublished;
    private Integer myAuthorId;  // Changed from Long to Integer
    private Integer myPublisherId;  // Changed from Long to Integer
    private String myGenre;
    private int myCopiesSold;
    private float myPrice;

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