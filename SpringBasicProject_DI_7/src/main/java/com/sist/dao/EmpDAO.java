package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.dao.*;
import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class EmpDAO {
	@Autowired // 자동 메모라 주소 설정
	private EmpMapper mapper;
	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
}
