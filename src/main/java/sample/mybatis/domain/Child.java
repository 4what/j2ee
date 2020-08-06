package sample.mybatis.domain;

public class Child {
	private int id;

	private int pojoId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPojoId() {
		return pojoId;
	}

	public void setPojoId(int pojoId) {
		this.pojoId = pojoId;
	}

	@Override
	public String toString() {
		return "Child{" +
			"id=" + id +
			", pojoId=" + pojoId +
			'}';
	}
}
