package com.example.manitech.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.manitech.domain.detailVO;

@Mapper
public interface detailMapper {
	
	//개발자 경력 조회
	List<HashMap<String, Object>> selectEmployeeProjectList();
	
}
