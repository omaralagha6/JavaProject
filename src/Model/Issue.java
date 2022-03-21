package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Issue {

	protected Book book;
	protected Employee employee;
	protected Member member;
	protected String issueDate;
	protected String isbn;
	
	public String getisbn()
	{
		return  this.getBook().getIsbn();
	}
	public void setisbn(String isbn)
	{this.getBook().setIsbn(isbn);
	}


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
