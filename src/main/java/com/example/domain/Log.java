package com.example.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicUpdate
public class Log {
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
	private int logId;

	private int adminId;

	private String module;

	private String action;
	private String message;

	private String ip;

	private int status;

	private Date createDate;

	/* getter & setter */
	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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
