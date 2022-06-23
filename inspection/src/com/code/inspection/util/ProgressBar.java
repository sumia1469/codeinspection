package com.code.inspection.util;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class ProgressBar extends JPanel implements PropertyChangeListener{
	private JProgressBar progressBar;
	private JTextArea taskOutput;
	private Task task;
	
	class Task extends SwingWorker<Void, Void> {
		@Override
		public Void doInBackground() {
			Random random = new Random();
			int progress = 0;
			setProgress(0);
			while(progress < 100) {
				try {
					Thread.sleep(random.nextInt(1000));
				} catch (InterruptedException ignore) {
				}
				progress += random.nextInt(10);
				setProgress(Math.min(progress,100));
			}
			return null;
		}
		
		@Override
		public void done() {
			Toolkit.getDefaultToolkit().beep();
			setCursor(null);
			taskOutput.append("Done!\n");
		}
			
	}
	
	public ProgressBar() {
		super(new BorderLayout());
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setEnabled(true);
		
		taskOutput = new JTextArea(5, 10);
		taskOutput.setMargin(new Insets(5,5,5,5));
		taskOutput.setEditable(false);
		
		JPanel panel = new JPanel();
		panel.add(progressBar);
		add(panel, BorderLayout.PAGE_END);
	}
	
	public void start() {
		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();
	}
	
	
	public void setValue(Integer value) {
		progressBar.setValue(value);
		task.addPropertyChangeListener(this);
	}

	
	public void propertyChange(PropertyChangeEvent evt) {
		if("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
			System.out.println(String.format("Completed %d%% of task.\n", task.getProgress()));
		}
	}
	
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("progressBar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComponent newContentPane = new ProgressBar();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
