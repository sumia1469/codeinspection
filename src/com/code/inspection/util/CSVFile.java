package com.code.inspection.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVFile {
	private final ArrayList<String[]> Rs = new ArrayList<String[]>();
	private String[] OneRow;
	
	public ArrayList<String[]> ReadCSVFile(File DataFile){
		try {
			BufferedReader brd = new BufferedReader(new FileReader(DataFile));
			while (brd.ready()) {
				String st = brd.readLine();
				OneRow = st.split(",|\\s|;");
				Rs.add(OneRow);
				System.out.println(Arrays.toString(OneRow));
			}
		} catch (Exception e) {
			String errmsg = e.getMessage();
			System.out.println("File nor Foud" + errmsg);
		}
		return Rs;
	}
}
