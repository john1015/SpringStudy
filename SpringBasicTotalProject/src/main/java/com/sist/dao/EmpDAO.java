package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class EmpDAO {
	@Autowired // 객체의 주소값만 주입이 가능 (일반 변수는 사용이 불가능)
	private EmpMapper mapper; // Spring => MyBatis에서 구현 => 구현한 클래스의 주소를 갖고 있음
	
	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
	public EmpVO empDetailData() {
		return mapper.empDetailData();
	}
	public List<String> empEnameList(){
		return mapper.empEnameList();
	}
	public List<EmpVO> empNameFindData(Map map){
		return mapper.empNameFindData(map);
	}
	public List<EmpVO> empFindData(Map map){
		return mapper.empFindData(map);
	}
}
