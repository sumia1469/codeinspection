package com.code.inspection.control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JTable;

import com.code.inspection.dao.InspectionMainTO;
import com.code.inspection.view.InspectionPopView;

public class RowSelectionListener extends JFrame implements MouseListener {
	private static JTable table = new JTable();
	public RowSelectionListener(JTable table) {
		super();
		this.table = table;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int viewRow = table.getSelectedRow();
		int viewcol = table.getSelectedColumn();
		int modelRow = table.convertRowIndexToModel(viewRow);
		Integer fpno = Integer.parseInt(table.getModel().getValueAt(modelRow, 0).toString());
		InspectionMainTO mainData = new InspectionMainTO();
		mainData.setF_pno(fpno);
		InspectionPopView frame = new InspectionPopView(mainData);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
