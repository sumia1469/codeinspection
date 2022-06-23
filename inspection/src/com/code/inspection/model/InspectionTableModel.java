package com.code.inspection.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.code.inspection.dao.InspectionMainDAO;
import com.code.inspection.dao.InspectionMainTO;

public class InspectionTableModel extends AbstractTableModel{
	private ArrayList<InspectionMainTO> fileList = new ArrayList<InspectionMainTO>();
	private String[] columnNames = {"NO","파일ID","파일명","스크립트라인수,","검사날짜"};
	
	public InspectionTableModel (Integer fileLen) {
		InspectionMainDAO fileDao = new InspectionMainDAO(); 
		fileList = fileDao.searchFileList(fileLen);
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public int getRowCount() {
		return fileList.size();
	}
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String result = "";
		InspectionMainTO to = fileList.get(rowIndex);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String strDate = dateFormat.format(to.getRegist_date());
		
		switch(columnIndex) {
			case 0:
				result = to.getF_pno().toString();
				break;
			case 1:
				result = to.getF_id();
				break;
			case 2:
				result = to.getF_name();
				break;
			case 3:
				result = to.getScriptlen();
				break;
			case 4:
				result = strDate;
				break;
		}
		return result;
	}
}
