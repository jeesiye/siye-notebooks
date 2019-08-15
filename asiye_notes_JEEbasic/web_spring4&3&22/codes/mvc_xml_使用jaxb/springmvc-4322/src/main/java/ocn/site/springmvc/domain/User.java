package ocn.site.springmvc.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String author;

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", author=" + author + "]";
	}

}
