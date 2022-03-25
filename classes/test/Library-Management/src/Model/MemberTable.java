package Model;

import javafx.beans.property.SimpleStringProperty;

public class MemberTable {
	private SimpleStringProperty name, phoneNbr, id, email, address;
	
	public MemberTable(String name, String id, String phoneNbr, String email, String address) {
		this.name = new SimpleStringProperty(name);
		this.id = new SimpleStringProperty(id);
		this.phoneNbr = new SimpleStringProperty(phoneNbr);
		this.email = new SimpleStringProperty(email);
		this.address = new SimpleStringProperty(address);
	}
	
	public String getName() {
		return this.name.get();
	}
	public String getID() {
		return this.id.get();
	}
	public String getPhoneNbr() {
		return this.phoneNbr.get();
	}
	public String getEmail() {
		return this.email.get();
	}
	public String getAddress() {
		return this.address.get();
	}
	
	
	public void setName(String name) {
		this.name.set(name);
	}
	public void setID(String ID) {
		this.id.set(ID);
	}
	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr.set(phoneNbr);
	}
	public void setEmail(String email) {
		this.email.set(email);
	}
	public void setAddress(String address) {
		this.address.set(address);
	}
	
	@Override
	public String toString() {
		return id.get() + ": Name: " + name.get() + ", Phone Number: " + phoneNbr.get();
	}
}
