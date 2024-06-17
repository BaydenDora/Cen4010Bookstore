package main.java.app.bookstore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "wishlistitem")
public class WishlistItems 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int myWishlistItemID;
}
