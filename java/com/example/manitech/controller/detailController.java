package com.example.manitech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.manitech.domain.EmployeeDto;
import com.example.manitech.domain.detailVO;
import com.example.manitech.service.detailService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class detailController {

	@Autowired
	private detailService detailservice;
	
	@GetMapping(value = "/EmployeeProjectList")
	public ModelAndView detlList(HttpServletRequest request, detailVO detailVo) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("views/employeePjList");
		
		List<HashMap<String, Object>> list = null;
		list = this.detailservice.selectEmployeeProjectList(request, detailVo);
		
		mv.addObject("list", list);
		mv.addObject("detailVO", detailVo);
		return mv;
	}
	
}
