package com.code.inspection.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;

import com.code.inspection.dao.InspectionMainTO;
import com.code.inspection.model.InspectionDetailTableModel;

public class InspectionPopView extends JFrame implements ActionListener {
	JLabel lblTitle = new JLabel("코드인스펙션 검사");
	JTextArea textArea = new JTextArea();
	InspectionMainTO mainData;
	
	public InspectionPopView(InspectionMainTO mainData) {
		super("코드인스펙션 유효성 검사결과");
		this.mainData = mainData;
		display();
		startEvent();
		setSize(1200, 600);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void display() {
		JTable table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		InspectionDetailTableModel tableModel = new InspectionDetailTableModel(mainData);
		table.setModel(tableModel);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		TableColumnModel colModel = table.getColumnModel();
		colModel.getColumn(0).setPreferredWidth(100);
		colModel.getColumn(1).setPreferredWidth(100);
		colModel.getColumn(2).setPreferredWidth(100);
		colModel.getColumn(3).setPreferredWidth(800);
		colModel.getColumn(4).setPreferredWidth(100);
		
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setPreferredSize(new Dimension(700,182));
		tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"검사리스트",TitledBorder.CENTER, TitledBorder.TOP));
		add(tableScrollPane);
	}
	
	public void startEvent() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
