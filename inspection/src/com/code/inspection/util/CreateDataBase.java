package com.code.inspection.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CreateDataBase {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private DatabaseConstant databaseConstant = new DatabaseConstant();
	
	public CreateDataBase(JFrame jFrame) {
		String url = databaseConstant.URL;
		String user = databaseConstant.USER;
		String password = databaseConstant.PASSWORD;
		
		try {
			Class.forName(databaseConstant.DRIVERNAME);
			if(this.conn == null) {
				this.conn = DriverManager.getConnection(url,user,password);
				stmt = conn.createStatement();
				int result = 0;
				String query = "";
				stmt.execute("USE INFORMATION_SCHEMA;");
				rs = stmt.executeQuery("SELECT 1 FROM SCHEMATA WHERE SCHEMA_NAME = 'INSPECTION';");
				if (rs.next()) {
					result = Integer.valueOf(rs.getString(1));
				}
				if (result == 0) {
					JOptionPane.showMessageDialog(jFrame, "DATABASE INSPECTION이 존재하지 않아 DATABASE를 생성합니다.");
					stmt.executeQuery("CREATE DATABASE INSPECTION;");
				}
				
				result = 0;
				stmt.executeQuery("USE INSPECTION;");
				rs = stmt.executeQuery("SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'INSPECTION' AND TABLE_NAME = 'COMCODE'");
				if(rs.next()) {
					result = Integer.valueOf(rs.getString(1));
				}
				
				if (result == 0) {
					JOptionPane.showMessageDialog(jFrame, "COMCODE가 존재하지 않아 TABLE를 생성합니다.");
					query = "CREATE TABLE COMCODE ("
							+ "CODE VARCHAR(6) NOT NULL, "
							+ "CODE_NM VARCHAR(60) NULL, "
							+ "CODE_DC VARCHAR(200) NULL, "
							+ "REGIST_DATE DATE NOT NULL DEFAULT CURRENT_TIMESTAMP(6),"
							+ "UPDATE_DATE DATE NULL, "
							+ "CONSTRAINT COMCODE_PK PRIMARY KEY(CODE)"
							+ ") CHARSET=utf8mb4";
					stmt.executeQuery(query);
				}

				result = 0;
				stmt.executeQuery("USE INSPECTION;");
				rs = stmt.executeQuery("SELECT 1 FROM COMCODE");
				if(rs.next()) {
					result = Integer.valueOf(rs.getString(1));
				}
				
				if (result == 0) {
					JOptionPane.showMessageDialog(jFrame, "COMCODE에 DATA가 존재하지 않아 데이터를 생성합니다.");
					stmt.executeUpdate("INSERT INTO COMCODE "
							+ "(CODE,"
							+ "CODE_NM,"
							+ "CODE_DC) "
							+ "VALUES"
							+ "('COD001','점검항목','점검항목'),"
							+ "('COD002','점검 체크리스트','점검 체크리스트'),"
							+ "('COD003','아이디체크기준','컴포넌트ID 명명규칙 리스트');");
				}
				result = 0;
				rs = stmt.executeQuery("SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'INSPECTION' AND TABLE_NAME = 'COMCODEDETAIL'");
				if(rs.next()) {
					result = Integer.valueOf(rs.getString(1));
				}
				
				if (result == 0) {
					JOptionPane.showMessageDialog(jFrame, "COMCODEDETAIL이 존재하지 않아 TABLE 및 DATA를 생성합니다.");
					query = "CREATE TABLE COMCODEDETAIL ("
							+ "CODE VARCHAR(15) NOT NULL, "
							+ "PCODE VARCHAR(6) NOT NULL, "
							+ "PCODE_NM VARCHAR(30) NOT NULL, "
							+ "CODE_NM VARCHAR(60) NULL, "
							+ "CODE_DC VARCHAR(200) NULL, "
							+ "REGIST_DATE DATE NOT NULL DEFAULT CURRENT_TIMESTAMP(6),"
							+ "UPDATE_DATE DATE NULL, "
							+ "CONSTRAINT COMCODEDETAIL_PK PRIMARY KEY(CODE),"
							+ "CONSTRAINT COMCODEDETAIL_FK1 FOREIGN KEY(PCODE) REFERENCES COMCODE(CODE)"
							+ ") CHARSET=utf8mb4";
					stmt.executeQuery(query);
				}

				result = 0;
				stmt.executeQuery("USE INSPECTION;");
				rs = stmt.executeQuery("SELECT 1 FROM COMCODEDETAIL");
				if(rs.next()) {
					result = Integer.valueOf(rs.getString(1));
				}
				
				if (result == 0) {
					JOptionPane.showMessageDialog(jFrame, "COMCODEDETAIL에 DATA가 존재하지 않아 데이터를 생성합니다.");
					stmt.executeQuery("INSERT INTO COMCODEDETAIL ("
							+ "CODE,"
							+ "PCODE,"
							+ "PCODE_NM,"
							+ "CODE_NM,"
							+ "CODE_DC"
							+ ") VALUES"
							+ "('COD001_001','COD001','점검항목','명명규칙','컴포넌트ID 명명규칙 준수'),"
							+ "('COD001_002','COD001','점검항목','주석','주석표준 준수'),"
							+ "('COD001_003','COD001','점검항목','REST API 표준','API에 따라 GET,POST,PUT,DELETE 메소드 적절히 구분'),"
							+ "('COD001_004','COD001','점검항목','REST 통신','URI 및 전달 데이터 변수에 대한 사용법 준수'),"
							+ "('COD001_005','COD001','점검항목','REST 통신','URI 작성시 bff context path(bffui)는 명시하지 않아야 함(공통으로 처리 됨)'),"
							+ "('COD001_006','COD001','점검항목','REST 통신','submission의 mode는 항상 asynchronous 사용해야 함'),"
							+ "('COD001_007','COD001','점검항목','REST 통신','subimssion done에서 결과 판별 시 응답헤더의 결과 code값을 확인해야 함'),"
							+ "('COD001_008','COD001','점검항목','개발표준 가이드','전역 함수를 선언하지 않아야 함'),"
							+ "('COD001_009','COD001','점검항목','개발표준 가이드','전역 변수를 선언하지 않아야 함'),"
							+ "('COD001_010','COD001','점검항목','개발표준 가이드','window.eval 함수를 사용하지 않아야 함'),"
							+ "('COD001_011','COD001','점검항목','개발표준 가이드','jQuery는 사용하지 않아야 함'),"
							+ "('COD001_012','COD001','점검항목','개발표준 가이드','부모창 객체 직접 접근하지 않아야 함'),"
							+ "('COD001_013','COD001','점검항목','개발표준 가이드','팝업창에서 오프너 창 직접 접근하지 않아야 함'),"
							+ "('COD001_014','COD001','점검항목','개발표준 가이드','브라우저 객체(DOM)을 직접 제어하는 API는 사용하지 않아야 함'),"
							+ "('COD001_015','COD001','점검항목','개발표준 가이드','브라우저 객체에 Event를 직접 제어하는 코드는 사용하지 않아야 함'),"
							+ "('COD001_016','COD001','점검항목','개발표준 가이드','XMLHttpRequest를 직접 생성하고 조작하는 코드는 사용하지 않아야 함'),"
							+ "('COD001_017','COD001','점검항목','개발표준 가이드','화면 간에 데이터를 주고 받을 때는 문자열로 Serialize해서 전달해야 함'),"
							+ "('COD001_018','COD001','점검항목','공통API사용','공통 API를 적절히 사용해야 함'),"
							+ "('COD001_019','COD001','점검항목','예외처리','오류발생시 적절하게 예외처리를 해야 함'),"
							+ "('COD001_020','COD001','점검항목','메시지','메시지 사용 시 가이드를 준수하여 메시지 코드를 작성해야 함');");
				}

				result = 0;
				rs = stmt.executeQuery("SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'INSPECTION' AND TABLE_NAME = 'COMCODEDETAILLIST'");
				if(rs.next()) {
					result = Integer.valueOf(rs.getString(1));
				}
				
				if (result == 0) {
					JOptionPane.showMessageDialog(jFrame, "COMCODEDETAILLIST가 존재하지 않아 TABLE를 생성합니다.");
					query = "CREATE TABLE COMCODEDETAILLIST ("
							+ "CODE VARCHAR(15) NOT NULL, "
							+ "PCODE VARCHAR(15) NOT NULL, "
							+ "PCODE_NM VARCHAR(30) NOT NULL, "
							+ "CODE_NM VARCHAR(60) NULL, "
							+ "CODE_DC VARCHAR(200) NULL, "
							+ "DESCRIPT VARCHAR(200), "
							+ "REGIST_DATE DATE NOT NULL DEFAULT CURRENT_TIMESTAMP(6),"
							+ "UPDATE_DATE DATE NULL"
							+ ") CHARSET=utf8mb4";
					stmt.executeQuery(query);
				}

				result = 0;
				stmt.executeQuery("USE INSPECTION;");
				rs = stmt.executeQuery("SELECT 1 FROM COMCODEDETAILLIST");
				if(rs.next()) {
					result = Integer.valueOf(rs.getString(1));
				}
				
				if (result == 0) {
					JOptionPane.showMessageDialog(jFrame, "COMCODEDETAILLIST에 DATA가 존재하지 않아 데이터를 생성합니다.");
					stmt.executeQuery("INSERT INTO COMCODEDETAILLIST"
							+ "("
							+ "CODE,"
							+ "PCODE,"
							+ "PCODE_NM,"
							+ "CODE_NM,"
							+ "CODE_DC,"
							+ "DESCRIPT"
							+ ") VALUES"
							+ "('01','COD001_001','명명규칙','w2:autoComplete','acb','자동완성 셀렉트박스'),"
							+ "('02','COD001_001','명명규칙','xf:trigger','btn','버튼'),"
							+ "('03','COD001_001','명명규칙','w2:anchor','btn','버튼'),"
							+ "('04','COD001_001','명명규칙','w2:inputCalendar','ica','날짜 입력용 inputbox'),"
							+ "('05','COD001_001','명명규칙','w2:calendar','cal','달력'),"
							+ "('06','COD001_001','명명규칙','xf:select','cbx','체크박스'),"
							+ "('07','COD001_001','명명규칙','xf:checkcombobox','ccb','체크박스콤보'),"
							+ "('08','COD001_001','명명규칙','w2:chart','cht','chart'),"
							+ "('09','COD001_001','명명규칙','w2:fusionchart','cht','fusionchart'),"
							+ "('10','COD001_001','명명규칙','w2:dataList','dtl','리스트 형태의 multi row 데이터 저장'),"
							+ "('11','COD001_001','명명규칙','w2:dataMap','dma','맵 형태의 단건 Row 데이터 저장'),"
							+ "('12','COD001_001','명명규칙','w2:datePicker','dpk','datepicker'),"
							+ "('13','COD001_001','명명규칙','w2:editor','edt','에디터'),"
							+ "('14','COD001_001','명명규칙','w2:floatingLayer','flt','floatingLayer'),"
							+ "('15','COD001_001','명명규칙','w2:fliptoggle','ftg','Fliptoggle'),"
							+ "('16','COD001_001','명명규칙','w2:generator','gen','제네레이터'),"
							+ "('17','COD001_001','명명규칙','w2:grid','grd','그리드'),"
							+ "('18','COD001_001','명명규칙','w2:gridView','grd','그리드뷰'),"
							+ "('19','COD001_001','명명규칙','xf:group','grp','그룹'),"
							+ "('20','COD001_001','명명규칙','w2:iframe','ifm','iframe'),"
							+ "('21','COD001_001','명명규칙','xf:image','img','이미지'),"
							+ "('22','COD001_001','명명규칙','xf:input','ibx','입력박스'),"
							+ "('23','COD001_001','명명규칙','w2:linkeddatalist','ldt','링크드데이터리스트'),"
							+ "('24','COD001_001','명명규칙','w2:menu','mnu','메뉴'),"
							+ "('25','COD001_001','명명규칙','w2:multiupload','mpd','파일멀티 업로드'),"
							+ "('26','COD001_001','명명규칙','xf:select','msb','멀티셀렉트박스'),"
							+ "('27','COD001_001','명명규칙','xf:output','opt','아웃풋박스'),"
							+ "('28','COD001_001','명명규칙','w2:pageControl','pgc','페이지 컨트롤'),"
							+ "('29','COD001_001','명명규칙','w2:pageList','pgl','페이지 리스트'),"
							+ "('30','COD001_001','명명규칙','xf:select1','rad','라디오박스'),"
							+ "('31','COD001_001','명명규칙','xf:submission','sbm','서버와 통신기능 제공'),"
							+ "('32','COD001_001','명명규칙','xf:select1','sbx','셀렉트박스'),"
							+ "('33','COD001_001','명명규칙','xf:secret','sct','입력한 문자가 보이지 않는 입력박스'),"
							+ "('34','COD001_001','명명규칙','w2:slideHide','shd','슬라이드hide'),"
							+ "('35','COD001_001','명명규칙','w2:spinner','spi','스피터'),"
							+ "('36','COD001_001','명명규칙','w2:span','spn','span태그'),"
							+ "('37','COD001_001','명명규칙','xf:switch','swh','스위치'),"
							+ "('38','COD001_001','명명규칙','w2:tabs','tab','탭 컨트롤 안에 각 탭'),"
							+ "('39','COD001_001','명명규칙','w2:tabControl','tac','탭 컨트롤'),"
							+ "('40','COD001_001','명명규칙','xf:group','tbl','테이블 레이아웃'),"
							+ "('41','COD001_001','명명규칙','w2:tag','tag','태그'),"
							+ "('42','COD001_001','명명규칙','w2:treeview','trv','트리뷰'),"
							+ "('43','COD001_001','명명규칙','xf:textarea','txa','textarea'),"
							+ "('44','COD001_001','명명규칙','w2:textbox','tbx','텍스트박스'),"
							+ "('45','COD001_001','명명규칙','w2:upload','upd','1개의 파일 업로드'),"
							+ "('46','COD001_001','명명규칙','w2:windowContainer','wdc','MDI'),"
							+ "('47','COD001_001','명명규칙','w2:wframe','wfm','WFrame');"
							);
				}
				result = 0;
				rs = stmt.executeQuery("SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'INSPECTION' AND TABLE_NAME = 'INSPECTION_MAIN'");
				if(rs.next()) {
					result = Integer.valueOf(rs.getString(1));
				}
				
				if (result == 0) {
					JOptionPane.showMessageDialog(jFrame, "INSPECTION_MAIN가 존재하지 않아 TABLE를 생성합니다.");
					query = "CREATE TABLE INSPECTION_MAIN ("
							+ "F_PNO INT(10) NOT NULL AUTO_INCREMENT, "
							+ "F_ID VARCHAR(30) NOT NULL, "
							+ "F_NAME VARCHAR(30) NOT NULL, "
							+ "F_INSPECTION VARCHAR(1) NULL, "
							+ "SCRIPTLEN VARCHAR(30) NULL, "
							+ "REGIST_DATE DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
							+ "REGIST_ID VARCHAR(30) NULL, "
							+ "UPDATE_DATE DATETIME NULL, "
							+ "UPDATE_ID VARCHAR(30) NULL, "
							+ "CONSTRAINT INSPECTION_MAIN_PK PRIMARY KEY(F_PNO)"
							+ ") CHARSET=utf8mb4";
					stmt.executeQuery(query);
				}
				
				result = 0;
				rs = stmt.executeQuery("SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'INSPECTION' AND TABLE_NAME = 'INSPECTION_DETAIL'");
				if(rs.next()) {
					result = Integer.valueOf(rs.getString(1));
				}
				
				if (result == 0) {
					JOptionPane.showMessageDialog(jFrame, "INSPECTION_DETAIL이 존재하지 않아 TABLE를 생성합니다.");
					query = "CREATE TABLE INSPECTION_DETAIL ("
							+ "I_PNO INT(10) NOT NULL AUTO_INCREMENT, "
							+ "F_PNO INT(10) NOT NULL, "
							+ "CHK_TYPE VARCHAR(30) NULL,"
							+ "CHK_TYPENM VARCHAR(30) NULL, "
							+ "CHK_PCODE VARCHAR(15) NULL,"
							+ "CHK_PCODENM VARCHAR(30) NULL,"
							+ "CHK_CODE VARCHAR(15) NULL,"
							+ "CHK_CODENM VARCHAR(100) NULL,"
							+ "CHK_STATUS VARCHAR(1) NULL,"
							+ "CHK_RESULT VARCHAR(500) NULL,"
							+ "REGIST_DATE DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
							+ "REGIST_ID VARCHAR(30) NULL,"
							+ "UPDATE_DATE DATETIME NULL,"
							+ "UPDATE_ID VARCHAR(30) NULL,"
							+ "CONSTRAINT INSPECTION_DETAIL_PK PRIMARY KEY(I_PNO)"
							+ ") CHARSET=utf8mb4";
					stmt.executeQuery(query);
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("[로드오류]\n" + e.getStackTrace());
			JOptionPane.showMessageDialog(jFrame, "[로드오류]\n" + e.getStackTrace());
		} catch (SQLException e) {
			System.out.println("[연결오류]\n" + e.getStackTrace());
			JOptionPane.showMessageDialog(jFrame, "[연결오류]\n" + e.getStackTrace());
		} finally {
			closeDatabase();
		}
	}
	
	public void closeDatabase() {
		try {
			if (conn != null) {
				conn.close();
			}
			
			if(stmt != null) {
				stmt.close();
			}
			
			if(rs != null) {
				rs.close();
			}
				 
		} catch (SQLException e) {
			System.out.println("[닫기오류]\n" + e.getStackTrace());
		}
	}
}
