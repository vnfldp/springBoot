package com.example.manitech.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.manitech.domain.EmployeeDto;
import com.example.manitech.domain.detailVO;
import com.example.manitech.mapper.detailMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
@Transactional
public class detailService {

	@Autowired
	detailMapper detailmapper;
	
	//개발자 경력 조회
	public List<HashMap<String, Object>> selectEmployeeProjectList(HttpServletRequest request, detailVO detailVo) throws Exception {
		return detailmapper.selectEmployeeProjectList();
	}
	
}
