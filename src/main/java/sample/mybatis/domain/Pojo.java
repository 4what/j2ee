package sample.mybatis.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Serializable: cache + XML
 */
public class Pojo implements Serializable {
	private int id;

	private String name;

	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Pojo{" +
			"id=" + id +
			", name=" + name +
			", date=" + date +
			'}';
	}
}
