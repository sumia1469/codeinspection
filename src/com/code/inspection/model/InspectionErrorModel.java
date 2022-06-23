package com.code.inspection.model;

import java.util.HashMap;

import com.code.inspection.dao.InspectionDetailDAO;

public class InspectionErrorModel {
	public InspectionErrorModel() {}
	public void insert(HashMap<String, String> errorData) {
		InspectionDetailDAO dao = new InspectionDetailDAO();
		dao.insertErrorList(errorData);
	}
}
