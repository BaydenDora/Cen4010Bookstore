// package com.GeekText.bookstore;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.*;

// @Entity // This tells Hibernate to make a table out of this class
// public class Publisher 
// {
// 	@Id
// 	@GeneratedValue(strategy=GenerationType.AUTO)
// 	private long myPublisherID;
	
// 	@Column(nullable = false, length = 100)
// 	private String myName;
	
// 	// No-Arg constructor
// 	public Publisher()
// 	{
// 		setID(0);
// 		setName("Publisher");
// 	}
	
// 	// Constructor
// 	public Publisher(long ID, String name)
// 	{
// 		setID(ID);
// 		setName(name);
// 	}
	
// 	//Copy constructor
// 	public Publisher(Publisher clonePublisher)
// 	{
// 		setID(clonePublisher.getID());
// 		setName(clonePublisher.getName());
// 	}
	
// 	// Getters
// 	public long getID ()
// 	{
// 		return myPublisherID;
// 	}
	
// 	public String getName ()
// 	{
// 		return myName;
// 	}
	
// 	// Setters
// 	protected void setID (long ID)
// 	{
// 		myPublisherID = ID;
// 	}
	
// 	protected void setName (String name)
// 	{
// 		myName = name;
// 	}
// }
