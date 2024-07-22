package com.example.manitech.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.manitech.domain.EmployeeDto;
import com.example.manitech.mapper.EmployeeMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;
	
	@SuppressWarnings("unchecked")
	public int selectEmployeeListCnt(Map vo) {

        @SuppressWarnings("rawtypes")
		List list = new ArrayList();
        if ("true".equals( vo.get("grade1"))) {
        	list.add("특급");
        }
        if ("true".equals( vo.get("grade2"))) {
        	list.add("고급");
        }
        if ("true".equals( vo.get("grade3"))) {
        	list.add("중급");
        }
        if ("true".equals( vo.get("grade4"))) {
        	list.add("초급");
        }
        vo.put("list", list);
        
		return employeeMapper.selectEmployeeListCnt(vo);
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectEmployeeList(Map vo) {

        @SuppressWarnings("rawtypes")
		List list = new ArrayList();
        if ("특급".equals( vo.get("grade1"))) {
        	list.add("특급");
        }
        if ("고급".equals( vo.get("grade2"))) {
        	list.add("고급");
        }
        if ("중급".equals( vo.get("grade3"))) {
        	list.add("중급");
        }
        if ("초급".equals( vo.get("grade4"))) {
        	list.add("초급");
        }
        vo.put("list", list);
        
		return employeeMapper.selectEmployeeList(vo);
	}
}
