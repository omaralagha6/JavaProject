package Model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Employee extends Person {


    @OneToMany(mappedBy = "employee")
    protected List<Issue> issue;

    public List<Issue> getIssue() {
        return issue;
    }

    public void setIssue(List<Issue> issue) {
        this.issue = issue;
    }


    @Column(name = "Password")
    protected String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee(String id, String name, String phoneNbr, String email, String password) {
        super(id, name, phoneNbr, email);
        this.password = password;

    }

    @Override
    public String toString() {
        return super.toString() + ", Password : " + password;
    }


    public Employee() {
    }


}
