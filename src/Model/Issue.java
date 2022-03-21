package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Issue")
public class Issue {

	@Id
	protected int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne
	protected Book book;
	@ManyToOne
	protected Employee employee;
	@ManyToOne
	protected Member member;
	
	@Column(name="IssueDate")
	protected String issueDate;
	



	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Issue( Book book, Employee employee, Member member) {
		
	
		this.book = book;
		this.employee = employee;
		this.member = member;
		this.issueDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public Issue() {
		
	}

	public Issue(Book book, Employee employee, Member member, String issueDate) {
		super();
		this.book = book;
		this.employee = employee;
		this.member = member;
		this.issueDate = issueDate;
	}

}
