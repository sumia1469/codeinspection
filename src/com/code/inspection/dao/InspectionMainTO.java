package com.code.inspection.dao;

import java.util.Date;

public class InspectionMainTO {
	private Integer f_pno;
	private String f_id;
	private String f_name;
	private String f_inspection;
	private String scriptlen;
	private Date regist_date;
	private String regist_id;
	private Date update_date;
	private String update_id;
	
	public InspectionMainTO() {
		
	}

	public Integer getF_pno() {
		return f_pno;
	}

	public void setF_pno(Integer f_pno) {
		this.f_pno = f_pno;
	}

	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getF_inspection() {
		return f_inspection;
	}

	public void setF_inspection(String f_inspection) {
		this.f_inspection = f_inspection;
	}

	public String getScriptlen() {
		return scriptlen;
	}

	public void setScriptlen(String scriptlen) {
		this.scriptlen = scriptlen;
	}

	public Date getRegist_date() {
		return regist_date;
	}

	public void setRegist_date(Date regist_date) {
		this.regist_date = regist_date;
	}

	public String getRegist_id() {
		return regist_id;
	}

	public void setRegist_id(String regist_id) {
		this.regist_id = regist_id;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getUpdate_id() {
		return update_id;
	}

	public void setUpdate_id(String update_id) {
		this.update_id = update_id;
	}
	
	
}
