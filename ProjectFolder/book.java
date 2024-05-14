public class book
{
	String myGenre;
	String myPublisher;
	int myCopiesSold;
	int myRating;
	
	// No Arg Constructor
	public book()
	{
		myGenre = "Novel";
		myPublisher = "Books Publishing";
		myCopiesSold = 0;
		myRating = 0;
	}
	
	
	
	// Constructor
	public book (String genre, String publisher, int copiesSold, int rating)
	{
		setGenre (genre);
		setPublisher (publisher);
		setCopiesSold (copiesSold);
		setRating (rating);
	}
	
	// Copy Constructor
	public book (book cloneBook)
	{
		setGenre (cloneBook.getGenre());
		setPublisher (cloneBook.getPublisher());
		setCopiesSold (cloneBook.getCopiesSold());
		setRating (cloneBook.getRating());
	}
	
	
	
	// Getters
	public String getGenre()
	{
		return myGenre;
	}
	
	public String getPublisher()
	{
		return myPublisher;
	}
	
	public int getCopiesSold()
	{
		return myCopiesSold;
	}
	
	public int getRating()
	{
		return myRating;
	}
	
	
	
	// Setters
	protected void setGenre (String genre)
	{
		myGenre = genre;
	}
		
	protected void setPublisher (String publisher)
	{
		myPublisher = publisher;
	}
	protected void setCopiesSold (int copiesSold)
	{
		myCopiesSold = copiesSold;
		if (myCopiesSold < 0) myCopiesSold = 0;
	}
		
	protected void setRating (int rating)
	{
		myRating = rating;
		if (myRating > 10) myRating = 10;
		if (myRating < 0) myRating = 0;
	}
}