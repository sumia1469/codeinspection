package com.code.inspection.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.code.inspection.util.DatabaseConstant;

public class InspectionMainDAO {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private ArrayList<InspectionMainTO> list = new ArrayList<InspectionMainTO>();
	private DatabaseConstant databaseConstant = new DatabaseConstant();
	
	public InspectionMainDAO() {}
	
	public void connection() {
		String url = databaseConstant.URL;
		String user = databaseConstant.USER;
		String password = databaseConstant.PASSWORD;
		
		try {
			Class.forName(databaseConstant.DRIVERNAME);
			if(this.conn == null) {
				this.conn = DriverManager.getConnection(url, user, password);
				stmt = conn.createStatement();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertFileList(HashMap<String, String> fileList) {
		connection();
		try {
			String sql = "INSERT INTO INSPECTION_MAIN (F_ID,F_NAME) VALUES (?,?)";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.executeQuery("USE INSPECTION;");
			pstmt.setString(1, fileList.get("f_id"));
			pstmt.setString(2, fileList.get("f_name"));
			ResultSet rs = pstmt.executeQuery();

			if(rs != null) {
				rs.close();
			};
			
			if(pstmt != null) {
				pstmt.close();
			}
			
			if(conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<InspectionMainTO> searchFileList(Integer fileLen) {
		connection();
		try {
			String sql = "SELECT * FROM INSPECTION_MAIN ORDER BY REGIST_DATE DESC LIMIT ?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.executeQuery("USE INSPECTION;");
			pstmt.setInt(1, fileLen);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				InspectionMainTO to = new InspectionMainTO();
				to.setF_pno(rs.getInt("F_PNO"));
				to.setF_id(rs.getString("F_ID"));
				to.setF_name(rs.getString("F_NAME"));
				to.setScriptlen(rs.getString("SCRIPTLEN"));
				to.setRegist_date(rs.getDate("REGIST_DATE"));
				list.add(to);
			}
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(this.conn != null)
				this.conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public InspectionMainTO searchLastList() {
		connection();
		InspectionMainTO to = new InspectionMainTO();
		try {
			String sql = "SELECT * FROM INSPECTION_MAIN ORDER BY F_PNO DESC LIMIT 1";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.executeQuery("USE INSPECTION;");
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				to.setF_pno(rs.getInt("F_PNO"));
				to.setF_id(rs.getString("F_ID"));
				to.setF_name(rs.getString("F_NAME"));
				to.setF_inspection(rs.getString("F_INSPECTION"));
				to.setRegist_date(rs.getDate("REGIST_DATE"));
			}
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(this.conn != null)
				this.conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return to;
	}
	
	public void insertScriptlen(HashMap<String, String> fileList) {
		connection();
		try {
			String sql = "UPDATE INSPECTION_MAIN SET SCRIPTLEN = ? WHERE F_PNO = ?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.executeQuery("USE INSPECTION;");
			pstmt.setString(1, fileList.get("scriptlen"));
			pstmt.setString(2, fileList.get("f_pno"));
			ResultSet rs = pstmt.executeQuery();

			if(rs != null) {
				rs.close();
			};
			
			if(pstmt != null) {
				pstmt.close();
			}
			
			if(conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
}
