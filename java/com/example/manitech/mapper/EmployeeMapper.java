package com.example.manitech.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {
	int selectEmployeeListCnt(Map vo);
	List<HashMap<String, Object>> selectEmployeeList(Map vo);
}