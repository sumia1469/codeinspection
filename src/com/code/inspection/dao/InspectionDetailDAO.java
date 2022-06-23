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

public class InspectionDetailDAO {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private ArrayList<InspectionDetailTO> list = new ArrayList<InspectionDetailTO>();
	private DatabaseConstant databaseConstant = new DatabaseConstant();
	
	public InspectionDetailDAO() {}
	
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
	
	public void insertErrorList(HashMap<String, String> errorData) {
		connection();
		try {
			String sql = "INSERT INTO INSPECTION_DETAIL (F_PNO,CHK_CODE,CHK_CODENM,CHK_PCODE,CHK_PCODENM,CHK_TYPE,CHK_TYPENM,CHK_RESULT) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.executeQuery("USE INSPECTION;");
			pstmt.setInt(1, Integer.parseInt(errorData.get("f_pno")));
			pstmt.setString(2, errorData.get("chk_code"));
			pstmt.setString(3, errorData.get("chk_codenm"));
			pstmt.setString(4, errorData.get("chk_pcode"));
			pstmt.setString(5, errorData.get("chk_pcodenm"));
			pstmt.setString(6, errorData.get("chk_type"));
			pstmt.setString(7, errorData.get("chk_typenm"));
			pstmt.setString(8, errorData.get("chk_result"));
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
	
	public ArrayList<InspectionDetailTO> searchErrorList() {
		connection();
		try {
			String sql = "SELECT * FROM INSPECTION_DETAIL ORDER BY REGIST_DATE DESC";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.executeQuery("USE INSPECTION;");
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				InspectionDetailTO to = new InspectionDetailTO();
				to.setF_pno(rs.getInt("F_PNO"));
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
	
	public ArrayList<InspectionDetailTO> searchDetailList(Integer fpno) {
		connection();
		try {
			String sql = "SELECT * FROM INSPECTION_DETAIL WHERE F_PNO = ?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.executeQuery("USE INSPECTION;");
			pstmt.setInt(1, fpno);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				InspectionDetailTO to = new InspectionDetailTO();
				to.setF_pno(rs.getInt("F_PNO"));
				to.setI_pno(rs.getInt("I_PNO"));
				to.setChk_code(rs.getString("CHK_CODE"));
				to.setChk_codenm(rs.getString("CHK_CODENM"));
				to.setChk_pcode(rs.getString("CHK_PCODE"));
				to.setChk_pcodenm(rs.getString("CHK_PCODENM"));
				to.setChk_result(rs.getString("CHK_RESULT"));
				to.setChk_status(rs.getString("CHK_STATUS"));
				to.setChk_type(rs.getString("CHK_TYPE"));
				to.setChk_typenm(rs.getString("CHK_TYPENM"));
				to.setChk_type(rs.getString("CHK_TYPE"));
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
}
