package app.bookstore.dto;

import java.util.List;

public class PublisherDTO {
    private int Publisher_ID;
    private String PublisherName;
    private List<Integer> authorIds;

    // Getters and setters

    public int getPublisher_ID() {
        return Publisher_ID;
    }

    public void setPublisher_ID(int publisher_ID) {
        Publisher_ID = publisher_ID;
    }

    public String getPublisherName() {
        return PublisherName;
    }

    public void setPublisherName(String publisherName) {
        PublisherName = publisherName;
    }

    public List<Integer> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<Integer> authorIds) {
        this.authorIds = authorIds;
    }
}
