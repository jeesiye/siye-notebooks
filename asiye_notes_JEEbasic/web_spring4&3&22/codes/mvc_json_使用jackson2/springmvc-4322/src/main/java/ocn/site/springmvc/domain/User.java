package ocn.site.springmvc.domain;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private int age;
	private String name;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String id, int age, String name) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", age=" + age + ", name=" + name + "]";
	}

}
