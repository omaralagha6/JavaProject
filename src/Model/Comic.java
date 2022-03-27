package Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Comic")
public class Comic extends Book {
Comic(){}
	Comic(String isbn, String bookAuthor, String bookName, String publisher)
	{
		super(isbn,bookAuthor,bookName,publisher);
		this.setPeriod(10);
	
	}
}
