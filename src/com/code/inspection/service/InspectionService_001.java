package com.code.inspection.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.code.inspection.dao.ComCodeDAO;
import com.code.inspection.dao.ComCodeDetailListTO;
import com.code.inspection.dao.ComCodeDetailTO;
import com.code.inspection.dao.InspectionMainTO;
import com.code.inspection.model.InspectionErrorModel;

public class InspectionService_001 {
	private static InspectionMainTO mainData = new InspectionMainTO();
	private static NodeList nodes;
	private static String comCd;
	
	public InspectionService_001(NodeList nodes, InspectionMainTO mainData, ComCodeDetailTO comcodeDetail) {
		this.mainData = mainData;
		this.nodes = nodes;
		this.comCd = comcodeDetail.getCode();
	}
	
	public static void validate() {
		for(int temp = 0; temp < nodes.getLength(); temp++) {
			Node nNode = nodes.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				process(eElement, nNode);
			}
		}
	}
	
	private static void process(Element eElement, Node nNode) {
		String compType = nNode.getNodeName();
		String compTypeNm = nNode.getNodeName();
		String compId = eElement.getAttribute("id");
		String renderType = eElement.getAttribute("renderType");
		String tagName = eElement.getAttribute("tagname");
		String prefix = "";
		matchProcess(compType, compTypeNm, renderType, compId, tagName);
	}
	
	private static void matchProcess(String compType, String compTypeNm, String renderType, String compId, String tagName) {
		HashMap<String, String> errorData = new HashMap<String, String>();
		String mainFPno = mainData.getF_pno().toString();
		ComCodeDAO comcode = new ComCodeDAO();
		ArrayList<ComCodeDetailListTO> comcodeDetailArrList = comcode.searchComCodeDetailList(comCd);
		String resultMessage = "";
		if(compId != null && compId != "") {
			for(ComCodeDetailListTO comcodeDetailList : comcodeDetailArrList) {
				String dCode = comcodeDetailList.getCode();
				String dCodeNm = comcodeDetailList.getCode_nm();
				String dPCode = comcodeDetailList.getPcode();
				String dPCodeNm = comcodeDetailList.getPcode_nm();
				String dCodeDc = comcodeDetailList.getCode_dc();
				String dDescript = comcodeDetailList.getDescript();
				if(compType.equals(dCodeNm)) {
					String prefix = dCodeDc + "_";
					resultMessage = "???????????? ??????????????? ?????????(?????????:" + compType + "??? ????????????ID(" + compId + ") ???????????? ???????????? ????????????. ???????????????: " + prefix + ")";
					//selectbox, radio, checkbox??? ??????
					if(compType.equals("xf:select") || compType.equals("xf:select1")) {
						if(renderType.equals("radiogroup") || renderType.equals("table")) {
							if(dCodeDc.equals("rad")) {
								if(compId.indexOf(prefix) < 0) {
									errorSave(mainFPno, dCode, dDescript, dPCode, dPCodeNm,compType,compTypeNm,resultMessage);
								}
							}
						} else if(renderType.equals("checkboxgroup")) {
							if(dCodeDc.equals("cbx")) {
								if(compId.indexOf(prefix) < 0) {
									errorSave(mainFPno, dCode, dDescript, dPCode, dPCodeNm,compType,compTypeNm,resultMessage);
								}
							}
						}
					} else if (compType.equals("xf:group")) {
						if(tagName.equals("tabel")) {
							if(dCodeDc.equals("tbl")) {
								if(compId.indexOf(prefix) < 0) {
									errorSave(mainFPno, dCode, dDescript, dPCode, dPCodeNm,compType,compTypeNm,resultMessage);
								}
							}
						}
					} else if (compType.equals("w2:wframe")) {
						if(!compId.equals("title")) {
							if(compId.indexOf(prefix) < 0) {
								errorSave(mainFPno, dCode, dDescript, dPCode, dPCodeNm,compType,compTypeNm,resultMessage);
							}
						}
					} else {
						if(compId.indexOf(prefix) < 0) {
							errorSave(mainFPno, dCode, dDescript, dPCode, dPCodeNm,compType,compTypeNm,resultMessage);
						}
					}
				}
			}
		} else {
			if(compType.equals("w2:require")) {
				resultMessage = "UDC????????????(UDC??? ???????????? ?????????????????? ?????? ???????????????.)";
				errorSave(mainFPno, "UDC", "UDC", "COD001_000", "?????????????????????",compType,compTypeNm,resultMessage);
			} else if(compType.equals("w2:wframe")) {
				if(!compId.equals("title")) {
					resultMessage = "PAGE HEADER Wframe??? id ???????????? 'title'";
					errorSave(mainFPno, "NONID", "NONID", "COD001_000", "?????????????????????",compType,compTypeNm,resultMessage);
				}
			}
		}
	}
	
	private static void errorSave(String f_pno, String chk_code, String chk_codenm, String chk_pcode, String chk_pcodenm, String chk_type, String chk_typenm, String chk_result) {
		HashMap<String, String> errorData = new HashMap<String, String>();
		errorData.put("f_pno", f_pno);
		errorData.put("chk_code", chk_code);
		errorData.put("chk_codenm", chk_codenm);
		errorData.put("chk_pcode", chk_pcode);
		errorData.put("chk_pcodenm", chk_pcodenm);
		errorData.put("chk_type", chk_type);
		errorData.put("chk_typenm", chk_typenm);
		errorData.put("chk_result", chk_result);
		InspectionErrorModel model = new InspectionErrorModel();
		model.insert(errorData);
	}
	
	private static void systemout(String renderType, String compType, String compId, String dCodeNm, String prefix, String tagName) {
		System.out.println("-------------------------------------");
		System.out.println("??????????????? :" + compType);
		System.out.println("???????????? ID :" + compId);
		System.out.println("????????? ????????? :" + dCodeNm);
		System.out.println("????????????ID :" + prefix);
		if(renderType != "") {
			System.out.println("???????????????(radio/checkbox) : " + renderType);
		}
		if(tagName != "") {
			System.out.println("????????? : " + tagName);
		}
		System.out.println("???????????? ??????????????? ?????????. ???????????? ???????????? ????????????.");
	}
}
