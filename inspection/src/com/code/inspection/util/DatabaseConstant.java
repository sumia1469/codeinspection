package com.code.inspection.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseConstant {
	public static String DRIVERNAME = "org.mariadb.jdbc.Driver";
	public static String URL = "jdbc:mariadb://localhost:43306/";
	public static String USER = "root";
	public static String PASSWORD = "1111";
	public static BufferedReader br;
	public static String path = "C:\\ProgramData\\codeinspection";
	public static String fullPath = "C:\\ProgramData\\codeinspection\\databaseSet.txt";

	public DatabaseConstant() {
		try {
			readToFile();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToFile(ArrayList<String> saveData) throws IOException {
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		File file = new File(fullPath);
		try {
			if(!file.exists()) {
				FileWriter fw = new FileWriter(file, true);
				try {
					BufferedWriter bw = new BufferedWriter(fw);
					for(String data : saveData) {
						bw.write(data);
						bw.newLine();
					}
					bw.flush();
					bw.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				FileWriter fw = new FileWriter(file, false);
				try {
					BufferedWriter bw = new BufferedWriter(fw);
					for(String data : saveData) {
						bw.write(data);
						bw.newLine();
					}
					bw.flush();
					bw.close();
					readToFile();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void readToFile() throws IOException {
		File file = new File("C:\\ProgramData\\codeinspection\\databaseSet.txt");
		if(file.isFile()) {
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				while(br.ready()) {
					String tmpTxt = br.readLine();
					String[] readTxd = tmpTxt.split("=");
					if("DRIVERNAME".equals(readTxd[0])){
						DRIVERNAME = readTxd[1];
					} else if ("URL".equals(readTxd[0])){
						URL = readTxd[1];
					} else if ("USER".equals(readTxd[0])){
						USER = readTxd[1];
					} else if ("PASSWORD".equals(readTxd[0])){
						PASSWORD = readTxd[1];
					}
				}
			} catch(Exception e) {
				System.out.println(e);
			}
		} else {
			ArrayList<String> saveData = new ArrayList<String>();
			saveData.add("DRIVERNAME=" + DRIVERNAME);
			saveData.add("URL=" + URL);
			saveData.add("USER=" + USER);
			saveData.add("PASSWORD=" + PASSWORD);
		}
	}
}
