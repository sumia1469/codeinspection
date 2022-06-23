package com.code.inspection.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.code.inspection.dao.InspectionDetailDAO;
import com.code.inspection.dao.InspectionDetailTO;
import com.code.inspection.dao.InspectionMainTO;

final public class InspectionDetailTableModel extends AbstractTableModel{
	private ArrayList<InspectionDetailTO> detailList = new ArrayList<InspectionDetailTO>();
	private String[] columnNames = {"검사종류","검사코드","컴포넌트타입","검사결과,","검사날짜"};
	
	public InspectionDetailTableModel (InspectionMainTO mainData) {
		InspectionDetailDAO detailDao = new InspectionDetailDAO(); 
		Integer fpno = mainData.getF_pno();
		detailList = detailDao.searchDetailList(fpno);
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public int getRowCount() {
		return detailList.size();
	}
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String result = "";
		InspectionDetailTO to = detailList.get(rowIndex);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String strDate = dateFormat.format(to.getRegist_date());
		
		switch(columnIndex) {
			case 0:
				result = to.getChk_pcodenm();
				break;
			case 1:
				result = to.getChk_codenm();
				break;
			case 2:
				result = to.getChk_typenm();
				break;
			case 3:
				result = to.getChk_result();
				break;
			case 4:
				result = strDate;
				break;
		}
		return result;
	}
}
