package Model;

public abstract class BookFactory {
	
	
	public static Book createBook(String type,String isbn, String bookAuthor, String bookName, String publisher)
	{
		if(type.equalsIgnoreCase("novel"))return new Novel( isbn,  bookAuthor,  bookName,publisher);
		
		return null;
			
	}
	


}
