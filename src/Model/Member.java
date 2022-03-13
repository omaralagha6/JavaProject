package Model;

public class Member extends Person {

	public Member(String name, String phoneNbr, String id, String email, String address) {
		super(id, name, phoneNbr, email);
		this.address = address;
	}

	public Member() {
		super();
	}

	@Override
	public String toString() {
		return super.toString()+", address : "+address;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	protected String address;
}
