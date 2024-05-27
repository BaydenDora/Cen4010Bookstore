package app.bookstore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity // This tells Hibernate to make a table out of this class
public class Book {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer isbn;

  private String name;

  private String author;

  public Integer getId() {
    return isbn;
  }

  public void setId(Integer id) {
    this.isbn = isbn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return author;
  }

  public void setEmail(String email) {
    this.author = author;
  }
}