package com.example.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicUpdate
public class Admin {
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
	private int adminId;

	private String username;
	private String password;

	private int status;

	private Date createDate;

	/* getter & setter */
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
