package sample.spring.domain;

import sample.hibernate.validator.Annotation;
import sample.hibernate.validator.Group;

import javax.validation.GroupSequence;
import javax.validation.constraints.Min;
import java.util.Date;

//@GroupSequence({})
public class Pojo {
	@Min(
		value = 1
		, message = "{pojo.id.min}" // LocalValidatorFactoryBean.setValidationMessageSource(MessageSource messageSource)
		//, groups = Group.class
	)
	private int id;

	@Annotation(value = "illegal", message = "Forbidden")
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
