package Model;




public abstract class Person {

	@Override
	public String toString() {
		return this.getClass().getSimpleName()+" name=" + name + ", phoneNbr=" + phoneNbr + ", email=" + email + ", id=" + id ;
	}

	protected String name, phoneNbr, email, id;
	
	


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
