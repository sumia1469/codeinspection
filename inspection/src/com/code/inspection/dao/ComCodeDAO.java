package com.code.inspection.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.code.inspection.util.DatabaseConstant;

public class ComCodeDAO {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private ArrayList<ComCodeTO> comcodeList = new ArrayList<ComCodeTO>();
	private ArrayList<ComCodeDetailTO> comcodeDetailList = new ArrayList<ComCodeDetailTO>();
	private ArrayList<ComCodeDetailListTO> comcodeDetailArrList = new ArrayList<ComCodeDetailListTO>();
	private DatabaseConstant databaseConstant = new DatabaseConstant();
	
	public ComCodeDAO() {}
	
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
	
	public ArrayList<ComCodeTO> searchComCode(String code) {
		connection();
		try {
			String sql = "SELECT * FROM COMCODE WHERE CODE = ?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.executeQuery("USE INSPECTION;");
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ComCodeTO to = new ComCodeTO();
				to.setCode(rs.getString("CODE"));
				to.setCode_nm(rs.getString("CODE_NM"));
				comcodeList.add(to);
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
		return comcodeList;
	}
	
	public ArrayList<ComCodeDetailTO> searchComCodeDetail(String code) {
		connection();
		try {
			String sql = "SELECT * FROM COMCODEDETAIL WHERE PCODE = ?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.executeQuery("USE INSPECTION;");
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ComCodeDetailTO to = new ComCodeDetailTO();
				to.setCode(rs.getString("CODE"));
				to.setCode_nm(rs.getString("CODE_NM"));
				comcodeDetailList.add(to);
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
		return comcodeDetailList;
	}
	
	public ArrayList<ComCodeDetailListTO> searchComCodeDetailList(String code) {
		connection();
		try {
			String sql = "SELECT * FROM COMCODEDETAILLIST WHERE PCODE = ?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.executeQuery("USE INSPECTION;");
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ComCodeDetailListTO to = new ComCodeDetailListTO();
				to.setCode(rs.getString("CODE"));
				to.setCode_nm(rs.getString("CODE_NM"));
				to.setPcode(rs.getString("PCODE"));
				to.setPcode_nm(rs.getString("PCODE_NM"));
				to.setCode_dc(rs.getString("CODE_DC"));
				to.setDescript(rs.getString("DESCRIPT"));
				comcodeDetailArrList.add(to);
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
		return comcodeDetailArrList;
	}
}
