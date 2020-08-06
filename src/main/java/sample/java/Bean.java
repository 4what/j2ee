package sample.java;

import java.util.Date;

public class Bean {
	private int id;

	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		return "Bean{" +
			"id=" + id +
			", date=" + date +
			'}';
	}
}
