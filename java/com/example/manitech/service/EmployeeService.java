package com.example.manitech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.manitech.mapper.EmployeeMapper;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;
	
	public int selectEmployeeListCnt(Map vo) {
		return employeeMapper.selectEmployeeListCnt(vo);
	}

	public List<HashMap<String, Object>> selectEmployeeList(Map vo) {
		return employeeMapper.selectEmployeeList(vo);
	}

	public int selectEmployeeProjectListCnt(Map vo) {
		return employeeMapper.selectEmployeeProjectListCnt(vo);
	}

	public List<HashMap<String, Object>> selectEmployeeProjectList(Map vo) {
		return employeeMapper.selectEmployeeProjectList(vo);
	}

	public int selectEmployeeNoteListCnt(Map vo) {
		return employeeMapper.selectEmployeeNoteListCnt(vo);
	}

	public List<HashMap<String, Object>> selectEmployeeNoteList(Map vo) {
		return employeeMapper.selectEmployeeNoteList(vo);
	}

	public int insertEmployeeCallSave(Map vo) {
		return employeeMapper.insertEmployeeCallSave(vo);
	}

}
