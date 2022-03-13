package Model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
	private SimpleStringProperty title, id, author, publisher;
	private SimpleBooleanProperty available;
	
	public Book(String title, String id, String author, String publisher, Boolean available) {
		this.title = new SimpleStringProperty(title);
		this.id = new SimpleStringProperty(id);
		this.author = new SimpleStringProperty(author);
		this.publisher = new SimpleStringProperty(publisher);
		this.available = new SimpleBooleanProperty(available);
	}
	
	public String getTitle() {
		return this.title.get();
	}
	public String getID() {
		return this.id.get();
	}
	public String getAuthor() {
		return this.author.get();
	}
	public String getPublisher() {
		return this.publisher.get();
	}
	public Boolean getAvailable() {
		return this.available.get();
	}
	
	
	public void setTitle(String title) {
		this.title.set(title);
	}
	public void setID(String ID) {
		this.id.set(ID);
	}
	public void setAuthor(String author) {
		this.author.set(author);
	}
	public void setPublisher(String publisher) {
		this.publisher.set(publisher);
	}
	public void setAvailable(Boolean available) {
		this.available.set(available);
	}
}
