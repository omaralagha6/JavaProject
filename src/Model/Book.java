package Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Book")
public abstract class Book {

	@Id
	protected String isbn;
	protected String bookAuthor;
	protected String bookName;
	protected String publisher;
	protected int period;

	public Book(String isbn, String bookAuthor, String bookName, String publisher) {

		this.isbn = isbn;
		this.bookAuthor = bookAuthor;
		this.bookName = bookName;
		this.publisher = publisher;
	}

	public Book() {

	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

}
