package com.example.manitech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.manitech.mapper.CallMapper;

@Service
@Transactional
public class CallService {

	@Autowired
	CallMapper callMapper;

	public int selectCallListCnt(Map vo) {
		return callMapper.selectCallListCnt(vo);
	}

	public List<HashMap<String, Object>> selectCallList(Map vo) {
		return callMapper.selectCallList(vo);
	}

	public int selectCallCnt(Map vo) {
		return callMapper.selectCallCnt(vo);
	}

	public List<HashMap<String, Object>> selectCall(Map vo) {
		return callMapper.selectCall(vo);
	}

	public int saveCall(Map vo) {
		// 분기
		// insert , update
		return callMapper.insertCall(vo);
		//return callMapper.updateCall(vo);
	}

	public int deleteCall(Map vo) {
		return callMapper.deleteCall(vo);
	}

}
