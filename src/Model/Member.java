package Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Member extends Person {
	
	@OneToMany(mappedBy="member")
	protected List<Issue>issue;

	public List<Issue> getIssue() {
		return issue;
	}

	public void setIssue(List<Issue> issue) {
		this.issue = issue;
	}

	public Member(String name, String phoneNbr, String id, String email, String address) {
		super(id, name, phoneNbr, email);
		this.address = address;
	}

	public Member() {
		super();
	}

	@Override
	public String toString() {
		return super.toString()+", Address : "+address;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNumber() {
		return phoneNbr;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="Address")
	protected String address;
}
