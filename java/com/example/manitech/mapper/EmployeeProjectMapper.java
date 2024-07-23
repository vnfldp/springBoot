package com.example.manitech.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.manitech.domain.EmployeeProjectDto;

@Mapper
public interface EmployeeProjectMapper {
	
	//개발자
	int selectEmployeeProjectListCnt(Map vo);
	//개발자 경력 조회
	List<HashMap<String, Object>> selectEmployeeProjectList(Map vo);
	
}
