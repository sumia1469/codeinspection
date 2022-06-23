package com.code.inspection.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.SourceSection;
import org.graalvm.polyglot.Value;

import com.code.inspection.dao.ComCodeDAO;
import com.code.inspection.dao.ComCodeDetailListTO;
import com.code.inspection.dao.ComCodeDetailTO;
import com.code.inspection.dao.InspectionMainTO;
import com.code.inspection.model.InspectionErrorModel;
import com.code.inspection.model.InspectionModel;

public class InspectionService_002 {
	private static InspectionMainTO mainData = new InspectionMainTO();
	private static String nodes;
	private static String comCd;
	private static String comCdNm;
	private static String comCdDc;
	private static int scriptlen = 0;
	
	public InspectionService_002(String nodes, InspectionMainTO mainData, ComCodeDetailTO comcodeDetail) {
		this.mainData = mainData;
		this.nodes = nodes;
		this.comCd = comcodeDetail.getCode();
		this.comCdNm = comcodeDetail.getCode_nm();
		this.comCdDc = comcodeDetail.getCode_dc();
	}
	
	public static void validate() {
		try(Context context = Context.create("js")){
			context.eval("js","var scwin = {};");
			context.eval("js","var com = {};");
			context.eval("js","var ucube = {};");
			context.eval("js","ucube.com = {};");
			context.eval("js","com.cf = {};");
			context.eval("js","com.cf.date = {}");
			context.eval("js","com.cf.date.getServerDateTime = function(){return '20220524'};");
			context.eval("js","ucube.nucr = {};");
			context.eval("js","ucube.nucr.cf = {};");
			context.eval("js","ucube.nucr.cf.getCustInfo = function(){return {custId:'a'}};");
			context.eval("js",nodes);
			context.eval("js","function returnType(func){return typeof func};");
			process(context);
			context.close();
			setScriptLen();
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private static void process(Context context) {
		String mainFPno = mainData.getF_pno().toString();
		Value bindings = context.getBindings("js");
		if(bindings == null) {
			
		} else {
			for(String key : bindings.getMemberKeys()) {
				Value subBindings = bindings.getMember(key);
				String gType = getType(context, subBindings, key, 1);
				Integer gLen = subBindings.getMemberKeys().size();
				if(gLen > 0) {
					Integer serverMessageCodeLen = 0;
					Integer scopeVariableLen = 0;
					Integer nuuxUse = 0;
					Integer mathUse = 0;
					Integer numberUse = 0;
					Integer inlineCodeUse = 0;
					
					for(String key2 : subBindings.getMemberKeys()) {
						Value innerBindings = subBindings.getMember(key2);
						String iType = getType(context, innerBindings, key2, 2);
						if("function".equals(iType)) {
							try (Context tmpContext = Context.create("js")){
								tmpContext.eval("js","var scwin = {};");
								tmpContext.eval("js","var com = {};");
								tmpContext.eval("js","var ucube = {};");
								tmpContext.eval("js","ucube.com = {};");
								tmpContext.eval("js","com.cf = {};");
								tmpContext.eval("js","com.cf.date = {}");
								tmpContext.eval("js","com.cf.date.getServerDateTime = function(){return '20220524'};");
								tmpContext.eval("js","ucube.nucr = {};");
								tmpContext.eval("js","ucube.nucr.cf = {};");
								tmpContext.eval("js","ucube.nucr.cf.getCustInfo = function(){return {custId:'a'}};");
								SourceSection sourceLocation = innerBindings.getSourceLocation();
								Integer startLine = sourceLocation.getStartLine() + 1;
								Integer endLine = sourceLocation.getEndLine();
								scriptlen = endLine;
								for(Integer i = startLine;i < endLine; i++) {
									Integer lineNum = (i - 1);
									String inspectCode = sourceLocation.getSource().getCharacters(i).toString();
									if(!"".equals(inspectCode)) {
										switch (comCd) {
											case "COD001_002" : //주석표준 준수
												break;
											case "COD001_003" : //API에 따라 GET,POST,PUT,DELETE 메소드 적절히 구분
												break;
											case "COD001_004" : //URI 및 전달 데이터 변수에 대한 사용법 준수
												break;
											case "COD001_005" : //URI 작성히 bff context path(bffui)는 명시하지 않아야 함(공통으로 처리됨)
												break;
											case "COD001_006" : //submission의 mode는 항상 asynchronous 사용해야 함.
												break;
											case "COD001_007" : //submission done에서 결과 판별 시 응답헤더의 결과 code값을 확인해야 함.
												break;
											case "COD001_008" : //전역 함수를 선언하지 않아야 함.
												break;
											case "COD001_009" : //전역 변수를 선언하지 않아야 함.
												break;
											case "COD001_010" : //window.eval 함수를 사용하지 않아야 함
												break;
											case "COD001_011" : //jQuery는 사용하지 않아야 함
												break;
											case "COD001_012" : //부모창 객체 직접 접근하지 않아야 함
												break;
											case "COD001_013" : //팝업창에서 오프너 창 직접 접근하지 않아야 함
												break;
											case "COD001_014" : //브라우저 객체(DOM)을 직접 제어하는 API는 사용하지 않아야 함
												break;
											case "COD001_015" : //브라우저 객체에 Event를 직접 제어하는 코드는 사용하지 않아야 함
												break;
											case "COD001_016" : //XMLHttpRequest를 직접 생성하고 조작하는 코드는 사용하지 않아야 함
												break;
											case "COD001_017" : //화면 간에 데이터를 주고 받을 때는 문자열로 Serialize해서 전달해야 함
												break;
											case "COD001_018" : //공통 API를 적절히 사용해야 함
												break;
											case "COD001_019" : //오류 발생시 적절하게 예외처리를 해랴 함.
												break;
											case "COD001_020" : //메시지 사용시 가이드를 준수하여 메시지 코드를 작설해야함
												break;
											default :
												break;
										}
									}
								}
								tmpContext.close();
							} catch(Exception e) {
								System.err.println(e);
							}
						}
					}
					String resultMessage = "";
					if("COD001_018".equals(comCd)) {
						//COD001_018 : 공통 API를 적절히 사용해야 함 : scopeVariableLen 사용금지
						if(scopeVariableLen > 0) {
							resultMessage = "공통 API를 적절히 사용해야 함 : scopeVariableLen 사용금지";
							errorSave(mainFPno, "script", "script", comCd, comCdNm, "0", "script", resultMessage);
						}
						
						if(nuuxUse > 0) {
							resultMessage = "공통 API를 적절히 사용해야 함 : com_nuux.js uri에 mip가 들어가는 transaction함수는 fail\r\n"
									+ "done처리가 없는 선전환코드(선전환 코드를 차세대에서 사용시fail)";
							errorSave(mainFPno, "script", "script", comCd, comCdNm, "0", "script", resultMessage);
						}
						
						if(mathUse > 0) {
							resultMessage = "공통 API를 적절히 사용해야 함 : Math사용(ucube.com.cf.win.round/floor/cell 사용가능)";
							errorSave(mainFPno, "script", "script", comCd, comCdNm, "0", "script", resultMessage);
						}
						
						if(numberUse > 0) {
							resultMessage = "공통 API를 적절히 사용해야 함 : Number(ucube.com.cf.parseInt 사용가능)";
							errorSave(mainFPno, "script", "script", comCd, comCdNm, "0", "script", resultMessage);
						}
						
						if(inlineCodeUse > 0) {
							resultMessage = "공통 API를 적절히 사용해야 함 : wbrowser에서 border문제 발생. 동적 UI inline style코드 사용시(border:1px) 선이 깨짐 증상";
							errorSave(mainFPno, "script", "script", comCd, comCdNm, "0", "script", resultMessage);
						}
						
						if("scwin".equals(key)) {
							Integer onloadcompleteLen = 0;
							Integer onpageunloadLen = 0;;
							Integer onpageloadLen = 0;
							for(String keyNm : subBindings.getMemberKeys()) {
								if("onloadcompleted".equals(keyNm)) {
									onloadcompleteLen++;
								} else if ("onpageunload".equals(keyNm)) {
									onpageunloadLen++;
								} else if ("onpageload".equals(keyNm)) {
									onpageloadLen++;
								}
							}

							if(onloadcompleteLen == 0) {
								resultMessage = "공통 API를 적절히 사용해야 함 : scwin.onloadcompleted는 공통 필수 함수입니다.";
								errorSave(mainFPno, "script", "script", comCd, comCdNm, "0", "script", resultMessage);
							}

							if(onpageunloadLen == 0) {
								resultMessage = "공통 API를 적절히 사용해야 함 : scwin.onpageunload는 공통 필수 함수입니다.";
								errorSave(mainFPno, "script", "script", comCd, comCdNm, "0", "script", resultMessage);
							}

							if(onpageloadLen == 0) {
								resultMessage = "공통 API를 적절히 사용해야 함 : scwin.onpageload는 공통 필수 함수입니다.";
								errorSave(mainFPno, "script", "script", comCd, comCdNm, "0", "script", resultMessage);
							}
						}
					}
				}
			}
		}
	}
	
	private static String getType(Context context, Value subBindings, String key, Integer depth) {
		Value accumulatorFunc = context.getBindings("js").getMember("returnType");
		String returnType = accumulatorFunc.execute(subBindings).asString();
		return returnType;
	}
	
	private static void matchProcess(String compType, String compTypeNm, String renderType, String compId, String tagName) {
		
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
		System.out.println("검색노드명 :" + compType);
		System.out.println("컴포넌트 ID :" + compId);
		System.out.println("비교할 노드명 :" + dCodeNm);
		System.out.println("프리픽스ID :" + prefix);
		if(renderType != "") {
			System.out.println("렌더링타입(radio/checkbox) : " + renderType);
		}
		if(tagName != "") {
			System.out.println("태그명 : " + tagName);
		}
		System.out.println("컴포넌트 명명규칙을 미준수. 식별자가 유효하지 않습니다.");
	}
	
	private static void setScriptLen() {
		HashMap<String, String> fileData = new HashMap<String, String>();
		String scriptLen = "SCRIPT : " + scriptlen + "line";
		fileData.put("scriptlen", scriptLen);
		fileData.put("f_pno", mainData.getF_pno().toString());
		InspectionModel model = new InspectionModel();
		model.insertScriptlen(fileData);
	}
}
