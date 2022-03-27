package Model;

import javax.persistence.*;

@Entity
@Table(name="Book")
public class Book {

	@Id
	@Column(name="Isbn")
	protected String isbn;
	@Column(name="BookAuthor")
	protected String bookAuthor;
	@Column(name="BookName")
	protected String bookName;
	@Column(name="Publisher")
	protected String publisher;
	@Column(name="Period")
	protected int period;
	@Column(name ="Available")
	protected Boolean isAvailable;

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Book(String isbn, String bookAuthor, String bookName, String publisher) {

		this.isbn = isbn;
		this.bookAuthor = bookAuthor;
		this.bookName = bookName;
		this.publisher = publisher;
		this.isAvailable=true;
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
