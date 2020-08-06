package sample.spring.mongo;

import java.util.Date;

public class Pojo {
	private String id; // (*) String

	private Date date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
			"id='" + id + '\'' +
			", date=" + date +
			'}';
	}
}
