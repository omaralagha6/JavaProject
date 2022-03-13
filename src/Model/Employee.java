package Model;

import java.util.Set;




public class Employee extends Person {

	protected String password;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee(String id, String name, String phoneNbr, String email, String password) {
		super(id, name, phoneNbr, email);
		this.password=password;

	}

	@Override
	public String toString() {
		return super.toString()+", password : "+ password;
	}

	Set<Member> members;

	public Employee() {
	}

	
}
