package Model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {

	@Override
	public String toString() {
		return "Name = " + name + ", PhoneNumber = " + phoneNbr + ", Email = " + email
				+ ", Id = " + id;
	}

	@Column(name = "Name")
	protected String name;
	@Column(name = "PhoneNumber")
	protected String phoneNbr;
	@Column(name = "Email")
	protected String email;
	@Id
	@Column(name = "Id")
	protected String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNbr() {
		return phoneNbr;
	}

	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Person(String id, String name, String phoneNbr, String email) {
		super();
		this.name = name;
		this.phoneNbr = phoneNbr;
		this.id = id;
		this.email = email;
	}

	Person() {
	}

}
