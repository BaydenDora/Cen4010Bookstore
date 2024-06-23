package app.bookstore.dto;

import java.util.List;

public class AuthorDTO {
    private int Author_ID;
    private String FirstName;
    private String LastName;
    private String Biography;
    private List<Integer> publisherIds;

    // Getters and setters

    public int getAuthor_ID() {
        return Author_ID;
    }

    public void setAuthor_ID(int author_ID) {
        Author_ID = author_ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getBiography() {
        return Biography;
    }

    public void setBiography(String biography) {
        Biography = biography;
    }

    public List<Integer> getPublisherIds() {
        return publisherIds;
    }

    public void setPublisherIds(List<Integer> publisherIds) {
        this.publisherIds = publisherIds;
    }
}
