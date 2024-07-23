package com.example.manitech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.manitech.domain.EmployeeDto;
import com.example.manitech.domain.EmployeeProjectDto;
import com.example.manitech.service.EmployeeProjectService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.StringUtil;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/employee")
public class EmployeeProjectController {

	@Autowired
	private EmployeeProjectService employeeProjectService;
	
	/**
	 * 프로젝트 목록
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("EmployeeProjectList")
	public ModelAndView employeeProjectList(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("views/employeeProjectList");
				
		Map vo =StringUtil.getParamListToOne(request.getParameterMap());
		
		// 목록 갯수
		int totalCnt = this.employeeProjectService.selectEmployeeProjectCnt(vo);
		
		// 목록
		List<HashMap<String, Object>> list = null;
		JSONArray jsonArray = new JSONArray();
		
		if(totalCnt > 0) {
			list = this.employeeProjectService.selectEmployeeProjectList(vo);
			
			for (Map<String, Object> map : list) {
				JSONObject json = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					json.put(key, value);
				}
				jsonArray.put(json);
			}
		}
		
		mv.addObject("totalCnt", totalCnt);
		mv.addObject("list", jsonArray);
		
		return mv;
	}
	
	/**
	 * 프로젝트 목록(검색시 동작) - ajax 호출
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("SearchEmployeeProjectList")
	@ResponseBody
	public Map<String, Object> employeeList3(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();
		
    	try {
			
			Map vo =StringUtil.getParamListToOne(request.getParameterMap());
			
			// 목록 갯수
			int totalCnt = this.employeeProjectService.selectEmployeeProjectCnt(vo);
			
			// 목록
			List<HashMap<String, Object>> list = null;
			JSONArray jsonArray = new JSONArray();
			
			if(totalCnt > 0){
				list = this.employeeProjectService.selectEmployeeProjectList(vo);
				
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
			
			resultMap.put("search", vo); 		// 검색어
			resultMap.put("totalCnt", totalCnt); 	// 목록개수
			resultMap.put("list", list); 		// 목록
	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return resultMap;
	}
	
}
