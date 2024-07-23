package com.example.manitech.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {
	// 개발자 목록
	int selectEmployeeListCnt(Map vo);
	List<HashMap<String, Object>> selectEmployeeList(Map vo);
	// 개발자별 프로젝트 목록
	int selectEmployeeProjectListCnt(Map vo);
	List<HashMap<String, Object>> selectEmployeeProjectList(Map vo);
	// 개발자별 상담 목록
	int selectEmployeeNoteListCnt(Map vo);
	List<HashMap<String, Object>> selectEmployeeNoteList(Map vo);
	// 상담내용 저장
	int insertEmployeeCallSave(Map vo);
}