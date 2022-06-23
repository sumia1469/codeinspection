package com.code.inspection.dao;

import java.util.Date;

public class ComCodeTO {
	private String code;
	private String code_nm;
	private String code_dc;
	private Date regist_date;
	private String regist_id;
	private Date update_date;
	private String update_id;
	
	public ComCodeTO() {
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode_nm() {
		return code_nm;
	}

	public void setCode_nm(String code_nm) {
		this.code_nm = code_nm;
	}

	public String getCode_dc() {
		return code_dc;
	}

	public void setCode_dc(String code_dc) {
		this.code_dc = code_dc;
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
