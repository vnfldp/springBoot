package com.example.manitech.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.manitech.domain.EmployeeDto;
import com.example.manitech.domain.EmployeeProjectDto;
import com.example.manitech.mapper.EmployeeProjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
@Transactional
public class EmployeeProjectService {

	@Autowired
	EmployeeProjectMapper employeeProjectMapper;
	
	@SuppressWarnings("unchecked")
	public int selectEmployeeProjectCnt(Map vo) {
		return employeeProjectMapper.selectEmployeeProjectListCnt(vo);
	}
	
	//개발자 경력 조회
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectEmployeeProjectList(Map vo) {
		return employeeProjectMapper.selectEmployeeProjectList(vo);
	}
	
}
