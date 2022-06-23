package com.code.inspection.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import com.code.inspection.model.InspectionTableModel;
import com.code.inspection.service.InspectionMainService;

public class InspectionDetailControl implements ActionListener{
	private JTextField searchTermTextField = new JTextField(26);
	private JFileChooser fileComponent = new JFileChooser("C:");
	private AbstractTableModel model;
	private JButton filterButton;
	private JTable table;
	private InspectionMainService fileSearch = new InspectionMainService();
	
	public InspectionDetailControl(JTextField searchTermTextField, JTable table, JButton filterButton) {
		super();
		this.searchTermTextField = searchTermTextField;
		this.table = table;
		this.filterButton = filterButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		fileComponent.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileComponent.showOpenDialog(fileComponent);
		searchTermTextField.setText(fileComponent.getSelectedFile() != null ? fileComponent.getSelectedFile().toString() : "");
		try {
			Integer fileLen = fileSearch.search(searchTermTextField.getText());
			InspectionTableModel tableModel = new InspectionTableModel(fileLen); 
			table.setModel(tableModel);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
