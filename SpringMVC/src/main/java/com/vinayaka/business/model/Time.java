package com.vinayaka.business.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Time 
{
	@JsonFormat(pattern = "MMM dd, yyyy HH:mm:ss 'UTC'")
	 private Date updated;
	 private String updatedISO;
	 private String updateduk;

	public String getUpdateduk() {
		return updateduk;
	}

	public void setUpdateduk(String updateduk) {
		this.updateduk = updateduk;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdatedISO() {
		return updatedISO;
	}

	public void setUpdatedISO(String updatedISO) {
		this.updatedISO = updatedISO;
	}
}
