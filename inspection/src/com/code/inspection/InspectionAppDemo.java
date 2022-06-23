package com.code.inspection;

import javax.swing.SwingUtilities;
import com.code.inspection.view.InspectionView;
public class InspectionAppDemo {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						createAndShowGUI();
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
		});
	}
	
	public static void createAndShowGUI() throws Exception {
		new InspectionView();
	}
}
