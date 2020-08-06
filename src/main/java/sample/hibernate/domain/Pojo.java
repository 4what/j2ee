package sample.hibernate.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "pojo")
@DynamicUpdate
public class Pojo {
	@Id
	//@Column(name = "id")
/*
	@GenericGenerator(
		name = "uuid",
		strategy = "uuid"
	)
*/
/*
	@TableGenerator(
		name = "table",
		table = "keygen",
		pkColumnName = "name",
		valueColumnName = "value"
		//pkColumnValue = "pojo",
		//allocationSize = 50
	)
*/
	@GeneratedValue/*(
		strategy = GenerationType.TABLE,
		generator = "table" // uuid
	)*/
	private int id;

	private String name;

	//@Temporal(TemporalType.TIMESTAMP)
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
