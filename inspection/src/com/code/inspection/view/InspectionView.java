package com.code.inspection.view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import com.code.inspection.control.DatabaseSetControl;
import com.code.inspection.control.InspectionMainControl;
import com.code.inspection.control.RowSelectionListener;
import com.code.inspection.util.CreateDataBase;

public class InspectionView {
	private JTable tableResult;
	public InspectionView() {
		JTextField searchTermTextField = new JTextField(26);
		JButton filterButton = new JButton("검사 폴더 선택");
		JButton databaseButton = new JButton("데이터베이스 설정");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(12,142,613,259);
		
		JTable table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new RowSelectionListener(table));
		scrollPane.setViewportView(table);
		
		InspectionMainControl controller = new InspectionMainControl(searchTermTextField, table, filterButton);
		filterButton.addActionListener(controller);
		
		DatabaseSetControl databaseSetcontroller = new DatabaseSetControl(databaseButton);
		databaseButton.addActionListener(databaseSetcontroller);
		
		JPanel ctrlPane = new JPanel();
		ctrlPane.add(searchTermTextField);
		ctrlPane.add(filterButton);
		ctrlPane.add(databaseButton);
		
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setPreferredSize(new Dimension(700,182));
		tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"검사리스트",TitledBorder.CENTER, TitledBorder.TOP));
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
		splitPane.setDividerLocation(35);
		splitPane.setEnabled(false);
		
		JFrame frame = new JFrame("LG UCUBE CODE INSPECTION");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(splitPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		new CreateDataBase(frame);
	}
}
