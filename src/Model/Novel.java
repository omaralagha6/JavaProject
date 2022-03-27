package Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Novel")
public class Novel extends Book{
	Novel(){}
	Novel(String isbn, String bookAuthor, String bookName, String publisher)
	{
		super(isbn,bookAuthor,bookName,publisher);
		this.setPeriod(20);
	
	}


}
