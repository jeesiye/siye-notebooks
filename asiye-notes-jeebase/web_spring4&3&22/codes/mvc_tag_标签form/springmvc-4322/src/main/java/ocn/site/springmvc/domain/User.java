package ocn.site.springmvc.domain;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public User(int id, String username, String passwd) {
		super();
		this.id = id;
		this.username = username;
		this.passwd = passwd;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int id;
	private String username;
	private String passwd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", passwd=" + passwd + "]";
	}

}
