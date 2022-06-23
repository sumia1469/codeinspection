package com.code.inspection.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.code.inspection.dao.ComCodeDAO;
import com.code.inspection.dao.ComCodeDetailTO;
import com.code.inspection.dao.InspectionMainTO;
import com.code.inspection.model.InspectionModel;

public class InspectionMainService {
	private File[] fileList;
	private static Integer fileLen = 0;
	
	public static void InspectionMainService() {}
	public Integer search(String args) throws IOException {
		System.setProperty("polyglot.engine.WarnInterpreterOnly", "false");
		File rw = new File(args);
		File[] fileList = rw.listFiles();
		fileLen = fileList.length;
		getFile(fileList);
		return fileLen;
	}
	
	private static void getFile(File[] fileList) throws IOException {
		for(File file:fileList) {
			if(file.isFile()) {
				try {
					try {
						documentParse(file);
					} catch (XPathExpressionException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void documentParse(File file) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
		List<HashMap<String, String>> fileDataList = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(file);
		document.getDocumentElement().normalize();
		XPath xpath = XPathFactory.newInstance().newXPath();
		
		//STEP
		systemout(file.getName());
		InspectionMainTO mainData = new InspectionMainTO();
		mainData = insertMainData(document, file.getName());
		codeInspection(document,mainData);
	}
	
	private static InspectionMainTO insertMainData(Document document, String fileName) throws XPathExpressionException {
		HashMap<String, String> fileData = new HashMap<String, String>();
		XPath xpath = XPathFactory.newInstance().newXPath();
		String fileId = fileName;
		String programName = (String) xpath.evaluate("//head/@meta_programName", document, XPathConstants.STRING);
		
		fileData.put("f_id", fileId);
		if("".equals(programName) || programName == null) {
			programName = "프로그램명 없음";
		}
		fileData.put("f_name", programName);
		InspectionModel model = new InspectionModel();
		model.insert(fileData);
		InspectionMainTO result = new InspectionMainTO();
		result = model.searchLastList();
		return result;
	}
	
	private static void codeInspection(Document document, InspectionMainTO mainData) throws XPathExpressionException {
		XPath xpath = XPathFactory.newInstance().newXPath();
		ComCodeDAO comcode = new ComCodeDAO();
		ArrayList<ComCodeDetailTO> comcodeDetailArr = comcode.searchComCodeDetail("COD001");
		for(ComCodeDetailTO comcodeDetail : comcodeDetailArr) {
			String comCd = comcodeDetail.getCode();
			if(comCd.equals("COD001_001")) {
				NodeList nodes = (NodeList) xpath.evaluate("//*", document, XPathConstants.NODESET);
				InspectionService_001 inspectionService001 = new InspectionService_001(nodes, mainData, comcodeDetail);
				inspectionService001.validate();
			} else {
				String scriptNode = (String) xpath.evaluate("//script/text()", document, XPathConstants.STRING);
				InspectionService_002 inspectionService002 = new InspectionService_002(scriptNode, mainData, comcodeDetail);
				inspectionService002.validate();
			}
		}
	}
	
	private static void systemout(String fileNm) {
		System.out.println("=========================================================");
		System.out.println("코드인스펙션 파일명 : " + fileNm);
	}
}
