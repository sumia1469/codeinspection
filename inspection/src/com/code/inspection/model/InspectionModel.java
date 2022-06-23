package com.code.inspection.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.code.inspection.dao.InspectionDetailDAO;
import com.code.inspection.dao.InspectionDetailTO;
import com.code.inspection.dao.InspectionMainDAO;
import com.code.inspection.dao.InspectionMainTO;

public class InspectionModel {
	public InspectionModel() {}
	public void insert(HashMap<String, String> fileList) {
		InspectionMainDAO dao = new InspectionMainDAO();
		dao.insertFileList(fileList);
	}
	
	public InspectionMainTO searchLastList() {
		InspectionMainDAO dao = new InspectionMainDAO();
		InspectionMainTO result = new InspectionMainTO();
		result = dao.searchLastList();
		return result;
	}
	
	public ArrayList<InspectionDetailTO> searchDetailList(Integer fpno){
		InspectionDetailDAO dao = new InspectionDetailDAO();
		ArrayList<InspectionDetailTO> result = new  ArrayList<InspectionDetailTO>();
		result = dao.searchDetailList(fpno);
		return result;
	}
	
	public void insertScriptlen(HashMap<String, String> fileList) {
		InspectionMainDAO dao = new InspectionMainDAO();
		dao.insertScriptlen(fileList);
	}
}
