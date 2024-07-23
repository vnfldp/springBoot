package com.example.manitech.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CallMapper {
	// 상담내용 목록
	int selectCallListCnt(Map vo);
	List<HashMap<String, Object>> selectCallList(Map vo);
	// 상담내용 조회
	int selectCallCnt(Map vo);
	List<HashMap<String, Object>> selectCall(Map vo);
	// 상담내용 저장
	int insertCall(Map vo);
	// 상담내용 수정
	int updateCall(Map vo);
	// 상담내용 삭제
	int deleteCall(Map vo);
}
