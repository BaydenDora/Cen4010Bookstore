package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Publisher_ID")
    private int Publisher_ID;

    @Column(name = "PublisherName", nullable = false, length = 50)
    private String PublisherName;

    @JsonIgnore
    @ManyToMany(mappedBy = "publishers")
    private List<Author> AuthorsPublished = new ArrayList<>();

    @JsonManagedReference(value = "publisher-books")
    @OneToMany(mappedBy = "myPublisher")
    private List<Book> BooksPublished = new ArrayList<>();

    public Publisher() {
        setID(0);
        setName("Publisher");
    }

    public Publisher(int ID, String name) {
        setID(ID);
        setName(name);
    }

    public Publisher(Publisher clonePublisher) {
        setID(clonePublisher.getID());
        setName(clonePublisher.getName());
    }

    public int getID() {
        return Publisher_ID;
    }

    public String getName() {
        return PublisherName;
    }

    public List<Author> getAuthors() {
        return AuthorsPublished;
    }

    public List<Book> getBooks() {
        return BooksPublished;
    }

    protected void setID(int ID) {
        Publisher_ID = ID;
    }

    protected void setName(String name) {
        PublisherName = name;
    }

    protected void setAuthors(List<Author> authors) {
        AuthorsPublished = authors;
    }

    protected void setBooks(List<Book> books) {
        BooksPublished = books;
    }
}