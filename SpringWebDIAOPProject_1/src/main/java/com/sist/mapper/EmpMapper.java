package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.EmpVO;

public interface EmpMapper {
	@Select("select empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday , sal "
			+ "  from emp order by empno asc")
	public List<EmpVO> empListData();
	
}
