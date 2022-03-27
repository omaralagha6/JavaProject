package Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Manga")
public class Manga extends Book {
	Manga(){}
	Manga(String isbn, String bookAuthor, String bookName, String publisher)
	{
		super(isbn,bookAuthor,bookName,publisher);
		this.setPeriod(5);
	
	}
}
