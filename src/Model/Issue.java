package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Issue")
public class Issue implements Serializable{

	@Id
	@OneToOne
	protected Book book;
	@ManyToOne
	protected Employee employee;
	@ManyToOne
	protected Member member;
	@Column(name = "IssueDate")
	protected LocalDate issueDate;
	
	@Column(name="NumberOfRenewal")
	protected int nbRenewal;
	
	public int getNbRenewal() {
		return nbRenewal;
	}

	public void setNbRenewal(int nbRenewal) {
		this.nbRenewal = nbRenewal;
	}

	public int getFine() {
		return fine;
	}

	public void setFine() {
		this.fine = book.getClass().getSimpleName().equalsIgnoreCase("novel")?10000:book.getClass().getSimpleName().equalsIgnoreCase("manga")?5000:3000;
		this.fine *= nbRenewal;
	}

	@Column(name="Fine")
	protected int fine;

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
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

	public Issue(Book book, Employee employee, Member member) {

		this.book = book;
		this.employee = employee;
		this.member = member;
		this.issueDate = LocalDate.now();
		this.nbRenewal=0;
		this.fine=0;
	}

	public Issue() {

	}

}
