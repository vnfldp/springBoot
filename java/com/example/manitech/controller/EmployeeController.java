package com.example.manitech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.manitech.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.StringUtil;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
		
	/**
	 * 기본정보 목록
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("list2")
	public ModelAndView employeeList2(HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("views/employeeList2");
		
		Map vo =StringUtil.getParamListToOne(request.getParameterMap());
		
		// 목록 갯수
		int totalCnt = this.employeeService.selectEmployeeListCnt(vo);
		
		// 목록
		List<HashMap<String, Object>> list = null;
		JSONArray jsonArray = new JSONArray();
		
		if(totalCnt > 0){
			list = this.employeeService.selectEmployeeList(vo);
			
			for (Map<String,Object> map : list) {
				JSONObject json = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					json.put(key, value);
				}
				jsonArray.put(json);
			}
		}
		
		System.out.println("jsonArray:::"+ jsonArray);
		
		mv.addObject("totalCnt", totalCnt);
		mv.addObject("list", jsonArray);
		
		return mv;
	}
	
	/**
	 * 기본정보 목록 - ajax 호출
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("list3")
	@ResponseBody
	public Map<String, Object> employeeList3(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();
		
    	try {
			
			Map vo =StringUtil.getParamListToOne(request.getParameterMap());
			
			// 목록 갯수
			int totalCnt = this.employeeService.selectEmployeeListCnt(vo);
			
			// 목록
			List<HashMap<String, Object>> list = null;
			JSONArray jsonArray = new JSONArray();
			
			if(totalCnt > 0){
				list = this.employeeService.selectEmployeeList(vo);
				
				for (Map<String,Object> map : list) {
					JSONObject json = new JSONObject();
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						String key = entry.getKey();
						Object value = entry.getValue();
						json.put(key, value);
					}
					jsonArray.put(json);
				}
			}
			
			System.out.println("ajax jsonArray:::"+ jsonArray);
			
			resultMap.put("search", vo); 		// 검색어
			resultMap.put("totalCnt", totalCnt); 	// 목록개수
			resultMap.put("list", list); 		// 목록
	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return resultMap;
	}

	/**
	 * 개발자별 프로젝트 목록 - ajax 호출
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("detail")
	@ResponseBody
	public Map<String, Object> employeeProjectList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();
		
    	try {
			
			Map vo =StringUtil.getParamListToOne(request.getParameterMap());
			
			// 목록 갯수
			int totalCnt = this.employeeService.selectEmployeeProjectListCnt(vo);
			
			// 목록
			List<HashMap<String, Object>> list = null;
			JSONArray jsonArray = new JSONArray();
			
			if(totalCnt > 0){
				list = this.employeeService.selectEmployeeProjectList(vo);
				
				for (Map<String,Object> map : list) {
					JSONObject json = new JSONObject();
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						String key = entry.getKey();
						Object value = entry.getValue();
						json.put(key, value);
					}
					jsonArray.put(json);
				}
			}
			

			// 목록 갯수
			int totalCnt2 = this.employeeService.selectEmployeeNoteListCnt(vo);
			
			// 목록
			List<HashMap<String, Object>> list2 = null;
			JSONArray jsonArray2 = new JSONArray();
			
			if(totalCnt2 > 0){
				list2 = this.employeeService.selectEmployeeNoteList(vo);
				
				for (Map<String,Object> map : list2) {
					JSONObject json = new JSONObject();
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						String key = entry.getKey();
						Object value = entry.getValue();
						json.put(key, value);
					}
					jsonArray2.put(json);
				}
			}
			
			System.out.println("ajax jsonArray:::"+ jsonArray);
			System.out.println("ajax jsonArray2:::"+ jsonArray2);
			
			resultMap.put("search", vo); 			// 검색어
			resultMap.put("totalCnt", totalCnt); 	// 개발자별 프로젝트 목록개수
			resultMap.put("list", list); 			// 개발자별 프로젝트  목록
			resultMap.put("totalCnt2", totalCnt2); 	// 개발자별 상담 목록개수
			resultMap.put("list2", list2); 			// 개발자별 상담 목록
	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return resultMap;
	}
	
	/**
	 * 개발자별 상담내용 저장 - ajax 호출
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("save")
	@ResponseBody
	public Map<String, Object> employeeCallSave(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();
		
    	try {
			
			Map vo =StringUtil.getParamListToOne(request.getParameterMap());

			int saveCnt = this.employeeService.insertEmployeeCallSave(vo);
			
			System.out.println("ajax saveCnt:::"+ saveCnt);
			
			resultMap.put("search", vo); 			// 검색어
			resultMap.put("saveCnt", saveCnt); 	// 개발자별 프로젝트 목록개수
	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return resultMap;
	}
	
}