package Model;

public class Novel extends Book{
	
	Novel(String isbn, String bookAuthor, String bookName, String publisher)
	{
		super(isbn,bookAuthor,bookName,publisher);
		this.setPeriod(20);
	
	}


}
