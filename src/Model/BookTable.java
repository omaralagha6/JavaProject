package Model;

import javafx.beans.property.SimpleStringProperty;

public class BookTable {
	private SimpleStringProperty title, id, author, publisher;
	private SimpleStringProperty available;
	
	public BookTable(String title, String id, String author, String publisher, String available) {
		this.title = new SimpleStringProperty(title);
		this.id = new SimpleStringProperty(id);
		this.author = new SimpleStringProperty(author);
		this.publisher = new SimpleStringProperty(publisher);
		this.available = new SimpleStringProperty(available);
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
	public String getAvailable() {
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
	public void setAvailable(String available) {
		this.available.set(available);
	}
}
