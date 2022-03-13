package Database;

import java.util.List;

import Model.Person;

public interface Dao {
	public List<Person> getAll();

	public Person get(String id);

	public void add(Person person);

	public void update(Person person);

	public void delete(String id);
}
