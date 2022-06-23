package com.code.inspection.control;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.code.inspection.util.DatabaseConstant;

public class DatabaseSetControl implements ActionListener{
	private JButton databaseButton;
	private DatabaseConstant databaseConstant = new DatabaseConstant();
	
	public DatabaseSetControl(JButton databaseButton) {
		super();
		this.databaseButton = databaseButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField DRIVERNAME_Field = new JTextField(30);
		JTextField URL_Field = new JTextField(30);
		JTextField USER_Field = new JTextField(30);
		JTextField PASSWORD_Field = new JTextField(30);
		
		DRIVERNAME_Field.setText(databaseConstant.DRIVERNAME);
		URL_Field.setText(databaseConstant.URL);
		USER_Field.setText(databaseConstant.USER);
		PASSWORD_Field.setText(databaseConstant.PASSWORD);
		
		JFrame frame = new JFrame("서버설정");
		frame.setPreferredSize(new Dimension(50,100));
		frame.setLocation(500,400);
		Container contentPane = frame.getContentPane();
		
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(4,2));
		myPanel.add(new JLabel("DRIVERNAME:"));
		myPanel.add(DRIVERNAME_Field);
		myPanel.add(new JLabel("URL:"));
		myPanel.add(URL_Field);
		myPanel.add(new JLabel("USER:"));
		myPanel.add(USER_Field);
		myPanel.add(new JLabel("PASSWORD:"));
		myPanel.add(PASSWORD_Field);
		contentPane.add(myPanel, BorderLayout.CENTER);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "데이터베이스 설정", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			ArrayList<String> saveData = new ArrayList<String>();
			saveData.add("DRIVERNAME=" + DRIVERNAME_Field.getText());
			saveData.add("URL=" + URL_Field.getText());
			saveData.add("USER=" + USER_Field.getText());
			saveData.add("PASSWORD=" + PASSWORD_Field.getText());
			try {
				DatabaseConstant.writeToFile(saveData);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
