package sample.mybatis.domain;

public class Parent {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Parent{" +
			"id=" + id +
			'}';
	}
}
