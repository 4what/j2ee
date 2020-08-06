package com.example.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicUpdate
public class Session {
	@Id
	@TableGenerator(
		name = "table",
		table = "keygen",
		pkColumnName = "name",
		valueColumnName = "value"
	)
	@GeneratedValue(
		strategy = GenerationType.TABLE,
		generator = "table"
	)
	private int sessionId;

	private int adminId;

	private String value;

	private int status;

	private Date createDate;

	/* getter & setter */
	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
